package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListPresenter;

public class ArtistDetailPresenter
		extends AlbumListPresenter
		implements ArtistDetailMvp.Presenter {

	private final ArtistDetailMvp.Model model;
	private final ArtistDetailMvp.View view;

	public ArtistDetailPresenter(ArtistDetailMvp.Model model, ArtistDetailMvp.View view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Artist artist = model.getArtist();
		view.setArtistTitle(artist.getTitle());
		view.setArtistInfo(artist.getAlbumsCount(), artist.getTracksCount());
		view.setArtistImage(null, R.drawable.fallback_artist);
	}
}
