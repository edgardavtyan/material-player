package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListView;

public interface ArtistDetailView extends AlbumListView {
	String EXTRA_ARTIST_TITLE = "extra_artistTitle";
	String SHARED_ART_X = "shared_art_x";
	String SHARED_ART_Y = "shared_art_y";
	String SHARED_ART_WIDTH = "shared_art_width";
	String SHARED_ART_HEGIHT = "shared_art_height";

	void setArtistTitle(String title);
	void setArtistInfo(int albumsCount, int tracksCount);
	void setArtistImage(Bitmap image);
}
