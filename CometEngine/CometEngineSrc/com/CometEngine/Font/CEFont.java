package com.CometEngine.Font;

import java.nio.FloatBuffer;
import java.util.Hashtable;

import javax.xml.XMLConstants;

import org.newdawn.slick.util.xml.XMLParser;

import com.CometEngine.Font.BMPFont.character;


public abstract class CEFont {
	
	public abstract FloatBuffer getVertex(char c);
	
	public abstract character getChar(char c);
	public abstract boolean isLoaded();
	public abstract FloatBuffer getTexCoord(char c);
	public abstract int getLineHeight();
	
}
