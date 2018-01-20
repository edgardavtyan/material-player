package com.edavtyan.materialplayer.components.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.ActivityScope;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailFactory {
	private final AppCompatActivity activity;
	private final AlbumDetailView view;
	private final int albumId;

	public AlbumDetailFactory(AppCompatActivity activity, AlbumDetailView view, int albumId) {
		this.activity = activity;
		this.view = view;
		this.albumId = albumId;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
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
	@ActivityScope
	public AlbumDetailAdapter provideAdapter(AlbumDetailPresenter presenter) {
		return new AlbumDetailAdapter(activity, presenter);
	}

	@Provides
	@ActivityScope
	public TestableRecyclerAdapter provideTestableRecyclerAdapter(AlbumDetailAdapter adapter) {
		return adapter;
	}

	@Provides
	@ActivityScope
	public AlbumDetailPresenter providePresenter(AlbumDetailModel model) {
		return new AlbumDetailPresenter(model, view);
	}

	@Provides
	@ActivityScope
	public ParallaxHeaderListPresenter provideParallaxPresenter(AlbumDetailPresenter presenter) {
		return presenter;
	}
}
