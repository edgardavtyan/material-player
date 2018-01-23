package com.edavtyan.materialplayer.screens.lists.artist_list;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;
import com.edavtyan.materialplayer.screens.SdkFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistListViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;

	private final Context context;
	private final ArtistListPresenter presenter;

	public ArtistListViewHolder(
			Context context, View itemView, ArtistListPresenter presenter, SdkFactory sdkFactory) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;

		ButterKnife.bind(this, itemView);

		itemView.setOnClickListener(this);

		ContextMenuModule contextMenu = new ContextMenuModule(context, sdkFactory);
		contextMenu.init(itemView, R.id.menu, R.menu.menu_track);
		contextMenu.setOnMenuItemClickListener(this);
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
