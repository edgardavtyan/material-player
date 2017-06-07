package com.edavtyan.materialplayer.lib.mvp.parallax_list_compact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.utils.ColorUtils;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;

public abstract class ParallaxListCompactActivity extends BaseToolbarActivity {
	@BindView(R.id.list_header) @Nullable RecyclerViewHeader header;
	@BindView(R.id.appbar_shadow) @Nullable View appbarShadow;
	@BindView(R.id.info_container) LinearLayout infoContainer;
	@BindView(R.id.list) RecyclerView list;

	private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
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

	public void init(RecyclerView.Adapter adapter) {
		list.setAdapter(adapter);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		list.setLayoutManager(new LinearLayoutManager(this));

		if (WindowUtils.isPortrait(this)) {
			assert header != null; // Removes lint warning
			list.addOnScrollListener(onScrollListener);
			header.attachTo(list);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail_compact;
	}
}
