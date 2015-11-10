package com.edavtyan.materialplayer.app.music.providers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.edavtyan.materialplayer.app.music.data.Track;
import com.edavtyan.materialplayer.app.utils.DataStorage;
import com.edavtyan.materialplayer.app.utils.FileUtils;
import com.esotericsoftware.wildcard.Paths;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public final class ArtProvider {
    private ArtProvider() {}


    private static final String TAG = "ArtProvider";


    public static File fromTrack(Track track) {
        String artFolder = DataStorage.DIR_ART.getAbsolutePath();
        String artGlob = track.getAlbumId() + "@*";
        List<File> artFiles = new Paths().glob(artFolder, artGlob).getFiles();

        boolean foundArt = artFiles.size() != 0;
        boolean artOutdated = foundArt && artFiles.get(0).lastModified() < track.getDateModified();

        int artGeneration = 1;
        if (artOutdated) {
            String artFileName = artFiles.get(0).getName();
            FileUtils.delete(artFileName);
            artGeneration = getArtGeneration(artFileName);
            artGeneration++;
        }

        File artFile = new File(
                DataStorage.DIR_ART,
                String.format("%d@%d", track.getAlbumId(), artGeneration));

        if (artOutdated || !foundArt) {
            Log.d(TAG, "Art for " + track.getAlbumTitle() + " does not exist or outdated");
            try {
                AudioFile audioFile = AudioFileIO.read(new File(track.getPath()));
                byte[] artBytes = audioFile.getTag().getFirstArtwork().getBinaryData();

                FileOutputStream outputStream = new FileOutputStream(artFile);
                Bitmap artBitmap = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
                artBitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
                outputStream.close();
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

    private static int getArtGeneration(String artFileName) {
        return Integer.parseInt(artFileName.substring(artFileName.indexOf("@") + 1));
    }
}
