package com.udacity.capstone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import com.udacity.capstone.R;

import butterknife.ButterKnife;

public class ContactUsActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(Html.fromHtml("<small>" + "Contact Us" + "</small>"));
    }

}
