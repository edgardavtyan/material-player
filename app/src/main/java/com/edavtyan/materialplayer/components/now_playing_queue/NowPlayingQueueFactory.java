package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;

import com.edavtyan.materialplayer.lib.mvp.list.ListFactory;

public class NowPlayingQueueFactory extends ListFactory {
	private final NowPlayingQueueMvp.View view;
	private NowPlayingQueueModel model;
	private NowPlayingQueuePresenter presenter;
	private NowPlayingQueueAdapter adapter;

	public NowPlayingQueueFactory(Context context, NowPlayingQueueMvp.View view) {
		super(context);
		this.view = view;
	}

	public NowPlayingQueueMvp.View provideView() {
		return view;
	}

	public NowPlayingQueueMvp.Model provideModel() {
		if (model == null)
			model = new NowPlayingQueueModel(provideContext(), provideCompactListPref());
		return model;
	}

	public NowPlayingQueueMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new NowPlayingQueuePresenter(provideModel(), provideView());
		return presenter;
	}

	public NowPlayingQueueAdapter provideAdapter() {
		if (adapter == null)
			adapter = new NowPlayingQueueAdapter(provideContext(), providePresenter());
		return adapter;
	}
}
