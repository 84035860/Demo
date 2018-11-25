package com.example.hspcadmin.htmlproject.widget.appWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.hspcadmin.htmlproject.R;


/**
 *  @author wzheng  create on 2016/8/11  11:39.
 */
public class UpdateWidget {
    private RemoteViews remoteViews;
    private Context context;
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;

    public UpdateWidget(Context context, int[] appWidgetIds, AppWidgetManager appWidgetManager) {
        this.context = context;
        this.appWidgetIds = appWidgetIds;
        this.appWidgetManager = appWidgetManager;
        this.remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main_layout);
        //为了方便，在创建UpdateWidget实例的时候，直接调用UpdateWidgetView();
        UpdateWidgetView();
        Log.e("test", "---UpdateWidget");
    }

    public void UpdateWidgetView() {
        for (int appWidgetId : appWidgetIds) {
            /**
             * @author David  created at 2016/8/11 17:37
             *  刷新按钮的点击事件,通过Intent启动服务，PendingIntent.getSwevice();
             */
//            Intent intent = new Intent(context, WidgetService.class);
//            intent.putExtra("appWidgetIds", appWidgetIds);
//            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            remoteViews.setOnClickPendingIntent(R.id.egold_app_top, pendingIntent);

            /**
             * @author David  created at 2016/8/11 17:37
             *  显示Topic及点击事件,通过Intent启动Activity，PendingIntent.getActivity();
             */
//            remoteViews.setImageViewResource(R.id.widget_image, R.drawable.ss_bg);
//            Intent topicIntent = new Intent(context, WelcomeActivity.class);
//            topicIntent.setAction(MyAppWidgetProvider.GOD_START_ACTIVITY);
//            topicIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pendingIntentActivity = pendingIntent.getActivity(context, 0, topicIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//            remoteViews.setOnClickPendingIntent(R.id.widget_image, pendingIntentActivity);

            /**
             * @author David  created at 2016/8/11 17:37
             *  加载Gridview
             */
            showGridInfo();

            /**
             * @author David  created at 2016/8/11 17:37
             *  最后，刷新remoteViews
             */
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        }
    }

    private void showGridInfo() {
        Log.e("test", "---showGridInfo");
        //设置数据显示
        Intent intent = new Intent(context, WidgetGridService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
        remoteViews.setRemoteAdapter(R.id.widget_list, intent);
        remoteViews.setEmptyView(R.id.widget_list, R.id.widget_empty_view);
        //响应事件
        Intent intentEvent = new Intent(Intent.ACTION_MAIN);
        //前提：知道要跳转应用的包名、类名
        ComponentName componentName = new ComponentName("com.sh.android.EGold.activity", "com.sh.android.EGold.activity.main.WelcomeActivity");
        intentEvent.setComponent(componentName);
        intentEvent.addCategory(Intent.CATEGORY_LAUNCHER);
        intentEvent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);// 关键的一步，设置启动模式
        intentEvent.setAction(MyAppWidgetProvider.GOD_GRID);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentEvent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.widget_list, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

}
