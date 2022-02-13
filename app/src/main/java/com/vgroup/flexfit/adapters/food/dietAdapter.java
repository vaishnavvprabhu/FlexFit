package com.vgroup.flexfit.adapters.food;

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
import com.vgroup.flexfit.data.diet;

public class dietAdapter extends FirebaseRecyclerAdapter<diet, dietAdapter.dietViewholder> {

    //Author : VVP

    public dietAdapter(
            @NonNull FirebaseRecyclerOptions<diet> options)
    {
        super(options);
    }

    //Function to bind the view in Card View (diet.xml) with data in the model class - diet.class
    @Override
    protected void onBindViewHolder(@NonNull dietAdapter.dietViewholder holder, int position, @NonNull diet model)
    {
        //Data Entry
        holder.dtname.setText(model.getName());
        int cal = Integer.parseInt(String.valueOf(model.getCalories()));
        String ex_cal_long_to_string = String.valueOf(cal);
        holder.dtcal.setText(ex_cal_long_to_string);
        holder.dtdiff.setText(model.getDifficulty());
        String eximgview = String.valueOf(model.getImage());
        //Picasso loads image into recycler from firebase link
        //Fix For Scroll Lag was adding .fit().centerCrop() to picasso
        //https://stackoverflow.com/questions/33288436/recyclerview-laggy-scrolling
        Picasso.get().load(eximgview).into(holder.image);

    }

    @NonNull
    @Override
    public dietAdapter.dietViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet, parent, false);
        return new dietAdapter.dietViewholder(view);
    }

    //Sub Class for Reference
    class dietViewholder extends RecyclerView.ViewHolder{
        TextView dtname, dtcal, dtdiff;
        ImageView image, dttype;

        public dietViewholder(@NonNull View itemView) {
            super(itemView);

            dtname = (TextView) itemView.findViewById(R.id.fd_name);
            dtcal = (TextView) itemView.findViewById(R.id.fd_fc);
            dtdiff = (TextView) itemView.findViewById(R.id.fd_df);
            dttype = (ImageView) itemView.findViewById(R.id.fd_type);
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
