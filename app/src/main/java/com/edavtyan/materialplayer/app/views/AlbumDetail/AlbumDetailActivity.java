package com.edavtyan.materialplayer.app.views.albumdetail;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.models.ArtProvider;
import com.edavtyan.materialplayer.app.models.album.Album;
import com.edavtyan.materialplayer.app.models.album.AlbumsProvider;
import com.edavtyan.materialplayer.app.models.track.Track;
import com.edavtyan.materialplayer.app.models.track.TracksProvider;
import com.edavtyan.materialplayer.app.views.lib.activities.CollapsingHeaderListActivity;
import com.edavtyan.materialplayer.app.views.lib.adapters.RecyclerViewCursorAdapter;

import java.io.File;

public class AlbumDetailActivity extends CollapsingHeaderListActivity {
	public static final String EXTRA_ALBUM_ID = "album_id";

	private AlbumDetailAdapter tracksAdapter;
	private TracksProvider tracksProvider;

	/*
	 * AsyncTasks
	 */

	private class ImageLoadTask extends AsyncTask<Integer, Void, File> {
		@Override
		protected File doInBackground(Integer... albumIds) {
			Track track = tracksProvider.getSingleTrackWithAlbumId(albumIds[0]);
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
		tracksAdapter = new AlbumDetailAdapter(this, null);
		tracksProvider = new TracksProvider(this);
		super.onCreate(savedInstanceState);

		AlbumsProvider albumsProvider = new AlbumsProvider(this);
		Album album = albumsProvider.getAlbumFromId(getIntent().getIntExtra(EXTRA_ALBUM_ID, -1));

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
		return tracksProvider.getAlbumTracksLoader(albumId);
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return tracksAdapter;
	}

	/*
	 * BaseToolbarActivity
	 */

	@Override
	public int getToolbarTitleStringId() {
		return R.string.app_name;
	}
}
