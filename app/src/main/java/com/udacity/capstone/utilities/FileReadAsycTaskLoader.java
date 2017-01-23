package com.udacity.capstone.utilities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mangesh on 19/1/17.
 */

public class FileReadAsycTaskLoader extends AsyncTaskLoader<String> {

    public static final String FILE_NAME = "FILE_NAME";

    private final String AUTHORITY = "content://com.udacity.capstone/";

    Uri mUri;

    Context mContext = null;

    public FileReadAsycTaskLoader(Context context) {
        super(context);
    }

    public FileReadAsycTaskLoader(Context context, Bundle args) {
        super(context);
        mContext = context;
        String fileName = args.getString(FILE_NAME);
        mUri = Uri.parse(AUTHORITY +fileName);
    }

    @Override
    public String loadInBackground() {


        StringBuilder result = new StringBuilder();
        try {
            InputStream is = null;
            try {
                is = mContext.getContentResolver().openInputStream(mUri);
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
}
