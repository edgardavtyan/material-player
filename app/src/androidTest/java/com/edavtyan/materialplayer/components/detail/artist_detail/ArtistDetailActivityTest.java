package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class ArtistDetailActivityTest extends ActivityTest {
	private static ArtistDetailActivity activity;
	private static ArtistDetailMvp.Presenter presenter;
	private static ArtistDetailAdapter adapter;
	private static Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(ArtistDetailMvp.Presenter.class);
			adapter = mock(ArtistDetailAdapter.class);
			navigator = mock(Navigator.class);

			ArtistDetailFactory factory = mock(ArtistDetailFactory.class);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getAdapter()).thenReturn(adapter);
			when(factory.getNavigator()).thenReturn(navigator);

			app.setArtistDetailFactory(factory);

			activity = spy(startActivity(ArtistDetailActivity.class));
			doNothing().when(activity).baseOnStop();
		} else {
			reset(adapter, presenter, navigator);
		}
	}

	@Test
	public void onCreate_initList() {
		RecyclerView list = activity.findView(R.id.list);
		assertThat(list.getLayoutManager()).isOfAnyClassIn(LinearLayoutManager.class);
		assertThat(list.getAdapter()).isEqualTo(adapter);
	}

	@Test
	public void onStart_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onStop_callPresenter() {
		instrumentation.callActivityOnStop(activity);
		verify(presenter).onDestroy();
	}

	@Test
	public void setArtistTitle_setTitleViewText() {
		TextView titleView = activity.findView(R.id.title);
		runOnUiThread(() -> activity.setArtistTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setArtistInfo_setInfoViewTextWithPattern() {
		TextView infoView = activity.findView(R.id.info);
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
	public void notifyDataSetChanged_callAdapter() {
		activity.notifyDataSetChanged();
		verify(adapter).notifyDataSetChangedNonFinal();
	}

	@Test
	public void gotoAlbumDetail_callNavigator() {
		activity.gotoAlbumDetail(3);
		verify(navigator).gotoAlbumDetail(3);
	}
}
