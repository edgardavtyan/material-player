package com.edavtyan.materialplayer.components.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.CompactPrefsModule;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.UtilsModule;
import com.edavtyan.materialplayer.components.lists.lib.ListFragment;
import com.edavtyan.materialplayer.db.DbModule;
import com.edavtyan.materialplayer.lib.lastfm.LastFmModule;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefsModule;
import com.edavtyan.materialplayer.modular.model.ModelModulesModule;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void gotoArtistDetailNormal(String title) {
		navigator.gotoArtistDetailNormal(title);
	}

	@Override
	public void gotoArtistDetailCompact(String title) {
		navigator.gotoArtistDetailCompact(title);
	}

	protected ArtistListComponent getComponent() {
		return DaggerArtistListComponent
				.builder()
				.artistListModule(new ArtistListModule(getActivity(), this))
				.compactPrefsModule(new CompactPrefsModule())
				.advancedSharedPrefsModule(new AdvancedSharedPrefsModule())
				.dbModule(new DbModule())
				.lastFmModule(new LastFmModule(getString(R.string.lastfm_api_key)))
				.modelModulesModule(new ModelModulesModule())
				.utilsModule(new UtilsModule())
				.build();
	}
}
