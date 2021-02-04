package com.poc.exported_service_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class Server_Service extends Service {

    public Server_Service() {
    }

    //Create new stub and write implementation for all the function declared in the AIDL file
    protected AidlInterface.Stub binder = new AidlInterface.Stub() {
        @Override
        public String printname(String name, String id) throws RemoteException {
            return "Hello " + name + " - " + id;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the created binder object to the client who is binding to this service
        return binder;
    }
}