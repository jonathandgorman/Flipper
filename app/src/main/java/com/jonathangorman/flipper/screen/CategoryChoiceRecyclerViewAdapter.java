package com.jonathangorman.flipper.screen;

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

import java.util.ArrayList;

// adapts individual items to main container layout
public class CategoryChoiceRecyclerViewAdapter extends RecyclerView.Adapter<CategoryChoiceRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "CategoryChoiceRecyclerV";
    ArrayList<String> imagesList;
    ArrayList<String> namesList;
    Context context;

    public CategoryChoiceRecyclerViewAdapter(ArrayList<String> imagesList, ArrayList<String> namesList, Context context) {
        this.imagesList = imagesList;
        this.namesList = namesList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        // holds widgits in memory
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_imageView1);
            constraintLayout = itemView.findViewById(R.id.category_parent_layout);
            textView = itemView.findViewById(R.id.category_textView1);
        }
    }

    // Responsible for inflating the view
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Sets the holder values
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Log.d(TAG,"New item added at position: " + i);
        viewHolder.imageView.setImageResource(Integer.valueOf(imagesList.get(i)));
        viewHolder.textView.setText(namesList.get(i));
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return namesList.size();
    }
}
