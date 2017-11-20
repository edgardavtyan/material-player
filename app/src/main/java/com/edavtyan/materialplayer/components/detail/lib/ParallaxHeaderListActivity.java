package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListMvp;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

import butterknife.BindView;

public abstract class ParallaxHeaderListActivity
		extends BaseToolbarActivity
		implements ListMvp.View {
	
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView imageView;

	private TestableRecyclerAdapter adapter;

	@Override
	public int getLayoutId() {
		return R.layout.activity_detail;
	}

	public void init(TestableRecyclerAdapter adapter) {
		this.adapter = adapter;
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String info) {
		infoView.setText(info);
	}

	public void setImage(Bitmap image, @DrawableRes int fallback) {
		if (image == null) {
			imageView.setImageResource(fallback);
		} else {
			imageView.setImageBitmap(image);
		}
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
