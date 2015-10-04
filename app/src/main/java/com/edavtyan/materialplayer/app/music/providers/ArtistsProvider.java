package com.edavtyan.materialplayer.app.music.providers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.edavtyan.materialplayer.app.music.data.MusicArtist;

import java.util.ArrayList;

public class ArtistsProvider {
    private ContentResolver contentResolver;
    private ArrayList<MusicArtist> artists;


    public ArrayList<MusicArtist> getArtists() {
        return artists;
    }


    public ArtistsProvider(Context context) {
        contentResolver = context.getContentResolver();
        artists = new ArrayList<>();
        loadArtists();
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
}
