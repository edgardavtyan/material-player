package com.edavtyan.materialplayer.components.player_notification;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;
import com.edavtyan.materialplayer.utils.PendingIntents;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerNotificationViewTest extends BaseTest {
	private AdvancedRemoteViews remoteViews;
	private AdvancedRemoteViews bigRemoteViews;
	private TestableNotificationManager manager;
	private NotificationCompat.Builder builder;
	private PendingIntents pendingIntents;
	private PlayerNotificationMvp.View view;

	@Override public void beforeEach() {
		super.beforeEach();
		remoteViews = mock(AdvancedRemoteViews.class);
		bigRemoteViews = mock(AdvancedRemoteViews.class);
		manager = mock(TestableNotificationManager.class);
		builder = spy(new NotificationCompat.Builder(context));
		pendingIntents = mock(PendingIntents.class);
		view = new PlayerNotification(
				context, remoteViews, bigRemoteViews, manager,
				builder, pendingIntents);
	}

	@Test public void constructor_buildNotificationWithCorrectValues() {
		PendingIntent contentIntent = pendingIntents.getActivity(MainActivity.class);
		when(pendingIntents.getActivity(MainActivity.class)).thenReturn(contentIntent);
		reset(builder);

		view = new PlayerNotification(
				context, remoteViews, bigRemoteViews, manager,
				builder, pendingIntents);

		verify(builder).setSmallIcon(R.drawable.ic_status);
		verify(builder).setContentIntent(contentIntent);
		verify(builder).setContent(remoteViews);
	}

	@Test public void constructor_setClickListeners() {
		verify(remoteViews).setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		verify(remoteViews).setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		verify(remoteViews).setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);
	}

	@Test public void setTitle_callRemoteViews() {
		view.setTitle("title");
		verify(remoteViews).setTextViewText(R.id.title, "title");
	}

	@Test public void setInfo_callRemoteViews() {
		view.setInfo("artist", "album");
		verify(remoteViews).setTextViewText(R.id.info, "artist - album");
	}

	@Test public void setArt_artExists_setArtOnRemoteViewsFromArtProvider() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
		view.setArt(art);
		verify(remoteViews).setImageViewBitmap(R.id.art, art);
	}

	@Test public void setArt_artNotExists_setFallbackImage() {
		view.setArt(null);
		verify(remoteViews).setImageViewResource(R.id.art, R.drawable.fallback_cover_listitem);
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
