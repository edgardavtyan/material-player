package com.edavtyan.materialplayer.components.nowplaying2;

public class NowPlayingPresenter {

	private NowPlayingActivity2 view;
	private NowPlayingModel model;

	public void bind(NowPlayingActivity2 view, NowPlayingModel model) {
		this.view = view;
		this.model = model;
		initView();
	}

	public void initView() {
		view.getInfoView().setTitle(model.getTrackTitle());
		view.getInfoView().setInfo(model.getArtistTitle(), model.getAlbumTitle());
	}
}
