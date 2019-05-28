package com.edavtyan.materialplayer.lib.music_api;

public class NoConnectionException extends Exception {
	public NoConnectionException() {
		super("Could not connect to music API server");
	}
}
