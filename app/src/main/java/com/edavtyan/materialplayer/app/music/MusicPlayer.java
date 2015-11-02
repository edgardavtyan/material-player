package com.edavtyan.materialplayer.app.music;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer implements MediaPlayer.OnCompletionListener,
                                    MediaPlayer.OnPreparedListener {
    private final MediaPlayer player;
    private final List<Track> tracks;
    private int currentTrackIndex;
    private MediaPlayer.OnPreparedListener onPreparedListener;
    private RepeatMode repeatMode;
    private SharedPreferences prefs;

    public MusicPlayer(Context context) {
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnPreparedListener(this);
        tracks = new ArrayList<>();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        repeatMode = RepeatMode.fromPref(prefs, "repeat_mode");
    }


    public Track getCurrentTrack() {
        return tracks.get(currentTrackIndex);
    }

    public int getCurrentTrackIndex() {
        return currentTrackIndex;
    }

    public void setCurrentTrackIndex(int index) {
        currentTrackIndex = index;
    }

    public RepeatMode getRepeatMode() {
        return repeatMode;
    }

    public void toggleRepeatMode() {
        switch (repeatMode) {
            case NO_REPEAT:
                repeatMode = RepeatMode.REPEAT;
                break;
            case REPEAT:
                repeatMode = RepeatMode.REPEAT_ONE;
                break;
            case REPEAT_ONE:
                repeatMode = RepeatMode.NO_REPEAT;
                break;
        }

        prefs.edit().putInt("repeat_mode", RepeatMode.toInt(repeatMode)).apply();
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> newTracks) {
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
        switch (repeatMode) {
            case NO_REPEAT:
                if (currentTrackIndex >= tracks.size() - 1) {
                    player.pause();
                }
                return;

            case REPEAT:
                moveNext();
                prepare();
                return;

            case REPEAT_ONE:
                player.seekTo(0);
                prepare();
                return;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        player.start();
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(mediaPlayer);
        }
    }
}
