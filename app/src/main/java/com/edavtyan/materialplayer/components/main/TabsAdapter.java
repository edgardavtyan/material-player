package com.edavtyan.materialplayer.components.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edavtyan.materialplayer.components.album_all.AlbumListFragment;
import com.edavtyan.materialplayer.components.artist_all.ArtistListFragment;
import com.edavtyan.materialplayer.components.track_all.TrackListFragment;

public abstract class TabsAdapter extends FragmentPagerAdapter {
	public TabsAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new ArtistListFragment();
		case 1:
			return new AlbumListFragment();
		case 2:
			return new TrackListFragment();
		default:
			return null;

		}
	}

	@Override
	public int getCount() {
		return 3;
	}
}
