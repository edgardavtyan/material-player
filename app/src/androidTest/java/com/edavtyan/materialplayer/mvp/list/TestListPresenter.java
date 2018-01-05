package com.edavtyan.materialplayer.mvp.list;

import com.edavtyan.materialplayer.components.lists.lib.ListPresenter;

public class TestListPresenter extends ListPresenter {
	public TestListPresenter(ListMvp.Model model, ListMvp.View view) {
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
