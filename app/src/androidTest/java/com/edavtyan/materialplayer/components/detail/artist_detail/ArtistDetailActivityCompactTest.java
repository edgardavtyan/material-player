package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityCompactTest extends ActivityTest {
	private static ArtistDetailActivityCompact activity;
	private static ArtistDetailPresenter presenter;
	private static Navigator navigator;
	private static ArtistDetailAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(ArtistDetailPresenter.class);
			navigator = mock(Navigator.class);
			adapter = mock(ArtistDetailAdapter.class);

			ArtistDetailFactory factory = mock(ArtistDetailFactory.class);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getNavigator()).thenReturn(navigator);
			when(factory.getAdapter()).thenReturn(adapter);

			app.setArtistDetailFactory(factory);

			activity = spy(startActivity(ArtistDetailActivityCompact.class));
			doNothing().when(activity).baseOnDestroy();
		}
	}

	@Test
	public void onCreate_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onDestroy_callPresenter() {
		activity.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void getLayoutId_returnCorrectId() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_detail_compact);
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
	public void setArtistArt_artIsNotNull_setGivenArt() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		runOnUiThread(() -> activity.setArtistImage(bitmap));
		assertThat(artView).hasImageBitmap(bitmap);
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		activity.gotoAlbumDetail(3);
		verify(navigator).gotoAlbumDetail(3);
	}

	@Test
	public void notifyDataSetChanged_callAdapter() {
		activity.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
