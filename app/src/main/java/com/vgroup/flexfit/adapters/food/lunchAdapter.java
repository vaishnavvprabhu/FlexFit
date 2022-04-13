package com.vgroup.flexfit.adapters.food;

import android.content.Intent;
import android.graphics.Color;
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
import com.vgroup.flexfit.data.diet;

public class lunchAdapter extends FirebaseRecyclerAdapter<diet, lunchAdapter.dietViewholder> {

    //Author : VVP

    public lunchAdapter(
            @NonNull FirebaseRecyclerOptions<diet> options)
    {
        super(options);
    }

    //Function to bind the view in Card View (diet.xml) with data in the model class - diet.class
    @Override
    protected void onBindViewHolder(@NonNull lunchAdapter.dietViewholder holder, int position, @NonNull diet model)
    {
        //Data Entry
        holder.dtname.setText(model.getName());
        int cal = Integer.parseInt(String.valueOf(model.getCalories()));
        String ex_cal_long_to_string = String.valueOf(cal);
        holder.dtcal.setText(ex_cal_long_to_string);
        holder.dtdiff.setText(model.getDifficulty());

       String dttypeclr = model.getDiet();
        if (dttypeclr != null)
        {
            if (dttypeclr.startsWith("V"))
            {
                //Set food type badge based on nonveg or veg
                 holder.dttypeclr.setBackgroundColor(Color.parseColor("#008000"));
                 holder.dttype.setText("     VEG     ");
            }
            else if (dttypeclr.startsWith("N"))
            {
                holder.dttypeclr.setBackgroundColor(Color.parseColor("#D40000"));
                holder.dttype.setText("NON VEG");
            }
        }
        else
        {
            holder.dttypeclr.setVisibility(View.VISIBLE);
            holder.dttype.setVisibility(View.VISIBLE);
        }



        String eximgview = String.valueOf(model.getImage());
        //Picasso loads image into recycler from firebase link
        //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
        //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
        Picasso.get().load(eximgview).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), activity_recipes.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("position",holder.getBindingAdapterPosition());
                intent.putExtra("type","lunch");
                System.out.println(holder.getBindingAdapterPosition());
                v.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public lunchAdapter.dietViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet, parent, false);
        return new lunchAdapter.dietViewholder(view);
    }

    //Sub Class for Reference
    class dietViewholder extends RecyclerView.ViewHolder{
        TextView dtname, dtcal, dtdiff, dttype;
        ImageView image;
        View dttypeclr;

        public dietViewholder(@NonNull View itemView) {
            super(itemView);

            dtname = (TextView) itemView.findViewById(R.id.fd_name);
            //for marquee to work
            dtname.setSelected(true);
            dtcal = (TextView) itemView.findViewById(R.id.fd_fc);
            dtdiff = (TextView) itemView.findViewById(R.id.fd_df);
            dttype = (TextView) itemView.findViewById(R.id.fd_type);
            dttypeclr = (View) itemView.findViewById(R.id.meal_badge_clr);
            image = (ImageView) itemView.findViewById(R.id.fd_imageview);

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
