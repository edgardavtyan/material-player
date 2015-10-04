package com.edavtyan.materialplayer.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.edavtyan.materialplayer.app.fragments.AlbumsListFragment;
import com.edavtyan.materialplayer.app.fragments.ArtistsListFragment;

public class TabPagerFragmentAdapter extends FragmentPagerAdapter {
    String[] tabNames = {
            "Artists",
            "Albums"
    };

    public TabPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

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
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }
}
