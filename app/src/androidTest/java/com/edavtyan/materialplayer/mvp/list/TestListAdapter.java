package com.edavtyan.materialplayer.mvp.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.lib.ListAdapter;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;

public class TestListAdapter extends ListAdapter {
	@SuppressWarnings("unchecked")
	public TestListAdapter(Context context, ListPresenter presenter) {
		super(context, presenter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(Context context, View view) {
		return null;
	}
}
