package com.edavtyan.materialplayer.ui.lists.album_list;

import android.os.AsyncTask;

import com.edavtyan.materialplayer.db.types.Track;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

public class AlbumListImageTask extends AsyncTask<Void, Void, String> {
	private final AlbumArtProvider artProvider;
	private final AlbumArtCallback callback;
	private final AlbumListImageTaskQueue queue;
	private final Track track;
	private final int position;

	public AlbumListImageTask(
			AlbumArtProvider artProvider,
			AlbumArtCallback callback,
			AlbumListImageTaskQueue queue,
			Track track,
			int position) {
		this.artProvider = artProvider;
		this.callback = callback;
		this.queue = queue;
		this.track = track;
		this.position = position;
	}

	@Override
	protected String doInBackground(Void... voids) {
		artProvider.load(track);
		return artProvider.getFilename(track.getAlbumTitle());
	}

	@Override
	protected void onPostExecute(String filename) {
		callback.onLoaded(position);
		queue.onPostExecute();
	}
}
