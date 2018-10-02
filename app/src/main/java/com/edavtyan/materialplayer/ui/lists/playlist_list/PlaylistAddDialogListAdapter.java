package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;

public class PlaylistAddDialogListAdapter
		extends RecyclerView.Adapter<PlaylistAddDialogViewHolder> {

	private final Context context;

	private String[] items;

	public PlaylistAddDialogListAdapter(Context context) {
		this.context = context;
	}

	public void update(String[] items) {
		this.items = items;
		notifyDataSetChanged();
	}

	@Override
	public PlaylistAddDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.listitem_text, parent, false);
		return new PlaylistAddDialogViewHolder(view);
	}

	@Override
	public void onBindViewHolder(PlaylistAddDialogViewHolder holder, int position) {
		holder.setText(items[position]);
	}

	@Override
	public int getItemCount() {
		return items.length;
	}
}
