package com.edavtyan.materialplayer.notification;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerNotificationPresenterTest extends BaseTest {
	private PlayerNotificationPresenter presenter;
	private PlayerNotification view;
	private PlayerNotificationModel model;

	@Override
	public void beforeEach() {
		super.beforeEach();
		model = mock(PlayerNotificationModel.class);
		view = mock(PlayerNotification.class);
		presenter = new PlayerNotificationPresenter(model, view);
	}

	@Test
	public void onCreate_bindModel() {
		presenter.onCreate();
		verify(model).bind();
	}

	@Test
	public void onDestroy_unbindModel() {
		presenter.onDestroy();
		verify(model).unbind();
	}

	@Test
	public void onNewTrack_updateView() {
		Track track = new Track();
		track.setTitle("title");
		track.setArtistTitle("artist");
		track.setAlbumTitle("album");
		track.setPath("path");
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(model.getTrack()).thenReturn(track);
		when(model.isPlaying()).thenReturn(true);
		when(model.getArt()).thenReturn(art);

		presenter.onNewTrack();

		verify(view).setTitle("title");
		verify(view).setInfo("artist", "album");
		verify(view).setArt(art);
		verify(view).setIsPlaying(true);
		verify(view).update();
	}

	@Test
	public void onPlayPause_updateView() {
		when(model.isPlaying()).thenReturn(true);

		presenter.onPlayPause();

		verify(view).setIsPlaying(true);
		verify(view).update();
	}
}
