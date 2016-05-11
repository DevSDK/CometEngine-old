package com.platform.cometengine.android.gl;

import android.opengl.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.opengles.GLES;

import com.CometEngine.Renderer.CEGLInterface;

public class CEAndroidGL implements CEGLInterface{
	

    private static int getBufferBytesSize(ByteBuffer buffer) {
        try {
			checkLimit(buffer);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
        return buffer.limit();
    }

    private static int getBufferBytesSize(ShortBuffer buffer) {
        try {
			checkLimit(buffer);
		} catch (Exception e) {

			System.err.println(e);
			e.printStackTrace();
		}
        return buffer.limit() * 2;
    }

    private static int getBufferBytesSize(IntBuffer buffer) {
        try {
			checkLimit(buffer);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
        return buffer.limit() * 4;
    }

    private static int getBufferBytesSize(FloatBuffer buffer) {
        try {
			checkLimit(buffer);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
        return buffer.limit() * 4;
    }

    private static int getLimitCount(Buffer buffer, int elementSize) {
        try {
			checkLimit(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return buffer.limit() / elementSize;
    }

    private static void checkLimit(Buffer buffer) throws Exception {
        if (buffer == null) {
            return;
        }
        if (buffer.limit() == 0) {
            throw new Exception("CEGLError : BufferSize 0");
        }
        if (buffer.remaining() == 0) {
            throw new Exception("CEGLError : BufferSize");
        }
    }
	@Override
	public void Clear(int mask) {
		GLES20.glClear(mask);
	}

	@Override
	public void ClearColor(float red, float green, float blue, float alpha) {
		GLES20.glClearColor(red, green, blue, alpha);
	}

	@Override
	public void Enable(int target) {
		GLES20.glEnable(target);
	}

	@Override
	public void Disable(int target) {
		GLES20.glDisable(target);
	}

	@Override
	public void Hint(int target, int hint) {
		GLES20.glHint(target, hint);
	}

	@Override
	public void BlendFunc(int sfactor, int dfactor) {
		GLES20.glBlendFunc(sfactor, dfactor);
	}

	@Override
	public void ActiveTexture(int texture) {
		GLES20.glActiveTexture(texture);
	}

	@Override
	public void BindTexture(int target, int texture) {
		GLES20.glBindTexture(target, texture);
	}

	@Override
	public void AttachShader(int program, int shader) {
		GLES20.glAttachShader(program, shader);
	}

	@Override
	public void BindBuffer(int target, int buffer) {
		GLES20.glBindBuffer(target, buffer);
	}
	
	@Override
	public void ColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GLES20.glColorMask(red, green, blue, alpha);
	}

	@Override
	public void CompileShader(int shader) {
		GLES20.glCompileShader(shader);
	}


	@Override
	public int CreateProgram() {
		return GLES20.glCreateProgram();
	}

	@Override
	public int CreateShader(int shaderType) {
		return GLES20.glCreateShader(shaderType);
	}

	@Override
	public void CullFace(int mode) {
		GLES20.glCullFace(mode);
	}



	@Override
	public void DeleteProgram(int program) {
		GLES20.glDeleteProgram(program);
	}

	@Override
	public void DeleteShader(int shader) {
		GLES20.glDeleteShader(shader);
	}



	@Override
	public void DepthFunc(int func) {
		GLES20.glDepthFunc(func);
	}

	@Override
	public void DepthMask(boolean flag) {
		GLES20.glDepthMask(flag);
	}



	@Override
	public void DetachShader(int program, int shader) {
		GLES20.glDetachShader(program, shader);
	}

	@Override
	public void DisableVertexAttribArray(int index) {
		GLES20.glDisableVertexAttribArray(index);
	}

	@Override
	public void DrawArrays(int mode, int first, int count) {
		GLES20.glDrawArrays(mode, first, count);
	}


	

	@Override
	public void EnableVertexAttribArray(int index) {
		GLES20.glEnableVertexAttribArray(index);
	}

	@Override
	public void BufferData(int target, FloatBuffer data, int usage) {
		GLES20.glBufferData(target, getBufferBytesSize(data), data, usage);
	}

	@Override
	public void BufferData(int target, ShortBuffer data, int usage) {
	   GLES20.glBufferData(target, getBufferBytesSize(data), data, usage);
	}

	@Override
	public void BufferData(int target, ByteBuffer data, int usage) {
		GLES20.glBufferData(target, getBufferBytesSize(data), data, usage);
	}

	@Override
	public void BufferData(int target, int data_size, int usage) {
		GLES20.glBufferData(target, (int)data_size, null, usage);
		
	}

	@Override
	public void BufferSubData(int target, int offset, FloatBuffer data) {
		GLES20.glBufferSubData(target, offset, getBufferBytesSize(data), data);
	}

	@Override
	public void BufferSubData(int target, int offset, ShortBuffer data) {
		GLES20.glBufferSubData(target, offset, getBufferBytesSize(data), data);
	}

	@Override
	public void BufferSubData(int target, int offset, ByteBuffer data) {
		GLES20.glBufferSubData(target, offset, getBufferBytesSize(data), data);
	}

	@Override
	public void GetBufferSubData(int target, int offset, ByteBuffer data) {
		
	}

	@Override
	public void CompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
			ByteBuffer data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
			int format, ByteBuffer data) {

	}

	@Override
	public void DeleteBuffers(IntBuffer buffers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteTextures(IntBuffer textures) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DepthRange(double nearVal, double farVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawRangeElements(int mode, int start, int end, int count, int type, long indices) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GenBuffers(IntBuffer buffers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GenTextures(IntBuffer textures) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int GetAttribLocation(int program, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void GetBoolean(int pname, ByteBuffer params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int GetError() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void GetInteger(int pname, IntBuffer params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetProgram(int program, int pname, IntBuffer params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetProgramInfoLog(int program, int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void GetShader(int shader, int pname, IntBuffer params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetShaderInfoLog(int shader, int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetString(int name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int GetUniformLocation(int program, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean IsEnabled(int cap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void LineWidth(float width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LinkProgram(int program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PixelStorei(int pname, int param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PolygonOffset(float factor, float units) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Scissor(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ShaderSource(int shader, String[] string, IntBuffer length) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StencilFuncSeparate(int face, int func, int ref, int mask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void StencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format,
			int type, ByteBuffer data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TexParameterf(int target, int pname, float param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TexParameteri(int target, int pname, int param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
			int type, ByteBuffer data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform1(int location, FloatBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform1(int location, IntBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform1f(int location, float v0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform1i(int location, int v0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform2(int location, IntBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform2(int location, FloatBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform2f(int location, float v0, float v1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform3(int location, IntBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform3(int location, FloatBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform3f(int location, float v0, float v1, float v2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform4(int location, FloatBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform4(int location, IntBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Uniform4f(int location, float v0, float v1, float v2, float v3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UniformMatrix3(int location, boolean transpose, FloatBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UniformMatrix4(int location, boolean transpose, FloatBuffer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UseProgram(int program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void VertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Viewport(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1,
			int dstY1, int mask, int filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BufferData(int target, IntBuffer data, int usage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BufferSubData(int target, long offset, IntBuffer data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawArraysInstancedARB(int mode, int first, int count, int primcount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawBuffers(IntBuffer bufs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DrawElementsInstancedARB(int mode, int indices_count, int type, long indices_buffer_offset,
			int primcount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetMultisample(int pname, int index, FloatBuffer val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TexImage2DMultisample(int target, int samples, int internalformat, int width, int height,
			boolean fixedsamplelocations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void VertexAttribDivisorARB(int index, int divisor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BindFramebufferEXT(int param1, int param2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BindRenderbufferEXT(int param1, int param2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int CheckFramebufferStatusEXT(int param1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void DeleteFramebuffersEXT(IntBuffer param1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteRenderbuffersEXT(IntBuffer param1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FramebufferRenderbufferEXT(int param1, int param2, int param3, int param4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FramebufferTexture2DEXT(int param1, int param2, int param3, int param4, int param5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GenFramebuffersEXT(IntBuffer param1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GenRenderbuffersEXT(IntBuffer param1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GenerateMipmapEXT(int param1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RenderbufferStorageEXT(int param1, int param2, int param3, int param4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ReadPixels(int x, int y, int width, int height, int format, int type, long offset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int ClientWaitSync(Object sync, int flags, long timeout) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void DeleteSync(Object sync) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object FenceSync(int condition, int flags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void BlendEquationSeparate(int colorMode, int alphaMode) {
		// TODO Auto-generated method stub
		
	}

	



}
