package com.udacity.capstone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

import com.udacity.capstone.R;
import com.udacity.capstone.data.AppConstants;
import com.udacity.capstone.utilities.PrefManager;
import com.udacity.capstone.data.AartiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LordAartiCollectionActivity extends BaseActivity {


    @Bind(R.id.cardList)
    RecyclerView cardList;

    private List<AartiModel> aartiModels;
    ArrayList<String> aartiList;
    ArrayList<String> aartiFileList;
    String godName;
    int imageResourceID = 0;

    String category = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lordaartigroup);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        cardList.setLayoutManager(llm);
        if (getIntent().getExtras() != null) {
            aartiList = getIntent().getExtras().getStringArrayList(AppConstants.AARTI_LIST);
            aartiFileList = getIntent().getExtras().getStringArrayList(AppConstants.AARTI_FILE_LIST);
            godName = getIntent().getExtras().getString(AppConstants.GOD_TITLE);
            imageResourceID = getIntent().getExtras().getInt(AppConstants.IMAGE_ID);
        }
        getSupportActionBar().setTitle(Html.fromHtml("<small>" + godName + "</small>"));


        if(aartiList!=null) {
            initializeDataFromIntent();
        }
        else {
            initializeData();
        }
        RVAdapter adapter = new RVAdapter(aartiModels, this);
        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, AartiModel viewModel) {
                sendEvent(godName, viewModel.getName()); //to trace which god and which prayer selected..adb shell setprop log.tag.GAv4 DEBUG
                Intent aartiDetailIntent = new Intent(LordAartiCollectionActivity.this, AartiDetailActivity.class);
                aartiDetailIntent.putExtra(AppConstants.FILE_NAME, viewModel.getFileName());
                aartiDetailIntent.putExtra(AppConstants.AARTI_TITLE, viewModel.getName());
                aartiDetailIntent.putExtra(AppConstants.IMAGE_ID, imageResourceID);
                LordAartiCollectionActivity.this.startActivity(aartiDetailIntent);

            }

            @Override
            public void onFavouriteClick(AartiModel viewModel) {

                sendEvent("FAVOURITE", viewModel.getName());
                PrefManager prefManager =  new PrefManager(LordAartiCollectionActivity.this);
                prefManager.saveFavouriteAarti(viewModel.getName(), imageResourceID, viewModel.getFileName());


            }
        });
        cardList.setAdapter(adapter);

        sendScreenName("COLLECTION SCREEN ACTIVITY");
    }

    private void initializeData() {
    }

    private void initializeDataFromIntent() {
        aartiModels = new ArrayList();
        int count = 0;
        for(String title : aartiList) {
            aartiModels.add(new AartiModel(title,aartiFileList.get(count++)));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
