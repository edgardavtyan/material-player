package com.edavtyan.materialplayer.components.lists.album_list;

import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.utils.Logger;

public class AlbumListPresenter extends ListPresenter<AlbumListViewHolder> {

	private final AlbumListModel model;
	private final AlbumListView view;

	public AlbumListPresenter(AlbumListModel model, AlbumListView view) {
		super(model, view);
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

	public void onHolderClick(int position) {
		int albumId = model.getAlbumAtIndex(position).getId();
		view.gotoAlbumDetail(albumId);
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
