package com.edavtyan.custompreference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.support.v7.app.AlertDialog;

import com.edavtyan.custompreference.utils.PixelConverter;

public abstract class DialogPreference<TController extends BaseController>
		extends BasePreference<TController>
		implements View.OnClickListener {

	private AlertDialog dialog = null;
	private final AlertDialog.Builder builder;


	public DialogPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		builder = new AlertDialog.Builder(context);
		onCreateDialogBuilder();
		setOnClickListener(this);
	}

	public DialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		builder = new AlertDialog.Builder(context);
		onCreateDialogBuilder();
		setOnClickListener(this);
	}


	protected abstract View onCreateDialogView();


	protected void closeDialog() {
		dialog.dismiss();
	}

	protected void onCreateDialogBuilder() {
		builder.setTitle(controller.getTitle());
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.setView(onCreateDialogView());
	}


	@Override
	public void onClick(View v) {
		if (dialog == null) {
			dialog = builder.create();
		}

		dialog.show();
		dialog.getWindow().setLayout(
				PixelConverter.dpToPx(320),
				LayoutParams.WRAP_CONTENT);
	}
}
