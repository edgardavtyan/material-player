package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.components.artists.Artist;

public class ArtistListPresenter implements ArtistListMvp.Presenter {
	private final ArtistListMvp.Model model;
	private final ArtistListMvp.View view;

	public ArtistListPresenter(ArtistListMvp.Model model, ArtistListMvp.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void bindViewHolder(ArtistsListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
	}

	@Override
	public int getItemCount() {
		return model.getArtistCount();
	}

	@Override
	public void onHolderClicked(ArtistsListViewHolder holder, int position) {
		view.goToArtistDetail(holder.getTitle());
	}
}
