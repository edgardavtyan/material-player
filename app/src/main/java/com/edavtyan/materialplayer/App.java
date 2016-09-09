package com.edavtyan.materialplayer;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailFactory;
import com.edavtyan.materialplayer.components.album_all.AlbumListFactory;
import com.edavtyan.materialplayer.components.album_all.AlbumListMvp;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailFactory;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailMvp;
import com.edavtyan.materialplayer.components.artist_all.ArtistListFactory;
import com.edavtyan.materialplayer.components.artist_all.ArtistListMvp;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingFactory;
import com.edavtyan.materialplayer.components.now_playing_floating.NowPlayingFloatingMvp;
import com.edavtyan.materialplayer.components.track_all.TrackListFactory;
import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.utils.logging.FileLogger;
import com.edavtyan.materialplayer.utils.logging.Logger;

import lombok.Getter;

public class App
		extends Application
		implements Thread.UncaughtExceptionHandler {
	private Logger logger;

	//---

	private @Getter static Context context;

	public AlbumListFactory getAlbumListDI(Context context, AlbumListMvp.View view) {
		return new AlbumListFactory(context, view);
	}

	public AlbumDetailFactory getAlbumDetailDI(AlbumDetailActivity albumDetailActivity, int albumId) {
		return new AlbumDetailFactory(albumDetailActivity, albumId);
	}

	public ArtistListFactory getArtistListDI(Context context, ArtistListMvp.View view) {
		return new ArtistListFactory(context, view);
	}

	public ArtistDetailFactory getArtistDetailDI(
			Context context,
			ArtistDetailMvp.View view,
			String artistTitle) {
		return new ArtistDetailFactory(context, view, artistTitle);
	}

	public TrackListFactory getTrackListDI(Context context, TrackListMvp.View view) {
		return new TrackListFactory(context, view);
	}

	public NowPlayingFloatingFactory getNowPlayingFloatingFactory(
			Context context,
			NowPlayingFloatingMvp.View view) {
		return new NowPlayingFloatingFactory(context, view);
	}

	//---

	@Override
	public void onCreate() {
		super.onCreate();
		logger = new FileLogger();
		context = getApplicationContext();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	//---

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		logger.log(throwable);
		Log.e("MaterialPlayer", "Uncaught exception", throwable);
		System.exit(1);
	}
}
