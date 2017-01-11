package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.db.Track;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Queue implements PlayerMvp.Queue {
	private final List<Track> tracks;

	private final HashMap<ShuffleMode, ShuffleMode> shuffleModeToggleMap;
	private final HashMap<RepeatMode, RepeatMode> repeatModeToggleMap;

	private @Getter @Setter ShuffleMode shuffleMode;
	private @Getter @Setter RepeatMode repeatMode;
	private @Getter @Setter int position;
	private @Getter boolean isEnded;

	public Queue(List<Track> tracks) {
		this.tracks = tracks;

		shuffleModeToggleMap = new HashMap<>();
		shuffleModeToggleMap.put(ShuffleMode.DISABLED, ShuffleMode.ENABLED);
		shuffleModeToggleMap.put(ShuffleMode.ENABLED, ShuffleMode.DISABLED);

		repeatModeToggleMap = new HashMap<>();
		repeatModeToggleMap.put(RepeatMode.DISABLED, RepeatMode.REPEAT_ALL);
		repeatModeToggleMap.put(RepeatMode.REPEAT_ALL, RepeatMode.REPEAT_ONE);
		repeatModeToggleMap.put(RepeatMode.REPEAT_ONE, RepeatMode.DISABLED);
	}

	@Override
	public void addTrack(Track track) {
		tracks.add(track);
	}

	@Override
	public void addManyTracks(List<Track> newTracks) {
		tracks.addAll(newTracks);
	}

	@Override
	public void removeTrackAt(int position) {
		tracks.remove(position);
	}

	@Override
	public void clear() {
		tracks.clear();
	}

	@Override
	public Track getTrackAt(int position) {
		return tracks.get(position);
	}

	@Override
	public Track getCurrentTrack() {
		return tracks.get(position);
	}

	@Override
	public int getTracksCount() {
		return tracks.size();
	}

	@Override
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

	@Override
	public void moveToPrev() {
		if (getRepeatMode() == RepeatMode.REPEAT_ONE) return;

		boolean atFirstTrack = position == 0;
		if (atFirstTrack) {
			position = tracks.size() - 1;
		} else {
			position--;
		}
	}

	@Override
	public void toggleShuffleMode() {
		shuffleMode = shuffleModeToggleMap.get(shuffleMode);
	}

	@Override
	public void toggleRepeatMode() {
		repeatMode = repeatModeToggleMap.get(repeatMode);
	}

	@Override
	public boolean hasData() {
		return tracks.size() > 0;
	}
}
