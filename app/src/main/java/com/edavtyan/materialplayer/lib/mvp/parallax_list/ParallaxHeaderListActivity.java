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

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.utils.AppColors;
import com.edavtyan.materialplayer.utils.ColorUtils;
import com.edavtyan.materialplayer.utils.CustomColor;
import com.edavtyan.materialplayer.utils.WindowUtils;

public class ParallaxHeaderListActivity extends BaseToolbarActivity {
	private TextView titleView;
	private TextView infoView;
	private ImageView imageView;
	private ImageView backImageView;
	private RecyclerView list;
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
			backImageView.setImageBitmap(image);
		} else {
			imageView.setImageResource(fallback);
			backImageView.setImageResource(fallback);
		}
	}

	protected void init(RecyclerView.Adapter adapter, ParallaxHeaderListPresenter presenter) {
		this.presenter = presenter;
		list.setAdapter(adapter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_collapsing_list;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		titleView = findView(R.id.title);
		infoView = findView(R.id.info);
		imageView = findView(R.id.art);
		backImageView = findView(R.id.back);

		list = findView(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(this));

		if (WindowUtils.isPortrait(this)) {
			WindowUtils.makeStatusBarTransparent(getWindow());

			LinearLayout appbarWrapper = findView(R.id.appbar_wrapper);
			appbarWrapper.bringToFront();

			RecyclerViewHeader header = findView(R.id.list_header);
			header.attachTo(list, true);

			AppBarLayout appbar = findView(R.id.appbar);
			View appbarShadow = findView(R.id.appbar_shadow);
			View statusShadow = findView(R.id.statusbar_tint);

			AppColors appColors = new AppColors(this);
			CustomColor primaryColor = new CustomColor(appColors.primary);
			CustomColor primaryDarkColor = new CustomColor(appColors.primaryDark);

			list.addOnScrollListener(new RecyclerView.OnScrollListener() {
				private final int parallaxAmount = 2;
				private int totalScrolled = 0;

				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					totalScrolled += dy;

					imageView.setTop(totalScrolled / parallaxAmount);
					backImageView.setTag(totalScrolled / parallaxAmount);

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
