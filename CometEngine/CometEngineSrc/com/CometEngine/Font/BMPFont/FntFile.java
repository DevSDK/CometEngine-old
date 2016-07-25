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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
	private int LineHeight = 0;
	private int CharCount = 0;
	private Map<Integer, character> Fontdata = new HashMap<Integer, character>();
	private String FileLocation;
	protected Hashtable<Integer, Integer> OffsetTable = new Hashtable<Integer, Integer>();
	protected final ArrayList<Float> VertexList = new ArrayList<Float>();
	protected final ArrayList<Float> TexCoordList = new ArrayList<Float>();
	// protected Hashtable<Integer, FloatBuffer> VertexTable = new
	// Hashtable<Integer, FloatBuffer>();
	private int FileCount = 0;
	private final HashMap<Integer, String> Fileset = new HashMap<Integer, String>();
	private BufferedReader reader;
	private Map<String, String> values = new HashMap<String, String>();

	private FntFileXML xmldata;
  
	private int DrawOffsetCounter = 0;

	public enum FNTFILETYPE {
		CE_NULL, CE_TEXT, CE_XML
	};

	public FntFile(String filepath, ByteBuffer file) {
		this.FileLocation = filepath;

		switch (getFileType(file)) {
		case CE_XML:
			LoadXML(file);
			break;
		case CE_TEXT:
			openFile(file);
			loadTEXT(file);
			break;
		default:
			break;
		}
		// TEST

	}

	private FNTFILETYPE getFileType(ByteBuffer data) {

		String str = new String(data.array(), Charset.forName("UTF-8"));
		if (str.startsWith("<"))
			return FNTFILETYPE.CE_XML;
		else
			return FNTFILETYPE.CE_TEXT;
	}

	private void LodeUpPaddingDataXML() {
		String[] ac = xmldata.getAttribute("padding").split(NUMBER_SEPARATOR);
		this.padding = ConvertStringArrayToIntArray(ac);
		this.paddingWidth = padding[PAD_LEFT] + padding[PAD_RIGHT];
		this.paddingHeight = padding[PAD_TOP] + padding[PAD_BOTTOM];
	}

	public void LoadXML(ByteBuffer file) {
		xmldata = new FntFileXML(file);
		xmldata.setElementSet("info");
		LodeUpPaddingDataXML();
		xmldata.setElementSet("common");
		LineHeight = Integer.parseInt(xmldata.getAttribute("lineHeight"));
		int imageWidth = Integer.parseInt(xmldata.getAttribute("scaleW"));
		int imageHeidth = Integer.parseInt(xmldata.getAttribute("scaleH"));

		xmldata.setElementSet("page");
		do {
			int id = Integer.parseInt(xmldata.getAttribute("id"));
			String filename = xmldata.getAttribute("file");
			String fullpath = FileLocation + filename;
			Fileset.put(id, fullpath);
			FileCount++;

		} while (xmldata.ProcessesElementCounter());
		xmldata.setElementSet("char");
		loadCharacterXMLData(imageWidth, imageHeidth);
	}

	private void loadCharacterXMLData(int imageWidth, int imageHeight) {

		do {
			character c = loadCharacterXML(imageWidth, imageHeight);
			if (c != null) {
				Fontdata.put(c.getId(), c);
			}
		} while (xmldata.ProcessesElementCounter());
	}

	private character loadCharacterXML(int imageWidth, int imageHeight) {
		int id = Integer.parseInt(xmldata.getAttribute("id"));

		if (id == 32) {
			this.spaceWidth = (Integer.parseInt(xmldata.getAttribute("xadvance")) - paddingWidth);
			return new character(32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, spaceWidth);
		}
		float xTex = Float.parseFloat(xmldata.getAttribute("x")) / imageWidth;
		float yTex = Float.parseFloat(xmldata.getAttribute("y")) / imageHeight;
		int width = Integer.parseInt(xmldata.getAttribute("width"));
		int height = Integer.parseInt(xmldata.getAttribute("height"));
		VertexList.add(0.0f);
		VertexList.add((float) -height);
		VertexList.add(0.0f);
		VertexList.add(0.0f);
		VertexList.add((float) width);
		VertexList.add((float) -height);
		VertexList.add((float) width);
		VertexList.add(0.0f);

		double quadWidth = width;
		double quadHeight = height;
		float xTexSize = ((float) width) / imageWidth;
		float yTexSize = ((float) height) / imageHeight;

		TexCoordList.add(xTex);
		TexCoordList.add(yTex + yTexSize);
		TexCoordList.add(xTex);
		TexCoordList.add(yTex);
		TexCoordList.add(xTex + xTexSize);
		TexCoordList.add(yTex + yTexSize);
		TexCoordList.add(xTex + xTexSize);
		TexCoordList.add(yTex);

		double xOff = (Double.parseDouble(xmldata.getAttribute("xoffset")));
		double yOff = (Double.parseDouble(xmldata.getAttribute("yoffset")));
		double xAdvance = (Double.parseDouble(xmldata.getAttribute("xadvance")));
		int FilePage = (Integer.parseInt(xmldata.getAttribute("page")));
		return new character(id, FilePage, DrawOffsetCounter++, xTex, yTex, xTexSize, yTexSize, xOff, yOff, quadWidth,
				quadHeight, xAdvance);
	}

	public HashMap<Integer, String> getFileSet() {
		return Fileset;
	}

	public int getFileCounter() {
		return FileCount;
	}

	public String getFileString(int id) {
		if (Fileset.containsKey(id)) {
			return Fileset.get(id);
		}
		return null;
	}

	public int getCharCounter() {
		return Fontdata.size() + 1;
	}

	private boolean processNextPageArea() {
		String line = null;
		values.clear();
		try {
			line = reader.readLine();

			for (String part : line.split(SPLITTER)) {
				String[] valuePairs = part.split("=");
				if (valuePairs.length == 2) {
					values.put(valuePairs[0], valuePairs[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (line == null)
			return false;

		boolean r = line.startsWith("page");
		return r;
	}

	private void loadTEXT(ByteBuffer file) {
		loadPaddingData();

		loadLineSizes();
		LineHeight = getValueOfVariable("lineHeight");
		int imageWidth = getValueOfVariable("scaleW");
		int imageHeidth = getValueOfVariable("scaleH");
		processNextLine();
		do {
			int id = getValueOfVariable("id");
			String filename = getStringOfVariable("file");
			String fullpath = FileLocation + filename;
			Fileset.put(id, fullpath);
			FileCount++;

		} while (processNextPageArea());

		loadCharacterTEXTData(imageWidth, imageHeidth);

		close();

	}

	public double getSpaceWidth() {
		return spaceWidth;
	}

	public character getCharacter(int ascii) {
		return Fontdata.get(ascii);
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
		if (!line.startsWith("char"))
			return false;

		return true;
	}

	private String getStringOfVariable(String variable) {
		String ac = values.get(variable).replace("\"", "");
		return ac;
	}

	private int getValueOfVariable(String variable) {
		return Integer.parseInt(values.get(variable));
	}

	private int[] ConvertStringArrayToIntArray(String[] nums) {

		int[] actualValues = new int[nums.length];
		for (int i = 0; i < actualValues.length; i++) {
			actualValues[i] = Integer.parseInt(nums[i]);
		}
		return actualValues;
	}

	private int[] getValuesOfVariable(String variable) {
		String[] numbers = values.get(variable).split(NUMBER_SEPARATOR);

		return ConvertStringArrayToIntArray(numbers);
	}

	private void close() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openFile(ByteBuffer file) {
		try {

			reader = new BufferedReader(new StringReader(new String(file.array(), Charset.forName("UTF-8"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public int getLineHeight() {
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

	private void loadCharacterTEXTData(int imageWidth, int imageHeight) {
		CharCount = getValueOfVariable("count");
		for (int i = 0; i <= CharCount; i++) {
			if (false == processNextLine()) {
				break;
			}
			character c = loadCharacterTEXT(imageWidth, imageHeight);
			if (c != null) {
				Fontdata.put(c.getId(), c);
			}
		}
	}

	private character loadCharacterTEXT(int imageWidth, int imageHeight) {
		int id = getValueOfVariable("id");
		if (id == 32) {
			this.spaceWidth = (getValueOfVariable("xadvance") - paddingWidth);
			return new character(32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, spaceWidth);
		}
		float xTex = ((float) getValueOfVariable("x")) / imageWidth;
		float yTex = ((float) getValueOfVariable("y")) / imageHeight;
		int width = getValueOfVariable("width");
		int height = getValueOfVariable("height");

		VertexList.add(0.0f);
		VertexList.add((float) -height);
		VertexList.add(0.0f);
		VertexList.add(0.0f);
		VertexList.add((float) width);
		VertexList.add((float) -height);
		VertexList.add((float) width);
		VertexList.add(0.0f);

		double quadWidth = width;
		double quadHeight = height;
		float xTexSize = ((float) width) / imageWidth;
		float yTexSize = ((float) height) / imageHeight;

		TexCoordList.add(xTex);
		TexCoordList.add(yTex + yTexSize);
		TexCoordList.add(xTex);
		TexCoordList.add(yTex);
		TexCoordList.add(xTex + xTexSize);
		TexCoordList.add(yTex + yTexSize);
		TexCoordList.add(xTex + xTexSize);
		TexCoordList.add(yTex);

		double xOff = (getValueOfVariable("xoffset"));
		double yOff = (getValueOfVariable("yoffset"));
		double xAdvance = (getValueOfVariable("xadvance"));
		int FilePage = (getValueOfVariable("page"));
		return new character(id, FilePage, DrawOffsetCounter++, xTex, yTex, xTexSize, yTexSize, xOff, yOff, quadWidth,
				quadHeight, xAdvance);
	}

	public ArrayList<Float> getTexCoordList() {
		return TexCoordList;
	}

	public ArrayList<Float> getVertexList() {
		return VertexList;
	}
}
