package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.util.Pair;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.testable.TestableViewHolder;
import com.edavtyan.materialplayer.lib.transition.SourceSharedViews;
import com.edavtyan.materialplayer.modular.viewholder.ContextMenuModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistListViewHolder
		extends TestableViewHolder
		implements View.OnClickListener,
				   PopupMenu.OnMenuItemClickListener {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;
	@BindView(R.id.art) ImageView artView;
	@BindView(R.id.menu) ImageButton menuButton;

	private final Context context;
	private final ArtistListPresenter presenter;

	public ArtistListViewHolder(
			Context context,
			View itemView,
			ArtistListPresenter presenter,
			ContextMenuModule contextMenu) {
		super(itemView);
		this.context = context;
		this.presenter = presenter;

		ButterKnife.bind(this, itemView);

		itemView.setOnClickListener(this);

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

	public void setImage(String artLink) {
		RequestOptions options = new RequestOptions()
				.error(R.drawable.fallback_artist)
				.placeholder(R.drawable.fallback_artist);

		Glide.with(context)
			 .load(artLink)
			 .apply(options)
			 .transition(DrawableTransitionOptions.withCrossFade())
			 .into(artView);
	}

	@Override
	public void onClick(View v) {
		SourceSharedViews sharedViews = new SourceSharedViews(Pair.create(artView, "art"));
		sharedViews.setOnExitAnimationEndListener(presenter::onEnterTransitionFinished);
		presenter.onHolderClick(getAdapterPositionNonFinal(), sharedViews);
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_to_queue:
			presenter.onAddToQueue(getAdapterPositionNonFinal());
			return true;
		case R.id.menu_add_to_playlist:
			presenter.onAddToPlaylist(getAdapterPositionNonFinal());
			return true;
		default:
			return false;
		}
	}
}
