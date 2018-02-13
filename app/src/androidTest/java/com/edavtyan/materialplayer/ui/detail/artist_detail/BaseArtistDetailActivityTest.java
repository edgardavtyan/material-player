package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.AppComponent;
import com.edavtyan.materialplayer.AppFactory;
import com.edavtyan.materialplayer.DaggerAppComponent;
import com.edavtyan.materialplayer.ui.detail.BaseDetailActivityTest;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseArtistDetailActivityTest extends BaseDetailActivityTest {
	protected static ArtistDetailComponent createMockComponent(AppCompatActivity activity) {
		ArtistDetailFactory artistDetailFactory = mock(ArtistDetailFactory.class, RETURNS_MOCKS);
		when(artistDetailFactory.provideActivity()).thenReturn(activity);

		AppFactory appFactory = mock(AppFactory.class, RETURNS_MOCKS);
		when(appFactory.provideContext()).thenReturn(activity);

		AppComponent appComponent = DaggerAppComponent
				.builder()
				.appFactory(appFactory)
				.utilsFactory(utilsFactory)
				.build();

		return DaggerArtistDetailComponent
				.builder()
				.appComponent(appComponent)
				.artistDetailFactory(artistDetailFactory)
				.build();
	}
}
