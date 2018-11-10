package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.libsutils.utils.DurationUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer.ui.SdkFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingQueueViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.now_playing) ImageView nowPlayingView;

	private final Context context;
	private final NowPlayingQueuePresenter presenter;

	public NowPlayingQueueViewHolder(
			Context context,
			View itemView,
			NowPlayingQueuePresenter presenter,
			SdkFactory sdkFactory) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;
		ButterKnife.bind(this, itemView);
		itemView.setOnClickListener(this);

		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		contextMenu.init(itemView, R.id.menu, R.menu.menu_queue);
		contextMenu.setOnMenuItemClickListener(this);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(long durationMillis, String artistTitle, String albumTitle) {
		String timeStr = DurationUtils.toStringUntilHours(durationMillis);
		String info = context.getString(R.string.pattern_track_info, timeStr, artistTitle, albumTitle);
		infoView.setText(info);
	}

	public void setIsPlaying(boolean isPlaying) {
		nowPlayingView.setVisibility(isPlaying ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onClick(View v) {
		presenter.onItemClick(getAdapterPositionNonFinal());
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_remove:
			presenter.onRemoveItemClick(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}
