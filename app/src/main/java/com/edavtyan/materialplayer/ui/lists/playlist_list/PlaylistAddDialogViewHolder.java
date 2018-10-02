package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PlaylistAddDialogViewHolder extends RecyclerView.ViewHolder {
	private final TextView itemView;

	public PlaylistAddDialogViewHolder(View itemView) {
		super(itemView);
		this.itemView = (TextView) itemView;
	}

	public void setText(String text) {
		itemView.setText(text);
	}
}
