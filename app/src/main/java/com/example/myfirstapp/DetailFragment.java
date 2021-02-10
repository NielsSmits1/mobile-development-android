package com.example.myfirstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    TextView textView;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        textView = view.findViewById(R.id.textView);
        imageView = view.findViewById(R.id.imageView);

        if(getArguments() != null){
            String pokemon = getArguments().getString("pokemon");
            setPokemon(pokemon);
        }

        return view;
    }

    public void setPokemon(String pokemon) {

        textView.setText(pokemon);
        switch (pokemon){
            case "Bulbasaur":
                imageView.setImageResource(R.drawable.bulbasaur);
                break;
            case "Dragonite":
                imageView.setImageResource(R.drawable.dragonite);
                break;
            case "Pikachu":
                imageView.setImageResource(R.drawable.pikachu);
                break;
        }
    }
}