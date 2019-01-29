package com.jonathangorman.flipper.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.jonathangorman.flipper.primary.CategoryChoiceActivity;

import java.util.ArrayList;

// adapts individual items to main container layout
public class LanguageChoiceAdapter extends RecyclerView.Adapter<LanguageChoiceAdapter.ViewHolder>{

    private static final String TAG = LanguageChoiceAdapter.class.getName();
    private Context context;
    private ArrayList<Integer> languageImageList;
    private ArrayList<String> languageDisplayTextList;
    ArrayList<String> languageNameList;

    public LanguageChoiceAdapter(Context context, ArrayList<Integer> imagesList, ArrayList<String> displayTextList,  ArrayList<String> languageNameList) {
        this.context = context;
        this.languageImageList = imagesList;
        this.languageDisplayTextList = displayTextList;
        this.languageNameList = languageNameList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        // holds widgets in memory
        public ViewHolder(View itemView) {
            super(itemView);

            // TODO Disable certain cards that are unavailable on the TTS engine
            //itemView.findViewById(R.id.language_card_view).setClickable(true);
            // itemView.findViewById(R.id.language_card_view).setAlpha(.5f);

            imageView = itemView.findViewById(R.id.category_imageView);
            textView = itemView.findViewById(R.id.category_textView);
            constraintLayout = itemView.findViewById(R.id.category_parent_layout);
        }

        // On click listener that provides the next activity with the language chosen in the form of an intent
        @Override
        public void onClick(View v) {
            Intent toCategoryChoice = new Intent(context, CategoryChoiceActivity.class);
            toCategoryChoice.putExtra("LANGUAGE", languageNameList.get(getAdapterPosition())); // adapter position is removed from the list and added to intent
            context.startActivity(toCategoryChoice);
            Log.d(TAG, "Language choice click: " + languageNameList.get(getAdapterPosition()));
        }
    }

    // Responsible for inflating the view
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Sets the holder values
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Log.d(TAG,"New item added at position: " + i);
        viewHolder.textView.setText(languageDisplayTextList.get(i));
        viewHolder.imageView.setImageResource(languageImageList.get(i));
        viewHolder.constraintLayout.setOnClickListener(viewHolder);
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return languageDisplayTextList.size();
    }
}