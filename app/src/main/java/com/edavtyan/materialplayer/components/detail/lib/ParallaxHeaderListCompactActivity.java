package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListMvp;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.utils.BitmapResizer;
import com.edavtyan.materialplayer.utils.DpConverter;
import com.edavtyan.materialplayer.utils.WindowUtils;

import butterknife.BindView;

public abstract class ParallaxHeaderListCompactActivity
		extends BaseToolbarActivity
		implements ListMvp.View {

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

	protected void init(TestableRecyclerAdapter adapter, ParallaxHeaderListPresenter presenter) {
		this.adapter = adapter;
		addModule(new ParallaxHeaderListCompactModule(this, adapter, presenter));
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
			if (WindowUtils.isPortrait(this)) {
				int imageViewSize = DpConverter.convertDpToPixel(120);
				Bitmap scaledImage = BitmapResizer.resize(image, imageViewSize);
				imageView.setImageBitmap(scaledImage);
			} else {
				imageView.setImageBitmap(image);
			}
		} else {
			imageView.setImageResource(fallback);
		}
	}

	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}
}
