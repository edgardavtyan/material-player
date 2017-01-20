package com.edavtyan.materialplayer.components.album_all;

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
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;
import lombok.Setter;

public class AlbumListViewHolder
		extends BaseViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.menu) ImageButton menuButton;

	private final Context context;
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
		itemView.setOnClickListener(this);

		popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_track);
		popupMenu.setOnMenuItemClickListener(this);
	}

	@OnClick(R.id.menu)
	public void onMenuClick() {
		popupMenu.show();
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
		// TODO: unit test correct artPath
		Glide.with(context)
			 .load(artPath)
			 .error(R.drawable.fallback_cover)
			 .placeholder(R.drawable.fallback_cover)
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
