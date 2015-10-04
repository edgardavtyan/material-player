package com.edavtyan.materialplayer.app.music.data;

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
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.tracksCount = tracksCount;
    }
}
