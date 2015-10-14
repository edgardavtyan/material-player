package com.edavtyan.materialplayer.app.music;

import java.io.File;

import lombok.Data;

@Data
public class Metadata {
    private int trackId;
    private int albumId;
    private long duration;
    private String trackTitle;
    private String artistTitle;
    private String albumTitle;
    private File artFile;
}
