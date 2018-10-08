package com.edavtyan.materialplayer.ui.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailDIModule {
	private final AlbumDetailActivity activity;
	private final int albumId;

	public AlbumDetailDIModule(AlbumDetailActivity activity, int albumId) {
		this.activity = activity;
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
			PlaylistManager playlistManager) {
		return new AlbumDetailModel(
				serviceModule, albumDB, trackDB, artProvider, playlistManager, albumId);
	}

	@Provides
	@ActivityScope
	public AlbumDetailAdapter provideAdapter(AlbumDetailPresenter presenter, SdkFactory sdkFactory) {
		return new AlbumDetailAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@ActivityScope
	public TestableRecyclerAdapter provideTestableRecyclerAdapter(AlbumDetailAdapter adapter) {
		return adapter;
	}

	@Provides
	@ActivityScope
	public AlbumDetailPresenter providePresenter(AlbumDetailModel model) {
		return new AlbumDetailPresenter(model, activity);
	}

	@Provides
	@ActivityScope
	public ParallaxHeaderListPresenter provideParallaxPresenter(AlbumDetailPresenter presenter) {
		return presenter;
	}
}
