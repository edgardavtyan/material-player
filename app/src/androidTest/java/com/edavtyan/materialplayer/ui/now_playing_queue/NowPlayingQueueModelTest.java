package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingQueueModelTest extends BaseTest {
	private NowPlayingQueueModel model;
	private PlayerService.PlayerBinder binder;
	private Player player;
	private ModelServiceModule serviceModule;

	@Override
	public void beforeEach() {
		super.beforeEach();

		player = mock(Player.class);

		PlayerService service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		serviceModule = spy(new ModelServiceModule(context));

		model = new NowPlayingQueueModel(serviceModule);
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindServiceWithCorrectParameters() {
		model.bindService();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(serviceModule), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).hasClass(PlayerService.class);
	}

	@Test
	public void unbind_unbindService() {
		serviceModule.onServiceConnected(null, binder);
		model.bindService();
		model.unbindService();
		verify(context).unbindService(serviceModule);
	}

	@Test
	public void playItemAtPosition_callPlayer() {
		serviceModule.onServiceConnected(null, binder);
		model.playItemAtPosition(7);
		verify(player).playTrackAt(7);
	}

	@Test
	public void removeItemAtPosition_callService() {
		serviceModule.onServiceConnected(null, binder);
		model.removeItemAtPosition(7);
		verify(player).removeTrackAt(7);
	}

	@Test
	public void getTrackAtPosition_serviceConnected_getTrackFromService() {
		Track track = mock(Track.class);
		when(player.getTrackAt(7)).thenReturn(track);

		serviceModule.onServiceConnected(null, binder);

		assertThat(model.getTrackAtPosition(7)).isEqualTo(track);
	}

	@Test
	public void getTrackCount_serviceConnected_getCountFromService() {
		when(player.getTracksCount()).thenReturn(7);
		serviceModule.onServiceConnected(null, binder);
		assertThat(model.getTrackCount()).isEqualTo(7);
		verify(player).getTracksCount();
	}

	@Test
	public void getTrackCount_serviceNotConnected_zero() {
		assertThat(model.getTrackCount()).isZero();
	}
}
