package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class AdminBloodBank extends AppCompatActivity {

    private TextInputEditText A_positive, A_negative, B_positive, B_negative,O_positive, O_negative, AB_positive, AB_negative;
    private Button update , Read;
    private ProgressDialog loader;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;

    private TextView textview;
    private EditText   title1,message1;

    private Toolbar toolbar;




    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("BloodBank");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_blood_bank);

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Bank Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        A_positive = findViewById(R.id.A_positive);
        A_negative = findViewById(R.id.A_negative);
        B_positive = findViewById(R.id.B_positive);
        B_negative = findViewById(R.id.B_negative);
        O_positive = findViewById(R.id.O_positive);
        O_negative = findViewById(R.id.O_negative);
        AB_positive = findViewById(R.id.AB_positive);
        AB_negative = findViewById(R.id.AB_negative);


        A_positive.addTextChangedListener(TextEmpty);
        A_negative.addTextChangedListener(TextEmpty);
        B_positive.addTextChangedListener(TextEmpty);
        B_negative.addTextChangedListener(TextEmpty);
        O_positive.addTextChangedListener(TextEmpty);
        O_negative.addTextChangedListener(TextEmpty);
        AB_positive.addTextChangedListener(TextEmpty);
        AB_negative.addTextChangedListener(TextEmpty);



        loader = new ProgressDialog(this);
        update = findViewById(R.id.update);

        mAuth = FirebaseAuth.getInstance();


        textview = findViewById(R.id.textview);
        title1 = findViewById(R.id.title1);
        message1 = findViewById(R.id.message1);

        Read = findViewById(R.id.Read);

//        userDatabaseRef = FirebaseDatabase.getInstance().getReference()
//                .child("BloodBank");

        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(AdminBloodBank.this, BloodBank.class);
                startActivity(intent);
                finish();
            }
        });

//        Read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userDatabaseRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()){
//                            String data = snapshot.getValue().toString();
//                            textview.setText(data);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });







        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String A_Positive = A_positive.getText().toString().trim();
                 String A_Negative = A_negative.getText().toString().trim();
                 String B_Positive = B_positive.getText().toString().trim();
                 String B_Negative = B_negative.getText().toString().trim();
                 String O_Positive = O_positive.getText().toString().trim();
                 String O_Negative = O_negative.getText().toString().trim();
                 String AB_Positive = AB_positive.getText().toString().trim();
                 String AB_Negative = AB_negative.getText().toString().trim();

                //convert string to int

                 int A_Positive_Int = Integer.parseInt(A_Positive);
                 int A_Negative_Int = Integer.parseInt(A_Negative);
                int B_Positive_Int = Integer.parseInt(B_Positive);
                int B_Negative_Int = Integer.parseInt(B_Negative);
                int O_Positive_Int = Integer.parseInt(O_Positive);
                 int O_Negative_Int = Integer.parseInt(O_Negative);
                 int AB_Positive_Int = Integer.parseInt(AB_Positive);
                 int AB_Negative_Int = Integer.parseInt(AB_Negative);


//                if (TextUtils.isEmpty(A_Positive)) {
//                    A_positive.setError("Value is required!");
//                    return;
//                }
                if (A_Positive_Int > 100) {
                    A_positive.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(A_Negative)) {
//                    A_negative.setError("Value is required!");
//                    return;
//                }
                if (A_Negative_Int > 100) {
                    A_negative.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(B_Positive)) {
//                    B_positive.setError("Value is required!");
//                    return;
//                }
                if (B_Positive_Int > 100) {
                    B_positive.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(B_Negative)) {
//                    B_negative.setError("Value is required!");
//                    return;
//                }
                if (B_Negative_Int > 100) {
                    B_negative.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(O_Positive)) {
//                    O_positive.setError("Value is required!");
//                    return;
//                }
                if (O_Positive_Int > 100) {
                    O_positive.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(O_Negative)) {
//                    O_negative.setError("Value is required!");
//                    return;
//                }
                if (O_Negative_Int > 100) {
                    O_negative.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(AB_Positive)) {
//                    AB_positive.setError("Value is required!");
//                    return;
//                }
                if (AB_Positive_Int > 100) {
                    AB_positive.setError("Value is greater than 100!");
                    return;
                }
//                if (TextUtils.isEmpty(AB_Negative)) {
//                    AB_negative.setError("Value is required!");
//                    return;
//                }
                if (AB_Negative_Int > 100) {
                    AB_negative.setError("Value is greater than 100!");
                    return;
                }else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                            title1.getText().toString(),message1.getText().toString(),getApplicationContext(),AdminBloodBank.this);
                    notificationsSender.SendNotifications();

                    userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                         .child("BloodBank");
                    HashMap userInfo = new HashMap();
                    userInfo.put("A+", A_Positive);
                    userInfo.put("A-", A_Negative);
                    userInfo.put("B+", B_Positive);
                    userInfo.put("B-", B_Negative);
                    userInfo.put("O+", O_Positive);
                    userInfo.put("O-", O_Negative);
                    userInfo.put("AB+", AB_Positive);
                    userInfo.put("AB-", AB_Negative);


                    userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AdminBloodBank.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AdminBloodBank.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            finish();
                            loader.dismiss();
                        }
                    });
                }
            }
        });
    }

    private TextWatcher TextEmpty = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String A_positiv  = A_positive.getText().toString().trim();
            String A_negativ = A_negative.getText().toString().trim();
            String B_positiv  = B_positive.getText().toString().trim();
            String B_negativ = B_negative.getText().toString().trim();
            String O_positiv  = O_positive.getText().toString().trim();
            String O_negativ = O_negative.getText().toString().trim();
            String AB_positiv = AB_positive.getText().toString().trim();
            String AB_negativ = AB_negative.getText().toString().trim();


            update.setEnabled(!A_positiv.isEmpty() && !A_negativ.isEmpty() &&
                    !B_positiv.isEmpty() &&!B_negativ.isEmpty() &&
                    !O_positiv.isEmpty() &&!O_negativ.isEmpty() &&
                    !AB_positiv.isEmpty() &&!AB_negativ.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.graph:
                Toast.makeText(AdminBloodBank.this, "Graph View", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(AdminBloodBank.this, GraphBloodBank.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.view:
                Toast.makeText(AdminBloodBank.this, "View Update Details", Toast.LENGTH_SHORT).show();
                Intent intent1  = new Intent(AdminBloodBank.this, BloodBank.class);
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