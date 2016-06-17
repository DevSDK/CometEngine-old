package com.CometEngine.Util.Meth;

import java.nio.FloatBuffer;

import com.CometEngine.Util.Meth.jglm.Mat4;
import com.CometEngine.Util.Meth.jglm.Vec3;
import com.CometEngine.Util.Meth.jglm.Vec4;

public class CEMatrix4f {
	private float m00, m10, m20, m30;
	private float m01, m11, m21, m31;
	private  float m02, m12, m22, m32;
	private float m03, m13, m23, m33;
	
	public CEMatrix4f()
	{
		resetIDENTITY();
	}
	public CEMatrix4f(CEMatrix4f matrix)
	{
		m00 = matrix.m00;
		m01 = matrix.m01;
		m02 = matrix.m02;
		m03 = matrix.m03;
		

		m10 = matrix.m10;
		m11 = matrix.m11;
		m12 = matrix.m12;
		m13 = matrix.m13;

		m20 = matrix.m20;
		m21 = matrix.m21;
		m22 = matrix.m22;
		m23 = matrix.m23;
		

		m30 = matrix.m30;
		m31 = matrix.m31;
		m32 = matrix.m32;
		m33 = matrix.m33;
	}
	
	
	public void resetIDENTITY()
	{
		m00 = 1;  m01 = 0;	m02 = 0;	m03 = 0;
		m10 = 0;  m11 = 1;	m12 = 0;	m13 = 0;
		m20 = 0;  m21 = 0;	m22 = 1;	m23 = 0;
		m30 = 0;  m31 = 0;	m32 = 0;	m33 = 1;

	}
	
	public CEMatrix4f multiply(CEMatrix4f right)
	{
        m00 = this.m00 * right.m00 + this.m10 * right.m01 + this.m20 * right.m02 + this.m30 * right.m03;
        m01 = this.m01 * right.m00 + this.m11 * right.m01 + this.m21 * right.m02 + this.m31 * right.m03;
        m02 = this.m02 * right.m00 + this.m12 * right.m01 + this.m22 * right.m02 + this.m32 * right.m03;
        m03 = this.m03 * right.m00 + this.m13 * right.m01 + this.m23 * right.m02 + this.m33 * right.m03;
        m10 = this.m00 * right.m10 + this.m10 * right.m11 + this.m20 * right.m12 + this.m30 * right.m13;
        m11 = this.m01 * right.m10 + this.m11 * right.m11 + this.m21 * right.m12 + this.m31 * right.m13;
        m12 = this.m02 * right.m10 + this.m12 * right.m11 + this.m22 * right.m12 + this.m32 * right.m13;
        m13 = this.m03 * right.m10 + this.m13 * right.m11 + this.m23 * right.m12 + this.m33 * right.m13;
        m20 = this.m00 * right.m20 + this.m10 * right.m21 + this.m20 * right.m22 + this.m30 * right.m23;
        m21 = this.m01 * right.m20 + this.m11 * right.m21 + this.m21 * right.m22 + this.m31 * right.m23;
        m22 = this.m02 * right.m20 + this.m12 * right.m21 + this.m22 * right.m22 + this.m32 * right.m23;
        m23 = this.m03 * right.m20 + this.m13 * right.m21 + this.m23 * right.m22 + this.m33 * right.m23;
        m30 = this.m00 * right.m30 + this.m10 * right.m31 + this.m20 * right.m32 + this.m30 * right.m33;
        m31 = this.m01 * right.m30 + this.m11 * right.m31 + this.m21 * right.m32 + this.m31 * right.m33;
        m32 = this.m02 * right.m30 + this.m12 * right.m31 + this.m22 * right.m32 + this.m32 * right.m33;
        m33 = this.m03 * right.m30 + this.m13 * right.m31 + this.m23 * right.m32 + this.m33 * right.m33;
        return this;
	}
	
    public CEMatrix4f subtract(final CEMatrix4f right) {
                        m00 -= right.m00;
                        m01 -= right.m01;
                        m02 -= right.m02;
                        m03 -= right.m03;
                        m10 -= right.m10;
                        m11 -= right.m11; 
                        m12 -= right.m12;
                        m13 -= right.m13;
                        m20 -= right.m20; 
                        m21 -= right.m21;
                        m22 -= right.m22;
                        m23 -= right.m23;
                        m30 -= right.m30;
                        m31 -= right.m31;
                        m32 -= right.m32;
                        m33 -= right.m33;
        return this;
    }
    
    
    public CEMatrix4f add(final CEMatrix4f right) { 
        m00 += right.m00;
        m01 += right.m01;
        m02 += right.m02;
        m03 += right.m03;
        m10 += right.m10;
        m11 += right.m11; 
        m12 += right.m12;
        m13 += right.m13;
        m20 += right.m20; 
        m21 += right.m21;
        m22 += right.m22;
        m23 += right.m23;
        m30 += right.m30;
        m31 += right.m31;
        m32 += right.m32;
        m33 += right.m33;
        return this;
}
    @Override
    public int hashCode()
    {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(m00);
		result = prime * result + Float.floatToIntBits(m01);
		result = prime * result + Float.floatToIntBits(m02);
		result = prime * result + Float.floatToIntBits(m03);
		result = prime * result + Float.floatToIntBits(m10);
		result = prime * result + Float.floatToIntBits(m11);
		result = prime * result + Float.floatToIntBits(m12);
		result = prime * result + Float.floatToIntBits(m13);
		result = prime * result + Float.floatToIntBits(m20);
		result = prime * result + Float.floatToIntBits(m21);
		result = prime * result + Float.floatToIntBits(m22);
		result = prime * result + Float.floatToIntBits(m23);
		result = prime * result + Float.floatToIntBits(m30);
		result = prime * result + Float.floatToIntBits(m31);
		result = prime * result + Float.floatToIntBits(m32);
		result = prime * result + Float.floatToIntBits(m33);
		return result;
    }
    
    
    public CEMatrix4f scale(float x, float y, float z)
    {
    	   m00 = this.m00 * x;
           m01 = this.m01 * x ;
           m02 = this.m02 * x ;
           m03 = this.m03 * x;
           m10 = this.m10 * y ;
           m11 =  this.m11 * y ;
           m12 =  this.m12 * y;
           m13 =  this.m13 * y;
           m20 = this.m20 * z ;
           m21 =  this.m21 *z ;
           m22 =  this.m22 * z;
           m23 =  this.m23 * z ;
           return this;
    }
    
	public boolean equals(CEMatrix4f other) {

		
		if (Float.floatToIntBits(m00) != Float.floatToIntBits(other.m00)) {
			return false;
		}
		if (Float.floatToIntBits(m01) != Float.floatToIntBits(other.m01)) {
			return false;
		}
		if (Float.floatToIntBits(m02) != Float.floatToIntBits(other.m02)) {
			return false;
		}
		if (Float.floatToIntBits(m03) != Float.floatToIntBits(other.m03)) {
			return false;
		}
		if (Float.floatToIntBits(m10) != Float.floatToIntBits(other.m10)) {
			return false;
		}
		if (Float.floatToIntBits(m11) != Float.floatToIntBits(other.m11)) {
			return false;
		}
		if (Float.floatToIntBits(m12) != Float.floatToIntBits(other.m12)) {
			return false;
		}
		if (Float.floatToIntBits(m13) != Float.floatToIntBits(other.m13)) {
			return false;
		}
		if (Float.floatToIntBits(m20) != Float.floatToIntBits(other.m20)) {
			return false;
		}
		if (Float.floatToIntBits(m21) != Float.floatToIntBits(other.m21)) {
			return false;
		}
		if (Float.floatToIntBits(m22) != Float.floatToIntBits(other.m22)) {
			return false;
		}
		if (Float.floatToIntBits(m23) != Float.floatToIntBits(other.m23)) {
			return false;
		}
		if (Float.floatToIntBits(m30) != Float.floatToIntBits(other.m30)) {
			return false;
		}
		if (Float.floatToIntBits(m31) != Float.floatToIntBits(other.m31)) {
			return false;
		}
		if (Float.floatToIntBits(m32) != Float.floatToIntBits(other.m32)) {
			return false;
		}
		if (Float.floatToIntBits(m33) != Float.floatToIntBits(other.m33)) {
			return false;
		}
		return true;
	}
	
	public CEMatrix4f translate(float x, float y, float z) {
		float vm00 =  m00* x; 
		float vm01 =  m01* x; 
		float vm02 =  m02* x; 
		float vm03 =  m03* x; 
		
		float vm10 =  m10* y; 
		float vm11 =  m11* y; 
		float vm12 =  m12* y; 
		float vm13 =  m13* y; 

		float vm20 =  m20* z; 
		float vm21 =  m21* z; 
		float vm22 =  m22* z; 
		float vm23 =  m23* z; 
		
		float rx = vm00 + vm10 + vm20 + m30;
		float ry = vm01 + vm11 + vm21 + m31;
		float rz = vm02 + vm12 + vm22 + m32;
		float rw = vm03 + vm13 + vm23 + m33;

		m30 = rx;
		m31 = ry;
		m32 = rz;
		m33 = rw;
		
		
		
		return this;
	
	}
	public CEMatrix4f rotate(float angle, float ax, float ay, float az)
	{
		double rcos = Math.cos(angle);
		double rsin = Math.sin(angle);
		float x = ax;
		float y = ay;
		float z = az;
		
		 m00 = (float) (rcos + x * x * (1 - rcos));
		 m01 = (float) (z * rsin + y * x * (1 - rcos));
		 m02 = (float) (-y * rsin + z * x * (1 - rcos));
		
		 m10 = (float) (-z * rsin + x * y * (1 - rcos));
		 m11 = (float) (rcos + y * y * (1 - rcos));
		 m12 =  (float) (x * rsin + z * y * (1 - rcos));
		
		 m20 = (float) (y * rsin + x * z * (1 - rcos));
		 m21 = (float) (-x * rsin + y * z * (1 - rcos));
		 m22 = (float) (rcos + z * z * (1 - rcos));

		 return this;
	}
	
	public void setMatrix(CEMatrix4f matrix)
	{
		m00 = matrix.m00;
		m01 = matrix.m01;
		m02 = matrix.m02;
		m03 = matrix.m03;
		

		m10 = matrix.m10;
		m11 = matrix.m11;
		m12 = matrix.m12;
		m13 = matrix.m13;

		m20 = matrix.m20;
		m21 = matrix.m21;
		m22 = matrix.m22;
		m23 = matrix.m23;
		

		m30 = matrix.m30;
		m31 = matrix.m31;
		m32 = matrix.m32;
		m33 = matrix.m33;
	}
	public void getBuffer(FloatBuffer buffer) {
		
		buffer.put(m00)
			.put(m01)
			.put(m02)
			.put(m03);
		
		buffer.put(m10)
			.put(m11)
			.put(m12)
			.put(m13);
		
		buffer.put(m20)
			.put(m21)
			.put(m22)
			.put(m23);
		
		buffer.put(m30)
			.put(m31)
			.put(m32)
			.put(m33);
		
		buffer.flip();
	}

}
