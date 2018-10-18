package com.jonathangorman.flipper.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonathangorman.flipper.R;
import com.jonathangorman.flipper.cards.Card;

import java.util.ArrayList;
import java.util.List;

// adapts individual items to main container layout
public class CardChoiceAdapter extends RecyclerView.Adapter<CardChoiceAdapter.ViewHolder>{

    private static final String TAG = "CardChoiceAdapter";
    Context context;
    ArrayList<String> cardList;

    public CardChoiceAdapter(ArrayList<String> cardList, Context context) {
        this.context = context;
        this.cardList = cardList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        ConstraintLayout constraintLayout;

        // holds widgets in memory
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_image);
            constraintLayout = itemView.findViewById(R.id.card_parent_layout);
        }
    }

    // Responsible for inflating the view
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Sets the holder values
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG,"New item added at position: " + i);
        viewHolder.imageView.setImageResource(Integer.valueOf(cardList.get(i)));
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
