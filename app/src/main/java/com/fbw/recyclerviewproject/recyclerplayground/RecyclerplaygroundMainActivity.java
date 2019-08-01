package com.fbw.recyclerviewproject.recyclerplayground;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.recyclerplayground.fragments.FixedTwoWayFragment;
import com.fbw.recyclerviewproject.recyclerplayground.fragments.HorizontalFragment;
import com.fbw.recyclerviewproject.recyclerplayground.fragments.NavigationDrawerFragment;
import com.fbw.recyclerviewproject.recyclerplayground.fragments.VerticalFragment;
import com.fbw.recyclerviewproject.recyclerplayground.fragments.VerticalGridFragment;
import com.fbw.recyclerviewproject.recyclerplayground.fragments.VerticalStaggeredGridFragment;


public class RecyclerplaygroundMainActivity extends AppCompatActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    public RecyclerplaygroundMainActivity(){
        Log.d("fbw","MainActivity");
    }

    @Override
    public void startLockTask() {
        Log.d("fbw","startLockTask");
        super.startLockTask();
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("fbw","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerplayground_activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.container, VerticalFragment.newInstance());
                break;
            case 1:
                ft.replace(R.id.container, HorizontalFragment.newInstance());
                break;
            case 2:
                ft.replace(R.id.container, VerticalGridFragment.newInstance());
                break;
            case 3:
                ft.replace(R.id.container, VerticalStaggeredGridFragment.newInstance());
                break;
            case 4:
                ft.replace(R.id.container, FixedTwoWayFragment.newInstance());
                break;
            default:
                //Do nothing
                break;
        }

        ft.commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

}
