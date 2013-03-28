package com.example.myandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;


/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-24
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public class MyActivity03  extends Activity {

    public  static  String TAG="MyActivity03";
    private ViewFlipper mViewFlipper;
    private float mOldTouchValue;

    @Override
    public  void onCreate(Bundle bundle){
            super.onCreate(bundle);
             getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                     WindowManager.LayoutParams.FLAG_FULLSCREEN);
             requestWindowFeature(Window.FEATURE_NO_TITLE);
             setContentView(R.layout.myactivity03);

             mViewFlipper = (ViewFlipper)findViewById(R.id.myViewFlipper);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

       // Log.v(TAG, "onTouchEvent");
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mOldTouchValue = event.getX();
                Log.v(TAG, Float.toString(mOldTouchValue) + "  ------++" );
                break;
            case MotionEvent.ACTION_UP :
                float  currentX = event.getX();
                Log.v(TAG, Float.toString(currentX) + "  ------" + Float.toString(mOldTouchValue));
                if (mOldTouchValue < currentX){
                    // set in 的启始动画事件
                    mViewFlipper.setInAnimation(
                            AnimationHelp.inFromLeftAnimation()
                    );
                    // set out
                    mViewFlipper.setOutAnimation(
                            AnimationHelp.outFromRightAnimation()
                    );
                    mViewFlipper.showNext();
                }
                else if (mOldTouchValue > currentX){

                    mViewFlipper.setInAnimation(AnimationHelp.inFromRightAnimation());

                    mViewFlipper.setOutAnimation(AnimationHelp.outFromLeftAnimation());
                    mViewFlipper.showNext();
                }
                break;

        }

        return super.onTouchEvent(event);
    }
}
