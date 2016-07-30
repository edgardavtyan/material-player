package com.edavtyan.custompreference;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;

import com.edavtyan.custompreference.utils.PixelConverter;

public class BaseDialog {
	private final AlertDialog dialog;

	public BaseDialog(Context context) {
		dialog = new AlertDialog.Builder(context)
				.setNegativeButton(android.R.string.cancel, null)
				.create();
	}

	public void setTitle(CharSequence title) {
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
}
