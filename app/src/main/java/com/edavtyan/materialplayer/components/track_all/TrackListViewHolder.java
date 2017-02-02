package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.list.ListViewHolder;
import com.edavtyan.materialplayer.utils.DurationUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class TrackListViewHolder extends ListViewHolder {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;

	private final Context context;
	private final TrackListMvp.Presenter presenter;

	public TrackListViewHolder(Context context, View itemView, TrackListMvp.Presenter presenter) {
		super(context, itemView);
		this.context = context;
		this.presenter = presenter;
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
		presenter.onHolderClick(getAdapterPositionNonFinal());
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_to_playlist:
			presenter.onAddToPlaylist(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}
