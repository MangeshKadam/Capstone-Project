package com.udacity.capstone.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.udacity.capstone.R;
import com.udacity.capstone.data.AartiConstants;
import com.udacity.capstone.data.AartiKathaDetails;
import com.udacity.capstone.data.AppConstants;

import java.util.Random;

public class SimpleWidgetProvider extends AppWidgetProvider {



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Get the layout for the App Widget and attach an on-click listener
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
            AartiKathaDetails aartiKathaDetails = getRandomAartiKatha(context);
            views.setTextViewText(R.id.txtaartiname, aartiKathaDetails.getAartiName()); // set aarti/katha name



            //set parameters to intent.
            Intent aartiDetailIntent = new Intent(context, AartiDetailActivity.class);
            aartiDetailIntent.putExtra(AppConstants.FILE_NAME, aartiKathaDetails.getFileName());
            aartiDetailIntent.putExtra(AppConstants.AARTI_TITLE, aartiKathaDetails.getAartiName());
            aartiDetailIntent.putExtra(AppConstants.IMAGE_ID, aartiKathaDetails.getImageID());

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, aartiDetailIntent, 0);
            views.setOnClickPendingIntent(R.id.baselayout, pendingIntent);



            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }


    }


    /**
     * this function will return random aarti or katha with its details..
     * @param context
     * @return
     */

    public AartiKathaDetails getRandomAartiKatha(Context context) {

        int randomNumberForAartiKatha = generateRandomNumberInRange(1, 2);

        int imageResourceIndex = 0;
        String fileName = null;
        String title = null;

        AartiKathaDetails aartiKathaDetails = null;

        if(randomNumberForAartiKatha == 1) { //its aarti

            int randomNumberForAarti = generateRandomNumberInRange(1, 89);

            Log.d("RANFORAARTI", String.valueOf(randomNumberForAarti));

            if(isBetween(randomNumberForAarti, 1,5)) { // ganesh
                imageResourceIndex = 0;
                fileName = AartiConstants.ganesh_aartis.get(getindexWithinRange(randomNumberForAarti,1));
                title = context.getResources().getStringArray(R.array.ganesh_aartis)[getindexWithinRange(randomNumberForAarti,1)];
            }
            else if(isBetween(randomNumberForAarti, 6,15)) { // shankar
                imageResourceIndex = 1;
                fileName = AartiConstants.shankar_aartis.get(getindexWithinRange(randomNumberForAarti,6));
                title = context.getResources().getStringArray(R.array.shankar_aartis)[getindexWithinRange(randomNumberForAarti,6)];
            }

            else if(isBetween(randomNumberForAarti, 16,20)) { // krishna
                imageResourceIndex = 2;
                fileName = AartiConstants.krishna_aartis.get(getindexWithinRange(randomNumberForAarti,16));
                title = context.getResources().getStringArray(R.array.krishna_aartis)[getindexWithinRange(randomNumberForAarti,16)];
            }

            else if(isBetween(randomNumberForAarti, 21,25)) { // ram
                imageResourceIndex = 3;
                fileName = AartiConstants.ram_aartis.get(getindexWithinRange(randomNumberForAarti,21));
                title = context.getResources().getStringArray(R.array.ram_aartis)[getindexWithinRange(randomNumberForAarti,21)];
            }

            else if(isBetween(randomNumberForAarti, 26,42)) { // hanuman
                imageResourceIndex = 4;
                fileName = AartiConstants.hanuman_aartis.get(getindexWithinRange(randomNumberForAarti,26));
                title = context.getResources().getStringArray(R.array.hanuman_aartis)[getindexWithinRange(randomNumberForAarti,26)];
            }

            else if(isBetween(randomNumberForAarti, 43,49)) { // vishnu
                imageResourceIndex = 5;
                fileName = AartiConstants.vishnu_aartis.get(getindexWithinRange(randomNumberForAarti,43));
                title = context.getResources().getStringArray(R.array.vishnu_aartis)[getindexWithinRange(randomNumberForAarti,43)];
            }

            else if(isBetween(randomNumberForAarti, 50,58)) { // laxmi
                imageResourceIndex = 6;
                fileName = AartiConstants.lakshmi_aartis.get(getindexWithinRange(randomNumberForAarti,50));
                title = context.getResources().getStringArray(R.array.lakshmi_aartis)[getindexWithinRange(randomNumberForAarti,50)];
            }

            else if(isBetween(randomNumberForAarti, 59,71)) { // durga
                imageResourceIndex = 7;
                fileName = AartiConstants.durga_aartis.get(getindexWithinRange(randomNumberForAarti,59));
                title = context.getResources().getStringArray(R.array.durga_and_kali_aartis)[getindexWithinRange(randomNumberForAarti,59)];
            }

            else if(isBetween(randomNumberForAarti, 72,75)) { // shani
                imageResourceIndex = 8;
                fileName = AartiConstants.shri_shani.get(getindexWithinRange(randomNumberForAarti,72));
                title = context.getResources().getStringArray(R.array.shani_aartis)[getindexWithinRange(randomNumberForAarti,72)];
            }

            else if(isBetween(randomNumberForAarti, 76,83)) { // gayatri
                imageResourceIndex = 9;
                fileName = AartiConstants.gayatri_aartis.get(getindexWithinRange(randomNumberForAarti,76));
                title = context.getResources().getStringArray(R.array.gayatri_and_saraswati_aartis)[getindexWithinRange(randomNumberForAarti,76)];
            }

            else  { // surya
                imageResourceIndex = 10;
                fileName = AartiConstants.surya_aartis.get(getindexWithinRange(randomNumberForAarti,84));
                title = context.getResources().getStringArray(R.array.surya_navgrah_aartis)[getindexWithinRange(randomNumberForAarti,84)];
            }

            aartiKathaDetails = new AartiKathaDetails(title, AartiConstants.aartiImages[imageResourceIndex], fileName);
        }
        else { ///its vrah katha

            int randomNumberForKatha = generateRandomNumberInRange(1, 37);

            Log.d("RANFORKATHA", String.valueOf(randomNumberForKatha));

            if(isBetween(randomNumberForKatha, 1,24)) { // ekadashi
                imageResourceIndex = 0;
                fileName = AartiConstants.ekadashi_vrat_katha.get(getindexWithinRange(randomNumberForKatha, 1));
                title = context.getResources().getStringArray(R.array.ekadashi_vratkatha)[getindexWithinRange(randomNumberForKatha, 1)];
            }
            else if(isBetween(randomNumberForKatha, 25,31)) { // saptahik
                imageResourceIndex = 1;
                fileName = AartiConstants.saptahik_vrat_katha.get(getindexWithinRange(randomNumberForKatha, 25));
                title = context.getResources().getStringArray(R.array.saptahik_vratkatha)[getindexWithinRange(randomNumberForKatha, 25)];
            }

            else { // vishishta
                imageResourceIndex = 2;
                fileName = AartiConstants.vishishta_vrat_katha.get(getindexWithinRange(randomNumberForKatha, 32));
                title = context.getResources().getStringArray(R.array.vishista_vratkatha)[getindexWithinRange(randomNumberForKatha, 32)];
            }

            aartiKathaDetails = new AartiKathaDetails(title, AartiConstants.vratKathaImages[imageResourceIndex], fileName);

        }

        Log.d("FILENAME", fileName);
        Log.d("TITLE", title);

        return aartiKathaDetails;


    }


     int generateRandomNumberInRange(int min, int max) {

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }



    public boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    
     int getindexWithinRange(int randomNumber, int min) {
         Log.d("getindexWithinRange", "NUM" + randomNumber  + "  MIN" + min);
         return randomNumber - min;
     }
}
