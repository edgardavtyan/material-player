package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class ArtistListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	private final TextView titleView;
	private final TextView infoView;
	private @Setter OnHolderClickListener onHolderClickListener;

	public interface OnHolderClickListener {
		void onHolderClick(ArtistListViewHolder holder, int position);
	}

	public ArtistListViewHolder(View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);
		titleView = (TextView) itemView.findViewById(R.id.title);
		infoView = (TextView) itemView.findViewById(R.id.info);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(int albumsCount, int tracksCount) {
		Resources res = App.getContext().getResources();
		String albumsCountStr = res.getQuantityString(R.plurals.albums, albumsCount, albumsCount);
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_artist_info, albumsCountStr, tracksCountStr);
		infoView.setText(info);
	}

	@Override
	public void onClick(View v) {
		if (onHolderClickListener != null) {
			onHolderClickListener.onHolderClick(this, getAdapterPosition());
		}
	}
}
