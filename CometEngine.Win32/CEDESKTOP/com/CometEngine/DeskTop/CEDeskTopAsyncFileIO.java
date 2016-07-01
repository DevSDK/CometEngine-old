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

	public void read(File file, final CEFileReadHandle handle) {

		try {
			AsynchronousFileChannel filechannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.READ);

			ByteBuffer buffer = ByteBuffer.allocate((int) filechannel.size());
			AttachMent attacment = new AttachMent(file.toPath(), buffer, filechannel);
			CompletionHandler<Integer, CEDeskTopAsyncFileIO.AttachMent> handler = new CompletionHandler<Integer, CEDeskTopAsyncFileIO.AttachMent>() {

				@Override
				public void failed(Throwable arg0, AttachMent arg1) {
					handle.failure(arg0);
				}

				@Override
				public void completed(Integer arg0, AttachMent arg1) {

					handle.complite(arg1.buffer);

					try {
						arg1.channel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			};

			filechannel.read(buffer, 0, attacment, handler);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void write(File file, byte[] buf, CEFileWriteHandle handle) {
		// TODO Auto-generated method stub

	}

	public void cleanUP() {
		// TODO Auto-generated method stub

	}

}
