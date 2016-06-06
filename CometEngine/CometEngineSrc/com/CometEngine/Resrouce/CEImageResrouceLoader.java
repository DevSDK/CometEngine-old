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
import com.CometEngine.Renderer.CEGL;
import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CEImageResrouceLoader {
	//TODO: Add the 
	private static final LinkedList<CEImageResrouce> loaded = new LinkedList<CEImageResrouce>();
	private static final CEImageResrouceLoader instence = new CEImageResrouceLoader();
	
	public static CEImageResrouceLoader getInstence()
	{
		return instence;
	}
	public void LoadImage(final String filepath, final CEImageResrouce image)
	{
		if(filepath.endsWith("png"))
		{	
			CEFileReadHandle handel = null;
			CEFileUtil.getInstence().ReadResoruceToAsync(filepath, handel = new CEFileReadHandle() {
			
			@Override
			public void failure(Throwable e) {
				e.printStackTrace();
				image.setData(null);
				
			}
			
			@Override
			public void complite(ByteBuffer data) {
		
					data.flip();
					byte[] array =  data.array().clone();
					ByteArrayInputStream stream;
					PNGDecoder decoder;
					ByteBuffer imbuf= null;
					try {
						decoder = new PNGDecoder(stream = new ByteArrayInputStream(array));
					
						imbuf = ByteBuffer.allocateDirect(4 * decoder.getHeight() * decoder.getWidth());						
						decoder.decode(imbuf, decoder.getWidth() * 4 , PNGDecoder.RGBA);
						imbuf.flip();
						image.setWidth(decoder.getWidth());
						image.setHeight(decoder.getHeight());
					
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					image.setData(imbuf);
					image.setIsLoaded(true);
				
 			}
		});
		 
		}
	}
}
