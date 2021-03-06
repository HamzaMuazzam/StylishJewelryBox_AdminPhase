package com.example.stylishjewelryboxadminphase.fcmAll;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.stylishjewelryboxadminphase.R;
import com.example.stylishjewelryboxadminphase.activities.OrderByNotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessageService extends FirebaseMessagingService {
    public static final String TAG = "MYTAG";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("MYTAG", "onMessageReceived: ");

        Log.d("MYTAG", "onMessageReceived: Message received from  " + remoteMessage.getFrom());


        if (remoteMessage.getNotification() != null) {
//            String title = remoteMessage.getNotification().getTitle();
//            String body = remoteMessage.getNotification().getBody();
//            Intent intent=new Intent(this, OrderByNotificationActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,
//                    intent,
//                    PendingIntent.FLAG_ONE_SHOT);
//
//            Notification notification = new
//                    NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_panel)
//                    .setContentTitle("title")
//                    .setContentText("body")
//                    .setAutoCancel(true)
//                    .setColor(Color.BLACK)
//                    .setContentIntent(pendingIntent)
//                    .build();
//            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            manager.notify(1002, notification);
        }
        if (remoteMessage.getData().size() > 0) {


            Intent intent = new Intent(this, OrderByNotificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            String oId = remoteMessage.getData().get("key");
            intent.putExtra("key", oId);
            Log.d(TAG, "onMessageReceived KEY: " + oId);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,
                    intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Notification notification = new
                    NotificationCompat.Builder(this, App.FCM_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_panel)
                    .setContentTitle("Order Received")
                    .setContentText("A new order has been received click to see details")
                    .setAutoCancel(true)
                    .setColor(Color.BLACK)
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(1002, notification);

//            int size = remoteMessage.getData().size();
//            String[] array = new String[size];
//            int x = 0;
//
//            for (String key : remoteMessage.getData().keySet()) {
//                array[x] = remoteMessage.getData().get(key);
////                Log.d(TAG, " "+key+"\n"+remoteMessage.getData().get(key));
//                x++;
//            }


            Intent intent2 = new Intent("FCM");
            intent2.putExtra("myid", oId);
            sendBroadcast(intent2);
            Log.d(TAG, "i am called" + oId);

        }



        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {

        Log.d(TAG, "onDeletedMessages: Called:");
        super.onDeletedMessages();

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("MYTAG", "onNewToken: " + s);
        Utils.savePreferences("token", s, this);


    }
}
