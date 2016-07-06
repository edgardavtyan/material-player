package com.edavtyan.materialplayer.components.tracks.models;

import lombok.Data;

@Data
public class Track {
	private int id;
	private int track;
	private String title;
	private String path;
	private long dateModified;
	private long duration;
	private int artistId;
	private String artistTitle;
	private int albumId;
	private String albumTitle;
	private int queueIndex;
}
