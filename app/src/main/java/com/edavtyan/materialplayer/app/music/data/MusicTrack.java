package com.edavtyan.materialplayer.app.music.data;

public class MusicTrack {
    private long index;
    private long duration;
    private String title;
    private String artist;
    private String album;


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


    public void setIndex(long index) {
        this.index = index;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicTrack that = (MusicTrack) o;

        if (index != that.index) return false;
        if (duration != that.duration) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        return !(album != null ? !album.equals(that.album) : that.album != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (index ^ (index >>> 32));
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }
}
