package com.edavtyan.materialplayer.app.models.album;

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
