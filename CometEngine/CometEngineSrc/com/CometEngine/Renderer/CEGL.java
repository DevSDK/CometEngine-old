package com.CometEngine.Renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


import com.CometEngine.Util.Buffer.CEBufferUtils;



public class CEGL {
		
		public static boolean init(CEGLInterface glinterface)
		{
			if(GL == null)
			{
				GL = glinterface;
				return true;
			}
				return false;
		}
		
		public CEGLInterface getInstence()
		{
			return GL;
		}
	 	public static void ActiveTexture(int texture)
	 	{
	 		GL.ActiveTexture(texture);
	 	}
	    public static void AttachShader(int program, int shader) {
	    	GL.AttachShader(program, shader);
	    }
	    public static void BindBuffer(int target, int buffer) {
	    	GL.BindBuffer(target, buffer);
		}
	    public static void BindTexture(int target, int texture){
	    	GL.BindTexture(target, texture);
	    }
	    public static void BlendFunc(int sfactor, int dfactor) {
	    	GL.BlendFunc(sfactor, dfactor);
	    }
	    public static void BufferData(int target, FloatBuffer data, int usage) {
	    	GL.BufferData(target, data, usage);
	    }
	    public static void BufferData(int target, ShortBuffer data, int usage) {
	    	GL.BufferData(target, data, usage);
	    }
	    public static void BufferData(int target, ByteBuffer data, int usage) {
	    	GL.BufferData(target, data, usage);
	    }
	    public static void BufferData(int target, int data_size, int usage) {
	    	GL.BufferData(target, data_size, usage);
	    }
	    public static void BufferSubData(int target, int offset, FloatBuffer data) {
	    	GL.BufferData(target, data, offset);
	    }
	    public static void BufferSubData(int target, int offset, ShortBuffer data) {
	    	GL.BufferSubData(target, offset, data);
	    }
	    public static void BufferSubData(int target, int offset, ByteBuffer data) {
	    	GL.BufferSubData(target, offset, data);
	    }
	    public static void GetBufferSubData(int target, int offset, ByteBuffer data) {
	    	GL.GetBufferSubData(target, offset, data);
	    }
	    public static void Clear(int mask) {
	    	GL.Clear(mask);
	    }
	    public static void ClearColor(float red, float green, float blue, float alpha) {
	    	GL.ClearColor(red, green, blue, alpha);
	    }
	    public static void ColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
	    	GL.ColorMask(red, green, blue, alpha);
	    }
	    public static void CompileShader(int shader) {
	    	GL.CompileShader(shader);	
	    }
	    public static void CompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, ByteBuffer data) {
	    	GL.CompressedTexImage2D(target, level, internalformat, width, height, border, data);
	    }
	    public static void CompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer data) {
	    	GL.CompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, data);
	    }
	    public  static int CreateProgram() {
			return GL.CreateProgram();
		}
	    public  static int CreateShader(int shaderType) {
	    	return GL.CreateShader(shaderType);
	    }
	    public static void CullFace(int mode) {
	    	GL.CullFace(mode);
		}
	    public static void DeleteBuffers(IntBuffer buffers) {
	    	GL.DeleteBuffers(buffers);
	    }
	    public static void DeleteProgram(int program) {
	    	GL.DeleteProgram(program);
	    }
	    public static void DeleteShader(int shader) {
	    	GL.DeleteShader(shader);
	    }
	    private static  IntBuffer deletebuffer = IntBuffer.allocate(1);
	    public static void DeleteTextures(int texture) {
	    	
	    	deletebuffer.put(texture);
	    	
	    	deletebuffer.flip();
	    	GL.DeleteTextures(deletebuffer);
	    	deletebuffer.clear();
	    	
	    }
	    public static void DepthFunc(int func) {
	    	GL.DepthFunc(func);
		}
	    public static void DepthMask(boolean flag) {
	    	GL.DepthMask(flag);
	    }
	    public static void DepthRange(double nearVal, double farVal) {
	    	GL.DepthRange(nearVal, farVal);
	    }
	    public static void DetachShader(int program, int shader) {
	    	GL.DetachShader(program, shader);
	    }
	    public static void Disable(int cap) {
	    	GL.Disable(cap);
	    }
	    public static void DisableVertexAttribArray(int index) {
	    	GL.DisableVertexAttribArray(index);
	    }
	    public static void DrawArrays(int mode, int first, int count) {
	    	GL.DrawArrays(mode, first, count);
	    }
	    public static void DrawRangeElements(int mode, int start, int end, int count, int type, long indices) {
	    	GL.DrawRangeElements(mode, start, end, count, type, indices);
	    }
	    public static void Enable(int cap) {
	    	GL.Enable(cap);
	    }
	    public static void EnableVertexAttribArray(int index) {
	    	GL.EnableVertexAttribArray(index);
	    }
	    public static int GenBuffers() {
	    	return GL.GenBuffers();
	    }
	    public static int GenTextures() {
	    	return GL.GenTextures();
		    }
	    public  static int GetAttribLocation(int program, String name) {
			return GL.GetAttribLocation(program, name);
		}
	    public static void GetBoolean(int pname, ByteBuffer params) {
	    	GL.GetBoolean(pname, params);
	    }
	    public  static int GetError() {
	    	return GL.GetError();
		}
	    public static void GetInteger(int pname, IntBuffer params) {
	    	GL.GetInteger(pname, params);
	    }
	    public static void GetProgram(int program, int pname, IntBuffer params) {
	    	GL.GetProgram(program, pname, params);
	    }
	    public static String GetProgramInfoLog(int program, int maxLength) {
			return GL.GetProgramInfoLog(program, maxLength);
		}
	    public static void GetShader(int shader, int pname, IntBuffer params) {
	    	GL.GetShader(shader, pname, params);
		}
	    public static String GetShaderInfoLog(int shader, int maxLength) {
			return GL.GetShaderInfoLog(shader, maxLength);
		}
	    public static String GetString(int name) {
			return GL.GetString(name);
		}
	    public static int GetUniformLocation(int program, String name) {
			return GL.GetUniformLocation(program, name);
		}
		public static void Hint(int traget, int hint) {
			GL.Hint(traget, hint);
		}
	    public static boolean IsEnabled(int cap) {
			return GL.IsEnabled(cap);
		}
	    public static void LineWidth(float width) {
	    	GL.LineWidth(width);
		}
	    public static void LinkProgram(int program) {
	    	GL.LinkProgram(program);
	    }
	    public static void PixelStorei(int pname, int param) {
	    	GL.PixelStorei(pname, param);
	    }
	    public static void PolygonOffset(float factor, float units) {
	    	GL.PolygonOffset(factor, units);
	    }
	    public static void ReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer data) {
	    	GL.ReadPixels(x, y, width, height, format, type, data);
	    }
	    public static void Scissor(int x, int y, int width, int height) {
	    	GL.Scissor(x, y, width, height);
	    }
	    public static void ShaderSource(int shader, String string)
	    {
	    	GL.ShaderSource(shader, string);
	    }
	    public static void ShaderSource(int shader, String[] strings, IntBuffer length) {
	    	GL.ShaderSource(shader, strings, length);
	   	}
	    public static void StencilFuncSeparate(int face, int func, int ref, int mask) {
	    	GL.StencilFuncSeparate(face, func, ref, mask);
	    }
	    public static void StencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
	    	GL.StencilOpSeparate(face, sfail, dpfail, dppass);
	    }
	    public static void TexImage2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, ByteBuffer data) {
	    	GL.TexImage2D(target, level, internalFormat, width, height, border, format, type, data);
	    }
	    public static void TexParameterf(int target, int pname, float param) {
	    	GL.TexParameterf(target, pname, param);
	    }
	    public static void TexParameteri(int target, int pname, int param) {
	    	GL.TexParameteri(target, pname, param);
	    }
	    public static void TexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer data) {
	    	GL.TexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, data);
	    }
	    public static void Uniform1fv(int location, FloatBuffer value) {
	    	GL.Uniform1fv(location, value);
	    }
	    public static void Uniform1iv(int location, IntBuffer value) {
	    	GL.Uniform1iv(location, value);
	    }
	    public static void Uniform1f(int location, float x) {
	    	GL.Uniform1f(location, x);
	    }
	    public static void Uniform1i(int location, int x) {
	    	GL.Uniform1i(location, x);
	    }
	    public static void Uniform2iv(int location, IntBuffer value) {
	    	GL.Uniform2iv(location, value);
	    }
	    public static void Uniform2fv(int location, FloatBuffer value) {
	    	GL.Uniform2fv(location, value);
	    }
	    public static void Uniform2f(int location, float x, float y) {
	    	GL.Uniform2f(location, x, y);
	    }
	    public static void Uniform3iv(int location, IntBuffer value) {
	    	GL.Uniform2iv(location, value);
	    }
	    public static void Uniform3fv(int location, FloatBuffer value) {
	    	GL.Uniform3fv(location, value);
	    }
	    public static void Uniform3f(int location, float x, float y, float z) {
	    	GL.Uniform3f(location, x, y, z);
	    }
	    public static void Uniform4fv(int location, FloatBuffer value) {
	    	GL.Uniform4fv(location, value);
	    }
	    public static void Uniform4iv(int location, IntBuffer value) {
	    	GL.Uniform4iv(location, value);
	    }
	    public static void Uniform4f(int location, float x, float y, float z, float w) {
	    	GL.Uniform4f(location, x, y, z, w);
	    }
	    public static void UniformMatrix3fv(int location, boolean transpose, FloatBuffer value) {
	    	GL.UniformMatrix3fv(location, transpose, value);
	    }
	    public static void UniformMatrix4fv(int location, boolean transpose, FloatBuffer value) {
	    	GL.UniformMatrix4fv(location, transpose, value);
	    }
	    public static void UseProgram(int program) {
	    	GL.UseProgram(program);
	    }
	    public static void VertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
	    	GL.VertexAttribPointer(index, size, type, normalized, stride, pointer);
	    }
	    public static void DrawElements (int mode, int count, int type, int offset)
	    {
	    	GL.DrawElements(mode, count, type, offset);
	    }
	    public static void Viewport(int x, int y, int width, int height) {
	    	GL.Viewport(x, y, width, height);
	    }
	    public static void BlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
	    	GL.BlitFramebufferEXT(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
	    }
	    public static void BufferData(int target, IntBuffer data, int usage) {
	    	GL.BufferData(target, data, usage);
	    }
	    public static void BufferSubData(int target, long offset, IntBuffer data) {
	    	GL.BufferSubData(target, offset, data);
	    }
	    public static void DrawArraysInstancedARB(int mode, int first, int count, int primcount) {
	    	GL.DrawArrays(mode, first, primcount);
	    }
	    public static void DrawBuffers(IntBuffer bufs) {
	    	GL.DrawBuffers(bufs);
	    }
	    public static void DrawElementsInstancedARB(int mode, int indices_count, int type, long indices_buffer_offset, int primcount) {
	    	GL.DrawElementsInstancedARB(mode, indices_count, type, indices_buffer_offset, primcount);
	    }
	    public static void GetMultisample(int pname, int index, FloatBuffer val) {
	    	GL.GetMultisample(pname, index, val);
	    }
	    public static void RenderbufferStorageMultisampleEXT(int target, int samples, int internalformat, int width, int height) {
	    	GL.RenderbufferStorageMultisampleEXT(target, samples, internalformat, width, height);
	    }
	    public static void TexImage2DMultisample(int target, int samples, int internalformat, int width, int height, boolean fixedsamplelocations) {
	    	GL.TexImage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
	    }
	    public static void VertexAttribDivisorARB(int index, int divisor) {
	    	GL.VertexAttribDivisorARB(index, divisor);
	    }
	    public static void BindFramebufferEXT(int target, int buffer) {
	    	GL.BindFramebufferEXT(target, buffer);
	    }
	    public static void BindRenderbufferEXT(int target, int buffer) {
	    	GL.BindRenderbufferEXT(target, buffer);
	    }
	    public static int CheckFramebufferStatusEXT(int target) {
			return GL.CheckFramebufferStatusEXT(target);
		}
	    public static void DeleteFramebuffersEXT(IntBuffer framebuffers) {
	    	GL.DeleteFramebuffersEXT(framebuffers);
	    }
	    public static void DeleteRenderbuffersEXT(IntBuffer framebuffers) {
	    	GL.DeleteRenderbuffersEXT(framebuffers);
	    }
	    public static void FramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer) {
	    	GL.FramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer);
	    }
	    public static void FramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level) {
	    	GL.FramebufferTexture2DEXT(target, attachment, textarget, texture, level);
	    }
	    public static void GenFramebuffersEXT(IntBuffer framebuffers) {
	    	GL.GenFramebuffersEXT(framebuffers);
	    }
	    public static void GenRenderbuffersEXT(IntBuffer framebuffers) {
	    	GL.GenRenderbuffersEXT(framebuffers);
	    }
	    public static void GenerateMipmapEXT(int target) {
	    	GL.GenerateMipmapEXT(target);
	    }
	    public static void RenderbufferStorageEXT(int target, int internalformat, int width, int height) {
	    	GL.RenderbufferStorageEXT(target, internalformat, width, height);
	    }
	    public static void ReadPixels(int x, int y, int width, int height, int format, int type, long offset) {
	    	GL.ReadPixels(x, y, width, height, format, type, offset);
	    }
	    public static int ClientWaitSync(Object sync, int flags, long timeout) {
			return GL.ClientWaitSync(sync, flags, timeout);
		}
	    public static void DeleteSync(Object sync) {
	    	GL.DeleteSync(sync);
	    }
	    public static Object FenceSync(int condition, int flags) {
			return GL.FenceSync(condition, flags);
	    }
	    public static void DrawElements (int mode, int count, int type, ByteBuffer indices) 
	    {
	    	GL.DrawElements(mode, count, type, indices);
	    }
	    public static void ValidateProgram (int program)
	    {
	    	GL.ValidateProgram(program);
	    }
	    public static void BindAttribLocation(int program, int index, String name)
	    {
	    	GL.BindAttribLocation(program, index, name);
	    }
	    public static int GetShaderi(int shader, int pname)
	    {
	    	return GL.GetShaderi(shader, pname);
	    }
	    public static void BindVertexArray(int array)
	    {
	    	GL.BindVertexArray(array);
	    }
	    
		public static int GenVertexArrays() {
			return GL.GenVertexArrays();
		}

		public static void DeleteVertexArrays(int target) {
			GL.DeleteVertexArrays(target);
		}
	    
	  private static CEGLInterface GL = null;
	
	  public static final int GL_ACTIVE_TEXTURE = 34016;
	  
	  public static final int GL_DEPTH_BUFFER_BIT = 256;
	  
	  public static final int GL_STENCIL_BUFFER_BIT = 1024;
	  
	  public static final int GL_COLOR_BUFFER_BIT = 16384;
	  
	  public static final int GL_FALSE = 0;
	  
	  public static final int GL_TRUE = 1;
	  
	  public static final int GL_POINTS = 0;
	  
	  public static final int GL_LINES = 1;
	 
	  public static final int GL_LINE_LOOP = 2;
	  
	  public static final int GL_LINE_STRIP = 3;
	  
	  public static final int GL_TRIANGLES = 4;
	  
	  public static final int GL_TRIANGLE_STRIP = 5;
	  
	  public static final int GL_TRIANGLE_FAN = 6;
	  
	  public static final int GL_ZERO = 0;
	  
	  public static final int GL_ONE = 1;
	  
	  public static final int GL_SRC_COLOR = 768;
	  
	  public static final int GL_ONE_MINUS_SRC_COLOR = 769;
	  
	  public static final int GL_SRC_ALPHA = 770;
	  
	  public static final int GL_ONE_MINUS_SRC_ALPHA = 771;
	  
	  public static final int GL_DST_ALPHA = 772;
	  
	  public static final int GL_ONE_MINUS_DST_ALPHA = 773;
	  
	  public static final int GL_DST_COLOR = 774;
	  
	  public static final int GL_ONE_MINUS_DST_COLOR = 775;
	  
	  public static final int GL_SRC_ALPHA_SATURATE = 776;
	  
	  public static final int GL_FUNC_ADD = 32774;
	  
	  public static final int GL_BLEND_EQUATION = 32777;
	  
	  public static final int GL_BLEND_EQUATION_RGB = 32777;
	  
	  public static final int GL_BLEND_EQUATION_ALPHA = 34877;
	  
	  public static final int GL_FUNC_SUBTRACT = 32778;
	  
	  public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
	  
	  public static final int GL_BLEND_DST_RGB = 32968;
	  
	  public static final int GL_BLEND_SRC_RGB = 32969;
	  
	  public static final int GL_BLEND_DST_ALPHA = 32970;
	  
	  public static final int GL_BLEND_SRC_ALPHA = 32971;
	  
	  public static final int GL_CONSTANT_COLOR = 32769;
	  
	  public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
	  
	  public static final int GL_CONSTANT_ALPHA = 32771;
	  
	  public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
	  
	  public static final int GL_BLEND_COLOR = 32773;
	  
	  public static final int GL_ARRAY_BUFFER = 34962;
	  
	  public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
	  
	  public static final int GL_ARRAY_BUFFER_BINDING = 34964;
	  
	  public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
	  
	  public static final int GL_STREAM_DRAW = 35040;
	  
	  public static final int GL_STATIC_DRAW = 35044;
	  
	  public static final int GL_DYNAMIC_DRAW = 35048;
	  
	  public static final int GL_BUFFER_SIZE = 34660;
	  
	  public static final int GL_BUFFER_USAGE = 34661;
	  
	  public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;
	  
	  public static final int GL_FRONT = 1028;
	  
	  public static final int GL_BACK = 1029;
	  
	  public static final int GL_FRONT_AND_BACK = 1032;
	  
	  public static final int GL_TEXTURE_2D = 3553;
	  
	  public static final int GL_CULL_FACE = 2884;
	  
	  public static final int GL_BLEND = 3042;
	  
	  public static final int GL_DITHER = 3024;
	  
	  public static final int GL_STENCIL_TEST = 2960;
	  
	  public static final int GL_DEPTH_TEST = 2929;
	  
	  public static final int GL_SCISSOR_TEST = 3089;
	  
	  public static final int GL_POLYGON_OFFSET_FILL = 32823;
	  
	  public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
	  
	  public static final int GL_SAMPLE_COVERAGE = 32928;
	  
	  public static final int GL_NO_ERROR = 0;
	  
	  public static final int GL_INVALID_ENUM = 1280;
	  
	  public static final int GL_INVALID_VALUE = 1281;
	  
	  public static final int GL_INVALID_OPERATION = 1282;
	  
	  public static final int GL_OUT_OF_MEMORY = 1285;
	  
	  public static final int GL_CW = 2304;
	  
	  public static final int GL_CCW = 2305;
	  
	  public static final int GL_LINE_WIDTH = 2849;
	  
	  public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;
	  
	  public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
	  
	  public static final int GL_CULL_FACE_MODE = 2885;
	  
	  public static final int GL_FRONT_FACE = 2886;
	  
	  public static final int GL_DEPTH_RANGE = 2928;
	  
	  public static final int GL_DEPTH_WRITEMASK = 2930;
	 
	  public static final int GL_DEPTH_CLEAR_VALUE = 2931;
	  
	  public static final int GL_DEPTH_FUNC = 2932;
	  
	  public static final int GL_STENCIL_CLEAR_VALUE = 2961;
	  
	  public static final int GL_STENCIL_FUNC = 2962;
	  
	  public static final int GL_STENCIL_FAIL = 2964;
	  
	  public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
	  
	  public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
	  
	  public static final int GL_STENCIL_REF = 2967;
	  
	  public static final int GL_STENCIL_VALUE_MASK = 2963;
	  
	  public static final int GL_STENCIL_WRITEMASK = 2968;
	  
	  public static final int GL_STENCIL_BACK_FUNC = 34816;
	  
	  public static final int GL_STENCIL_BACK_FAIL = 34817;
	 
	  public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;
	  
	  public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;
	 
	  public static final int GL_STENCIL_BACK_REF = 36003;
	  
	  public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
	  
	  public static final int GL_STENCIL_BACK_WRITEMASK = 36005;
	  
	  public static final int GL_VIEWPORT = 2978;
	  
	  public static final int GL_SCISSOR_BOX = 3088;
	 
	  public static final int GL_COLOR_CLEAR_VALUE = 3106;
	  
	  public static final int GL_COLOR_WRITEMASK = 3107;
	  
	  public static final int GL_UNPACK_ALIGNMENT = 3317;
	  
	  public static final int GL_PACK_ALIGNMENT = 3333;
	  
	  public static final int GL_MAX_TEXTURE_SIZE = 3379;
	  
	  public static final int GL_MAX_VIEWPORT_DIMS = 3386;
	  
	  public static final int GL_SUBPIXEL_BITS = 3408;
	  
	  public static final int GL_RED_BITS = 3410;
	  
	  public static final int GL_GREEN_BITS = 3411;
	  
	  public static final int GL_BLUE_BITS = 3412;
	  
	  public static final int GL_ALPHA_BITS = 3413;
	  
	  public static final int GL_DEPTH_BITS = 3414;
	  
	  public static final int GL_STENCIL_BITS = 3415;
	  
	  public static final int GL_POLYGON_OFFSET_UNITS = 10752;
	  
	  public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
	  
	  public static final int GL_TEXTURE_BINDING_2D = 32873;
	  
	  public static final int GL_SAMPLE_BUFFERS = 32936;
	  
	  public static final int GL_SAMPLES = 32937;
	  
	  public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
	  
	  public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
	  
	  public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
	  
	  public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
	  
	  public static final int GL_DONT_CARE = 4352;
	 
	  public static final int GL_FASTEST = 4353;
	  
	  public static final int GL_NICEST = 4354;
	  
	  public static final int GL_GENERATE_MIPMAP_HINT = 33170;
	  
	  public static final int GL_BYTE = 5120;
	  
	  public static final int GL_UNSIGNED_BYTE = 5121;
	  
	  public static final int GL_SHORT = 5122;
	  
	  public static final int GL_UNSIGNED_SHORT = 5123;
	  
	  public static final int GL_INT = 5124;
	  
	  public static final int GL_UNSIGNED_INT = 5125;
	  
	  public static final int GL_FLOAT = 5126;
	  
	  public static final int GL_FIXED = 5132;
	  
	  public static final int GL_DEPTH_COMPONENT = 6402;
	  
	  public static final int GL_ALPHA = 6406;
	  
	  public static final int GL_RGB = 6407;
	  
	  public static final int GL_RGBA = 6408;
	  
	  public static final int GL_LUMINANCE = 6409;
	  
	  public static final int GL_LUMINANCE_ALPHA = 6410;
	  
	  public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
	  
	  public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
	  
	  public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;
	  
	  public static final int GL_FRAGMENT_SHADER = 35632;
	  
	  public static final int GL_VERTEX_SHADER = 35633;
	  
	  public static final int GL_MAX_VERTEX_ATTRIBS = 34921;
	  
	  public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
	  
	  public static final int GL_MAX_VARYING_VECTORS = 36348;
	  
	  public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;
	  
	  public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
	  
	  public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
	  
	  public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
	  
	  public static final int GL_SHADER_TYPE = 35663;
	  
	  public static final int GL_DELETE_STATUS = 35712;
	 
	  public static final int GL_LINK_STATUS = 35714;
	  
	  public static final int GL_VALIDATE_STATUS = 35715;
	  
	  public static final int GL_ATTACHED_SHADERS = 35717;
	  
	  public static final int GL_ACTIVE_UNIFORMS = 35718;
	  
	  public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;
	  
	  public static final int GL_ACTIVE_ATTRIBUTES = 35721;
	 
	  public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;
	  
	  public static final int GL_SHADING_LANGUAGE_VERSION = 35724;
	  
	  public static final int GL_CURRENT_PROGRAM = 35725;
	  
	  public static final int GL_NEVER = 512;
	  
	  public static final int GL_LESS = 513;
	  
	  public static final int GL_EQUAL = 514;
	  
	  public static final int GL_LEQUAL = 515;
	  
	  public static final int GL_GREATER = 516;
	  
	  public static final int GL_NOTEQUAL = 517;
	  
	  public static final int GL_GEQUAL = 518;
	  
	  public static final int GL_ALWAYS = 519;
 
	  public static final int GL_KEEP = 7680;
	  
	  public static final int GL_REPLACE = 7681;
	  
	  public static final int GL_INCR = 7682;
	  
	  public static final int GL_DECR = 7683;
	  
	  public static final int GL_INVERT = 5386;
	  
	  public static final int GL_INCR_WRAP = 34055;
	  
	  public static final int GL_DECR_WRAP = 34056;
	  
	  public static final int GL_VENDOR = 7936;
	  
	  public static final int GL_RENDERER = 7937;
	  
	  public static final int GL_VERSION = 7938;
	  
	  public static final int GL_EXTENSIONS = 7939;
	  
	  public static final int GL_NEAREST = 9728;
	  
	  public static final int GL_LINEAR = 9729;
	  
	  public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;
	  
	  public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;
	  
	  public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;
	  
	  public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;
	  
	  public static final int GL_TEXTURE_MAG_FILTER = 10240;
	  
	  public static final int GL_TEXTURE_MIN_FILTER = 10241;
	  
	  public static final int GL_TEXTURE_WRAP_S = 10242;
	  
	  public static final int GL_TEXTURE_WRAP_T = 10243;
	 
	  public static final int GL_TEXTURE = 5890;
	  
	  public static final int GL_TEXTURE_CUBE_MAP = 34067;
	  
	  public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
	  
	  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
	  
	  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
	  
	  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
	  
	  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
	  
	  public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
	  
	  public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
	  
	  public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
	  
	  public static final int GL_TEXTURE0 = 33984;
	  
	  public static final int GL_TEXTURE1 = 33985;
	  
	  public static final int GL_TEXTURE2 = 33986;
	  
	  public static final int GL_TEXTURE3 = 33987;
	  
	  public static final int GL_TEXTURE4 = 33988;
	  
	  public static final int GL_TEXTURE5 = 33989;
	  
	  public static final int GL_TEXTURE6 = 33990;
	  
	  public static final int GL_TEXTURE7 = 33991;
	  
	  public static final int GL_TEXTURE8 = 33992;
	  
	  public static final int GL_TEXTURE9 = 33993;
	  
	  public static final int GL_TEXTURE10 = 33994;
	  
	  public static final int GL_TEXTURE11 = 33995;
	  
	  public static final int GL_TEXTURE12 = 33996;
	  
	  public static final int GL_TEXTURE13 = 33997;
	  
	  public static final int GL_TEXTURE14 = 33998;
	  
	  public static final int GL_TEXTURE15 = 33999;
	  
	  public static final int GL_TEXTURE16 = 34000;
	  
	  public static final int GL_TEXTURE17 = 34001;
	  
	  public static final int GL_TEXTURE18 = 34002;
	  
	  public static final int GL_TEXTURE19 = 34003;
	  
	  public static final int GL_TEXTURE20 = 34004;
	  
	  public static final int GL_TEXTURE21 = 34005;
	  
	  public static final int GL_TEXTURE22 = 34006;
	  
	  public static final int GL_TEXTURE23 = 34007;
	  
	  public static final int GL_TEXTURE24 = 34008;
	  
	  public static final int GL_TEXTURE25 = 34009;
	  
	  public static final int GL_TEXTURE26 = 34010;
	  
	  public static final int GL_TEXTURE27 = 34011;
	  
	  public static final int GL_TEXTURE28 = 34012;
	  
	  public static final int GL_TEXTURE29 = 34013;
	  
	  public static final int GL_TEXTURE30 = 34014;
	  
	  public static final int GL_TEXTURE31 = 34015;
	  
	  public static final int GL_REPEAT = 10497;
	  
	  public static final int GL_CLAMP_TO_EDGE = 33071;
	  
	  public static final int GL_MIRRORED_REPEAT = 33648;
	  
	  public static final int GL_FLOAT_VEC2 = 35664;
	  
	  public static final int GL_FLOAT_VEC3 = 35665;
	  
	  public static final int GL_FLOAT_VEC4 = 35666;
	  
	  public static final int GL_INT_VEC2 = 35667;
	  
	  public static final int GL_INT_VEC3 = 35668;
	  
	  public static final int GL_INT_VEC4 = 35669;
	  
	  public static final int GL_BOOL = 35670;
	  
	  public static final int GL_BOOL_VEC2 = 35671;
	  
	  public static final int GL_BOOL_VEC3 = 35672;
	  
	  public static final int GL_BOOL_VEC4 = 35673;
	  
	  public static final int GL_FLOAT_MAT2 = 35674;
	  
	  public static final int GL_FLOAT_MAT3 = 35675;
	  
	  public static final int GL_FLOAT_MAT4 = 35676;
	  
	  public static final int GL_SAMPLER_2D = 35678;
	  
	  public static final int GL_SAMPLER_CUBE = 35680;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;
	  
	  public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
	  
	  public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
	  
	  public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
	  
	  public static final int GL_COMPILE_STATUS = 35713;
	  
	  public static final int GL_INFO_LOG_LENGTH = 35716;
	  
	  public static final int GL_SHADER_SOURCE_LENGTH = 35720;
	  
	  public static final int GL_SHADER_COMPILER = 36346;
	  
	  public static final int GL_SHADER_BINARY_FORMATS = 36344;
	  
	  public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
	  
	  public static final int GL_LOW_FLOAT = 36336;
	  
	  public static final int GL_MEDIUM_FLOAT = 36337;
	 
	  public static final int GL_HIGH_FLOAT = 36338;
	  
	  public static final int GL_LOW_INT = 36339;
	  
	  public static final int GL_MEDIUM_INT = 36340;
	 
	  public static final int GL_HIGH_INT = 36341;
	
	  public static final int GL_FRAMEBUFFER = 36160;

	  public static final int GL_RENDERBUFFER = 36161;
	  
	  public static final int GL_RGBA4 = 32854;
	  
	  public static final int GL_RGB5_A1 = 32855;
	  
	  public static final int GL_RGB565 = 36194;
	  
	  public static final int GL_DEPTH_COMPONENT16 = 33189;
	  
	  @java.lang.Deprecated
	  public static final int GL_STENCIL_INDEX = 6401;
	  
	  public static final int GL_STENCIL_INDEX8 = 36168;
	  
	  public static final int GL_RENDERBUFFER_WIDTH = 36162;
	  
	  public static final int GL_RENDERBUFFER_HEIGHT = 36163;
	  
	  public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
	 
	  public static final int GL_RENDERBUFFER_RED_SIZE = 36176;

	  public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;

	  public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;

	  public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;

	  public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;

	  public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;

	  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;

	  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;

	  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;

	  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;

	  public static final int GL_COLOR_ATTACHMENT0 = 36064;

	  public static final int GL_DEPTH_ATTACHMENT = 36096;

	  public static final int GL_STENCIL_ATTACHMENT = 36128;

	  public static final int GL_NONE = 0;

	  public static final int GL_FRAMEBUFFER_COMPLETE = 36053;

	  public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;

	  public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;

	  public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 36057;

	  public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;

	  public static final int GL_FRAMEBUFFER_BINDING = 36006;

	  public static final int GL_RENDERBUFFER_BINDING = 36007;
	  
	  public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;

	  public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
	  
	  // GLES 3.0 - GL 20~30
	  
	  
	  
	  //ADD....
	  
	  public static final int GL_READ_BUFFER                             = 0x0C02;
	  public static final int GL_UNPACK_ROW_LENGTH                       = 0x0CF2;
	  public static final int GL_UNPACK_SKIP_ROWS                        = 0x0CF3;
	  public static final int GL_UNPACK_SKIP_PIXELS                      = 0x0CF4;
	  public static final int GL_PACK_ROW_LENGTH                         = 0x0D02;
	  public static final int GL_PACK_SKIP_ROWS                          = 0x0D03;
	  public static final int GL_PACK_SKIP_PIXELS                        = 0x0D04;
	  public static final int GL_COLOR                                   = 0x1800;
	  public static final int GL_DEPTH                                   = 0x1801;
	  public static final int GL_STENCIL                                 = 0x1802;
	  public static final int GL_RED                                     = 0x1903;
	  public static final int GL_RGB8                                    = 0x8051;
	  public static final int GL_RGBA8                                   = 0x8058;
	  public static final int GL_RGB10_A2                                = 0x8059;
	  public static final int GL_TEXTURE_BINDING_3D                      = 0x806A;
	  public static final int GL_UNPACK_SKIP_IMAGES                      = 0x806D;
	  public static final int GL_UNPACK_IMAGE_HEIGHT                     = 0x806E;
	  public static final int GL_TEXTURE_3D                              = 0x806F;
	  public static final int GL_TEXTURE_WRAP_R                          = 0x8072;
	  public static final int GL_MAX_3D_TEXTURE_SIZE                     = 0x8073;
	  public static final int GL_UNSIGNED_INT_2_10_10_10_REV             = 0x8368;
	  public static final int GL_MAX_ELEMENTS_VERTICES                   = 0x80E8;
	  public static final int GL_MAX_ELEMENTS_INDICES                    = 0x80E9;
	  public static final int GL_TEXTURE_MIN_LOD                         = 0x813A;
	  public static final int GL_TEXTURE_MAX_LOD                         = 0x813B;
	  public static final int GL_TEXTURE_BASE_LEVEL                      = 0x813C;
	  public static final int GL_TEXTURE_MAX_LEVEL                       = 0x813D;
	  public static final int GL_MIN                                     = 0x8007;
	  public static final int GL_MAX                                     = 0x8008;
	  public static final int GL_DEPTH_COMPONENT24                       = 0x81A6;
	  public static final int GL_MAX_TEXTURE_LOD_BIAS                    = 0x84FD;
	  public static final int GL_TEXTURE_COMPARE_MODE                    = 0x884C;
	  public static final int GL_TEXTURE_COMPARE_FUNC                    = 0x884D;
	  public static final int GL_CURRENT_QUERY                           = 0x8865;
	  public static final int GL_QUERY_RESULT                            = 0x8866;
	  public static final int GL_QUERY_RESULT_AVAILABLE                  = 0x8867;
	  public static final int GL_BUFFER_MAPPED                           = 0x88BC;
	  public static final int GL_BUFFER_MAP_POINTER                      = 0x88BD;
	  public static final int GL_STREAM_READ                             = 0x88E1;
	  public static final int GL_STREAM_COPY                             = 0x88E2;
	  public static final int GL_STATIC_READ                             = 0x88E5;
	  public static final int GL_STATIC_COPY                             = 0x88E6;
	  public static final int GL_DYNAMIC_READ                            = 0x88E9;
	  public static final int GL_DYNAMIC_COPY                            = 0x88EA;
	  public static final int GL_MAX_DRAW_BUFFERS                        = 0x8824;
	  public static final int GL_DRAW_BUFFER0                            = 0x8825;
	  public static final int GL_DRAW_BUFFER1                            = 0x8826;
	  public static final int GL_DRAW_BUFFER2                            = 0x8827;
	  public static final int GL_DRAW_BUFFER3                            = 0x8828;
	  public static final int GL_DRAW_BUFFER4                            = 0x8829;
	  public static final int GL_DRAW_BUFFER5                            = 0x882A;
	  public static final int GL_DRAW_BUFFER6                            = 0x882B;
	  public static final int GL_DRAW_BUFFER7                            = 0x882C;
	  public static final int GL_DRAW_BUFFER8                            = 0x882D;
	  public static final int GL_DRAW_BUFFER9                            = 0x882E;
	  public static final int GL_DRAW_BUFFER10                           = 0x882F;
	  public static final int GL_DRAW_BUFFER11                           = 0x8830;
	  public static final int GL_DRAW_BUFFER12                           = 0x8831;
	  public static final int GL_DRAW_BUFFER13                           = 0x8832;
	  public static final int GL_DRAW_BUFFER14                           = 0x8833;
	  public static final int GL_DRAW_BUFFER15                           = 0x8834;
	  public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS         = 0x8B49;
	  public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS           = 0x8B4A;
	  public static final int GL_SAMPLER_3D                              = 0x8B5F;
	  public static final int GL_SAMPLER_2D_SHADOW                       = 0x8B62;
	  public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT         = 0x8B8B;
	  public static final int GL_PIXEL_PACK_BUFFER                       = 0x88EB;
	  public static final int GL_PIXEL_UNPACK_BUFFER                     = 0x88EC;
	  public static final int GL_PIXEL_PACK_BUFFER_BINDING               = 0x88ED;
	  public static final int GL_PIXEL_UNPACK_BUFFER_BINDING             = 0x88EF;
	  public static final int GL_FLOAT_MAT2x3                            = 0x8B65;
	  public static final int GL_FLOAT_MAT2x4                            = 0x8B66;
	  public static final int GL_FLOAT_MAT3x2                            = 0x8B67;
	  public static final int GL_FLOAT_MAT3x4                            = 0x8B68;
	  public static final int GL_FLOAT_MAT4x2                            = 0x8B69;
	  public static final int GL_FLOAT_MAT4x3                            = 0x8B6A;
	  public static final int GL_SRGB                                    = 0x8C40;
	  public static final int GL_SRGB8                                   = 0x8C41;
	  public static final int GL_SRGB8_ALPHA8                            = 0x8C43;
	  public static final int GL_COMPARE_REF_TO_TEXTURE                  = 0x884E;
	  public static final int GL_MAJOR_VERSION                           = 0x821B;
	  public static final int GL_MINOR_VERSION                           = 0x821C;
	  public static final int GL_NUM_EXTENSIONS                          = 0x821D;
	  public static final int GL_RGBA32F                                 = 0x8814;
	  public static final int GL_RGB32F                                  = 0x8815;
	  public static final int GL_RGBA16F                                 = 0x881A;
	  public static final int GL_RGB16F                                  = 0x881B;
	  public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER             = 0x88FD;
	  public static final int GL_MAX_ARRAY_TEXTURE_LAYERS                = 0x88FF;
	  public static final int GL_MIN_PROGRAM_TEXEL_OFFSET                = 0x8904;
	  public static final int GL_MAX_PROGRAM_TEXEL_OFFSET                = 0x8905;
	  public static final int GL_MAX_VARYING_COMPONENTS                  = 0x8B4B;
	  public static final int GL_TEXTURE_2D_ARRAY                        = 0x8C1A;
	  public static final int GL_TEXTURE_BINDING_2D_ARRAY                = 0x8C1D;
	  public static final int GL_R11F_G11F_B10F                          = 0x8C3A;
	  public static final int GL_UNSIGNED_INT_10F_11F_11F_REV            = 0x8C3B;
	  public static final int GL_RGB9_E5                                 = 0x8C3D;
	  public static final int GL_UNSIGNED_INT_5_9_9_9_REV                = 0x8C3E;
	  public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH   = 0x8C76;
	  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE          = 0x8C7F;
	  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS   = 0x8C80;
	  public static final int GL_TRANSFORM_FEEDBACK_VARYINGS             = 0x8C83;
	  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START         = 0x8C84;
	  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE          = 0x8C85;
	  public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN   = 0x8C88;
	  public static final int GL_RASTERIZER_DISCARD                      = 0x8C89;
	  public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS    = 0x8C8A;
	  public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 0x8C8B;
	  public static final int GL_INTERLEAVED_ATTRIBS                     = 0x8C8C;
	  public static final int GL_SEPARATE_ATTRIBS                        = 0x8C8D;
	  public static final int GL_TRANSFORM_FEEDBACK_BUFFER               = 0x8C8E;
	  public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING       = 0x8C8F;
	  public static final int GL_RGBA32UI                                = 0x8D70;
	  public static final int GL_RGB32UI                                 = 0x8D71;
	  public static final int GL_RGBA16UI                                = 0x8D76;
	  public static final int GL_RGB16UI                                 = 0x8D77;
	  public static final int GL_RGBA8UI                                 = 0x8D7C;
	  public static final int GL_RGB8UI                                  = 0x8D7D;
	  public static final int GL_RGBA32I                                 = 0x8D82;
	  public static final int GL_RGB32I                                  = 0x8D83;
	  public static final int GL_RGBA16I                                 = 0x8D88;
	  public static final int GL_RGB16I                                  = 0x8D89;
	  public static final int GL_RGBA8I                                  = 0x8D8E;
	  public static final int GL_RGB8I                                   = 0x8D8F;
	  public static final int GL_RED_INTEGER                             = 0x8D94;
	  public static final int GL_RGB_INTEGER                             = 0x8D98;
	  public static final int GL_RGBA_INTEGER                            = 0x8D99;
	  public static final int GL_SAMPLER_2D_ARRAY                        = 0x8DC1;
	  public static final int GL_SAMPLER_2D_ARRAY_SHADOW                 = 0x8DC4;
	  public static final int GL_SAMPLER_CUBE_SHADOW                     = 0x8DC5;
	  public static final int GL_UNSIGNED_INT_VEC2                       = 0x8DC6;
	  public static final int GL_UNSIGNED_INT_VEC3                       = 0x8DC7;
	  public static final int GL_UNSIGNED_INT_VEC4                       = 0x8DC8;
	  public static final int GL_INT_SAMPLER_2D                          = 0x8DCA;
	  public static final int GL_INT_SAMPLER_3D                          = 0x8DCB;
	  public static final int GL_INT_SAMPLER_CUBE                        = 0x8DCC;
	  public static final int GL_INT_SAMPLER_2D_ARRAY                    = 0x8DCF;
	  public static final int GL_UNSIGNED_INT_SAMPLER_2D                 = 0x8DD2;
	  public static final int GL_UNSIGNED_INT_SAMPLER_3D                 = 0x8DD3;
	  public static final int GL_UNSIGNED_INT_SAMPLER_CUBE               = 0x8DD4;
	  public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY           = 0x8DD7;
	  public static final int GL_BUFFER_ACCESS_FLAGS                     = 0x911F;
	  public static final int GL_BUFFER_MAP_LENGTH                       = 0x9120;
	  public static final int GL_BUFFER_MAP_OFFSET                       = 0x9121;
	  public static final int GL_DEPTH_COMPONENT32F                      = 0x8CAC;
	  public static final int GL_DEPTH32F_STENCIL8                       = 0x8CAD;
	  public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV          = 0x8DAD;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING   = 0x8210;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE   = 0x8211;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE         = 0x8212;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE       = 0x8213;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE        = 0x8214;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE       = 0x8215;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE       = 0x8216;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE     = 0x8217;
	  public static final int GL_FRAMEBUFFER_DEFAULT                     = 0x8218;
	  public static final int GL_FRAMEBUFFER_UNDEFINED                   = 0x8219;
	  public static final int GL_DEPTH_STENCIL_ATTACHMENT                = 0x821A;
	  public static final int GL_DEPTH_STENCIL                           = 0x84F9;
	  public static final int GL_UNSIGNED_INT_24_8                       = 0x84FA;
	  public static final int GL_DEPTH24_STENCIL8                        = 0x88F0;
	  public static final int GL_UNSIGNED_NORMALIZED                     = 0x8C17;
	  public static final int GL_DRAW_FRAMEBUFFER_BINDING                = GL_FRAMEBUFFER_BINDING;
	  public static final int GL_READ_FRAMEBUFFER                        = 0x8CA8;
	  public static final int GL_DRAW_FRAMEBUFFER                        = 0x8CA9;
	  public static final int GL_READ_FRAMEBUFFER_BINDING                = 0x8CAA;
	  public static final int GL_RENDERBUFFER_SAMPLES                    = 0x8CAB;
	  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER    = 0x8CD4;
	  public static final int GL_MAX_COLOR_ATTACHMENTS                   = 0x8CDF;
	  public static final int GL_COLOR_ATTACHMENT1                       = 0x8CE1;
	  public static final int GL_COLOR_ATTACHMENT2                       = 0x8CE2;
	  public static final int GL_COLOR_ATTACHMENT3                       = 0x8CE3;
	  public static final int GL_COLOR_ATTACHMENT4                       = 0x8CE4;
	  public static final int GL_COLOR_ATTACHMENT5                       = 0x8CE5;
	  public static final int GL_COLOR_ATTACHMENT6                       = 0x8CE6;
	  public static final int GL_COLOR_ATTACHMENT7                       = 0x8CE7;
	  public static final int GL_COLOR_ATTACHMENT8                       = 0x8CE8;
	  public static final int GL_COLOR_ATTACHMENT9                       = 0x8CE9;
	  public static final int GL_COLOR_ATTACHMENT10                      = 0x8CEA;
	  public static final int GL_COLOR_ATTACHMENT11                      = 0x8CEB;
	  public static final int GL_COLOR_ATTACHMENT12                      = 0x8CEC;
	  public static final int GL_COLOR_ATTACHMENT13                      = 0x8CED;
	  public static final int GL_COLOR_ATTACHMENT14                      = 0x8CEE;
	  public static final int GL_COLOR_ATTACHMENT15                      = 0x8CEF;
	  public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE      = 0x8D56;
	  public static final int GL_MAX_SAMPLES                             = 0x8D57;
	  public static final int GL_HALF_FLOAT                              = 0x140B;
	  public static final int GL_MAP_READ_BIT                            = 0x0001;
	  public static final int GL_MAP_WRITE_BIT                           = 0x0002;
	  public static final int GL_MAP_INVALIDATE_RANGE_BIT                = 0x0004;
	  public static final int GL_MAP_INVALIDATE_BUFFER_BIT               = 0x0008;
	  public static final int GL_MAP_FLUSH_EXPLICIT_BIT                  = 0x0010;
	  public static final int GL_MAP_UNSYNCHRONIZED_BIT                  = 0x0020;
	  public static final int GL_RG                                      = 0x8227;
	  public static final int GL_RG_INTEGER                              = 0x8228;
	  public static final int GL_R8                                      = 0x8229;
	  public static final int GL_RG8                                     = 0x822B;
	  public static final int GL_R16F                                    = 0x822D;
	  public static final int GL_R32F                                    = 0x822E;
	  public static final int GL_RG16F                                   = 0x822F;
	  public static final int GL_RG32F                                   = 0x8230;
	  public static final int GL_R8I                                     = 0x8231;
	  public static final int GL_R8UI                                    = 0x8232;
	  public static final int GL_R16I                                    = 0x8233;
	  public static final int GL_R16UI                                   = 0x8234;
	  public static final int GL_R32I                                    = 0x8235;
	  public static final int GL_R32UI                                   = 0x8236;
	  public static final int GL_RG8I                                    = 0x8237;
	  public static final int GL_RG8UI                                   = 0x8238;
	  public static final int GL_RG16I                                   = 0x8239;
	  public static final int GL_RG16UI                                  = 0x823A;
	  public static final int GL_RG32I                                   = 0x823B;
	  public static final int GL_RG32UI                                  = 0x823C;
	  public static final int GL_VERTEX_ARRAY_BINDING                    = 0x85B5;
	  public static final int GL_R8_SNORM                                = 0x8F94;
	  public static final int GL_RG8_SNORM                               = 0x8F95;
	  public static final int GL_RGB8_SNORM                              = 0x8F96;
	  public static final int GL_RGBA8_SNORM                             = 0x8F97;
	  public static final int GL_SIGNED_NORMALIZED                       = 0x8F9C;
	  public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX           = 0x8D69;
	  public static final int GL_COPY_READ_BUFFER                        = 0x8F36;
	  public static final int GL_COPY_WRITE_BUFFER                       = 0x8F37;
	  public static final int GL_COPY_READ_BUFFER_BINDING                = GL_COPY_READ_BUFFER;
	  public static final int GL_COPY_WRITE_BUFFER_BINDING               = GL_COPY_WRITE_BUFFER;
	  public static final int GL_UNIFORM_BUFFER                          = 0x8A11;
	  public static final int GL_UNIFORM_BUFFER_BINDING                  = 0x8A28;
	  public static final int GL_UNIFORM_BUFFER_START                    = 0x8A29;
	  public static final int GL_UNIFORM_BUFFER_SIZE                     = 0x8A2A;
	  public static final int GL_MAX_VERTEX_UNIFORM_BLOCKS               = 0x8A2B;
	  public static final int GL_MAX_FRAGMENT_UNIFORM_BLOCKS             = 0x8A2D;
	  public static final int GL_MAX_COMBINED_UNIFORM_BLOCKS             = 0x8A2E;
	  public static final int GL_MAX_UNIFORM_BUFFER_BINDINGS             = 0x8A2F;
	  public static final int GL_MAX_UNIFORM_BLOCK_SIZE                  = 0x8A30;
	  public static final int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS  = 0x8A31;
	  public static final int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS     = 0x8A33;
	  public static final int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT         = 0x8A34;
	  public static final int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH    = 0x8A35;
	  public static final int GL_ACTIVE_UNIFORM_BLOCKS                   = 0x8A36;
	  public static final int GL_UNIFORM_TYPE                            = 0x8A37;
	  public static final int GL_UNIFORM_SIZE                            = 0x8A38;
	  public static final int GL_UNIFORM_NAME_LENGTH                     = 0x8A39;
	  public static final int GL_UNIFORM_BLOCK_INDEX                     = 0x8A3A;
	  public static final int GL_UNIFORM_OFFSET                          = 0x8A3B;
	  public static final int GL_UNIFORM_ARRAY_STRIDE                    = 0x8A3C;
	  public static final int GL_UNIFORM_MATRIX_STRIDE                   = 0x8A3D;
	  public static final int GL_UNIFORM_IS_ROW_MAJOR                    = 0x8A3E;
	  public static final int GL_UNIFORM_BLOCK_BINDING                   = 0x8A3F;
	  public static final int GL_UNIFORM_BLOCK_DATA_SIZE                 = 0x8A40;
	  public static final int GL_UNIFORM_BLOCK_NAME_LENGTH               = 0x8A41;
	  public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS           = 0x8A42;
	  public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES    = 0x8A43;
	  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER    = 0x8A44;
	  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER  = 0x8A46;
	  public static final int GL_INVALID_INDEX                           = -1;
	  public static final int GL_MAX_VERTEX_OUTPUT_COMPONENTS            = 0x9122;
	  public static final int GL_MAX_FRAGMENT_INPUT_COMPONENTS           = 0x9125;
	  public static final int GL_MAX_SERVER_WAIT_TIMEOUT                 = 0x9111;
	  public static final int GL_OBJECT_TYPE                             = 0x9112;
	  public static final int GL_SYNC_CONDITION                          = 0x9113;
	  public static final int GL_SYNC_STATUS                             = 0x9114;
	  public static final int GL_SYNC_FLAGS                              = 0x9115;
	  public static final int GL_SYNC_FENCE                              = 0x9116;
	  public static final int GL_SYNC_GPU_COMMANDS_COMPLETE              = 0x9117;
	  public static final int GL_UNSIGNALED                              = 0x9118;
	  public static final int GL_SIGNALED                                = 0x9119;
	  public static final int GL_ALREADY_SIGNALED                        = 0x911A;
	  public static final int GL_TIMEOUT_EXPIRED                         = 0x911B;
	  public static final int GL_CONDITION_SATISFIED                     = 0x911C;
	  public static final int GL_WAIT_FAILED                             = 0x911D;
	  public static final int GL_SYNC_FLUSH_COMMANDS_BIT                 = 0x00000001;
	  public static final long GL_TIMEOUT_IGNORED                         = -1;
	  public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR             = 0x88FE;
	  public static final int GL_ANY_SAMPLES_PASSED                      = 0x8C2F;
	  public static final int GL_ANY_SAMPLES_PASSED_CONSERVATIVE         = 0x8D6A;
	  public static final int GL_SAMPLER_BINDING                         = 0x8919;
	  public static final int GL_RGB10_A2UI                              = 0x906F;
	  public static final int GL_TEXTURE_SWIZZLE_R                       = 0x8E42;
	  public static final int GL_TEXTURE_SWIZZLE_G                       = 0x8E43;
	  public static final int GL_TEXTURE_SWIZZLE_B                       = 0x8E44;
	  public static final int GL_TEXTURE_SWIZZLE_A                       = 0x8E45;
	  public static final int GL_GREEN                                   = 0x1904;
	  public static final int GL_BLUE                                    = 0x1905;
	  public static final int GL_INT_2_10_10_10_REV                      = 0x8D9F;
	  public static final int GL_TRANSFORM_FEEDBACK                      = 0x8E22;
	  public static final int GL_TRANSFORM_FEEDBACK_PAUSED               = 0x8E23;
	  public static final int GL_TRANSFORM_FEEDBACK_ACTIVE               = 0x8E24;
	  public static final int GL_TRANSFORM_FEEDBACK_BINDING              = 0x8E25;
	  public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT         = 0x8257;
	  public static final int GL_PROGRAM_BINARY_LENGTH                   = 0x8741;
	  public static final int GL_NUM_PROGRAM_BINARY_FORMATS              = 0x87FE;
	  public static final int GL_PROGRAM_BINARY_FORMATS                  = 0x87FF;
	  public static final int GL_COMPRESSED_R11_EAC                      = 0x9270;
	  public static final int GL_COMPRESSED_SIGNED_R11_EAC               = 0x9271;
	  public static final int GL_COMPRESSED_RG11_EAC                     = 0x9272;
	  public static final int GL_COMPRESSED_SIGNED_RG11_EAC              = 0x9273;
	  public static final int GL_COMPRESSED_RGB8_ETC2                    = 0x9274;
	  public static final int GL_COMPRESSED_SRGB8_ETC2                   = 0x9275;
	  public static final int GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2     = 0x9276;
	  public static final int GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2    = 0x9277;
	  public static final int GL_COMPRESSED_RGBA8_ETC2_EAC               = 0x9278;
	  public static final int GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC        = 0x9279;
	  public static final int GL_TEXTURE_IMMUTABLE_FORMAT                = 0x912F;
	  public static final int GL_MAX_ELEMENT_INDEX                       = 0x8D6B;
	  public static final int GL_NUM_SAMPLE_COUNTS                       = 0x9380;
	  public static final int GL_TEXTURE_IMMUTABLE_LEVELS                = 0x82DF;
	  
	  
}
