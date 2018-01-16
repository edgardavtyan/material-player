package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import com.ed.libsutils.utils.ColorUtils;
import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;
import com.edavtyan.materialplayer.utils.CustomColor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListModule extends ActivityModule {
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.appbar_wrapper) @Nullable LinearLayout appbarWrapper;
	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.appbar) @Nullable AppBarLayout appbar;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.statusbar_tint) @Nullable View statusShadow;

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;

	private CustomColor appbarColor;
	private CustomColor statusbarColor;

	public ParallaxHeaderListModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter) {
		this.activity = activity;
		this.adapter = adapter;
		this.presenter = presenter;
		appbarColor = new CustomColor(0);
		statusbarColor = new CustomColor(0);
	}

	@Override
	public void onCreate() {
		ButterKnife.bind(this, activity);

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			// Removes lint warnings
			assert statusShadow != null;
			assert appbarWrapper != null;
			assert header != null;
			assert appbar != null;
			assert appbarShadow != null;

			if (Build.VERSION.SDK_INT < 21) {
				statusShadow.setVisibility(View.GONE);
			}

			WindowUtils.makeStatusBarTransparent(activity.getWindow());
			appbarWrapper.bringToFront();
			header.attachTo(list);

			list.addOnScrollListener(new RecyclerView.OnScrollListener() {
				private final int parallaxAmount = 2;
				private int totalScrolled = 0;

				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					totalScrolled += dy;

					imageView.setTranslationY(totalScrolled / parallaxAmount);
					appbar.setBackgroundColor(appbarColor.fade(totalScrolled));
					appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(totalScrolled));
					statusShadow.setBackgroundColor(statusbarColor.fade(totalScrolled));
				}
			});
		}
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		appbarColor = new CustomColor(((ColorDrawable) appbar.getBackground()).getColor());
		statusbarColor = new CustomColor(WindowUtils.getStatusBarColor(activity));
	}

	@Override
	public void onStart() {
		presenter.onCreate();
	}

	@Override
	public void onStop() {
		presenter.onDestroy();
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String info) {
		infoView.setText(info);
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		if (image == null) {
			imageView.setImageResource(fallback);
		} else {
			imageView.setImageBitmap(image);
		}
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
