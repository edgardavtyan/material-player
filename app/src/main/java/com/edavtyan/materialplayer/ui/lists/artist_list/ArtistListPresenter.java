package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;

public class ArtistListPresenter implements ListPresenter<ArtistListViewHolder> {

	private final ArtistListModel model;
	private final ArtistListView view;

	public ArtistListPresenter(ArtistListModel model, ArtistListView view) {
		super();
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());
		model.getArtistImageLink(position, holder::setImage);
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

	public void onHolderClick(int position, SourceSharedViews sharedViews) {
		view.disableTouchEvents();
		view.gotoArtistDetail(model.getArtistAtIndex(position).getTitle(), sharedViews);
	}

	public void onAddToQueue(int position) {
		int artistId = model.getArtistAtIndex(position).getId();
		model.addToPlaylist(artistId);
	}

	public void onEnterTransitionFinished() {
		view.enableTouchEvents();
	}

	public void onAddToPlaylist(int position) {
		view.showPlaylistSelectionDialog(model.getArtistTracks(position));
	}
}
