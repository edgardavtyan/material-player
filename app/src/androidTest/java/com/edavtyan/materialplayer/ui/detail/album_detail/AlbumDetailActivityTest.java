package com.edavtyan.materialplayer.ui.detail.album_detail;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.transition.SourceSharedViews;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThatBundle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityTest extends BaseAlbumDetailActivityTest {

	public static class TestAlbumDetailActivity extends AlbumDetailActivity {
		@Override
		protected AlbumDetailDIComponent getComponent() {
			return createMockComponent(this);
		}
	}

	@Rule
	public final ActivityTestRule<TestAlbumDetailActivity> activityRule
			= new ActivityTestRule<>(TestAlbumDetailActivity.class, false, false);

	private TestAlbumDetailActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = activityRule.launchActivity(null);
	}

	@Test
	public void set_album_title() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> activity.setAlbumTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void set_album_info_in_portrait_mode() {
		TextView infoTopView = (TextView) activity.findViewById(R.id.info_top);
		TextView infoBottomView = (TextView) activity.findViewById(R.id.info_bottom);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 1, 61123));
		assertThat(infoTopView.getText()).isEqualTo("artist");
		assertThat(infoBottomView.getText()).isEqualTo("1 Track, 1 min.");
	}

	@Test
	public void set_album_info_in_landscape_mode() {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		instrumentation.waitForIdleSync();
		TextView infoView = (TextView) activity.findViewById(R.id.info);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 2, 122234));
		assertThat(infoView.getText()).isEqualTo("artist \u2022 2 Tracks");
	}

	@Test
	public void set_fallback_image_if_art_is_null() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		runOnUiThread(() -> activity.setAlbumImage(null));
		assertThat(artView).hasImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void set_given_image_if_it_is_not_null() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		runOnUiThread(() -> activity.setAlbumImage(bitmap));
		assertThat(artView).hasScaledImageBitmap(bitmap, 120);
	}

	@Test
	public void go_to_now_playing_screen_via_navigator_in_portrait() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		RecyclerView list = (RecyclerView) activity.findViewById(R.id.list);
		View listBackground = activity.findViewById(R.id.list_background);
		SourceSharedViews sourceViews = new SourceSharedViews(
				Pair.create(artView, "art"),
				Pair.create(list, "list"),
				Pair.create(listBackground, "listBackground"));

		runOnUiThread(activity::gotoNowPlaying);

		ArgumentCaptor<Bundle> bundleCaptor = ArgumentCaptor.forClass(Bundle.class);
		verify(navigator).gotoNowPlaying(eq(activity), bundleCaptor.capture());
		assertThatBundle(bundleCaptor.getValue()).isEqualTo(sourceViews.build());
	}
}
