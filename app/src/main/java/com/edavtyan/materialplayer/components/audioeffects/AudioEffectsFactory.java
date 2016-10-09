package com.edavtyan.materialplayer.components.audioeffects;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AudioEffectsFactory extends BaseFactory {
	private final AudioEffectsMvp.View view;

	private AudioEffectsMvp.Presenter presenter;
	private AudioEffectsMvp.Model model;

	public AudioEffectsFactory(Context context, AudioEffectsMvp.View view) {
		super(context);
		this.view = view;
	}

	public AudioEffectsMvp.View provideView() {
		return view;
	}

	public AudioEffectsMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new AudioEffectsPresenter(provideModel(), provideView());
		return presenter;
	}

	public AudioEffectsMvp.Model provideModel() {
		if (model == null)
			model = new AudioEffectsModel(provideContext());
		return model;
	}
}
