package com.edavtyan.custompreference.simple_list;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.edavtyan.custompreference.base.BaseDialog;
import com.edavtyan.custompreference.base.BasePreference;
import com.edavtyan.custompreference.base.SummaryEntry;
import com.edavtyan.custompreference.utils.PixelConverter;

public class SimpleListPreference
		extends BasePreference
		implements SummaryEntry.OnClickListener {

	private SummaryEntry entryView;
	private BaseDialog dialogView;
	private SimpleListPresenter presenter;

	public SimpleListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPreference(context, attrs);
	}

	public SimpleListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPreference(context, attrs);
	}

	public void openDialog() {
		dialogView.show();
	}

	public void closeDialog() {
		dialogView.dismiss();
	}

	public void setTitle(String title) {
		entryView.setTitle(title);
		dialogView.setTitle(title);
	}

	public void setSummary(String summary) {
		entryView.setSummary(summary);
	}

	@Override
	public void onEntryClick() {
		presenter.onEntryClicked();
	}

	private void initPreference(Context context, AttributeSet attrs) {
		entryView = initEntryView();
		if (isInEditMode()) {
		} else {
			presenter = new SimpleListPresenter(this, new SimpleListModel(context, attrs));
			dialogView = initDialogView(presenter);
			presenter.onViewsInit();
		}
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
