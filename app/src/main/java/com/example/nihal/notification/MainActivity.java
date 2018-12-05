package com.example.nihal.notification;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    //public static final String TOKEN_BROADCAST="fcmtokenbroadcast";


    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;


    private TextView textView;
//  private BroadcastReceiver broadcastReceiver;
    private static final String TAG = "MainActivity";
    private static final int Error_Dialog=9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Starting of Navigation bar code

        toolbar=findViewById(R.id.navigation_bar);
        drawerLayout=findViewById(R.id.Drawerlayout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);

        //Ending of Navigation bar code

        textView=findViewById(R.id.showToken);


        final String TOKEN_Val=SharedPrefManager.getInstance(MainActivity.this).getDeviceToken();
         //   Log.d("TTTOOOKKEEEENNNN_value:",TOKEN_Val);

       if (SharedPrefManager.getInstance(this).getDeviceToken() !=null){
            textView.setText(TOKEN_Val);
            Log.d("FCM_TOKEN:" ,SharedPrefManager.getInstance(this).getDeviceToken());
           //Log.d("lati:" ,SharedPrefManager.getInstance(this).get_Ambulance_Latitude());

           //Log.d("FCM_TOKEN:" ,SharedPrefManager.getInstance(this).get_Ambulance_Longitude());

          // Log.d("FCM_TOKEN:" ,SharedPrefManager.getInstance(this).get_client_Latitude());

//           Log.d("FCM_TOKEN:" ,SharedPrefManager.getInstance(this).get_client_Longitude());

       }


        registerReceiver(broadcastReceiver, new IntentFilter(FireBaseMessaging.TOKEN_BROADCAST));
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
       if(isServiceOK()){
           init();
           getLocation();
       }

    }

   final BroadcastReceiver broadcastReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(FireBaseMessaging.TOKEN_BROADCAST)) {
                textView.setText(SharedPrefManager.getInstance(MainActivity.this).getDeviceToken());
                Log.d("TTTOOOKKKKEENNNN_value:", SharedPrefManager.getInstance(MainActivity.this).getDeviceToken());
                Toast.makeText(context, SharedPrefManager.getInstance(MainActivity.this).getDeviceToken(), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context,"There is no any message",Toast.LENGTH_LONG);
            }

        }
    };

    public void init(){
        Button btn= findViewById(R.id.Mapbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServiceOK(){
        Log.d(TAG,"isServiceOK: checking gooogle services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG,"isServiceOK: Google Play serivces is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG,"isServiceOK: an error occured but we can fix it");
            Dialog dialog =GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available,Error_Dialog);
            dialog.show();
        }else{
            Toast.makeText(this,"Can't make map requests",Toast.LENGTH_SHORT).show();
        }
        return false;
    }







        //uploading section
    LocationManager locationManager;
    static final int REQUEST_LOCATION=1;

    public void getLocation(){

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        }
        else{
            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null){
                double latti=location.getLatitude();

                String latitude= String.valueOf(latti);
                float lats=Float.valueOf(latitude);
                double longi=location.getLongitude();
                String longiutde=String.valueOf(longi);
                float longis=Float.valueOf(longiutde);
                BackgroundTask backgroundTask=new BackgroundTask(this);
                String type="login";
                backgroundTask.execute(type,latitude,longiutde);
                //TokenBackgroundTask tokenBackgroundTask=new TokenBackgroundTask(this);
                //String token=SharedPrefManager.getInstance(this).getDeviceToken();
                //String type1="login1";
                //tokenBackgroundTask.execute(type1,token,"");
                store_Ambu_latitude(lats);
                store_Ambu_longitude(longis);

            }else{
            }


        }
    }


    private void store_Ambu_latitude(float latitude){
        SharedPrefManager.getInstance(getApplicationContext()).save_Ambu_Latitude(latitude);
    }
    private void store_Ambu_longitude(float longitude){
        SharedPrefManager.getInstance(getApplicationContext()).save_Ambu_Longitude(longitude);
    }



}
