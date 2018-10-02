package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;

import butterknife.BindView;
import lombok.Setter;

public class PlaylistAddDialog extends BaseDialog {
	@BindView(R.id.playlist_button_new) Button newButton;
	@BindView(R.id.list) RecyclerView list;

	private final PlaylistAddDialogListAdapter adapter;

	public interface OnNewPlaylistClickListener {
		void onNewPlaylistClick();
	}

	private @Setter OnNewPlaylistClickListener onNewPlaylistClickListener;

	public PlaylistAddDialog(Context context) {
		super(context);

		adapter = new PlaylistAddDialogListAdapter(context);
		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(context));

		newButton.setOnClickListener(v -> {
			if (onNewPlaylistClickListener != null) {
				onNewPlaylistClickListener.onNewPlaylistClick();
			}
		});
	}

	public void show(String[] playlistNames) {
		adapter.update(playlistNames);
		show();
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_add_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_add_action;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_playlist_add;
	}

	@Override
	public void onShow() {
		newButton.getBackground().setColorFilter(getTint(), PorterDuff.Mode.SRC_ATOP);
	}
}
