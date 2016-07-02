package com.edavtyan.materialplayer.views.trackslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

public abstract class TracksViewHolder extends RecyclerView.ViewHolder {
	protected final Context context;
	protected final View itemView;
	protected final PopupMenu popupMenu;

	protected final TextView titleView;
	protected final ImageButton menuButton;
	protected final TextView infoView;

	//---

	public TracksViewHolder(Context context, View itemView) {
		super(itemView);

		this.context = context;
		this.itemView = itemView;

		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
		menuButton = (ImageButton) itemView.findViewById(R.id.menu);

		popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_track);
		menuButton.setOnClickListener(view -> popupMenu.show());
	}

	//---

	public abstract void setInfo(long duration, String artist, String album);

	//---

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setOnClickListener(View.OnClickListener listener) {
		itemView.setOnClickListener(listener);
	}

	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		popupMenu.setOnMenuItemClickListener(listener);
	}
}
