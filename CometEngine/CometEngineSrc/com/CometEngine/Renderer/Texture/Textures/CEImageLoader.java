package com.CometEngine.Renderer.Texture.Textures;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;

public class CEImageLoader {
	//TODO: Add the 
	private static final LinkedList<CEImage> loaded = new LinkedList<CEImage>();
	private static CEImageLoader instence = null;
	public static CEImageLoader getInstence()
	{
		if(instence == null)
			instence = new CEImageLoader();
		return instence;
	}
	public void LoadImage(final String filepath, final CEImage image)
	{
		if(filepath.endsWith("png"))
		{
			
		
		if((new File(filepath).isFile())==false)
			try {
				throw new NoSuchFieldException();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		CEFileUtil.getInstence().ReadResoruceToAsync(filepath, new CEFileReadHandle() {
			
			@Override
			public void failure(Throwable e) {
				e.printStackTrace();
				image.setTextureData(null);
				
			}
			
			@Override
			public void complite(ByteBuffer data) {
				data.flip();
				image.onTheFlagForSetting(); 
				image.setTextureData(data);
			}
		});
		 
		}
	}
}
