package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.ContentResolver;
import android.database.Cursor;

import com.edavtyan.materialplayer.components.artists.Artist;

import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;

public class ArtistListModel extends ArtistBaseModel implements ArtistListMvp.Model {
	private final List<Artist> artists;

	public ArtistListModel(ContentResolver resolver) {
		super(resolver);
		artists = new ArrayList<>();
		updateData();
	}

	public void updateData() {
		artists.clear();
		@Cleanup Cursor cursor = resolver.query(URI_EXTERNAL, PROJECTION, null, null, TITLE);
		if (cursor == null) return;
		while (cursor.moveToNext()) {
			artists.add(getArtistFromCursor(cursor));
		}
	}

	@Override
	public int getArtistCount() {
		return artists.size();
	}

	@Override
	public Artist getArtistAtIndex(int position) {
		if (artists.size() == 0) return null;
		return artists.get(position);
	}
}
