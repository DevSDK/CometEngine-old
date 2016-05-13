package com.CometEngine.Buffer.Utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


public class CEBufferUtils {
	public static FloatBuffer ArrayToBuffer(float [] array)
	{
		FloatBuffer buffer = FloatBuffer.allocate(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static IntBuffer ArrayToBuffer(int [] array)
	{
		IntBuffer buffer = IntBuffer.allocate(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static ByteBuffer ArrayToBuffer(byte [] array)
	{
		ByteBuffer buffer = ByteBuffer.allocate(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static ShortBuffer ArrayToBuffer(short [] array)
	{
		ShortBuffer buffer = ShortBuffer.allocate(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static FloatBuffer CreateFloatBuffer(int Size)
	{
		return FloatBuffer.allocate(Float.SIZE/Byte.SIZE * Size );
	}
	public static IntBuffer CreateIntBuffer(int Size)
	{
		return IntBuffer.allocate(Integer.SIZE/Byte.SIZE * Size );
	}
	public static ShortBuffer CreateShortBuffer(int Size)
	{
		return ShortBuffer.allocate(Short.SIZE/Byte.SIZE * Size );
	}
	public static ByteBuffer CreateByteBuffer(int Size)
	{
		return ByteBuffer.allocate(Byte.SIZE/Byte.SIZE * Size );
	}
}
