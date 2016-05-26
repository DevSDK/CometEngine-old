package com.CometEngine.Resrouce;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.LinkedList;


import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.PNGDecoder.Format;
import org.newdawn.slick.opengl.PNGImageData;

import com.CometEngine.CometEngine;
import com.CometEngine.FileUtil.CEFileUtil;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CEImageResrouceLoader {
	//TODO: Add the 
	private static final LinkedList<CERawImageResrouce> loaded = new LinkedList<CERawImageResrouce>();
	private static CEImageResrouceLoader instence = null;
	
	public static CEImageResrouceLoader getInstence()
	{
		if(instence == null)
			instence = new CEImageResrouceLoader();
		return instence;
	}
	public void LoadImage(final String filepath, final CERawImageResrouce image)
	{
		if(filepath.endsWith("png"))
		{	
			if((new File(CEFileUtil.getInstence().getFullResourcePath(filepath)).isFile())==false)	
			try {
				throw new NoSuchFieldException();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				CometEngine.getInstece().EXIT(-1);
			}
			CEFileUtil.getInstence().ReadResoruceToAsync(filepath, new CEFileReadHandle() {
			
			@Override
			public void failure(Throwable e) {
				e.printStackTrace();
				image.setData(null);
				
			}
			
			@Override
			public void complite(ByteBuffer data) {
				
				try {
					PNGDecoder decoder = new PNGDecoder(new ByteArrayInputStream(data.array()));
					
					ByteBuffer buffer;
		
					buffer = ByteBuffer.allocateDirect(4 * decoder.getHeight() * decoder.getWidth());						
					
					image.setWidth(decoder.getWidth());
					image.setHeight(decoder.getHeight());
					decoder.decode(buffer, decoder.getWidth() * 4 , PNGDecoder.RGBA);
					decoder = null;
					buffer.flip();
					image.setData(buffer);
					
					image.setIsLoaded(true);
				} catch (IOException e) {
		
				}
				
 			}
		});
		 
		}
	}
}
