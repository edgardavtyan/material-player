package com.edavtyan.materialplayer.app.music.providers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.edavtyan.materialplayer.app.music.data.MusicAlbum;
import com.edavtyan.materialplayer.app.music.data.MusicArtist;

import java.util.ArrayList;

public class AlbumsProvider {
    private ContentResolver contentResolver;
    private ArrayList<MusicAlbum> albums;


    public ArrayList<MusicAlbum> getAlbums() {
        return albums;
    }


    public AlbumsProvider(Context context) {
        contentResolver = context.getContentResolver();
        albums = new ArrayList<>();
        loadAlbums();
    }


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
}
