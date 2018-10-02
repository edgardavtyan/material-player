package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistManager;

import java.util.List;

public class PlaylistDetailPresenter implements ListPresenter<PlaylistDetailViewHolder> {
	private final PlaylistDetailActivity view;
	private final List<Track> playlistItems;

	public PlaylistDetailPresenter(
			PlaylistManager manager,
			PlaylistDetailActivity view,
			String playlistName) {
		this.view = view;
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
	public void onDestroy() {

	}

	@Override
	public void onCreate() {

	}
}
