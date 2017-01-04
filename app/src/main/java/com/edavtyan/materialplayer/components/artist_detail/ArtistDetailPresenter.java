package com.edavtyan.materialplayer.components.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_all.AlbumListPresenter;
import com.edavtyan.materialplayer.db.Artist;

public class ArtistDetailPresenter
		extends AlbumListPresenter
		implements ArtistDetailMvp.Presenter,
				   ArtistDetailMvp.Model.OnArtLoadedListener {

	private final ArtistDetailMvp.Model model;
	private final ArtistDetailMvp.View view;

	public ArtistDetailPresenter(ArtistDetailMvp.Model model, ArtistDetailMvp.View view) {
		super(model, view);
		this.model = model;
		this.model.setOnArtLoadedListener(this);
		this.view = view;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		model.loadArtistImage();

		Artist artist = model.getArtist();
		view.setArtistTitle(artist.getTitle());
		view.setArtistInfo(artist.getAlbumsCount(), artist.getTracksCount());
	}

	@Override
	public void onArtistImageLoaded(Bitmap art) {
		view.setArtistImage(art, R.drawable.fallback_artist);
	}
}
