package com.example.myandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-20
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public class MyActivity02 extends Activity {

    private final String TAG =  this.getClass().getSimpleName();
    private TextView mTextView = null;
    private Button   mButton = null;
    private EditText mEditText = null;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main01);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.TextView01);
        mEditText = (EditText)findViewById(R.id.editText);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                Socket socket = null;
                String message = mEditText.getText().toString()+"\r\n";
                try{
                       socket = new Socket("10.0.2.2", 54321);

                       PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                       out.println(message);

                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg = br.readLine();

                    if (msg != null){
                         mTextView.setText(msg);
                    }else {
                        mTextView.setText("NULL!");
                    }
                    out.close();
                    br.close();
                    socket.close();

                }   catch (Exception e)
                {
                    Log.v(TAG,e.getMessage() + " ----------");
                }
            }
        });
    }
}
