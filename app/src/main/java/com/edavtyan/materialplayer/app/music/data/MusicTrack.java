package com.edavtyan.materialplayer.app.music.data;

public class MusicTrack {
    public long Index;
    public String Title;
    public String Artist;
    public String Album;

    public long getIndex() {
        return Index;
    }

    public String getTitle() {
        return Title;
    }

    public String getArtist() {
        return Artist;
    }

    public String getAlbum() {
        return Album;
    }

    public MusicTrack(long index, String title, String artist, String album) {
        Index = index;
        Title = title;
        Artist = artist;
        Album = album;
    }
}
