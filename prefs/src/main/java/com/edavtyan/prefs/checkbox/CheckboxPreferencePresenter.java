package com.edavtyan.prefs.checkbox;

public class CheckboxPreferencePresenter {
	private final CheckboxPreference pref;
	private final CheckboxPreferenceModel model;

	public CheckboxPreferencePresenter(CheckboxPreference pref, CheckboxPreferenceModel model) {
		this.pref = pref;
		this.model = model;
	}

	public void onInit() {
		pref.setTitle(model.getTitle());
		pref.setChecked(model.getChecked());
	}

	public void onPrefClicked() {
		model.toggleChecked();
		pref.setChecked(model.getChecked());
	}
}
