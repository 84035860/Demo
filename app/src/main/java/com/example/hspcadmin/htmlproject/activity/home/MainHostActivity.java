package com.example.hspcadmin.htmlproject.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.abstracts.BaseActivity;
import com.example.hspcadmin.htmlproject.activity.abstracts.BaseViewActivity;
import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.example.hspcadmin.htmlproject.util.UimoduleUtils;
import com.example.hspcadmin.htmlproject.view.AbstractLayout;
import com.example.hspcadmin.htmlproject.view.BottomNavigationBar;
import com.example.hspcadmin.htmlproject.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wzheng on 2018/6/28.
 */

public class MainHostActivity extends BaseActivity {

    @BindView(R.id.demo_host_bottom)
    BottomNavigationBar demoHostBottom;
    @BindView(R.id.demo_host_viewpager)
    CustomViewPager demoHostViewpager;
    @BindView(R.id.main_setting)
    ImageView mainSetting;
    private List<View> HostView = new ArrayList<>();
    private HomeViewUi homeViewUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        HostView.add(UimoduleUtils.getUimoduleUtils().getUiView(UimoduleUtils.BASEVIEW_WEBVIEW, MainHostActivity.this).getAbstractLayout());
        HostView.add(UimoduleUtils.getUimoduleUtils().getUiView(-1, MainHostActivity.this).getAbstractLayout());
        homeViewUi = new HomeViewUi(this);
        HostView.add(homeViewUi);
        homeViewUi = new HomeViewUi(this);
        HostView.add(homeViewUi);
        HostView.add(UimoduleUtils.getUimoduleUtils().getUiView(UimoduleUtils.BASEVIEW_HOME_SETTING, MainHostActivity.this).getAbstractLayout());
    }

    private void initData() {
        demoHostViewpager.setAdapter(new ViewPagerAdapter());
        demoHostViewpager.setScanScroll(false);
        demoHostViewpager.setOnPageChangeListener(changeListener);

        demoHostBottom.setCheckState(0);
        demoHostBottom.setOnTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabselected(int curPosition, int lastPosition) {
                switchFragment(curPosition);
            }
        });
    }

    @OnClick(R.id.main_setting)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.main_setting:
                ToolUtils.starIntentBaseView(BaseViewActivity.class, UimoduleUtils.BASEVIEW_HOME_SETTING);
                break;
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return HostView.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(HostView.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(HostView.get(position));
            return HostView.get(position);
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int position) {
        }
    };

    private void switchFragment(int i) {
        demoHostBottom.clearBottomState();
        demoHostBottom.setCheckState(i);
        demoHostViewpager.setCurrentItem(i);
        switch (i) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                // TODO: 2018/6/19 是否登录判断
                break;
            default:
                break;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是返回键,直接返回到桌面
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            exitApp();
//            if(myWebView.getUrl().equals("http://121.41.41.4:8081/app/login")){
            exitApp();
//            }else {
//                myWebView.goBack();
//            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime;

    /**
     * 退出App
     */
    private void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainHostActivity.this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            exitSystem();
        }
    }

    protected void exitSystem() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startMain);
        System.exit(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = demoHostViewpager.findViewWithTag(demoHostViewpager.getCurrentItem());
        if (view instanceof AbstractLayout) {
            ((AbstractLayout) view).onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        View view = demoHostViewpager.findViewWithTag(demoHostViewpager.getCurrentItem());
        if (view instanceof AbstractLayout) {
            ((AbstractLayout) view).onPause();
        }
    }
}
