package com.edavtyan.materialplayer.components.album_all;

import com.edavtyan.materialplayer.db.Album;

public class AlbumListPresenter
		implements AlbumListMvp.Presenter,
				   AlbumListMvp.Model.OnCompactModeChangedListener {

	private final AlbumListMvp.Model model;
	private final AlbumListMvp.View view;

	public AlbumListPresenter(AlbumListMvp.Model model, AlbumListMvp.View view) {
		this.model = model;
		this.model.setOnCompactModeChangedListener(this);
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
		return model.getAlbumsCount();
	}

	@Override
	public void onHolderClick(int position) {
		int albumId = model.getAlbumAtIndex(position).getId();
		view.goToAlbumDetail(albumId);
	}

	@Override
	public void onAddToPlaylist(int position) {
		int albumId = model.getAlbumAtIndex(position).getId();
		model.addToPlaylist(albumId);
	}

	@Override
	public boolean isCompactModeEnabled() {
		return model.isCompactModeEnabled();
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

	@Override public void onCompactModeChanged() {
		view.notifyDataSetChanged();
	}
}
