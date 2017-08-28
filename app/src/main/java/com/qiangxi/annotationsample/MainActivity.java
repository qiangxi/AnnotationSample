package com.qiangxi.annotationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qiangxi.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(22)
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
