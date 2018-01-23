package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import org.junit.Rule;
import org.junit.Test;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityNormalTest extends BaseArtistDetailActivityTest {

	public static class TestArtistDetailActivityNormal extends ArtistDetailActivityNormal {
		@Override
		protected ArtistDetailComponent getComponent() {
			return createMockComponent(this);
		}
	}

	@Rule
	public final ActivityTestRule<TestArtistDetailActivityNormal> activityRule
			= new ActivityTestRule<>(TestArtistDetailActivityNormal.class, false, false);

	private ArtistDetailActivityNormal activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = activityRule.launchActivity(null);
	}

	@Test
	public void setArtistTitle_setTitleViewText() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> activity.setArtistTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setArtistInfo_setInfoViewTextWithPattern() {
		TextView infoView = (TextView) activity.findViewById(R.id.info);
		runOnUiThread(() -> activity.setArtistInfo(3, 9));
		assertThat(infoView.getText()).isEqualTo("3 Albums \u2022 9 Tracks");
	}

	@Test
	public void setImage_setHeaderImage() {
		Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		runOnUiThread(() -> activity.setArtistImage(image));
		assertThat(activity, R.id.art).hasImageBitmap(image);
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		activity.gotoAlbumDetailNormal(3);
		verify(navigator).gotoAlbumDetailNormal(3);
	}
}
