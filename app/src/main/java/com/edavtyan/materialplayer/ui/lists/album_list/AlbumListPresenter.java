package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.db.Album;
import com.ed.libsutils.utils.Logger;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

public class AlbumListPresenter implements ListPresenter<AlbumListViewHolder> {

	private final AlbumListModel model;
	private final AlbumListView view;

	public AlbumListPresenter(AlbumListModel model, AlbumListView view) {
		super();
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(AlbumListViewHolder holder, int position) {
		Album album = model.getAlbumAtIndex(position);
		holder.setTitle(album.getTitle());
		holder.setInfo(album.getTracksCount(), album.getArtistTitle());
		holder.setArt(album.getArt());
	}

	@Override
	public int getItemCount() {
		Logger.d(this, "Albums count: %d", model.getAlbumsCount());
		return model.getAlbumsCount();
	}

	public void onHolderClick(int position, SourceSharedViews sharedViews) {
		int albumId = model.getAlbumAtIndex(position).getId();
		view.gotoAlbumDetail(albumId, sharedViews);
	}

	public void onAddToPlaylist(int position) {
		int albumId = model.getAlbumAtIndex(position).getId();
		model.addToPlaylist(albumId);
	}

	@Override
	public void onCreate() {
		model.update();
		model.bindService();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
	}
}
