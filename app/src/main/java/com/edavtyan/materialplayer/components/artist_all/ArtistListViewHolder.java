package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseViewHolder;

import butterknife.BindView;

public class ArtistListViewHolder extends BaseViewHolder implements View.OnClickListener {
	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;

	private final Context context;
	private final ArtistListMvp.Presenter presenter;

	public ArtistListViewHolder(Context context, View itemView, ArtistListMvp.Presenter presenter) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;
		itemView.setOnClickListener(this);
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

	public void setImage(Bitmap art) {
		if (art == null) {
			artView.setImageResource(R.drawable.fallback_artist);
		} else {
			artView.setImageBitmap(art);
		}
	}

	@Override
	public void onClick(View v) {
		presenter.onHolderClick(getAdapterPositionNonFinal());
	}
}
