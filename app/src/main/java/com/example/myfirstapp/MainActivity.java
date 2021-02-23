package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPokemon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);

        startActivityForResult(intent, 1);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setPokemon();
    }

    void setPokemon()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean show = preferences.getBoolean("show", true);
        String name = preferences.getString("name", "");
        String pokemon = preferences.getString("pokemon", "");
        boolean bckgrnd = preferences.getBoolean("background", true);
        Set<String> flipping = preferences.getStringSet("flipping", Collections.<String>emptySet());
        int icon = getResources().getIdentifier(pokemon, "drawable", getPackageName());
        View view = findViewById(android.R.id.content);
        view.setAlpha(show ? (float) 1.0 : (float) 0.25);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(name);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(icon != 0 ? icon : R.mipmap.ic_launcher);
        imageView.setBackgroundResource(bckgrnd? android.R.color.darker_gray : android.R.color.transparent);
        imageView.setScaleX(flipping.contains("horizontal") ? -1 : 1);
        imageView.setScaleY(flipping.contains("vertical") ? -1 : 1);
    }
}
