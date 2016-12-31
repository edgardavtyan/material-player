package com.edavtyan.materialplayer.components.track_all;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import java.util.List;

import lombok.Setter;

public class TrackListModel implements TrackListMvp.Model, ServiceConnection,
									   SharedPreferences.OnSharedPreferenceChangeListener {

	private static final String PREF_COMPACT_LISTS_KEY = "pref_compactList";
	private static final boolean PREF_COMPACT_LISTS_DEFAULT = false;

	private final Context context;
	private final TrackDB db;
	private final AdvancedSharedPrefs prefs;
	private List<Track> tracks;
	private PlayerService service;

	private @Setter OnCompactModeChangedListener onCompactModeChangedListener;

	public TrackListModel(Context context, TrackDB db, AdvancedSharedPrefs prefs) {
		this.context = context;
		this.db = db;
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public boolean isCompactModeEnabled() {
		return prefs.getBoolean(PREF_COMPACT_LISTS_KEY, PREF_COMPACT_LISTS_DEFAULT);
	}

	@Override
	public Track getTrackAtIndex(int position) {
		if (tracks == null) return null;
		return tracks.get(position);
	}

	@Override
	public int getItemCount() {
		if (tracks == null) return 0;
		return tracks.size();
	}

	@Override
	public void playQueue(int position) {
		service.getPlayer().playNewTracks(tracks, position);
	}

	@Override
	public void addToQueue(int position) {
		service.getPlayer().addTrack(tracks.get(position));
	}

	@Override
	public void bindService() {
		context.bindService(
				new Intent(context, PlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbindService() {
		context.unbindService(this);
	}

	@Override
	public void update() {
		tracks = queryTracks();
	}

	@Override
	public void close() {
		tracks = null;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}

	protected List<Track> queryTracks() {
		return db.getAllTracks();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(PREF_COMPACT_LISTS_KEY) && onCompactModeChangedListener != null) {
			onCompactModeChangedListener.onCompactModeChanged(isCompactModeEnabled());
		}
	}
}
