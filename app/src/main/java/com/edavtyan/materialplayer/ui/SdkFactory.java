package com.edavtyan.materialplayer.ui;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.View;

public class SdkFactory {
	public PopupMenu createPopupMenu(Context context, View anchor) {
		return new PopupMenu(context, anchor);
	}
}
