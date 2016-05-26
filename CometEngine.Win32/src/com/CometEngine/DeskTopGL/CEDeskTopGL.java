package com.CometEngine.DeskTopGL;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.ARBDrawInstanced;
import org.lwjgl.opengl.ARBInstancedArrays;
import org.lwjgl.opengl.ARBSync;
import org.lwjgl.opengl.ARBTextureMultisample;
import org.lwjgl.opengl.EXTFramebufferBlit;
import org.lwjgl.opengl.EXTFramebufferMultisample;
import org.lwjgl.opengl.EXTFramebufferObject;
import com.CometEngine.Renderer.CEGLInterface;

import sun.nio.cs.ext.GB18030;

public class CEDeskTopGL implements CEGLInterface{


	
	@Override
	public void Clear(int mask) {
		GL11.glClear(mask);	
		
	}

	@Override
	public void ClearColor(float red, float green, float blue, float alpha) {
		GL11.glClearColor(red, green, blue, alpha);
		
	}

	@Override
	public void Enable(int target) {
		GL11.glEnable(target);
	}

	@Override
	public void Disable(int target) {
		GL11.glEnable(target);
	}

	@Override
	public void Hint(int target, int hint) {	
		GL11.glHint(target, hint);
		
	}

	@Override
	public void BlendFunc(int sfactor, int dfactor) {
		GL11.glBlendFunc(sfactor, dfactor);
	}

	@Override
	public void ActiveTexture(int texture) {
		GL13.glActiveTexture(texture);
	}

	@Override
	public void BindTexture(int target, int texture) {
		GL11.glBindTexture(target, texture);
	}

	@Override
	public void AttachShader(int program, int shader) {
		GL20.glAttachShader(program, shader);
	}

	@Override
	public void BindBuffer(int target, int buffer) {
		GL15.glBindBuffer(target, buffer);
	}

	@Override
	public void BufferData(int target, FloatBuffer data, int usage) {
		CheackBuffer(data);
		GL15.glBufferData(target, data, usage);
	}

	@Override
	public void BufferData(int target, ShortBuffer data, int usage) {
		CheackBuffer(data);
		GL15.glBufferData(target, data, usage);
	}

	@Override
	public void BufferData(int target, ByteBuffer data, int usage) {
		CheackBuffer(data);
		GL15.glBufferData(target, data, usage);
	}

	@Override
	public void BufferData(int target, int data_size, int usage) {
		GL15.glBufferData(target, data_size, usage);
	}

	@Override
	public void BufferSubData(int target, int offset, FloatBuffer data) {
		CheackBuffer(data);
		GL15.glBufferSubData(target, offset, data);
	}

	@Override
	public void BufferSubData(int target, int offset, ShortBuffer data) {
		CheackBuffer(data);
		GL15.glBufferSubData(target, offset, data);
	}

	@Override
	public void BufferSubData(int target, int offset, ByteBuffer data) {
		CheackBuffer(data);
		GL15.glBufferSubData(target, offset, data);
	}

	@Override
	public void GetBufferSubData(int target, int offset, ByteBuffer data) {
		CheackBuffer(data);
		GL15.glGetBufferSubData(target, offset, data);
	}

	@Override
	public void ColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		GL11.glColorMask(red, green, blue, alpha);
	}

	@Override
	public void CompileShader(int shader) {
		GL20.glCompileShader(shader);
	}

	@Override
	public void CompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
			ByteBuffer data) {
		CheackBuffer(data);
		GL13.glCompressedTexImage1D(target, level, internalformat, width, border, data);
	}

	@Override
	public void CompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
			int format, ByteBuffer data) {
		CheackBuffer(data);
		GL13.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data);
	}

	@Override
	public int CreateProgram() {
		return GL20.glCreateProgram();
	}

	@Override
	public int CreateShader(int shaderType) {
		return GL20.glCreateShader(shaderType);
	}

	@Override
	public void CullFace(int mode) {
		GL11.glCullFace(mode);
	}

	@Override
	public void DeleteBuffers(IntBuffer buffers) {
		GL15.glDeleteBuffers(buffers);
	}

	@Override
	public void DeleteProgram(int program) {
		GL20.glDeleteProgram(program);
	}

	@Override
	public void DeleteShader(int shader) {
		GL20.glDeleteShader(shader);
	}

	@Override
	public void DeleteTextures(IntBuffer textures) {
			GL11.glDeleteTextures(textures);
	}

	@Override
	public void DepthFunc(int func) {
		GL11.glDepthFunc(func);
	}

	@Override
	public void DepthMask(boolean flag) {
		GL11.glDepthMask(flag);
	}

	@Override
	public void DepthRange(double nearVal, double farVal) {
		GL11.glDepthRange( nearVal, farVal );
	}

	@Override
	public void DetachShader(int program, int shader) {
		GL20.glDetachShader(program, shader);
	}

	@Override
	public void DisableVertexAttribArray(int index) {
		GL20.glDisableVertexAttribArray(index);
	}

	@Override
	public void DrawArrays(int mode, int first, int count) {
		GL11.glDrawArrays(mode, first, count);
	}

	@Override
	public void DrawRangeElements(int mode, int start, int end, int count, int type, long indices) {
		GL12.nglDrawRangeElements(mode, start, end, count, type, indices);
	}

	@Override
	public void EnableVertexAttribArray(int index) {
		GL20.glEnableVertexAttribArray(index);
	}

	@Override
	public int GenBuffers() {
		return GL15.glGenBuffers();
	}

	@Override
	public int GenTextures() {
		return GL11.glGenTextures();
	}

	@Override
	public int GetAttribLocation(int program, String name) {
		return GL20.glGetAttribLocation(program, name);
	}

	@Override
	public void GetBoolean(int pname, ByteBuffer params) {
		CheackBuffer(params);
		GL11.glGetBooleanv(pname, params);
	}

	@Override
	public int GetError() {
		return GL11.glGetError();
	}

	@Override
	public void GetInteger(int pname, IntBuffer params) {
		CheackBuffer(params);
		GL11.glGetIntegerv(pname, params);
		
	}

	@Override
	public void GetProgram(int program, int pname, IntBuffer params) {
		CheackBuffer(params);
		GL20.glGetProgramiv(program, pname, params);
	}

	@Override
	public String GetProgramInfoLog(int program, int maxLength) {
		return	GL20.glGetProgramInfoLog(program, maxLength);
	}

	@Override
	public void GetShader(int shader, int pname, IntBuffer params) {
		CheackBuffer(params);
		GL20.glGetShaderiv(shader, pname, params);
	}

	@Override
	public String GetShaderInfoLog(int shader, int maxLength) {
		return GL20.glGetShaderInfoLog(shader, maxLength);
	}

	@Override
	public String GetString(int name) {
		return GL11.glGetString(name);
	}

	@Override
	public int GetUniformLocation(int program, String name) {
		return GL20.glGetUniformLocation(program, name);
	}

	@Override
	public boolean IsEnabled(int cap) {
		return GL11.glIsEnabled(cap);
	}

	@Override
	public void LineWidth(float width) {
		GL11.glLineWidth(width);
	}

	@Override
	public void LinkProgram(int program) {
		GL20.glLinkProgram(program);
	}

	@Override
	public void PixelStorei(int pname, int param) {
		GL11.glPixelStorei(pname, param);
	}

	@Override
	public void PolygonOffset(float factor, float units) {
		GL11.glPolygonOffset(factor, units);
	}

	@Override
	public void ReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer data) {
		CheackBuffer(data);
		GL11.glReadPixels(x, y, width, height, format, type, data);
	}

	@Override
	public void Scissor(int x, int y, int width, int height) {
		GL11.glScissor(x, y, width, height);
	}

	@Override
	public void ShaderSource(int shader, String[] string, IntBuffer length) {
		CheackBuffer(length);
		GL20.glShaderSource(shader, string);
	}

	@Override
	public void StencilFuncSeparate(int face, int func, int ref, int mask) {
		GL20.glStencilFuncSeparate(face, func, ref, mask);
	}

	@Override
	public void StencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		GL20.glStencilOpSeparate(face, sfail, dpfail, dppass);
	}

	@Override
	public void TexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format,
			int type, ByteBuffer data) {
		CheackBuffer(data);
		GL11.glTexImage2D(target, level, internalFormat, width, height, border, format, type, data);
	}

	@Override
	public void TexParameterf(int target, int pname, float param) {
		GL11.glTexParameterf(target, pname, param);
	}

	@Override
	public void TexParameteri(int target, int pname, int param) {
		GL11.glTexParameteri(target, pname, param);
	}

	@Override
	public void TexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
			int type, ByteBuffer data) {
		CheackBuffer(data);
		GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, data);
	}

	@Override
	public void Uniform1fv(int location, FloatBuffer value) {
		CheackBuffer(value);
		GL20.glUniform1fv(location, value);
	}

	@Override
	public void Uniform1iv(int location, IntBuffer value) {
		CheackBuffer(value);
		GL20.glUniform1iv(location, value);
		
		
	}

	@Override
	public void Uniform1f(int location, float v0) {
		GL20.glUniform1f(location, v0);
	}

	@Override
	public void Uniform1i(int location, int v0) {
		GL20.glUniform1f(location, v0);
	}

	@Override
	public void Uniform2iv(int location, IntBuffer value) {
		CheackBuffer(value);
		GL20.glUniform2iv(location, value);
		
	}

	@Override
	public void Uniform2fv(int location, FloatBuffer value) {
		CheackBuffer(value);
		GL20.glUniform2fv(location, value);
		
	}

	@Override
	public void Uniform2f(int location, float v0, float v1) {
		GL20.glUniform2f(location, v0, v1);
	}

	@Override
	public void Uniform3iv(int location, IntBuffer value) {
		CheackBuffer(value);
		GL20.glUniform3iv(location, value);
		
	}

	@Override
	public void Uniform3fv(int location, FloatBuffer value) {
		CheackBuffer(value);
		GL20.glUniform3fv(location, value);
	}

	@Override
	public void Uniform3f(int location, float v0, float v1, float v2) {
		GL20.glUniform3f(location, v0, v1, v2);
	}

	@Override
	public void Uniform4fv(int location, FloatBuffer value) {
		CheackBuffer(value);
		GL20.glUniform4fv(location, value);
		
	}

	@Override
	public void Uniform4iv(int location, IntBuffer value) {
		CheackBuffer(value);
		GL20.glUniform4iv(location, value);
	}

	@Override
	public void Uniform4f(int location, float v0, float v1, float v2, float v3) {
		GL20.glUniform4f(location, v0, v1, v2, v3);
	}

	@Override
	public void UniformMatrix3fv(int location, boolean transpose, FloatBuffer value) {
		CheackBuffer(value);
		GL20.glUniformMatrix3fv(location, transpose, value);
	}

	@Override
	public void UniformMatrix4fv(int location, boolean transpose, FloatBuffer value) {
		CheackBuffer(value);
		GL20.glUniformMatrix4fv(location, transpose, value);
	}

	@Override
	public void UseProgram(int program) {
			GL20.glUseProgram(program);
		
	}

	@Override
	public void VertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
			GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
	}

	@Override
	public void Viewport(int x, int y, int width, int height) {
			GL11.glViewport(x, y, width, height);
	}

	@Override
	public void BlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1,
			int dstY1, int mask, int filter) {
			
	}

	@Override
	public void BufferData(int target, IntBuffer data, int usage) {
		CheackBuffer(data);
		GL15.glBufferData(target, data, usage);
	}

	@Override
	public void BufferSubData(int target, long offset, IntBuffer data) {
		CheackBuffer(data);
		GL15.glBufferSubData(target, offset, data);
	}

	@Override
	public void DrawArraysInstancedARB(int mode, int first, int count, int primcount) {
		ARBDrawInstanced.glDrawArraysInstancedARB(mode, first, count, primcount);
	}

	@Override
	public void DrawBuffers(IntBuffer bufs) {
		CheackBuffer(bufs);
		GL20.glDrawBuffers(bufs);
	}

	@Override
	public void DrawElementsInstancedARB(int mode, int indices_count, int type, long indices_buffer_offset,
			int primcount) {
		ARBDrawInstanced.glDrawElementsInstancedARB(mode, indices_count, type, indices_buffer_offset, primcount);
	}

	@Override
	public void GetMultisample(int pname, int index, FloatBuffer val) {
		CheackBuffer(val);
	    ARBTextureMultisample.glGetMultisamplefv(pname, index, val);
	}

	@Override
	public void RenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height) {
		EXTFramebufferMultisample.glRenderbufferStorageMultisampleEXT(target, samples, internalformat, width, height);
	}

	@Override
	public void TexImage2DMultisample(int target, int samples, int internalformat, int width, int height,
			boolean fixedsamplelocations) {
		   ARBTextureMultisample.glTexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
	}

	@Override
	public void VertexAttribDivisorARB(int index, int divisor) {
		 ARBInstancedArrays.glVertexAttribDivisorARB(index, divisor);
		
	}

	@Override
	public void BindFramebufferEXT(int param1, int param2) {
		EXTFramebufferObject.glBindFramebufferEXT(param1, param2);
	}

	@Override
	public void BindRenderbufferEXT(int param1, int param2) {
        EXTFramebufferObject.glBindRenderbufferEXT(param1, param2);
		
	}

	@Override
	public int CheckFramebufferStatusEXT(int param1) {
        return EXTFramebufferObject.glCheckFramebufferStatusEXT(param1);
	}

	@Override
	public void DeleteFramebuffersEXT(IntBuffer param1) {
		CheackBuffer(param1);
		   EXTFramebufferObject.glDeleteRenderbuffersEXT(param1);

	}

	@Override
	public void DeleteRenderbuffersEXT(IntBuffer param1) {
		CheackBuffer(param1);
		   EXTFramebufferObject.glDeleteRenderbuffersEXT(param1);
	}

	@Override
	public void FramebufferRenderbufferEXT(int param1, int param2, int param3, int param4) {

        EXTFramebufferObject.glFramebufferRenderbufferEXT(param1, param2, param3, param4);
	}

	@Override
	public void FramebufferTexture2DEXT(int param1, int param2, int param3, int param4, int param5) {

        EXTFramebufferObject.glFramebufferTexture2DEXT(param1, param2, param3, param4, param5);
	}

	@Override
	public void GenFramebuffersEXT(IntBuffer param1) {
		CheackBuffer(param1);
		  EXTFramebufferObject.glGenFramebuffersEXT(param1);
		
	}

	@Override
	public void GenRenderbuffersEXT(IntBuffer param1) {
		CheackBuffer(param1);
		 EXTFramebufferObject.glGenRenderbuffersEXT(param1);
	}

	@Override
	public void GenerateMipmapEXT(int param1) {
		 EXTFramebufferObject.glGenerateMipmapEXT(param1);		
	}

	@Override
	public void RenderbufferStorageEXT(int param1, int param2, int param3, int param4) {
		 EXTFramebufferObject.glRenderbufferStorageEXT(param1, param2, param3, param4);
		
	}

	@Override
	public void ReadPixels(int x, int y, int width, int height, int format, int type, long offset) {
		GL11.glReadPixels(x, y, width, height, format, type, offset);
	}

	@Override
	public int ClientWaitSync(Object sync, int flags, long timeout) {
		 return ARBSync.glClientWaitSync((Long) sync, flags, timeout);
	}

	@Override
	public void DeleteSync(Object sync) {
		 ARBSync.glDeleteSync((Long) sync);
	}

	@Override
	public Object FenceSync(int condition, int flags) {
		return ARBSync.glFenceSync(condition, flags);
	}

	@Override
	public void BlendEquationSeparate(int colorMode, int alphaMode) {
		GL20.glBlendEquationSeparate(colorMode, alphaMode);
		
	}
    private static void CheackBuffer(Buffer buffer) {
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
	public void ShaderSource(int shader, String string) {
		GL20.glShaderSource(shader, string);
	}

	



	@Override
	public void DrawElements(int mode, int count, int type, ByteBuffer indices) {
		GL11.glDrawElements(mode, count, type, indices);
	}

	@Override
	public void DrawElements(int mode, int count, int type, int offset) {
		GL11.glDrawElements(mode, count, type, offset);
	}

	@Override
	public void ValidateProgram(int program) {
		GL20.glValidateProgram(program);
	}

	@Override
	public void BindAttribLocation(int program, int index, String name) {
		GL20.glBindAttribLocation(program, index, name);
	}

	@Override
	public int GetShaderi(int shader, int pname) {
		return GL20.glGetShaderi(shader, pname);
	}



    
}
