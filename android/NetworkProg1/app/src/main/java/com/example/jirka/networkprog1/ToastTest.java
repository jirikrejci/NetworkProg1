package com.example.jirka.networkprog1;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jirka on 9.8.2016.
 */
public class ToastTest {
    public static void runToastTest (Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
}
