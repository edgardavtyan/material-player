package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.ListFactory;

public class NowPlayingQueueFactory extends ListFactory {
	private final NowPlayingQueueActivity view;
	private NowPlayingQueueModel model;
	private NowPlayingQueuePresenter presenter;
	private NowPlayingQueueAdapter adapter;

	public NowPlayingQueueFactory(Context context, NowPlayingQueueActivity view) {
		super(context);
		this.view = view;
	}

	public NowPlayingQueueModel getModel() {
		if (model == null)
			model = new NowPlayingQueueModel(getModelServiceModule(), getCompactListPref());
		return model;
	}

	public NowPlayingQueuePresenter getPresenter() {
		if (presenter == null)
			presenter = new NowPlayingQueuePresenter(getModel(), view);
		return presenter;
	}

	public NowPlayingQueueAdapter getAdapter() {
		if (adapter == null)
			adapter = new NowPlayingQueueAdapter(getContext(), getPresenter());
		return adapter;
	}
}
