package com.example.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnChange;
    TextView text;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        btnChange = (Button) findViewById(R.id.buttonChanageText);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                text.setText("Text has been changed");
            }
        };
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            try {
                                Log.i("LOG", "start");
                                wait(1000);
                                handler.sendEmptyMessage(0);
                                Log.i("LOG", "finish");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }
        });
    }
}