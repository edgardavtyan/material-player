package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
	private final ItemTouchHelperAdapter adapter;

	public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return true;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return true;
	}

	@Override
	public boolean onMove(
			RecyclerView recyclerView,
			RecyclerView.ViewHolder viewHolder,
			RecyclerView.ViewHolder target) {
		adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}

	@Override
	public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
		adapter.onItemDismiss(viewHolder.getAdapterPosition());
	}
}
