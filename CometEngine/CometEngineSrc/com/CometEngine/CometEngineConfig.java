package com.CometEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.CometEngine.FileUtil.CEFileUtil;

public class CometEngineConfig {
	private static final CometEngineConfig Instance = new CometEngineConfig();
	public String TitleName = "";
	private int FrameWidth = 0;
	private int FrameHeight = 0;
	private int CoordWidth = 0;
	private int CoordHeight = 0;
	
	
	public String getTitle()
	{
		return TitleName;
	}
	
	
	public String getString(String line)
	{
		String [] res = line.split("=");
		return res[1];
	}
	public int getInt(String line)
	{

		String [] res = line.split("=");

		String num = res[1].replace(" " , "");
		return Integer.parseInt(num);
	}
	public void Load(String Config)
	{
		try {
			ByteBuffer ConfigFile = CEFileUtil.getInstence().ReadResurceDirectoryToSync(Config);
			BufferedReader reader =new BufferedReader(new StringReader(new String(ConfigFile.array(), Charset.forName("UTF-8"))));
			String line;
			line = reader.readLine();
			while(line != null)
			{
				if(line.startsWith("Title"))
				{
					TitleName = getString(line);
				}
				if(line.startsWith("FrameWidth"))
				{
					FrameWidth = getInt(line);
				}
				if(line.startsWith("FrameHeight"))
				{
					FrameHeight = getInt(line);
				}if(line.startsWith("CoordWidth"))
				{
					CoordWidth = getInt(line);
				}if(line.startsWith("CoordHeight"))
				{
					CoordHeight = getInt(line);
				}
				
				line = reader.readLine();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Resource 디렉토리 내  LaunchConfig.cecfg 파일이 없습니다. CometEngineLaunchConfig.cecfg 은 텍스트파일로 생성하여 " );

			System.err.println(" Title = 윈도우 창 이름" );
			System.err.println("FrameWidth= 윈도우 창 너비 " );

			System.err.println("FrameHeight= 윈도우 창 높이 " );
			System.err.println("CoordWidth= 좌표게 너비" );
			System.err.println("CoordHeight= 좌표게 높이 " );
			System.err.println("" );
			System.err.println(".cecfg 로 저장한뒤 Resource 디렉토리에 넣어주세요." );
			
			System.exit(-1);
			
		}
	
			
		
	}


	public int getFrameWidth() {
		return FrameWidth;
	}

	public static CometEngineConfig getInstance() {
		return Instance;
	}


	public int getFrameHeight() {
		return FrameHeight;
	}


	public int getCoordWidth() {
		return CoordWidth;
	}


	public int getCoordHeight() {
		return CoordHeight;
	}
}
