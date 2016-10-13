package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingMvp.Model.OnNewTrackListener;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingMvp.Model.OnServiceConnectedListener;
import com.edavtyan.materialplayer.components.player2.PlayerMvp;
import com.edavtyan.materialplayer.components.player2.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.utils.ArtProvider2;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;

import static com.edavtyan.materialplayer.lib.asertions.IntentAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingFloatingModelTest extends BaseTest {
	private Context context;
	private ArtProvider2 artProvider;
	private PlayerService.PlayerBinder binder;
	private NowPlayingFloatingModel model;
	private PlayerMvp.Player player;

	@Override public void beforeEach() {
		super.beforeEach();

		context = mock(Context.class);
		artProvider = mock(ArtProvider2.class);
		model = new NowPlayingFloatingModel(context, artProvider);

		player = mock(PlayerMvp.Player.class);

		PlayerService service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);
	}

	@SuppressWarnings("WrongConstant")
	@Test public void bind_bindMusicPlayerService() {
		model.bind();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).classEqualTo(PlayerService.class);
	}

	@Test public void unbind_unbindMusicPlayerService() {
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test public void getNowPlayingTrack_getTrackFromService() {
		Track track = new Track();
		when(player.getCurrentTrack()).thenReturn(track);
		model.onServiceConnected(null, binder);

		assertThat(model.getNowPlayingTrack()).isSameAs(track);
		verify(player).getCurrentTrack();
	}

	@Test public void getNowPlayingTrackArt_getArtFromArtProvider() {
		Track track = new Track();
		when(player.getCurrentTrack()).thenReturn(track);
		File artFile = mock(File.class);
		when(artFile.getAbsolutePath()).thenReturn("artPath");
		when(artProvider.load(track)).thenReturn(artFile);
		model.onServiceConnected(null, binder);

		assertThat(model.getNowPlayingTrackArtPath()).isSameAs("artPath");
	}

	@Test public void isPlaying_getFromService() {
		when(player.isPlaying()).thenReturn(true);
		model.onServiceConnected(null, binder);

		assertThat(model.isPlaying()).isEqualTo(true);
		verify(player).isPlaying();
	}

	@Test public void togglePlayPause_playPausePlayer() {
		model.onServiceConnected(null, binder);
		model.togglePlayPause();
		verify(player).playPause();
	}

	@Test public void onServiceConnected_setOnPreparedListener() {
		model.onServiceConnected(null, binder);
		verify(player).setOnNewTrackListener(model);
	}

	@Test public void onNewTrackListener_newTrackIsPlayed_called() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onServiceConnected(null, binder);

		model.onNewTrack();

		verify(listener).onNewTrack();
	}

	@Test public void onServiceConnectedListener_serviceConnected_called() {
		OnServiceConnectedListener listener = mock(OnServiceConnectedListener.class);
		model.setOnServiceConnectedListener(listener);

		model.onServiceConnected(null, binder);

		verify(listener).onServiceConnected();
	}
}
