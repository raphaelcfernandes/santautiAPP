package santauti.app.Animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class MyAnimation {

    public void slideDownLinearLayout(Context ctx, final LinearLayout linearLayout) {
        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if(a!=null) {
            a.reset();
            linearLayout.clearAnimation();
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    linearLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    linearLayout.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            linearLayout.startAnimation(a);
        }
    }

    public void slideUpLinearLayout(Context ctx, final LinearLayout linearLayout) {
        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if(a!=null) {
            a.reset();
            linearLayout.clearAnimation();
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    linearLayout.setVisibility(View.GONE);
                    linearLayout.clearAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            linearLayout.startAnimation(a);
        }
    }

    public int rotateImageView180(ImageView v, int myRotationAngle){
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation",myRotationAngle, myRotationAngle + 180);
        anim.setDuration(500);
        anim.start();
        myRotationAngle += 180;
        myRotationAngle = myRotationAngle%360;
        return myRotationAngle;
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
