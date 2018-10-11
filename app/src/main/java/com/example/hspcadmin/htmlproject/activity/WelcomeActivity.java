package com.example.hspcadmin.htmlproject.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.home.MainHostActivity;
import com.example.hspcadmin.htmlproject.util.UimoduleUtils;
import com.example.hspcadmin.htmlproject.util.ZipUtils;

public class WelcomeActivity extends Activity {
    private TextView welcome_load;
    private String loadstr = ".";
    private TimeCount timeCount;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE = 0xa7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        welcome_load = (TextView)findViewById(R.id.welcome_load);

        if (ZipUtils.userSDKVersion >= 23 && ContextCompat.checkSelfPermission(WelcomeActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {// 请求获取当前权限
            // 权限：读取手机状态、SD卡读写权限、定位权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE);
        }else {
            ZipFlie();
        }

        InitException();
    }

    private void InitException(){
        UimoduleUtils.getUimoduleUtils();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ZipFlie();
                } else {
                    Toast.makeText(WelcomeActivity.this,"请同意系统权限后继续",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /* 定义一个倒计时的内部类 */
    private class TimeCount extends CountDownTimer {
        protected TimeCount(long millisInFuture) {
            super(millisInFuture, 1000);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            welcome_load.setText("加载完成!");
            Intent intent = new Intent(WelcomeActivity.this,MainHostActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            switch (loadstr){
                case ".":
                    loadstr = "..";
                    break;
                case "..":
                    loadstr = "...";
                    break;
                default:
                    loadstr = ".";
                    break;
            }
            welcome_load.setText("加载中"+loadstr);
        }
    }

    /**保存至本地
     * */
    private void ZipFlie(){
        timeCount = new TimeCount(3000);
        timeCount.start();
//        ZipUtils.copyDbFile(this,"APP_HOME.zip");
//        ZipUtils.Zipjy(this);
    }

}
