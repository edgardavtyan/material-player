package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;

public class ParallaxHeaderListCompactActivity extends BaseToolbarActivity {
	@BindView(R.id.list) RecyclerView list;
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.info_top) @Nullable TextView portraitTopInfoView;
	@BindView(R.id.info_bottom) @Nullable TextView portraitBottomInfoView;
	@BindView(R.id.info) @Nullable TextView landscapeInfoView;

	private TestableRecyclerAdapter adapter;

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail_compact;
	}

	protected void init(TestableRecyclerAdapter adapter) {
		this.adapter = adapter;
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String portraitTopInfo, String portraitBottomInfo, String landscapeInfo) {
		if (WindowUtils.isPortrait(this)) {
			// Removes lint warnings
			assert portraitTopInfoView != null;
			assert portraitBottomInfoView != null;

			portraitTopInfoView.setText(portraitTopInfo);
			portraitBottomInfoView.setText(portraitBottomInfo);
		} else {
			// Removes lint warnings
			assert landscapeInfoView != null;

			landscapeInfoView.setText(landscapeInfo);
		}
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		if (image != null) {
			imageView.setImageBitmap(image);
		} else {
			imageView.setImageResource(R.drawable.fallback_artist);
		}
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
