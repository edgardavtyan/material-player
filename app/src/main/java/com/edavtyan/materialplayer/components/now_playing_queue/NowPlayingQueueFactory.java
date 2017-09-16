package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.ListFactory;

public class NowPlayingQueueFactory extends ListFactory {
	private final NowPlayingQueueMvp.View view;
	private NowPlayingQueueModel model;
	private NowPlayingQueuePresenter presenter;
	private NowPlayingQueueAdapter adapter;

	public NowPlayingQueueFactory(Context context, NowPlayingQueueMvp.View view) {
		super(context);
		this.view = view;
	}

	public NowPlayingQueueMvp.View getView() {
		return view;
	}

	public NowPlayingQueueMvp.Model getModel() {
		if (model == null)
			model = new NowPlayingQueueModel(getContext(), getCompactListPref());
		return model;
	}

	public NowPlayingQueueMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new NowPlayingQueuePresenter(getModel(), getView());
		return presenter;
	}

	public NowPlayingQueueAdapter getAdapter() {
		if (adapter == null)
			adapter = new NowPlayingQueueAdapter(getContext(), getPresenter());
		return adapter;
	}
}
