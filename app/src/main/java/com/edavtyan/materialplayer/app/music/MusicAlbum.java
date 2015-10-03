package com.edavtyan.materialplayer.app.music;

public class MusicAlbum {
    private long id;
    private String title;
    private String artist;
    private int tracksCount;


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getTracksCount() {
        return tracksCount;
    }


    public MusicAlbum(long id, String title, String artist, int tracksCount) {

    }
}
