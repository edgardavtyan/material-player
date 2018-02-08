package com.edavtyan.materialplayer.screens.detail.album_detail;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListPresenter;

public class AlbumDetailPresenter
		extends TrackListPresenter
		implements ParallaxHeaderListPresenter, Player.OnNewTrackListener {

	private final AlbumDetailView view;
	private final AlbumDetailModel model;

	private boolean wasHolderClicked;

	public AlbumDetailPresenter(AlbumDetailModel model, AlbumDetailView view) {
		super(view, model);
		this.view = view;
		this.model = model;
		this.model.addOnNewTrackListener(this);
		wasHolderClicked = false;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Album album = model.getAlbum();
		view.setAlbumTitle(album.getTitle());
		view.setAlbumInfo(
				album.getArtistTitle(),
				album.getTracksCount(),
				model.getTotalAlbumDuration());
		view.setAlbumImage(model.getAlbumArt());
	}

	@Override
	public void onHolderClick(int position) {
		model.playQueue(position);
		wasHolderClicked = true;
	}

	@Override
	public void onNewTrack() {
		if (wasHolderClicked) {
			wasHolderClicked = false;
			view.gotoNowPlaying();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		model.removeOnNewTrackListener(this);
	}
}
