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
import com.jonathangorman.flipper.primary.CardScreenActivity;

import java.util.ArrayList;

// adapts individual items to main container layout
public class CategoryChoiceAdapter extends RecyclerView.Adapter<CategoryChoiceAdapter.ViewHolder>{

    private static final String TAG = "CategoryChoiceAdapter";
    private ArrayList<Integer> categoryImagesList;
    private ArrayList<String> categoryTextList;
    private ArrayList<String> categoryNameList;
    private String languageChosen;
    Context context;

    public CategoryChoiceAdapter(Context context, String languageChosen, ArrayList<String> nameList, ArrayList<String> textList, ArrayList<Integer> imagesList) {
        this.categoryImagesList = imagesList;
        this.categoryTextList = textList;
        this.categoryNameList = nameList;
        this.languageChosen = languageChosen;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        // holds widgets in memory
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_imageView);
            constraintLayout = itemView.findViewById(R.id.category_parent_layout);
            textView = itemView.findViewById(R.id.category_textView);
        }

        @Override
        public void onClick(View v) {
            Intent toCardScreen = new Intent(context, CardScreenActivity.class);
            toCardScreen.putExtra("CATEGORY", categoryNameList.get(getAdapterPosition())); // adapter position is removed from the list and added to intent
            toCardScreen.putExtra("LANGUAGE", languageChosen); // add language
            context.startActivity(toCardScreen);
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG,"New item added at position: " + i);
        viewHolder.imageView.setImageResource(categoryImagesList.get(i));
        viewHolder.textView.setText(categoryTextList.get(i));
        viewHolder.constraintLayout.setOnClickListener(viewHolder);
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return categoryTextList.size();
    }
}
