package com.edavtyan.materialplayer.lib.music_api;

public abstract class MusicApi {
	private final MusicApiFileStorage fileStorage;

	public MusicApi(MusicApiFileStorage fileStorage) {
		this.fileStorage = fileStorage;
	}

	protected abstract MusicApiInfo getInfoFromApi(String artistTitle)
	throws InfoNotFoundException, NoConnectionException;

	public MusicApiInfo getArtistInfo(String artistTitle)
	throws InfoNotFoundException, NoConnectionException {
		if (fileStorage.exists(artistTitle)) {
			return MusicApiInfo.fromJson(fileStorage.load(artistTitle));
		}

		MusicApiInfo artistInfo = getInfoFromApi(artistTitle);
		fileStorage.save(artistTitle, artistInfo.toJson());
		return artistInfo;
	}
}
