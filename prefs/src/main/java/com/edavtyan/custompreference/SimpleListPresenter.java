package com.edavtyan.custompreference;

public class SimpleListPresenter {
	private final SimpleListPreference pref;
	private final SimpleListController model;
	private final String initialSummary;

	public SimpleListPresenter(SimpleListPreference pref, SimpleListController model) {
		this.model = model;
		this.initialSummary = model.getSummary().toString();

		this.pref = pref;
		this.pref.setTitle(model.getTitle());
		this.pref.setSummary(getFormattedSummary());
	}

	public void onEntryClicked() {
		pref.openDialog();
	}

	public void onListItemSelected(CharSequence value) {
		pref.closeDialog();
		pref.setSummary(getFormattedSummary());
		model.savePref(value);
	}

	private String getFormattedSummary() {
		return initialSummary.replace("%s", model.getCurrentPreference());
	}
}
