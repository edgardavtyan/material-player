package com.edavtyan.custompreference;

public class ColorSelectionPresenter {
	private final ColorSelectionPreference pref;
	private final ColorSelectionController model;

	public ColorSelectionPresenter(ColorSelectionPreference pref, ColorSelectionController model) {
		this.model = model;
		this.pref = pref;
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
