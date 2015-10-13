package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder>
extends RecyclerView.Adapter<VH> {
    /* ****** */
    /* Fields */
    /* ****** */

    private CursorAdapter cursorAdapter;

    /* **************** */
    /* Protected fields */
    /* **************** */

    protected Context context;

    /* ************ */
    /* Constructors */
    /* ************ */

    public RecyclerViewCursorAdapter(Context context, Cursor cursor) {
        this.context = context;

        cursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return RecyclerViewCursorAdapter.this.newView(context, cursor, parent);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                RecyclerViewCursorAdapter.this.bindView(view, context, cursor);
            }
        };
    }

    /* ************** */
    /* Public methods */
    /* ************** */

    public void swapCursor(Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
        notifyDataSetChanged();
    }

    public Cursor getCursor() {
        return cursorAdapter.getCursor();
    }

    /* ************************** */
    /* Protected abstract methods */
    /* ************************** */

    protected abstract View newView(Context context, Cursor cursor, ViewGroup parent);

    protected abstract void bindView(View view, Context context, Cursor cursor);

    protected abstract VH createViewHolder(View view, ViewGroup parent, int position);

    /* **************************** */
    /* RecyclerView.Adapter members */
    /* **************************** */

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int position) {
        View v = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        return createViewHolder(v, parent, position);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }
}
