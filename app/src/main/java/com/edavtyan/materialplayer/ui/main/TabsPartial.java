package com.edavtyan.materialplayer.ui.main;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabsPartial {
	@BindView(R.id.tab_layout) TabLayout tabLayout;
	@BindView(R.id.appbar) AppBarLayout appbar;

	public TabsPartial(MainActivity activity) {
		ButterKnife.bind(this, activity);
	}

	public void setViewPager(ViewPager viewPager) {
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.getTabAt(0).setIcon(R.drawable.ic_person);
		tabLayout.getTabAt(1).setIcon(R.drawable.ic_album);
		tabLayout.getTabAt(2).setIcon(R.drawable.ic_note);
		tabLayout.getTabAt(3).setIcon(R.drawable.ic_playlist2);
	}
}
