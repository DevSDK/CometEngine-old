package com.CometEngine.DeskTop;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.annotation.processing.Completion;

import com.CometEngine.DeskTop.CEDeskTopAsyncFileIO.AttachMent;
import com.CometEngine.FileUtil.Handle.CEFileReadHandle;
import com.CometEngine.FileUtil.Handle.CEFileWriteHandle;
import com.sun.xml.internal.ws.api.message.Attachment;

public class CEDeskTopAsyncFileIO {
	public class AttachMent {
		Path path;
		AsynchronousFileChannel channel;
		ByteBuffer buffer;

		public AttachMent(Path path, ByteBuffer buffer, AsynchronousFileChannel channel) {
			this.path = path;
			this.channel = channel;
			this.buffer = buffer;
		}

	}


	public void read(String File, final CEFileReadHandle handle) {

		 CEAsyncLoader LoaderThread = new CEAsyncLoader();
		 LoaderThread.setFILE(File);
		 LoaderThread.setHandle(handle);
		 LoaderThread.start();

	}

	public void write(File file, byte[] buf, CEFileWriteHandle handle) {
		// TODO Auto-generated method stub

	}

	public void cleanUP() {
		// TODO Auto-generated method stub

	}

}
