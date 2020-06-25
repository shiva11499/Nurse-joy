package com.gshivansh37.nursejoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private EditText id;
    private EditText password;
    public Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);

        String id_req = id.getText().toString();
        final String password_req = password.getText().toString();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("1234")) {
                    Intent intent = new Intent(Signup.this, MainActivity.class);
                    startActivity ( intent );
                    Toast.makeText(getApplicationContext(), "Access granted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Access denied!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
