package com.edavtyan.materialplayer.lib.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListFragment;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListFragment;
import com.edavtyan.materialplayer.components.tracks.TracksListFragment;

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
				return new ArtistListFragment();
			case 1:
				return new AlbumListFragment();
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
