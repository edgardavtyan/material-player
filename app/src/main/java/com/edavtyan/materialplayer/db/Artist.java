package com.edavtyan.materialplayer.db;

import lombok.Data;

@Data
public class Artist {
	private int id;
	private String title;
	private int tracksCount;
	private int albumsCount;
}
