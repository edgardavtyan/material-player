package com.edavtyan.materialplayer.transition;

import android.view.View;

import java.util.Stack;

public class CurrentSharedViews {
	private final Stack<View> sharedViewsStack;

	public CurrentSharedViews() {
		sharedViewsStack = new Stack<>();
	}

	public void push(SourceSharedViews sharedViews) {
		for (View view : sharedViews.getViews()) {
			sharedViewsStack.push(view);
		}
	}

	public View pop() {
		return sharedViewsStack.pop();
	}

	public View peek() {
		return sharedViewsStack.peek();
	}
}
