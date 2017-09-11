package com.edavtyan.materialplayer.components.album_detail;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.modular.activity.NavigationMenuModule;
import com.edavtyan.materialplayer.modular.activity.ThemeSwitchModule;
import com.edavtyan.materialplayer.modular.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.ParallaxHeaderListModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailActivity
		extends ModularActivity
		implements AlbumDetailMvp.View {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;

	private Navigator navigator;
	private AlbumDetailAdapter adapter;

	public void setAlbumTitle(String title) {
		titleView.setText(title);
	}

	public void setAlbumInfo(String artistTitle, int tracksCount, long duration) {
		Resources res = getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = getString(R.string.pattern_album_info, artistTitle, tracksCountStr);
		infoView.setText(info);
	}

	public void setAlbumImage(Bitmap art) {
		if (art == null) {
			artView.setImageResource(R.drawable.fallback_cover);
		} else {
			artView.setImageBitmap(art);
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);

		ButterKnife.bind(this);

		AlbumDetailFactory factory = getDI();

		navigator = factory.getNavigator();
		adapter = factory.getAdapter();

		addModule(new NavigationMenuModule(this, factory.getNavigator()));
		addModule(new ThemeSwitchModule(this, factory.getPrefs(), factory.getThemeUtils()));
		addModule(new ParallaxHeaderListModule(this, factory.getAdapter(), factory.getPresenter()));
	}

	@Override
	public void gotoNowPlaying() {
		navigator.gotoNowPlaying();
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChanged();
	}

	protected AlbumDetailFactory getDI() {
		int albumId = getIntent().getIntExtra(AlbumDetailMvp.EXTRA_ALBUM_ID, -1);
		return ((App) getApplicationContext()).getAlbumDetailDI(this, this, albumId);
	}
}
