package com.edavtyan.materialplayer.components.player.receivers;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SkipToNextReceiverTest extends BaseTest {
	private SkipToNextReceiver skipToNextReceiver;
	private PlayerMvp.Player player;

	@Override
	public void beforeEach() {
		super.beforeEach();
		player = mock(PlayerMvp.Player.class);
		skipToNextReceiver = new SkipToNextReceiver(player);
	}

	@Test
	public void onReceive_fastForwardPlayer() {
		skipToNextReceiver.onReceive(context, null);
		player.skipToNext();
	}
}
