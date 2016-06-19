package com.CometEngine.Font.BMPFont;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.Util.Buffer.CEBufferUtils;



public class FntFile {

	private static final int PAD_TOP = 0;
	private static final int PAD_LEFT = 1;
	private static final int PAD_BOTTOM = 2;
	private static final int PAD_RIGHT = 3;

	private static final String SPLITTER = " ";
	private static final String NUMBER_SEPARATOR = ",";


	private double spaceWidth;
	private int[] padding;
	private int paddingWidth;
	private int paddingHeight;
	private int LineHeight = 0 ;
	private Map<Integer, character> metaData = new HashMap<Integer, character>();
	
	protected Hashtable<Integer, FloatBuffer> VertexTable = new Hashtable<Integer, FloatBuffer>();
	protected Hashtable<Integer, FloatBuffer> TextureCoordTable = new Hashtable<Integer, FloatBuffer>();
	
	private BufferedReader reader;
	private Map<String, String> values = new HashMap<String, String>();

	public FntFile(ByteBuffer file) {
		
		openFile(file);
		loadTEXT(file);
		
	}
	
	
	
	private void loadTEXT(ByteBuffer file)
	{
		loadPaddingData();
	
		loadLineSizes();
		LineHeight = getValueOfVariable("lineHeight");
		int imageWidth = getValueOfVariable("scaleW");
		int imageHeidth = getValueOfVariable("scaleH");
		loadCharacterData(imageWidth, imageHeidth);

		close();

	}


	
	public double getSpaceWidth() {
		return spaceWidth;
	}

	public character getCharacter(int ascii) {
		return metaData.get(ascii);
	}

	
	private boolean processNextLine() {
		values.clear();
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e1) {
		}
		if (line == null) {
			return false;
		}
		for (String part : line.split(SPLITTER)) {
			String[] valuePairs = part.split("=");
			if (valuePairs.length == 2) {
				values.put(valuePairs[0], valuePairs[1]);
			}
		}
		if(!line.startsWith("char"))
			return false;
		
		return true;
	}

	private int getValueOfVariable(String variable) {
		return Integer.parseInt(values.get(variable));
	}

	private int[] getValuesOfVariable(String variable) {
		String[] numbers = values.get(variable).split(NUMBER_SEPARATOR);
		int[] actualValues = new int[numbers.length];
		for (int i = 0; i < actualValues.length; i++) {
			actualValues[i] = Integer.parseInt(numbers[i]);
		}
		return actualValues;
	}

	private void close() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public FloatBuffer getVertex(char c)
	{
		if(VertexTable.containsKey((int)c))
		{
			return VertexTable.get((int)c);
		}
		return null;
				
	}
	public FloatBuffer TextureCoord(char c)
	{
		if(TextureCoordTable.containsKey((int)c))
		{
			return TextureCoordTable.get((int)c);
		}
		return null;
	}
	
	private void openFile(ByteBuffer file) {
		try {
		
			reader = new BufferedReader(new StringReader( new String(file.array(), Charset.forName("UTF-8"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't read font meta file!");
		}
	}
	public int getLineHeight()
	{
		return LineHeight;
	}
	
	private void loadPaddingData() {
		processNextLine();
		
		this.padding = getValuesOfVariable("padding");
		this.paddingWidth = padding[PAD_LEFT] + padding[PAD_RIGHT];
		this.paddingHeight = padding[PAD_TOP] + padding[PAD_BOTTOM];
	}

	private void loadLineSizes() {
		processNextLine();
	
	}

	
	private void loadCharacterData(int imageWidth ,int imageHeight) {
		processNextLine();
		processNextLine();
	int count  =	getValueOfVariable("count");
	for(int i = 0; i<count; i++)
		{
			processNextLine();
			character c = loadCharacter(imageWidth, imageHeight);
			if (c != null) {
				metaData.put(c.getId(), c);
				}
		}
	}

	
	private character loadCharacter(int imageWidth, int imageHeight) {
		int id = getValueOfVariable("id");
		if (id ==  32 ) {
			this.spaceWidth = (getValueOfVariable("xadvance") - paddingWidth) ;
			return new character(32, 0, 0, 0, 0, 0, 0, 0, 0, spaceWidth);
		}
		float xTex = ((float) getValueOfVariable("x") ) / imageWidth;
		float yTex = ((float) getValueOfVariable("y")) / imageHeight;
		int width = getValueOfVariable("width") ;
		int height = getValueOfVariable("height" );

		float[] vertices = new float[]{
	    		0, -height, 
	    		0, 0,
	            width, -height,
	            width, 0
	    };
		VertexTable.put(id, CEBufferUtils.ArrayToBuffer(vertices));
	
		double quadWidth = width;
		double quadHeight = height;
		float xTexSize = ((float) width  ) / imageWidth;
		float yTexSize = ((float) height  )  / imageHeight ;
		float []texcood = new float[]{
				xTex , yTex + yTexSize ,
				xTex  ,yTex , 
				xTex  +xTexSize, yTex + yTexSize, 
				xTex + xTexSize , yTex 
		};
		
		
		
		TextureCoordTable.put(id, CEBufferUtils.ArrayToBuffer(texcood));
		
		double xOff = (getValueOfVariable("xoffset")) ;
		double yOff = (getValueOfVariable("yoffset") );
		double xAdvance = (getValueOfVariable("xadvance") ) ;
		return new character(id, xTex, yTex, xTexSize, yTexSize, xOff, yOff, quadWidth, quadHeight, xAdvance);
	}
}
