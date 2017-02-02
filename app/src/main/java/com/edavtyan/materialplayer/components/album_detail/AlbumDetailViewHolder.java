package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.components.track_all.TrackListViewHolder;
import com.edavtyan.materialplayer.utils.DurationUtils;

import butterknife.BindView;

public class AlbumDetailViewHolder extends TrackListViewHolder {
	@BindView(R.id.info) TextView infoView;

	public AlbumDetailViewHolder(Context context, View itemView, TrackListMvp.Presenter presenter) {
		super(context, itemView, presenter);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		infoView.setText(DurationUtils.toStringUntilHours(duration));
	}
}
