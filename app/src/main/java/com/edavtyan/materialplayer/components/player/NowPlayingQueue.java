package com.edavtyan.materialplayer.components.player;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.tracks.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class NowPlayingQueue extends ArrayList<Track> {
	private final SharedPreferences prefs;
	private @Getter RepeatMode repeatMode;
	private @Getter @Setter int currentTrackIndex;
	private @Getter @Setter boolean isShuffling;

	/* Constructors */

	public NowPlayingQueue(Context context) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		repeatMode = RepeatMode.fromPref(prefs, "repeat_mode");
		isShuffling = prefs.getBoolean("is_shuffling", false);
	}

	/* Public methods */

	public Track getCurrentTrack() {
		return get(currentTrackIndex);
	}

	public void setTracks(List<Track> tracks, int index) {
		clear();
		addAll(tracks);
		currentTrackIndex = index;
		if (isShuffling) shuffle();
	}

	public boolean isAtLastTrack() {
		return currentTrackIndex >= size() - 1;
	}

	public boolean hasData() {
		return size() > 0;
	}

	public void moveToNext() {
		if (currentTrackIndex >= size() - 1) {
			currentTrackIndex = 0;
		} else {
			currentTrackIndex++;
		}
	}

	public void moveToPrev() {
		if (currentTrackIndex != 0) {
			currentTrackIndex--;
		} else {
			currentTrackIndex = size() - 1;
		}
	}

	public void shuffle() {
		Track currentTrack = get(currentTrackIndex);
		Collections.shuffle(this);
		Collections.swap(this, 0, indexOf(currentTrack));
		currentTrackIndex = 0;
		isShuffling = true;
	}

	public void sort() {
		Track currentTrack = get(currentTrackIndex);
		Collections.sort(this, (t1, t2) -> t1.getQueueIndex() - t2.getQueueIndex());
		currentTrackIndex = indexOf(currentTrack);
		isShuffling = false;
	}

	public void toggleShuffling() {
		if (isShuffling) {
			sort();
		} else {
			shuffle();
		}

		prefs.edit().putBoolean("is_shuffling", isShuffling).apply();
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
}
