package com.edavtyan.materialplayer.app.activities.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.AudioEffectsActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_effects:
                startActivity(new Intent(this, AudioEffectsActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
