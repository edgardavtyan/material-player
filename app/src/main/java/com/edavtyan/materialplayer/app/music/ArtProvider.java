package com.edavtyan.materialplayer.app.music;

import android.content.Context;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class ArtProvider {
    private static HashMap<Integer, File> arts;

    static {
        arts = new HashMap<>();
    }


    private Context context;

    public ArtProvider(Context context) {
        this.context = context;
    }


    public File getArt(Metadata metadata) {
        int albumId = metadata.getAlbumId();

        if (!arts.containsKey(albumId)) {
            File newArtFile = saveArtToFile(metadata);
            arts.put(albumId, newArtFile);
        }

        return arts.get(albumId);
    }


    private File saveArtToFile(Metadata metadata) {
        String artStr = "album_" + metadata.getAlbumId();
        File artFile = new File(context.getFilesDir() + "/" + artStr);

        if (!artFile.exists() || artFile.lastModified() > metadata.getDateModified()) {
            try {
                AudioFile audiofile = AudioFileIO.read(new File(metadata.getPath()));
                byte[] artBytes = audiofile.getTag().getFirstArtwork().getBinaryData();

                FileOutputStream fos = context.openFileOutput(artStr, Context.MODE_PRIVATE);
                fos.write(artBytes);
                fos.close();

                artFile = context.getFileStreamPath(artStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return artFile;
    }
}
