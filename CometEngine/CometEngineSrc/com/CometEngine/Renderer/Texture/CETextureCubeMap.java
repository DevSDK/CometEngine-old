package com.CometEngine.Renderer.Texture;

import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.CEGLResourceManager;
import com.CometEngine.Resrouce.CEImageResrouce;
import com.CometEngine.Resrouce.CEImageResrouceLoader;
import com.CometEngine.Resrouce.CEResourceManager;

public class CETextureCubeMap extends CETexture {

	CEImageResrouce image_top;
	CEImageResrouce image_bottom;

	CEImageResrouce image_front;
	CEImageResrouce image_back;

	CEImageResrouce image_right;
	CEImageResrouce image_left;
	private String FileKey = "";

	private CETextureCubeMap(String top, String bottoom, String front, String back, String right, String left) {
		image_top = getorgenImageResource(top);
		image_bottom = getorgenImageResource(bottoom);
		image_front = getorgenImageResource(front);
		image_back = getorgenImageResource(back);
		image_right = getorgenImageResource(right);
		image_left = getorgenImageResource(left);
		FileKey = getPathKey(top, bottoom, front, back, right, left);
	}

	public static CETextureCubeMap Create(String[] FilePath) {
		if (FilePath.length <= 6) {
			return null;
		}
		return CETextureCubeMap.Create(FilePath[0], FilePath[1], FilePath[2], FilePath[3], FilePath[4], FilePath[5]);
	}

	private CEImageResrouce getorgenImageResource(String Path) {
		if (CEResourceManager.getInstence().isData(Path)) {
			return CEResourceManager.getInstence().getResoruce(Path);
		} else {
			CEImageResrouce image = new CEImageResrouce(Path);
			CEResourceManager.getInstence().putResoruceData(image);
			CEImageResrouceLoader.getInstence().LoadImage(Path, image);
			return image;
		}
	}

	public static CETextureCubeMap Create(String top, String bottom, String front, String back, String right,
			String left) {

		String FileKey = getPathKey(top, bottom, front, back, right, left);
		if (CETextureManager.getInstence().isHaveTexture(FileKey)) {
			return CETextureManager.getInstence().getTextureCubeMap(FileKey);
		}

		CETextureCubeMap cubemap = new CETextureCubeMap(top, bottom, front, back, right, left);

		CEGLResourceManager.getInstence().putGLResrouce(cubemap);
		CETextureManager.getInstence().addTexture(FileKey, cubemap);
		return cubemap;
	}

	private static String getPathKey(String... arg) {
		String keys = "KEY@";
		for (String key : arg) {
			keys += "[" + key + "]";
		}
		return keys;
	}

	@Override
	public void onGLLoad() {
		this.TextureID = CEGL.GenTextures();
		CEGL.BindTexture(CEGL.GL_TEXTURE_CUBE_MAP, TextureID);
		CEGL.TexImage2D(CEGL.GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, image_right.getRGBType(), image_right.getWidth(),
				image_right.getHeight(), 0, image_right.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image_right.getData());

		CEGL.TexImage2D(CEGL.GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, image_back.getRGBType(), image_back.getWidth(),
				image_back.getHeight(), 0, image_back.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image_back.getData());

		CEGL.TexImage2D(CEGL.GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0, image_left.getRGBType(), image_left.getWidth(),
				image_left.getHeight(), 0, image_left.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image_left.getData());

		CEGL.TexImage2D(CEGL.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, image_front.getRGBType(), image_front.getWidth(),
				image_front.getHeight(), 0, image_front.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image_front.getData());

		CEGL.TexImage2D(CEGL.GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, image_top.getRGBType(), image_top.getWidth(),
				image_top.getHeight(), 0, image_top.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image_top.getData());

		CEGL.TexImage2D(CEGL.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, image_bottom.getRGBType(), image_bottom.getWidth(),
				image_bottom.getHeight(), 0, image_bottom.getRGBType(), CEGL.GL_UNSIGNED_BYTE, image_bottom.getData());

		CEGL.TexParameteri(CEGL.GL_TEXTURE_CUBE_MAP, CEGL.GL_TEXTURE_MAG_FILTER, CEGL.GL_LINEAR);
		CEGL.TexParameteri(CEGL.GL_TEXTURE_CUBE_MAP, CEGL.GL_TEXTURE_MIN_FILTER, CEGL.GL_LINEAR);
	}

	@Override
	public boolean isloaded() {

		return image_top.isSetting() && image_back.isSetting() && image_bottom.isSetting() && image_front.isSetting()
				&& image_left.isSetting() && image_right.isSetting();
	}

	@Override
	public String getKey() {
		return FileKey;
	}

	@Override
	public void onGLDelete() {
		// TODO Auto-generated method stub

	}

}
