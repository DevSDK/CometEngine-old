package com.CometEngine.Util.Buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/*
  	native buffer 에 대하여 정의
  	Utils 로 게임 프로그래머도 사용할 수 있도록 정의
  	Byteorder 중요
 */
public class CEBufferUtils {

	
	public static FloatBuffer ArrayToBuffer(float [] array)
	{
		FloatBuffer buffer= CreateFloatBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static IntBuffer ArrayToBuffer(int [] array)
	{
		IntBuffer buffer =  CreateIntBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static ByteBuffer ArrayToBuffer(byte [] array)
	{
		ByteBuffer buffer =  CreateByteBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	public static ShortBuffer ArrayToBuffer(short [] array)
	{
		ShortBuffer buffer = CreateShortBuffer(array.length);
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	// << 2 =  * 4
	public static FloatBuffer CreateFloatBuffer(int Size)
	{
		return  CreateByteBuffer(Size << 2).asFloatBuffer();
	}
	public static IntBuffer CreateIntBuffer(int Size)
	{
	 	return CreateByteBuffer(Size << 2).asIntBuffer();
	}
	public static ShortBuffer CreateShortBuffer(int Size)
	{
		return CreateByteBuffer(Size << 1).asShortBuffer();
	}
	public static ByteBuffer CreateByteBuffer(int Size)
	{
		return ByteBuffer.allocateDirect(Size).order(ByteOrder.nativeOrder());
	}
}
