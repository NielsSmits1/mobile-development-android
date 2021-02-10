package com.example.myfirstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    public List<Pokemon> pokemon;
    public PokemonAdapter(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.pokemon_item, parent, false);

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = this.pokemon.get(position);
       holder.getTextView().setText(pokemon.name);
       holder.getImageView().setImageResource(pokemon.image_id);
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }
}
