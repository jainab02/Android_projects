package com.example.practical_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    int loginAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginAttempts < 2) {
                    if (isValidCredentials(username.getText().toString(), password.getText().toString())) {
                        // Successful authentication - navigate to Google URL
                        Intent authenticationIntent = new Intent(Intent.ACTION_VIEW);
                        authenticationIntent.setData(Uri.parse("https://www.google.com/"));

                        if (authenticationIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(authenticationIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "No app can handle this intent", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Authentication failed
                        loginAttempts++;
                        Toast.makeText(MainActivity.this, "Authentication Failed. Attempt " + loginAttempts, Toast.LENGTH_LONG).show();
                        if(loginAttempts==2){
                            Toast.makeText(MainActivity.this, "Redirecting!!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                            startActivity(intent);
                        }
                    }
                }
//                else {
//                    // After two failed attempts, go to the Forgot Password page
//                    Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
//                    startActivity(intent);
//                }
            }
        });
    }

    // Check if the entered credentials are valid
    private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals("admin") && enteredPassword.equals("admin");
    }
}
