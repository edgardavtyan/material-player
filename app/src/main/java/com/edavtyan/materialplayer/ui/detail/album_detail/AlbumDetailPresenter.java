package com.edavtyan.materialplayer.ui.detail.album_detail;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListPresenter;

public class AlbumDetailPresenter
		extends TrackListPresenter
		implements ParallaxHeaderListPresenter, Player.OnNewTrackListener {

	private final AlbumDetailActivity activity;
	private final AlbumDetailModel model;

	public AlbumDetailPresenter(AlbumDetailModel model, AlbumDetailActivity activity, ThemeColors theme) {
		super(activity, model, theme);
		this.activity = activity;
		this.model = model;
		this.model.addOnNewTrackListener(this);
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
		model.playQueue(position);
	}

	@Override
	public void onNewTrack() {
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		model.removeOnNewTrackListener(this);
	}
}
