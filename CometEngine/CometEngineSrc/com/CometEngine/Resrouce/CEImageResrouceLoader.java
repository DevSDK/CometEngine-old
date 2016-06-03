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
					image.setData(data);
					image.setIsLoaded(true);
				
 			}
		});
		 
		}
	}
}
