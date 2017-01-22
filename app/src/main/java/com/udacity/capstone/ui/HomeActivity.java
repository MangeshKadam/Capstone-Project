package com.udacity.capstone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.capstone.data.AartiKathaDetails;
import com.udacity.capstone.R;
import com.udacity.capstone.data.AartiConstants;
import com.udacity.capstone.data.AppConstants;
import com.udacity.capstone.utilities.DownloadService;
import com.udacity.capstone.utilities.PrefManager;

import org.codechimp.apprater.AppRater;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    PrefManager prefManager = null;

    MenuItem favourite1;

    MenuItem favourite2;

    MenuItem favourite3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);

        prefManager = new PrefManager(HomeActivity.this);

        favourite1 = navigationView.getMenu().findItem(R.id.nav_fav1);
        favourite2 = navigationView.getMenu().findItem(R.id.nav_fav2);
        favourite3 = navigationView.getMenu().findItem(R.id.nav_fav3);
        updateDrawerItems();

        beginDownload();

        sendScreenName("HOME SCREEN ACTIVITY");

    }

    private void updateDrawerItems() {


        if (prefManager.isFavouriteAvailable(1)) {
            favourite1.setTitle(prefManager.getFavouriteDetails(1).getAartiName());
        }

        if (prefManager.isFavouriteAvailable(2)) {
            favourite2.setTitle(prefManager.getFavouriteDetails(2).getAartiName());
        }

        if (prefManager.isFavouriteAvailable(3)) {
            favourite3.setTitle(prefManager.getFavouriteDetails(3).getAartiName());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateDrawerItems();

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
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.nav_contactUs) {
            showContactUsActivity();
            return true;
        } else if (item.getItemId() == R.id.nav_aboutUs) {
            showAboutUsActivity();
            return true;
        } else if (item.getItemId() == R.id.nav_rateThisApp) {
            rateApp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
            item.setChecked(true);
            setTitle(item.getTitle());
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            showAartis();
            return true;
        }
        else if(item.getItemId() == R.id.nav_vratkatha) {
            item.setChecked(true);
            setTitle(item.getTitle());
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            showVratKatha();
            return true;
        }
        else if (item.getItemId() == R.id.nav_fav1) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            if (prefManager.isFavouriteAvailable(1)) {

                sendEvent("DRAWER ITEM", "FAVOURITE 1");
                AartiKathaDetails aartiKathaDetails = prefManager.getFavouriteDetails(1);
                Intent detailAarti = new Intent(HomeActivity.this, AartiDetailActivity.class);
                detailAarti.putExtra(AppConstants.FILE_NAME, aartiKathaDetails.getFileName());
                detailAarti.putExtra(AppConstants.AARTI_TITLE, aartiKathaDetails.getAartiName());
                detailAarti.putExtra(AppConstants.IMAGE_ID, aartiKathaDetails.getImageID());
                startActivity(detailAarti);
            }
            return true;
        } else if (item.getItemId() == R.id.nav_fav2) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            if (prefManager.isFavouriteAvailable(2)) {
                sendEvent("DRAWER ITEM", "FAVOURITE 2");
                AartiKathaDetails aartiKathaDetails = prefManager.getFavouriteDetails(2);
                Intent detailAarti = new Intent(HomeActivity.this, AartiDetailActivity.class);
                detailAarti.putExtra(AppConstants.FILE_NAME, aartiKathaDetails.getFileName());
                detailAarti.putExtra(AppConstants.AARTI_TITLE, aartiKathaDetails.getAartiName());
                detailAarti.putExtra(AppConstants.IMAGE_ID, aartiKathaDetails.getImageID());
                startActivity(detailAarti);
            }
            return true;
        } else if (item.getItemId() == R.id.nav_fav3) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            if (prefManager.isFavouriteAvailable(3)) {
                sendEvent("DRAWER ITEM", "FAVOURITE 3");
                AartiKathaDetails aartiKathaDetails = prefManager.getFavouriteDetails(3);
                Intent detailAarti = new Intent(HomeActivity.this, AartiDetailActivity.class);
                detailAarti.putExtra(AppConstants.FILE_NAME, aartiKathaDetails.getFileName());
                detailAarti.putExtra(AppConstants.AARTI_TITLE, aartiKathaDetails.getAartiName());
                detailAarti.putExtra(AppConstants.IMAGE_ID, aartiKathaDetails.getImageID());
                startActivity(detailAarti);
            }
            return true;
        } else {

            Fragment fragment = null;
            Class fragmentClass = AartiKathaFragment.class;
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            switch (id) {


                case R.id.nav_favourite:

            }
            return true;
        }
    }


    public void showAartis() {

        sendScreenName("AARTI SCREEN FRAGMENT");

        Fragment fragment = AartiKathaFragment.newInstance(AppConstants.CATEGORY_AARTI, AartiConstants.aartiImages, getResources().getStringArray(R.array.god_names));
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment, AppConstants.HOME_FRAGMENT).commit();
    }

    public void showVratKatha() {

        sendScreenName("VRAT KATHA FRAGMENT");


        Fragment fragment = AartiKathaFragment.newInstance(AppConstants.CATEGORY_VRATKATHA, AartiConstants.vratKathaImages, getResources().getStringArray(R.array.vrat_katha_names));
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment, AppConstants.HOME_FRAGMENT).commit();

    }


    private void showAboutUsActivity() {
        Intent enquiryIntent = new Intent(this, AboutUsActivity.class);
        startActivity(enquiryIntent);
    }


    private void showContactUsActivity() {
        Intent loginIntent = new Intent(this, ContactUsActivity.class);
        startActivity(loginIntent);
    }

    private void showAartiCollectionActivity() {
        Intent loginIntent = new Intent(this, LordAartiCollectionActivity.class);
        startActivity(loginIntent);
    }





    public void rateApp() {
        AppRater.showRateDialog(this);
    }


    private void beginDownload() {
        Intent intent = new Intent(this, DownloadService.class)
                .setAction(DownloadService.ACTION_DOWNLOAD);
        startService(intent);
    }

}

