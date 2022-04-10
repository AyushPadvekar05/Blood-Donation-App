package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.Ayush.BloodDonationAPP.Adapter.UserAdapter;
import com.Ayush.BloodDonationAPP.Model.User;
import com.google.firebase.database.DatabaseError;

public class AboutSection extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CardView Previous;
    private CardView Information;
    private CardView help;
    private CardView Contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_section);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Previous =(CardView) findViewById(R.id.Previous);
        Information = (CardView)findViewById(R.id.Information);
        help = (CardView)findViewById(R.id.help);
        Contact = (CardView)findViewById(R.id.Contact);

        Previous.setOnClickListener(this);
        Information.setOnClickListener(this);
        help.setOnClickListener(this);
        Contact.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.Previous:
                Intent intent = new Intent(AboutSection.this,FAQ_Activity.class);
                startActivity(intent);
                break;

            case R.id.Information:
                Intent intent1 = new Intent(AboutSection.this,InformationActivity. class);
                startActivity(intent1);
                break;

            case R.id.help:
                Intent intent2  = new Intent(AboutSection.this,helpActivity. class);
                startActivity(intent2);
                break;

            case R.id.Contact:
                Intent intent3  = new Intent(AboutSection.this,Admin_Profile. class);
                startActivity(intent3);
                break;

        }

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