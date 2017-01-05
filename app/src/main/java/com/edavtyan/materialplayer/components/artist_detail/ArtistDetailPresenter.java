package com.edavtyan.materialplayer.components.artist_detail;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_all.AlbumListPresenter;
import com.edavtyan.materialplayer.db.Artist;

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

		//TODO: Find how to unit test this
		model.loadArtistImage(art -> view.setArtistImage(art, R.drawable.fallback_artist));
	}
}
