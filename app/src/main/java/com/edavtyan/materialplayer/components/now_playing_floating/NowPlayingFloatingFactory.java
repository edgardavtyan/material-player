package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class NowPlayingFloatingFactory extends BaseFactory {
	private final NowPlayingFloatingMvp.View view;
	private NowPlayingFloatingModel model;
	private NowPlayingFloatingPresenter presenter;

	public NowPlayingFloatingFactory(Context context, NowPlayingFloatingMvp.View view) {
		super(context);
		this.view = view;
	}

	public NowPlayingFloatingMvp.View provideView() {
		return view;
	}

	public NowPlayingFloatingMvp.Model provideModel() {
		if (model == null)
			model = new NowPlayingFloatingModel(provideContext());
		return model;
	}

	public NowPlayingFloatingMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new NowPlayingFloatingPresenter(provideModel(), provideView());
		return presenter;
	}
}
