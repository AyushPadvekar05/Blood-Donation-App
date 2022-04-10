package com.Ayush.BloodDonationAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    /* Define the UI elements */
    private TextView backButton;
    private EditText AdminLoginEmail;
    private EditText AdminLoginPassword;
    private TextView AttemptsRemaining;
    private Button loginButton;
    private int counter = 5;

    String userName = "";
    String userPassword = "";

    /* Class to hold credentials */
    class Credentials
    {
        String name = "Admin";
        String password = "123456";
    }

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);

        /* Bind the XML views to Java Code Elements */
        AdminLoginEmail = findViewById(R.id.AdminLoginEmail);
        AdminLoginPassword = findViewById(R.id.AdminLoginPassword);
        AttemptsRemaining = findViewById(R.id.AttemptsRemaining);
        loginButton = findViewById(R.id.loginButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /* Describe the logic when the login button is clicked */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userName = AdminLoginEmail.getText().toString();
                userPassword = AdminLoginPassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(AdminLoginActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = validate(userName, userPassword);

                    /* Validate the user inputs */

                    /* If not valid */
                    if (!isValid) {

                        /* Decrement the counter */
                        counter--;

                        /* Show the attempts remaining */
                        AttemptsRemaining.setText("No. Of Attempts Remaining : " + String.valueOf(counter));

                        /* Disable the login button if there are no attempts left */
                        if (counter == 0) {
                            loginButton.setEnabled(false);
                            Toast.makeText(AdminLoginActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(AdminLoginActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If valid */
                    else {

                        /* Allow the user in to your app by going into the next activity */
                        Toast.makeText(AdminLoginActivity.this,"Log in successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminLoginActivity.this, AdminMainActivity.class));
                    }

                }
            }
        });
    }

    /* Validate the credentials */
    private boolean validate(String userName, String userPassword)
    {
        /* Get the object of Credentials class */
        Credentials credentials = new Credentials();

        /* Check the credentials */
        if(userName.equals(credentials.name) && userPassword.equals(credentials.password))
        {
            return true;
        }

        return false;
    }
}