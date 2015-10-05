package com.edavtyan.materialplayer.app.music.data;

public class MusicTrack {
    private final long index;
    private final long duration;
    private final String title;
    private final String artist;
    private final String album;



    public long getIndex() {
        return index;
    }

    public long getDuration() {
        return duration;
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



    public MusicTrack(long index, String title, String artist, String album, long duration) {
        this.index = index;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }
}
