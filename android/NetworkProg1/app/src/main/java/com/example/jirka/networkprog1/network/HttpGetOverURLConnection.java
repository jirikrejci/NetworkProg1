package com.example.jirka.networkprog1.network;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jirka on 9.8.2016.
 */
public class HttpGetOverURLConnection extends AsyncTask<String, String, String> {


    public interface HttpReadListener {
        void onHttpReadFinished ();

    }

    TextView tvOutputTextView = null;
    StringBuffer responseData;
    HttpReadListener httpReadListener = null;




    public void  setTvOutputTextView (TextView tv) {
        this.tvOutputTextView = tv;
    }

    public void setHttpReadListener (HttpReadListener listener) {
        this.httpReadListener = listener;
    }



        @Override
        protected String doInBackground(String... params) {
            responseData = new StringBuffer();
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection(); // Connect to server and GET request

                InputStream in = new BufferedInputStream(conn.getInputStream()); // zde vyhodí exception, když je špatně URL
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                int status =  conn.getResponseCode();

                String rawData;
                while ((rawData = br.readLine()) != null) {
                    responseData.append(rawData);
                }

                return responseData.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
        }



        @Override
        protected void onPostExecute(String text) {
            super.onPostExecute(text);
            if (tvOutputTextView != null) tvOutputTextView.setText(text);
            if (httpReadListener != null) httpReadListener.onHttpReadFinished();


        }

        @Override
        protected void onProgressUpdate(String... status) {
            super.onProgressUpdate(status);
    //        Toast.makeText(getApplicationContext(), status[0], Toast.LENGTH_SHORT).show();


        }

        public String getHttpResponseString() {
            if (responseData !=  null)
                return responseData.toString();
            else
                return "NULL";
        }



}
