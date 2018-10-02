package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseActivity;
import com.edavtyan.materialplayer.lib.layout_managers.AnimatingLinearLayoutManager;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingQueueActivity extends BaseActivity implements ListView {

	@BindView(R.id.list) RecyclerView list;

	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject ScreenThemeModule themeModule;

	@Inject NowPlayingQueuePresenter presenter;
	@Inject NowPlayingQueueAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queue);
		ButterKnife.bind(this);

		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);

		adapter.setHasStableIds(true);

		list.setLayoutManager(new AnimatingLinearLayoutManager(this));
		list.setAdapter(adapter);

		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public void notifyDataSetChanged() {
		adapter.notifyDataSetChangedNonFinal();
	}

	public void removeItem(int position) {
		adapter.notifyItemRemoved(position);
	}

	protected NowPlayingQueueDIComponent getComponent() {
		return DaggerNowPlayingQueueDIComponent
				.builder()
				.appDIComponent(((App)getApplication()).getAppComponent())
				.nowPlayingQueueDIModule(new NowPlayingQueueDIModule(this))
				.build();
	}
}
