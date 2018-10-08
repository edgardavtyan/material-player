package com.edavtyan.materialplayer.ui.main;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListFragment;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistListFragment;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListFragment;

public class TabsAdapter extends FragmentPagerAdapter {
	private final Activity activity;

	public TabsAdapter(Activity activity, FragmentManager fm) {
		super(fm);
		this.activity = activity;
	}

	@Override
	@Nullable
	public Fragment getItem(int position) {
		switch (position) {
		case 0: return new ArtistListFragment();
		case 1: return new AlbumListFragment();
		case 2: return new TrackListFragment();
		case 3: return new PlaylistListFragment();
		default: return null;
		}
	}

	@Override
	public int getCount() { return 4; }

	@Override
	public CharSequence getPageTitle(int position) {
		SpannableStringBuilder stringBuilder = new SpannableStringBuilder(" ");
		if (position == 0) setUpTabIcon(stringBuilder, R.drawable.ic_person);
		if (position == 1) setUpTabIcon(stringBuilder, R.drawable.ic_album);
		if (position == 2) setUpTabIcon(stringBuilder, R.drawable.ic_note);
		if (position == 3) setUpTabIcon(stringBuilder, R.drawable.ic_playlist2);
		return stringBuilder;
	}

	private void setUpTabIcon(SpannableStringBuilder stringBuilder, @DrawableRes int drawableId) {
		Drawable drawable = ContextCompat.getDrawable(activity, drawableId);
		drawable.setBounds(0, 0, 36, 36);
		drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
		ImageSpan imageSpan = new ImageSpan(drawable);
		stringBuilder.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}
}
