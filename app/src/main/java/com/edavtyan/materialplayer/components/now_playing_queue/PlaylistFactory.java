package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class PlaylistFactory extends BaseFactory {
	private final Context context;
	private PlaylistModel model;
	private PlaylistMvp.View view;
	private PlaylistPresenter presenter;
	private PlaylistAdapter adapter;

	public PlaylistFactory(Context context, PlaylistMvp.View view) {
		super(context);
		this.context = context;
		this.view = view;
	}

	public PlaylistMvp.Model provideModel() {
		if (model == null) {
			model = new PlaylistModel(context);
		}

		return model;
	}

	public PlaylistMvp.View provideView() {
		return view;
	}

	public PlaylistMvp.Presenter providePresenter() {
		if (presenter == null) {
			presenter = new PlaylistPresenter(provideModel(), provideView());
		}

		return presenter;
	}

	public PlaylistAdapter provideAdapter() {
		if (adapter == null) {
			adapter = new PlaylistAdapter(context, providePresenter());
		}

		return adapter;
	}
}
