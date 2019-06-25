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
import android.widget.Toast;

import com.jonathangorman.lorlingo.R;
import com.jonathangorman.lorlingo.com.jonathangorman.lorlingo.domain.LanguageItem;
import com.jonathangorman.lorlingo.activity.CategoryChoiceActivity;
import com.jonathangorman.lorlingo.activity.LanguageChoiceActivity;

import java.util.ArrayList;
import java.util.Locale;

// adapts individual items to main container layout
public class LanguageChoiceAdapter extends RecyclerView.Adapter<LanguageChoiceAdapter.ViewHolder>{

    private static final String TAG = LanguageChoiceAdapter.class.getName();
    private Context context;
    private ArrayList<LanguageItem> languageItemList;

    public LanguageChoiceAdapter(Context context, ArrayList<LanguageItem> languageItemList) {
        this.context = context;
        this.languageItemList = languageItemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;

        // holds widgets in memory
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_imageView);
            textView = itemView.findViewById(R.id.category_textView);
            constraintLayout = itemView.findViewById(R.id.category_parent_layout);
        }

        // On click listener that provides the next activity with the language chosen in the form of an intent
        @Override
        public void onClick(View v) {
            // check that the TTS engine is initialised and that the language is available
            if (readyForTTS(languageItemList.get(getAdapterPosition()).getLocale()) == true)
            {
                Intent toCategoryChoice = new Intent(context, CategoryChoiceActivity.class);
                toCategoryChoice.putExtra("LANGUAGE", languageItemList.get(getAdapterPosition()).getNameId()); // adapter position is removed from the list and added to intent
                context.startActivity(toCategoryChoice);
                //Log.d(TAG, "Language choice click: " + languageItemList.get(getAdapterPosition()).getNameId());
            }
            //Log.d(TAG, "Not ready for TTS for language " + languageItemList.get(getAdapterPosition()).getNameId());
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

        //Log.d(TAG,"New item added at position: " + i);

        viewHolder.textView.setText(languageItemList.get(i).getDisplayText());
        viewHolder.imageView.setImageResource(languageItemList.get(i).getImageId());
        viewHolder.constraintLayout.setOnClickListener(viewHolder);
    }

    // Prompt TTS install intent from main activity
    public boolean readyForTTS(Locale locale) {

        // first, checks TTS engine is ready
        if (((LanguageChoiceActivity) context).getTtsManager().isTtsInitialised() == false)
        {
            Toast.makeText(context, R.string.tts_not_initialised_msg, Toast.LENGTH_SHORT);
            return false;
        }
        // second, checks that the voice data is installed. It automatically
        if (((LanguageChoiceActivity) context).getTtsManager().checkVoiceDataAvailable(locale) == false)
        {
            Toast.makeText(context,  R.string.tts_voice_data_installing_msg, Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    // Returns the number of items
    @Override
    public int getItemCount() {
        return languageItemList.size();
    }
}