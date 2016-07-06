package com.edavtyan.materialplayer.components.artists.models;

import lombok.Data;

@Data
public class Artist {
	private int id;
	private String title;
	private int tracksCount;
	private int albumsCount;
}
