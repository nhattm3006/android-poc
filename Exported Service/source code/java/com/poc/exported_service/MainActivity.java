package com.poc.exported_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txt_gmail;
    EditText txt_subject;
    EditText txt_message;
    Button btn_send;

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(MainActivity.this, SendMailService.class);
                serviceIntent.putExtra("gmail", txt_gmail.getText().toString().trim());
                serviceIntent.putExtra("subject", txt_subject.getText().toString());
                serviceIntent.putExtra("content", txt_message.getText().toString());
                startService(serviceIntent);
            }
        });
    }

    private void initialize() {
        txt_gmail = findViewById(R.id.txt_gmail);
        txt_subject = findViewById(R.id.txt_subject);
        txt_message = findViewById(R.id.txt_message);
        btn_send = findViewById(R.id.btn_send);
    }
}
