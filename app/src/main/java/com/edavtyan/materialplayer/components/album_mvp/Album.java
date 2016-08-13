package com.edavtyan.materialplayer.components.album_mvp;

import lombok.Data;

@Data
public class Album {
	private int id;
	private String title;
	private String artistTitle;
	private int tracksCount;
	private String art;
}
