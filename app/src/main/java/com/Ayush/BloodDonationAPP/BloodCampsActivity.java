package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class BloodCampsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseReference userDatabaseRef;
    private TextView placename, address, landmark, phonenumber,disable_past_date,from_time,to_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_camps);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Camp Updated");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        placename = findViewById(R.id.placename);
        address = findViewById(R.id.address);
        landmark = findViewById(R.id.landmark);
        phonenumber = findViewById(R.id.phonenumber);
        disable_past_date = findViewById(R.id.disable_past_date);
        from_time = findViewById(R.id.from_time);
        to_time = findViewById(R.id.to_time);

        userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                .child("BloodCamp");

        userDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Map map= (Map) snapshot.getValue();
                    Object Placename=map.get("Placename");
                    Object Address=map.get("Address");
                    Object Landmark=map.get("Landmark");
                    Object Phonenumber=map.get("Phonenumber");
                    Object Disable_past_date=map.get("Date");
                    Object From_time=map.get("From Time");
                    Object To_time=map.get("To Time");

                    placename.setText(""+Placename);
                    address.setText(""+Address);
                    landmark.setText(""+Landmark);
                    phonenumber.setText(""+Phonenumber);
                    disable_past_date.setText(""+Disable_past_date);
                    from_time.setText(""+From_time);
                    to_time.setText(""+To_time);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}