package com.CometEngine.Util.Images.JPEG;

import java.nio.IntBuffer;

public class JPEGImage {
	private boolean mIsDamaged;
	private int[][] mBuffers;
	private int[] mLastMCUPosition;
	private int mMCUWidth;
	private int mMCUHeight;
	private int mComponents;

	private final static int FP_SCALEBITS = 16;
	private final static int FP_HALF = 1 << (FP_SCALEBITS - 1);
	private final static int FP_140200 = (int) (0.5 + (1 << FP_SCALEBITS) * 1.402);
	private final static int FP_034414 = (int) (0.5 + (1 << FP_SCALEBITS) * 0.34414);
	private final static int FP_071414 = (int) (0.5 + (1 << FP_SCALEBITS) * 0.71414);
	private final static int FP_177200 = (int) (0.5 + (1 << FP_SCALEBITS) * 1.772);

	int[] pixelate;

	// private final static int[] ycbcrConversionCR = new int[256 + 128];
	// private final static int[] ycbcrConversionCB = new int[256 + 128];
	// private final static int[] ycbcrConversionCBCR = new int[(256 + 128) *
	// (256 + 128)];
	//
	//
	// stati
	// {
	// for (int i = 0; i < 256; i++)
	// {
	// ycbcrConversionCR[i + 64] = (int)Math.round(1.402 * (i - 128));
	// ycbcrConversionCB[i + 64] = (int)Math.round(1.772 * (i - 128));
	// for (int j = 0; j < 256; j++)
	// {
	// ycbcrConversionCBCR[((i + 64) << 8) + (j + 64)] =
	// (int)Math.round(-0.34414 * (i - 128) - 0.71414 * (j - 128));
	// }
	// }
	// }

	int rwidth = 0;
	int rheight = 0;

	JPEGImage(int aWidth, int aHeight, Point aMaxSampling, int aDensitiesUnits, Point aDensity, int aComponents) {

		pixelate = new int[aWidth * aHeight];
		rwidth = aWidth;
		rheight = aHeight;

		mBuffers = new int[JPEGImageReader.MAX_CHANNELS][aMaxSampling.x * aMaxSampling.y * 64];
		mMCUWidth = 8 * aMaxSampling.x;
		mMCUHeight = 8 * aMaxSampling.y;
		mLastMCUPosition = new int[2];
		mComponents = aComponents;

		// Log.out.println(mMCUWidth+" "+mMCUHeight);
	}

	void setDamaged() {
		mIsDamaged = true;
		int[] pixels = pixelate;

		int w = mLastMCUPosition[0] + mMCUWidth;
		for (int y = mLastMCUPosition[1], yy = y * rwidth; y < rheight; y++, yy += rwidth) {
			for (int x = w; x < rwidth; x++) {
				pixels[x + yy] = (((x >> 3) & 1) ^ ((y >> 3) & 1)) == 0 ? 0xffe0e0e0 : 0xffffffff;
			}
		}
		for (int y = mLastMCUPosition[1] + mMCUHeight, yy = y * rwidth; y < rheight; y++, yy += rwidth) {
			for (int x = 0; x < w; x++) {
				pixels[x + yy] = (((x >> 3) & 1) ^ ((y >> 3) & 1)) == 0 ? 0xffe0e0e0 : 0xffffffff;
			}
		}
	}

	/*
	 * 1x1 1x1 1x1 2x1 1x1 1x1 1x2 1x1 1x1 2x2 1x1 1x1 2x2 2x1 2x1 4x2 1x1 1x1
	 * 2x4 1x1 1x1 4x1 1x1 1x1 1x4 1x1 1x1 4x1 2x1 2x1 1x4 1x2 1x2 4x4 2x2 2x2
	 */

	private void copyBlock(int[] dctCoefficients, int[] buffer, int w, int dst) {
		for (int src = 0; src < 64; dst += w, src += 8) {
			System.arraycopy(dctCoefficients, src, buffer, dst, 8);
		}
	}

	private void scaleBlock(int[] aCoefficients, int[] aBuffer, int q, int mcuWidth, int mcuHeight, int xShift,
			int yShift) {
		for (int y = 0; y < mcuHeight; y++, q += mcuWidth) {
			for (int x = 0, dst = q, src = (y >> yShift) * 8; x < mcuWidth; x++, dst++) {
				aBuffer[dst] = aCoefficients[(x >> xShift) + src];
			}
		}
	}

	void setData(int cx, int cy, Point aSampling, int aComponent, int[] aCoefficients) {
		int[] buffer = mBuffers[aComponent];

		if (mMCUWidth == 8 && mMCUHeight == 8) {
			System.arraycopy(aCoefficients, 0, buffer, 0, 64);
		} else if (mMCUWidth == 8 * aSampling.x && mMCUHeight == 8 * aSampling.y) {
			copyBlock(aCoefficients, buffer, mMCUWidth, 8 * cx + 8 * cy * mMCUWidth);
		} else {
			int mcuWidth = mMCUWidth;
			int mcuHeight = mMCUHeight;
			int blockWidth = mMCUWidth / aSampling.x;
			int blockHeight = mMCUHeight / aSampling.y;
			int xShift = blockWidth == 8 ? 0 : blockWidth == 16 ? 1 : blockWidth == 32 ? 2 : 3;
			int yShift = blockHeight == 8 ? 0 : blockHeight == 16 ? 1 : blockHeight == 32 ? 2 : 3;

			scaleBlock(aCoefficients, buffer, (cx * 8) + (cy * 8) * mcuWidth, mcuWidth, mcuHeight, xShift, yShift);
		}
	}

	void flushMCU(int x, int y) {
		x *= mMCUWidth;
		y *= mMCUHeight;

		mLastMCUPosition[0] = x;
		mLastMCUPosition[1] = y;

		int[] yComponent = mBuffers[0];
		int[] cbComponent = mBuffers[1];
		int[] crComponent = mBuffers[2];

		int[] raster = pixelate;
		int height = Math.min(y + mMCUHeight, rheight) * rwidth;
		int px0 = Math.min(mMCUWidth, rwidth - x);
		int pix = 0;
		if (mComponents == 1) {
			for (int py = y * rwidth, k = 0; py < height; py += rwidth, k += mMCUWidth) {
				for (int px = 0, i = k, j = x + py; px < px0; px++, i++, j++) {
					int lu = clamp(yComponent[i]);
					raster[j] = 0xff000000 | (lu << 16) + (lu << 8) + lu;
				}
			}
		} else {
			for (int py = y * rwidth, k = 0; py < height; py += rwidth, k += mMCUWidth) {
				for (int px = 0, i = k, j = x + py; px < px0; px++, i++, j++) {
					int lu = yComponent[i];
					int cb = cbComponent[i] - 128;
					int cr = crComponent[i] - 128;

					int r = clamp(lu + ((FP_HALF + FP_140200 * cr) >> FP_SCALEBITS));
					int g = clamp(lu - ((FP_HALF + FP_034414 * cb + FP_071414 * cr) >> FP_SCALEBITS));
					int b = clamp(lu + ((FP_HALF + FP_177200 * cb) >> FP_SCALEBITS));

					raster[j] = 0xff000000 | (r << 16) + (g << 8) + b;
				}
			}
		}

		// for (int py = y * imageWidth, k = 0; py < height; py += imageWidth, k
		// += mMCUWidth)
		// {
		// for (int px = pxStart - x, i = k, j = x + py; --px >= 0; i++, j++)
		// {
		// int lu = clamp(yComponent[i]);
		// int cb = cbComponent[i] + 64;
		// int cr = crComponent[i] + 64;
		//
		// int r = clamp(lu + ycbcrConversionCR[cr]);
		// int g = clamp(lu + ycbcrConversionCBCR[(cb << 8) + cr]);
		// int b = clamp(lu + ycbcrConversionCB[cb]);
		//
		// raster[j] = 0xff000000 | (r << 16) + (g << 8) + b;
		// }
		// }
	}

	public int[] getData() {
		return pixelate;
	}

	public int getWidth() {
		return rwidth;
	}

	public int getHeight() {
		return rheight;
	}

	private int clamp(int aValue) {
		return aValue < 0 ? 0 : aValue > 255 ? 255 : aValue;
	}

	public boolean isDamaged() {
		return mIsDamaged;
	}
}