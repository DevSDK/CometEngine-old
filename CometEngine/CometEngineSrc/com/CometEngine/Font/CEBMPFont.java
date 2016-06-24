package com.CometEngine.Font;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Hashtable;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Font.BMPFont.FntFile;
import com.CometEngine.Font.BMPFont.character;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class CEBMPFont extends CEFont{

	private FntFile file = null;
	private CETexture2D texture = null;
	private ArrayList<Float> VertexList = null;
	@Override
	public character getChar(char c) {
		return file.getCharacter((int)c);
	}
	
	public static CEBMPFont create(String BitMapFileName, String FontFileName)
	{
		CEFont tes = null;
		if(  (tes = CEFontManager.getInstence().getFont(FontFileName)) != null)
			return (CEBMPFont)tes;
		
		CEBMPFont font = new CEBMPFont(BitMapFileName, FontFileName);
		CEFontManager.getInstence().putFont(FontFileName, font);
		return font;
	}
	private CEBMPFont(String BitMapFileName, String FontFileName)
	{
		texture = CETexture2D.CreateTexture2D(BitMapFileName);
		file = new FntFile(CEFileUtil.getInstence().ReadResurceDirectoryToSync(FontFileName));
		VertexList = file.getVertexList();
	}
		
	@Override
	public boolean isLoaded() {
		return texture.isGLLoaded();
	}
	@Override
	public ArrayList<Float> getVertexList()
	{
		return VertexList;
	}
	public CETexture2D getTexture()
	{
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
