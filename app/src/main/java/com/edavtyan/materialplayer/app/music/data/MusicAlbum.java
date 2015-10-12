package com.edavtyan.materialplayer.app.music.data;

public class MusicAlbum {
    private long id;
    private String title;
    private String artist;
    private int tracksCount;
    private String art;


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


    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracksCount(int tracksCount) {
        this.tracksCount = tracksCount;
    }

    public void setArt(String art) {
        this.art = art;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicAlbum that = (MusicAlbum) o;

        if (id != that.id) return false;
        if (tracksCount != that.tracksCount) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        return !(art != null ? !art.equals(that.art) : that.art != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + tracksCount;
        result = 31 * result + (art != null ? art.hashCode() : 0);
        return result;
    }
}
