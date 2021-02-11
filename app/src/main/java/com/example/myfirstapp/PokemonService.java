package com.example.myfirstapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.Random;

public class PokemonService extends Service {
    int favorite;
    int POKEMON_NOTIFICATION = 123456;
    static String  POKEMON_BROADCAST = "POKEMON_BROADCAST";
    public PokemonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
              favorite = (favorite + 1 + new Random().nextInt(2)) % 3;

              sendNotification();

              sendBroadcast();

              int FIVE_SECONDS = 5000;
              handler.postDelayed(this, FIVE_SECONDS);
            }
        });
        return START_STICKY;
    }

    void sendNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(manager != null){
            manager.notify(POKEMON_NOTIFICATION, getNotification());
        }
    }

    void sendBroadcast() {
        Intent intent = new Intent();

        intent.putExtra("favorite", favorite);
        intent.setAction(POKEMON_BROADCAST);

        sendBroadcast(intent);
    }

    void updateWidget() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(manager != null){
            manager.notify(POKEMON_NOTIFICATION, getNotification());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        startForeground(POKEMON_NOTIFICATION, getNotification());
    }

    private Notification getNotification(){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), getIcon(favorite));
        return new NotificationCompat
                .Builder(this, MainActivity.POKEMON_CHANNEL)
                .setContentTitle("Pokemon")
                .setContentText("Favorite: " + getName(favorite))
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(icon)
                .build();
    }

    static int getIcon(int favorite){
        int[] icons = {R.drawable.bulbasaur, R.drawable.dragonite, R.drawable.pikachu};

        return icons[favorite];
    }

    static String getName(int favorite){
        String[] names = {"bulbasaur", "dragonite", "pikachu"};

        return names[favorite];
    }
}