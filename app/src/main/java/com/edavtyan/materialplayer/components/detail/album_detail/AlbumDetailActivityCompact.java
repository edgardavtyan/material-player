package com.edavtyan.materialplayer.components.detail.album_detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListCompactModule;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.modular.activity.BaseToolbarModule;
import com.edavtyan.materialplayer.utils.WindowUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

import static com.edavtyan.materialplayer.components.detail.album_detail.AlbumDetailMvp.EXTRA_ALBUM_ID;

public class AlbumDetailActivityCompact
		extends BaseActivity
		implements AlbumDetailMvp.View {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.info_top) @Nullable TextView infoTopView;
	@BindView(R.id.info_bottom) @Nullable TextView infoBottomView;
	@BindView(R.id.info) @Nullable TextView infoView;

	private Navigator navigator;
	private AlbumDetailAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, -1);
		AlbumDetailFactory factory = ((App) getApplicationContext()).getAlbumDetailDI(this, this, albumId);

		adapter = factory.getAdapter();
		navigator = factory.getNavigator();

		addModule(new BaseToolbarModule(this));
		addModule(new ParallaxHeaderListCompactModule(this, factory.getAdapter(), factory.getPresenter()));
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
	@SuppressWarnings("ConstantConditions")
	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		String tracksCountStr = getResources().getQuantityString(R.plurals.tracks, tracksCount, tracksCount);

		if (WindowUtils.isPortrait(this)) {
			long durationStr = TimeUnit.MILLISECONDS.toMinutes(duration);
			String info = getString(R.string.pattern_track_time_count, tracksCountStr, durationStr);

			infoTopView.setText(artistTitle);
			infoBottomView.setText(info);
		} else {
			String info = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
			infoView.setText(info);
		}
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
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
