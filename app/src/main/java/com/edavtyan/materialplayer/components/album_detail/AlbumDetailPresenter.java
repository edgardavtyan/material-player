package com.edavtyan.materialplayer.components.album_detail;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.track_all.TrackListPresenter;
import com.edavtyan.materialplayer.db.Album;

public class AlbumDetailPresenter extends TrackListPresenter implements AlbumDetailMvp.Presenter {
	private final AlbumDetailMvp.View view;
	private final AlbumDetailMvp.Model model;

	public AlbumDetailPresenter(AlbumDetailMvp.Model model, AlbumDetailMvp.View view) {
		super(view, model);
		this.view = view;
		this.model = model;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Album album = model.getAlbum();
		view.setAlbumTitle(album.getTitle());
		view.setAlbumInfo(album.getArtistTitle(), album.getTracksCount());
		view.setAlbumImage(model.getAlbumArt(), R.drawable.fallback_cover);
	}
}
