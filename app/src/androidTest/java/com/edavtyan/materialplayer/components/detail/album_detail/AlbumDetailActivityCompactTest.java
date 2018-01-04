package com.edavtyan.materialplayer.components.detail.album_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Ignore;
import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityCompactTest extends ActivityTest {
	private static AlbumDetailActivityCompact activity;
	private static Navigator navigator;
	private static AlbumDetailAdapter adapter;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			AlbumDetailPresenter presenter = mock(AlbumDetailPresenter.class);
			navigator = mock(Navigator.class);
			adapter = mock(AlbumDetailAdapter.class);

			AlbumDetailFactory factory = mock(AlbumDetailFactory.class);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getNavigator()).thenReturn(navigator);
			when(factory.getAdapter()).thenReturn(adapter);

			app.setAlbumDetailFactory(factory);

			activity = spy(startActivity(AlbumDetailActivityCompact.class));
			doNothing().when(activity).baseOnDestroy();
		}
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
	@Ignore
	public void set_album_info_in_landscape_mode() {
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
		assertThat(artView).hasImageBitmap(bitmap);
	}

	@Test
	public void go_to_now_playing_screen_via_navigator() {
		activity.gotoNowPlaying();
		verify(navigator).gotoNowPlaying();
	}

	@Test
	public void notify_adapter_data_changed() {
		activity.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}
}
