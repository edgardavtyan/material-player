package com.edavtyan.materialplayer.ui;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

public class SdkFactory {
	public PopupMenu createPopupMenu(Context context, View anchor) {
		return new PopupMenu(context, anchor);
	}
}
