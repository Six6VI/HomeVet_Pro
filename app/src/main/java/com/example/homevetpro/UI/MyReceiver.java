package com.example.homevetpro.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.homevetpro.R;

public class MyReceiver extends BroadcastReceiver {

    String channel_id = "notifyAlert";
    static int notifyID;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("appDate"),Toast.LENGTH_LONG).show();
        createNotifyChannel(context,channel_id);
        Notification n = new NotificationCompat.Builder(context,channel_id)
                .setSmallIcon(R.drawable.ic_my_vector)
                .setContentText(intent.getStringExtra("appDate"))
                .setContentTitle("Notification").build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifyID++,n);

    }
    private void createNotifyChannel(Context context, String CHANNEL_ID){
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}