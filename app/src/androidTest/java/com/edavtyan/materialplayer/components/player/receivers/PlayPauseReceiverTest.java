package com.edavtyan.materialplayer.components.player.receivers;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class PlayPauseReceiverTest extends BaseTest {
	private PlayPauseReceiver playPauseReceiver;
	private PlayerMvp.Player player;

	@Override public void beforeEach() {
		super.beforeEach();
		player = mock(PlayerMvp.Player.class);
		playPauseReceiver = new PlayPauseReceiver(player);
	}

	@Test public void onReceive_playPausePlayer() {
		playPauseReceiver.onReceive(context, null);
		player.playPause();
	}
}
