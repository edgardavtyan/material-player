package com.edavtyan.materialplayer.app.music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.edavtyan.materialplayer.app.utils.DataStorage;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.ByteArrayOutputStream;
import java.io.File;

public final class ArtProvider {
    private ArtProvider() {}

    public static File fromTrack(Track track) {
        File artFile = DataStorage.readArt(track.getAlbumId());
        if (!artFile.exists() || artFile.lastModified() < track.getDateModified()) {
            try {
                AudioFile audiofile = AudioFileIO.read(new File(track.getPath()));
                byte[] artBytes = audiofile.getTag().getFirstArtwork().getBinaryData();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                Bitmap art = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
                art.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);

                DataStorage.saveArt(track.getAlbumId(), byteArrayOutputStream.toByteArray());
                artFile = DataStorage.readArt(track.getAlbumId());

                byteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return artFile;
    }

    public static File fromPath(String artPath) {
        File artFile = null;
        if (artPath != null) {
            artFile = new File(artPath);
        }

        return artFile;
    }
}
