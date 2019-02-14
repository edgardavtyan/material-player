package com.edavtyan.materialplayer.ui.lists.album_list;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListDIModule {
	private final Activity activity;
	private final AlbumListFragment view;

	public AlbumListDIModule(Activity activity, AlbumListFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public AlbumListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public AlbumListModel provideModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			AlbumArtProvider albumArtProvider) {
		return new AlbumListModel(serviceModule, albumDB, trackDB, albumArtProvider);
	}

	@Provides
	@FragmentScope
	public AlbumListPresenter providePresenter(AlbumListModel model, AlbumListView view) {
		return new AlbumListPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public AlbumListAdapter provideAdapter(
			Activity activity, AlbumListPresenter presenter, SdkFactory sdkFactory) {
		return new AlbumListAdapter(activity, presenter, sdkFactory);
	}
}
