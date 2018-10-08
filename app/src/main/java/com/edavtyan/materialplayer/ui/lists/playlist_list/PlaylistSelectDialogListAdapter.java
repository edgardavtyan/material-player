package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class PlaylistSelectDialogListAdapter
		extends RecyclerView.Adapter<PlaylistSelectDialogViewHolder> {

	private final Context context;

	private @Setter PlaylistSelectDialogViewHolder.OnClickListener onClickListener;

	private String[] items;

	public PlaylistSelectDialogListAdapter(Context context) {
		this.context = context;
	}

	public void update(String[] items) {
		this.items = items;
		notifyDataSetChanged();
	}

	@Override
	public PlaylistSelectDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_text, parent, false);
		return new PlaylistSelectDialogViewHolder(view);
	}

	@Override
	public void onBindViewHolder(PlaylistSelectDialogViewHolder holder, int position) {
		holder.setText(items[position]);
		holder.setOnClickListener(p -> onClickListener.onClick(p));
	}

	@Override
	public int getItemCount() {
		return items.length;
	}
}
