package com.edavtyan.materialplayer.app.models.track;

import lombok.Data;

@Data
public class Track {
	private int queueIndex;
	private int trackId;
	private int albumId;
	private long duration;
	private String trackTitle;
	private String artistTitle;
	private String albumTitle;
	private String path;
	private long dateModified;
}
