package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;

public class ArtistListPresenter
		implements ArtistListMvp.Presenter,
				   ArtistListMvp.Model.OnCompactListsPrefChangedListener {

	private final ArtistListMvp.Model model;
	private final ArtistListMvp.View view;

	public ArtistListPresenter(ArtistListMvp.Model model, ArtistListMvp.View view) {
		this.model = model;
		this.model.setOnCompactListsPrefChangedListener(this);
		this.view = view;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
	}

	@Override
	public int getItemCount() {
		return model.getArtistCount();
	}

	@Override
	public void onCreate() {
		model.update();
	}

	@Override
	public void onDestroy() {
	}

	@Override
	public void onHolderClick(int position) {
		Artist artist = model.getArtistAtIndex(position);
		view.goToArtistDetail(artist.getTitle());
	}

	@Override
	public boolean isCompactModeEnabled() {
		return model.isCompactListsEnabled();
	}

	@Override
	public void onCompactListsPrefChanged(boolean isCompactListsEnabled) {
		view.notifyDataSetChanged();
	}
}
