package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

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
		colorSelectionView = initColorSelectionView();
		dialog = initDialog();
		presenter = initPresenter(attrs);
	}

	public ColorSelectionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		entryView = initEntryView();
		colorSelectionView = initColorSelectionView();
		dialog = initDialog();
		presenter = initPresenter(attrs);
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
		View view = inflate(context, R.layout.entry_color, this);
		ColorSelectionEntry entryView = new ColorSelectionEntry(view);
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
		return new ColorSelectionPresenter(this, new ColorSelectionController(context, attributeSet));
	}
}
