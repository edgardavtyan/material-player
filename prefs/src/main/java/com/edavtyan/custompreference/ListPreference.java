package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.edavtyan.custompreference.utils.PixelConverter;

public abstract class ListPreference<TController extends ListController, TAdapter extends ListAdapter>
		extends SummaryPreference<TController> {

	public ListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	protected abstract TAdapter createAdapter(TController controller);


	@Override
	protected View onCreateDialogView() {
		RecyclerView list = new RecyclerView(context, null);
		list.setAdapter(createAdapter(controller));
		list.setLayoutManager(new LinearLayoutManager(context));
		list.setPadding(0, PixelConverter.dpToPx(8), 0, 0);
		return list;
	}
}
