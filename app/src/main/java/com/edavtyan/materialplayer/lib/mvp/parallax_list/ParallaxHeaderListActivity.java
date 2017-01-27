package com.edavtyan.materialplayer.lib.mvp.parallax_list;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.utils.AppColors;
import com.edavtyan.materialplayer.utils.ColorUtils;
import com.edavtyan.materialplayer.utils.CustomColor;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;

public abstract class ParallaxHeaderListActivity extends BaseToolbarActivity {
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.appbar_wrapper) LinearLayout appbarWrapper;
	@BindView(R.id.list_header) RecyclerViewHeader header;
	@BindView(R.id.appbar) AppBarLayout appbar;
	@BindView(R.id.appbar_shadow) View appbarShadow;
	@BindView(R.id.statusbar_tint) View statusShadow;

	private ParallaxHeaderListPresenter presenter;

	protected void setHeaderTitle(String title) {
		titleView.setText(title);
	}

	protected void setHeaderInfo(String info) {
		infoView.setText(info);
	}

	protected void setHeaderImage(Bitmap image, int fallback) {
		if (image != null) {
			imageView.setImageBitmap(image);
		} else {
			imageView.setImageResource(fallback);
		}
	}

	protected void init(RecyclerView.Adapter adapter, ParallaxHeaderListPresenter presenter) {
		this.presenter = presenter;
		list.setAdapter(adapter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		list.setLayoutManager(new LinearLayoutManager(this));

		if (WindowUtils.isPortrait(this)) {
			WindowUtils.makeStatusBarTransparent(getWindow());
			appbarWrapper.bringToFront();
			header.attachTo(list);

			AppColors appColors = new AppColors(this);
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
	protected void onStart() {
		super.onStart();
		presenter.onCreate();
	}

	@Override
	protected void onStop() {
		super.onStop();
		presenter.onDestroy();
	}
}
