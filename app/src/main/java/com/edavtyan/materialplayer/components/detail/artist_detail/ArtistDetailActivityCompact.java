package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.mvp.parallax_list_compact.ParallaxListCompactActivity;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;

import static com.edavtyan.materialplayer.components.detail.artist_detail.ArtistDetailMvp.EXTRA_ARTIST_TITLE;

public class ArtistDetailActivityCompact
		extends ParallaxListCompactActivity
		implements ArtistDetailMvp.View {

	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.art) ImageView artView;
	@Nullable @BindView(R.id.info_top) TextView infoTopView;
	@Nullable @BindView(R.id.info_bottom) TextView infoBottomView;
	@Nullable @BindView(R.id.info) TextView infoView;

	private ArtistDetailMvp.Presenter presenter;
	private ArtistDetailAdapter adapter;
	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		ArtistDetailFactory factory = ((App) getApplicationContext()).getArtistDetailDI(this, this, artistTitle);

		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		navigator = factory.getNavigator();

		init(adapter);
		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail_compact;
	}

	@Override
	public void setArtistTitle(String title) {
		titleView.setText(title);
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);

		if (WindowUtils.isPortrait(this)) {
			infoTopView.setText(albumsCountStr);
			infoBottomView.setText(tracksCountStr);
		} else {
			String infoStr = res.getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
			infoView.setText(infoStr);
		}
	}

	@Override
	public void setArtistImage(Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_artist);
		}
	}

	@Override
	public void gotoAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
