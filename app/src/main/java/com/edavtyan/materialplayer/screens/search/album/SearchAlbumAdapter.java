package com.edavtyan.materialplayer.screens.search.album;

import android.content.Context;

import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListAdapter;

public class SearchAlbumAdapter extends AlbumListAdapter {
	public SearchAlbumAdapter(Context context, SearchAlbumPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
	}
}
