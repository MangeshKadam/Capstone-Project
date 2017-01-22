package com.udacity.capstone.utilities;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mangesh on 19/1/17.
 */

public class FileReadAsycTask extends AsyncTask<Uri, Void, String> {

    ReadCompleteCallBack mReadCompleteCallBack;
    Context mContext;

    public FileReadAsycTask(Context context, ReadCompleteCallBack readCompleteCallBack) {
        this.mContext = context;
        this.mReadCompleteCallBack = readCompleteCallBack;
    }


    @Override
    protected String doInBackground(Uri... uris) {

        StringBuilder result = new StringBuilder();
        try {
            InputStream is = null;
            try {
                is = mContext.getContentResolver().openInputStream(uris[0]);
                BufferedReader r = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = r.readLine()) != null) {
                    result.append(line);
                    result.append("<br />");
                }
                if (is != null) is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String fileContents) {
        mReadCompleteCallBack.onFileReadCompleted(fileContents);
    }
}
