package com.edavtyan.materialplayer.db.lib;

import com.edavtyan.materialplayer.lib.db.TestProvider;

public class ArtistDBProvider extends TestProvider<ArtistDBHelper> {
	public ArtistDBProvider() {
		super(ArtistDBHelper.class);
	}
}
