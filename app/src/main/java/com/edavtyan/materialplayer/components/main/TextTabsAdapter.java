package com.edavtyan.materialplayer.components.main;

import android.support.v4.app.FragmentManager;

public class TextTabsAdapter extends TabsAdapter {
	private static final String[] tabNames = {"Artists", "Albums", "Tracks"};

	public TextTabsAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabNames[position];
	}
}
