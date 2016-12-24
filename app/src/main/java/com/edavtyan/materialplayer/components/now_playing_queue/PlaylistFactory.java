package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class PlaylistFactory extends BaseFactory {
	private final PlaylistMvp.View view;
	private PlaylistModel model;
	private PlaylistPresenter presenter;
	private PlaylistAdapter adapter;

	public PlaylistFactory(Context context, PlaylistMvp.View view) {
		super(context);
		this.view = view;
	}

	public PlaylistMvp.View provideView() {
		return view;
	}

	public PlaylistMvp.Model provideModel() {
		if (model == null)
			model = new PlaylistModel(provideContext());
		return model;
	}

	public PlaylistMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new PlaylistPresenter(provideModel(), provideView());
		return presenter;
	}

	public PlaylistAdapter provideAdapter() {
		if (adapter == null)
			adapter = new PlaylistAdapter(provideContext(), providePresenter());
		return adapter;
	}
}
