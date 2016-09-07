package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;
import com.edavtyan.materialplayer.utils.DurationUtils;

import lombok.Setter;

public class TrackListViewHolder
		extends BaseViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	private final Context context;
	private final TextView titleView;
	private final TextView infoView;
	private final ImageButton menuButton;

	private @Setter OnHolderClickListener onHolderClickListener;
	private @Setter OnHolderMenuItemClickListener onHolderMenuItemClickListener;

	public interface OnHolderClickListener {
		void onHolderClick(TrackListViewHolder holder);
	}

	public interface OnHolderMenuItemClickListener {
		void onAddToPlaylistMenuItemClick(TrackListViewHolder holder);
	}

	public TrackListViewHolder(Context context, View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);
		this.context = context;
		this.titleView = findView(R.id.title);
		this.infoView = findView(R.id.info);
		this.menuButton = findView(R.id.menu);

		PopupMenu popupMenu = new PopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_track);
		popupMenu.setOnMenuItemClickListener(this);
		menuButton.setOnClickListener(v -> popupMenu.show());
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(long duration, String artist, String album) {
		String durationStr = DurationUtils.toStringUntilHours(duration);
		String info = context.getString(R.string.pattern_track_info, durationStr, artist, album);
		infoView.setText(info);
	}

	@Override
	public void onClick(View v) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(this);
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_addToPlaylist:
			onHolderMenuItemClickListener.onAddToPlaylistMenuItemClick(this);
			return true;
		default:
			return false;
		}
	}
}
