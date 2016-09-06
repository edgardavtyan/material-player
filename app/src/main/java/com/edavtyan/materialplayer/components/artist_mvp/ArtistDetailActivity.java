package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.TestableActivity;
import com.edavtyan.materialplayer.components.albums.AlbumDetailActivity;
import com.edavtyan.materialplayer.utils.AppColors;
import com.edavtyan.materialplayer.utils.ColorUtils;
import com.edavtyan.materialplayer.utils.CustomColor;
import com.edavtyan.materialplayer.utils.DeviceUtils;
import com.edavtyan.materialplayer.utils.WindowUtils;

public class ArtistDetailActivity extends TestableActivity implements ArtistDetailMvp.View {

	public static final String EXTRA_ARTIST_TITLE = "extra_artistTitle";

	private ArtistDetailMvp.Presenter presenter;
	private TextView titleView;
	private TextView infoView;
	private ImageView artView;

	public static void startActivity(Context context, String artistTitle) {
		Intent intent = new Intent(context, ArtistDetailActivity.class);
		intent.putExtra(EXTRA_ARTIST_TITLE, artistTitle);
		context.startActivity(intent);
	}

	@Override
	public void setArtistTitle(String title) {
		titleView.setText(title);
	}

	@Override
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		infoView.setText(info);
	}

	@Override
	public void setArtistImage(Drawable drawable, int fallbackImage) {
		artView.setImageResource(fallbackImage);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_collapsing_list;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArtistDetailDI di = getDI();
		presenter = di.providePresenter();

		RecyclerView list = findView(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(this));
		list.setAdapter(di.provideAdapter());

		titleView = findView(R.id.title);
		infoView = findView(R.id.info);
		artView = findView(R.id.art);

		if (DeviceUtils.isPortrait(getResources())) {
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

					artView.setTop(totalScrolled / parallaxAmount);

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

	@Override
	public void goToAlbumDetail(int albumId) {
		AlbumDetailActivity.startActivity(this, albumId);
	}

	protected ArtistDetailDI getDI() {
		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		return ((App) getApplication()).getArtistDetailDI(this, this, artistTitle);
	}
}
