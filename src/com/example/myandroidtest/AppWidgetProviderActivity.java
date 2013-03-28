package com.example.myandroidtest;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zouying
 * Date: 13-3-28
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class AppWidgetProviderActivity  extends AppWidgetProvider{
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {

        Intent intent = new Intent(context, UpdateService.class);
        context.startService(intent);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public static class UpdateService extends Service {
        public IBinder onBind(Intent intent){
            return null;
        }
        @Override
        public void onStart(Intent intent, int startId){
            super.onStart(intent, startId);

            RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.main);//
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            updateViews.setTextViewText(R.id.TextView01, ""+sdf.format(new Date()));
            ComponentName thisWidget = new ComponentName(this, this.getClass());
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            manager.updateAppWidget(thisWidget, updateViews);
        }
    }

}
