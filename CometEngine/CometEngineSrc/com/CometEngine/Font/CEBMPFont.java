package com.CometEngine.Font;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Hashtable;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Font.BMPFont.FntFile;
import com.CometEngine.Font.BMPFont.character;
import com.CometEngine.Renderer.Texture.Textures.CETexture2D;

public class CEBMPFont extends CEFont{

	private FntFile file = null;
	private CETexture2D texture = null;
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
	}
		
	@Override
	public boolean isLoaded() {
		return texture.isGLLoaded();
	}
	public CETexture2D getTexture()
	{
		return texture;
	}

	@Override
	public FloatBuffer getVertex(char c) {
		
		return file.getVertex(c);
	}
	
	@Override
	public FloatBuffer getTexCoord(char c) {
		return file.TextureCoord(c);
	}

	@Override
	public int getLineHeight() {
		return file.getLineHeight();
	}
	
}
