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

import lombok.Getter;
import lombok.Setter;

public class MusicPlayer implements MediaPlayer.OnCompletionListener,
                                    MediaPlayer.OnPreparedListener {
    private final MediaPlayer player;
    private final @Getter List<Track> queue;
    private @Getter @Setter int currentTrackIndex;
    private @Getter boolean isShuffling;
    private @Setter OnPreparedListener onPreparedListener;
    private @Setter OnPlaybackStateChangedListener onPlaybackStateChangedListener;
    private @Getter RepeatMode repeatMode;
    private SharedPreferences prefs;


    public interface OnPreparedListener {
        void onPrepared();
    }

    public interface OnPlaybackStateChangedListener {
        void onPlaybackStateChanged(PlaybackState state);
    }

    public enum PlaybackState {
        PAUSED, RESUMED
    }


    public MusicPlayer(Context context) {
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnPreparedListener(this);
        queue = new ArrayList<>();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        repeatMode = RepeatMode.fromPref(prefs, "repeat_mode");
        isShuffling = prefs.getBoolean("is_shuffling", false);
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

    public void resume() {
        player.start();
        raiseOnPlaybackStateChanged(PlaybackState.RESUMED);
    }

    public void pause() {
        player.pause();
        raiseOnPlaybackStateChanged(PlaybackState.PAUSED);
    }

    public void moveNext() {
        if (currentTrackIndex >= queue.size() - 1) {
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
                currentTrackIndex = queue.size() - 1;
            }
        }
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

    public void toggleShuffling() {
        isShuffling = !isShuffling;
        prefs.edit().putBoolean("is_shuffling", isShuffling).apply();
        if (isShuffling) {
            shuffleQueue();
        } else {
            Track currentTrack = queue.get(currentTrackIndex);
            Collections.sort(queue, (t1, t2) -> t1.getQueueIndex() - t2.getQueueIndex());
            currentTrackIndex = queue.indexOf(currentTrack);
        }
    }

    public Track getCurrentTrack() {
        return queue.get(currentTrackIndex);
    }

    public void setTracks(List<Track> newTracks, int index) {
        queue.clear();
        queue.addAll(newTracks);
        currentTrackIndex = index;
        if (isShuffling) shuffleQueue();
    }

    public boolean hasData() {
        return queue.size() > 0;
    }

    public int getDuration() {
        return player.getDuration();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        switch (repeatMode) {
            case NO_REPEAT:
                if (currentTrackIndex >= queue.size() - 1) {
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
            onPreparedListener.onPrepared();
        }
    }

    /*
     * Private methods
     */

    private void shuffleQueue() {
        Track currentTrack = queue.get(currentTrackIndex);
        Collections.shuffle(queue);
        Collections.swap(queue, 0, queue.indexOf(currentTrack));
        currentTrackIndex = 0;
    }

    private void raiseOnPlaybackStateChanged(PlaybackState state) {
        if (onPlaybackStateChangedListener != null) {
            onPlaybackStateChangedListener.onPlaybackStateChanged(state);
        }
    }
}
