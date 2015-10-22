package com.edavtyan.materialplayer.app.music;

import android.util.Log;

import com.edavtyan.materialplayer.app.utils.DataStorage;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;

public class ArtProvider {
    public File getArt(Metadata metadata) {
        File artFile = DataStorage.readArt(metadata.getAlbumId());
        if (!artFile.exists() || artFile.lastModified() < metadata.getDateModified()) {
            try {
                AudioFile audiofile = AudioFileIO.read(new File(metadata.getPath()));
                byte[] artBytes = audiofile.getTag().getFirstArtwork().getBinaryData();
                DataStorage.saveArt(metadata.getAlbumId(), artBytes);
                artFile = DataStorage.readArt(metadata.getAlbumId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return artFile;
    }
}
