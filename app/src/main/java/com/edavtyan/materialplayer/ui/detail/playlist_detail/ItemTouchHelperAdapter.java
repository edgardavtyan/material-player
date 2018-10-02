package com.edavtyan.materialplayer.ui.detail.playlist_detail;

public interface ItemTouchHelperAdapter {
	void onItemMove(int fromPosition, int toPosition);
	void onItemDismiss(int position);
}
