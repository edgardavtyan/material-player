package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityNormalTest extends ActivityTest {
	private static Navigator navigator;
	private static ArtistDetailPresenter presenter;
	private static ArtistDetailAdapter adapter;

	public static class TestArtistDetailActivityNormal extends ArtistDetailActivityNormal {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.navigator = ArtistDetailActivityNormalTest.navigator;
			this.presenter = ArtistDetailActivityNormalTest.presenter;
			this.adapter = ArtistDetailActivityNormalTest.adapter;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected ArtistDetailComponent getComponent() {
			return mock(ArtistDetailComponent.class);
		}
	}

	@Rule
	public final ActivityTestRule<ArtistDetailActivityNormal> activityRule
			= new ActivityTestRule<>(ArtistDetailActivityNormal.class, false, false);

	private ArtistDetailActivityNormal activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		navigator = mock(Navigator.class);
		presenter = mock(ArtistDetailPresenter.class);
		adapter = mock(ArtistDetailAdapter.class);
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
		runOnUiThread(() -> {
			Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
			activity.setArtistImage(image);
			assertThat(activity, R.id.art).hasImageBitmap(image);
		});
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		activity.gotoAlbumDetailNormal(3);
		verify(navigator).gotoAlbumDetailNormal(3);
	}
}
