package com.edavtyan.materialplayer.app.music.data;

public class MusicAlbum {
    private final long id;
    private final String title;
    private final String artist;
    private final int tracksCount;
    private final String art;


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

    public String getArt() {
        return art;
    }


    public MusicAlbum(long id, String title, String artist, int tracksCount, String art) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.tracksCount = tracksCount;
        this.art = art;
    }
}
