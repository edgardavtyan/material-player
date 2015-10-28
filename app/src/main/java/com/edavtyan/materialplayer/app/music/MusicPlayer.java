package com.edavtyan.materialplayer.app.music;

import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer implements MediaPlayer.OnCompletionListener,
                                    MediaPlayer.OnPreparedListener {
    private final MediaPlayer player;
    private final List<Metadata> tracks;
    private int currentTrackIndex;
    private MediaPlayer.OnPreparedListener onPreparedListener;


    public MusicPlayer() {
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnPreparedListener(this);
        tracks = new ArrayList<>();
    }


    public Metadata getCurrentTrack() {
        return tracks.get(currentTrackIndex);
    }

    public void setCurrentTrackIndex(int index) {
        currentTrackIndex = index;
    }

    public List<Metadata> getTracks() {
        return tracks;
    }

    public void setTracks(List<Metadata> newTracks) {
        tracks.clear();
        tracks.addAll(newTracks);
    }

    public void moveNext() {
        if (currentTrackIndex >= tracks.size() - 1) {
            currentTrackIndex = 0;
        } else {
            currentTrackIndex++;
        }
    }

    public void movePrev() {
        if (player.getCurrentPosition() > 5000) {
            player.seekTo(0);
        } else {
            if (currentTrackIndex != 0) {
                currentTrackIndex--;
            } else {
                currentTrackIndex = tracks.size() - 1;
            }
        }
    }


    public void prepare() {
        try {
            player.reset();
            player.setDataSource(getCurrentTrack().getPath());
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSeek() {
        return player.getCurrentPosition();
    }

    public void seekTo(int position) {
        player.seekTo(position);
    }

    public int getDuration() {
        return player.getDuration();
    }

    public void resume() {
        player.start();
    }

    public void pause() {
        player.pause();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }


    public void setOnPreparedListener(MediaPlayer.OnPreparedListener listener) {
        onPreparedListener = listener;
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        moveNext();
        prepare();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        player.start();
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(mediaPlayer);
        }
    }
}
