package com.edavtyan.materialplayer.components.tracks;

import lombok.Data;

@Data
public class Track {
	private int id;
	private String title;
	private String path;
	private long dateModified;
	private int albumId;
	private long duration;
	private String artistTitle;
	private String albumTitle;
	private int queueIndex;
}
