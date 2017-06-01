package santauti.app.Animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class MyAnimation {
    private static int rotationAngle = 0;
    public static void rotateImageView180(ImageView v){
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation",rotationAngle, rotationAngle + 180);
        anim.setDuration(500);
        anim.start();
        rotationAngle += 180;
        rotationAngle = rotationAngle%360;
    }
    public static void slide_down(Context ctx, View v) {
        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }
    public static void slide_up(Context ctx, View v) {
        android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }
}
