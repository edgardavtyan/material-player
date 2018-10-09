package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.lists.lib.ListAdapter;

public class NowPlayingQueueAdapter extends ListAdapter<NowPlayingQueueViewHolder> {

	private final NowPlayingQueuePresenter presenter;
	private final SdkFactory sdkFactory;

	public NowPlayingQueueAdapter(
			Context context, NowPlayingQueuePresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public long getItemId(int position) {
		return presenter.getItemId(position);
	}

	@Override
	public NowPlayingQueueViewHolder onCreateViewHolder(Context context, View view) {
		return new NowPlayingQueueViewHolder(context, view, presenter, sdkFactory);
	}
}
