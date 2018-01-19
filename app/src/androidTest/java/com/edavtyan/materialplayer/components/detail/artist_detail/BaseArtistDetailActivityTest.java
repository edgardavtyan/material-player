package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.detail.BaseDetailActivityTest;
import com.edavtyan.materialplayer.lib.lastfm.LastFmFactory;

import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseArtistDetailActivityTest extends BaseDetailActivityTest {
	protected static LastFmFactory lasFmFactory;

	protected static ArtistDetailComponent createMockComponent(AppCompatActivity activity) {
		ArtistDetailFactory artistDetailFactory = mock(ArtistDetailFactory.class, RETURNS_MOCKS);
		when(artistDetailFactory.provideActivity()).thenReturn(activity);
		when(artistDetailFactory.provideContext()).thenReturn(activity);

		return DaggerArtistDetailComponent
				.builder()
				.artistDetailFactory(artistDetailFactory)
				.themeFactory(themeFactory)
				.utilsFactory(utilsFactory)
				.lastFmFactory(lasFmFactory)
				.build();
	}

	@Override
	public void beforeEach() {
		super.beforeEach();
		lasFmFactory = mock(LastFmFactory.class, RETURNS_MOCKS);
	}
}
