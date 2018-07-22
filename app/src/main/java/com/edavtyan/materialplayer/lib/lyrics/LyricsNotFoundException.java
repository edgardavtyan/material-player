package com.edavtyan.materialplayer.lib.lyrics;

public class LyricsNotFoundException extends Exception {
	public LyricsNotFoundException(String artist, String track) {
		super("Lyrics for " + artist + ":" + track + " not found");
	}
}
