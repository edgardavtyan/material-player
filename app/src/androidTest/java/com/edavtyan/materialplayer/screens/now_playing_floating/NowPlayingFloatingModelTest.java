package com.edavtyan.materialplayer.screens.now_playing_floating;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.screens.now_playing_floating.NowPlayingFloatingModel.OnNewTrackListener;
import com.edavtyan.materialplayer.screens.now_playing_floating.NowPlayingFloatingModel.OnServiceConnectedListener;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static com.ed.libsutils.assertj.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingFloatingModelTest extends BaseTest {
	private Context context;
	private AlbumArtProvider albumArtProvider;
	private PlayerService.PlayerBinder binder;
	private NowPlayingFloatingModel model;
	private Player player;

	@Override
	public void beforeEach() {
		super.beforeEach();

		context = mock(Context.class);
		albumArtProvider = mock(AlbumArtProvider.class);
		model = new NowPlayingFloatingModel(context, albumArtProvider);

		player = mock(Player.class);

		PlayerService service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindMusicPlayerService() {
		model.bind();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).hasClass(PlayerService.class);
	}

	@Test
	public void unbind_unbindMusicPlayerService() {
		model.onServiceConnected(null, binder);
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test
	public void getNowPlayingTrack_getTrackFromService() {
		Track track = new Track();
		when(player.getCurrentTrack()).thenReturn(track);
		model.onServiceConnected(null, binder);

		assertThat(model.getNowPlayingTrack()).isSameAs(track);
		verify(player).getCurrentTrack();
	}

	@Test
	public void getNowPlayingTrackArt_getArtFromArtProvider() {
		Track track = new Track();
		when(player.getCurrentTrack()).thenReturn(track);
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(albumArtProvider.load(track)).thenReturn(art);
		model.onServiceConnected(null, binder);

		assertThat(model.getNowPlayingTrackArt()).isSameAs(art);
	}

	@Test
	public void isPlaying_getFromService() {
		when(player.isPlaying()).thenReturn(true);
		model.onServiceConnected(null, binder);

		assertThat(model.isPlaying()).isEqualTo(true);
		verify(player).isPlaying();
	}

	@Test
	public void togglePlayPause_playPausePlayer() {
		model.onServiceConnected(null, binder);
		model.togglePlayPause();
		verify(player).playPause();
	}

	@Test
	public void onServiceConnected_setOnPreparedListener() {
		model.onServiceConnected(null, binder);
		verify(player).setOnNewTrackListener(model);
	}

	@Test // For coverage
	public void onServiceDisconnected_notDoAnything() {
		model.onServiceDisconnected(null);
	}

	@Test
	public void onNewTrackListener_newTrackIsPlayed_called() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onServiceConnected(null, binder);

		model.onNewTrack();

		verify(listener).onNewTrack();
	}

	@Test
	public void onNewTrackListener_notSet_notThrowNPE() {
		assertThatNPENotThrown(model::onNewTrack);
	}

	@Test
	public void onServiceConnectedListener_serviceConnected_called() {
		OnServiceConnectedListener listener = mock(OnServiceConnectedListener.class);
		model.setOnServiceConnectedListener(listener);

		model.onServiceConnected(null, binder);

		verify(listener).onServiceConnected();
	}
}
