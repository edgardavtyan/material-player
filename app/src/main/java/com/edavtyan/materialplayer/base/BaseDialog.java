package com.edavtyan.materialplayer.base;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import butterknife.ButterKnife;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class BaseDialog {
	private final AlertDialog dialog;

	@SuppressWarnings("FieldCanBeLocal")
	private final DialogInterface.OnShowListener onShowListener
			= new DialogInterface.OnShowListener() {
		@Override
		public void onShow(DialogInterface dialogInterface) {
			dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(tint);
			dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(tint);
			dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(tint);
			BaseDialog.this.onShow();
		}
	};

	public interface OnDismissListener {
		void onDismiss();
	}

	private @Setter OnDismissListener onDismissListener;

	private @Setter @Getter(AccessLevel.PROTECTED) int tint;

	private @Setter boolean showKeyboardEnabled;

	@StringRes
	public abstract int getDialogTitleRes();

	@StringRes
	public abstract int getPositiveButtonTextRes();

	@LayoutRes
	public abstract int getLayoutRes();

	public BaseDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(getLayoutRes(), null, false);
		ButterKnife.bind(this, view);

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(getDialogTitleRes())
				.setPositiveButton(getPositiveButtonTextRes(), (d, w) -> onConfirm())
				.setNegativeButton(android.R.string.cancel, null)
				.setView(view);

		if (getNeutralButtonTextRes() != -1) {
			builder.setNeutralButton(getNeutralButtonTextRes(), (d, w) -> onNeutral());
		}

		dialog = builder.create();
		dialog.setOnShowListener(onShowListener);
		dialog.setOnDismissListener(d -> onDismiss());
	}

	@StringRes
	public int getNeutralButtonTextRes() {
		return -1;
	}

	public void onShow() {}

	public void onConfirm() {}

	public void onNeutral() {}

	public void onDismiss() {
		if (onDismissListener != null) onDismissListener.onDismiss();
	}

	public void show() {
		if (showKeyboardEnabled) {
			dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		}

		dialog.show();
	}

	public void close() {
		dialog.dismiss();
	}
}
