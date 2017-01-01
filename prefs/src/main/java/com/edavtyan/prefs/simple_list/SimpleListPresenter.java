package com.edavtyan.prefs.simple_list;

public class SimpleListPresenter {
	private final SimpleListPreference pref;
	private final SimpleListModel model;

	public SimpleListPresenter(SimpleListPreference pref, SimpleListModel model) {
		this.pref = pref;
		this.model = model;
	}

	public void onViewsInit() {
		pref.setTitle(model.getTitle());
		pref.setSummary(model.getSummary(), model.getCurrentPreference());
	}

	public void onEntryClicked() {
		pref.openDialog();
	}

	public void onListItemSelected(String value) {
		pref.closeDialog();
		pref.setSummary(model.getSummary(), model.getCurrentPreference());
		model.savePref(value);
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
