package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.edavtyan.custompreference.utils.PixelConverter;

public class ColorSelectionPreference
		extends DialogPreference<ColorSelectionController, ColorSelectionEntry>
		implements ColorSelectionView.OnColorSelectedListener {

	private ColorSelectionView colorSelectionView;


	public ColorSelectionPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ColorSelectionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected View onCreateDialogView() {
		int padding = PixelConverter.dpToPx(24);

		colorSelectionView = new ColorSelectionView(context, null);
		colorSelectionView.setPadding(padding, 0, padding, 0);
		colorSelectionView.setColors(controller.getEntries());
		colorSelectionView.rebuild();
		colorSelectionView.setSelectedColor(controller.getSelectedPrefIndex());
		colorSelectionView.setOnColorSelectedListener(this);
		return colorSelectionView;
	}

	@Override
	protected ColorSelectionController createController(AttributeSet attrs) {
		return new ColorSelectionController(this, attrs);
	}

	@Override
	protected int getEntryLayoutId() {
		return R.layout.entry_color;
	}

	@Override
	protected ColorSelectionEntry onCreateEntryView() {
		return new ColorSelectionEntry(this, controller);
	}

	@Override
	public void onColorSelected(int position) {
		controller.savePref(position);
		colorSelectionView.setSelectedColor(position);
		entryView.setColor(controller.getCurrentColor());
		closeDialog();
	}
}
