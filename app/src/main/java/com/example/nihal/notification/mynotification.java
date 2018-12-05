package com.example.nihal.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class mynotification {
        private Context ctx;
        public mynotification(Context ctx){
            this.ctx=ctx;
        }
        public void showNotification(String from, String notification, Intent intent){
            Intent intent1=new Intent(ctx,SecondActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(
              ctx,
              12,
              intent1,
              PendingIntent.FLAG_CANCEL_CURRENT
            );

            NotificationCompat.Builder builder =new NotificationCompat.Builder(ctx);
            Notification notification1=builder.setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(from)
                    .setContentText(notification)
                    .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.ic_launcher))
                    .build();
            notification1.flags |=Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager=(NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(12,notification1);

        }



}
