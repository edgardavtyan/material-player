package com.edavtyan.materialplayer.ui.lists.lib;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.fragment.ModularFragment;
import com.edavtyan.materialplayer.ui.main.MainActivity;

public class ListFragment extends ModularFragment implements ListView {
	private ListFragmentModule listFragmentModule;

	protected void initListView(ListPresenter presenter, ListAdapter adapter) {
		listFragmentModule = new ListFragmentModule(this, adapter, presenter);
		addModule(listFragmentModule);
	}

	@Override
	public void disableTouchEvents() {
		((MainActivity) getActivity()).disableTouchEvents();
	}

	@Override
	public void enableTouchEvents() {
		((MainActivity) getActivity()).enableTouchEvents();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list;
	}

	@Override
	public void notifyDataSetChanged() {
		listFragmentModule.notifyDataSetChanged();
	}
}
