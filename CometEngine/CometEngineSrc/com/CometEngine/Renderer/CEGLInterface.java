package com.CometEngine.Renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public interface CEGLInterface {

	public void Hint(int traget, int hint);
    public void ActiveTexture(int texture);
    public void AttachShader(int program, int shader);
    public void BindBuffer(int target, int buffer);
    public void BindTexture(int target, int texture) ;
    public void BlendFunc(int sfactor, int dfactor) ;
    public void BufferData(int target, FloatBuffer data, int usage);
    public void BufferData(int target, ShortBuffer data, int usage);
    public void BufferData(int target, ByteBuffer data, int usage) ;
    public void BufferData(int target, int data_size, int usage);
    public void BufferSubData(int target, int offset, FloatBuffer data);
    public void BufferSubData(int target, int offset, ShortBuffer data);
    public void BufferSubData(int target, int offset, ByteBuffer data);
    public void GetBufferSubData(int target, int offset, ByteBuffer data);
    public void Clear(int mask);
    public void ClearColor(float red, float green, float blue, float alpha);
    public void ColorMask(boolean red, boolean green, boolean blue, boolean alpha);
    public void CompileShader(int shader);
    public void CompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data);
    public void CompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data);
    public int CreateProgram();
    public int CreateShader(int shaderType);
    public void CullFace(int mode);
    public void DeleteBuffers(IntBuffer buffers);
    public void DeleteProgram(int program) ;
    public void DeleteShader(int shader);
    public void DeleteTextures(IntBuffer textures);
    public void DepthFunc(int func);
    public void DepthMask(boolean flag);
    public void DepthRange(double nearVal, double farVal);
    public void DetachShader(int program, int shader);
    public void Disable(int cap);
    public void DisableVertexAttribArray(int index);
    public void DrawArrays(int mode, int first, int count);
    public void DrawRangeElements(int mode, int start, int end, int count, int type, long indices);
    public void Enable(int cap);
    public void EnableVertexAttribArray(int index);
    public void GenBuffers(IntBuffer buffers) ;
    public void GenTextures(IntBuffer textures) ;
    public int GetAttribLocation(int program, String name);
    public void GetBoolean(int pname, ByteBuffer params);
    public int GetError() ;
    public void GetInteger(int pname, IntBuffer params) ;
    public void GetProgram(int program, int pname, IntBuffer params);
    public String GetProgramInfoLog(int program, int maxLength);
    public void GetShader(int shader, int pname, IntBuffer params);
    public String GetShaderInfoLog(int shader, int maxLength);
    public String GetString(int name);
    public int GetUniformLocation(int program, String name);
    public boolean IsEnabled(int cap);
    public void LineWidth(float width);
    public void LinkProgram(int program);
    public void PixelStorei(int pname, int param);
    public void PolygonOffset(float factor, float units) ;
    public void ReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer data);
    public void Scissor(int x, int y, int width, int height);
    public void ShaderSource(int shader, String[] string, IntBuffer length);
    public void StencilFuncSeparate(int face, int func, int ref, int mask);
    public void StencilOpSeparate(int face, int sfail, int dpfail, int dppass);
    public void TexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, ByteBuffer data);
    public void TexParameterf(int target, int pname, float param);
    public void TexParameteri(int target, int pname, int param);
    public void TexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer data);
    public void Uniform1fv(int location, FloatBuffer value);
    public void Uniform1iv(int location, IntBuffer value);
    public void Uniform1f(int location, float v0);
    public void Uniform1i(int location, int v0);
    public void Uniform2iv(int location, IntBuffer value);
    public void Uniform2fv(int location, FloatBuffer value);
    public void Uniform2f(int location, float v0, float v1);
    public void Uniform3iv(int location, IntBuffer value);
    public void Uniform3fv(int location, FloatBuffer value);
    public void Uniform3f(int location, float v0, float v1, float v2);
    public void Uniform4fv(int location, FloatBuffer value);
    public void Uniform4iv(int location, IntBuffer value);
    public void Uniform4f(int location, float v0, float v1, float v2, float v3);
    public void UniformMatrix3fv(int location, boolean transpose, FloatBuffer value);
    public void UniformMatrix4fv(int location, boolean transpose, FloatBuffer value);
    public void UseProgram(int program);
    public void VertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) ;
    public void Viewport(int x, int y, int width, int height);
    public void BlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter);
    public void BufferData(int target, IntBuffer data, int usage);
    public void BufferSubData(int target, long offset, IntBuffer data);
    public void DrawArraysInstancedARB(int mode, int first, int count, int primcount);
    public void DrawBuffers(IntBuffer bufs);
    public void DrawElementsInstancedARB(int mode, int indices_count, int type, long indices_buffer_offset, int primcount);
    public void GetMultisample(int pname, int index, FloatBuffer val);
    public void RenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height);
    public void TexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations);
    public void VertexAttribDivisorARB(int index, int divisor);
    public void BindFramebufferEXT(int param1, int param2);
    public void BindRenderbufferEXT(int param1, int param2);
    public int CheckFramebufferStatusEXT(int param1);
    public void DeleteFramebuffersEXT(IntBuffer param1);
    public void DeleteRenderbuffersEXT(IntBuffer param1);
    public void FramebufferRenderbufferEXT(int param1, int param2, int param3, int param4);
    public void FramebufferTexture2DEXT(int param1, int param2, int param3, int param4, int param5);
    public void GenFramebuffersEXT(IntBuffer param1);
    public void GenRenderbuffersEXT(IntBuffer param1);
    public void GenerateMipmapEXT(int param1);
    public void RenderbufferStorageEXT(int param1, int param2, int param3, int param4);
    public void ReadPixels(int x, int y, int width, int height, int format, int type, long offset);
    public int ClientWaitSync(Object sync, int flags, long timeout);
    public void DeleteSync(Object sync);
    public Object FenceSync(int condition, int flags);
    public void BlendEquationSeparate(int colorMode, int alphaMode);
}


