package com.vgroup.flexfit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vgroup.flexfit.R;

;


public class ExerciseDescActivity extends AppCompatActivity{
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_desc);
        overridePendingTransition(R.anim.slide_up1, R.anim.slide_down1);
        btn = findViewById(R.id.registerBtn);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent intent = new Intent(ExerciseDescActivity.this, activity_exercise_timer.class);
                                       startActivity(intent);
                                   }
                               });
//        ActivityOptions options =
//                ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out);
//        context.startActivity(myIntent, options.toBundle());
    }
        @Override
        public void onBackPressed()
    {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_up1,
                    R.anim.slide_down1);
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.activity_exercise_desc, container, false);
//
//        toolbar = view.findViewById(R.id.toolbar);
//
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        toolbar.setNavigationOnClickListener(v -> dismiss());
//        toolbar.setTitle("Some Title");
//        toolbar.inflateMenu(R.menu.top_bar_menu);
//        toolbar.setOnMenuItemClickListener(item -> {
//            dismiss();
//            return true;
//        });
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            int width = ViewGroup.LayoutParams.MATCH_PARENT;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            dialog.getWindow().setLayout(width, height);
//            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
//        }
//    }
}
