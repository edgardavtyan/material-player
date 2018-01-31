package com.edavtyan.materialplayer.screens.lists.artist_list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.screens.Navigator;
import com.edavtyan.materialplayer.screens.detail.artist_detail.ArtistDetailSharedViews;
import com.edavtyan.materialplayer.screens.lists.lib.ListFragment;

import javax.inject.Inject;

public class ArtistListFragment extends ListFragment implements ArtistListView {

	@Inject Navigator navigator;
	@Inject ArtistListPresenter presenter;
	@Inject ArtistListAdapter adapter;

	public class OnNextActivityCreatedListener {
		private final ArtistDetailSharedViews sharedViews;

		public OnNextActivityCreatedListener(ArtistDetailSharedViews sharedViews) {
			this.sharedViews = sharedViews;
		}

		public void hide() {
			sharedViews.hide();
		}

		public void show() {
			sharedViews.show();
		}
	}

	public static OnNextActivityCreatedListener onNextActivityCreatedListener;
	public static ArtistDetailSharedViews sharedViews;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getComponent().inject(this);
		initListView(presenter, adapter);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (sharedViews != null) sharedViews.show();
	}

	@Override
	public void gotoArtistDetailNormal(String title) {
		navigator.gotoArtistDetailNormal(title);
	}

	@Override
	public void gotoArtistDetailCompact(String title, ArtistDetailSharedViews sharedViews) {
		onNextActivityCreatedListener = new OnNextActivityCreatedListener(sharedViews);
		ArtistListFragment.sharedViews = sharedViews;
		navigator.gotoArtistDetailCompact(getActivity(), title, sharedViews.build());

	}

	protected ArtistListComponent getComponent() {
		return DaggerArtistListComponent
				.builder()
				.appComponent(((App) getContext().getApplicationContext()).getAppComponent())
				.artistListFactory(new ArtistListFactory(getActivity(), this))
				.build();
	}
}
