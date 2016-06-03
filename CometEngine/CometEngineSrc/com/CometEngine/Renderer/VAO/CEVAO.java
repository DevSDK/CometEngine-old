package com.CometEngine.Renderer.VAO;

public class CEVAO {
	private int Size = 0 ;
	private int[] VboArray = null;
	private boolean isLoadup = false;
	private long ID = 0 ;
	
	private CEVAO( int[]array,int size)
	{
		this.Size = size;
		VboArray = array;
	}
	
	public int getBOID(int index)
	{
		return VboArray[index];
	}
	
	
	public static long createVAO(int size)
	{
		int []array = new int[size];
		CEVAO vao = new CEVAO( array, size);
		long id = CEVAOManager.genID(vao);
		vao.ID= id;	
		return id;
	}
	
}
