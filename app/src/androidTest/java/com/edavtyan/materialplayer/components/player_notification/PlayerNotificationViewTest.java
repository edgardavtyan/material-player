package com.edavtyan.materialplayer.components.player_notification;

import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;

import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayerNotificationViewTest extends BaseTest {
	private PlayerNotificationMvp.View view;
	private AdvancedRemoteViews remoteViews;
	private TestableNotificationManager manager;

	@Override public void beforeEach() {
		super.beforeEach();
		remoteViews = mock(AdvancedRemoteViews.class);
		manager = mock(TestableNotificationManager.class);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		view = new PlayerNotification(context, remoteViews, manager, builder);
	}

	@Test public void setTitle_callRemoteViews() {
		view.setTitle("title");
		verify(remoteViews).setTextViewText(R.id.title, "title");
	}

	@Test public void setInfo_callRemoteViews() {
		view.setInfo("artist", "album");
		verify(remoteViews).setTextViewText(R.id.info, "artist - album");
	}

	@Ignore
	@Test public void setArt_setArtOnRemoteViewsFromArtProvider() {
	}

	@Test public void isPlaying_true_setPlayPauseIconToPause() {
		view.setIsPlaying(true);
		verify(remoteViews).setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
	}

	@Test public void isPlaying_false_setPlayPauseIconToPlay() {
		view.setIsPlaying(false);
		verify(remoteViews).setImageViewResource(R.id.play_pause, R.drawable.ic_play);
	}

	@Test public void update_notifyViaManager() {
		view.update();
		verify(manager).notify(PlayerNotification.NOTIFICATION_ID, view.getNotification());
	}
}
