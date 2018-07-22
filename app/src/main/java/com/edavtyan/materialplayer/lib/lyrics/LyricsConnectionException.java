package com.edavtyan.materialplayer.lib.lyrics;

public class LyricsConnectionException extends Exception {
	public LyricsConnectionException(Exception e) {
		super("Could not connect to lyrics database", e);
	}
}
