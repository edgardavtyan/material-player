package com.edavtyan.materialplayer.components.audioeffects;

import android.content.Context;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class AudioEffectsFactory extends BaseFactory {
	private final Context context;
	private AudioEffectsMvp.Presenter presenter;
	private AudioEffectsMvp.Model model;
	private AudioEffectsMvp.View view;

	public AudioEffectsFactory(Context context, AudioEffectsMvp.View view) {
		super(context);
		this.context = context;
		this.view = view;
	}

	public AudioEffectsMvp.Presenter providePresenter() {
		if (presenter == null) presenter = new AudioEffectsPresenter(provideModel(), provideView());
		return presenter;
	}

	private AudioEffectsMvp.Model provideModel() {
		if (model == null) model = new AudioEffectsModel(context);
		return model;
	}

	private AudioEffectsMvp.View provideView() {
		return view;
	}
}
