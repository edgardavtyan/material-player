package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.edavtyan.materialplayer.lib.asertions.IntentAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlaylistModelTest extends BaseTest {
	private PlaylistMvp.Model model;
	private MusicPlayerService.MusicPlayerBinder binder;
	private MusicPlayerService service;

	@Override
	public void beforeEach() {
		super.beforeEach();

		service = mock(MusicPlayerService.class);
		binder = mock(MusicPlayerService.MusicPlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		model = new PlaylistModel(context);
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindServiceWithCorrectParameters() {
		model.bind();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).classEqualTo(MusicPlayerService.class);
	}

	@Test
	public void unbind_unbindService() {
		model.bind();
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test
	public void playItemAtPosition_switchNowPlayingQueueTrackViaService() {
		model.onServiceConnected(null, binder);
		model.playItemAtPosition(7);
		verify(service).switchQueueTrackToPosition(7);
	}

	@Test
	public void removeItemAtPosition_removeItemFromNowPlayingQueueViaService() {
		model.onServiceConnected(null, binder);
		model.removeItemAtPosition(7);
		verify(service).removeQueueTrackAtPosition(7);
	}

	@Test
	public void getTrackAtPosition_serviceConnected_getTrackFromService() {
		Track track = mock(Track.class);
		when(service.getQueueTrackAt(7)).thenReturn(track);

		model.onServiceConnected(null, binder);

		assertThat(model.getTrackAtPosition(7)).isEqualTo(track);
	}

	@Test
	public void getTrackCount_serviceConnected_getCountFromService() {
		when(service.getQueueCount()).thenReturn(7);
		model.onServiceConnected(null, binder);
		assertThat(model.getTrackCount()).isEqualTo(7);
		verify(service).getQueueCount();
	}

	@Test
	public void getTrackCount_serviceNotConnected_zero() {
		assertThat(model.getTrackCount()).isZero();
	}
}
