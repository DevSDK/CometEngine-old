package com.CometEngine.Util.Images.BMP;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


final class LittleEndianDataInput {
	
	private DataInputStream in;
	
	
	
	public LittleEndianDataInput(InputStream in) {
		this.in = new DataInputStream(in);
	}
	
	
	
	public int readInt16() throws IOException {  // Returns unsigned int16
		int x = in.readShort();
		return (x & 0xFF) << 8 | (x & 0xFF00) >>> 8;
	}
	
	
	public int readInt32() throws IOException {
		return Integer.reverseBytes(in.readInt());
	}
	
	
	public void skipFully(int len) throws IOException {
		while (len > 0) {
			long temp = in.skip(len);
			if (temp == 0)
				throw new EOFException();
			len -= temp;
		}
	}
	
	
	public void readFully(byte[] b) throws IOException {
		int off = 0;
		while (off < b.length) {
			int temp = in.read(b, off, b.length - off);
			if (temp == -1)
				throw new EOFException();
			off += temp;
		}
	}
	
}
