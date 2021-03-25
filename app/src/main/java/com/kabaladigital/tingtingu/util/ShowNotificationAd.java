package com.kabaladigital.tingtingu.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.service.NotificationReceiver;
import com.kabaladigital.tingtingu.ui.activity.LoginActivity;

public class ShowNotificationAd {

    private static final String NOTIFICATION_CHANNEL_ID = "channel_id";
    private static final int NOTIFICATION_ID = 888;
    private static final String CHANNEL_NAME = "Notification Name";
    private static final String CHANNEL_DESC = "Notification Channel";

    private static final String NOTIFICATION_CHANNEL_ID_WITHOUT_CALL = "channel_id";
    private static final int NOTIFICATION_ID_WITHOUT_CALL = 000;
    private static final String CHANNEL_NAME_WITHOUT_CALL = "Notification Name";
    private static final String CHANNEL_DESC_WITHOUT_CALL = "Notification Channel";

    private static final String NOTIFICATION_CHANNEL_ID_VIDEO_URL = "channel_id_video_url";
    private static final int NOTIFICATION_ID_VIDEO_URL = 111;
    private static final String CHANNEL_NAME_VIDEO_URL = "Notification Name_video_url";
    private static final String CHANNEL_DESC_VIDEO_URL = "Notification Channel_video_url";

    private static final String NOTIFICATION_CHANNEL_ID_IMAGE_URL = "channel_id_image_url";
    private static final int NOTIFICATION_ID_IMAGE_URL = 222;
    private static final String CHANNEL_NAME_IMAGE_URL = "Notification Name_image_url";
    private static final String CHANNEL_DESC_IMAGE_URL = "Notification Channel_image_url";


    public static void createNotification(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                              CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

          //Custum Notification
            RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.custom_push_notification);

            RemoteViews contentView2 = new RemoteViews(context.getPackageName(), R.layout.custom_push_notification);
            //contentView.setImageViewResource(R.id.image_app_notification, R.mipmap.ic_launcher);
            contentView.setImageViewResource(R.id.image_bigimage_notification,R.drawable.tingtingu_logo);
            contentView.setTextViewText(R.id.tv_notification_title, "Ting Ting U Custom notification");
            contentView.setTextViewText(R.id.tv_subtitle_notification, "This is a custom layout This is a custom layout ");

            //contentView2.setImageViewResource(R.id.image_app_notification, R.mipmap.ic_launcher);
            contentView2.setImageViewResource(R.id.image_bigimage_notification,R.drawable.tingtingu_logo);
            contentView2.setTextViewText(R.id.tv_notification_title, "Ting Ting U Custom notification");
            contentView2.setTextViewText(R.id.tv_subtitle_notification, "This is a custom layout This is a custom layout ");

            NotificationCompat.Builder builder=
                    new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
                            .setSmallIcon(R.drawable.tingtingu_logo)
                            .setCustomContentView(contentView)
                            .setCustomBigContentView(contentView2)
                            .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
//                            .setContentTitle("Ting Ting U Notification")
//                            .setContentText("Much longer text that cannot fit one line ")
                            .setChannelId(NOTIFICATION_CHANNEL_ID)
//                            .setStyle(new NotificationCompat.BigPictureStyle()
//                                    .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.tingtingu_logo))
//                                    .bigLargeIcon(null))
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
        }
    }


    public static void createImageWithoutCallNotification(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID_WITHOUT_CALL,
                            CHANNEL_NAME_WITHOUT_CALL, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC_WITHOUT_CALL);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

            Notification notification =
                    new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID_WITHOUT_CALL)
                            .setSmallIcon(R.drawable.tingtingu_logo)
                            .setChannelId(NOTIFICATION_CHANNEL_ID_WITHOUT_CALL)
                            .setContentTitle("Simple Image without call to action")
                            .setContentText("This Ad only display in Notification panel as small image which shows as a bigger image when nitification is expended...")
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.news_image))
                            .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.news_image)))
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(NOTIFICATION_ID_WITHOUT_CALL,notification);
        }

    }


    public static void createImageWithCallNotification(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                            CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

            Intent snoozeIntent = new Intent(context, LoginActivity.class);
            snoozeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent snoozePendingIntent =
                    PendingIntent.getActivity(context, 0, snoozeIntent, 0);

            Notification notification =
                    new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                            .setSmallIcon(R.drawable.tingtingu_logo)
                            .setChannelId(NOTIFICATION_CHANNEL_ID)
                            .setContentTitle("News 24 (Breaking News)")
                            .setContentText("Pakistan tried to attack Indian soldier's bases...")
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.tinglogo1))
                            .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.news_image)))
                             // .bigLargeIcon(null))
                            .setContentIntent(snoozePendingIntent)
                            .addAction(R.drawable.ic_arrow_blue_24dp, "View", snoozePendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(NOTIFICATION_ID, notification);
        }

    }


    public static void createImageWithURLNotification(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID_IMAGE_URL,
                            CHANNEL_NAME_IMAGE_URL, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC_IMAGE_URL);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

            Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rukminim1.flixcart.com/image/416/416/j3xbzww0/dish-cleaning-gel/j/j/j/lime-500-active-bottle-vim-original-imaeuyy6ssa27vax.jpeg?q=70"));
            PendingIntent pendingIntentUrl =
                    PendingIntent.getActivity(context, 0, intentUrl, 0);

            Intent intentBtn = new Intent(context, NotificationReceiver.class);
            intentBtn.putExtra("notificationId", NOTIFICATION_ID_IMAGE_URL);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentBtn,0);

            Notification notification =
                    new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID_IMAGE_URL)
                            .setSmallIcon(R.drawable.tingtingu_logo)
                            .setChannelId(NOTIFICATION_CHANNEL_ID_IMAGE_URL)
                            .setContentTitle("Vim Dishwash Liquid, 500 ml")
                            .setContentText("Vim is a quality product from Hindustan Unilever Ltd, the makers of Surf Excel, Lakme, Red Label, Ponds & other such well-known brands.")
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.tinglogo1))
                            .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_vim_dishwash_)))
                            // .bigLargeIcon(null))
                           // .setContentIntent(snoozePendingIntent)
                            .addAction(R.drawable.ic_arrow_blue_24dp, "View", pendingIntentUrl)
                            .addAction(R.drawable.ic_clear_black_24dp,"Dismiss",pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(NOTIFICATION_ID_IMAGE_URL, notification);
        }

    }

    public static void createVideoWithURLNotification(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID_VIDEO_URL,
                            CHANNEL_NAME_VIDEO_URL, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(CHANNEL_DESC_VIDEO_URL);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

            Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gA3siZFEcG4"));
            PendingIntent pendingIntentUrl =
                    PendingIntent.getActivity(context, 0, intentUrl, 0);

            Intent intentBtn = new Intent(context, NotificationReceiver.class);
            intentBtn.putExtra("notificationId", NOTIFICATION_ID_VIDEO_URL);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentBtn,0);

            Notification notification =
                    new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID_VIDEO_URL)
                            .setSmallIcon(R.drawable.tingtingu_logo)
                            .setChannelId(NOTIFICATION_CHANNEL_ID_VIDEO_URL)
                            .setContentTitle("Reynolds Racer Gel Pen")
                            .setContentText("Protect your writing with this pack of 20 reynolds racer gel sporty pens. The waterproof gel ink keeps important work safe from spills and is also resistant to fading for extra protection.")
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.tinglogo1))
                            .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.reynold_pixsle_1)))
                            // .bigLargeIcon(null))
                            // .setContentIntent(snoozePendingIntent)
                            .addAction(R.drawable.ic_arrow_blue_24dp, "View", pendingIntentUrl)
                            .addAction(R.drawable.ic_clear_black_24dp,"Dismiss",pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .build();

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(NOTIFICATION_ID_VIDEO_URL, notification);
        }

    }


}

//    https://www.journaldev.com/15468/android-notification-styling

