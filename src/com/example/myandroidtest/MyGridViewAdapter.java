package com.example.myandroidtest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-27
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
public class MyGridViewAdapter extends BaseAdapter {

    private Context  mContext;
    private String[] mItems;
    private int[]   mIcons;

    public MyGridViewAdapter(Context con, String[] items, int[] icons){
        mContext = con;
        mIcons   = icons;
        mItems = items;
    }

    public int getCount(){
        return mItems.length;
    }

    public Object getItem(int pos){
        return mItems[pos];
    }
    public long getItemId(int pos){
        return pos;
    }

    public View getView(int postion, View convertView, ViewGroup parent){
        LayoutInflater factory = LayoutInflater.from(mContext);
        View v =(View)factory.inflate(R.layout.grid, null);
        ImageView iv = (ImageView)v.findViewById(R.id.icon);
        TextView  tv = (TextView)v.findViewById(R.id.text);
        iv.setImageResource(mIcons[postion]);
        tv.setText(mItems[postion]);
        Log.v("MyGridViewAdapter", Integer.toString(postion) + "-------------");
        return v;
    }
}
