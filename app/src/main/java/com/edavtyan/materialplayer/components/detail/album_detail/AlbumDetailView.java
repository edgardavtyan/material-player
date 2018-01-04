package com.edavtyan.materialplayer.components.detail.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.lists.track_list.TrackListView;

public interface AlbumDetailView extends TrackListView {
	String EXTRA_ALBUM_ID = "extra_albumId";

	void setAlbumTitle(String title);
	void setAlbumInfo(String artistTitle, int tracksCount, long duration);
	void setAlbumImage(Bitmap albumArt);
}
