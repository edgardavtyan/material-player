package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListViewHolder;
import com.ed.libsutils.utils.DurationUtils;

import butterknife.BindView;

public class AlbumDetailViewHolder extends TrackListViewHolder {
	@BindView(R.id.info) TextView infoView;

	public AlbumDetailViewHolder(
			Context context, View itemView, TrackListPresenter presenter, SdkFactory sdkFactory) {
		super(context, itemView, presenter, sdkFactory);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		infoView.setText(DurationUtils.toStringUntilHours(duration));
	}
}
