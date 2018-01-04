package com.edavtyan.materialplayer.components.audio_effects;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AudioEffectsViewFactory extends BaseFactory {
	private final AudioEffectsActivity view;

	private AudioEffectsPresenter presenter;
	private AudioEffectsModel model;

	public AudioEffectsViewFactory(Context context, AudioEffectsActivity view) {
		super(context);
		this.view = view;
	}

	public AudioEffectsPresenter getPresenter() {
		if (presenter == null)
			presenter = new AudioEffectsPresenter(getModel(), view);
		return presenter;
	}

	public AudioEffectsModel getModel() {
		if (model == null)
			model = new AudioEffectsModel(getModelServiceModule());
		return model;
	}
}
