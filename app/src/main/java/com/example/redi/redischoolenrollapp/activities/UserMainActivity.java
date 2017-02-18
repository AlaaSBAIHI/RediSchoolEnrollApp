package com.example.redi.redischoolenrollapp.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.redi.redischoolenrollapp.R;
import com.example.redi.redischoolenrollapp.uifragments.ApplyFragment;
import com.example.redi.redischoolenrollapp.uifragments.CoursesFragment;
import com.example.redi.redischoolenrollapp.uifragments.RetainFragment;

public class UserMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String NETWORK_FRAGMENT_TAG = "NETWORK_FRAGMENT";
    public static final String APPLY_FRAGMENT_TAG = "APPLY_FRAGMENT";
    public static final String COURSES_FRAGMENT_TAG = "COURSES_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        getRetainFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_main, menu);
        return true;
    }

    @Override
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_locations) {

        } else if (id == R.id.nav_apply) {
            getApplyFragment();
        } else if (id == R.id.nav_courses) {
            getCoursesFragment();
        } else if (id == R.id.nav_my_courses) {

        } else if (id == R.id.nav_about_redi) {

        } else if (id == R.id.nav_about_app) {


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public ApplyFragment getApplyFragment() {
        ApplyFragment applyFragment = (ApplyFragment) getSupportFragmentManager().findFragmentByTag(APPLY_FRAGMENT_TAG);
        if (applyFragment == null) {
            applyFragment = new ApplyFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, applyFragment).commit();
        }

        return applyFragment;
    }

    public CoursesFragment getCoursesFragment() {

      /*FragmentCoursesBinding fragmentCoursesBinding = DataBindingUtil.setContentView(this, R.layout.fragment_courses);

        AddCourseViewModel addCourseViewModel = AddCourseViewModel.builder().build();

        fragmentCoursesBinding.setAddCourseViewModel(addCourseViewModel);
*/
        CoursesFragment coursesFragment = (CoursesFragment) getSupportFragmentManager().findFragmentByTag(COURSES_FRAGMENT_TAG);
        if (coursesFragment == null) {
            coursesFragment = new CoursesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, coursesFragment).commit();
        }

        return coursesFragment;
    }

    public RetainFragment getRetainFragment() {
        RetainFragment retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, NETWORK_FRAGMENT_TAG).commit();
        }

        return retainFragment;
    }
}
