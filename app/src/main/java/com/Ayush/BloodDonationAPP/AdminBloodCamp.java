package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Ayush.BloodDonationAPP.Model.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class AdminBloodCamp extends AppCompatActivity {

    private TextInputEditText placename, address, landmark, phonenumber;
    private FirebaseAuth mAuth;
    private Button update1;
    private ProgressDialog loader;
    private EditText title1,message1;

    private TextView disablePastDate,from_time,to_time;

    private DatabaseReference userDatabaseRef;
    private Toolbar toolbar;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("BloodCamp");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_blood_camp);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Camp Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        placename = findViewById(R.id.placename);
        address = findViewById(R.id.address);
        landmark = findViewById(R.id.landmark);
        phonenumber = findViewById(R.id.phonenumber);
        loader = new ProgressDialog(this);
        disablePastDate = findViewById(R.id.disable_past_date);
        from_time = findViewById(R.id.from_time);
        to_time = findViewById(R.id.to_time);

        placename.addTextChangedListener(TextView);
        address.addTextChangedListener(TextView);
        landmark.addTextChangedListener(TextView);
        phonenumber.addTextChangedListener(TextView);
        disablePastDate.addTextChangedListener(TextView);
        from_time.addTextChangedListener(TextView);
        to_time.addTextChangedListener(TextView);

        update1 = findViewById(R.id.update1);
        mAuth = FirebaseAuth.getInstance();
        title1 = findViewById(R.id.title1);
        message1 = findViewById(R.id.message1);

        from_time.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hours =calendar.get(Calendar.HOUR_OF_DAY);
                int mins =calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog= new TimePickerDialog(AdminBloodCamp.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, i);
                        c.set(Calendar.MINUTE, i1);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        from_time.setText(time);
                    }
                },hours ,mins, false);
                timePickerDialog.show();

            }
        });


        to_time.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hours =calendar.get(Calendar.HOUR_OF_DAY);
                int mins =calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog= new TimePickerDialog(AdminBloodCamp.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, i);
                        c.set(Calendar.MINUTE, i1);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        to_time.setText(time);
                    }
                },hours ,mins, false);
                timePickerDialog.show();

            }
        });


        //Initialize calendar
        Calendar calendar = Calendar.getInstance();
        //Get year
        int year = calendar.get(Calendar. YEAR);
        //Get month
        int month = calendar.get(Calendar.MONTH);
        //Get day
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        disablePastDate.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Initialize date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminBloodCamp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
                        month = month+1;
                        //store date in string
                        String sDate = dayofMonth + "/" + month + "/" + year;
                        //set date on text view
                        disablePastDate.setText(sDate);
                    }
                    }, year, month, day
                );
                //Disable past date
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                //Show date picker dialog
                datePickerDialog.show();
            }
        });





        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Placename = placename.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Landmark = landmark.getText().toString().trim();
                String Phonenumber = phonenumber.getText().toString().trim();
                String Disable_past_date = disablePastDate.getText().toString().trim();
                String From_time = from_time.getText().toString().trim();
                String To_time = to_time.getText().toString().trim();





                if (TextUtils.isEmpty(Placename)) {
                    placename.setError("Value is required!");
                    return;
                }

                if (TextUtils.isEmpty(Address)) {
                    address.setError("Value is required!");
                    return;
                }
                if (TextUtils.isEmpty(Landmark)) {
                    landmark.setError("Value is required!");
                    return;
                }
                if (TextUtils.isEmpty(Phonenumber)) {
                    phonenumber.setError("Value is required!");
                    return;
                }else {
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                            title1.getText().toString(), message1.getText().toString(), getApplicationContext(), AdminBloodCamp.this);
                    notificationsSender.SendNotifications();


                    userDatabaseRef = FirebaseDatabase.getInstance().getReference()
                            .child("BloodCamp");
                    HashMap userInfo = new HashMap();
                    userInfo.put("Placename", Placename);
                    userInfo.put("Address", Address);
                    userInfo.put("Landmark", Landmark);
                    userInfo.put("Phonenumber", Phonenumber);
                    userInfo.put("Date", Disable_past_date);
                    userInfo.put("From Time", From_time);
                    userInfo.put("To Time", To_time);

                    userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AdminBloodCamp.this, "Data set Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AdminBloodCamp.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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
            String Placename = placename.getText().toString().trim();
            String Address = address.getText().toString().trim();
            String Landmark = landmark.getText().toString().trim();
            String Phonenumber = phonenumber.getText().toString().trim();
            String Disable_past_date = disablePastDate.getText().toString().trim();
            String From_time = from_time.getText().toString().trim();
            String To_time = to_time.getText().toString().trim();





            update1.setEnabled(!Placename.isEmpty() && !Address.isEmpty() &&
                    !Landmark.isEmpty() &&!Phonenumber.isEmpty()
                     &&!Disable_past_date.isEmpty() &&
                    !From_time.isEmpty() &&!To_time.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_camp,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.view_camp:
                Toast.makeText(AdminBloodCamp.this, "View Update Details", Toast.LENGTH_SHORT).show();
                Intent intent1  = new Intent(AdminBloodCamp.this, BloodCampsActivity.class);
                startActivity(intent1);
                finish();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}