package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.Ayush.BloodDonationAPP.Adapter.UserAdapter;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class View_Other_Profile extends AppCompatActivity {

    private Toolbar toolbar;


    TextView userName1,userEmail1,phoneNumber1,bloodGroup1,type1;
    Button emailNow;
    private CircleImageView userProfileImage1;
    private String userName,userEmail,phoneNumber,bloodGroup,type, userProfileImage;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_other_profile);



        userName = getIntent().getStringExtra("userName");
        userEmail = getIntent().getStringExtra("userEmail");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        bloodGroup = getIntent().getStringExtra("bloodGroup");
        type = getIntent().getStringExtra("type");
        userProfileImage = getIntent().getStringExtra("userProfileImage");




        userName1 = findViewById(R.id.userName1);
        userEmail1 = findViewById(R.id.userEmail1);
        phoneNumber1 = findViewById(R.id.phoneNumber1);
        bloodGroup1 = findViewById(R.id.bloodGroup1);
        type1 = findViewById(R.id.type);
        userProfileImage1 = findViewById(R.id.userProfileImage1);
        emailNow = findViewById(R.id.emailNow);



                Picasso.get().load(Uri.parse(userProfileImage)).into(userProfileImage1);

                userName1.setText(userName);
                userEmail1.setText(userEmail);
                phoneNumber1.setText(phoneNumber);
                bloodGroup1.setText(bloodGroup);
                type1.setText(type);
//


        // change the first letter to uppercase
        String firstLetter = userName.substring(0, 1);
        String remainingLetters = userName.substring(1, userName.length());
        firstLetter = firstLetter.toUpperCase();
        // join the two substrings
        userName = firstLetter + remainingLetters;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(userName+" Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}