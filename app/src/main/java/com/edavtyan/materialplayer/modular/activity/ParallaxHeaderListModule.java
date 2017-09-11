package com.edavtyan.materialplayer.modular.activity;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.parallax_list.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.modular.ActivityModule;
import com.edavtyan.materialplayer.utils.AppColors;
import com.edavtyan.materialplayer.utils.ColorUtils;
import com.edavtyan.materialplayer.utils.CustomColor;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListModule extends ActivityModule {
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.appbar_wrapper) @Nullable LinearLayout appbarWrapper;
	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.appbar) @Nullable AppBarLayout appbar;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.statusbar_tint) @Nullable View statusShadow;

	private final AppCompatActivity activity;
	private final RecyclerView.Adapter adapter;
	private ParallaxHeaderListPresenter presenter;

	public ParallaxHeaderListModule(
			AppCompatActivity activity,
			RecyclerView.Adapter adapter,
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

			AppColors appColors = new AppColors(activity);
			CustomColor primaryColor = new CustomColor(appColors.primary);
			CustomColor primaryDarkColor = new CustomColor(appColors.primaryDark);

			list.addOnScrollListener(new RecyclerView.OnScrollListener() {
				private final int parallaxAmount = 2;
				private int totalScrolled = 0;

				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					totalScrolled += dy;

					imageView.setTranslationY(totalScrolled / parallaxAmount);
					appbar.setBackgroundColor(primaryColor.fade(totalScrolled));
					appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(totalScrolled));
					statusShadow.setBackgroundColor(primaryDarkColor.fade(totalScrolled));
				}
			});
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
}
