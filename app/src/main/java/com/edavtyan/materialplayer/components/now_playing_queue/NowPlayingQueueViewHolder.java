package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.SdkFactory;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;
import com.edavtyan.materialplayer.utils.DurationUtils;

import lombok.Setter;

public class NowPlayingQueueViewHolder
		extends BaseViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	private final Context context;
	private final TextView titleView;
	private final TextView infoView;

	private @Setter OnHolderClickListener onHolderClickListener;
	private @Setter OnMenuClickListener onMenuClickListener;

	interface OnHolderClickListener {
		void onHolderClick(NowPlayingQueueViewHolder holder);
	}

	interface OnMenuClickListener {
		void onRemoveFromQueueClick(NowPlayingQueueViewHolder holder);
	}

	public NowPlayingQueueViewHolder(Context context, View itemView) {
		super(itemView);

		this.context = context;

		itemView.setOnClickListener(this);

		titleView = findView(R.id.title);
		infoView = findView(R.id.info);

		App app = (App) context.getApplicationContext();
		SdkFactory sdkFactory = app.getSdkFactory();

		ImageButton menuButton = findView(R.id.menu);
		PopupMenu popupMenu = sdkFactory.createPopupMenu(context, menuButton);
		popupMenu.inflate(R.menu.menu_queue);
		popupMenu.setOnMenuItemClickListener(this);
		menuButton.setOnClickListener(v -> popupMenu.show());
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(long durationMillis, String artistTitle, String albumTitle) {
		String timeStr = DurationUtils.toStringUntilHours(durationMillis);
		String info = context.getString(R.string.pattern_track_info, timeStr, artistTitle, albumTitle);
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
		if (onMenuClickListener == null) return false;
		switch (item.getItemId()) {
		case R.id.menu_remove:
			onMenuClickListener.onRemoveFromQueueClick(this);
			return true;
		default:
			return false;
		}
	}
}
