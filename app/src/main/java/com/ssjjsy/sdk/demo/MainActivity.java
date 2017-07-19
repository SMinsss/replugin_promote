package com.ssjjsy.sdk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qihoo360.replugin.RePlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_plugin1_start_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RePlugin.startActivity(MainActivity.this, RePlugin.createIntent("plugin_one", "com.ssjjsy.sdk.plugin.one.MainActivity"));
            }
        });

    }
}
