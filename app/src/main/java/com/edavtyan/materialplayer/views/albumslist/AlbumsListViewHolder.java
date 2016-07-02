package com.edavtyan.materialplayer.views.albumslist;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.models.ArtProvider;

public class AlbumsListViewHolder extends RecyclerView.ViewHolder {
	private final Context context;

	private final TextView titleView;
	private final TextView infoView;
	private final ImageView artView;
	private final ImageButton menuButton;

	private final View itemView;
	private final PopupMenu popupMenu;

	//---

	public AlbumsListViewHolder(Context context, View itemView) {
		super(itemView);

		this.context = context;
		this.itemView = itemView;

		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
		artView = (ImageView) itemView.findViewById(R.id.art);
		menuButton = (ImageButton) itemView.findViewById(R.id.menu);

		popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_track);
		menuButton.setOnClickListener(view -> popupMenu.show());
	}

	//---

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_album_info, artist, tracksCountStr);
		infoView.setText(info);
	}

	public void setArt(String artPath) {
		Glide.with(context)
				.load(ArtProvider.fromPath(artPath))
				.error(R.drawable.fallback_cover_listitem)
				.into(artView);
	}

	public void setOnClickListener(View.OnClickListener listener) {
		itemView.setOnClickListener(listener);
	}

	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		popupMenu.setOnMenuItemClickListener(listener);
	}
}
