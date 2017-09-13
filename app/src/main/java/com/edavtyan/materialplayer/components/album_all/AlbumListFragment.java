package com.edavtyan.materialplayer.components.album_all;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.modular.ModularFragment;
import com.edavtyan.materialplayer.modular.fragment.ListFragmentModule;

public class AlbumListFragment
		extends ModularFragment
		implements AlbumListMvp.View {

	private Navigator navigator;
	private ListFragmentModule listFragmentModule;

	protected void setListFragmentModule(ListFragmentModule module) {
		listFragmentModule = module;
		switchModule(ListFragmentModule.class, module);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_list;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		App app = (App) getContext().getApplicationContext();
		AlbumListFactory factory = app.getAlbumListDI(getActivity(), this);

		navigator = factory.getNavigator();

		listFragmentModule = new ListFragmentModule(this, factory.getAdapter(), factory.getPresenter());

		addModule(listFragmentModule);
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}

	@Override
	public void notifyDataSetChanged() {
		listFragmentModule.notifyDataSetChanged();
	}
}
