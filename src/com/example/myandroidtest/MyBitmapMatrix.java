package com.example.myandroidtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-25
 * Time: 下午10:08
 * To change this template use File | Settings | File Templates.
 */
public class MyBitmapMatrix extends Activity {


    private ImageView mImageView;
    private Button mBigButton;
    private Button mSmallButton;
    private RelativeLayout layout;
    private Bitmap bitmap;
    private int id=0;
    private int displayWith = 0;
    private int dispalyHigh = 0;

    private float scaleWith = 1.0f;
    private float scaleHigh = 1.0f;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.bitmapmatrix);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        dispalyHigh = dm.heightPixels - 80;
        displayWith = dm.widthPixels;

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chinaflag);

        if (bitmap == null){

            Log.v("---", "bitmap is null") ;
        }
        mImageView = (ImageView)findViewById(R.id.imageView);
        layout = (RelativeLayout)findViewById(R.id.relationLayout);

        mBigButton = (Button)findViewById(R.id.bigbutton);
        mSmallButton = (Button)findViewById(R.id.smallbutton);

        mBigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                bigger();
            }
        });

        mSmallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                smaller();
            }
        });


    }

    private void bigger(){
        int bitmapWith = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        float   scale = 1.2f;
        scaleHigh = scaleHigh * scale ;
        scaleWith = scaleWith * scale ;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWith, scaleHigh);
        Log.v("-------", Float.toString(bitmapWith) + "\t" + Float.toString(bitmapHeight) + " ----" +
                Float.toString(scaleWith)+ "  " + Float.toString(scaleHigh));
        Bitmap bm = Bitmap.createBitmap(bitmap,0,0, bitmapWith,bitmapHeight,matrix,true);

        if (id==0){
            layout.removeView(mImageView);
        }else {
            layout.removeView((ImageView)findViewById(id));
        }
        id++;

        ImageView imageView = new ImageView(this);
        imageView.setId(id);
        imageView.setImageBitmap(bm);
        layout.addView(imageView);
        setContentView(layout);

        if (scaleHigh * scale * bitmapHeight > dispalyHigh ||
                scaleWith * scale * bitmapWith > displayWith){
            mBigButton.setEnabled(false);
        }
    }

    private void smaller(){
        int bitmapWith = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        float  scale= 0.8f;
        scaleHigh = scaleHigh * scale;
        scaleWith = scaleHigh * scale;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWith, scaleHigh);

        Log.v("-------", Float.toString(bitmapWith) + "\t" + Float.toString(bitmapHeight) + " ----" +
                Float.toString(scaleWith)+ "  " + Float.toString(scaleHigh));
        Bitmap bm = Bitmap.createBitmap(bitmap,0,0,bitmapWith, bitmapWith,matrix,true);
        if (id==0){
            layout.removeView(mImageView);
        }
        else {
            layout.removeView((ImageView)findViewById(id));
        }
        id++;
        ImageView imageView = new ImageView(this);
        imageView.setId(id);
        imageView.setImageBitmap(bm);
        layout.addView(imageView);
        setContentView(layout);

        mBigButton.setEnabled(true);


    }
}
