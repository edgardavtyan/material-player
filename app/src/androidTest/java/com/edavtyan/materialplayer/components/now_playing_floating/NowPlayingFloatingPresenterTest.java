package com.edavtyan.materialplayer.components.now_playing_floating;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingFloatingPresenterTest extends BaseTest {
	private NowPlayingFloatingMvp.Presenter presenter;
	private NowPlayingFloatingMvp.Model model;
	private NowPlayingFloatingMvp.View view;

	@Override
	public void beforeEach() {
		super.beforeEach();

		model = mock(NowPlayingFloatingMvp.Model.class);
		view = mock(NowPlayingFloatingMvp.View.class);
		presenter = new NowPlayingFloatingPresenter(model, view);
	}

	@Test
	public void onCreate_bindModel() {
		presenter.onCreate();

		verify(model).bind();
		verify(model).setOnNewTrackListener(presenter);
		verify(model).setOnServiceConnectedListener(presenter);
	}

	@Test
	public void onDestroy_unbindModel() {
		presenter.onDestroy();
		verify(model).unbind();
	}

	@Test
	public void onServiceConnected_updateViewAndModel() {
		Track track = new Track();
		track.setTitle("title");
		track.setArtistTitle("artist");
		track.setAlbumTitle("album");

		when(model.getNowPlayingTrack()).thenReturn(track);
		when(model.isPlaying()).thenReturn(true);
		when(model.hasData()).thenReturn(true);

		presenter.onServiceConnected();

		verify(view).setTrackTitle("title");
		verify(view).setTrackInfo("artist", "album");
		verify(view).setIsPlaying(true);
		verify(view).setIsVisible(true);
	}

	@Test
	public void onViewClick_gotoNowPlaying() {
		presenter.onViewClick();
		verify(view).gotoNowPlaying();
	}

	@Test
	public void onPlayPauseClick_updateModelAndView() {
		when(model.isPlaying()).thenReturn(true);

		presenter.onPlayPauseClick();

		verify(model).togglePlayPause();
		verify(view).setIsPlaying(true);
	}

	@Test
	public void onNewTrack_hasData_updateView() {
		Track track = new Track();
		track.setTitle("title");
		track.setArtistTitle("artist");
		track.setAlbumTitle("album");

		when(model.getNowPlayingTrack()).thenReturn(track);
		when(model.isPlaying()).thenReturn(true);
		when(model.hasData()).thenReturn(true);

		presenter.onNewTrack();

		verify(view).setTrackTitle("title");
		verify(view).setTrackInfo("artist", "album");
		verify(view).setIsPlaying(true);
		verify(view).setIsVisible(true);
		verify(view).setArt(any());
	}
}
