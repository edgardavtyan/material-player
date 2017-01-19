package com.edavtyan.materialplayer.components.album_detail;

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
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import static com.edavtyan.materialplayer.components.album_detail.AlbumDetailMvp.EXTRA_ALBUM_ID;

public class AlbumDetailActivityCompact extends BaseToolbarActivity implements AlbumDetailMvp.View {
	private TextView titleView;
	private TextView infoTopView;
	private TextView infoBottomView;
	private ImageView artView;

	private AlbumDetailMvp.Presenter presenter;
	private Navigator navigator;
	private AlbumDetailAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		titleView = findView(R.id.title);
		infoTopView = findView(R.id.info_top);
		infoBottomView = findView(R.id.info_bottom);
		artView = findView(R.id.art);

		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		AlbumDetailFactory factory = ((App) getApplicationContext()).getAlbumDetailDI(this, this, albumId);

		adapter = factory.getAdapter();
		navigator = factory.getNavigator();
		presenter = factory.getPresenter();

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
	public void setAlbumTitle(String albumTitle) {
		titleView.setText(albumTitle);
	}

	@Override
	public void setAlbumInfo(String artistTitle, int tracksCount) {
		infoTopView.setText(artistTitle);
		infoBottomView.setText(
				getResources().getQuantityString(R.plurals.tracks, tracksCount, tracksCount));
	}

	@Override
	public void setAlbumImage(Bitmap art) {
		if (art != null) {
			artView.setImageBitmap(art);
		} else {
			artView.setImageResource(R.drawable.fallback_cover);
		}
	}

	@Override
	public void goToNowPlaying() {
		navigator.gotoNowPlaying();
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
