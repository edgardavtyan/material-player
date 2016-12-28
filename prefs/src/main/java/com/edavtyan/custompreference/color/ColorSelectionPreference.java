package com.edavtyan.custompreference.color;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.edavtyan.custompreference.R;
import com.edavtyan.custompreference.base.BaseDialog;
import com.edavtyan.custompreference.base.BasePreference;
import com.edavtyan.custompreference.utils.PixelConverter;

import java.util.List;

import lombok.Cleanup;

public class ColorSelectionPreference
		extends BasePreference
		implements ColorSelectionEntry.OnClickListener,
				   ColorSelectionView.OnColorSelectedListener {

	private ColorSelectionEntry entryView;
	private BaseDialog dialog;
	private ColorSelectionView colorSelectionView;
	private ColorSelectionPresenter presenter;

	public ColorSelectionPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPreference(attrs);
	}

	public ColorSelectionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPreference(attrs);
	}

	public void setTitle(String title) {
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

	private void initPreference(AttributeSet attrs) {
		entryView = initEntryView();
		if (isInEditMode()) {
			@Cleanup("recycle")
			TypedArray typedAttrs = context.obtainStyledAttributes(attrs, R.styleable.ColorSelectionPref);
			String title = typedAttrs.getString(R.styleable.ColorSelectionPref_cp_title);
			entryView.setTitle(title);
			entryView.setColor(Color.RED);
		} else {
			colorSelectionView = initColorSelectionView();
			dialog = initDialog();
			presenter = initPresenter(attrs);
		}
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
