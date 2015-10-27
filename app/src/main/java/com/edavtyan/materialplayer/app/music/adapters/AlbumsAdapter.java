package com.edavtyan.materialplayer.app.music.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.AlbumActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.music.columns.AlbumColumns;
import com.edavtyan.materialplayer.app.music.providers.TracksProvider;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;

import java.io.File;
import java.util.List;

public class AlbumsAdapter
        extends RecyclerViewCursorAdapter<AlbumsAdapter.AlbumViewHolder>
        implements ServiceConnection {

    private MusicPlayerService service;
    private boolean isBound;

    public AlbumsAdapter(Context context) {
        super(context);
        context.bindService(
                new Intent(context, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    /*
     * RecyclerViewCursorAdapter
     */

    @Override
    protected View newView(Context context, ViewGroup parent) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_album, parent, false);

        AlbumViewHolder vh = new AlbumViewHolder(view);
        view.setTag(vh);
        return view;
    }

    @Override
    protected void bindView(View view, Context context, Cursor cursor) {
        AlbumViewHolder vh = (AlbumViewHolder) view.getTag();
        vh.titleTextView.setText(cursor.getString(AlbumColumns.TITLE));
        vh.infoTextView.setText(getAdditionalInfo(cursor));

        String artPath = getCursor().getString(AlbumColumns.ART);
        File artFile = AlbumArtUtils.getArtFileFromPath(artPath);

        Glide.with(context)
                .load(artFile)
                .error(R.drawable.fallback_cover_listitem)
                .into(vh.artImageView);
    }

    @Override
    protected AlbumViewHolder createViewHolder(View view) {
        return new AlbumViewHolder(view);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView infoTextView;
        private final ImageView artImageView;
        private final ImageButton menuButton;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                getCursor().moveToPosition(getAdapterPosition());

                Intent i = new Intent(context, AlbumActivity.class);
                i.putExtra(AlbumActivity.EXTRA_ALBUM_ID, getCursor().getInt(AlbumColumns.ID));
                context.startActivity(i);
            });

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            infoTextView = (TextView) itemView.findViewById(R.id.info);
            artImageView = (ImageView) itemView.findViewById(R.id.art);
            menuButton = (ImageButton) itemView.findViewById(R.id.menu);

            PopupMenu popupMenu = new PopupMenu(context, menuButton);
            popupMenu.inflate(R.menu.menu_track);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.menu_addToPlaylist:
                        getCursor().moveToPosition(getAdapterPosition());
                        int albumId = getCursor().getInt(AlbumColumns.ID);
                        List<Metadata> tracks = TracksProvider.getAlbumTracks(albumId, context);
                        service.getTracks().addAll(tracks);

                    default:
                        return false;
                }
            });

            menuButton.setOnClickListener(view -> popupMenu.show());
        }
    }

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        service = ((MusicPlayerService.MusicPlayerBinder)iBinder).getService();
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
    }

    /*
     * Private methods
     */

    private String getAdditionalInfo(Cursor cursor) {
        Resources res = context.getResources();

        String tracksCount = res.getQuantityString(
                R.plurals.tracks,
                cursor.getInt(AlbumColumns.ARTIST),
                cursor.getInt(AlbumColumns.SONGS_COUNT));

        return res.getString(
                R.string.two_strings_with_bar,
                cursor.getString(AlbumColumns.ARTIST),
                tracksCount);
    }
}
