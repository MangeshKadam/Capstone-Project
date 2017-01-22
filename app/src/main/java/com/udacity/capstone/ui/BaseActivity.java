package com.udacity.capstone.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.udacity.capstone.R;
import com.udacity.capstone.analytics.AnalyticsApplication;
import com.udacity.capstone.utilities.DownloadService;

import org.codechimp.apprater.AppRater;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mangesh on 18/1/17.
 */

public class BaseActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;

    private BroadcastReceiver mBroadcastReceiver;

    private final String TAG = "BASEACTIVITY";
    private AlertDialog mAlertDialog;


    private Tracker mTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (intent.getAction()) {

                    case DownloadService.DOWNLOAD_COMPLETED:
                        String data = intent.getStringExtra(DownloadService.DOWNLOAD_DATA);
                        String version = getVersionName(context);
                        Log.d(TAG, version);
                        checkForUpdate(data, version);
                }
            }
        };


        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }

    private void checkForUpdate(String data, String version) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String serverVersion = jsonObject.optString("app-version");
            boolean forceUpdate = jsonObject.optBoolean("force-update");
            if (!version.equalsIgnoreCase(serverVersion)) {
                if (forceUpdate) {
                    showMessageDialog("App Update", getString(R.string.app_update_message));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(mBroadcastReceiver, DownloadService.getIntentFilter());
    }


    @Override
    public void onStop() {
        super.onStop();

        // Unregister download receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    private void showMessageDialog(String title, String message) {
        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setNeutralButton(getString(R.string.update_txt), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppRater.rateNow(BaseActivity.this);
                        mAlertDialog.dismiss();
                    }
                })
                .setOnKeyListener(new Dialog.OnKeyListener() {

                    @Override
                    public boolean onKey(DialogInterface arg0, int keyCode,
                                         KeyEvent event) {
                        // TODO Auto-generated method stub
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            mAlertDialog.dismiss();
                        }
                        return true;
                    }
                })
                .setMessage(getString(R.string.app_update_message))

                .create();
        mAlertDialog.show();
    }


    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException ex) {
        }
        return null;
    }

    public void sendScreenName(String screenName) {

        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void sendEvent(String category, String action) {
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .build());
    }
}
