package com.edavtyan.materialplayer.components.player_notification;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationMvp.Model.OnNewTrackListener;
import com.edavtyan.materialplayer.components.player_notification.PlayerNotificationMvp.Model.OnPlayPauseListener;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.asertions.IntentAssert;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerNotificationModelTest extends BaseTest {
	private PlayerNotificationModel model;
	private PlayerMvp.Player player;
	private AlbumArtProvider albumArtProvider;
	private PlayerService.PlayerBinder binder;
	private Track track;

	@Override public void beforeEach() {
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

	@SuppressWarnings("WrongConstant")
	@Test public void bind_bindPlayerService() {
		model.bind();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		IntentAssert.assertThat(intentCaptor.getValue()).classEqualTo(PlayerService.class);
	}

	@Test public void unbind_unbindModelFromService() {
		model.onServiceConnected(null, binder);
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test public void isPlaying_returnFromService() {
		model.onServiceConnected(null, binder);
		when(player.isPlaying()).thenReturn(true);
		assertThat(model.isPlaying()).isTrue();
	}

	@Test public void getTrack_returnCurrentTrackFromPlayer() {
		Track track = new Track();
		when(player.getCurrentTrack()).thenReturn(track);
		model.onServiceConnected(null, binder);
		assertThat(model.getTrack()).isEqualTo(track);
	}

	@Test public void getArt_returnArtFromArtProvider() {
		Track track = mock(Track.class);
		when(player.getCurrentTrack()).thenReturn(track);
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		when(albumArtProvider.load(track)).thenReturn(art);
		model.onServiceConnected(null, binder);

		assertThat(model.getArt()).isSameAs(art);
	}

	@Test public void onNewTrack_listenerNotSet_notThrowException() {
		try {
			model.onNewTrack();
		} catch (Exception e) {
			fail("onNewTrack when not set should not throw exception");
		}
	}

	@Test public void onNewTrack_listenerSet_callListener() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onNewTrack();
		verify(listener).onNewTrack();
	}

	@Test public void onPlayPause_listenerSet_callListener() {
		OnPlayPauseListener listener = mock(OnPlayPauseListener.class);
		model.setOnPlayPauseListener(listener);
		model.onPlayPause();
		verify(listener).onPlayPause();
	}

	@Test public void onPlayPause_listenerNotSet_notThrowException() {
		try {
			model.onPlayPause();
		} catch (NullPointerException e) {
			fail("Expected onPlayPause to not throw NullPointerException"
				 + "event if OnPlayPauseListener not set");
		}
	}
}
