package com.edavtyan.materialplayer.components;

import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailActivity;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingActivity;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.edavtyan.materialplayer.lib.asertions.IntentAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NavigatorTest extends BaseTest {
	private Navigator navigator;
	private ArgumentCaptor<Intent> intentCaptor;

	@Override
	public void beforeEach() {
		super.beforeEach();
		context = mock(ContextThemeWrapper.class);
		navigator = new Navigator(context);
		intentCaptor = ArgumentCaptor.forClass(Intent.class);
	}

	@Test
	public void gotoArtistDetail_startActivityWithCorrectParameters() {
		navigator.gotoArtistDetail("title");

		verify(context).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(ArtistDetailActivity.class)
				.hasExtra(ArtistDetailActivity.EXTRA_ARTIST_TITLE, "title");
	}

	@Test
	public void gotoAlbumDetail_startAlbumDetailActivityWithCorrectParameters() {
		navigator.gotoAlbumDetail(7);

		verify(context).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(AlbumDetailActivity.class)
				.hasExtra(AlbumDetailActivity.EXTRA_ALBUM_ID, "7");
	}

	@Test
	public void gotoNowPlaying_startNowPlayingActivityWithCorrectParameters() {
		navigator.gotoNowPlaying();

		verify(context).startActivity(intentCaptor.capture());

		assertThat(intentCaptor.getValue())
				.classEqualTo(NowPlayingActivity.class);
	}
}
