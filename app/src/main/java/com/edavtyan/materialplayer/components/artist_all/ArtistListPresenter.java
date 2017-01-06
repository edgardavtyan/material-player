package com.edavtyan.materialplayer.components.artist_all;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.mvp.list.ListPresenter;

public class ArtistListPresenter
		extends ListPresenter<ArtistListViewHolder>
		implements ArtistListMvp.Presenter {

	private final ArtistListMvp.Model model;
	private final ArtistListMvp.View view;

	public ArtistListPresenter(ArtistListMvp.Model model, ArtistListMvp.View view) {
		super(model, view);
		this.model = model;
		this.view = view;
	}

	@Override
	public void onBindViewHolder(ArtistListViewHolder holder, int position) {
		Artist artist = model.getArtistAtIndex(position);
		holder.setTitle(artist.getTitle());
		holder.setInfo(artist.getAlbumsCount(), artist.getTracksCount());

		Bitmap imageFromCache = model.getArtistImageFromCache(position);
		if (imageFromCache != null) {
			holder.setImage(imageFromCache);
		} else {
			model.getArtistImage(position, holder::setImage);
		}
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
}
