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
import com.jonathangorman.flipper.screen.CardScreenActivity;
import com.jonathangorman.flipper.screen.CategoryChoiceActivity;

import java.util.ArrayList;

// adapts individual items to main container layout
public class LanguageChoiceAdapter extends RecyclerView.Adapter<LanguageChoiceAdapter.ViewHolder>{

    private static final String TAG = "LanguageChoiceAdapter";
    private Context context;
    private ArrayList<Integer> languageImageList;
    private ArrayList<String> languageTextList;

    public LanguageChoiceAdapter(Context context, ArrayList<Integer> imagesList, ArrayList<String> textList) {
        this.context = context;
        this.languageImageList = imagesList;
        this.languageTextList = textList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        // holds widgets in memory
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.language_image_view);
            textView = itemView.findViewById(R.id.language_text_view);
            constraintLayout = itemView.findViewById(R.id.language_constraint_layout);
        }
    }

    // Responsible for inflating the view
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.language_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Sets the holder values
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG,"New item added at position: " + i);
        viewHolder.textView.setText(languageTextList.get(i));
        viewHolder.imageView.setImageResource(languageImageList.get(i));

        viewHolder.constraintLayout.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent toCategoryChoice = new Intent(context, CategoryChoiceActivity.class);
                toCategoryChoice.putExtra("LANGUAGE","english");
                context.startActivity(toCategoryChoice);
            }
        });
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return languageTextList.size();
    }
}
