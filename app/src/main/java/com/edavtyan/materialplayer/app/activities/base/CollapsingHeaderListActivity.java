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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.resources.AppColors;
import com.edavtyan.materialplayer.app.utils.CustomColor;
import com.edavtyan.materialplayer.app.utils.DeviceUtils;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public abstract class CollapsingHeaderListActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    protected int totalScrolled = 0;
    protected ImageView imageView;
    protected TextView titleView;
    protected TextView infoView;

    /*
     * AppCompatActivity
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_list);
        getSupportLoaderManager().initLoader(0, null, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        imageView = (ImageView) findViewById(R.id.art);
        titleView = (TextView) findViewById(R.id.title);
        infoView = (TextView) findViewById(R.id.info);

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (DeviceUtils.isPortrait(getResources())) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }

            LinearLayout appbarWrapper = (LinearLayout) findViewById(R.id.appbar_wrapper);
            appbarWrapper.bringToFront();

            View appbarShadow = findViewById(R.id.appbar_shadow);
            View statusBarTint = findViewById(R.id.statusbar_tint);

            RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.list_header);
            header.attachTo(recyclerView, true);

            imageView.getLayoutParams().height = getResources().getDisplayMetrics().widthPixels;


            CustomColor primaryColor = new CustomColor(AppColors.getPrimary(this));
            CustomColor statusBarTintColor = new CustomColor(
                    ContextCompat.getColor(this, R.color.transparent_gray_light));

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalScrolled += dy;
                    imageView.setTop(totalScrolled / 2);

                    float alpha;
                    if (totalScrolled > 255) {
                        alpha = 1.0f;
                    } else {
                        alpha = totalScrolled / 255f;
                    }

                    appbar.setBackgroundColor(primaryColor.fade(totalScrolled));
                    appbarShadow.setAlpha(alpha);
                    if (statusBarTint != null) {
                        statusBarTint.setBackgroundColor(statusBarTintColor.fadeLimit(totalScrolled));
                    }
                }
            });
        }
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
}
