package com.edavtyan.materialplayer;

import java.util.Stack;

public class CurrentSharedViews {
	private final Stack<SharedViews> sharedViewsStack;

	public CurrentSharedViews() {
		sharedViewsStack = new Stack<>();
	}

	public void push(SharedViews sharedViews) {
		sharedViewsStack.push(sharedViews);
	}

	public SharedViews pop() {
		return sharedViewsStack.pop();
	}

	public SharedViews peek() {
		return sharedViewsStack.peek();
	}
}
