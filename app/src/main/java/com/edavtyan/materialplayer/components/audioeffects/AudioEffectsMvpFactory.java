package com.edavtyan.materialplayer.components.audioeffects;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AudioEffectsMvpFactory extends BaseFactory {
	private final AudioEffectsMvp.View view;

	private AudioEffectsMvp.Presenter presenter;
	private AudioEffectsMvp.Model model;

	public AudioEffectsMvpFactory(Context context, AudioEffectsMvp.View view) {
		super(context);
		this.view = view;
	}

	public AudioEffectsMvp.View getView() {
		return view;
	}

	public AudioEffectsMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new AudioEffectsPresenter(getModel(), getView());
		return presenter;
	}

	public AudioEffectsMvp.Model getModel() {
		if (model == null)
			model = new AudioEffectsModel(getModelServiceModule());
		return model;
	}
}
