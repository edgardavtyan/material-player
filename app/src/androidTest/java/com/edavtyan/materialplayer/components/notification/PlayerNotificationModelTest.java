package com.edavtyan.materialplayer.components.notification;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationMvp.Model.OnNewTrackListener;
import com.edavtyan.materialplayer.components.notification.PlayerNotificationMvp.Model.OnPlayPauseListener;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static com.edavtyan.materialplayer.testlib.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerNotificationModelTest extends BaseTest {
	private PlayerNotificationModel model;
	private Player player;
	private AlbumArtProvider albumArtProvider;
	private PlayerService.PlayerBinder binder;

	@Override
	public void beforeEach() {
		super.beforeEach();
		context = mock(ContextThemeWrapper.class);
		player = mock(Player.class);
		albumArtProvider = mock(AlbumArtProvider.class);
		model = new PlayerNotificationModel(context, albumArtProvider);

		PlayerService service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);
	}

	@Test
	@SuppressWarnings("WrongConstant")
	public void bind_bindPlayerService() {
		model.bind();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).hasClass(PlayerService.class);
	}

	@Test
	public void unbind_unbindModelFromService() {
		model.onServiceConnected(null, binder);
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test
	public void unbind_removePlayerListeners() {
		model.onServiceConnected(null, binder);
		model.bind();
		model.unbind();
		verify(player).removeOnNewTrackListener(model);
		verify(player).removeOnPlayPauseListener(model);
	}

	@Test
	public void isPlaying_returnFromService() {
		model.onServiceConnected(null, binder);
		when(player.isPlaying()).thenReturn(true);
		assertThat(model.isPlaying()).isTrue();
	}

	@Test
	public void getTrack_returnCurrentTrackFromPlayer() {
		Track track = new Track();
		when(player.getCurrentTrack()).thenReturn(track);
		model.onServiceConnected(null, binder);
		assertThat(model.getTrack()).isEqualTo(track);
	}

	@Test
	public void getArt_returnArtFromArtProvider() {
		Track track = mock(Track.class);
		when(player.getCurrentTrack()).thenReturn(track);
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(albumArtProvider.load(track)).thenReturn(art);
		model.onServiceConnected(null, binder);

		assertThat(model.getArt()).isSameAs(art);
	}

	@Test
	public void onNewTrack_listenerNotSet_notThrowException() {
		assertThatNPENotThrown(model::onNewTrack);
	}

	@Test
	public void onNewTrack_listenerSet_callListener() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onNewTrack();
		verify(listener).onNewTrack();
	}

	@Test
	public void onPlayPause_listenerSet_callListener() {
		OnPlayPauseListener listener = mock(OnPlayPauseListener.class);
		model.setOnPlayPauseListener(listener);
		model.onPlayPause();
		verify(listener).onPlayPause();
	}

	@Test
	public void onPlayPause_listenerNotSet_notThrowException() {
		assertThatNPENotThrown(model::onPlayPause);
	}
}
