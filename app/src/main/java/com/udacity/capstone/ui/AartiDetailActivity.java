package com.udacity.capstone.ui;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.capstone.R;
import com.udacity.capstone.data.AppConstants;
import com.udacity.capstone.utilities.FileReadAsycTask;
import com.udacity.capstone.utilities.ReadCompleteCallBack;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AartiDetailActivity extends BaseActivity {


    @Bind(R.id.imggod)
    ImageView imggod;
    @Bind(R.id.txtaarti)
    TextView txtaarti;
    String aartiText;
    String aartiName = null;
    int imageResourceID = 0;
    String fileName = null;

    private InterstitialAd mInterstitialAd;

    FileReadAsycTask fileReadAsycTask = null;

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


        Uri theUri = Uri.parse(AppConstants.AUTHORITY + fileName);

        fileReadAsycTask = new FileReadAsycTask(AartiDetailActivity.this, new ReadCompleteCallBack() {
            @Override
            public void onFileReadCompleted(String fileData) {
                aartiText = fileData;
                txtaarti.setText(Html.fromHtml(aartiText.replace("\r\n", "<br />").replace(".", "")));
            }
        });
        fileReadAsycTask.execute(theUri);


        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        sendScreenName("DETAIL SCREEN ACTIVITY");
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
        if (fileReadAsycTask != null && fileReadAsycTask.getStatus() == AsyncTask.Status.RUNNING) {
            fileReadAsycTask.cancel(true);
        }
    }
}
