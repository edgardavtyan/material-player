package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.edavtyan.custompreference.utils.PixelConverter;

public class SimpleListPreference
		extends BasePreference
		implements SummaryEntry.OnClickListener,
				   SimpleListViewHolder.OnHolderClickListener {

	private final SummaryEntry entryView;
	private final BaseDialog dialogView;
	private final SimpleListPresenter presenter;

	public SimpleListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		SimpleListModel model = new SimpleListModel(context, attrs);

		entryView = initEntryView();
		dialogView = initDialogView(model);
		presenter = new SimpleListPresenter(this, model);
	}

	public SimpleListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		SimpleListModel controller = new SimpleListModel(context, attrs);

		entryView = initEntryView();
		dialogView = initDialogView(controller);
		presenter = new SimpleListPresenter(this, controller);
	}

	public void openDialog() {
		dialogView.show();
	}

	public void closeDialog() {
		dialogView.dismiss();
	}

	public void setTitle(CharSequence title) {
		entryView.setTitle(title);
		dialogView.setTitle(title);
	}

	public void setSummary(CharSequence summary) {
		entryView.setSummary(summary);
	}

	@Override
	public void onEntryClick() {
		presenter.onEntryClicked();
	}

	@Override
	public void onHolderClick(CharSequence value) {
		presenter.onListItemSelected(value);
		dialogView.dismiss();
	}

	private SummaryEntry initEntryView() {
		inflate(context, R.layout.entry_summary, this);
		SummaryEntry entryView = new SummaryEntry(context, this);
		entryView.setOnClickListener(this);
		return entryView;
	}

	private BaseDialog initDialogView(SimpleListModel model) {
		SimpleListAdapter adapter = new SimpleListAdapter(context, model);
		adapter.setOnHolderClickListener(this);

		RecyclerView list = new RecyclerView(context);
		list.setLayoutManager(new LinearLayoutManager(context));
		list.setAdapter(adapter);
		list.setPadding(0, PixelConverter.dpToPx(16), 0, 0);

		BaseDialog dialog = new BaseDialog(context);
		dialog.setView(list);
		return dialog;
	}
}
