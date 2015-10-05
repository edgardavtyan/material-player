package com.edavtyan.materialplayer.app.music.data;

public class MusicArtist {
    private final long id;
    private final String title;
    private final int albumsCount;
    private final int tracksCount;



    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAlbumsCount() {
        return albumsCount;
    }

    public int getTracksCount() {
        return tracksCount;
    }



    public MusicArtist(long id, String title, int albumsCount, int tracksCount) {
        this.id = id;
        this.title = title;
        this.albumsCount = albumsCount;
        this.tracksCount = tracksCount;
    }
}
