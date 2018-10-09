package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.transition.SourceSharedViews;

import org.junit.Rule;
import org.junit.Test;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityTest extends BaseArtistDetailActivityTest {
	public static class TestArtistDetailActivity extends ArtistDetailActivity {
		@Override
		protected ArtistDetailDIComponent getComponent() {
			return createMockComponent(this);
		}
	}

	@Rule
	public final ActivityTestRule<TestArtistDetailActivity> activityRule
			= new ActivityTestRule<>(TestArtistDetailActivity.class, false, false);

	private ArtistDetailActivity activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = activityRule.launchActivity(null);
	}

	@Test
	public void setArtistTitle_setTitleTextViewText() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> activity.setArtistTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setArtistInfo_setTopAndBottomInfoWithPattern() {
		TextView infoTopView = (TextView) activity.findViewById(R.id.info_top);
		TextView infoBottomView = (TextView) activity.findViewById(R.id.info_bottom);
		runOnUiThread(() -> activity.setArtistInfo(1, 2));
		assertThat(infoTopView.getText()).isEqualTo("1 Album");
		assertThat(infoBottomView.getText()).isEqualTo("2 Tracks");
	}

	@Test
	public void setArtistArt_artIsNull_setFallbackImage() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		runOnUiThread(() -> activity.setArtistImage(null));
		assertThat(artView).hasImageResource(R.drawable.fallback_artist);
	}

	@Test
	public void setArtistArt_artIsNotNull_setScaledArt() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		runOnUiThread(() -> activity.setArtistImage(bitmap));
		assertThat(artView).hasScaledImageBitmap(bitmap, 120);
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		Bundle bundle = new Bundle();
		SourceSharedViews sharedViews = mock(SourceSharedViews.class);
		when(sharedViews.build()).thenReturn(bundle);
		activity.gotoAlbumDetail(3, sharedViews);
		verify(navigator).gotoAlbumDetail(activity, 3, bundle);
	}
}
