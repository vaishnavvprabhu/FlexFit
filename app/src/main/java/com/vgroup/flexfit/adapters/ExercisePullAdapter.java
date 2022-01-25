package com.vgroup.flexfit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vgroup.flexfit.R;
import com.vgroup.flexfit.data.FetchExercises;

import java.util.List;

public class ExercisePullAdapter extends RecyclerView.Adapter {

    List<FetchExercises> exercisesList;

    public ExercisePullAdapter(List<FetchExercises> exercisesList) {
        this.exercisesList = exercisesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_exercises, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        FetchExercises fetchExercises = exercisesList.get(position);
        viewHolderClass.name.setText(fetchExercises.getName());
        viewHolderClass.time_taken.setText(fetchExercises.getTime_taken());

        /*viewHolderClass.image.setImageDrawable(exercisesList.getImage());*/

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name, time_taken;
        ImageView image;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.exercise_name);
            time_taken=itemView.findViewById(R.id.exercise_tt);
            image=itemView.findViewById(R.id.exercise_img);
        }
    }
}
