package com.edavtyan.materialplayer.ui.lists.artist_list;

class CouldNotLoadImageException extends Exception {
	public CouldNotLoadImageException(String artistTitle) {
		super(String.format("Could not load image for <%s>", artistTitle));
	}
}
