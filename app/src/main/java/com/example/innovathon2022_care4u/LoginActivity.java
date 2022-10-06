package com.example.innovathon2022_care4u;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;

    TextView forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);

        Button login_button = (Button) findViewById(R.id.login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        forgot_password = (TextView) findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals("vedant1234") && email.getText().toString().equals("vedant@gmail.com")) {
                    Intent i = new Intent(LoginActivity.this, UserHomeDashboard.class);
                    LoginActivity.this.startActivity(i);
                } else {
                    login_button.setBackgroundColor(getResources().getColor(R.color.red));
                    login_button.setText("Wrong password!");

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            login_button.setBackgroundColor(getResources().getColor(R.color.navy_blue));
                            login_button.setText("Login");
                        }
                    }, 1000);
                }
            }
        });
    }
}