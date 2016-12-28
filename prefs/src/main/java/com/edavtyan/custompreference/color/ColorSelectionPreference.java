package com.edavtyan.custompreference.color;

import android.content.Context;
import android.util.AttributeSet;

import com.edavtyan.custompreference.base.BaseDialog;
import com.edavtyan.custompreference.base.BasePreference;
import com.edavtyan.custompreference.utils.PixelConverter;

import java.util.List;

public class ColorSelectionPreference
		extends BasePreference
		implements ColorSelectionEntry.OnClickListener,
				   ColorSelectionView.OnColorSelectedListener {

	private final ColorSelectionEntry entryView;
	private final BaseDialog dialog;
	private final ColorSelectionView colorSelectionView;
	private final ColorSelectionPresenter presenter;

	public ColorSelectionPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		entryView = initEntryView();
		if (isInEditMode()) {
			colorSelectionView = null;
			dialog = null;
			presenter = null;
		} else {
			colorSelectionView = initColorSelectionView();
			dialog = initDialog();
			presenter = initPresenter(attrs);
		}
	}

	public ColorSelectionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		entryView = initEntryView();
		if (isInEditMode()) {
			dialog = null;
			colorSelectionView = null;
			presenter = null;
		} else {
			colorSelectionView = initColorSelectionView();
			dialog = initDialog();
			presenter = initPresenter(attrs);
		}
	}

	public void setTitle(CharSequence title) {
		entryView.setTitle(title);
		dialog.setTitle(title);
	}

	public void setColors(List<Integer> colors) {
		colorSelectionView.setColors(colors);
		colorSelectionView.rebuild();
	}

	public void setSelectedColor(int position, int color) {
		colorSelectionView.setSelectedColor(position);
		entryView.setColor(color);
	}

	public void showDialog() {
		dialog.show();
	}

	public void closeDialog() {
		dialog.dismiss();
	}

	@Override
	public void onEntryClick() {
		presenter.onEntryClick();
	}

	@Override
	public void onColorSelected(int position) {
		presenter.onColorSelected(position);
	}

	private ColorSelectionEntry initEntryView() {
		ColorSelectionEntry entryView = new ColorSelectionEntry(context, this);
		entryView.setOnClickListener(this);
		return entryView;
	}

	private ColorSelectionView initColorSelectionView() {
		int padding = PixelConverter.dpToPx(24);

		ColorSelectionView colorSelectionView = new ColorSelectionView(context, null);
		colorSelectionView.setPadding(padding, 0, padding, 0);
		colorSelectionView.setOnColorSelectedListener(this);
		return colorSelectionView;
	}

	private BaseDialog initDialog() {
		BaseDialog dialog = new BaseDialog(context);
		dialog.setView(colorSelectionView);
		return dialog;
	}

	private ColorSelectionPresenter initPresenter(AttributeSet attributeSet) {
		return new ColorSelectionPresenter(this, new ColorSelectionModel(context, attributeSet));
	}
}
