package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.AppDIModule;
import com.edavtyan.materialplayer.DaggerAppDIComponent;
import com.edavtyan.materialplayer.ui.detail.BaseDetailActivityTest;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseArtistDetailActivityTest extends BaseDetailActivityTest {
	protected static ArtistDetailDIComponent createMockComponent(AppCompatActivity activity) {
		ArtistDetailDIModule artistDetailFactory = mock(ArtistDetailDIModule.class, RETURNS_MOCKS);
		when(artistDetailFactory.provideActivity()).thenReturn(activity);

		AppDIModule appFactory = mock(AppDIModule.class, RETURNS_MOCKS);
		when(appFactory.provideContext()).thenReturn(activity);

		AppDIComponent appComponent = DaggerAppDIComponent
				.builder()
				.appDIModule(appFactory)
				.utilsDIModule(utilsFactory)
				.build();

		return DaggerArtistDetailDIComponent
				.builder()
				.appDIComponent(appComponent)
				.artistDetailDIModule(artistDetailFactory)
				.build();
	}
}
