package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class Admin_Update_Profile extends AppCompatActivity {

    private TextInputEditText name, address, email, phonenumber;
    private Button update;
    private Toolbar toolbar;
    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_update_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin Profile Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        loader = new ProgressDialog(this);
        phonenumber = findViewById(R.id.phonenumber);
        update = findViewById(R.id.update);
        mAuth = FirebaseAuth.getInstance();

        name.addTextChangedListener(TextView);
        address.addTextChangedListener(TextView);
        email.addTextChangedListener(TextView);
        phonenumber.addTextChangedListener(TextView);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Phonenumber = phonenumber.getText().toString().trim();



                if (TextUtils.isEmpty(Name)) {
                    name.setError("Value is required!");
                    return;
                }
                if (TextUtils.isEmpty(Address)) {
                    address.setError("Value is required!");
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    email.setError("Value is required!");
                    return;
                }
                if (TextUtils.isEmpty(Phonenumber)) {
                    phonenumber.setError("Value is required!");
                    return;
                }else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();



                    userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                            .child("Admin Profile");
                    HashMap userInfo = new HashMap();
                    userInfo.put("Name", Name);
                    userInfo.put("Address", Address);
                    userInfo.put("Email", Email);
                    userInfo.put("Phonenumber", Phonenumber);




                    userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Admin_Update_Profile.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Admin_Update_Profile.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            finish();
                            loader.dismiss();
                        }
                    });
                }
            }
        });


    }
    private final TextWatcher TextView = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Name = name.getText().toString().trim();
            String Address = address.getText().toString().trim();
            String Email = email.getText().toString().trim();
            String Phonenumber = phonenumber.getText().toString().trim();






            update.setEnabled(!Name.isEmpty() && !Address.isEmpty() &&
                    !Email.isEmpty() &&!Phonenumber.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.view_admin:
                Toast.makeText(Admin_Update_Profile.this, "View Profile", Toast.LENGTH_SHORT).show();
                Intent intent1  = new Intent(Admin_Update_Profile.this, Admin_Profile.class);
                startActivity(intent1);
                finish();
                return true;
            case android.R.id.home:
                finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}