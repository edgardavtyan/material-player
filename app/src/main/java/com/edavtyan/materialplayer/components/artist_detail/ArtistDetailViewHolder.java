package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_all.AlbumListViewHolder;

import butterknife.BindView;

public class ArtistDetailViewHolder extends AlbumListViewHolder {
	@BindView(R.id.info) TextView infoView;

	private final Context context;

	public ArtistDetailViewHolder(Context context, View itemView, ArtistDetailMvp.Presenter presenter) {
		super(context, itemView, presenter);
		this.context = context;
	}

	@Override
	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String info = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		infoView.setText(info);
	}
}
