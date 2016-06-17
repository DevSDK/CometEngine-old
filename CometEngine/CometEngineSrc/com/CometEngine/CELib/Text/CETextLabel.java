package com.CometEngine.CELib.Text;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.CometEngine.CELib.Camera.CE2DDefaultCamera;
import com.CometEngine.CELib.Node.CENode2D;
import com.CometEngine.Font.CEBMPFont;
import com.CometEngine.Font.CEFont;
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Renderer.Commend.CERenderCommand;
import com.CometEngine.Renderer.Commend.CERenderCommandCustom;
import com.CometEngine.Renderer.Commend.CERenderCustomCommandInvoker;
import com.CometEngine.Renderer.Shader.ShaderProgram;
import com.CometEngine.Tester.Default2DShader;
import com.CometEngine.Util.Meth.CEMatrix4f;

public abstract class CETextLabel extends CENode2D {
	protected CEFont Font = null;
	private boolean isCentered = false;
	ArrayList<String> TextString;
	ArrayList<char []> TextCharData = new ArrayList<char[]>();
	protected int LabelWidth = 0 ;
	protected int LabelHeight = 0 ;
	
	ShaderProgram program = new Default2DShader();
	CEMatrix4f TransLateMatrx = new CEMatrix4f();
	
	protected CETextLabel(String Text, float scale, boolean centered, CEFont font)
	{
		this.scale.x = scale;
		this.scale.y = scale;
		this.Font = font;
		this.isCentered = centered;
		setString(Text);
		
	}
	
	public static CEBMPTextLabel CreateBMPText(String strings, float scale, boolean isCentered, CEBMPFont font)
	{
		CEBMPTextLabel label = new CEBMPTextLabel(strings, scale, isCentered, font);
		return label;
	}
	
	private String[] getLine(String string)
	{	
		String [] str =  string.split("[\r\n]");
		
		if(str[0].endsWith("\\\\"))
		{
			str[0] += "\\";
		}
		return str;
	}
 	
	public void setString(String string)
	{
		String [] lines = getLine(string);
		
		for(int i = 0 ; i < lines.length ; i++)
		{
			TextCharData.add(lines[i].toCharArray());
		}
	}
	public ArrayList<String> getLines()
	{
		return TextString;
	}


	

}
