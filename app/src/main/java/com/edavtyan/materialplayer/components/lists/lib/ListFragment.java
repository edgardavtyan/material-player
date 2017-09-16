package com.edavtyan.materialplayer.components.lists.lib;

import com.edavtyan.materialplayer.R;
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
