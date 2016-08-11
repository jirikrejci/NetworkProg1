package com.example.jirka.networkprog1.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jirka.networkprog1.R;
import com.example.jirka.networkprog1.ToastTest;
import com.example.jirka.networkprog1.network.HttpGetOverSocket;
import com.example.jirka.networkprog1.network.HttpGetOverURLConnection;

/**********************************************
 * tento příklad je pro ilustraci !!!!
 * normálně se nepoužívá !!!!!!!!!!!!!!
 * navíc mi program nefunguje - nenačítá se readline
 */

public class actSocketExample extends AppCompatActivity {

    TextView tvNetworkOutput;
    HttpGetOverSocket httpGetOverSocket;
    HttpGetOverURLConnection httpGetOverURLConnection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_example1);

        tvNetworkOutput =  (TextView) findViewById(R.id.tvNetworkOutput);






    }


    public void btnRunSocketExample_onClick(View view) {
        tvNetworkOutput.setText("");

        httpGetOverSocket = new HttpGetOverSocket();
        httpGetOverSocket.setOutputTextView(tvNetworkOutput);
        httpGetOverSocket.execute("www.dre.vanderbilt.edu");
    }

    public void btnRunURLExample_onClick(View view) {
        tvNetworkOutput.setText("");

        httpGetOverURLConnection = new HttpGetOverURLConnection();
//        httpGetOverURLConnection.setTvOutputTextView(tvNetworkOutput);  // předávání parametru na TextView není dle MVC !!!

        httpGetOverURLConnection.setHttpReadListener(new HttpGetOverURLConnection.HttpReadListener() {
            @Override
            public void onHttpReadFinished() {
                tvNetworkOutput.setText(httpGetOverURLConnection.getHttpResponseString());
            }
        });


        httpGetOverURLConnection.execute("http://www.dre.vanderbilt.edu");
    }

    public void btnToastTest_onClick(View view) {
        ToastTest.runToastTest(this, "Toast Test v Class");
    }

    public void btnShowURLExampleOutput_onClick(View view) {
        tvNetworkOutput.setText("");
        tvNetworkOutput.setText(httpGetOverURLConnection.getHttpResponseString());
    }

    public void btnClearDisplay_onClick(View view) {
        tvNetworkOutput.setText("");
    }

}
