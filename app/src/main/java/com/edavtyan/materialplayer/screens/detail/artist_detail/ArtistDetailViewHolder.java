package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListViewHolder;

import butterknife.BindView;

public class ArtistDetailViewHolder extends AlbumListViewHolder {
	@BindView(R.id.info) TextView infoView;

	private final Context context;

	public ArtistDetailViewHolder(
			Context context, View itemView, ArtistDetailPresenter presenter, SdkFactory sdkFactory) {
		super(context, itemView, presenter, sdkFactory);
		this.context = context;
	}

	@Override
	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String info = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		infoView.setText(info);
	}
}
