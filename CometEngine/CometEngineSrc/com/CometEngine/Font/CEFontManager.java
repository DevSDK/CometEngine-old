package com.CometEngine.Font;

import java.util.Hashtable;

import org.newdawn.slick.TrueTypeFont;

import com.CometEngine.Font.BMPFont.FntFile;

public class CEFontManager {
	private static final CEFontManager instence = new CEFontManager();
	
	private static final Hashtable<String, CEFont> FontTable  = new Hashtable<String, CEFont>();
	
	public static CEFontManager getInstence()
	{
		return instence;
	}
	public void putFont(String key, CEFont font){
		if(FontTable.containsKey(key) == false)
		{
			FontTable.put(key, font);
		}
	}
	public CEFont getFont(String key)
	{
		if(FontTable.containsKey(key))
		{
			return FontTable.get(key);
		}
		return null;
	}

}
