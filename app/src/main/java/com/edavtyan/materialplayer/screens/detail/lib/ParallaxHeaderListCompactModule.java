package com.edavtyan.materialplayer.screens.detail.lib;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.ed.libsutils.utils.BitmapResizer;
import com.ed.libsutils.utils.ColorUtils;
import com.ed.libsutils.utils.DpConverter;
import com.ed.libsutils.utils.ViewUtils;
import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.CurrentSharedViews;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListCompactModule extends ActivityModule {

	public static final String SHARED_ART_X = "shared_art_x";
	public static final String SHARED_ART_Y = "shared_art_y";
	public static final String SHARED_ART_WIDTH = "shared_art_width";
	public static final String SHARED_ART_HEIGHT = "shared_art_height";

	private static final int SCALED_ART_SIZE_DP = 120;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info_top) @Nullable TextView portraitTopInfoView;
	@BindView(R.id.info_bottom) @Nullable TextView portraitBottomInfoView;
	@BindView(R.id.info) @Nullable TextView landscapeInfoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.shared_art) ImageView sharedArtView;
	@BindView(R.id.shared_art_exit) @Nullable ImageView sharedArtExitView;
	@BindView(R.id.info_wrapper) LinearLayout infoWrapper;
	@BindView(R.id.main_wrapper) @Nullable LinearLayout mainWrapper;
	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.list_background) @Nullable View listBackground;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.click_blocker) @Nullable View clickBlockerView;

	private final RecyclerView.OnScrollListener onScrollListener
			= new RecyclerView.OnScrollListener() {
		private int totalScrolled = 0;

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			assert appbarShadow != null; // Removes lint warning

			totalScrolled += dy;
			int parallax = (totalScrolled + header.getHeight()) / 2;

			listBackground.setTranslationY(-totalScrolled);
			infoWrapper.setTranslationY(-parallax);
			appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(parallax));
		}
	};

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;
	private final CurrentSharedViews currentSharedViews;

	private ParallaxHeaderListCompactIntent intent;
	private boolean isExitTransitionRunning;

	public ParallaxHeaderListCompactModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			CurrentSharedViews currentSharedViews) {
		this.activity = activity;
		this.adapter = adapter;
		this.presenter = presenter;
		this.currentSharedViews = currentSharedViews;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ButterKnife.bind(this, activity);
		intent = new ParallaxHeaderListCompactIntent(activity.getIntent());

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			assert header != null; // Removes lint warning
			list.addOnScrollListener(onScrollListener);
			header.post(() -> {
				list.setPadding(0, header.getHeight(), 0, 0);
				list.scrollBy(-1000, -1000);
				listBackground.setTranslationY(header.getHeight());
				ViewUtils.setHeight(listBackground, activity);
			});
		}

		if (savedInstanceState == null) {
			beginEnterTransition();
		}
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
		beginExitTransition();
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

	public void setArt(Bitmap art, @DrawableRes int fallback) {
		if (art != null) {
			if (WindowUtils.isPortrait(activity)) {
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
				sharedArtExitView.setImageResource(fallback);
			}
		}
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	private void beginEnterTransition() {
		sharedArtView.setVisibility(View.VISIBLE);
		artView.setVisibility(View.INVISIBLE);

		mainWrapper.setAlpha(0);
		mainWrapper.animate().alpha(1);

		sharedArtView.post(() -> {
			int[] sharedArtViewLocation = ViewUtils.getLocationOnScreen(sharedArtView);
			int[] artViewLocation = ViewUtils.getLocationOnScreen(artView);
			float startXDelta = intent.getSharedArtX() - sharedArtViewLocation[0];
			float startYDelta = intent.getSharedArtY() - sharedArtViewLocation[1];
			float startScaleX = (float) intent.getSharedArtWidth() / artView.getWidth();
			float startScaleY = (float) intent.getSharedArtHeight() / artView.getHeight();
			float endXDelta = artViewLocation[0] - sharedArtViewLocation[0];
			float endYDelta = artViewLocation[1] - sharedArtViewLocation[1];

			ViewUtils.setSize(sharedArtView, artView);
			sharedArtView.setTranslationX(startXDelta);
			sharedArtView.setTranslationY(startYDelta);
			sharedArtView.setScaleX(startScaleX);
			sharedArtView.setScaleY(startScaleY);
			sharedArtView.setPivotX(0);
			sharedArtView.setPivotY(0);
			sharedArtView.animate()
						 .translationX(endXDelta)
						 .translationY(endYDelta)
						 .scaleX(1)
						 .scaleY(1)
						 .setDuration(500)
						 .withStartAction(() -> new Handler().postDelayed(() -> {
							 currentSharedViews.peek().hide();
						 }, 50))
						 .withEndAction(() -> {
							 currentSharedViews.peek().show();
							 sharedArtView.setVisibility(View.INVISIBLE);
							 sharedArtView.setTranslationX(0);
							 sharedArtView.setTranslationY(0);
							 artView.setVisibility(View.VISIBLE);
						 })
						 .start();
		});
	}

	public void beginExitTransition() {
		if (WindowUtils.isPortrait(activity)) {
			clickBlockerView.setVisibility(View.VISIBLE);
			listBackground.animate().alpha(0);
			header.animate().alpha(0);
			list.animate().alpha(0);
		} else {
			mainWrapper.animate().alpha(0);
		}

		artView.setVisibility(View.INVISIBLE);

		ImageView animatingArtView = WindowUtils.isPortrait(activity) ? sharedArtExitView : sharedArtView;
		int[] artViewLocation = ViewUtils.getLocationOnScreen(artView);
		int[] transitionArtViewLocation = ViewUtils.getLocationOnScreen(animatingArtView);
		float startXDelta = artViewLocation[0] - transitionArtViewLocation[0];
		float startYDelta = artViewLocation[1] - transitionArtViewLocation[1];
		float startScaleX = (float) intent.getSharedArtWidth() / artView.getWidth();
		float startScaleY = (float) intent.getSharedArtHeight() / artView.getHeight();
		float endXDelta = intent.getSharedArtX() - transitionArtViewLocation[0];
		float endYDelta = intent.getSharedArtY() - transitionArtViewLocation[1];

		ViewUtils.setSize(animatingArtView, artView);
		animatingArtView.setVisibility(View.VISIBLE);
		animatingArtView.setTranslationX(startXDelta);
		animatingArtView.setTranslationY(startYDelta);
		animatingArtView.setPivotX(0);
		animatingArtView.setPivotY(0);
		animatingArtView.animate()
						.translationX(endXDelta)
						.translationY(endYDelta)
						.scaleX(startScaleX)
						.scaleY(startScaleY)
						.setDuration(500)
						.withStartAction(() -> currentSharedViews.peek().hide())
						.withEndAction(() -> {
							activity.finish();
							activity.overridePendingTransition(0, 0);
							currentSharedViews.pop().show();
						});
	}
}
