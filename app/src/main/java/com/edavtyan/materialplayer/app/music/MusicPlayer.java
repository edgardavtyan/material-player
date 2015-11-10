package com.edavtyan.materialplayer.app.music;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.app.music.data.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicPlayer implements MediaPlayer.OnCompletionListener,
                                    MediaPlayer.OnPreparedListener {
    private final MediaPlayer player;
    private final List<Track> tracks;
    private int currentTrackIndex;
    private boolean isShuffling;
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
        isShuffling = prefs.getBoolean("is_shuffling", false);
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> newTracks, int index) {
        tracks.clear();
        tracks.addAll(newTracks);
        currentTrackIndex = index;
        if (isShuffling) shuffleQueue();
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

    public boolean isShuffling() {
        return isShuffling;
    }

    public void toggleShuffling() {
        isShuffling = !isShuffling;
        prefs.edit().putBoolean("is_shuffling", isShuffling).apply();
        if (isShuffling) {
            shuffleQueue();
        } else {
            Track currentTrack = tracks.get(currentTrackIndex);
            Collections.sort(tracks, (t1, t2) -> t1.getQueueIndex() - t2.getQueueIndex());
            currentTrackIndex = tracks.indexOf(currentTrack);
        }
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
                break;

            case REPEAT:
                moveNext();
                prepare();
                break;

            case REPEAT_ONE:
                player.seekTo(0);
                prepare();
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        player.start();
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(mediaPlayer);
        }
    }

    /*
     * Private methods
     */

    private void shuffleQueue() {
        Track currentTrack = tracks.get(currentTrackIndex);
        Collections.shuffle(tracks);
        Collections.swap(tracks, 0, tracks.indexOf(currentTrack));
        currentTrackIndex = 0;
    }
}
