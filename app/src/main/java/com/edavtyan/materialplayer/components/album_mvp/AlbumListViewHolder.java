package com.edavtyan.materialplayer.components.album_mvp;

import android.content.Context;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.BaseViewHolder;

import lombok.Setter;

public class AlbumListViewHolder
		extends BaseViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	private final Context context;
	private final TextView titleView;
	private final TextView infoView;
	private final ImageView artView;
	private final ImageButton menuButton;
	private final View itemView;
	private final PopupMenu popupMenu;
	private @Setter OnHolderClickListener onHolderClickListener;
	private @Setter OnHolderMenuItemClickListener onHolderMenuItemClickListener;

	public interface OnHolderClickListener {
		void onHolderClick(AlbumListViewHolder holder);
	}

	public interface OnHolderMenuItemClickListener {
		void onMenuAddToPlaylistClick(AlbumListViewHolder holder);
	}

	public AlbumListViewHolder(Context context, View itemView) {
		super(itemView);

		this.context = context;

		this.itemView = itemView;
		this.itemView.setOnClickListener(this);

		titleView = findView(R.id.title);
		infoView = findView(R.id.info);
		artView = findView(R.id.art);
		menuButton = findView(R.id.menu);

		popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_track);
		popupMenu.setOnMenuItemClickListener(this);

		menuButton.setOnClickListener(view -> popupMenu.show());
	}

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
				.load(artPath)
				.error(R.drawable.fallback_cover_listitem)
				.into(artView);
	}

	@Override
	public void onClick(View v) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(this);
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		if (onHolderMenuItemClickListener == null) return false;

		switch (item.getItemId()) {
		case R.id.menu_addToPlaylist:
			onHolderMenuItemClickListener.onMenuAddToPlaylistClick(this);
			return true;
		default:
			return false;
		}
	}
}
