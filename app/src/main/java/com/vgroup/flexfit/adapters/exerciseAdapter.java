package com.vgroup.flexfit.adapters;

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
import com.vgroup.flexfit.data.exercises;

public class exerciseAdapter extends FirebaseRecyclerAdapter<exercises, exerciseAdapter.exercisesViewholder> {

    public exerciseAdapter(
            @NonNull FirebaseRecyclerOptions<exercises> options)
    {
        super(options);
    }

    //Function to bind the view in Card View (exercise.xml) with data in the model class - exercises.class
    @Override
    protected void onBindViewHolder(@NonNull exercisesViewholder holder, int position, @NonNull exercises model)
    {
        //Data Entry
        holder.exname.setText(model.getName());
        String ex_tt_long_to_string = String.valueOf(model.getTime_taken());
        holder.extt.setText(ex_tt_long_to_string);
        holder.eximage.setText(model.getImage());
        String eximgview = String.valueOf(model.getImage());

        //Picasso loads image into recycler from firebase link
        //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
        //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
        Picasso.get().load(eximgview).into(holder.image);
    }

    @NonNull
    @Override
    public exercisesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercises, parent, false);
        return new exerciseAdapter.exercisesViewholder(view);
    }

    //Sub Class for Reference
    class exercisesViewholder extends RecyclerView.ViewHolder{
        TextView exname, extt, eximage;
        ImageView image;

        public exercisesViewholder(@NonNull View itemView) {
            super(itemView);

            exname = (TextView) itemView.findViewById(R.id.ex_name);
            extt = (TextView) itemView.findViewById(R.id.ex_tt);
            eximage = (TextView) itemView.findViewById(R.id.ex_img);
            image = (ImageView) itemView.findViewById(R.id.ex_imageview);
        }
    }
}