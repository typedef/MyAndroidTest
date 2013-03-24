package com.example.myandroidtest;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-24
 * Time: 下午10:36
 * To change this template use File | Settings | File Templates.
 */
public class AnimationHelp{

        // from right to left     in screen
        public static Animation inFromRightAnimation(){
            Animation inFromRight = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT,
                    +1.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f);

            inFromRight.setDuration(350);
            inFromRight.setInterpolator(new AccelerateInterpolator());
            return  inFromRight;
        }

        // from left to right   in screen
        public static Animation inFromLeftAnimation(){
            Animation inFromRight = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT,
                    -1.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f);

            inFromRight.setDuration(350);
            inFromRight.setInterpolator(new AccelerateInterpolator());
            return  inFromRight;
        }

        // from right to left   out screen
        public static Animation outFromLeftAnimation(){
            Animation inFromRight = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    -1.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f);

            inFromRight.setDuration(350);
            inFromRight.setInterpolator(new AccelerateInterpolator());
            return  inFromRight;
        }

        // from left to right   out screen
        public static Animation outFromRightAnimation(){
            Animation inFromRight = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    1.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f,
                    Animation.RELATIVE_TO_PARENT,
                    0.0f);

            inFromRight.setDuration(350);
            inFromRight.setInterpolator(new AccelerateInterpolator());
            return  inFromRight;
        }

    }
