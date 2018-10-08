package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistManager;

import java.util.List;

public class PlaylistDetailPresenter implements ListPresenter<PlaylistDetailViewHolder> {
	private final PlaylistDetailActivity view;
	private final PlaylistManager manager;
	private final String playlistName;

	private List<Track> playlistItems;

	public PlaylistDetailPresenter(
			PlaylistManager manager,
			PlaylistDetailActivity view,
			String playlistName) {
		this.manager = manager;
		this.view = view;
		this.playlistName = playlistName;
		this.playlistItems = manager.load(playlistName);
	}

	@Override
	public void onBindViewHolder(PlaylistDetailViewHolder holder, int position) {
		holder.setText(playlistItems.get(position).getTitle());
	}

	@Override
	public int getItemCount() {
		return playlistItems.size();
	}

	@Override
	public void onCreate() {
		manager.bind();
	}

	@Override
	public void onDestroy() {
		manager.unbind();
	}

	public void onItemClick(int position) {
		manager.playPlaylist(playlistName, position);
	}

	public void onRemoveFromPlaylistMenuClick(int position) {
		manager.removeTrack(playlistName, position);
		playlistItems.remove(position);
		view.notifyItemRemoved(position);
	}

	public void onItemDragDropMove(int fromPosition, int toPosition) {
		manager.moveTrack(playlistName, fromPosition, toPosition);
		playlistItems = manager.load(playlistName);
		view.notifyItemMoved(fromPosition, toPosition);
	}

	public void onItemSwipeRemove(int position) {
		onRemoveFromPlaylistMenuClick(position);
	}

	public void onHandleDrag(PlaylistDetailViewHolder holder) {
		view.onHandleDrag(holder);
	}
}
