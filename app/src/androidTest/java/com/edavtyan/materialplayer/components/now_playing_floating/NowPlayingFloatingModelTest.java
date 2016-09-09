package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingMvp.Model.OnNewTrackListener;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingMvp.Model.OnServiceConnectedListener;
import com.edavtyan.materialplayer.components.player.MusicPlayer;
import com.edavtyan.materialplayer.components.player.NowPlayingQueue;
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

public class NowPlayingFloatingModelTest extends BaseTest {
	private Context context;
	private MusicPlayerService service;
	private MusicPlayerBinder binder;
	private NowPlayingFloatingModel model;
	private NowPlayingQueue queue;
	private MusicPlayer player;

	@Override
	public void beforeEach() {
		super.beforeEach();

		context = mock(Context.class);
		model = new NowPlayingFloatingModel(context);

		service = mock(MusicPlayerService.class);
		binder = mock(MusicPlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		queue = mock(NowPlayingQueue.class);
		player = mock(MusicPlayer.class);

		when(service.getPlayer()).thenReturn(player);
		when(service.getQueue()).thenReturn(queue);
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindMusicPlayerService() {
		model.bind();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).classEqualTo(MusicPlayerService.class);
	}

	@Test
	public void unbind_unbindMusicPlayerService() {
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test
	public void getNowPlayingTrack_getTrackFromService() {
		Track track = new Track();
		when(queue.getCurrentTrack()).thenReturn(track);
		model.onServiceConnected(null, binder);

		assertThat(model.getNowPlayingTrack()).isSameAs(track);
		verify(queue).getCurrentTrack();
	}

	@Test
	public void isPlaying_getFromService() {
		when(player.isPlaying()).thenReturn(true);
		model.onServiceConnected(null, binder);

		assertThat(model.isPlaying()).isEqualTo(true);
		verify(player).isPlaying();
	}

	@Test
	public void togglePlayPause_isPlaying_pause() {
		when(player.isPlaying()).thenReturn(true);
		model.onServiceConnected(null, binder);

		model.togglePlayPause();

		verify(player).pause();
	}

	@Test
	public void togglePlayPause_notIsPlaying_resume() {
		when(player.isPlaying()).thenReturn(false);
		model.onServiceConnected(null, binder);

		model.togglePlayPause();

		verify(player).resume();
	}

	@Test
	public void onServiceConnected_setOnPreparedListener() {
		model.onServiceConnected(null, binder);
		verify(player).setOnPreparedListener(model);
	}

	@Test
	public void onNewTrackListener_newTrackIsPlayed_called() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onServiceConnected(null, binder);

		model.onPrepared();

		verify(listener).onNewTrack();
	}

	@Test
	public void onServiceConnectedListener_serviceConnected_called() {
		OnServiceConnectedListener listener = mock(OnServiceConnectedListener.class);
		model.setOnServiceConnectedListener(listener);

		model.onServiceConnected(null, binder);

		verify(listener).onServiceConnected();
	}
}
