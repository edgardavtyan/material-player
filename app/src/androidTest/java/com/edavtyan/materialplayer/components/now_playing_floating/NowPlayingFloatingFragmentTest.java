package com.edavtyan.materialplayer.components.now_playing_floating;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.testlib.tests.FragmentTest;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class NowPlayingFloatingFragmentTest extends FragmentTest<NowPlayingFloatingFragment> {
	private NowPlayingFloatingMvp.Presenter presenter;
	private Navigator navigator;
	private TextView infoView;
	private TextView titleView;
	private ImageView artView;
	private ImageButton playPauseView;
	private LinearLayout infoWrapper;
	private LinearLayout mainWrapper;

	@Override
	public void beforeEach() {
		super.beforeEach();

		initFragment(new NowPlayingFloatingFragment());
		doReturn(context.getResources()).when(activity).getResources();

		presenter = mock(NowPlayingFloatingMvp.Presenter.class);
		navigator = mock(Navigator.class);
		TestableBitmapFactory bitmapFactory = mock(TestableBitmapFactory.class);

		NowPlayingFloatingFactory factory = mock(NowPlayingFloatingFactory.class);
		when(factory.providePresenter()).thenReturn(presenter);
		when(factory.provideNavigator()).thenReturn(navigator);
		when(factory.provideBitmapFactory()).thenReturn(bitmapFactory);
		when(app.getNowPlayingFloatingFactory(any(), any())).thenReturn(factory);

		infoView = spy(new TextView(context));
		titleView = spy(new TextView(context));
		artView = spy(new ImageView(context));
		playPauseView = spy(new ImageButton(context));
		infoWrapper = spy(new LinearLayout(context));
		mainWrapper = spy(new LinearLayout(context));

		when(fragmentView.findViewById(R.id.info)).thenReturn(infoView);
		when(fragmentView.findViewById(R.id.title)).thenReturn(titleView);
		when(fragmentView.findViewById(R.id.art)).thenReturn(artView);
		when(fragmentView.findViewById(R.id.playPause)).thenReturn(playPauseView);
		when(fragmentView.findViewById(R.id.infoWrapper)).thenReturn(infoWrapper);
		when(fragmentView.findViewById(R.id.container)).thenReturn(mainWrapper);
	}

	@Test
	public void onStart_callPresenter() {
		fragment.onCreate(null);
		fragment.onStart();
		verify(presenter).onCreate();
	}

	@Test
	public void onStop_callPresenter() {
		fragment.onCreate(null);
		fragment.onStop();
		verify(presenter).onDestroy();
	}

	@Test
	public void onCreateView_inflateViewWithCorrectParameters() {
		LinearLayout container = new LinearLayout(context);

		fragment.onCreate(null);
		fragment.onCreateView(inflater, container, null);

		verify(inflater).inflate(R.layout.fragment_nowplaying_floating, container, false);
	}

	@SuppressLint("SetTextI18n")
	@Test
	public void setTrackTitle_setTitleViewText() {
		fragment.onCreateView(inflater, null, null);
		fragment.setTrackTitle("title");

		verify(titleView).setText("title");
	}

	@SuppressLint("SetTextI18n")
	@Test
	public void setTrackInfo_setInfoViewTextWithPattern() {
		fragment.onCreateView(inflater, null, null);
		fragment.setTrackInfo("artist", "album");

		verify(infoView).setText("artist - album");
	}

	@Test
	public void setArt_bitmapIsNotNull_setBitmap() {
		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);

		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
		fragment.setArt(art);

		verify(artView).setImageBitmap(isA(Bitmap.class));
	}

	@Test
	public void setArt_bitmapIsNull_setFallbackImage() {
		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);
		fragment.setArt(null);
		verify(artView).setImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void setIsPlaying_true_setIconToPause() {
		fragment.onCreateView(inflater, null, null);
		fragment.setIsPlaying(true);
		verify(playPauseView).setImageResource(R.drawable.ic_pause);
	}

	@Test
	public void setIsPlaying_false_setIconToPlay() {
		ImageButton playPauseButton = mock(ImageButton.class);
		when(fragmentView.findViewById(R.id.playPause)).thenReturn(playPauseButton);

		fragment.onCreateView(inflater, null, null);
		fragment.setIsPlaying(false);

		verify(playPauseButton).setImageResource(R.drawable.ic_play);
	}

	@Test
	public void setIsVisible_true_setVisibilityToVisible() {
		fragment.onCreateView(inflater, null, null);
		fragment.setIsVisible(true);
		verify(mainWrapper).setVisibility(View.VISIBLE);
	}

	@Test
	public void onClickListener_artClicked_called() {
		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);
		artView.setId(R.id.art);
		artView.performClick();

		verify(presenter).onViewClick();
	}

	@Test
	public void onClickListener_infoWrapperClicked_called() {
		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);
		infoWrapper.setId(R.id.infoWrapper);
		infoWrapper.performClick();

		verify(presenter).onViewClick();
	}

	// Mostly for coverage only
	@Test
	public void onClickListener_otherViewClicked_notCalled() {
		View view = mock(View.class);
		when(view.getId()).thenReturn(-1);
		fragment.onClick(view);
		verifyZeroInteractions(presenter);
	}

	@Test
	public void onPlayPauseClick_playPauseClicked_called() {
		fragment.onCreate(null);
		fragment.onCreateView(inflater, null, null);
		playPauseView.setId(R.id.playPause);
		playPauseView.performClick();

		verify(presenter).onPlayPauseClick();
	}

	@Test
	public void gotoNowPlaying_callNavigator() {
		fragment.onCreate(null);
		fragment.gotoNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
