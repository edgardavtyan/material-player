package com.edavtyan.materialplayer.transition.exceptions;

public class ShareViewNotFoundException extends RuntimeException {
	public ShareViewNotFoundException(String transitionName) {
		super(String.format("Matching shared view with transitionName='%s' not found", transitionName));
	}
}
