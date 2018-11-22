package com.example.hspcadmin.htmlproject.activity.abstracts;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.abstracts.swipeback.SwipeBackHelper;
import com.example.hspcadmin.htmlproject.activity.abstracts.swipeback.SwipeListener;
import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.example.hspcadmin.htmlproject.util.UimoduleUtils;
import com.example.hspcadmin.htmlproject.view.TopBaseView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by hspcadmin on 2018/10/8.
 */

public class AbstractActivity extends BaseActivity {

    @BindView(R.id.base_top_tv)
    TopBaseView baseTopTv;

    public final static String ClASSNAME = "ClassNAME";
    public AbstractLayout baseViewUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightScrollDestroyActivity();
//        setStatusBar();

        setContentView(R.layout.view_ui_layout);
        ButterKnife.bind(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, ToolUtils.dpToPx(20) + (int) getResources().getDimension(R.dimen.activity_top_height), 0, 0);

        UimoduleUtils.UiBean uiBean = UimoduleUtils.getUimoduleUtils().getUiView(getIntent().getIntExtra(ClASSNAME, -1),this);
        baseTopTv.setText(uiBean.getName());
        baseViewUi = uiBean.getAbstractLayout();
        addContentView(baseViewUi, layoutParams);

    }


    //设置右滑销毁界面

    private void setRightScrollDestroyActivity() {
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeEdgePercent(0.1f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeRelateOffset(300).addListener(new SwipeListener() {//滑动监听
            @Override
            public void onScroll(float percent, int px) {//滑动的百分比与距离
            }

            @Override
            public void onEdgeTouch() {//当开始滑动
            }

            @Override
            public void onScrollToClose() {//当滑动关闭
            }
        });
    }

    /**
     * 此方法用来改变状态栏字体的颜色
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
//            if (SharedPreferencesMgr.getInt("theme", 0) == 1) {
//                //白天模式
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            }else{
//                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseViewUi.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseViewUi.onPause();
    }
}
