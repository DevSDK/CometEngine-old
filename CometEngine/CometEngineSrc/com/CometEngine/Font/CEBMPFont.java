package com.CometEngine.Font;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Hashtable;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Font.BMPFont.FntFile;
import com.CometEngine.Font.BMPFont.character;
import com.CometEngine.Renderer.Texture.CETexture2D;

public class CEBMPFont extends CEFont {

	private FntFile file = null;
	private CETexture2D[] texture = null;
	private ArrayList<Float> VertexList = null;

	@Override
	public character getChar(char c) {
		return file.getCharacter((int) c);
	}

	public static CEBMPFont create(String FontFileName) {
		CEFont tes = null;
		if ((tes = CEFontManager.getInstence().getFont(FontFileName)) != null)
			return (CEBMPFont) tes;

		CEBMPFont font = new CEBMPFont(FontFileName);
		CEFontManager.getInstence().putFont(FontFileName, font);
		return font;
	}

	private String getFileLocation(String file) {
		String[] locs = file.split("/");
		if (locs.length <= 1)
			return "";
		String loc = "";
		for (int i = 0; i < locs.length - 1; i++) {
			loc += locs[i] + '/';

		}
		return loc;
	}

	private CEBMPFont(String FontFileName) {
		String location = getFileLocation(FontFileName);
		file = new FntFile(location, CEFileUtil.getInstence().ReadResurceDirectoryToSync(FontFileName));
		texture = new CETexture2D[file.getFileCounter()];
		for (Integer i : file.getFileSet().keySet()) {
			texture[i] = CETexture2D.CreateTexture2D(file.getFileSet().get(i));
		}
		VertexList = file.getVertexList();
	}

	@Override
	public boolean isLoaded() {
		for (int i = 0; i < texture.length; i++) {
			if (((CETexture2D) texture[i]).isloaded() == false)
				return false;
		}
		return true;
	}

	@Override
	public ArrayList<Float> getVertexList() {
		return VertexList;
	}

	public CETexture2D[] getTextures() {
		return texture;
	}

	@Override
	public int getLineHeight() {
		return file.getLineHeight();
	}

	@Override
	public ArrayList<Float> getTextureCoordList() {
		return file.getTexCoordList();
	}

	@Override
	public int getCharacterSize() {
		return file.getCharCounter();
	}

}
