package com.edavtyan.materialplayer.ui.lists.track_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackListDIModule {
	private final FragmentActivity activity;
	private final TrackListFragment view;

	public TrackListDIModule(FragmentActivity activity, TrackListFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public FragmentActivity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public TrackListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public TrackListModel provideModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB) {
		return new TrackListModel(serviceModule, trackDB);
	}

	@Provides
	@FragmentScope
	public TrackListPresenter providePresenter(TrackListView view, TrackListModel model) {
		return new TrackListPresenter(view, model);
	}

	@Provides
	@FragmentScope
	public TrackListAdapter provideAdapter(
			FragmentActivity activity, TrackListPresenter presenter, SdkFactory sdkFactory) {
		return new TrackListAdapter(activity, presenter, sdkFactory);
	}
}
