package com.example.myandroidtest;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-28
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class SearchManagerActivity extends Activity {
     private static final int MENU_SEARCH=1;
     private Gallery mGallery;
     private TextView mTextView01;
     private ArrayList<String> mFileNames;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.searchmanager);

        // web 搜索
        Intent queryIntent = getIntent();
        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction)){
            Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
            searchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String query = queryIntent.getStringExtra(SearchManager.QUERY);
            searchIntent.putExtra(SearchManager.QUERY, query);

            Bundle appdata = queryIntent.getBundleExtra(SearchManager.APP_DATA);
            if (appdata != null){
                searchIntent.putExtra(SearchManager.APP_DATA, appdata);
                Log.v("TAG+++", "app data is not null!") ;
            }
            startActivity(searchIntent);
        }

       /*
        本地搜索
        mGallery = (Gallery)findViewById(R.id.myGallery);
        mTextView01 = (TextView)findViewById(R.id.myText);
        Intent queryIntent = getIntent();
        final String queryAction = queryIntent.getAction();
        if (Intent.ACTION_SEARCH.equals(queryAction)){
            String query = queryIntent.getStringExtra(SearchManager.QUERY);
            // 打开图片库
            queryPicture(query);

        }
        */
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        menu.add(0, MENU_SEARCH, 0, "search").setIcon(android.R.drawable.ic_search_category_default).setAlphabeticShortcut(SearchManager.MENU_KEY);
        //Log.v("SearchManagerActivity", "+onCreateOptionsMenu+++++");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem selectItem){

        switch (selectItem.getItemId()){
            case MENU_SEARCH:
                onSearchRequested();
                return true;
        }
        return super.onOptionsItemSelected(selectItem);
    }

    private void queryPicture(String query){
        mFileNames = new ArrayList<String>();
        String [] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_MODIFIED,
                MediaStore.Images.Media.DISPLAY_NAME
        };

        String selection = "("  + projection[1] + ">=? and " + projection[1] + "<=?)";

        String selectionArgs[] = getStartEnd(query);
        Log.d("TAG++", selectionArgs.toString()+"=====");
        Cursor cursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,null,null,null
                //selection,selectionArgs, null
        );
        while (cursor.moveToNext()){
            String filename=cursor.getString(0);
            mFileNames.add(filename);
        }
        mTextView01.setText(query + " total " + mFileNames.size() + " pics !");
        mGallery.setAdapter(new MyAdapter(this));
    }

    String[] getStartEnd(String query){
        String[] result = {"", ""};
        String startDateTime = query + "000000000";
        String endDateTime   = query + "235959000";
        try {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(Calendar.YEAR, new Integer(startDateTime.substring(0,4)).intValue());
            calendar.set(Calendar.MONTH, new Integer(startDateTime.substring(4,6)).intValue() - 1);
            calendar.set(Calendar.DATE, new Integer(startDateTime.substring(6,8)).intValue());
            calendar.set(Calendar.HOUR_OF_DAY, new Integer(startDateTime.substring(8,10)).intValue());
            calendar.set(Calendar.MINUTE, new Integer(startDateTime.substring(10,12)).intValue());
            calendar.set(Calendar.SECOND, new Integer(startDateTime.substring(12,14)).intValue());

            startDateTime = String.valueOf(calendar.getTimeInMillis()).substring(0,10);
            Log.v("--------", calendar.toString() + " start !");
            calendar.clear();
           // java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(Calendar.YEAR, new Integer(endDateTime.substring(0,4)).intValue()+1);
            calendar.set(Calendar.MONTH, new Integer(endDateTime.substring(4,6)).intValue() - 1);
            calendar.set(Calendar.DATE, new Integer(endDateTime.substring(6,8)).intValue());
            calendar.set(Calendar.HOUR_OF_DAY, new Integer(endDateTime.substring(8,10)).intValue());
            calendar.set(Calendar.MINUTE, new Integer(endDateTime.substring(10,12)).intValue());
            calendar.set(Calendar.SECOND, new Integer(endDateTime.substring(12,14)).intValue());
            Log.v("--------", calendar.toString() + " end !");
            endDateTime = String.valueOf(calendar.getTimeInMillis()).substring(0,10);
        }catch (Exception e){
            startDateTime="";
            endDateTime = "";
        }finally {
            result[0] = startDateTime;
            result[1] = endDateTime;
        }
        return result;
    }

    private class  MyAdapter extends BaseAdapter{
        Context myContext;
        public MyAdapter(Context context){
            myContext = context;
        }
        public int getCount(){
            return mFileNames.size();
        }

        public Object getItem(int arg0){
            return arg0;
        }

        public long getItemId(int position){
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView = new ImageView(this.myContext);
            InputStream is = null;
            //Log.d("TAG", Integer.toString(position) + "-------");
            LayoutInflater factory = LayoutInflater.from(myContext);
            View v =(View)factory.inflate(R.layout.grid, null);
            try{
              /*  ImageView iv = (ImageView)v.findViewById(R.id.icon);
                TextView  tv = (TextView)v.findViewById(R.id.text);
                is = new FileInputStream(mFileNames.get(position));
                Bitmap bm = BitmapFactory.decodeStream(is);
                is.close();
                iv.setImageBitmap(bm);
                tv.setText(mFileNames.get(position));
                */
                is = new FileInputStream(mFileNames.get(position));
                Bitmap bm = BitmapFactory.decodeStream(is);
                is.close();
                imageView.setImageBitmap(bm);
                Log.v("MyGridViewAdapter", Integer.toString(position) + "-------------");
                }catch (Exception e){
                    e.printStackTrace();
            }
            return imageView;
        }

    }

}
