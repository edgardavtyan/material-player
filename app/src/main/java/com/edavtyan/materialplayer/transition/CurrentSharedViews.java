package com.edavtyan.materialplayer.transition;

import java.util.Stack;

public class CurrentSharedViews {
	private final Stack<SourceSharedViews> sharedViewsStack;

	public CurrentSharedViews() {
		sharedViewsStack = new Stack<>();
	}

	public void push(SourceSharedViews sharedViews) {
		sharedViewsStack.push(sharedViews);
	}

	public SourceSharedViews pop() {
		return sharedViewsStack.pop();
	}

	public SourceSharedViews peek() {
		return sharedViewsStack.peek();
	}

	public boolean isEmpty() {
		return sharedViewsStack.isEmpty();
	}
}
