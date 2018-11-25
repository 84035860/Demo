package com.example.hspcadmin.htmlproject.widget.appWidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.widget.mvp.presenter.GridInfoPresenter;
import com.example.hspcadmin.htmlproject.widget.mvp.view.GridInfoView;


/**
 * @author wzheng  create on 2016/8/11  11:34.
 * @info 桌面闹钟列表
 */
public class WidgetService extends Service implements GridInfoView {

    private GridInfoPresenter gridInfoPresenter;
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;
    private Thread thread = new Thread(new TimeThread());
    private boolean isUpdata = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //拿到更新所需要的内容：appWidgetIds;appWidgetManager;
        if (intent.getIntArrayExtra("appWidgetIds")==null){
            return super.onStartCommand(intent, flags, startId);
        }
        appWidgetIds = intent.getIntArrayExtra("appWidgetIds");
        appWidgetManager = AppWidgetManager.getInstance(WidgetService.this);
        //利用MVP模式，下载数据。备注：如果对MVP不是很了解的话，也可以使用自己的请求方式请求数据即可。
        //另：如果你想学习MVP模式的话：可前往“https://github.com/GodDavide/MVP”查看MVP模式介绍，感谢您的支持。
        Toast.makeText(WidgetService.this, "小组件拼命加载中...", Toast.LENGTH_SHORT).show();
        gridInfoPresenter = new GridInfoPresenter(this);

        gridInfoPresenter.loadWidgetInfo();

        //初始化Widget（此时Gridview还没有最新数据，如果是首次创建的话，数据为空，非首次，显示的是上次请求的数据。数据存储在数据库里）
        //为了方便，将更新方法直接写进构造函数里了
        new UpdateWidget(WidgetService.this, appWidgetIds, appWidgetManager);
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isUpdata = false;
        Log.e("test", "---handler"+appWidgetIds+"--"+appWidgetManager);
        super.onDestroy();
    }

    @Override
    public void loadWidgetInfo(String str) {
        //利用MVP模式下载数据，下载成功后，首先更新数据库，然后，通过Handler返回信息，执行刷新Widget功能。
        try {
            if (str != null) {
                //首先，清空数据库，然后，将新数据添加进去。
//                SharedPreferenceUtils.getSharedPreferenceUtils().setWidgetpriceData(str);
                Message obtain = Message.obtain();
                obtain.what = 100;
                handler.sendMessage(obtain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //WidgetGridService将自动执行:onDataSetChanged()方法，然后，从新对Gridview进行赋值刷新。
            if (msg.what == 100) {
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
            }else if(msg.what == 1){
                Log.e("test", "---handler"+appWidgetIds+"--"+appWidgetManager);
                gridInfoPresenter.loadWidgetInfo();
                //初始化Widget（此时Gridview还没有最新数据，如果是首次创建的话，数据为空，非首次，显示的是上次请求的数据。数据存储在数据库里）
                //为了方便，将更新方法直接写进构造函数里了
                new UpdateWidget(WidgetService.this, appWidgetIds, appWidgetManager);
            }
        }
    };

    @Override
    public void loadFailed(String s) {
    }

    class TimeThread extends Thread {
          @Override
          public void run() {
            while (isUpdata){
               try {
                   Thread.sleep(10000);//每隔10s执行一次
                   Message msg = new Message();
                   msg.what = 1;
                   handler.sendMessage(msg);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }

             }
          }
    }
}
