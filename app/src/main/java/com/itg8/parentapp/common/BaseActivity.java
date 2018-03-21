package com.itg8.parentapp.common;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.itg8.parentapp.R;
import com.itg8.parentapp.widgets.BadgeDrawable;

public class BaseActivity extends AppCompatActivity {

    private LayerDrawable icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_drawer, menu);

        //If you want to add your ActionItem programmatically you can do this too. You do the following:
//        new ActionItemBadgeAdder().act(this).menu(menu).title(R.string.sample_2).itemDetails(0, SAMPLE2_ID, 1).showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS).add(bigStyle, 1);
        MenuItem itemCart = menu.findItem(R.id.action_notification);
        icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(getNoticationCount());
        return true;

    }


    public void setBadgeCount(int count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(this);
        }

        if (count > 0) {
            Prefs.putInt(CommonMethod.NOTIFICATION_COUNT, count);
            badge.setCount(String.valueOf(count));
            icon.mutate();
            icon.setDrawableByLayerId(R.id.ic_badge, badge);
        }
    }


    private int getNoticationCount() {
        return 0;
    }
}
