package com.edavtyan.materialplayer.app.music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MusicLibrary {
    /*
     * Fields
     */
    private ArrayList<MusicTrack> tracks;
    private ArrayList<MusicArtist> artists;
    private ArrayList<MusicAlbum> albums;
    private ContentResolver contentResolver;


    /*
     * Constructors
     */
    public MusicLibrary(Context context) {
        tracks = new ArrayList<>();
        artists = new ArrayList<>();
        albums = new ArrayList<>();
        contentResolver = context.getContentResolver();
        loadArtists();
    }


    /*
     * Public methods
     */
    public ArrayList<MusicArtist> getArtists() {
        return artists;
    }

    /*
     * Private methods
     */
    private void loadAlbums() {
        Uri albumsUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS,
        };

        Cursor cursor = contentResolver.query(albumsUri, projection, null, null, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String artist = cursor.getString(2);
            int tracksCount = cursor.getInt(3);
            MusicAlbum album = new MusicAlbum(id, title, artist, tracksCount);
            albums.add(album);
        }

        cursor.close();
    }

    private void loadArtists() {
        Uri artistsUri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
        };

        Cursor cursor = contentResolver.query(artistsUri, projection, null, null, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            int albumsCount = cursor.getInt(2);
            int tracksCount = cursor.getInt(3);
            MusicArtist artist = new MusicArtist(id, title, albumsCount, tracksCount);
            artists.add(artist);
        }

        cursor.close();
    }

    private void loadTracks() {
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.TRACK,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM
        };

        Cursor cursor = contentResolver.query(musicUri, projection, null, null, null);

        while (cursor.moveToNext()) {
            long index = cursor.getLong(0);
            String title = cursor.getString(1);
            String artist = cursor.getString(2);
            String album = cursor.getString(3);
            MusicTrack track = new MusicTrack(index, title, artist, album);
            tracks.add(track);
        }

        cursor.close();
    }
}
