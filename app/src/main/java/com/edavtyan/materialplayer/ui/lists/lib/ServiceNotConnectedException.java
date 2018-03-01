package com.edavtyan.materialplayer.ui.lists.lib;

public class ServiceNotConnectedException extends IllegalStateException {
	public ServiceNotConnectedException() {
		super("PlayerService is not connected yet");
	}
}
