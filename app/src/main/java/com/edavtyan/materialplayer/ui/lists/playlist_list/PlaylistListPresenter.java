package com.edavtyan.materialplayer.ui.lists.playlist_list;

import com.edavtyan.materialplayer.lib.playlist.models.PlaylistManager;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;

public class PlaylistListPresenter implements ListPresenter<PlaylistListViewHolder> {
	private final PlaylistListFragment view;
	private final PlaylistManager manager;

	private int pendingPlaylistPosition;

	public PlaylistListPresenter(PlaylistListFragment view, PlaylistManager manager) {
		this.view = view;
		this.manager = manager;
	}

	@Override
	public void onBindViewHolder(PlaylistListViewHolder holder, int position) {
		holder.setText(manager.list()[position]);
	}

	@Override
	public int getItemCount() {
		return manager.list().length;
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onCreate() {

	}

	public void onPlaylistClick(int position) {
		view.gotoPlaylistDetail(manager.list()[position]);
	}

	public void onDeletePlaylistMenuItemClicked(int position) {
		view.showDeletePlaylistDialog();
		pendingPlaylistPosition = position;
	}

	public void onDeletePlaylistConfirm() {
		manager.delete(pendingPlaylistPosition);
		view.notifyItemRemoved(pendingPlaylistPosition);
		pendingPlaylistPosition = -1;
	}
}
