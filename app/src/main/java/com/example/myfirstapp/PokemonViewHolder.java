package com.example.myfirstapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;
    private final ImageView imageView;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.textView);
        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }

    public TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
