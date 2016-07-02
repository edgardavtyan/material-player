package com.edavtyan.materialplayer.views.lib.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edavtyan.materialplayer.views.albumslist.AlbumsListFragment;
import com.edavtyan.materialplayer.views.artistslist.ArtistsListFragment;
import com.edavtyan.materialplayer.views.trackslist.TracksListFragment;

public class TabPagerFragmentAdapter extends FragmentPagerAdapter {
	private final String[] tabNames = {
			"Artists",
			"Albums",
			"Tracks"
	};

	public TabPagerFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	/*
	 FragmentPagerAdapter
	 */

	@Override
	public CharSequence getPageTitle(int position) {
		return tabNames[position];
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new ArtistsListFragment();
			case 1:
				return new AlbumsListFragment();
			case 2:
				return new TracksListFragment();
			default:
				return null;

		}
	}

	@Override
	public int getCount() {
		return tabNames.length;
	}
}
