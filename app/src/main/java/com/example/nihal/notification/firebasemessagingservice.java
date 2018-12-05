package com.example.nihal.notification;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;

public class firebasemessagingservice extends FirebaseMessagingService {

    private static final String TAG = "fcmexample";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        Map<String,String> params=remoteMessage.getData();
        JSONObject object=new JSONObject(params);
        Log.d("DATA'S:",object.toString());
        String body=object.optString("body");
        Log.d(TAG, "Body'sssssss: " + body);
        String latitude=object.optString("icon");
        float lati1=Float.valueOf("27.6844");
        Log.d(TAG, "Laitude:-->>>>>> "+lati1);
        String longitude=object.optString("color");
        float long1=Float.valueOf("85.3059");
        Log.d(TAG, "Longitude:-->>>>>> "+lati1);
        Log.d(TAG, "Latitude: "+latitude);
        Log.d(TAG, "Longitude: "+longitude);
     //  String Latitude=remoteMessage.getNotification().getIcon();
       // String Longitude=remoteMessage.getNotification().getColor();

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " +remoteMessage.getFrom());
        //Log.d(TAG, "Latitude: " +remoteMessage.getNotification().getIcon());
        //Log.d(TAG, "Longitude: " +remoteMessage.getNotification().getColor());
        //Log.d(TAG, "Title: " + remoteMessage.getNotification().getTitle());
        //Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        notifyUser(remoteMessage.getFrom(),body);
         storeLatitude(lati1);
            storeLongitude(long1);

    }
    public void notifyUser(String from, String notification){
        mynotification MYnotify=new mynotification(getApplicationContext());
        MYnotify.showNotification(from,notification,new Intent(getApplicationContext(),SecondActivity.class));
    }
    public void storeLatitude(float latitude){
        SharedPrefManager.getInstance(getApplicationContext()).save_Client_Latitude(latitude);
    }
    public void storeLongitude(float longitude){
        SharedPrefManager.getInstance(getApplicationContext()).save_Client_Longitude(longitude);
    }
}
