package com.edavtyan.custompreference.description_list;

public class DescriptionListPresenter {
	private final DescriptionListPreference pref;
	private final DescriptionListModel model;
	private final String initialSummary;

	public DescriptionListPresenter(
			DescriptionListPreference pref,
			DescriptionListModel model) {
		this.pref = pref;
		this.model = model;
		this.initialSummary = model.getSummary();
	}

	public void onViewsInit() {
		pref.setTitle(model.getTitle());
		pref.setSummary(getFormattedSummary());
	}

	public void onListItemSelected(String value) {
		model.savePref(value);
		pref.closeDialog();
		pref.setSummary(getFormattedSummary());
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

	private String getFormattedSummary() {
		return initialSummary.replace("%s", model.getCurrentPreference());
	}
}
