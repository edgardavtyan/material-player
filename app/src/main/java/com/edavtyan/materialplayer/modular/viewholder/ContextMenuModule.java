package com.edavtyan.materialplayer.modular.viewholder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.view.View;
import android.widget.PopupMenu;

import com.edavtyan.materialplayer.screens.SdkFactory;

public class ContextMenuModule {
	private final Context context;
	private final SdkFactory sdkFactory;

	private PopupMenu popupMenu;

	public ContextMenuModule(
			Context context, SdkFactory sdkFactory) {
		this.context = context;
		this.sdkFactory = sdkFactory;
	}

	public void init(View itemView, @IdRes int anchorId, @MenuRes int menuResId) {
		View anchorView = itemView.findViewById(anchorId);
		popupMenu = sdkFactory.createPopupMenu(context, anchorView);
		popupMenu.inflate(menuResId);
		anchorView.setOnClickListener(v -> popupMenu.show());
	}

	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		popupMenu.setOnMenuItemClickListener(listener);
	}
}
