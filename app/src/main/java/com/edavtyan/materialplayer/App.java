package com.edavtyan.materialplayer;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.edavtyan.materialplayer.components.album_detail.AlbumDetailActivity;
import com.edavtyan.materialplayer.components.album_detail.AlbumDetailDI;
import com.edavtyan.materialplayer.components.album_all.AlbumListDI;
import com.edavtyan.materialplayer.components.album_all.AlbumListMvp;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailDI;
import com.edavtyan.materialplayer.components.artist_detail.ArtistDetailMvp;
import com.edavtyan.materialplayer.components.artist_all.ArtistListDI;
import com.edavtyan.materialplayer.components.artist_all.ArtistListMvp;
import com.edavtyan.materialplayer.components.track_all.TrackListDI;
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

	public AlbumListDI getAlbumListDI(Context context, AlbumListMvp.View view) {
		return new AlbumListDI(context, view);
	}

	public AlbumDetailDI getAlbumDetailDI(AlbumDetailActivity albumDetailActivity, int albumId) {
		return new AlbumDetailDI(albumDetailActivity, albumId);
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
