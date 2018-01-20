package com.edavtyan.materialplayer.screens.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListFragment;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListFragment;

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
