package com.edavtyan.materialplayer.app.music.data;

public class MusicArtist {
    private long id;
    private String title;
    private int albumsCount;
    private int tracksCount;


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


    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbumsCount(int albumsCount) {
        this.albumsCount = albumsCount;
    }

    public void setTracksCount(int tracksCount) {
        this.tracksCount = tracksCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicArtist that = (MusicArtist) o;

        if (id != that.id) return false;
        if (albumsCount != that.albumsCount) return false;
        if (tracksCount != that.tracksCount) return false;
        return !(title != null ? !title.equals(that.title) : that.title != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + albumsCount;
        result = 31 * result + tracksCount;
        return result;
    }
}
