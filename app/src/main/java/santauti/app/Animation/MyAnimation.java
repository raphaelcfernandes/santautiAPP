package santauti.app.Animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class MyAnimation {
    private int rotationAngle = 0;

    public int getRotationAngle() {
        return rotationAngle;
    }

    public void rotateImageView180(ImageView v){
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation",rotationAngle, rotationAngle + 180);
        anim.setDuration(500);
        anim.start();
        rotationAngle += 180;
        rotationAngle = rotationAngle%360;
    }

    public void slide_down(Context ctx, final View v) {
        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.setVisibility(View.VISIBLE);
                v.startAnimation(a);
            }
        }
    }

    public void slide_up(Context ctx, final View v) {
        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if(a!=null) {
            a.reset();
            v.clearAnimation();
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            v.startAnimation(a);
        }
    }

    public void fade_in(View view){
        view.animate().alpha(1.0f);
    }

    public void fade_out(View view){
        view.animate().alpha(0.0f);
    }

    public void slideInLeft(Context context,View view){
        android.view.animation.Animation a = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(a);
    }

    public void slideOutRight(Context context,View view){
        android.view.animation.Animation a = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
        view.startAnimation(a);
    }

}
