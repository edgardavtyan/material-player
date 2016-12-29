package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.utils.AppColors;

public class NowPlayingFactory extends BaseFactory {
	private final NowPlayingActivity activity;
	private final NowPlayingMvp.View view;

	private NowPlayingMvp.Model model;
	private NowPlayingMvp.Presenter presenter;
	private NowPlayingMvp.View.Controls controls;
	private NowPlayingMvp.View.Info info;
	private NowPlayingMvp.View.Art art;
	private NowPlayingMvp.View.Seekbar seekbar;
	private NowPlayingMvp.View.Fab fab;
	private AppColors appColors;

	public NowPlayingFactory(NowPlayingActivity activity, NowPlayingMvp.View view) {
		super(activity);
		this.activity = activity;
		this.view = view;
	}

	public NowPlayingActivity provideActivity() {
		return activity;
	}

	public NowPlayingMvp.View provideView() {
		return view;
	}

	public NowPlayingMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new NowPlayingPresenter(provideModel(), provideView());
		return presenter;
	}

	public NowPlayingMvp.Model provideModel() {
		if (model == null)
			model = new NowPlayingModel(provideActivity(), provideArtProvider());
		return model;
	}

	public NowPlayingMvp.View.Controls provideControls() {
		if (controls == null)
			controls = new NowPlayingControls(
					provideActivity(),
					providePresenter(),
					provideAppColors());

		return controls;
	}

	public NowPlayingMvp.View.Info provideInfo() {
		if (info == null)
			info = new NowPlayingInfo(provideActivity());
		return info;
	}

	public NowPlayingMvp.View.Art provideArt() {
		if (art == null)
			art = new NowPlayingArt(provideActivity());
		return art;
	}

	public NowPlayingMvp.View.Seekbar provideSeekbar() {
		if (seekbar == null)
			seekbar = new NowPlayingSeekbar(provideActivity(), providePresenter());
		return seekbar;
	}

	public NowPlayingMvp.View.Fab provideFab() {
		if (fab == null)
			fab = new NowPlayingFab(provideActivity(), providePresenter());
		return fab;
	}

	public AppColors provideAppColors() {
		if (appColors == null)
			appColors = new AppColors(provideActivity());
		return appColors;
	}
}
