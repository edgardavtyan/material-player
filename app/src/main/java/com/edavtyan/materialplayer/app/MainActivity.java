package com.edavtyan.materialplayer.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import com.edavtyan.materialplayer.app.adapters.ArtistAdapter;
import com.edavtyan.materialplayer.app.music.MusicLibrary;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    MusicLibrary library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        library = new MusicLibrary(this);

        ArtistAdapter artistAdapter = new ArtistAdapter(this, library.getArtists());

        ListView artistsListView = (ListView) findViewById(R.id.artists_listview);
        artistsListView.setAdapter(artistAdapter);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu);
    }
}
