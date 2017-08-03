package com.ssjjsy.sdk.plugin.two;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ssjjsy.sdk.plugin.two.activity.forplugin1.NormalActivity0;
import com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity0;
import com.ssjjsy.sdk.plugin.two.activity.forplugin1.ProcessActivity1;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        Log.i("SM", "Activity name: " + this.getClass().getSimpleName() + " ,taskId: " + getTaskId());

        findViewById(R.id.btn_activity_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NormalActivity0.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_activity_backToPluginOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_activity_sameProcessToPluginOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProcessActivity0.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_activity_otherProcessToPluginOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProcessActivity1.class);
                startActivity(intent);
            }
        });
    }
}
