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

	public BaseDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(getDialogTitleRes())
				.setNegativeButton(android.R.string.cancel, null);

		if (getPositiveButtonTextRes() != -1) {
			if (closeWhenPositiveButtonClicked()) {
				builder.setPositiveButton(getPositiveButtonTextRes(), (d, w) -> onPositiveButtonClick());
			} else {
				builder.setPositiveButton(getPositiveButtonTextRes(), null);
			}
		}

		if (getLayoutRes() != -1) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(getLayoutRes(), null, false);
			ButterKnife.bind(this, view);
			builder.setView(view);
		}

		if (getMessageRes() != -1) {
			builder.setMessage(getMessageRes());
		}

		dialog = builder.create();
		dialog.setOnShowListener(onShowListener);
		dialog.setOnDismissListener(d -> onDismiss());
	}

	@LayoutRes
	public int getLayoutRes() {
		return -1;
	}

	@StringRes
	public int getPositiveButtonTextRes() {
		return -1;
	}

	@StringRes
	public int getMessageRes() {
		return -1;
	}

	public boolean closeWhenPositiveButtonClicked() {
		return true;
	}

	public void onShow() {}

	public void onPositiveButtonClick() {}

	public void onDismiss() {
		if (onDismissListener != null) onDismissListener.onDismiss();
	}

	public void show() {
		if (showKeyboardEnabled && dialog.getWindow() != null) {
			dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		}

		dialog.show();

		if (!closeWhenPositiveButtonClicked()) {
			dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> onPositiveButtonClick());
		}
	}

	public void close() {
		dialog.dismiss();
	}
}
