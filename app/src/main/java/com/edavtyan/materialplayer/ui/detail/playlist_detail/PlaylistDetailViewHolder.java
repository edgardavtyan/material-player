package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistDetailViewHolder
		extends RecyclerView.ViewHolder
		implements ItemTouchHelperViewHolder, View.OnClickListener {

	@BindView(R.id.title) TextView textView;
	@BindView(R.id.menu) ImageButton menuButton;
	@BindView(R.id.handle) ImageView handle;
	@BindView(R.id.background) View backgroundView;

	private final PlaylistDetailPresenter presenter;

	public PlaylistDetailViewHolder(View itemView, PlaylistDetailPresenter presenter) {
		super(itemView);
		this.presenter = presenter;
		ButterKnife.bind(this, itemView);
		itemView.setOnClickListener(this);

		PopupMenu menu = new PopupMenu(itemView.getContext(), menuButton);
		menu.inflate(R.menu.menu_playlist);
		menu.setOnMenuItemClickListener(this::onMenuItemClick);
		menuButton.setOnClickListener(v -> menu.show());

		handle.setOnTouchListener((view, event) -> {
			if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
				presenter.onHandleDrag(this);
			}
			return false;
		});
	}

	public void setText(String text) {
		textView.setText(text);
	}

	protected boolean onMenuItemClick(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case R.id.menu_remove:
			presenter.onRemoveFromPlaylistMenuClick(getAdapterPosition());
			return true;
		}
		return false;
	}

	@Override
	public void onItemSelected() {
		backgroundView.animate().alpha(1);
	}

	@Override
	public void onItemClear() {
		backgroundView.animate().alpha(0);
	}

	@Override
	public void onClick(View v) {
		presenter.onItemClick(getAdapterPosition());
	}
}
