package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListView;

public interface ArtistDetailView extends AlbumListView {
	String EXTRA_ARTIST_TITLE = "extra_artistTitle";

	void setArtistTitle(String title);
	void setArtistInfo(int albumsCount, int tracksCount);
	void setArtistImage(Bitmap image);
}
