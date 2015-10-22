package com.edavtyan.materialplayer.app.music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.edavtyan.materialplayer.app.utils.DataStorage;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ArtProvider {
    public File getArt(Metadata metadata) {
        File artFile = DataStorage.readArt(metadata.getAlbumId());
        if (!artFile.exists() || artFile.lastModified() < metadata.getDateModified()) {
            try {
                AudioFile audiofile = AudioFileIO.read(new File(metadata.getPath()));
                byte[] artBytes = audiofile.getTag().getFirstArtwork().getBinaryData();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                Bitmap art = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
                art.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);

                DataStorage.saveArt(metadata.getAlbumId(), byteArrayOutputStream.toByteArray());
                artFile = DataStorage.readArt(metadata.getAlbumId());

                byteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return artFile;
    }
}
