package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class NowPlayingFactory extends BaseFactory {
	private final NowPlayingActivity activity;

	private NowPlayingModel model;
	private NowPlayingPresenter presenter;
	private NowPlayingControls controls;
	private NowPlayingInfo info;
	private NowPlayingArt art;
	private NowPlayingSeekbar seekbar;
	private NowPlayingFab fab;

	public NowPlayingFactory(NowPlayingActivity activity) {
		super(activity);
		this.activity = activity;
	}

	public NowPlayingActivity getActivity() {
		return activity;
	}

	public NowPlayingPresenter getPresenter() {
		if (presenter == null)
			presenter = new NowPlayingPresenter(getModel(), activity);
		return presenter;
	}

	public NowPlayingModel getModel() {
		if (model == null)
			model = new NowPlayingModel(getActivity(), getArtProvider());
		return model;
	}

	public NowPlayingControls getControls() {
		if (controls == null)
			controls = new NowPlayingControls(
					getActivity(),
					getPresenter());

		return controls;
	}

	public NowPlayingInfo getInfo() {
		if (info == null)
			info = new NowPlayingInfo(getActivity());
		return info;
	}

	public NowPlayingArt getArt() {
		if (art == null)
			art = new NowPlayingArt(getActivity());
		return art;
	}

	public NowPlayingSeekbar getSeekbar() {
		if (seekbar == null)
			seekbar = new NowPlayingSeekbar(getActivity(), getPresenter());
		return seekbar;
	}

	public NowPlayingFab getFab() {
		if (fab == null)
			fab = new NowPlayingFab(getActivity(), getPresenter());
		return fab;
	}
}
