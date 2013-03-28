package com.example.myandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SlidingDrawer;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-27
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
public class SlidingDrawerActivity  extends Activity {
    private GridView gv;
    private SlidingDrawer sd;
    private ImageView im;
    private int[] icons = {R.drawable.alarm, R.drawable.calendar,
                             R.drawable.camera, R.drawable.clock,
                             R.drawable.music,  R.drawable.tv};

    private String[] items = {
            "Alarm", "Calendar", "Camera", "Clock", "Music", "TV"
    };


    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.slidingdrawerlayout);
        gv = (GridView) findViewById(R.id.myContent);
        sd = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        im = (ImageView)findViewById(R.id.imageView);

        MyGridViewAdapter adapter = new MyGridViewAdapter(this,items, icons);
        gv.setAdapter(adapter);
        sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener(){
            public  void onDrawerOpened(){
                im.setImageResource(R.drawable.open);
                Log.v("SlidingDrawerActivity", "opend!!!!!!") ;
            }
        });
        sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                //To change body of implemented methods use File | Settings | File Templates.
                im.setImageResource(R.drawable.close);
                Log.v("SlidingDrawerActivity", "close !!!!!!") ;
            }
        });
    }

}
