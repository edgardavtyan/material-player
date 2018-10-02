package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lombok.Setter;

public class PlaylistAddDialogViewHolder extends RecyclerView.ViewHolder {
	private final TextView itemView;

	public interface OnClickListener {
		void onClick(int position);
	}

	private @Setter OnClickListener onClickListener;

	public PlaylistAddDialogViewHolder(View itemView) {
		super(itemView);
		this.itemView = (TextView) itemView;
		this.itemView.setOnClickListener(v -> onClickListener.onClick(getAdapterPosition()));
	}

	public void setText(String text) {
		itemView.setText(text);
	}
}
