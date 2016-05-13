package com.platform.cometengine.android.gl;

import android.app.Application;
import android.opengl.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


import com.CometEngine.Renderer.CEGLInterface;

public class CEAndroidGL implements CEGLInterface{
	

    private static int getBufferLimit(ByteBuffer buffer) {
   
    	CheckBuffer(buffer);
        return buffer.limit();
    }

    private static int getBufferLimit(ShortBuffer buffer) {
  
    	CheckBuffer(buffer);

        return buffer.limit() * 2;
    }

    private static int getBufferLimit(IntBuffer buffer) {

			CheckBuffer(buffer);
	
	
        return buffer.limit() * 4;
    }

    private static int getBufferLimit(FloatBuffer buffer) {
      
			CheckBuffer(buffer);
	
        return buffer.limit() * 4;
    }

    private static int getBufferLimitOfCounter(Buffer buffer, int elementSize) {
  
			CheckBuffer(buffer);

        return buffer.limit() / elementSize;
    }

    private static void CheckBuffer(Buffer buffer)  {
        if (buffer == null) {
            return;
        }
        if (buffer.limit() == 0) {
        	System.err.println("CEGL Warnning Buffer" + buffer + "Size = 0");
        }
        if (buffer.remaining() == 0) {
        	System.err.println("CEGL Warnning Buffer" + buffer + "Size = 0");
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
	
		GLES20.glBufferData(target, getBufferLimit(data), data, usage);
	}

	@Override
	public void BufferData(int target, ShortBuffer data, int usage) {
	   GLES20.glBufferData(target, getBufferLimit(data), data, usage);
	}

	@Override
	public void BufferData(int target, ByteBuffer data, int usage) {
		GLES20.glBufferData(target, getBufferLimit(data), data, usage);
	}

	@Override
	public void BufferData(int target, int data_size, int usage) {
		GLES20.glBufferData(target, (int)data_size, null, usage);
		
	}

	@Override
	public void BufferSubData(int target, int offset, FloatBuffer data) {
		GLES20.glBufferSubData(target, offset, getBufferLimit(data), data);
	}

	@Override
	public void BufferSubData(int target, int offset, ShortBuffer data) {
		GLES20.glBufferSubData(target, offset, getBufferLimit(data), data);
	}

	@Override
	public void BufferSubData(int target, int offset, ByteBuffer data) {
		GLES20.glBufferSubData(target, offset, getBufferLimit(data), data);
	}

	@Override
	public void GetBufferSubData(int target, int offset, ByteBuffer data) {
		System.err.println("OPEN GL ES has does not support glGetBufferSubData");
		
	}

	@Override
	public void CompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
			ByteBuffer data) {
		GLES20.glCompressedTexImage2D(target, level, internalformat, width, height, border, getBufferLimit(data), data);
	}

	@Override
	public void CompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
			int format, ByteBuffer data) {
		GLES20.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, getBufferLimit(data), data);
		
	}

	@Override
	public void DeleteBuffers(IntBuffer buffers) {
		GLES20.glDeleteBuffers(getBufferLimit(buffers), buffers);
	}

	@Override
	public void DeleteTextures(IntBuffer textures) {
		GLES20.glDeleteTextures(getBufferLimit(textures), textures);
	}

	@Override
	public void DepthRange(double nearVal, double farVal) {
		GLES20.glDepthRangef((float)nearVal,(float) farVal);
	}

	@Override
	public void DrawRangeElements(int mode, int start, int end, int count, int type, long indices) {
		GLES20.glDrawElements(mode, count, type, (int)indices);
	}

	@Override
	public void GenBuffers(IntBuffer buffers) {
		GLES20.glGenBuffers(getBufferLimit(buffers), buffers);
	}

	@Override
	public void GenTextures(IntBuffer textures) {
		GLES20.glGenTextures(getBufferLimit(textures), textures);
	}

	@Override
	public int GetAttribLocation(int program, String name) {
		return GLES20.glGetAttribLocation(program, name);
	}

	@Override
	public void GetBoolean(int pname, ByteBuffer params) {
		System.err.println("GLES BOOLEAN CAST TO INTBUFFER ");
		CheckBuffer(params);
		IntBuffer ib = ((ByteBuffer) params.rewind()).asIntBuffer();
		GLES20.glGetBooleanv(pname, ib);
	}

	@Override
	public int GetError() {
		return GLES20.glGetError();
	}

	@Override
	public void GetInteger(int pname, IntBuffer params) {
		CheckBuffer(params);
		GLES20.glGetIntegerv(pname, params);
	}

	@Override
	public void GetProgram(int program, int pname, IntBuffer params) {
		CheckBuffer(params);
		GLES20.glGetProgramiv(program, pname, params);
	}

	@Override
	public String GetProgramInfoLog(int program, int maxLength) {
		return GLES20.glGetProgramInfoLog(program);
		}

	@Override
	public void GetShader(int shader, int pname, IntBuffer params) {
		CheckBuffer(params);
		GLES20.glGetShaderiv(shader, pname, params);
	}

	@Override
	public String GetShaderInfoLog(int shader, int maxLength) {
		return GLES20.glGetShaderInfoLog(shader);
		}

	@Override
	public String GetString(int name) {
		return GLES20.glGetString(name);
	}

	@Override
	public int GetUniformLocation(int program, String name) {
		return GLES20.glGetUniformLocation(program, name);
	}

	@Override
	public boolean IsEnabled(int cap) {
		return GLES20.glIsBuffer(cap);
	}

	@Override
	public void LineWidth(float width) {
		GLES20.glLineWidth(width);
	}

	@Override
	public void LinkProgram(int program) {
		GLES20.glLinkProgram(program);
	}

	@Override
	public void PixelStorei(int pname, int param) {
		GLES20.glPixelStorei(pname, param);
	}

	@Override
	public void PolygonOffset(float factor, float units) {
		GLES20.glPolygonOffset(factor, units);
	}

	@Override
	public void ReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer data) {
		GLES20.glReadPixels(x, y, width, height, format, type, data);
	}

	@Override
	public void Scissor(int x, int y, int width, int height) {
		GLES20.glScissor(x, y, width, height);
	}

	@Override
	public void ShaderSource(int shader, String[] strings, IntBuffer length) {
        if (strings.length != 1) {
        	System.err.println("CEGL SHADERSOURCE STRING LENGTH != 1. is GLES Error");
              }
        GLES20.glShaderSource(shader, strings[0]);
	}

	@Override
	public void StencilFuncSeparate(int face, int func, int ref, int mask) {
		GLES20.glStencilFuncSeparate(face, func, ref, mask);
	}

	@Override
	public void StencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		GLES20.glStencilOpSeparate(face, sfail, dpfail, dppass);
	}

	@Override
	public void TexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format,
			int type, ByteBuffer data) {
		CheckBuffer(data);
		GLES20.glTexImage2D(target, level, internalFormat, width, height, border, format, type, data);
	}

	@Override
	public void TexParameterf(int target, int pname, float param) {
		GLES20.glTexParameterf(target, pname, param);
	}

	@Override
	public void TexParameteri(int target, int pname, int param) {
		GLES20.glTexParameteri(target, pname, param);
	}

	@Override
	public void TexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
			int type, ByteBuffer data) {
		CheckBuffer(data);
		GLES20.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, data);
	}

	@Override
	public void Uniform1fv(int location, FloatBuffer value) {
		GLES20.glUniform1fv(location, getBufferLimitOfCounter(value, 1),  value);
	}

	@Override
	public void Uniform1iv(int location, IntBuffer value) {
		GLES20.glUniform1iv(location, getBufferLimitOfCounter(value, 1), value);
	}

	@Override
	public void Uniform1f(int location, float x) {
		GLES20.glUniform1f(location, x);
	}

	@Override
	public void Uniform1i(int location, int x) {
		GLES20.glUniform1f(location, x);
	}

	@Override
	public void Uniform2iv(int location, IntBuffer value) {
		GLES20.glUniform2iv(location, getBufferLimitOfCounter(value, 2), value);
	}

	@Override
	public void Uniform2fv(int location, FloatBuffer value) {
		GLES20.glUniform2fv(location, getBufferLimitOfCounter(value, 2), value);
	}

	@Override
	public void Uniform2f(int location, float x, float y) {
		GLES20.glUniform2f(location, x, y);
	}

	@Override
	public void Uniform3iv(int location, IntBuffer value) {
		GLES20.glUniform2iv(location, getBufferLimitOfCounter(value, 2), value);
	}

	@Override
	public void Uniform3fv(int location, FloatBuffer value) {
		GLES20.glUniform3fv(location, getBufferLimitOfCounter(value, 3), value);
	}

	@Override
	public void Uniform3f(int location, float x, float y, float z) {
		GLES20.glUniform3f(location, x, y, z);
	}

	@Override
	public void Uniform4fv(int location, FloatBuffer value) {
		GLES20.glUniform4fv(location, getBufferLimitOfCounter(value, 4), value);
	}

	@Override
	public void Uniform4iv(int location, IntBuffer value) {
		GLES20.glUniform4iv(location, getBufferLimitOfCounter(value, 4), value);
	}

	@Override
	public void Uniform4f(int location, float x, float y, float z, float w) {
		GLES20.glUniform4f(location, x, y, z, w);
	}

	@Override
	public void UniformMatrix3fv(int location, boolean transpose, FloatBuffer value) {
		GLES20.glUniformMatrix3fv(location, getBufferLimitOfCounter(value, 3 * 3), transpose, value);
	}

	@Override
	public void UniformMatrix4fv(int location, boolean transpose, FloatBuffer value) {
		GLES20.glUniformMatrix4fv(location, getBufferLimitOfCounter(value, 4*4), transpose, value);
	}

	@Override
	public void UseProgram(int program) {
		GLES20.glUseProgram(program);
	}

	@Override
	public void VertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
		GLES20.glVertexAttribPointer(index, size, type, normalized, stride, (int)pointer);
	}

	@Override
	public void Viewport(int x, int y, int width, int height) {
		GLES20.glViewport(x, y, width, height);
	}

	@Override
	public void BlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1,
			int dstY1, int mask, int filter) {
		System.err.println(" CEGL WARNNING GLES20.BlitFramebufferEXT not available on Android");
		System.exit(-1);
	}

	@Override
	public void BufferData(int target, IntBuffer data, int usage) {
		GLES20.glBufferData(target, getBufferLimit(data), data, usage);
	}

	@Override
	public void BufferSubData(int target, long offset, IntBuffer data) {
		GLES20.glBufferSubData(target, (int)offset, getBufferLimit(data), data);
	}

	@Override
	public void DrawArraysInstancedARB(int mode, int first, int count, int primcount) {
		System.err.println(" CEGL WARNNING GLES20.DrawArraysInstancedAR not available on Android");
		System.exit(-1);
	}

	@Override
	public void DrawBuffers(IntBuffer bufs) {
		System.err.println(" CEGL WARNNING GLES20.glDrawBuffers not available on Android");
		System.exit(-1);
	}

	@Override
	public void DrawElementsInstancedARB(int mode, int indices_count, int type, long indices_buffer_offset,
			int primcount) {
		System.err.println(" CEGL WARNNING GLES20.DrawElementsInstancedARB not available on Android");
		System.exit(-1);

	}

	@Override
	public void GetMultisample(int pname, int index, FloatBuffer val) {
		System.err.println(" CEGL WARNNING GLES20.GetMultisample not available on Android");
		System.exit(-1);
	}

	@Override
	public void RenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height) {
		System.err.println(" CEGL WARNNING GLES20.RenderbufferStorageMultisampleEXT not available on Android");
		System.exit(-1);
	}

	@Override
	public void TexImage2DMultisample(int target, int samples, int internalformat, int width, int height,
			boolean fixedsamplelocations) {
		System.err.println(" CEGL WARNNING GLES20.TexImage2DMultisample not available on Android");
		System.exit(-1);
	}

	@Override
	public void VertexAttribDivisorARB(int index, int divisor) {
		System.err.println(" CEGL WARNNING GLES20.VertexAttribDivisorARB not available on Android");
		System.exit(-1);
	}

	@Override
	public void BindFramebufferEXT(int param1, int param2) {
		GLES20.glBindBuffer(param1, param2);

	}

	@Override
	public void BindRenderbufferEXT(int param1, int param2) {
		GLES20.glBindRenderbuffer(param1, param2);
	}

	@Override
	public int CheckFramebufferStatusEXT(int param1) {
		return GLES20.glCheckFramebufferStatus(param1);
	}

	@Override
	public void DeleteFramebuffersEXT(IntBuffer param1) {
		GLES20.glDeleteFramebuffers(getBufferLimit(param1), param1);
	}

	@Override
	public void DeleteRenderbuffersEXT(IntBuffer param1) {
		GLES20.glDeleteRenderbuffers(getBufferLimit(param1), param1);
	}

	@Override
	public void FramebufferRenderbufferEXT(int param1, int param2, int param3, int param4) {
		GLES20.glFramebufferRenderbuffer(param1, param2, param3, param4);
	}

	@Override
	public void FramebufferTexture2DEXT(int param1, int param2, int param3, int param4, int param5) {
		GLES20.glFramebufferTexture2D(param1,param2, param3, param4, param5);
	}

	@Override
	public void GenFramebuffersEXT(IntBuffer param1) {
		GLES20.glGenFramebuffers(getBufferLimit(param1), param1);
	}

	@Override
	public void GenRenderbuffersEXT(IntBuffer param1) {
		GLES20.glGenRenderbuffers(getBufferLimit(param1), param1);
	}

	@Override
	public void GenerateMipmapEXT(int param1) {
		GLES20.glGenerateMipmap(param1);
	}

	@Override
	public void RenderbufferStorageEXT(int target, int internalformat, int width, int height) {
		GLES20.glRenderbufferStorage(target, internalformat, width, height);
	}

	@Override
	public void ReadPixels(int x, int y, int width, int height, int format, int type, long offset) {
		GLES20.glReadPixels(x, y, width, height, format, type, null);
	}

	@Override
	public int ClientWaitSync(Object sync, int flags, long timeout) {
		System.err.println(" CEGL WARNNING GLES20.TexImage2DMultisample not available on Android");
		System.exit(-1);
		return -1;
	}

	@Override
	public void DeleteSync(Object sync) {
		System.err.println(" CEGL WARNNING GLES20.TexImage2DMultisample not available on Android");
		System.exit(-1);
	}

	@Override
	public Object FenceSync(int condition, int flags) {
		System.err.println(" CEGL WARNNING GLES20.TexImage2DMultisample not available on Android");
		System.exit(-1);
		return null;
	}

	@Override
	public void BlendEquationSeparate(int colorMode, int alphaMode) {
		GLES20.glBlendEquationSeparate(colorMode, alphaMode);
	}


	@Override
	public void ShaderSource(int shader, String string) {
		GLES20.glShaderSource(shader, string);
	}	


	@Override
	public void DrawElements(int mode, int count, int type, ByteBuffer indices) {
		GLES20.glDrawElements(mode, count, type, indices);
	}

	@Override
	public void glDrawElements(int mode, int count, int type, int offset) {
		GLES20.glDrawElements(mode, count, type, offset);
	}

	@Override
	public void ValidateProgram(int program) {
		GLES20.glValidateProgram(program);
	}

	@Override
	public void BindAttribLocation(int program, int index, String name) {
		GLES20.glBindAttribLocation(program, index, name);
	}

	@Override
	public int GetShaderi(int shader, int pname) {
		IntBuffer buf = IntBuffer.allocate(Integer.SIZE/8);
		GLES20.glGetShaderiv(shader, pname, buf);
		return buf.get();
	}
	
	
	
	
	



}
