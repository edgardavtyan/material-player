package com.edavtyan.materialplayer.app.activities.base;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.resources.AppColors;
import com.edavtyan.materialplayer.app.utils.CustomColor;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public abstract class CollapsingHeaderListActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private int totalScrolled = 0;

    /*
     * AppCompatActivity
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_list);
        getSupportLoaderManager().initLoader(0, null, this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.bringToFront();

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

        View statusBarTint = findViewById(R.id.statusbar_tint);

        CustomColor primaryColor = new CustomColor(AppColors.getPrimary(this));
        CustomColor statusBarTintColor = new CustomColor(
                ContextCompat.getColor(this, R.color.transparent_gray_light));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalScrolled += dy;
                artistArtView.setTop(totalScrolled / 2);

                appbar.setBackgroundColor(primaryColor.fade(totalScrolled));
                statusBarTint.setBackgroundColor(statusBarTintColor.fadeLimit(totalScrolled));
            }
        });
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

    /*
     * LoaderCallbacks
     */

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

    /*
     * Abstract methods
     */

    protected abstract Loader<Cursor> getLoader();

    protected abstract RecyclerViewCursorAdapter getAdapter();

    protected abstract String getToolbarTitle();

    protected abstract void setImageSource(ImageView imageView);
}
