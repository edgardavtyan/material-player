package com.edavtyan.materialplayer.components.playlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

public class PlaylistTrackViewHolder extends RecyclerView.ViewHolder {
	private final Context context;
	private final View itemView;
	private final PopupMenu popupMenu;

	private final TextView titleView;
	private final TextView infoView;
	private final ImageButton menuButton;
	private final ImageView nowPlayingIcon;

	//---

	public PlaylistTrackViewHolder(Context context, View itemView) {
		super(itemView);

		this.context = context;
		this.itemView = itemView;

		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
		menuButton = (ImageButton) itemView.findViewById(R.id.menu);
		nowPlayingIcon = (ImageView) itemView.findViewById(R.id.nowPlaying);

		popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_queue);
		menuButton.setOnClickListener(view -> popupMenu.show());
	}

	//---

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String albumTitle) {
		infoView.setText(albumTitle);
	}

	public void setNowPlaying(boolean isNowPlaying) {
		nowPlayingIcon.setVisibility(isNowPlaying ? View.VISIBLE : View.GONE);
	}

	public void setOnClickListener(View.OnClickListener listener) {
		itemView.setOnClickListener(listener);
	}

	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		popupMenu.setOnMenuItemClickListener(listener);
	}
}
