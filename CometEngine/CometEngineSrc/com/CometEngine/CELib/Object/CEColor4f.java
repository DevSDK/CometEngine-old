package com.CometEngine.CELib.Object;

import java.nio.FloatBuffer;

import com.CometEngine.Util.Buffer.CEBufferUtils;

public class CEColor4f {
	public float Red = 0;
	public float Green = 0;
	public float Blue = 0;
	public float Alpha = 0;

	public final static CEColor4f RED = new CEColor4f(1, 0, 0, 1);
	public final static CEColor4f GREEN = new CEColor4f(0, 1, 0, 1);
	public final static CEColor4f BLUE = new CEColor4f(0, 0, 1, 1);
	public final static CEColor4f BLACK = new CEColor4f(0, 0, 0, 1);
	public final static CEColor4f WHITE = new CEColor4f(1, 1, 1, 1);
	public final static CEColor4f YELLOW = new CEColor4f(1, 1, 0, 1);
	public final static CEColor4f MAGENTA = new CEColor4f(1, 0, 1, 1);
	public final static CEColor4f ORANGE = new CEColor4f(1, 0.5f, 0, 1);
	public final static CEColor4f GRAY = new CEColor4f(0.65f, 0.65f, 0.65f, 1);

	public CEColor4f(float Red, float Green, float Blue, float alpha) {
		this.Red = Red;
		this.Green = Green;
		this.Blue = Blue;
		this.Alpha = alpha;

	}

	public CEColor4f Clone() {
		return new CEColor4f(Red, Green, Blue, Alpha);
	}

	public void getBuffer(FloatBuffer buffer) {

		buffer.put(Red);
		buffer.put(Green);
		buffer.put(Blue);
		buffer.put(Alpha);
		buffer.flip();
	}
}
