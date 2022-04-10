package com.Ayush.BloodDonationAPP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView type, name, email, idNumber, phoneNumber, bloodGroup,id;

    private CircleImageView profileImage;
    private Button backButton, delete_account;
    ProgressBar progressbar;
    private DatabaseReference ref;
    FirebaseUser firebaseuser;
    FirebaseAuth firebaseAuth;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        type = findViewById(R.id.type);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        idNumber = findViewById(R.id.idNumber);
        phoneNumber = findViewById(R.id.phoneNumber);
        bloodGroup = findViewById(R.id.bloodGroup);
        profileImage = findViewById(R.id.profileImage);
        backButton = findViewById(R.id.backButton);

        delete_account = findViewById(R.id.delete_account);
        progressbar = findViewById(R.id.progressbar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseuser = firebaseAuth.getCurrentUser();


        //Delete Account code

//        Intent intent=getIntent();
//        String key=intent.getStringExtra( "key");
//        ref= FirebaseDatabase.getInstance().getReference("users");
//        delete_account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            deleteUser(key);
//            }
//
//            private void deleteUser(String key) {
//                FirebaseDatabase.getInstance ().getReference( "users").child("id").removeValue ();
//                Toast.makeText(getApplicationContext(),"Recored deleted!", Toast. LENGTH_LONG).show();
//            }
//        });





//






        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("users").child((FirebaseAuth.getInstance().getCurrentUser()).getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    type.setText(snapshot.child("type").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());
                    idNumber.setText(snapshot.child("idnumber").getValue().toString());
                    phoneNumber.setText(snapshot.child("phonenumber").getValue().toString());
                    bloodGroup.setText(snapshot.child("bloodgroup").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    id.setText(snapshot.child("id").getValue().toString());


                    Glide.with(getApplicationContext()).load(snapshot.child("profilepictureurl").getValue().toString()).into(profileImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in completely removing your " +
                        "account from the system and you won't be able to access the app.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressbar.setVisibility(View.VISIBLE);



                        firebaseuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(ProfileActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });





        
        

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });






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
//    delete_account.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        String name2 =id.getText().toString();
//        if(!name2.isEmpty());
//        {
//        delete(name2);
//        }
//        }
//private void delete(String name2) {
//        ref = FirebaseDatabase.getInstance().getReference( "users");
//        ref.child(name2).removeValue();
//        }
//        });

