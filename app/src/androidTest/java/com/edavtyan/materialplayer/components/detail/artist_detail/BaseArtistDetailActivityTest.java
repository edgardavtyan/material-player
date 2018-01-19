package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.detail.BaseDetailActivityTest;
import com.edavtyan.materialplayer.lib.lastfm.LastFmModule;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseArtistDetailActivityTest extends BaseDetailActivityTest {
	protected static LastFmModule lasFmModule;

	protected static ArtistDetailComponent createMockComponent(AppCompatActivity activity) {
		ArtistDetailModule artistDetailModule = mock(ArtistDetailModule.class, RETURNS_MOCKS);
		when(artistDetailModule.provideActivity()).thenReturn(activity);
		when(artistDetailModule.provideContext()).thenReturn(activity);

		return DaggerArtistDetailComponent
				.builder()
				.artistDetailModule(artistDetailModule)
				.themeDaggerModule(themeDaggerModule)
				.utilsModule(utilsModule)
				.lastFmModule(lasFmModule)
				.build();
	}

	@Override
	public void beforeEach() {
		super.beforeEach();
		lasFmModule = mock(LastFmModule.class, RETURNS_MOCKS);
	}
}
