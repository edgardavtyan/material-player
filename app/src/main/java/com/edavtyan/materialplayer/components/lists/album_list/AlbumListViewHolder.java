package com.edavtyan.materialplayer.components.lists.album_list;

import android.content.Context;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListViewHolder;

import butterknife.BindView;

public class AlbumListViewHolder extends ListViewHolder {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;

	private final Context context;
	private final AlbumListMvp.Presenter presenter;

	public AlbumListViewHolder(Context context, View itemView, AlbumListMvp.Presenter presenter) {
		super(context, itemView);
		this.context = context;
		this.presenter = presenter;
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(int tracksCount, String artist) {
		Resources res = context.getResources();
		String tracksCountStr = res.getQuantityString(R.plurals.tracks, tracksCount, tracksCount);
		String info = res.getString(R.string.pattern_album_info, artist, tracksCountStr);
		infoView.setText(info);
	}

	public void setArt(String artPath) {
		// TODO: unit test correct artPath

		RequestOptions options = new RequestOptions()
				.error(R.drawable.fallback_cover)
				.placeholder(R.drawable.fallback_cover);

		Glide.with(context)
			 .load(artPath)
			 .apply(options)
			 .into(artView);
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
