package com.CometEngine.Resrouce;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.LinkedList;


import com.CometEngine.CometEngine;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Util.Buffer.CEBufferUtils;
import com.CometEngine.Util.Images.BMP.BmpImage;
import com.CometEngine.Util.Images.BMP.BmpReader;
import com.CometEngine.Util.Images.JPEG.JPEGImage;
import com.CometEngine.Util.Images.JPEG.JPEGImageReader;
import com.CometEngine.Util.Images.PNG.PNGDecoder;
import com.CometEngine.Util.Images.TGA.TGAImageData;

public class CEImageResrouceLoader {
	// TODO: Add the
	private static final LinkedList<CEImageResrouce> loaded = new LinkedList<CEImageResrouce>();
	private static final CEImageResrouceLoader instence = new CEImageResrouceLoader();

	public static CEImageResrouceLoader getInstence() {
		return instence;
	}

	public void LoadImage(final String filepath, final CEImageResrouce image) {
		CEFileReadHandle handel = null;
		if (filepath.endsWith("png")) {
			CEFileUtil.getInstence().ReadResoruceToAsync(filepath, handel = new CEFileReadHandle() {

				@Override
				public void failure(Throwable e) {
					System.err.println("FALLURE FILE LOAD");
					e.printStackTrace();
					image.setData(null);

				}

				@Override
				public void complite(ByteBuffer data) {

					data.flip();
					byte[] array = data.array().clone();
					ByteArrayInputStream stream;

					PNGDecoder decoder;
					ByteBuffer imbuf = null;
					try {
						decoder = new PNGDecoder(stream = new ByteArrayInputStream(array));
						imbuf = ByteBuffer.allocateDirect(4 * decoder.getHeight() * decoder.getWidth());
						decoder.decode(imbuf, decoder.getWidth() * 4, PNGDecoder.RGBA);
						imbuf.flip();
						image.setWidth(decoder.getWidth());
						image.setHeight(decoder.getHeight());
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					image.setData(imbuf);
					image.setIsLoaded(true);

				}
			});
		}
		if (filepath.endsWith("tga")) {
			CEFileUtil.getInstence().ReadResoruceToAsync(filepath, handel = new CEFileReadHandle() {

				@Override
				public void failure(Throwable e) {
					e.printStackTrace();
					image.setData(null);

				}

				@Override
				public void complite(ByteBuffer data) {

					data.flip();
					byte[] array = data.array().clone();

					ByteBuffer imbuf = null;
					TGAImageData tga = new TGAImageData();
					try {
						imbuf = tga.loadImage(new ByteArrayInputStream(array));
					} catch (IOException e) {
						e.printStackTrace();
					}

					image.setWidth(tga.getWidth());
					image.setHeight(tga.getHeight());

					image.setData(imbuf);
					image.setIsLoaded(true);

				}
			});

		}
		if (filepath.endsWith("bmp")) {
			CEFileUtil.getInstence().ReadResoruceToAsync(filepath, handel = new CEFileReadHandle() {

				@Override
				public void failure(Throwable e) {
					e.printStackTrace();
					image.setData(null);

				}

				@Override
				public void complite(ByteBuffer data) {

					data.flip();
					byte[] array = data.array().clone();
					ByteArrayInputStream stream;
					ByteBuffer imbuf = null;
					BmpImage bmp = null;
					try {
						bmp = BmpReader.read(new ByteArrayInputStream(array));
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}

					imbuf = ByteBuffer.allocateDirect(bmp.image.getWidth() * bmp.image.getHeight() * 3);
					for (int y = 0; y < bmp.image.getHeight(); y++) {
						for (int x = 0; x < bmp.image.getWidth(); x++) {
							int pixel = bmp.image.getRgb888Pixel(x, y);

							imbuf.put((byte) ((pixel >> 16) & 0xFF));
							imbuf.put((byte) ((pixel >> 8) & 0xFF));
							imbuf.put((byte) (pixel & 0xFF));
						}
					}
					imbuf.flip();
					image.setRGBType(CEGL.GL_RGB);
					image.setWidth(bmp.image.getWidth());
					image.setHeight(bmp.image.getHeight());

					image.setData(imbuf);
					image.setIsLoaded(true);

				}
			});
		}
		if (filepath.endsWith("jpg")) {
			CEFileUtil.getInstence().ReadResoruceToAsync(filepath, handel = new CEFileReadHandle() {

				@Override
				public void failure(Throwable e) {
					e.printStackTrace();
					image.setData(null);

				}

				@Override
				public void complite(ByteBuffer data) {

					byte[] array = data.array().clone();

					ByteBuffer imbuf = null;
					try {
						JPEGImage reade = JPEGImageReader.read(new ByteArrayInputStream(array));

						int[] pixels = reade.getData();
						imbuf = ByteBuffer.allocateDirect(pixels.length * 3);
						for (int y = 0; y < reade.getHeight(); y++) {
							for (int x = 0; x < reade.getWidth(); x++) {
								int pixel = pixels[y * reade.getWidth() + x];

								imbuf.put((byte) ((pixel >> 16) & 0xFF));
								imbuf.put((byte) ((pixel >> 8) & 0xFF));
								imbuf.put((byte) (pixel & 0xFF));
							}
						}
						imbuf.flip();
						image.setWidth(reade.getWidth());
						image.setHeight(reade.getHeight());
					} catch (Exception e) {

						e.printStackTrace();
					}

					image.setRGBType(CEGL.GL_RGB);

					image.setData(imbuf);
					image.setIsLoaded(true);

				}
			});
		}
	}
	// TODO: Support JPEG
}
