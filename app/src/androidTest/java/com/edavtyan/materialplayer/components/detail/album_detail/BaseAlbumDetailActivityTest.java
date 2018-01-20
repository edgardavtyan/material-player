package com.edavtyan.materialplayer.components.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.AppFactory;
import com.edavtyan.materialplayer.DaggerAppComponent;
import com.edavtyan.materialplayer.components.detail.BaseDetailActivityTest;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseAlbumDetailActivityTest extends BaseDetailActivityTest {
	protected static AlbumDetailComponent createMockComponent(AppCompatActivity activity) {
		AlbumDetailFactory albumDetailFactory = mock(AlbumDetailFactory.class, RETURNS_MOCKS);
		when(albumDetailFactory.provideActivity()).thenReturn(activity);

		AppFactory appFactory = mock(AppFactory.class, RETURNS_MOCKS);
		when(appFactory.provideContext()).thenReturn(activity);

		AppComponent appComponent = DaggerAppComponent
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
