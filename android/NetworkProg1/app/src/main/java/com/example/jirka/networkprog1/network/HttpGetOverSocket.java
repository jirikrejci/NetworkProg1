package com.example.jirka.networkprog1.network;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jirka on 9.8.2016.
 */
public class HttpGetOverSocket extends AsyncTask<String, Void, String> {
    TextView outputTextView = null;

    public void setOutputTextView (TextView textView) {
        this.outputTextView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        Socket socket = null;
        StringBuffer data = new StringBuffer();

        try {
            socket = new Socket(params[0], 80);  // connect to server  - už tady vyhazuje exception při špatně zadaném "url"
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println("GET /index.html");
            pw.flush();

            BufferedReader brInputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String rawData;

            while ((rawData = brInputReader.readLine()) != null) {
                data.append(rawData);
            };

            return data.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
        // close socket ?? tahle poznámka nevím, co znamená
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (outputTextView !=null)  outputTextView.setText(result);
    }
}
