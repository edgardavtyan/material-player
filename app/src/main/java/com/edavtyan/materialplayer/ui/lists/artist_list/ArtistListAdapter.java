package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.lists.lib.ListAdapter;

public class ArtistListAdapter extends ListAdapter<ArtistListViewHolder> {

	private final ArtistListPresenter presenter;
	private final SdkFactory sdkFactory;

	public ArtistListAdapter(Context context, ArtistListPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_album;
	}

	@Override
	public ArtistListViewHolder onCreateViewHolder(Context context, View view) {
		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		return new ArtistListViewHolder(context, view, presenter, contextMenu);
	}
}
