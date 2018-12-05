package com.example.nihal.notification;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME="FCMSharedPref";
    private static final String SHARED_PREF_NAME1="latitide";
    private static final String SHARED_PREF_NAME2="longiutde";
    private static final String SHARED_PREF_NAME3="a_longiutde";
    private static final String SHARED_PREF_NAME4="a_longiutde";

    private static final String TAG_TOKEN ="tagToken";
    private static final String TAG_LATITUDE="lati";
    private static final String TAG_LONGITUDE="longi";
    private static final String TAG_LATITUDE1="lati";
    private static final String TAG_LONGITUDE1="longi";


    private static SharedPrefManager mInstance;
    private static Context mcontext;

    private SharedPrefManager(Context context)
    {

        mcontext =context;
    }


    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance ==null){
            mInstance= new SharedPrefManager(context);
        }
        return mInstance;
    }

    //This method will save the device token to shared prefrences
    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TAG_TOKEN,token);
        editor.apply();
        return true;
    }


    public boolean save_Client_Latitude(float Latitude){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME1,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat(TAG_LATITUDE,Latitude);
        editor.apply();
        return true;

    }
    public boolean save_Client_Longitude(float Longitude){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME2,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat(TAG_LONGITUDE,Longitude);
        editor.apply();
        return true;
    }
    public boolean save_Ambu_Latitude(float Latitude){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME3,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat(TAG_LATITUDE1,Latitude);
        editor.apply();
        return true;
    }
    public boolean save_Ambu_Longitude(float Longitude){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME4,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat(TAG_LONGITUDE1,Longitude);
        editor.apply();
        return true;
    }




    //This method will fetch the device token from shared preferences
        public String getDeviceToken(){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_TOKEN,null);
        }
    public float get_client_Latitude(){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME1,Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(TAG_LATITUDE1,0);
    }
    public float get_client_Longitude(){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME2,Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(TAG_LONGITUDE1,0);
    }
    public float get_Ambulance_Latitude(){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME3,Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(TAG_LATITUDE,0);
    }
    public float get_Ambulance_Longitude(){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME4,Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(TAG_LONGITUDE,0);
    }


}
