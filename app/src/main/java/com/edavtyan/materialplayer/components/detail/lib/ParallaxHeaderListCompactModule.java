package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
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
import com.ed.libsutils.BitmapResizer;
import com.ed.libsutils.ColorUtils;
import com.ed.libsutils.DpConverter;
import com.ed.libsutils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListCompactModule extends ActivityModule {

	private static final int SCALED_ART_SIZE_DP = 120;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.info_container) LinearLayout infoContainer;
	@BindView(R.id.info_top) @Nullable TextView portraitTopInfoView;
	@BindView(R.id.info_bottom) @Nullable TextView portraitBottomInfoView;
	@BindView(R.id.info) @Nullable TextView landscapeInfoView;
	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.list) RecyclerView list;

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;

	private final RecyclerView.OnScrollListener onScrollListener
			= new RecyclerView.OnScrollListener() {
		private int totalScrolled = 0;

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);

			assert appbarShadow != null; // Removes lint warning

			totalScrolled += dy;

			int parallax = totalScrolled / 2;
			infoContainer.setTranslationY(parallax);
			appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(parallax));
		}
	};

	public ParallaxHeaderListCompactModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter) {
		this.activity = activity;
		this.adapter = adapter;
		this.presenter = presenter;
	}

	@Override
	public void onCreate() {
		ButterKnife.bind(this, activity);

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			assert header != null; // Removes lint warning
			list.addOnScrollListener(onScrollListener);
			header.attachTo(list);
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
			} else {
				imageView.setImageBitmap(image);
			}
		} else {
			imageView.setImageResource(fallback);
		}
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
