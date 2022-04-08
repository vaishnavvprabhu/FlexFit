package com.vgroup.flexfit.adapters;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;
import com.vgroup.flexfit.R;
import com.vgroup.flexfit.activities.ExerciseDescActivity;
import com.vgroup.flexfit.activities.HomeActivity;
import com.vgroup.flexfit.activities.InfoPage;
import com.vgroup.flexfit.activities.WorkoutActivity;
import com.vgroup.flexfit.data.exercises;

import java.lang.reflect.Array;

public class exerciseAdapter extends FirebaseRecyclerAdapter<exercises, exerciseAdapter.exercisesViewholder> {

    //Author : VVP

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
        double ex_tt_to_num = Double.parseDouble(String.valueOf(model.getTime_taken()));
        ex_tt_to_num = ex_tt_to_num/60;
        String ex_tt_long_to_string = String.valueOf(ex_tt_to_num);
        holder.extt.setText(ex_tt_long_to_string+" Mins");
        holder.exdesc.setText(model.getDesc());
        String eximgview = String.valueOf(model.getImage());

        //Picasso loads image into recycler from firebase link
        //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
        //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
        Picasso.get().load(eximgview).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //https://stackoverflow.com/questions/28528009/start-new-intent-from-recyclerviewadapter
                Intent intent = new Intent(v.getContext(), ExerciseDescActivity.class);

                //intent.putExtra("exercise",model.getName());

                System.out.println(holder.getBindingAdapterPosition());
                v.getContext().startActivity(intent);
            }
        });

        /*
        public void onClick(View view) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(HomeActivity.this, InfoPage.class);

            startActivity(intent);
        }
    }*/
    }

    @NonNull
    @Override
    public exercisesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercises, parent, false);
        return new exerciseAdapter.exercisesViewholder(view);
    }

    //Sub Class for Reference
    class exercisesViewholder extends RecyclerView.ViewHolder{
        TextView exname, extt, exdesc;
        ImageView image;

        public exercisesViewholder(@NonNull View itemView) {
            super(itemView);

            exname = (TextView) itemView.findViewById(R.id.ex_name);
            extt = (TextView) itemView.findViewById(R.id.ex_tt);
            exdesc = (TextView) itemView.findViewById(R.id.ex_img);
            image = (ImageView) itemView.findViewById(R.id.ex_imageview);

            //Set Fonts for various TextViews
            //Typeface boldTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "res/font/nunitosans_semibold.ttf"); Used Earlier but due to Font not found under "java.lang.RuntimeException: Font asset not found res/font/nunitosans_regular.ttf"
            //We used stuff as per https://stackoverflow.com/questions/33888127/android-runtime-exception-font-asset-not-found

/*          Typeface regularTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "res/font/nunitosans_regular.ttf");
            Typeface boldTypeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "res/font/nunitosans_semibold.ttf");
            this.exname.setTypeface(boldTypeface);
            this.extt.setTypeface(regularTypeface);
            this.eximage.setTypeface(regularTypeface);*/

        }
    }
}