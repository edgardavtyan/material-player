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

	public NowPlayingActivity getActivity() {
		return activity;
	}

	public NowPlayingMvp.View getView() {
		return view;
	}

	public NowPlayingMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new NowPlayingPresenter(getModel(), getView());
		return presenter;
	}

	public NowPlayingMvp.Model getModel() {
		if (model == null)
			model = new NowPlayingModel(getActivity(), getArtProvider());
		return model;
	}

	public NowPlayingMvp.View.Controls getControls() {
		if (controls == null)
			controls = new NowPlayingControls(
					getActivity(),
					getPresenter());

		return controls;
	}

	public NowPlayingMvp.View.Info getInfo() {
		if (info == null)
			info = new NowPlayingInfo(getActivity());
		return info;
	}

	public NowPlayingMvp.View.Art getArt() {
		if (art == null)
			art = new NowPlayingArt(getActivity());
		return art;
	}

	public NowPlayingMvp.View.Seekbar getSeekbar() {
		if (seekbar == null)
			seekbar = new NowPlayingSeekbar(getActivity(), getPresenter());
		return seekbar;
	}

	public NowPlayingMvp.View.Fab getFab() {
		if (fab == null)
			fab = new NowPlayingFab(getActivity(), getPresenter());
		return fab;
	}
}
