package com.edavtyan.prefs.description_list;

public class DescriptionListPresenter {
	private final DescriptionListPreference pref;
	private final DescriptionListModel model;

	public DescriptionListPresenter(
			DescriptionListPreference pref,
			DescriptionListModel model) {
		this.pref = pref;
		this.model = model;
	}

	public void onViewsInit() {
		pref.setTitle(model.getTitle());
		pref.setSummary(model.getSummary(), model.getCurrentPreference());
	}

	public void onListItemSelected(String value) {
		model.savePref(value);
		pref.closeDialog();
		pref.setSummary(model.getSummary(), model.getCurrentPreference());
	}

	public void onEntryClicked() {
		pref.showDialog();
	}

	public void bindViewHolder(DescriptionListViewHolder holder, int position) {
		holder.setTitle(model.getEntries().get(position));
		holder.setDescription(model.getSummaries().get(position));
		holder.setChecked(model.getPrefSelectedAtIndex(position));
		holder.setValue(model.getValues().get(position));
	}

	public int getItemCount() {
		return model.getEntries().size();
	}
}
