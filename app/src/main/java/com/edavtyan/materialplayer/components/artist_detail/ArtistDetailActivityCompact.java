package com.edavtyan.materialplayer.components.artist_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import butterknife.BindView;

import static com.edavtyan.materialplayer.components.artist_detail.ArtistDetailMvp.EXTRA_ARTIST_TITLE;

public class ArtistDetailActivityCompact
		extends BaseToolbarActivity
		implements ArtistDetailMvp.View {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info_top) TextView infoTopView;
	@BindView(R.id.info_bottom) TextView infoBottomView;
	@BindView(R.id.art) ImageView artView;

	private ArtistDetailMvp.Presenter presenter;
	private AlbumListAdapter adapter;
	private Navigator navigator;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String artistTitle = getIntent().getStringExtra(EXTRA_ARTIST_TITLE);
		ArtistDetailFactory factory = ((App) getApplicationContext()).getArtistDetailDI(this, this, artistTitle);

		presenter = factory.getPresenter();
		adapter = factory.getAdapter();
		navigator = factory.getNavigator();

		RecyclerView list = findView(R.id.list);
		list.setLayoutManager(new LinearLayoutManager(this));
		list.setAdapter(adapter);

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
	public void setArtistInfo(int albumsCount, int tracksCount) {
		Resources res = getResources();
		infoTopView.setText(res.getQuantityString(R.plurals.albums, albumsCount, albumsCount));
		infoBottomView.setText(res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount));
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
	public void goToAlbumDetail(int albumId) {
		navigator.gotoAlbumDetail(albumId);
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
