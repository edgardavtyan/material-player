package com.edavtyan.materialplayer.db.types;

import lombok.Data;

@Data
public class Album {
	private int id;
	private String title;
	private String artistTitle;
	private int tracksCount;
	private String art;
}
