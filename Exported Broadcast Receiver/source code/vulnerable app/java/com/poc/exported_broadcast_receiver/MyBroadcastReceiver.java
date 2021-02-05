package com.poc.exported_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public abstract class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String value = (String) intent.getExtras().get("data");
        if (value.equals("hacked")) {
            whenHacked();
        } else if (value.equals("change")) {
            whenChanged();
        }
    }

    public abstract void whenHacked();

    public abstract void whenChanged();
}
