package com.example.nihal.notification;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FireBaseMessaging extends FirebaseInstanceIdService{
   public static final String TOKEN_BROADCAST="myfcmtokenbroadcast";

    @Override
    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));


        Log.d("Token :::->>",refreshedToken);
        storeToken(refreshedToken);
    }



    private void storeToken(String token){
            SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
