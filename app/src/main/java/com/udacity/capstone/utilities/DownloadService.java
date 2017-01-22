

package com.udacity.capstone.utilities;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Service to handle downloading files from Firebase Storage.
 * used reference from firebase storage example app.
 */
public class DownloadService extends Service {


    /**
     * Actions
     **/
    public static final String ACTION_DOWNLOAD = "action_download";
    public static final String DOWNLOAD_COMPLETED = "download_completed";

    /**
     * Extras
     **/
    public static final String DOWNLOAD_DATA = "download_data";

    private StorageReference mStorageRef;



    public static final String DOWNLOAD_PATH = "https://firebasestorage.googleapis.com/v0/b/devotional-app-f8523.appspot.com/o/appinfo.json?alt=media&token=23da720a-2573-425d-9917-a5bee05ac38b";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (ACTION_DOWNLOAD.equals(intent.getAction())) {
            downloadFromPath();
        }

        return START_REDELIVER_INTENT;
    }

    private void downloadFromPath() {

        try { //required - not working on emulator..
            mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl(DOWNLOAD_PATH);
            mStorageRef.getStream(new StreamDownloadTask.StreamProcessor() {
                @Override
                public void doInBackground(StreamDownloadTask.TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException {
                }
            }).addOnSuccessListener(new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
                    String response = convertStreamToString(taskSnapshot.getStream());
                    Log.d("FIREBASE", "download:SUCCESS" + response);
                    try {
                        taskSnapshot.getStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        Intent broadcast = new Intent(DOWNLOAD_COMPLETED);
                        broadcast.putExtra(DOWNLOAD_DATA, response);
                        LocalBroadcastManager.getInstance(getApplicationContext())
                                .sendBroadcast(broadcast);
                        stopSelf();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("FIREBASE", "download:FAILURE");
                    stopSelf();
                }
            });
        }
        catch (Exception e) {
            Log.e("FIREBASE", "Failed to connect to firebase."); //this will be executed on emulator..
        }

    }

    public static IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DOWNLOAD_COMPLETED);

        return filter;
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
