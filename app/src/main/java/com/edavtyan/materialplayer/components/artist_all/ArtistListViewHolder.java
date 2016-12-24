package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import lombok.Setter;

public class ArtistListViewHolder extends BaseViewHolder implements View.OnClickListener {
	private final TextView titleView;
	private final TextView infoView;
	private final Context context;
	private @Setter OnHolderClickListener onHolderClickListener;

	public interface OnHolderClickListener {
		void onHolderClick(ArtistListViewHolder holder);
	}

	public ArtistListViewHolder(Context context, View itemView) {
		super(itemView);
		this.context = context;
		itemView.setOnClickListener(this);
		titleView = findView(R.id.title);
		infoView = findView(R.id.info);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(int albumsCount, int tracksCount) {
		Resources res = context.getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		infoView.setText(info);
	}

	@Override
	public void onClick(View v) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(this);
		}
	}
}
