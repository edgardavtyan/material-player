package com.edavtyan.custompreference.simple_list;

public class SimpleListPresenter {
	private final SimpleListPreference pref;
	private final SimpleListModel model;
	private final String initialSummary;

	public SimpleListPresenter(SimpleListPreference pref, SimpleListModel model) {
		this.pref = pref;
		this.model = model;
		this.initialSummary = model.getSummary().toString();
	}

	public void onViewsInit() {
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

	public void bindViewHolder(SimpleListViewHolder holder, int position) {
		holder.setTitle(model.getEntries().get(position));
		holder.setChecked(model.getPrefSelectedAtIndex(position));
		holder.setValue(model.getValues().get(position));
	}

	public int getItemCount() {
		return model.getEntries().size();
	}
}
