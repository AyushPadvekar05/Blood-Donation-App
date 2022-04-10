package com.Ayush.BloodDonationAPP;


import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.util.Log;
import android.widget.SearchView;
//import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Ayush.BloodDonationAPP.Adapter.UserAdapter;
import com.Ayush.BloodDonationAPP.Email.JavaMailApi;
import com.Ayush.BloodDonationAPP.Model.User;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DonorList extends AppCompatActivity {

    RecyclerView donorlist;
    DatabaseReference userRef;

    private List<User> userList;
    private UserAdapter userAdapter;

    UserAdapter adapter;

    private Toolbar toolbar;

    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_donor_list);

        donorlist = findViewById(R.id.donorlist);
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        donorlist.setHasFixedSize(true);
        donorlist.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(DonorList.this, userList);

        donorlist.setAdapter(userAdapter);
        progressbar = findViewById(R.id.progressbar);
        adapter=new UserAdapter(this,userList);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("users");
        Query query = reference.orderByChild("type").equalTo("donor");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                userAdapter.notifyDataSetChanged();
               // progressbar.setVisibility(View.GONE);

                if (userList.isEmpty()){
                    Toast.makeText(DonorList.this, "No donor", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
    // Search View

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search,menu );
//        MenuItem menuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (userAdapter != null) {
//                    userAdapter.getFilter().filter(newText);
//                }
//                return false;
//            }
//        });
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
//



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//
//        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem menuItem) {
//                Toast.makeText(DonorList.this, "Search Users", Toast.LENGTH_SHORT).show();
//
//                return true;
//            }
//
//            @Override

//            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//                return false;
//            }
//        };
//        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
//        SearchView searchView= (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setQueryHint("Search");
//
//        return true;
//
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
////                search_adapter.getFilter().filter(s);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }




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