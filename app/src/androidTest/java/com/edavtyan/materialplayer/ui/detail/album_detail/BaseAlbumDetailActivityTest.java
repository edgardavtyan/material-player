package com.edavtyan.materialplayer.ui.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.AppDIModule;
import com.edavtyan.materialplayer.DaggerAppComponent;
import com.edavtyan.materialplayer.ui.detail.BaseDetailActivityTest;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseAlbumDetailActivityTest extends BaseDetailActivityTest {
	protected static AlbumDetailDIComponent createMockComponent(AppCompatActivity activity) {
		AlbumDetailDIModule albumDetailFactory = mock(AlbumDetailDIModule.class, RETURNS_MOCKS);
		when(albumDetailFactory.provideActivity()).thenReturn(activity);

		AppDIModule appFactory = mock(AppDIModule.class, RETURNS_MOCKS);
		when(appFactory.provideContext()).thenReturn(activity);

		AppDIComponent appComponent = DaggerAppComponent
				.builder()
				.appFactory(appFactory)
				.utilsFactory(utilsFactory)
				.build();

		return DaggerAlbumDetailComponent
				.builder()
				.appComponent(appComponent)
				.albumDetailFactory(albumDetailFactory)
				.build();
	}
}
