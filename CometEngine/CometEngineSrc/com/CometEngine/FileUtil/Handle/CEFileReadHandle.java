package com.CometEngine.FileUtil.Handle;

import java.nio.ByteBuffer;

public interface CEFileReadHandle {
	
	public void complite(ByteBuffer data);
	public void failure(Throwable e);
}
