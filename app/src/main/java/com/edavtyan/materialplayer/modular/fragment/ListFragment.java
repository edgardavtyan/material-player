package com.edavtyan.materialplayer.modular.fragment;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.list.ListAdapter;
import com.edavtyan.materialplayer.lib.mvp.list.ListMvp;
import com.edavtyan.materialplayer.modular.ModularFragment;

public class ListFragment extends ModularFragment {
	private ListFragmentModule listFragmentModule;

	protected void initListView(ListMvp.Presenter presenter, ListAdapter adapter) {
		listFragmentModule = new ListFragmentModule(this, adapter, presenter);
		addModule(listFragmentModule);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list;
	}

	public void notifyDataSetChanged() {
		listFragmentModule.notifyDataSetChanged();
	}
}
