package com.edavtyan.materialplayer.components.album_mvp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.MusicPlayerService;

import java.util.ArrayList;
import java.util.List;

public class AlbumListModel implements AlbumListMvp.Model, ServiceConnection {
	private final Context context;
	private final AlbumDB albumDB;
	private final List<Album> albums;
	private final TrackDB trackDB;
	private MusicPlayerService service;

	public AlbumListModel(Context context, AlbumDB albumDB, TrackDB trackDB) {
		this.context = context;
		this.albumDB = albumDB;
		this.trackDB = trackDB;
		this.albums = new ArrayList<>();
	}

	@Override
	public Album getAlbumAtIndex(int index) {
		if (albums.size() == 0) return null;
		return albums.get(index);
	}

	@Override
	public int getAlbumsCount() {
		return albums.size();
	}

	@Override
	public void addToPlaylist(int albumId) throws IllegalStateException {
		if (service == null) {
			throw new IllegalStateException(
					"'bindService' should be called before calling 'addToPlaylist'");
		}

		service.getQueue().addAll(trackDB.getTracksWithAlbumId(albumId));
	}

	@Override
	public void update() {
		albums.clear();
		albums.addAll(albumDB.getAllAlbums());
	}

	public void bindService() {
		context.bindService(
				new Intent(context, MusicPlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbindService() {
		context.unbindService(this);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		service = null;
	}
}
