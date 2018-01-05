package com.edavtyan.materialplayer.mvp.list;

import com.edavtyan.materialplayer.components.lists.lib.ListModel;
import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.components.lists.lib.ListView;

public class TestListPresenter extends ListPresenter {
	public TestListPresenter(ListModel model, ListView view) {
		super(model, view);
	}

	@Override
	public void onBindViewHolder(Object holder, int position) {
	}

	@Override
	public int getItemCount() {
		return 0;
	}

	@Override
	public void onDestroy() {
	}
}
