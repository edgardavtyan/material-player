package com.edavtyan.materialplayer.components.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.detail.BaseDetailActivityTest;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseAlbumDetailActivityTest extends BaseDetailActivityTest {
	protected static AlbumDetailComponent createMockComponent(AppCompatActivity activity) {
		AlbumDetailFactory albumDetailFactory = mock(AlbumDetailFactory.class, RETURNS_MOCKS);
		when(albumDetailFactory.provideActivity()).thenReturn(activity);
		when(albumDetailFactory.provideContext()).thenReturn(activity);

		return DaggerAlbumDetailComponent
				.builder()
				.albumDetailFactory(albumDetailFactory)
				.themeFactory(themeFactory)
				.utilsFactory(utilsFactory)
				.build();
	}
}
