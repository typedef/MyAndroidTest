package com.example.myandroidtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Preview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mPreview = new Preview(this);
        setContentView(mPreview);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event){

        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_CENTER:
                mPreview.takePicture();
                break;
            default:
                return false;
        }
        return true;
    }

    class  Preview  extends SurfaceView implements SurfaceHolder.Callback
    {
        SurfaceHolder mHolder;
        Camera mCamera;
        Bitmap CameraBitmap;
        Preview(Context context){
            super(context);
            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        public void surfaceCreated(SurfaceHolder holder){
            mCamera = Camera.open();
            /*if (mCamera == null){
                Log.v("MyActivity", "mCamera not found!");
                return;
            }*/
            try {
                mCamera.setPreviewDisplay(holder);
            }
            catch (IOException exception){
                mCamera.release();
                mCamera = null;
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder){
            mCamera.stopPreview();
            mCamera = null;
        }
        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPictureFormat(PixelFormat.JPEG);
            parameters.setPreviewSize(320,480);
            parameters.setPictureSize(320,480);
            mCamera.setParameters(parameters);
        }

        public void  takePicture(){
            if (mCamera != null){
                 mCamera.takePicture(null,null, jpegCallback);
            }
        }

        private Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] _data, Camera _camera) {
                //To change body of implemented methods use File | Settings | File Templates.
                CameraBitmap  = BitmapFactory.decodeByteArray(_data,0,_data.length);
                File myCaptureFile = new File("/sdcard/camera1.jpg") ;
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                    CameraBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                    bos.flush();
                    bos.close();
                    Canvas canvas = mHolder.lockCanvas();
                    canvas.drawBitmap(CameraBitmap, 0, 0, null);
                    mHolder.unlockCanvasAndPost(canvas);
                }
                catch (Exception e){
                    e.getMessage();
                }
            }
        };
    }
}
