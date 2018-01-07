package com.edavtyan.materialplayer.components.detail.album_detail;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailModule {
	private final AlbumDetailView view;
	private final Context context;
	private final int albumId;

	public AlbumDetailModule(Context context, AlbumDetailView view, int albumId) {
		this.context = context;
		this.view = view;
		this.albumId = albumId;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public AlbumDetailModel provideModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			AlbumArtProvider artProvider,
			CompactListPref compactListPrefs) {
		return new AlbumDetailModel(
				serviceModule, albumDB, trackDB, artProvider, compactListPrefs, albumId);
	}

	@Provides
	@Singleton
	public AlbumDetailAdapter provideAdapter(AlbumDetailPresenter presenter) {
		return new AlbumDetailAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public AlbumDetailPresenter providePresenter(AlbumDetailModel model) {
		return new AlbumDetailPresenter(model, view);
	}
}
