package com.udacity.capstone.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.udacity.capstone.data.AartiKathaDetails;

/**
 * Created by mangesh on 14/1/17.
 * This class will be used to save favourite items.
 */
public class PrefManager {


    private SharedPreferences tokenPrefs;

    private String PREF_NAME = "Khopoli App";

    private String USER_NAME = "username";

    private String USER_PROFILE = "userprofile";

    private String FAVOURITE_AARTI_NAME1 = "FAVOURITE_1";

    private String FAVOURITE_AARTI_NAME2 = "FAVOURITE_2";

    private String FAVOURITE_AARTI_NAME3 = "FAVOURITE_3";


    private String FAVOURITE_AARTI_IMAGE1 = "AARTI_IMAGE1";

    private String FAVOURITE_AARTI_IMAGE2 = "AARTI_IMAGE2";

    private String FAVOURITE_AARTI_IMAGE3 = "AARTI_IMAGE3";


    private String FAVOURITE_AARTI_FILENAME1 = "AARTI_FILENAME_1";

    private String FAVOURITE_AARTI_FILENAME2 = "AARTI_FILENAME_2";

    private String FAVOURITE_AARTI_FILENAME3 = "AARTI_FILENAME_3";


    private String FAVOURITE_AARTI_TITLE1 = "AARTI_TITLE_1";

    private String FAVOURITE_AARTI_TITLE2 = "AARTI_TITLE_2";

    private String FAVOURITE_AARTI_TITLE3 = "AARTI_TITLE_3";


    private String FAVOURITE_PARENT_TITLE1 = "PARENT_TITLE1";

    private String FAVOURITE_PARENT_TITLE2 = "PARENT_TITLE2";

    private String FAVOURITE_PARENT_TITLE3 = "PARENT_TITLE3";

    String name1  = null;
    String name2  = null;
    String name3  = null;

    private Context context;

    public PrefManager(Context context) {
        this.context = context;
        tokenPrefs = this.context.getSharedPreferences(PREF_NAME, 0);
    }

    public void saveUserID(String userID) {
        SharedPreferences.Editor editor = tokenPrefs.edit();
        editor.putString(USER_NAME, userID);
        editor.commit();
    }

    public String getUserID() {
        return tokenPrefs.getString(USER_NAME, null);
    }

    public String getUserProfile() {
        return tokenPrefs.getString(USER_PROFILE, null);
    }


    public boolean logout() {
        SharedPreferences.Editor editor = tokenPrefs.edit();
        editor.clear();
        return editor.commit();
    }

    public void saveFavouriteAarti(String aartiName, int imageResourceID, String fileName) {
        if (tokenPrefs.getString(FAVOURITE_AARTI_NAME1, null) == null) {
            SharedPreferences.Editor editor = tokenPrefs.edit();
            editor.putString(FAVOURITE_AARTI_NAME1, aartiName);
            editor.putInt(FAVOURITE_AARTI_IMAGE1, imageResourceID);
            editor.putString(FAVOURITE_AARTI_FILENAME1, fileName);
            editor.commit();
        } else if (tokenPrefs.getString(FAVOURITE_AARTI_NAME2, null) == null) {
            SharedPreferences.Editor editor = tokenPrefs.edit();
            editor.putString(FAVOURITE_AARTI_NAME2, aartiName);
            editor.putInt(FAVOURITE_AARTI_IMAGE2, imageResourceID);
            editor.putString(FAVOURITE_AARTI_FILENAME2, fileName);
            editor.commit();
        } else if (tokenPrefs.getString(FAVOURITE_AARTI_NAME3, null) == null) {
            SharedPreferences.Editor editor = tokenPrefs.edit();
            editor.putString(FAVOURITE_AARTI_NAME3, aartiName);
            editor.putInt(FAVOURITE_AARTI_IMAGE3, imageResourceID);
            editor.putString(FAVOURITE_AARTI_FILENAME3, fileName);
            editor.commit();
        }
    }


    public boolean isFavouriteAvailable(int id) {

        switch (id) {
            case 1:
                return tokenPrefs.getString(FAVOURITE_AARTI_NAME1, null) != null;
            case 2:
                return tokenPrefs.getString(FAVOURITE_AARTI_NAME2, null) != null;
            case 3:
                return tokenPrefs.getString(FAVOURITE_AARTI_NAME3, null) != null;
        }
        return false;
    }

    public boolean isAartiNameAvailable(String name) {

        if(name1 ==null && name2 == null && name3 == null)
            return false;
        if(name1!=null && name1.equalsIgnoreCase(name))
            return true;
        if(name2!=null && name2.equalsIgnoreCase(name))
            return true;
        if(name3!=null && name3.equalsIgnoreCase(name))
            return true;
        return false;
    }

    /**
     * get Details of the favourite Item
     * @param id
     * @return
     */
    public AartiKathaDetails getFavouriteDetails(int id) {
        AartiKathaDetails aartiKathaDetails = null;
        switch (id) {
            case 1:
                aartiKathaDetails = new AartiKathaDetails(tokenPrefs.getString(FAVOURITE_AARTI_NAME1, null),
                        tokenPrefs.getInt(FAVOURITE_AARTI_IMAGE1, 0),
                        tokenPrefs.getString(FAVOURITE_AARTI_FILENAME1, null));
                return aartiKathaDetails;
            case 2:
                aartiKathaDetails = new AartiKathaDetails(tokenPrefs.getString(FAVOURITE_AARTI_NAME2, null),
                        tokenPrefs.getInt(FAVOURITE_AARTI_IMAGE2, 0),
                        tokenPrefs.getString(FAVOURITE_AARTI_FILENAME2, null));
                return aartiKathaDetails;
            case 3:
                aartiKathaDetails = new AartiKathaDetails(tokenPrefs.getString(FAVOURITE_AARTI_NAME3, null),
                        tokenPrefs.getInt(FAVOURITE_AARTI_IMAGE3, 0),
                        tokenPrefs.getString(FAVOURITE_AARTI_FILENAME3, null));
                return aartiKathaDetails;

        }
        return aartiKathaDetails;
    }


    public void cacheStoredNames() {

         name1 = tokenPrefs.getString(FAVOURITE_AARTI_NAME1, null);
         name2 = tokenPrefs.getString(FAVOURITE_AARTI_NAME2, null);
         name3 = tokenPrefs.getString(FAVOURITE_AARTI_NAME3, null);
    }


    public boolean isFreeSlotAvailable() {
        if(name1== null || name2 == null || name3== null)
            return true;
        else
            return false;
    }
}


