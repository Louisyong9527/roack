package com.fnconn.myroack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.tv_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                throw new RuntimeException("tv_top按钮崩溃了");
            }
        });

        findViewById(R.id.tv_ringth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("tv_ringth按钮崩溃了");

            }
        });
    }
}
