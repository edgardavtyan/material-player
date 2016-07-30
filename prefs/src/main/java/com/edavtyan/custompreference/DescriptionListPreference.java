package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.edavtyan.custompreference.utils.PixelConverter;

public class DescriptionListPreference extends BasePreference
		implements DescriptionListAdapter.OnHolderClickListener, SummaryEntry.OnClickListener {

	private final SummaryEntry entryView;
	private final BaseDialog dialog;
	private final DescriptionListAdapter adapter;
	private final DescriptionListPresenter presenter;

	public DescriptionListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		DescriptionListController model = initModel(attrs);
		entryView = initEntryView();
		adapter = initAdapter(model);
		dialog = initDialog();
		presenter = initPresenter(model);
	}

	public DescriptionListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		DescriptionListController model = initModel(attrs);
		entryView = initEntryView();
		adapter = initAdapter(model);
		dialog = initDialog();
		presenter = initPresenter(model);
	}

	public void showDialog() {
		dialog.show();
	}

	public void closeDialog() {
		dialog.dismiss();
	}

	public void notifyDataChanged() {
		adapter.notifyDataSetChanged();
	}

	public void setTitle(CharSequence title) {
		dialog.setTitle(title);
		entryView.setTitle(title);
	}

	public void setSummary(String summary) {
		entryView.setSummary(summary);
	}

	@Override
	public void onEntryClick() {
		presenter.onEntryClicked();
	}

	@Override
	public void onHolderClick(CharSequence value) {
		presenter.onListItemSelected(value);
	}

	private SummaryEntry initEntryView() {
		inflate(context, R.layout.entry_summary, this);
		SummaryEntry entryView = new SummaryEntry(context, this);
		entryView.setOnClickListener(this);
		return entryView;
	}

	private DescriptionListAdapter initAdapter(DescriptionListController model) {
		DescriptionListAdapter adapter = new DescriptionListAdapter(context, model);
		adapter.setOnHolderClickListener(this);
		return adapter;
	}

	private BaseDialog initDialog() {
		RecyclerView list = new RecyclerView(context);
		list.setLayoutManager(new LinearLayoutManager(context));
		list.setAdapter(adapter);
		list.setPadding(0, PixelConverter.dpToPx(16), 0, 0);

		BaseDialog dialog = new BaseDialog(context);
		dialog.setView(list);
		return dialog;
	}

	private DescriptionListPresenter initPresenter(DescriptionListController model) {
		DescriptionListPresenter presenter = new DescriptionListPresenter(this, model);
		return presenter;
	}

	private DescriptionListController initModel(AttributeSet attributeSet) {
		return new DescriptionListController(context, attributeSet);
	}
}
