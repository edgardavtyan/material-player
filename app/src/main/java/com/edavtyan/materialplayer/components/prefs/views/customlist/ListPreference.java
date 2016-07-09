package com.edavtyan.materialplayer.components.prefs.views.customlist;

import android.app.AlertDialog;
import android.content.Context;
import android.preference.DialogPreference;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.edavtyan.materialplayer.R;

public abstract class ListPreference
		extends DialogPreference
		implements ListPreferenceAdapter.OnItemClickListener {
	protected final Context context;
	protected final ListPreferenceAdapter adapter;
	protected final ListPreferenceManager manager;
	private final String initialSummary;

	//---

	public ListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initialSummary = getSummary().toString();
		manager = getManager(attrs);
		adapter = getAdapter(manager);
		adapter.setOnItemClickListener(this);
		setDialogLayoutResource(getLayoutId());
		setPersistent(false);
		updateSummary();
	}

	public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initialSummary = getSummary().toString();
		manager = getManager(attrs);
		adapter = getAdapter(manager);
		adapter.setOnItemClickListener(this);
		setDialogLayoutResource(getLayoutId());
		setPersistent(false);
		updateSummary();
	}

	//---

	public abstract ListPreferenceAdapter getAdapter(ListPreferenceManager manager);
	public abstract ListPreferenceManager getManager(AttributeSet attributeSet);
	public abstract int getLayoutId();

	//---

	protected void updateSummary() {
		setSummary(initialSummary.replace("%s", manager.getSelectedPreference()));
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
		list.setAdapter(adapter);
	}

	@Override
	public void onPreferenceSelected(int position) {
		String selectedPref = manager.getValues().get(position).toString();
		manager.savePreference(selectedPref);
		getDialog().dismiss();
		updateSummary();
	}
}
