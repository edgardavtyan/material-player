package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class NowPlayingFloatingFactory extends BaseFactory {
	private final NowPlayingFloatingFragment view;
	private NowPlayingFloatingModel model;
	private NowPlayingFloatingPresenter presenter;

	public NowPlayingFloatingFactory(Context context, NowPlayingFloatingFragment view) {
		super(context);
		this.view = view;
	}

	public NowPlayingFloatingModel getModel() {
		if (model == null)
			model = new NowPlayingFloatingModel(getContext(), getArtProvider());
		return model;
	}

	public NowPlayingFloatingPresenter getPresenter() {
		if (presenter == null)
			presenter = new NowPlayingFloatingPresenter(getModel(), view);
		return presenter;
	}
}
