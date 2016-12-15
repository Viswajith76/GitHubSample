package com.boxme.githubsample.githhubsample.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.boxme.githubsample.githhubsample.R;

/**
 * Created by vth02 on 15-Dec-16.
 */

public class AnimationUtil {

    // Animates view by scaling height of the view
    public static void scaleHeightAnimation(final View cardView, final View button) {
        cardView.setPivotY(cardView.getMeasuredHeight());
        cardView.setPivotX(cardView.getMeasuredWidth() / 2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(cardView, "scaleY", 0.5f, 1f);
        scaleY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                button.setBackground(ContextCompat.getDrawable(cardView.getContext(), R.drawable.curved_border_drawable_aqua));
                button.setVisibility(View.VISIBLE);
                scaleWidthAnimation(button);
            }
        });
        scaleY.start();
    }

    // Animates view by scaling width of the view
    public static void scaleWidthAnimation(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
        scaleX.start();
    }


}
