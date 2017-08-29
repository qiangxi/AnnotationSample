package com.qiangxi.annotationsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qiangxi.BindField;
import com.qiangxi.annotationsample.R;

public class MainActivity extends AppCompatActivity {

    @BindField(true)
    private static final String id = "rqq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
