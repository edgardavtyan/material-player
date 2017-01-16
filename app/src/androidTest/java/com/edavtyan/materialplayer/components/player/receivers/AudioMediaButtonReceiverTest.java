package com.edavtyan.materialplayer.components.player.receivers;

import android.content.Intent;
import android.view.KeyEvent;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AudioMediaButtonReceiverTest extends BaseTest {
	private AudioMediaButtonReceiver receiver;
	private PlayerMvp.Player player;

	@Override
	public void beforeEach() {
		super.beforeEach();
		player = mock(PlayerMvp.Player.class);
		receiver = new AudioMediaButtonReceiver(player);
	}

	@Test
	public void onReceive_playPressed_playPlayer() {
		callOnReceiveWithKeyCode(KeyEvent.KEYCODE_MEDIA_PLAY);
		verify(player).play();
	}

	@Test
	public void onReceive_pausePressed_pausePlayer() {
		callOnReceiveWithKeyCode(KeyEvent.KEYCODE_MEDIA_PAUSE);
		verify(player).pause();
	}

	@Test
	public void onReceive_skipNextPressed_skipPlayerToNext() {
		callOnReceiveWithKeyCode(KeyEvent.KEYCODE_MEDIA_NEXT);
		verify(player).skipToNext();
	}

	@Test
	public void onReceive_skipPreviousPressed_skipPlayerToPrevious() {
		callOnReceiveWithKeyCode(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
		verify(player).skipToPrevious();
	}

	private void callOnReceiveWithKeyCode(int keyCode) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_BUTTON);
		intent.putExtra(Intent.EXTRA_KEY_EVENT, keyCode);
		receiver.onReceive(context, intent);
	}
}
