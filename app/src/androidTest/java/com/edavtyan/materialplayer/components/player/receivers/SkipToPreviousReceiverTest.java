package com.edavtyan.materialplayer.components.player.receivers;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SkipToPreviousReceiverTest extends BaseTest {
	private SkipToPreviousReceiver skipToPreviousReceiver;
	private PlayerMvp.Player player;

	@Override
	public void beforeEach() {
		super.beforeEach();
		player = mock(PlayerMvp.Player.class);
		skipToPreviousReceiver = new SkipToPreviousReceiver(player);
	}

	@Test
	public void onReceive_rewindPlayer() {
		skipToPreviousReceiver.onReceive(context, null);
		player.skipToPrevious();
	}
}
