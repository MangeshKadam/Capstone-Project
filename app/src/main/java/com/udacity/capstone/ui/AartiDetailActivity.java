package com.udacity.capstone.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.capstone.R;
import com.udacity.capstone.data.AppConstants;
import com.udacity.capstone.utilities.FileReadAsycTaskLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AartiDetailActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<String> {


    @Bind(R.id.imggod)
    ImageView imggod;
    @Bind(R.id.txtaarti)
    TextView txtaarti;
    String aartiText;
    String aartiName = null;
    int imageResourceID = 0;
    String fileName = null;

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aarti_detail);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            fileName = getIntent().getExtras().getString(AppConstants.FILE_NAME);
            aartiName = getIntent().getExtras().getString(AppConstants.AARTI_TITLE);
            imageResourceID = getIntent().getExtras().getInt(AppConstants.IMAGE_ID);
            imggod.setBackgroundResource(imageResourceID);
        }
        getSupportActionBar().setTitle(Html.fromHtml("<small>" + aartiName + "</small>"));

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        sendScreenName("DETAIL SCREEN ACTIVITY");

        Bundle bundle = new Bundle();
        bundle.putString(FileReadAsycTaskLoader.FILE_NAME, fileName);
        Loader loader = this.getSupportLoaderManager().initLoader(0, bundle, this);
        loader.forceLoad();
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                finish();
            }
        });
        return interstitialAd;
    }


    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            finish();
        }
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showInterstitial();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showInterstitial();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new FileReadAsycTaskLoader(AartiDetailActivity.this.getBaseContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        aartiText = data;
        txtaarti.setText(Html.fromHtml(aartiText.replace("\r\n", "<br />").replace(".", "")));
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }


}
