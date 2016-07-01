package com.CometEngine.CELib.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.CometEngine.CELib.Camera.CE2DDefaultCamera;
import com.CometEngine.CELib.Node.CEObject;
import com.CometEngine.CELib.Node.CERenderableObject;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Font.CEFont;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Tester.Default2DShader;
import com.CometEngine.Util.Meth.CEMatrix4f;

public abstract class CETextLabel extends CERenderableObject {
	protected CEFont Font = null;
	protected boolean isCentered = false;
	ArrayList<String> TextString;
	ArrayList<char []> TextCharData = new ArrayList<char[]>();
	protected int LabelWidth = 0 ;
	protected int LabelHeight = 0 ;
	
	
	
	ShaderProgram program = new Default2DShader();
	CEMatrix4f TransLateMatrx = new CEMatrix4f();
	
	protected CETextLabel(String []Text, float scale, boolean centered, CEFont font)
	{
		this.scale.x = scale;
		this.scale.y = scale;
		this.Font = font;
		this.isCentered = centered;
		setString(Text);
		
	}
	
	public static CEBMPTextLabel CreateBMPText(CEBMPFont font, float scale, boolean isCentered, String ...strings)
	{
		CEBMPTextLabel label = new CEBMPTextLabel(font, scale, isCentered, strings);
		return label;
	}
	

 	

	public void setString(String line){
		TextCharData.clear();
		TextCharData.add(line.toCharArray());	
	}
	public void setString(String []stringLine)
	{
		TextCharData.clear();
		for(int i = 0 ; i < stringLine.length ; i++)
		{
		  TextCharData.add(stringLine[i].toCharArray());
		}
	}
	public ArrayList<String> getLines()
	{
		return TextString;
	}


	

}
