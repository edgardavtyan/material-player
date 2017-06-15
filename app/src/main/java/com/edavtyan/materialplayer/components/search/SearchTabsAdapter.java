package com.edavtyan.materialplayer.components.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchTabsAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments;
	private CharSequence[] titles = {"Artists"};

	public SearchTabsAdapter(FragmentManager fm) {
		super(fm);

		fragments = new ArrayList<>();
		fragments.add(new SearchArtistFragment());
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	public Fragment getFragment(int position) {
		return fragments.get(position);
	}
}
