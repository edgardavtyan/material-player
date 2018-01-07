package com.edavtyan.materialplayer.components.lists.album_list;

import android.content.Context;

import com.edavtyan.materialplayer.components.detail.lib.CompactDetailPref;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumListModule {
	private final Context context;
	private final AlbumListView view;

	public AlbumListModule(Context context, AlbumListView view) {
		this.context = context;
		this.view = view;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public AlbumListView provideView() {
		return view;
	}

	@Provides
	@Singleton
	public AlbumListModel provideModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new AlbumListModel(
				serviceModule,
				albumDB,
				trackDB,
				compactListPref);
	}

	@Provides
	@Singleton
	public AlbumListPresenter providePresenter(AlbumListModel model, AlbumListView view) {
		return new AlbumListPresenter(model, view);
	}

	@Provides
	@Singleton
	public AlbumListAdapter provideAdapter(Context context, AlbumListPresenter presenter) {
		return new AlbumListAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public CompactListPref provideCompactListPref(Context context, AdvancedSharedPrefs prefs) {
		return new CompactListPref(context, prefs);
	}

	@Provides
	@Singleton
	public CompactDetailPref provideCompactDetailPref(Context context) {
		return new CompactDetailPref(context);
	}
}
