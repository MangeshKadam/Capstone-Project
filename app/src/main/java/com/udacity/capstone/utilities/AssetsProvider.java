package com.udacity.capstone.utilities;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by mangesh on 16/1/17.
 * This class will be used to return file provided by URL
 */

public class AssetsProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }


    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode ) throws FileNotFoundException
    {
        Log.v( "CP", "AssetsGetter: Open asset file" );
        AssetManager am = getContext( ).getAssets();
        String file_name = uri.getLastPathSegment( );
        if( file_name == null )
            throw new FileNotFoundException( );
        AssetFileDescriptor afd = null;
        try
        {
            afd = am.openFd( file_name );

        }
        catch(IOException e)
        {
            e.printStackTrace( );
        }
        return afd;
    }





}
