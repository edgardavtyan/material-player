package com.edavtyan.materialplayer;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListDI;
import com.edavtyan.materialplayer.components.album_mvp.AlbumListMvp;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistDetailDI;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistDetailMvp;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListDI;
import com.edavtyan.materialplayer.components.artist_mvp.ArtistListMvp;
import com.edavtyan.materialplayer.components.track_mvp.TrackListDI;
import com.edavtyan.materialplayer.components.track_mvp.TrackListMvp;
import com.edavtyan.materialplayer.utils.logging.FileLogger;
import com.edavtyan.materialplayer.utils.logging.Logger;

import lombok.Getter;

public class App
		extends Application
		implements Thread.UncaughtExceptionHandler {
	private Logger logger;

	//---

	private @Getter static Context context;

	public AlbumListDI getAlbumListDI(Context context, AlbumListMvp.View view) {
		return new AlbumListDI(context, view);
	}

	public ArtistListDI getArtistListDI(Context context, ArtistListMvp.View view) {
		return new ArtistListDI(context, view);
	}

	public ArtistDetailDI getArtistDetailDI(
			Context context,
			ArtistDetailMvp.View view,
			String artistTitle) {
		return new ArtistDetailDI(context, view, artistTitle);
	}

	public TrackListDI getTrackListDI(Context context, TrackListMvp.View view) {
		return new TrackListDI(context, view);
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
