package com.fbw.recyclerviewproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionSet;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.lang.ref.WeakReference;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setUpWindowTrisience();
        setUpGrideView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpWindowTrisience() {
        TransitionSet  mtransitionset=new TransitionSet();
        mtransitionset.addTransition(new ChangeBounds());
        mtransitionset.addTransition(new ChangeImageTransform());
        mtransitionset.setDuration(250);
        getWindow().setEnterTransition(mtransitionset);
        getWindow().setExitTransition(mtransitionset);
        getWindow().setSharedElementEnterTransition(mtransitionset);
        getWindow().setSharedElementExitTransition(mtransitionset);
        getWindow().setAllowEnterTransitionOverlap(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

      private   void setUpGrideView() {
          GridView  grideView=(GridView)findViewById(R.id.gridView);
          GrideViewAdapter  mGrideViewAdapter=new GrideViewAdapter(getApplicationContext(),0);
          for(int i=0;i<20;i++){
              mGrideViewAdapter.add(String.valueOf(i));
          }
          grideView.setAdapter(mGrideViewAdapter);
          grideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  ActivityOptionsCompat  aop=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                          view.findViewById(R.id.imageView),"123");
                  Intent   intent=new Intent(getApplicationContext(),SecondActivity.class);
                 ActivityCompat.startActivity(MainActivity.this,intent,aop.toBundle());
              }
          });
    }

    class  GrideViewAdapter  extends ArrayAdapter<String>{
        public GrideViewAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View  view=View.inflate(getApplicationContext(),R.layout.gride_view_item,null);
            return view;
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
