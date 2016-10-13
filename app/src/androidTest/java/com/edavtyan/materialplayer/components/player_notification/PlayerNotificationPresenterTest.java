package com.edavtyan.materialplayer.components.player_notification;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerNotificationPresenterTest extends BaseTest {
	private PlayerNotificationMvp.Presenter presenter;
	private PlayerNotificationMvp.View view;
	private PlayerNotificationMvp.Model model;

	@Override public void beforeEach() {
		super.beforeEach();
		model = mock(PlayerNotificationMvp.Model.class);
		view = mock(PlayerNotificationMvp.View.class);
		presenter = new PlayerNotificationPresenter(model, view);
	}

	@Test public void onCreate_bindModel() {
		presenter.onCreate();
		verify(model).bind();
	}

	@Test public void onDestroy_unbindModel() {
		presenter.onDestroy();
		verify(model).unbind();
	}

	@Test public void onNewTrack_updateView() {
		Track track = new Track();
		track.setTitle("title");
		track.setArtistTitle("artist");
		track.setAlbumTitle("album");
		track.setPath("path");
		when(model.getTrack()).thenReturn(track);
		when(model.isPlaying()).thenReturn(true);
		when(model.getArtPath()).thenReturn("artPath");

		presenter.onNewTrack();

		verify(view).setTitle("title");
		verify(view).setInfo("artist", "album");
		verify(view).setArt("artPath");
		verify(view).setIsPlaying(true);
		verify(view).update();
	}
}
