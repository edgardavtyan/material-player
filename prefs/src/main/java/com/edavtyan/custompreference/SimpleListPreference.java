package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.edavtyan.custompreference.utils.PixelConverter;

public class SimpleListPreference
		extends BasePreference
		implements SummaryEntry.OnClickListener {

	private final SummaryEntry entryView;
	private final BaseDialog dialogView;
	private final SimpleListPresenter presenter;

	public SimpleListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		presenter = new SimpleListPresenter(this, new SimpleListModel(context, attrs));
		entryView = initEntryView();
		dialogView = initDialogView(presenter);
		presenter.onViewsInit();
	}

	public SimpleListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		presenter = new SimpleListPresenter(this, new SimpleListModel(context, attrs));
		entryView = initEntryView();
		dialogView = initDialogView(presenter);
		presenter.onViewsInit();
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

	private SummaryEntry initEntryView() {
		SummaryEntry entryView = new SummaryEntry(context, this);
		entryView.setOnClickListener(this);
		return entryView;
	}

	private BaseDialog initDialogView(SimpleListPresenter presenter) {
		RecyclerView list = new RecyclerView(context);
		list.setLayoutManager(new LinearLayoutManager(context));
		list.setAdapter(new SimpleListAdapter(context, presenter));
		list.setPadding(0, PixelConverter.dpToPx(8), 0, 0);

		BaseDialog dialog = new BaseDialog(context);
		dialog.setView(list);
		return dialog;
	}
}
