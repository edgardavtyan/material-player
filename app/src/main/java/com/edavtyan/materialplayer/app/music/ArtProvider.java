package com.edavtyan.materialplayer.app.music;

import android.content.Context;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;
import java.io.FileOutputStream;

public class ArtProvider {
    private static final String ART_PREFIX = "artwork_";


    private Context context;


    public ArtProvider(Context context) {
        this.context = context;
    }


    public File getArt(Metadata metadata) {

        String artStr = getArtStr(metadata.getAlbumId());
        File artFile = new File(context.getFilesDir() + "/" + artStr);

        if (!artFile.exists() || artFile.lastModified() > metadata.getDateModified()) {
            try {
                AudioFile audiofile = AudioFileIO.read(new File(metadata.getPath()));
                byte[] artBytes = audiofile.getTag().getFirstArtwork().getBinaryData();

                FileOutputStream fos = context.openFileOutput(
                        getArtStr(metadata.getAlbumId()), Context.MODE_PRIVATE);
                fos.write(artBytes);
                fos.close();

                artFile = context.getFileStreamPath(artStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return artFile;
    }

    private String getArtStr(int albumId) {
        return ART_PREFIX + Integer.toString(albumId);
    }
}
