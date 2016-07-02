package com.edavtyan.materialplayer.views.lib.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.utils.AppColors;
import com.edavtyan.materialplayer.utils.ColorUtils;
import com.edavtyan.materialplayer.utils.CustomColor;
import com.edavtyan.materialplayer.utils.DeviceUtils;
import com.edavtyan.materialplayer.utils.WindowUtils;
import com.edavtyan.materialplayer.views.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.views.lib.decorators.DividerItemDecoration;

public abstract class CollapsingHeaderListActivity
		extends BaseToolbarActivity
		implements LoaderManager.LoaderCallbacks<Cursor> {
	protected AppColors appColors;
	protected ImageView imageView;
	protected ImageView imageBackView;
	protected TextView titleView;
	protected TextView infoView;

	/*
	 * AppCompatActivity
	 */

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportLoaderManager().initLoader(0, null, this);

		appColors = new AppColors(this);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
		recyclerView.setAdapter(getAdapter());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DividerItemDecoration(appColors.divider));

		imageView = (ImageView) findViewById(R.id.art);
		imageBackView = (ImageView) findViewById(R.id.back);
		titleView = (TextView) findViewById(R.id.title);
		infoView = (TextView) findViewById(R.id.info);

		if (DeviceUtils.isPortrait(getResources())) {
			WindowUtils.makeStatusBarTransparent(getWindow());

			LinearLayout appbarWrapper = (LinearLayout) findViewById(R.id.appbar_wrapper);
			appbarWrapper.bringToFront();

			RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.list_header);
			header.attachTo(recyclerView, true);

			AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
			View appbarShadow = findViewById(R.id.appbar_shadow);
			View statusShadow = findViewById(R.id.statusbar_tint);
			CustomColor primaryColor = new CustomColor(appColors.primary);
			CustomColor primaryDarkColor = new CustomColor(appColors.primaryDark);

			recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
				private int totalScrolled = 0;
				private final int parallaxAmount = 2;

				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
					totalScrolled += dy;

					imageView.setTop(totalScrolled / parallaxAmount);
					imageBackView.setTop(totalScrolled / parallaxAmount);

					appbar.setBackgroundColor(primaryColor.fade(totalScrolled));
					appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(totalScrolled));
					statusShadow.setBackgroundColor(primaryDarkColor.fade(totalScrolled));
				}
			});
		}
	}

	/* BaseActivity */

	@Override
	public int getLayoutId() {
		return R.layout.activity_collapsing_list;
	}

	/*
	 * LoaderCallbacks
	 */

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return getLoader();
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		getAdapter().swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		getAdapter().swapCursor(null);
	}

	/*
	 * Abstract methods
	 */

	protected abstract Loader<Cursor> getLoader();

	protected abstract RecyclerViewCursorAdapter getAdapter();
}
