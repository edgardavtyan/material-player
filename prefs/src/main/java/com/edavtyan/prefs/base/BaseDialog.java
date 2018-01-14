package com.edavtyan.prefs.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;

import com.edavtyan.prefs.utils.PixelConverter;

public class BaseDialog implements DialogInterface.OnShowListener {
	private final AlertDialog dialog;

	private int buttonsColor;

	public BaseDialog(Context context) {
		dialog = new AlertDialog.Builder(context)
				.setNegativeButton(android.R.string.cancel, null)
				.create();
		dialog.setOnShowListener(this);
	}

	public void setTitle(String title) {
		dialog.setTitle(title);
	}

	public void setView(View view) {
		dialog.setView(view);
	}

	public void show() {
		dialog.show();
		dialog.getWindow().setLayout(
				PixelConverter.dpToPx(320),
				FrameLayout.LayoutParams.WRAP_CONTENT);
	}

	public void dismiss() {
		dialog.dismiss();
	}

	public void setButtonsColor(int color) {
		buttonsColor = color;
	}

	@Override
	public void onShow(DialogInterface argDialog) {
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(buttonsColor);
		dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(buttonsColor);
	}
}
