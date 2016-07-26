package com.example.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.support.v7.app.AlertDialog;

import com.example.custompreference.utils.PixelConverter;

public abstract class DialogPreference<TController extends BaseController>
		extends BasePreference<TController>
		implements View.OnClickListener {

	private AlertDialog dialog = null;


	public DialogPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	public DialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOnClickListener(this);
	}


	protected abstract void createDialogBuilder(AlertDialog.Builder builder);


	protected void closeDialog() {
		dialog.dismiss();
	}


	@Override
	public void onClick(View v) {
		if (dialog == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle(controller.getTitle());
			builder.setNegativeButton(android.R.string.cancel, null);
			createDialogBuilder(builder);
			dialog = builder.create();
		}

		dialog.show();
		dialog.getWindow().setLayout(
				PixelConverter.dpToPx(320),
				LayoutParams.WRAP_CONTENT);
	}
}
