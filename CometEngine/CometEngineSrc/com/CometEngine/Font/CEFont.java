package com.CometEngine.Font;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.XMLConstants;

import com.CometEngine.Font.BMPFont.character;



public abstract class CEFont {
	
	
	public abstract character getChar(char c);
	public abstract boolean isLoaded();
	public abstract int getLineHeight();
	public abstract ArrayList<Float> getVertexList();
	public abstract ArrayList<Float> getTextureCoordList();
	public abstract int getCharacterSize();
	
}
