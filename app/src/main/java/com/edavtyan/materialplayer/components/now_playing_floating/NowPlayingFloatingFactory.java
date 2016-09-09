package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class NowPlayingFloatingFactory extends BaseFactory {
	private final Context context;
	private final NowPlayingFloatingMvp.View view;
	private NowPlayingFloatingModel model;

	public NowPlayingFloatingFactory(Context context, NowPlayingFloatingMvp.View view) {
		super(context);
		this.context = context;
		this.view = view;
	}

	private NowPlayingFloatingMvp.Model provideModel() {
		if (model == null) model = new NowPlayingFloatingModel(context);
		return model;
	}

	public NowPlayingFloatingMvp.View provideView() {
		return view;
	}

	public NowPlayingFloatingMvp.Presenter providePresenter() {
		return new NowPlayingFloatingPresenter(provideModel(), provideView());
	}
}
