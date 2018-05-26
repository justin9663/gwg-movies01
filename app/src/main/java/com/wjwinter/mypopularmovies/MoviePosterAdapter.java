package com.wjwinter.mypopularmovies;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviePosterAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private ArrayList<String> images;
    private int screenWidth;

    //Constructor
    public MoviePosterAdapter(Activity activity, ArrayList<String> images){
        this.activity = activity;
        this.images = images;
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }


    private AdapterView.OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(MoviePosterViewHolder itemHolder) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    public class MoviePosterViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        public MoviePosterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemHolderClick(this);
        }
    }

    public MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.poster_images, parent, false);
        Holder dataObjectHolder = new Holder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder mHolder = (Holder) holder;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Picasso.with(activity)
                .load(images.get(position))
                .resize(screenWidth / 2, 300)
                .centerCrop()
                .into(mHolder.images);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class Holder extends MoviePosterViewHolder {
        private ImageView images;
        public Holder(View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.poster_iv);
        }
    }

}
