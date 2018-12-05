package com.example.nihal.notification;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class TokenBackgroundTask extends AsyncTask<String,Void,String> {


    Context context;
    AlertDialog alertDialog;

    TokenBackgroundTask(Context ctx){
        context=ctx;
    }
    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String login_url = "http://nepsian.000webhostapp.com/token_register.php";

        if (type.equals("login1")) {
            try {
                String Latitude = voids[1];
                String Longitude = voids[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Latitude", "UTF-8") + "=" + URLEncoder.encode(Latitude, "UTF-8") + "&"
                        + URLEncoder.encode("Longitude", "UTF-8") + "=" + URLEncoder.encode(Longitude, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog =new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Location and Token");
    }

    @Override
    protected void onPostExecute(String aVoid) {
        alertDialog.setMessage(aVoid);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
