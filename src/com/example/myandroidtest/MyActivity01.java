package com.example.myandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import com.example.myandroidtest.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.lang.InterruptedException;
import java.lang.Override;
import java.lang.Runnable;
import java.lang.String;
import java.lang.Thread;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import  java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-20
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class MyActivity01 extends Activity {
    private final static String TAG = "MyActivity01";// getClass().getSimpleName();
    private TextView mTextView;
    private Button mButton;
    public void onCreate(Bundle bundle){

        super.onCreate(bundle);
        setContentView(R.layout.main01);
        mButton = (Button)findViewById(R.id.button);
        mTextView = (TextView)findViewById(R.id.TextView01);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                refresh();
            }
        });
        new Thread(mRunnable).start();
    }
    private  void refresh(){
        String httpUrl = "http://10.20.126.198/images/page_test.jsp";
        String resultData = "";
        URL url=null;
        try{
            url = new URL(httpUrl);
        }catch (MalformedURLException e){
            Log.e(TAG, "MalformedURLException  " + e);
        }
        if (url != null) {
            try{
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream()) ;
                BufferedReader buffer = new BufferedReader(in);
                String inputLine = null;
                while (((inputLine = buffer.readLine()))!= null){
                    resultData +=  inputLine + "\n";
                    //Log.v(TAG, "-----" + resultData +"\n");
                }
                buffer.close();
                in.close();
                urlConnection.disconnect();
                if (resultData != null){
                    mTextView.setText(resultData);
                }else{
                    mTextView.setText("读取的内容为NULL");
                }
            }catch (IOException  e){
                Log.e(TAG, "IOException"+e.toString() + "\t" + e.getMessage());
            }

        }else{
            Log.e(TAG, "URL null!");
        }
    }

    private java.lang.Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //To change body of implemented methods use File | Settings | File Templates.
            while(true){
                try
                {
                    Thread.sleep(5 * 1000);
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
                catch (InterruptedException e){
                    Log.e(TAG, e.toString());
                }
            }
        }
    };
    Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            refresh();
        }
    };
}
