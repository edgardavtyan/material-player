package com.edavtyan.materialplayer.views.lib.views;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.edavtyan.materialplayer.R;

import lombok.Getter;

public class BaseAppbar extends FrameLayout {
	private @Getter AppBarLayout appbar;
	private @Getter Toolbar toolbar;

	public BaseAppbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true);

		appbar = (AppBarLayout) findViewById(R.id.appbar);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
	}

	public void setBackgroundColor(int color) {
		appbar.setBackgroundColor(color);
		toolbar.setBackgroundColor(color);
	}

	public void setElevation(int elevation) {
		appbar.setTargetElevation(elevation);
	}
}
