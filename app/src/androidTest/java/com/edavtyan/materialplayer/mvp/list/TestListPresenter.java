package com.edavtyan.materialplayer.mvp.list;

import com.edavtyan.materialplayer.ui.lists.lib.ListModel;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

public class TestListPresenter extends ListPresenter {
	public TestListPresenter(ListModel model, ListView view) {
		super();
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
