package com.edavtyan.materialplayer.ui.search.album;

import android.content.Context;

import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListAdapter;

public class SearchAlbumAdapter extends AlbumListAdapter {
	public SearchAlbumAdapter(Context context, SearchAlbumPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
	}
}
