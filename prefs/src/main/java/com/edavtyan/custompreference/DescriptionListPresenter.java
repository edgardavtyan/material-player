package com.edavtyan.custompreference;

public class DescriptionListPresenter {
	private final DescriptionListPreference pref;
	private final DescriptionListController model;
	private final String initialSummary;

	public DescriptionListPresenter(
			DescriptionListPreference pref,
			DescriptionListController model) {
		this.model = model;
		this.initialSummary = model.getSummary().toString();

		this.pref = pref;
		pref.setTitle(model.getTitle());
		pref.setSummary(getFormattedSummary());
	}

	public void onListItemSelected(CharSequence value) {
		model.savePref(value);
		pref.closeDialog();
		pref.notifyDataChanged();
		pref.setSummary(getFormattedSummary());
	}

	public void onEntryClicked() {
		pref.showDialog();
	}

	private String getFormattedSummary() {
		return initialSummary.replace("%s", model.getCurrentPreference());
	}
}
