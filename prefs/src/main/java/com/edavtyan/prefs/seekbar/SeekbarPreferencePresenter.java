package com.edavtyan.prefs.seekbar;

public class SeekbarPreferencePresenter {
	private final SeekbarPreference pref;
	private final SeekbarPreferenceModel model;

	public SeekbarPreferencePresenter(SeekbarPreference pref, SeekbarPreferenceModel model) {
		this.pref = pref;
		this.model = model;
	}

	public void onInit() {
		pref.setTitle(model.getTitle());
		pref.setSeek(model.getSeek());
		pref.setMaxSeek(model.getMaxValue());
	}

	public void onSeek(int seek) {
		model.setValue(seek);
		pref.setSeekText(String.format(model.getFormat(), seek * model.getMultiplier()));
	}
}
