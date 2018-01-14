package com.edavtyan.materialplayer.components.detail.lib;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;
import com.ed.libsutils.ColorUtils;
import com.ed.libsutils.WindowUtils;
import com.edavtyan.materialplayer.utils.ThemeColors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParallaxHeaderListCompactModule extends ActivityModule {

	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.info_container) LinearLayout infoContainer;
	@BindView(R.id.list) RecyclerView list;

	private final AppCompatActivity activity;
	private final TestableRecyclerAdapter adapter;
	private final ParallaxHeaderListPresenter presenter;

	private final RecyclerView.OnScrollListener onScrollListener
			= new RecyclerView.OnScrollListener() {
		private int totalScrolled = 0;

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);

			assert appbarShadow != null; // Removes lint warning

			totalScrolled += dy;

			int parallax = totalScrolled / 2;
			infoContainer.setTranslationY(parallax);
			appbarShadow.setAlpha(ColorUtils.intToFloatAlpha(parallax));
		}
	};

	public ParallaxHeaderListCompactModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter) {
		this.activity = activity;
		this.adapter = adapter;
		this.presenter = presenter;
	}

	@Override
	public void onCreate() {
		ButterKnife.bind(this, activity);

		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(activity));

		if (WindowUtils.isPortrait(activity)) {
			assert header != null; // Removes lint warning
			list.addOnScrollListener(onScrollListener);
			header.attachTo(list);
		}
	}

	@Override
	public void onStart() {
		presenter.onCreate();
	}

	@Override
	public void onStop() {
		presenter.onDestroy();
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
	}
}
