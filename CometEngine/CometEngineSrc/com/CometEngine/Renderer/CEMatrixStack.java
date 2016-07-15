package com.CometEngine.Renderer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import com.CometEngine.Util.Meth.CEMatrix4f;

public class CEMatrixStack {
	private final static CEMatrixStack Instance = new CEMatrixStack();
	private final Stack<CEMatrix4f> STACK = new Stack<CEMatrix4f>();

	public static CEMatrixStack getInstance() {
		return Instance;
	}

	public void GetTopOfStackMatrix(CEMatrix4f result) {
		if (STACK.isEmpty()) {
			result.resetIDENTITY();
			return;
		}
		result.setMatrix(STACK.peek());
	}

	public void Push(CEMatrix4f matrix) {
		STACK.push(matrix);
	}

	public void Pop() {
		STACK.pop();
	}
}
