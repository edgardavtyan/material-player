package com.edavtyan.materialplayer.components.main;

import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class MainFactory extends BaseFactory {
	private final MainActivity activity;

	private CompactMainScreenPref compactMainScreenPref;
	private TextTabsAdapter textTabsAdapter;
	private IconsTabsAdapter iconsTabsAdapter;

	public MainFactory(MainActivity activity) {
		super(activity);
		this.activity = activity;
	}

	public CompactMainScreenPref getCompactMainScreenPref() {
		if (compactMainScreenPref == null)
			compactMainScreenPref = new CompactMainScreenPref(getContext(), getPrefs());
		return compactMainScreenPref;
	}

	public TextTabsAdapter getTextTabsAdapter() {
		if (textTabsAdapter == null)
			textTabsAdapter = new TextTabsAdapter(activity.getSupportFragmentManager());
		return textTabsAdapter;
	}

	public IconsTabsAdapter getIconsTabsAdapter() {
		if (iconsTabsAdapter == null)
			iconsTabsAdapter = new IconsTabsAdapter(activity.getSupportFragmentManager(), activity);
		return iconsTabsAdapter;
	}
}
