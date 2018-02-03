package com.edavtyan.materialplayer.screens.detail.lib;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
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
import com.edavtyan.materialplayer.screens.detail.artist_detail.ArtistDetailIntent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListCompactModule extends ActivityModule {

	public static final String SHARED_ART_X = "shared_art_x";
	public static final String SHARED_ART_Y = "shared_art_y";
	public static final String SHARED_ART_WIDTH = "shared_art_width";
	public static final String SHARED_ART_HEIGHT = "shared_art_height";

	private static final int SCALED_ART_SIZE_DP = 120;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.main_wrapper) @Nullable LinearLayout mainWrapper;
	@BindView(R.id.info_container) LinearLayout infoContainer;
	@BindView(R.id.info_top) @Nullable TextView portraitTopInfoView;
	@BindView(R.id.info_bottom) @Nullable TextView portraitBottomInfoView;
	@BindView(R.id.info) @Nullable TextView landscapeInfoView;
	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.list_background) @Nullable View listBackground;
	@BindView(R.id.click_blocker) @Nullable View clickBlockerView;
	@BindView(R.id.art_wrapper) @Nullable View imageViewWrapper;
	@BindView(R.id.shared_art) ImageView sharedImageView;
	@BindView(R.id.shared_art_exit) @Nullable ImageView sharedImageExitView;

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;
	private final CurrentSharedViews currentSharedViews;

	private final RecyclerView.OnScrollListener onScrollListener
			= new RecyclerView.OnScrollListener() {
		private int totalScrolled = 0;

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);

			assert appbarShadow != null; // Removes lint warning

			totalScrolled += dy;

			listBackground.setTranslationY(-totalScrolled);

			int parallax = (totalScrolled + header.getHeight()) / 2;
			infoContainer.setTranslationY(-parallax);
			appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(parallax));
		}
	};

	private boolean isExitTransitionRunning;
	private float sharedImageViewStartScaleX;
	private float sharedImageViewStartScaleY;

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
	public void onCreate() {
		ButterKnife.bind(this, activity);

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			assert header != null; // Removes lint warning
			list.addOnScrollListener(onScrollListener);
			header.post(() -> {
				list.setPadding(0, header.getHeight(), 0, 0);
				list.scrollBy(-1000, -1000);
				listBackground.setTranslationY(header.getHeight());
				listBackground.getLayoutParams().height = activity.getWindow().getDecorView().getHeight();
			});
		}

		beginEnterTransition();
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
		infoContainer.setBackgroundColor(colors.getColorPrimary());
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

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		if (image != null) {
			if (WindowUtils.isPortrait(activity)) {
				int imageViewSize = DpConverter.dpToPixel(SCALED_ART_SIZE_DP);
				Bitmap scaledImage = BitmapResizer.resize(image, imageViewSize);
				imageView.setImageBitmap(scaledImage);
				sharedImageView.setImageBitmap(scaledImage);
				sharedImageExitView.setImageBitmap(scaledImage);
			} else {
				sharedImageView.setImageBitmap(image);
				imageView.setImageBitmap(image);
			}
		} else {
			imageView.setImageResource(fallback);
			sharedImageView.setImageResource(fallback);
			if (WindowUtils.isPortrait(activity)) {
				sharedImageExitView.setImageResource(fallback);
			}
		}
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	private void beginEnterTransition() {
		sharedImageView.setVisibility(View.VISIBLE);
		imageView.setVisibility(View.INVISIBLE);

		mainWrapper.setAlpha(0);
		mainWrapper.animate().alpha(1);

		sharedImageView.post(() -> {
			ArtistDetailIntent intent = new ArtistDetailIntent(activity.getIntent());
			sharedImageViewStartScaleX = (float) intent.getSharedArtWidth() / imageView.getWidth();
			sharedImageViewStartScaleY = (float) intent.getSharedArtHeight() / imageView.getHeight();

			int[] sharedImageViewLocation = ViewUtils.getLocationOnScreen(sharedImageView);
			int[] imageViewLocation = ViewUtils.getLocationOnScreen(imageView);
			float startXDelta = intent.getSharedArtX() - sharedImageViewLocation[0];
			float startYDelta = intent.getSharedArtY() - sharedImageViewLocation[1];
			float endXDelta = imageViewLocation[0] - sharedImageViewLocation[0];
			float endYDelta = imageViewLocation[1] - sharedImageViewLocation[1];

			ViewUtils.setSize(sharedImageView, imageView.getWidth(), imageView.getHeight());
			sharedImageView.setTranslationX(startXDelta);
			sharedImageView.setTranslationY(startYDelta);
			sharedImageView.setScaleX(sharedImageViewStartScaleX);
			sharedImageView.setScaleY(sharedImageViewStartScaleY);
			sharedImageView.setPivotX(0);
			sharedImageView.setPivotY(0);
			sharedImageView.animate()
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
							   sharedImageView.setVisibility(View.INVISIBLE);
							   sharedImageView.setTranslationX(0);
							   sharedImageView.setTranslationY(0);
							   imageView.setVisibility(View.VISIBLE);
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

		imageView.setVisibility(View.INVISIBLE);

		ImageView animatingImageView = WindowUtils.isPortrait(activity) ? sharedImageExitView : sharedImageView;
		ArtistDetailIntent intent = new ArtistDetailIntent(activity.getIntent());

		int[] imageViewLocation = ViewUtils.getLocationOnScreen(imageView);
		int[] animatingImageViewLocation = ViewUtils.getLocationOnScreen(animatingImageView);
		float startXDelta = imageViewLocation[0] - animatingImageViewLocation[0];
		float startYDelta = imageViewLocation[1] - animatingImageViewLocation[1];
		float endXDelta = intent.getSharedArtX() - animatingImageViewLocation[0];
		float endYDelta = intent.getSharedArtY() - animatingImageViewLocation[1];

		animatingImageView.setVisibility(View.VISIBLE);
		animatingImageView.setTranslationX(startXDelta);
		animatingImageView.setTranslationY(startYDelta);
		animatingImageView.setPivotX(0);
		animatingImageView.setPivotY(0);
		ViewUtils.setSize(animatingImageView, imageView.getWidth(), imageView.getHeight());
		animatingImageView.animate()
						  .translationX(endXDelta)
						  .translationY(endYDelta)
						  .scaleX(sharedImageViewStartScaleX)
						  .scaleY(sharedImageViewStartScaleY)
						  .setDuration(500)
						  .withStartAction(() -> currentSharedViews.peek().hide())
						  .withEndAction(() -> {
							  activity.finish();
							  activity.overridePendingTransition(0, 0);
							  currentSharedViews.pop().show();
						  });
	}
}
