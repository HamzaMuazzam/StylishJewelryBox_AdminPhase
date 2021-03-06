package com.example.stylishjewelryboxadminphase.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.fragments.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("notifyadmin").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(MainActivity.this, "Topic Subscribed", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Subscription failed", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment, new HomeFragment()).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Log.d("MYTAG", "getIntent: " + getIntent().getStringExtra(key));

            }

        }

    }
}
