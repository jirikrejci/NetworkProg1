package com.example.jirka.networkprog1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class actURLExample extends AppCompatActivity {

    TextView tvNetworkOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_example1);

        tvNetworkOutput =  (TextView) findViewById(R.id.tvNetworkOutput);
        ((Button) findViewById(R.id.btnRunExample1)).setText("Run URL Example");

    }



    private class HttpGet extends AsyncTask <String, String, String>{


        @Override
        protected String doInBackground(String... params) {
            StringBuffer serverData = new StringBuffer();
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection(); // Connect to server and GET request

                InputStream in = new BufferedInputStream(conn.getInputStream()); // zde vyhodí exception, když je špatně URL
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                int status =  conn.getResponseCode();

                String rawData;
                while ((rawData = br.readLine()) != null) {
                    serverData.append(rawData);
                }

                return serverData.toString();




            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }


            //return serverData.toString();
        }



        @Override
        protected void onPostExecute(String text) {
            super.onPostExecute(text);
            tvNetworkOutput.setText(text);


        }

        @Override
        protected void onProgressUpdate(String... status) {
            super.onProgressUpdate(status);
            Toast.makeText(getApplicationContext(), status[0], Toast.LENGTH_SHORT).show();


        }
    }

    public void btnRunExample1_onClick(View view) {

        new HttpGet().execute("http://www.dre.vanderbilt.edu");
    }


    public void btnToastTest_onClick(View view) {
        ToastTest.runToastTest(this, "Toast Test v Class");
    }

}
