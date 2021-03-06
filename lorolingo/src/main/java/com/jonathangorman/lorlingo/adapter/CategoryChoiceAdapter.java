package com.jonathangorman.lorlingo.adapter;

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

import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.activity.CardScreenActivity;
import com.jonathangorman.lorlingo.domain.CategoryItem;

import java.util.ArrayList;

// adapts individual items to main container layout
public class CategoryChoiceAdapter extends RecyclerView.Adapter<CategoryChoiceAdapter.ViewHolder>{

    private static final String TAG = "CategoryChoiceAdapter";
    private ArrayList<CategoryItem> categoryItemList;
    private String languageChosen;
    Context context;

    public CategoryChoiceAdapter(Context context, String languageChosen, ArrayList<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
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
            toCardScreen.putExtra("CATEGORY", categoryItemList.get(getAdapterPosition()).getNameId()); // adapter position is used to locate chosen category
            toCardScreen.putExtra("LANGUAGE", languageChosen); // language choice is also added to intent
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

        //Log.d(TAG,"New item added at position: " + i);
        viewHolder.imageView.setImageResource(categoryItemList.get(i).getImageId());
        viewHolder.textView.setText(categoryItemList.get(i).getDisplayText());
        viewHolder.constraintLayout.setOnClickListener(viewHolder);
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }
}
