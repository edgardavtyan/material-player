package com.edavtyan.materialplayer.player;

import com.edavtyan.materialplayer.db.Track;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PlayerQueue {
	private final List<Track> tracks;

	private final HashMap<ShuffleMode, ShuffleMode> shuffleModeToggleMap;
	private final HashMap<RepeatMode, RepeatMode> repeatModeToggleMap;

	private @Getter @Setter ShuffleMode shuffleMode;
	private @Getter @Setter RepeatMode repeatMode;
	private @Getter @Setter int position;
	private @Getter boolean isEnded;

	public PlayerQueue(List<Track> tracks) {
		this.tracks = tracks;

		shuffleModeToggleMap = new HashMap<>();
		shuffleModeToggleMap.put(ShuffleMode.DISABLED, ShuffleMode.ENABLED);
		shuffleModeToggleMap.put(ShuffleMode.ENABLED, ShuffleMode.DISABLED);

		repeatModeToggleMap = new HashMap<>();
		repeatModeToggleMap.put(RepeatMode.DISABLED, RepeatMode.REPEAT_ALL);
		repeatModeToggleMap.put(RepeatMode.REPEAT_ALL, RepeatMode.REPEAT_ONE);
		repeatModeToggleMap.put(RepeatMode.REPEAT_ONE, RepeatMode.DISABLED);
	}

	public void addTrack(Track track) {
		tracks.add(track);
	}

	public void addManyTracks(List<Track> newTracks) {
		if (shuffleMode == ShuffleMode.ENABLED) {
			shuffleTracks(newTracks);
		}

		tracks.addAll(newTracks);
	}

	public void replaceTracks(List<Track> newTracks, int position) {
		tracks.clear();
		tracks.addAll(newTracks);
		setPosition(position);

		if (shuffleMode == ShuffleMode.ENABLED) {
			shuffleTracks(tracks);
		}
	}

	public void removeTrackAt(int position) {
		tracks.remove(position);
	}

	public Track getTrackAt(int position) {
		return tracks.get(position);
	}

	public Track getCurrentTrack() {
		return tracks.get(position);
	}

	public int getTracksCount() {
		return tracks.size();
	}

	public void moveToNext() {
		if (getRepeatMode() == RepeatMode.REPEAT_ONE) {
			isEnded = false;
			return;
		}

		boolean atLastTrack = position == (tracks.size() - 1);

		if (atLastTrack) {
			if (getRepeatMode() == RepeatMode.DISABLED) {
				isEnded = true;
			} else if (getRepeatMode() == RepeatMode.REPEAT_ALL) {
				isEnded = false;
				position = 0;
			}
		} else {
			isEnded = false;
			position++;
		}
	}

	public void moveToPrev() {
		if (getRepeatMode() == RepeatMode.REPEAT_ONE) return;

		boolean atFirstTrack = position == 0;
		if (atFirstTrack) {
			position = tracks.size() - 1;
		} else {
			position--;
		}
	}

	public void toggleShuffleMode() {
		shuffleMode = shuffleModeToggleMap.get(shuffleMode);
		switch (shuffleMode) {
		case ENABLED:
			shuffleTracks(tracks);
			break;
		case DISABLED:
			sortTracks(tracks);
			break;
		}
	}

	public void toggleRepeatMode() {
		repeatMode = repeatModeToggleMap.get(repeatMode);
	}

	public boolean hasData() {
		return tracks.size() > 0;
	}

	private void shuffleTracks(List<Track> tracks) {
		if (tracks.size() == 0) return;
		Track currentTrackBeforeShuffle = tracks.get(position);
		Collections.shuffle(tracks);
		int currentTrackAfterShufflePosition = tracks.indexOf(currentTrackBeforeShuffle);
		Track firstTrackAfterShuffle = tracks.get(0);
		tracks.set(0, tracks.get(currentTrackAfterShufflePosition));
		tracks.set(currentTrackAfterShufflePosition, firstTrackAfterShuffle);
		position = 0;
	}

	private void sortTracks(List<Track> tracks) {
		if (tracks.size() == 0) return;
		Track currentTrackBeforeSort = tracks.get(position);
		Collections.sort(tracks, (o1, o2) -> o1.getTrack() - o2.getTrack());
		position = tracks.indexOf(currentTrackBeforeSort);
	}
}
