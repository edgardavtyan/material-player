package com.edavtyan.materialplayer.components.album_mvp;

public class AlbumListPresenter implements AlbumListMvp.Presenter {
	private final AlbumListMvp.Model model;
	private final AlbumListMvp.View view;

	public AlbumListPresenter(AlbumListMvp.Model model, AlbumListMvp.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void bindViewHolder(AlbumListViewHolder holder, int position) {
		Album album = model.getAlbumAtIndex(position);
		holder.setAlbumId(album.getId());
		holder.setTitle(album.getTitle());
		holder.setInfo(album.getTracksCount(), album.getArtistTitle());
		holder.setArt(album.getArt());
	}

	@Override
	public int getItemCount() {
		return model.getAlbumsCount();
	}

	@Override
	public void onItemClicked(int albumId) {
		view.goToAlbumDetail(albumId);
	}

	@Override
	public void addToPlaylist(int albumId) {
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
