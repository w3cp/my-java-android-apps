package com.anwar.rafiqul;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NotificationHandler {
    // Notification handler singleton
    private static NotificationHandler nHandler;
    private static NotificationManager mNotificationManager;


    private NotificationHandler () {}


    /**
     * Singleton pattern implementation
     * @return
     */
    public static  NotificationHandler getInstance(Context context) {
        if(nHandler == null) {
            nHandler = new NotificationHandler();
            mNotificationManager =
                (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return nHandler;
    }


    /**
     * Shows a simple notification
     * @param context aplication context
     */
    public void createSimpleNotification(Context context) {
        // Creates an explicit intent for an Activity
        Intent resultIntent = new Intent(context, ExitCamera.class);

        // Creating a artifical activity stack for the notification activity
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ExitCamera.class);
        stackBuilder.addNextIntent(resultIntent);

        // Pending intent to the notification manager
        PendingIntent resultPending = stackBuilder
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Building the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_launcher) // notification icon
            .setContentTitle(context.getResources().getString(R.string.camera_running_title)) // main title of the notification
            .setContentText(context.getResources().getString(R.string.camera_running_text)) // notification text
            .setOngoing(true) // persistent notification
            .setContentIntent(resultPending); // notification intent

        // mId allows you to update the notification later on.
        mNotificationManager.notify(10, mBuilder.build());
    } // end method createSimpleNotification
    
    public void createExpandableNotification (Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Building the expandable content
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            String text = context.getResources().getString(R.string.camera_running_text);
            String [] content = text.split("\\.");

            inboxStyle.setBigContentTitle(context.getResources().getString(R.string.camera_running_title));
            for (String line : content) {
                inboxStyle.addLine(line);
            }

            // Building the notification
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher) // notification icon
                .setContentTitle(context.getResources().getString(R.string.camera_running_title)) // title of notification
                .setContentText(context.getResources().getString(R.string.camera_running_text)) // text inside the notification
                .setOngoing(true) // persistent notification
                .setStyle(inboxStyle); // adds the expandable content to the notification

            mNotificationManager.notify(11, nBuilder.build());

        } else {
            Toast.makeText(context, "Can't show Expandable Notification", Toast.LENGTH_LONG).show();
            createSimpleNotification(context);
        }
    } // end method createExpandableNotification
    
    public void closeAll() {
        mNotificationManager.cancelAll();
    }
} // end class NotificationHandler
