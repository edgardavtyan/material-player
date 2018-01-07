package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
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
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityNormalTest extends ActivityTest {
	@Rule
	public final ActivityTestRule<ArtistDetailActivityNormal> activityRule
			= new ActivityTestRule<>(ArtistDetailActivityNormal.class, false, false);

	private ArtistDetailActivityNormal activity;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		navigator = mock(Navigator.class);
		ArtistDetailPresenter presenter = mock(ArtistDetailPresenter.class);
		ArtistDetailAdapter adapter = mock(ArtistDetailAdapter.class);

		ArtistDetailFactory factory = mock(ArtistDetailFactory.class);
		when(factory.getPresenter()).thenReturn(presenter);
		when(factory.getAdapter()).thenReturn(adapter);
		when(factory.getNavigator()).thenReturn(navigator);
		app.setArtistDetailFactory(factory);

		activity = startActivity(ArtistDetailActivityNormal.class);
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
		activity.gotoAlbumDetail(3);
		verify(navigator).gotoAlbumDetail(3);
	}
}
