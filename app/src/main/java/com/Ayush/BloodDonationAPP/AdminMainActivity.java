package com.Ayush.BloodDonationAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.transition.Slide;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminMainActivity extends AppCompatActivity {

        NavigationView nav;
        ActionBarDrawerToggle toggle;
        DrawerLayout drawerLayout;
        CardView Donor,Recipient,BloodCamps,BloodBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Donor=findViewById(R.id.Donor);
        Recipient=findViewById(R.id.Recipient);
        BloodCamps=findViewById(R.id.BloodCamps);
        BloodBank=findViewById(R.id.BloodBank);

        Donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainActivity.this, DonorList.class);
                startActivity(intent);
            }
        });

        Recipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainActivity.this, RecipientList.class);
                startActivity(intent);
            }
        });

        BloodCamps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainActivity.this, AdminBloodCamp.class);
                startActivity(intent);
            }
        });

        BloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMainActivity.this, AdminBloodBank.class);
                startActivity(intent);
            }
        });


        nav=(NavigationView)findViewById(R.id.nav_view);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.sentEmail:
                        Intent intent2 = new Intent(AdminMainActivity.this, SentEmailActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.BloodCamps:
                        Intent intent3 = new Intent(AdminMainActivity.this, AdminBloodCamp.class);
                        startActivity(intent3);
                        break;

                    case R.id.BloodBank:
                        Intent intent4 = new Intent(AdminMainActivity.this, AdminBloodBank.class);
                        startActivity(intent4);
                        break;

                    case R.id.notifications:
                        Intent intent5 = new Intent(AdminMainActivity.this, NotificationsActivity.class);
                        startActivity(intent5);
                        break;

                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent6 = new Intent(AdminMainActivity.this, LoginActivity.class);
                        startActivity(intent6);
                        break;

                    case R.id.Donor:
                        Intent intent7 = new Intent(AdminMainActivity.this,DonorList.class);
                        startActivity(intent7);
                        break;

                    case R.id.Recipient:
                        Intent intent8 = new Intent(AdminMainActivity.this, RecipientList.class);
                        startActivity(intent8);
                        break;

                    case R.id.profile:
                        Intent intent9 = new Intent(AdminMainActivity.this, Admin_Update_Profile.class);
                        startActivity(intent9);
                        break;

                    case R.id.about:
                        Intent intent10 = new Intent(AdminMainActivity.this, AboutSection.class);
                        startActivity(intent10);
                        break;

                    case R.id.share:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent11 = new Intent(Intent.ACTION_SEND);
                        intent11.setType("text/plain");
                        String Body = "Download this App";
                        intent11.putExtra(Intent.EXTRA_TEXT,Body);
                        startActivity(intent11.createChooser(intent11,"Share Using"));
                        break;
                }

                return true;
            }
        });

    }
}
