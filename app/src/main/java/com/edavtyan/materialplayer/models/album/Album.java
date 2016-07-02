package com.edavtyan.materialplayer.models.album;

import java.io.File;

import lombok.Data;

@Data
public class Album {
	private int id;
	private String title;
	private String artistTitle;
	private int tracksCount;
	private File art;
}
