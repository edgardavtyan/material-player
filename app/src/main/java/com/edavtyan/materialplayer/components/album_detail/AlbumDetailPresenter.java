package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.components.track_all.TrackListPresenter;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.utils.ArtProvider;

public class AlbumDetailPresenter extends TrackListPresenter implements AlbumDetailMvp.Presenter {
	private final AlbumDetailMvp.View view;
	private final AlbumDetailMvp.Model model;

	public AlbumDetailPresenter(AlbumDetailMvp.View view, AlbumDetailMvp.Model model) {
		super(view, model);
		this.view = view;
		this.model = model;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Album album = model.getAlbum();
		Track track = model.getFirstAlbumTrack();
		view.setAlbumTitle(album.getTitle());
		view.setAlbumInfo(album.getArtistTitle(), album.getTracksCount());
		view.setAlbumImage(ArtProvider.fromTrack(track), R.drawable.fallback_cover);
	}
}
