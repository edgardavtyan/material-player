package com.edavtyan.prefs.color;

public class ColorSelectionPresenter {
	private final ColorSelectionPreference pref;
	private final ColorSelectionModel model;

	public ColorSelectionPresenter(ColorSelectionPreference pref, ColorSelectionModel model) {
		this.pref = pref;
		this.model = model;
		pref.setColors(model.getEntries());
		pref.setTitle(model.getTitle());
		pref.setSelectedColor(model.getSelectedPrefIndex(), model.getCurrentColor());
	}

	public void onEntryClick() {
		pref.showDialog();
	}

	public void onColorSelected(int position) {
		model.savePref(position);
		pref.setSelectedColor(position, model.getCurrentColor());
		pref.closeDialog();
	}
}
