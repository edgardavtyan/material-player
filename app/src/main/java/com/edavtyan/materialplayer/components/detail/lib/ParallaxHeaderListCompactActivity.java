package com.edavtyan.materialplayer.components.detail.lib;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ed.libsutils.BitmapResizer;
import com.ed.libsutils.DpConverter;
import com.ed.libsutils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListView;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;

import butterknife.BindView;

public abstract class ParallaxHeaderListCompactActivity
		extends BaseToolbarActivity
		implements ListView {

	private static final int SCALED_ART_SIZE_DP = 120;

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.art) ImageView imageView;
	@BindView(R.id.info_container) LinearLayout infoContainer;
	@BindView(R.id.info_top) @Nullable TextView portraitTopInfoView;
	@BindView(R.id.info_bottom) @Nullable TextView portraitBottomInfoView;
	@BindView(R.id.info) @Nullable TextView landscapeInfoView;

	private TestableRecyclerAdapter adapter;

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		infoContainer.setBackgroundColor(colors.getColorPrimary());
	}

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
				int imageViewSize = DpConverter.convertDpToPixel(SCALED_ART_SIZE_DP);
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
