package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.playlist.dialogs.PlaylistDeleteDialog;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.ui.detail.playlist_detail.PlaylistDetailActivity;
import com.edavtyan.materialplayer.ui.lists.lib.ListFragment;

import javax.inject.Inject;

public class PlaylistListFragment extends ListFragment {
	@Inject PlaylistListPresenter presenter;
	@Inject PlaylistListAdapter adapter;
	@Inject PlaylistDeleteDialog deleteDialog;
	@Inject ScreenThemeModule screenThemeModule;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
		addModule(screenThemeModule);
	}

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		deleteDialog.setTint(colors.getColorPrimary());
	}

	public void gotoPlaylistDetail(String playlistName) {
		Intent intent = new Intent(getActivity(), PlaylistDetailActivity.class);
		intent.putExtra(PlaylistDetailActivity.EXTRA_PLAYLIST_NAME, playlistName);
		startActivity(intent);
	}

	public void showDeletePlaylistDialog() {
		deleteDialog.show();
	}

	public void notifyItemRemoved(int position) {
		adapter.notifyItemRemoved(position);
	}

	private PlaylistListDIComponent getComponent() {
		return DaggerPlaylistListDIComponent
				.builder()
				.appDIComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.playlistListDIModule(new PlaylistListDIModule(this))
				.build();
	}
}
