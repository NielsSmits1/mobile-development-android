package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OverviewFragment.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        boolean portrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if(portrait){
            manager.beginTransaction().add(R.id.container, new OverviewFragment(), "overview").commit();
        }
    }


    @Override
    public void onItemSelected(String pokemon) {
        boolean portrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        if(portrait){
            FragmentManager manager = getSupportFragmentManager();

            DetailFragment fragment = new DetailFragment();

            fragment.setArguments(new Bundle());
            fragment.getArguments().putString("pokemon", pokemon);

            manager.beginTransaction().replace(R.id.container, fragment, "detail").addToBackStack(null).commit();
        }else{
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);

            detailFragment.setPokemon(pokemon);
        }

    }
}
