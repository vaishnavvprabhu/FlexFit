package com.vgroup.flexfit.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.activities.activity_recipes;
import com.vgroup.flexfit.activities.activity_webview;
import com.vgroup.flexfit.data.blogs;

public class homeBlogsAdapter extends FirebaseRecyclerAdapter<blogs, homeBlogsAdapter.blogsViewholder> {

    //Author : VVP

    public homeBlogsAdapter(
            @NonNull FirebaseRecyclerOptions<blogs> options)
    {
        super(options);
    }

    //Function to bind the view in Card View (blogs.xml) with data in the model class - blogs.class
    @Override
    protected void onBindViewHolder(@NonNull homeBlogsAdapter.blogsViewholder holder, int position, @NonNull blogs model)
    {
        //Data Entry
        holder.dtname.setText(model.getName());

        String eximgview = String.valueOf(model.getImage());
        //Picasso loads image into recycler from firebase link
        //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
        //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
        Picasso.get().load(eximgview).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(v.getContext(), activity_recipes.class);*/
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                /*startActivity(browserIntent);*/
                /*                intent.putExtra("name", model.getName());
                intent.putExtra("position",holder.getBindingAdapterPosition());*/
                System.out.println(holder.getBindingAdapterPosition());
                v.getContext().startActivity(browserIntent);
            }
        });

    }

    @NonNull
    @Override
    public homeBlogsAdapter.blogsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blogs, parent, false);
        return new homeBlogsAdapter.blogsViewholder(view);
    }

    //Sub Class for Reference
    class blogsViewholder extends RecyclerView.ViewHolder{
        TextView dtname , dtcal, dtdiff, dttype;
        ImageView image;
        View dttypeclr;

        public blogsViewholder(@NonNull View itemView) {
            super(itemView);

            dtname = (TextView) itemView.findViewById(R.id.blog_name);
            //for marquee to work
            dtname.setSelected(true);

            image = (ImageView) itemView.findViewById(R.id.blog_imageview);

            //Set Fonts for various TextViews
            //Typeface boldTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "res/font/nunitosans_semibold.ttf"); Used Earlier but due to Font not found under "java.lang.RuntimeException: Font asset not found res/font/nunitosans_regular.ttf"
            //We used stuff as per https://stackoverflow.com/questions/33888127/android-runtime-exception-font-asset-not-found

/*            Typeface regularTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "res/font/nunitosans_regular.ttf");
            Typeface boldTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "res/font/nunitosans_semibold.ttf");
            this.exname.setTypeface(boldTypeface);
            this.extt.setTypeface(regularTypeface);
            this.eximage.setTypeface(regularTypeface);*/
        }
    }
}
