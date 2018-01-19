package com.edavtyan.materialplayer.components.detail.album_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.detail.BaseDetailActivityTest;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseAlbumDetailActivityTest extends BaseDetailActivityTest {
	protected static AlbumDetailComponent createMockComponent(AppCompatActivity activity) {
		AlbumDetailModule albumDetailModule = mock(AlbumDetailModule.class, RETURNS_MOCKS);
		when(albumDetailModule.provideActivity()).thenReturn(activity);
		when(albumDetailModule.provideContext()).thenReturn(activity);

		return DaggerAlbumDetailComponent
				.builder()
				.albumDetailModule(albumDetailModule)
				.activityModulesModule(activityModulesModule)
				.themeDaggerModule(themeDaggerModule)
				.utilsModule(utilsModule)
				.build();
	}
}
