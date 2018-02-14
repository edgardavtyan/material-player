package com.edavtyan.materialplayer.ui.detail.album_detail;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListPresenter;

public class AlbumDetailPresenter
		extends TrackListPresenter
		implements ParallaxHeaderListPresenter, Player.OnNewTrackListener {

	private final AlbumDetailActivity activity;
	private final AlbumDetailModel model;

	private boolean wasHolderClicked;

	public AlbumDetailPresenter(AlbumDetailModel model, AlbumDetailActivity activity) {
		super(activity, model);
		this.activity = activity;
		this.model = model;
		this.model.addOnNewTrackListener(this);
		wasHolderClicked = false;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Album album = model.getAlbum();
		activity.setAlbumTitle(album.getTitle());
		activity.setAlbumInfo(
				album.getArtistTitle(),
				album.getTracksCount(),
				model.getTotalAlbumDuration());
		activity.setAlbumImage(model.getAlbumArt());
	}

	@Override
	public void onHolderClick(int position) {
		activity.disableTouchEvents();
		model.playQueue(position);
		wasHolderClicked = true;
	}

	@Override
	public void onNewTrack() {
		if (wasHolderClicked) {
			wasHolderClicked = false;
			activity.gotoNowPlaying();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		model.removeOnNewTrackListener(this);
	}

	public void onEnterTransitionEnd() {
		activity.enableTouchEvents();
	}
}
