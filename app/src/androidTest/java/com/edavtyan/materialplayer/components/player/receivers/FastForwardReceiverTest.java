package com.edavtyan.materialplayer.components.player.receivers;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class FastForwardReceiverTest extends BaseTest {
	private FastForwardReceiver fastForwardReceiver;
	private PlayerMvp.Player player;

	@Override
	public void beforeEach() {
		super.beforeEach();
		player = mock(PlayerMvp.Player.class);
		fastForwardReceiver = new FastForwardReceiver(player);
	}

	@Test
	public void onReceive_fastForwardPlayer() {
		fastForwardReceiver.onReceive(context, null);
		player.playNext();
	}
}
