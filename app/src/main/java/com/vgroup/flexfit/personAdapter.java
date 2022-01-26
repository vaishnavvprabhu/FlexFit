package com.vgroup.flexfit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class personAdapter extends FirebaseRecyclerAdapter<exercises, personAdapter.personsViewholder> {

    public personAdapter(
            @NonNull FirebaseRecyclerOptions<exercises> options)
    {
        super(options);
    }

    //Function to bind the view in Card View (exercise.xml) with data in the model class - exercises.class
    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull exercises model)
    {
        //Data Entry
        holder.exname.setText(model.getName());
        String ex_tt_long_to_string = String.valueOf(model.getTime_taken());
        holder.extt.setText(ex_tt_long_to_string);
        holder.eximage.setText(model.getImage());
    }

    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercises, parent, false);
        return new personAdapter.personsViewholder(view);
    }

    //Sub Class for Reference
    class personsViewholder extends RecyclerView.ViewHolder{
        TextView exname, extt, eximage;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);

            exname = (TextView) itemView.findViewById(R.id.ex_name);
            extt = (TextView) itemView.findViewById(R.id.ex_tt);
            eximage = (TextView) itemView.findViewById(R.id.ex_img);
        }
    }
}
