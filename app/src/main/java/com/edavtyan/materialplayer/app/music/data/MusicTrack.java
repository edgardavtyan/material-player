package com.edavtyan.materialplayer.app.music.data;

public class MusicTrack {
    private long index;
    private String title;
    private String artist;
    private String album;
    private long duration;

    public long getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public long getDuration() {
        return duration;
    }

    public MusicTrack(long index, String title, String artist, String album, long duration) {
        this.index = index;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }
}
