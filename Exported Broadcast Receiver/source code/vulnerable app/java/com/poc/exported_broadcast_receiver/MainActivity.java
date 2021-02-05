package com.poc.exported_broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView btc;
    TextView lock;
    TextView message;
    Button change;

    MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btc = findViewById(R.id.btc);
        lock = findViewById(R.id.lock);
        message = findViewById(R.id.message);
        change = findViewById(R.id.change);

        myBroadcastReceiver = new MyBroadcastReceiver() {
            @Override
            public void whenHacked() {
                btc.setText("Your bitcoin: 0 BTC");
                lock.setText("LOCK: BROKEN");
                message.setText("Hacker X have taken all your money");
            }

            @Override
            public void whenChanged() {
                if (lock.getText().equals("LOCK: ON")) {
                    lock.setText("LOCK: OFF");
                    message.setText("Your money is unsafe");
                } else if (lock.getText().equals("LOCK: OFF")) {
                    lock.setText("LOCK: ON");
                    message.setText("Your money is safe");
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("BTClock");
        this.registerReceiver(myBroadcastReceiver, intentFilter);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.poc.exported_broadcast_receiver.MyBroadcastReceiver.");
                intent.setAction("BTClock");
                intent.putExtra("data", "change");
                sendBroadcast(intent);
            }
        });
    }
}
