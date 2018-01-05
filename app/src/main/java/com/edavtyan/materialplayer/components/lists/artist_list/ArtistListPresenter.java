package com.edavtyan.materialplayer.components.lists.artist_list;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;

public class ArtistListPresenter extends ListPresenter<ArtistListViewHolder> {

	private final ArtistListModel model;
	private final ArtistListView view;

	public ArtistListPresenter(ArtistListModel model, ArtistListView view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
		model.getArtistImage(position, holder::setImage);
	}

	@Override
	public int getItemCount() {
		return model.getArtistCount();
	}

	@Override
	public void onCreate() {
		model.bindService();
		model.update();
	}

	@Override
	public void onDestroy() {
		model.unbindService();
	}

	public void onHolderClick(int position) {
		Artist artist = model.getArtistAtIndex(position);
		view.gotoArtistDetail(artist.getTitle());
	}

	public void onAddToPlaylist(int position) {
		int artistId = model.getArtistAtIndex(position).getId();
		model.addToPlaylist(artistId);
	}
}
