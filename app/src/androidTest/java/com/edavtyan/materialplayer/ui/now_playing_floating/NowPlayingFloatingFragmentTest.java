package com.edavtyan.materialplayer.ui.now_playing_floating;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.AppDIComponent;
import com.edavtyan.materialplayer.AppDIModule;
import com.edavtyan.materialplayer.DaggerAppDIComponent;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.utils.UtilsDIModule;

import org.junit.Test;

import static com.ed.libsutils.assertj.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class NowPlayingFloatingFragmentTest extends FragmentTest {
	private static NowPlayingFloatingDIComponent component;

	public static class TestNowPlayingFloatingFragment extends NowPlayingFloatingFragment {
		@Override
		protected NowPlayingFloatingDIComponent getComponent() {
			return component;
		}
	}

	private NowPlayingFloatingPresenter presenter;
	private Navigator navigator;
	private TestNowPlayingFloatingFragment fragment;

	private TextView infoView;
	private TextView titleView;
	private ImageView artView;
	private ImageButton playPauseView;
	private LinearLayout infoWrapper;
	private LinearLayout mainWrapper;

	@Override
	@SuppressWarnings("ConstantConditions")
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(NowPlayingFloatingPresenter.class);
		NowPlayingFloatingDIModule mainModule = mock(NowPlayingFloatingDIModule.class, RETURNS_MOCKS);
		when(mainModule.providePresenter(any(), any())).thenReturn(presenter);

		navigator = mock(Navigator.class);
		UtilsDIModule utilsFactory = mock(UtilsDIModule.class, RETURNS_MOCKS);
		when(utilsFactory.provideNavigator(any())).thenReturn(navigator);

		AppDIModule appFactory = mock(AppDIModule.class, RETURNS_MOCKS);
		when(appFactory.provideContext()).thenReturn(context);

		AppDIComponent appComponent = DaggerAppDIComponent
				.builder()
				.appDIModule(appFactory)
				.utilsDIModule(utilsFactory)
				.build();

		component = DaggerNowPlayingFloatingDIComponent
				.builder()
				.appDIComponent(appComponent)
				.nowPlayingFloatingDIModule(mainModule)
				.build();

		fragment = new TestNowPlayingFloatingFragment();
		initFragment(fragment);

		infoView = (TextView) fragment.getView().findViewById(R.id.info);
		titleView = (TextView) fragment.getView().findViewById(R.id.title);
		artView = (ImageView) fragment.getView().findViewById(R.id.art);
		playPauseView = (ImageButton) fragment.getView().findViewById(R.id.play_pause);
		infoWrapper = (LinearLayout) fragment.getView().findViewById(R.id.info_wrapper);
		mainWrapper = (LinearLayout) fragment.getView().findViewById(R.id.container);
	}

	@Test
	public void onStart_callPresenter() {
		verify(presenter).onCreate();
	}

	@Test
	public void onStop_callPresenter() {
		fragment.onStop();
		fragment = null; // force recreation of fragment
		verify(presenter).onDestroy();
	}

	@Test
	public void getLayoutId_returnCorrectId() {
		assertThat(fragment.getLayoutId()).isEqualTo(R.layout.fragment_nowplaying_floating);
	}

	@Test
	@SuppressLint("SetTextI18n")
	public void setTrackTitle_setTitleViewText() {
		runOnUiThread(() -> fragment.setTrackTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@SuppressLint("SetTextI18n")
	@Test
	public void setTrackInfo_setInfoViewTextWithPattern() {
		runOnUiThread(() -> fragment.setTrackInfo("artist", "album"));
		assertThat(infoView.getText()).isEqualTo("artist \u2022 album");
	}

	@Test
	public void setArt_bitmapIsNotNull_setBitmap() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
		runOnUiThread(() -> fragment.setArt(art));
		assertThat(artView).hasScaledImageBitmap(art, 44);
	}

	@Test
	public void setArt_bitmapIsNull_setFallbackImage() {
		runOnUiThread(() -> fragment.setArt(null));
		assertThat(artView).hasImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void setIsPlaying_true_setIconToPause() {
		runOnUiThread(() -> fragment.setIsPlaying(true));
		assertThat(playPauseView).hasImageResource(R.drawable.ic_pause);
	}

	@Test
	public void setIsPlaying_false_setIconToPlay() {
		runOnUiThread(() -> fragment.setIsPlaying(false));
		assertThat(playPauseView).hasImageResource(R.drawable.ic_play);
	}

	@Test
	public void setIsVisible_true_setVisibilityToVisible() {
		runOnUiThread(() -> fragment.setIsVisible(true));
		assertThat(mainWrapper.getVisibility()).isEqualTo(View.VISIBLE);
	}

	@Test
	public void onClickListener_artClicked_called() {
		runOnUiThread(artView::performClick);
		verify(presenter).onViewClick();
	}

	@Test
	public void onClickListener_infoWrapperClicked_called() {
		runOnUiThread(infoWrapper::performClick);
		verify(presenter).onViewClick();
	}

	@Test
	public void onPlayPauseClick_playPauseClicked_called() {
		runOnUiThread(playPauseView::performClick);
		verify(presenter).onPlayPauseClick();
	}

	@Test
	public void gotoNowPlaying_callNavigator() {
		fragment.gotoNowPlaying();
		verify(navigator).gotoNowPlaying(getActivity(), new Bundle());
	}
}
