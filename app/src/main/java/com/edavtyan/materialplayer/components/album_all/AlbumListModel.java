package com.edavtyan.materialplayer.components.album_all;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import java.util.List;

import lombok.Setter;

public class AlbumListModel implements AlbumListMvp.Model, ServiceConnection,
									   SharedPreferences.OnSharedPreferenceChangeListener {
	private static final String PREF_COMPACT_LISTS_KEY = "pref_compactList";
	private static final boolean PREF_COMPACT_LISTS_DEFAULT = false;

	private final Context context;
	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private final AdvancedSharedPrefs prefs;
	private List<Album> albums;
	private PlayerService service;

	private @Setter OnCompactModeChangedListener onCompactModeChangedListener;

	public AlbumListModel(Context context, AlbumDB albumDB, TrackDB trackDB, AdvancedSharedPrefs prefs) {
		this.context = context;
		this.albumDB = albumDB;
		this.trackDB = trackDB;
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public boolean isCompactModeEnabled() {
		return prefs.getBoolean(PREF_COMPACT_LISTS_KEY, PREF_COMPACT_LISTS_DEFAULT);
	}

	@Override
	public Album getAlbumAtIndex(int index) {
		if (albums == null) return null;
		return albums.get(index);
	}

	@Override
	public int getAlbumsCount() {
		if (albums == null) return 0;
		return albums.size();
	}

	@Override
	public void addToPlaylist(int albumId) throws IllegalStateException {
		if (service == null) {
			throw new IllegalStateException(
					"'bindService' should be called before calling 'onAddToPlaylist'");
		}

		service.getPlayer().addManyTracks(trackDB.getTracksWithAlbumId(albumId));
	}

	@Override
	public void update() {
		albums = queryAlbums();
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
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		service = null;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(PREF_COMPACT_LISTS_KEY) && onCompactModeChangedListener != null) {
			onCompactModeChangedListener.onCompactModeChanged(isCompactModeEnabled());
		}
	}

	protected List<Album> queryAlbums() {
		return albumDB.getAllAlbums();
	}
}
