package com.edavtyan.materialplayer.ui.lists.lib.simple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.lib.ListAdapter;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;

public abstract class SimpleTextListAdapter<VH extends RecyclerView.ViewHolder>
		extends ListAdapter<VH> {

	public SimpleTextListAdapter(Context context, ListPresenter<VH> presenter) {
		super(context, presenter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_simple;
	}
}
