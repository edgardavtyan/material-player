package com.edavtyan.materialplayer.components.albums;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.components.tracks.TrackDB;
import com.edavtyan.materialplayer.lib.activities.CollapsingHeaderListActivity;
import com.edavtyan.materialplayer.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.utils.ArtProvider;

import java.io.File;

public class AlbumDetailActivity extends CollapsingHeaderListActivity {
	public static final String EXTRA_ALBUM_ID = "album_id";

	private AlbumDetailAdapter tracksAdapter;
	private TrackDB trackDB;

	/*
	 * AsyncTasks
	 */

	private class ImageLoadTask extends AsyncTask<Integer, Void, File> {
		@Override
		protected File doInBackground(Integer... albumIds) {
			Track track = trackDB.getSingleTrackWithAlbumId(albumIds[0]);
			return ArtProvider.fromTrack(track);
		}

		@Override
		protected void onPostExecute(File artFile) {
			DrawableRequestBuilder artRequest = Glide.with(AlbumDetailActivity.this)
					.load(artFile)
					.error(R.drawable.fallback_cover);

			artRequest.into(imageBackView);
			artRequest.into(imageView);
		}
	}

	//---

	public static void startActivity(Context context, int albumId) {
		Intent intent = new Intent(context, AlbumDetailActivity.class);
		intent.putExtra(EXTRA_ALBUM_ID, albumId);
		context.startActivity(intent);
	}

	/*
	 * CollapsingHeaderListActivity
	 */

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		trackDB = new TrackDB(this);
		tracksAdapter = new AlbumDetailAdapter(this, trackDB);
		super.onCreate(savedInstanceState);

		AlbumDB albumDB = new AlbumDB(this);
		Album album = albumDB.getAlbumFromId(getIntent().getIntExtra(EXTRA_ALBUM_ID, -1));

		titleView.setText(album.getTitle());
		String tracksCount = getResources().getQuantityString(
				R.plurals.tracks, album.getTracksCount(), album.getTracksCount());
		String albumInfo = getResources().getString(
				R.string.pattern_album_info, album.getArtistTitle(), tracksCount);
		infoView.setText(albumInfo);

		new ImageLoadTask().execute(album.getId());
	}

	@Override
	protected void onResume() {
		super.onResume();
		tracksAdapter.bindService();
	}

	@Override
	protected void onPause() {
		super.onPause();
		tracksAdapter.unbindService();
	}

	@Override
	public Loader<Cursor> getLoader() {
		int albumId = getIntent().getIntExtra(EXTRA_ALBUM_ID, 0);
		return trackDB.getAlbumTracksLoader(albumId);
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return tracksAdapter;
	}
}
