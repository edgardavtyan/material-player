package com.edavtyan.materialplayer.components.prefs.views.summarylist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.edavtyan.materialplayer.R;

public class SummaryListPreference extends DialogPreference
		implements SummaryListAdapter.OnItemSelectedListener {
	private final SummaryListAttributes attributes;
	private final SummaryListAdapter adapter;
	private final String initialSummary;

	//---

	public SummaryListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		attributes = new SummaryListAttributes(context, attrs);
		adapter = new SummaryListAdapter(context, attributes);
		initialSummary = getSummary().toString();
		setDialogLayoutResource(R.layout.pref_list_summary);
		setPersistent(false);
		updateSummary();
	}

	public SummaryListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		attributes = new SummaryListAttributes(context, attrs);
		adapter = new SummaryListAdapter(context, attributes);
		initialSummary = getSummary().toString();
		setDialogLayoutResource(R.layout.pref_list_summary);
		setPersistent(false);
		updateSummary();
	}

	//---

	@Override
	protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
		super.onPrepareDialogBuilder(builder);
		builder.setPositiveButton(null, null);
		updateSummary();
	}

	@Override
	protected void onBindDialogView(View view) {
		super.onBindDialogView(view);

		RecyclerView list = (RecyclerView) view.findViewById(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(getContext()));

		SummaryListAdapter adapter = new SummaryListAdapter(getContext(), attributes);
		adapter.setOnItemSelectedListener(this);
		list.setAdapter(adapter);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		updateSummary();
		super.onDismiss(dialog);
	}

	@Override
	public void onItemSelected() {
		getDialog().dismiss();
		updateSummary();
	}

	//---

	private void updateSummary() {
		setSummary(initialSummary.replace("%s", adapter.getSelectedItem()));
	}
}
