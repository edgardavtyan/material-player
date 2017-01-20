package com.edavtyan.materialplayer.components.now_playing_floating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest2;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class NowPlayingFloatingFragmentTest extends FragmentTest2 {
	private static NowPlayingFloatingFragment fragment;
	private static NowPlayingFloatingMvp.Presenter presenter;
	private static Navigator navigator;
	private static LayoutInflater inflater;
	private static TextView infoView;
	private static TextView titleView;
	private static ImageView artView;
	private static ImageButton playPauseView;
	private static LinearLayout infoWrapper;
	private static LinearLayout mainWrapper;

	@Override
	@SuppressWarnings("ConstantConditions")
	public void beforeEach() {
		super.beforeEach();

		if (fragment == null) {
			presenter = mock(NowPlayingFloatingMvp.Presenter.class);
			navigator = mock(Navigator.class);

			NowPlayingFloatingFactory factory = mock(NowPlayingFloatingFactory.class);
			when(factory.getPresenter()).thenReturn(presenter);
			when(factory.getNavigator()).thenReturn(navigator);

			app.setNowPlayingFloatingFactory(factory);

			inflater = spy(LayoutInflater.from(context));
			when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);

			fragment = spy(new NowPlayingFloatingFragment());
			doReturn(context).when(fragment).getContext();
			initFragment(fragment);
		} else {
			reset(presenter, navigator);
		}

		infoView = (TextView) fragment.getView().findViewById(R.id.info);
		titleView = (TextView) fragment.getView().findViewById(R.id.title);
		artView = (ImageView) fragment.getView().findViewById(R.id.art);
		playPauseView = (ImageButton) fragment.getView().findViewById(R.id.playPause);
		infoWrapper = (LinearLayout) fragment.getView().findViewById(R.id.infoWrapper);
		mainWrapper = (LinearLayout) fragment.getView().findViewById(R.id.container);
	}

	@Test
	public void onStart_callPresenter() {
		fragment.onStart();
		verify(presenter).onCreate();
	}

	@Test
	public void onStop_callPresenter() {
		fragment.onStop();
		verify(presenter).onDestroy();
	}

	@Test
	public void onCreateView_inflateViewWithCorrectParameters() {
		verify(inflater).inflate(
				eq(R.layout.fragment_nowplaying_floating),
				notNull(ViewGroup.class),
				eq(false));
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
		assertThat(infoView.getText()).isEqualTo("artist - album");
	}

	@Test
	public void setArt_bitmapIsNotNull_setBitmap() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
		runOnUiThread(() -> fragment.setArt(art));
		assertThat(artView).hasImageBitmap(art);
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

	@Test // Mostly for coverage only
	public void onClickListener_otherViewClicked_notCalled() {
		View view = mock(View.class);
		when(view.getId()).thenReturn(-1);
		runOnUiThread(() -> fragment.onClick(view));
	}

	@Test
	public void onPlayPauseClick_playPauseClicked_called() {
		runOnUiThread(playPauseView::performClick);
		verify(presenter).onPlayPauseClick();
	}

	@Test
	public void gotoNowPlaying_callNavigator() {
		fragment.gotoNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
