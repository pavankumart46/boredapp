package com.blogspot.pavankreddytadi.imfeelingbored;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchAnActivity extends AsyncTask<Void,Void,String>
{

    TextView activities;
    ProgressBar progressBar;

    public FetchAnActivity(TextView activities, ProgressBar progressBar) {
        this.activities = activities;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        try {
            URL url = new URL("http://www.boredapi.com/api/activity");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            if(inputStream==null)
                return "Data is not fetched";
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while((line=br.readLine())!=null)
            {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.INVISIBLE);
        if(s.equalsIgnoreCase("Data is not fetched")){
            activities.setText("Data is not fetched for Some Reason");
        }
        else
        {
            try {
                JSONObject root = new JSONObject(s);
                String activity = root.getString("activity");
                activities.setText(activity);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
