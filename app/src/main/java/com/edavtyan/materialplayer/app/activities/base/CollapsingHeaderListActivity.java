package com.edavtyan.materialplayer.app.activities.base;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public abstract class CollapsingHeaderListActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_list);
        getSupportLoaderManager().initLoader(0, null, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.list_header);
        header.attachTo(recyclerView, true);
        header.getLayoutParams().height = getResources().getDisplayMetrics().widthPixels;

        ImageView artistArtView = (ImageView) findViewById(R.id.art_header);
        setImageSource(artistArtView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return getLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        getAdapter().swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        getAdapter().swapCursor(null);
    }


    protected abstract Loader<Cursor> getLoader();

    protected abstract RecyclerViewCursorAdapter getAdapter();

    protected abstract String getToolbarTitle();

    protected abstract void setImageSource(ImageView imageView);
}
