package com.edavtyan.materialplayer.ui.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListFragment;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListFragment;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistListFragment;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListFragment;

public class TabsAdapter extends FragmentPagerAdapter {

	public TabsAdapter(FragmentManager fm) {
		super(fm);
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
		return "";
	}
}
