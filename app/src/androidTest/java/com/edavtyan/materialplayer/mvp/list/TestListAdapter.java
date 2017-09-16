package com.edavtyan.materialplayer.mvp.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListAdapter;
import com.edavtyan.materialplayer.components.lists.lib.ListMvp;

public class TestListAdapter extends ListAdapter {
	@SuppressWarnings("unchecked")
	public TestListAdapter(Context context, ListMvp.Presenter presenter) {
		super(context, presenter);
	}

	@Override
	public int getNormalLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public int getCompactLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(Context context, View view) {
		return null;
	}
}
