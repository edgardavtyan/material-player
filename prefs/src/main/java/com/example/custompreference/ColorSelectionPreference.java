package com.example.custompreference;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.custompreference.utils.PixelConverter;

public class ColorSelectionPreference extends DialogPreference<ColorSelectionController>
		implements ColorSelectionView.OnColorSelectedListener {
	public ColorSelectionPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ColorSelectionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected void createDialogBuilder(AlertDialog.Builder builder) {
		int padding = PixelConverter.dpToPx(24);

		ColorSelectionView colorSelectionView = new ColorSelectionView(context, null);
		colorSelectionView.setPadding(padding, 0, padding, 0);
		colorSelectionView.setColors(controller.getEntries());
		colorSelectionView.rebuild();
		colorSelectionView.setSelectedColor(controller.getSelectedPrefIndex());
		colorSelectionView.setOnColorSelectedListener(this);
		builder.setView(colorSelectionView);
	}

	@Override
	protected ColorSelectionController createController(AttributeSet attrs) {
		return new ColorSelectionController(this, attrs);
	}

	@Override
	protected void createEntryView() {
		inflate(context, R.layout.entry_summary, this);
		TextView titleView = (TextView) findViewById(R.id.title);
		titleView.setText(controller.getTitle());
	}

	@Override
	public void onColorSelected(int color, int position) {
		Log.i(getClass().getSimpleName(), "color: " + Integer.toString(color));
		Log.i(getClass().getSimpleName(), "position: " + Integer.toString(position));
	}
}
