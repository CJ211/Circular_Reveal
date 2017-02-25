package com.example.jay.circular_reveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mbutton;

    private TextView mtextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbutton = (Button) findViewById(R.id.b1);
        mtextview = (TextView) findViewById(R.id.tv);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(mbutton.getText().toString().equals("Click Me"))
                {
                    if (Build.VERSION.SDK_INT >= 21)
                    {
                        // get the center for the clipping circle
                        int cx = mtextview.getWidth() / 2;
                        int cy = mtextview.getHeight() / 2;

                        // get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

                        // create the animator for this view (the start radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(mtextview, cx, cy, 0, finalRadius);

                        // make the view visible and start the animation
                        mtextview.setVisibility(View.VISIBLE);
                        anim.start();
                        mbutton.setText("Hide");
                    }
                    else
                    {
                        mtextview.setVisibility(View.VISIBLE);
                        mbutton.setText("Hide");
                    }
                }

                else
                {
                    if (Build.VERSION.SDK_INT >= 21)
                    {
                        // get the center for the clipping circle
                        int cx = mtextview.getWidth() / 2;
                        int cy = mtextview.getHeight() / 2;

                        // get the initial radius for the clipping circle
                        float initialRadius = (float) Math.hypot(cx, cy);

                        // create the animation (the final radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(mtextview, cx, cy, initialRadius, 0);

                        // make the view invisible when the animation is done
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mtextview.setVisibility(View.INVISIBLE);
                                mbutton.setText("Click Me");
                            }
                        });
                        // start the animation
                        anim.start();

                    }

                    else
                    {
                        mtextview.setVisibility(View.VISIBLE);
                        mbutton.setText("Click Me");
                    }
                }

            }
        });

    }
}
