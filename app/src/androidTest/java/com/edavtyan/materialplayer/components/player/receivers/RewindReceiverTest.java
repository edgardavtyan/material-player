package com.edavtyan.materialplayer.components.player.receivers;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class RewindReceiverTest extends BaseTest {
	private RewindReceiver rewindReceiver;
	private PlayerMvp.Player player;

	@Override
	public void beforeEach() {
		super.beforeEach();
		player = mock(PlayerMvp.Player.class);
		rewindReceiver = new RewindReceiver(player);
	}

	@Test
	public void onReceive_rewindPlayer() {
		rewindReceiver.onReceive(context, null);
		player.skipToPrevious();
	}
}
