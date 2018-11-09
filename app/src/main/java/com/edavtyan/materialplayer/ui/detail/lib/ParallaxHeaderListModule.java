package com.edavtyan.materialplayer.ui.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.ColorUtils;
import com.ed.libsutils.utils.DpConverter;
import com.ed.libsutils.utils.ViewUtils;
import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.lib.transition.OutputSharedViews;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;
import com.edavtyan.materialplayer.lib.transition.SharedViewSet;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListModule extends ActivityModule {

	private static final int SCALED_ART_SIZE_DP = 120;

	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info_top) @Nullable TextView portraitTopInfoView;
	@BindView(R.id.info_bottom) @Nullable TextView portraitBottomInfoView;
	@BindView(R.id.info) @Nullable TextView landscapeInfoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.shared_art) ImageView sharedArtView;
	@BindView(R.id.shared_art_exit) @Nullable ImageView sharedArtExitView;
	@BindView(R.id.info_wrapper) LinearLayout infoWrapper;
	@BindView(R.id.main_wrapper) @Nullable LinearLayout mainWrapper;
	@BindView(R.id.list_header) @Nullable FrameLayout header;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.list_background) @Nullable View listBackground;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.click_blocker) @Nullable View clickBlockerView;
	@BindView(R.id.land_list_wrapper) @Nullable FrameLayout landListWrapper;

	private final RecyclerView.OnScrollListener onScrollListener
			= new RecyclerView.OnScrollListener() {
		private int totalScrolled = 0;

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			assert appbarShadow != null;
			assert header != null;
			assert listBackground != null;

			totalScrolled += dy;
			int parallax = (totalScrolled + header.getHeight()) / 2;

			if (totalScrolled < header.getHeight()) {
				listBackground.setTranslationY(-totalScrolled);
			} else {
				listBackground.setTranslationY(-header.getHeight());
			}

			infoWrapper.setTranslationY(-parallax);
			appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(parallax));
		}
	};

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;
	private final SharedTransitionsManager transitionsManager;

	private boolean isExitTransitionRunning;

	public ParallaxHeaderListModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			SharedTransitionsManager transitionsManager) {
		this.activity = activity;
		this.adapter = adapter;
		this.presenter = presenter;
		this.transitionsManager = transitionsManager;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ButterKnife.bind(this, activity);

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			assert header != null; // Removes lint warning
			list.addOnScrollListener(onScrollListener);
			header.post(() -> {
				assert listBackground != null;
				list.setPadding(0, header.getHeight(), 0, 0);
				list.scrollBy(-1000, -1000);
				listBackground.setTranslationY(header.getHeight());
				ViewUtils.setHeight(listBackground, activity);
			});
		}

		assert sharedArtExitView != null;
		transitionsManager.createSharedTransition(OutputSharedViews
				.builder()
				.sharedViewSets(
						SharedViewSet.translating("art", artView, sharedArtView)
									 .exitPortraitView(sharedArtExitView))
				.enterFadingViews(mainWrapper)
				.exitPortraitFadingViews(
						clickBlockerView, listBackground, list, header, appbar)
				.exitLandscapeFadingViews(mainWrapper)
				.build());
		transitionsManager.beginEnterTransition(activity, savedInstanceState);
	}

	@Override
	public void onStart() {
		presenter.onCreate();
	}

	@Override
	public void onStop() {
		presenter.onDestroy();
	}

	@Override
	public void onBackPressed() {
		if (isExitTransitionRunning) activity.finish();
		transitionsManager.beginExitTransition(activity);
		isExitTransitionRunning = true;
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		infoWrapper.setBackgroundColor(colors.getColorPrimary());
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String portraitTopInfo, String portraitBottomInfo, String landscapeInfo) {
		if (WindowUtils.isPortrait(activity)) {
			// Removes lint warnings
			assert portraitTopInfoView != null;
			assert portraitBottomInfoView != null;

			portraitTopInfoView.setText(portraitTopInfo);
			portraitBottomInfoView.setText(portraitBottomInfo);
		} else {
			// Removes lint warnings
			assert landscapeInfoView != null;

			landscapeInfoView.setText(landscapeInfo);
		}
	}

	public void setArt(@Nullable Bitmap art, @DrawableRes int fallback) {
		if (art != null) {
			if (WindowUtils.isPortrait(activity)) {
				assert sharedArtExitView != null;
				int artViewSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
				Bitmap scaledArt = BitmapResizer.resize(art, artViewSize);
				artView.setImageBitmap(scaledArt);
				sharedArtView.setImageBitmap(scaledArt);
				sharedArtExitView.setImageBitmap(scaledArt);
			} else {
				sharedArtView.setImageBitmap(art);
				artView.setImageBitmap(art);
			}
		} else {
			artView.setImageResource(fallback);
			sharedArtView.setImageResource(fallback);
			if (WindowUtils.isPortrait(activity)) {
				assert sharedArtExitView != null;
				sharedArtExitView.setImageResource(fallback);
			}
		}
	}

	public void setTheme(ThemeColors theme) {
		infoWrapper.setBackgroundColor(theme.getColorPrimary());

		if (WindowUtils.isPortrait(activity)) {
			portraitTopInfoView.setTextColor(theme.getTextContrastPrimary());
			portraitBottomInfoView.setTextColor(theme.getTextContrastPrimary());
			listBackground.setBackgroundColor(theme.getBackground());
		} else {
			landscapeInfoView.setTextColor(theme.getTextContrastPrimary());
			landListWrapper.setBackgroundColor(theme.getBackground());
		}
	}
}
