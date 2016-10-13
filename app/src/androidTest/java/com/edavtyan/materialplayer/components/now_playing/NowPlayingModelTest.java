package com.edavtyan.materialplayer.components.now_playing;

import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp.Model.OnModelBoundListener;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp.Model.OnNewTrackListener;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp.Model.OnPlayPauseListener;
import com.edavtyan.materialplayer.components.player2.PlayerMvp;
import com.edavtyan.materialplayer.components.player2.PlayerService;
import com.edavtyan.materialplayer.components.player2.RepeatMode;
import com.edavtyan.materialplayer.components.player2.ShuffleMode;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.asertions.IntentAssert;
import com.edavtyan.materialplayer.utils.ArtProvider2;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingModelTest extends BaseTest {
	private NowPlayingModel model;
	private PlayerService.PlayerBinder binder;
	private PlayerMvp.Player player;
	private ArtProvider2 artProvider;

	@Override public void beforeEach() {
		super.beforeEach();

		player = mock(PlayerMvp.Player.class);

		PlayerService service = mock(PlayerService.class);
		when(service.getPlayer()).thenReturn(player);

		binder = mock(PlayerService.PlayerBinder.class);
		when(binder.getService()).thenReturn(service);

		artProvider = mock(ArtProvider2.class);

		runOnUiThread(() -> {
			model = new NowPlayingModel(context, artProvider);
			model.onServiceConnected(null, binder);
		});
	}

	@Test @SuppressWarnings("WrongConstant")
	public void bind_bindServiceWithCorrectIntent() {
		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);

		NowPlayingMvp.Model model = new NowPlayingModel(context, artProvider);
		model.bind();

		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		IntentAssert.assertThat(intentCaptor.getValue()).classEqualTo(PlayerService.class);
	}

	@Test public void unbind_unbindService() {
		model.bind();
		model.unbind();
		verify(context).unbindService(model);
	}

	@Test public void getRepeatMode_returnRepeatModeFromPlayer() {
		RepeatMode repeatMode = RepeatMode.REPEAT_ALL;
		when(player.getRepeatMode()).thenReturn(repeatMode);
		assertThat(model.getRepeatMode()).isSameAs(repeatMode);
	}

	@Test public void toggleRepeatMode_callService() {
		model.toggleRepeatMode();
		verify(player).toggleRepeatMode();
	}

	@Test public void getShuffleMode_returnShuffleModeFromPlayer() {
		ShuffleMode shuffleMode = ShuffleMode.ENABLED;
		when(player.getShuffleMode()).thenReturn(shuffleMode);
		assertThat(model.getShuffleMode()).isSameAs(shuffleMode);
	}

	@Test public void toggleShuffle() {
		model.toggleShuffleMode();
		verify(player).toggleShuffleMode();
	}

	@Test public void isPlaying_returnIsPlayingFromPlayer() {
		when(player.isPlaying()).thenReturn(true);
		assertThat(model.isPlaying()).isTrue();
	}

	@Test public void togglePlayPause_playPausePlayer() {
		model.playPause();
		verify(player).playPause();
	}

	@Test public void getTitle_returnTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setTitle("title");
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getTitle()).isEqualTo("title");
	}

	@Test public void getArtist_returnArtistTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setArtistTitle("artist");
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getArtist()).isEqualTo("artist");
	}

	@Test public void getAlbum_returnAlbumTitleOfNowPlayingTrack() {
		Track track = new Track();
		track.setAlbumTitle("album");
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getAlbum()).isEqualTo("album");
	}

	@Test public void getDuration_returnDurationOfNowPlayingTrack() {
		Track track = new Track();
		track.setDuration(1234);
		when(player.getCurrentTrack()).thenReturn(track);
		assertThat(model.getDuration()).isEqualTo(1234);
	}

	@Test public void getPosition_returnPositionOfNowPlayingTrack() {
		when(player.getPosition()).thenReturn(5678L);
		assertThat(model.getPosition()).isEqualTo(5678);
	}

	@Test public void getTrackArt_returnArtFromArtProvider() {
		Track track = new Track();
		track.setAlbumId(123);
		when(player.getCurrentTrack()).thenReturn(track);

		File artFile = mock(File.class);
		when(artProvider.load(track)).thenReturn(artFile);

		assertThat(model.getArt()).isSameAs(artFile);
	}

	@Test public void seek_setPlayerPosition() {
		model.seek(1357);
		verify(player).setPosition(1357);
	}

	@Test public void rewind_rewindPlayerViaService() {
		model.rewind();
		verify(player).rewind();
	}

	@Test public void fastForward_fastForwardPlayerViaService() {
		model.fastForward();
		verify(player).playNext();
	}

	@Test public void onServiceConnected_callOnModelConnectedListener() {
		OnModelBoundListener listener = mock(OnModelBoundListener.class);
		model.setOnModelBoundListener(listener);
		model.onServiceConnected(null, binder);
		verify(listener).onModelBound();
	}

	@Test public void onNewTrackListener_calledWhenOnNewTrackCalled() {
		OnNewTrackListener listener = mock(OnNewTrackListener.class);
		model.setOnNewTrackListener(listener);
		model.onNewTrack();
		verify(listener).onNewTrack();
	}

	@Test public void onNewTrackListener_notCalledIfNotSet() {
		try {
			model.onNewTrack();
		} catch (NullPointerException e) {
			fail("NullPointerException because onNewTrackListener not set");
		}
	}

	@Test public void onPlayPauseListener_calledWhenOnPlayPauseCalled() {
		OnPlayPauseListener listener = mock(OnPlayPauseListener.class);
		model.setOnPlayPauseListener(listener);
		model.onPlayPause();
		verify(listener).onPlayPause();
	}

	@Test public void onPlayPauseListener_notCalledIfNotSet() {
		try {
			model.onPlayPause();
		} catch (NullPointerException e) {
			fail("NullPointerException because onPlayPauseListener not set");
		}
	}
}
