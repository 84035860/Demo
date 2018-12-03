package com.example.hspcadmin.htmlproject.activity.home;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractActivity;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;
import com.example.hspcadmin.htmlproject.activity.abstracts.BaseActivity;
import com.example.hspcadmin.htmlproject.util.EventBusUtils;
import com.example.hspcadmin.htmlproject.util.EventBusVal;
import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.example.hspcadmin.htmlproject.util.UimoduleUtils;
import com.example.hspcadmin.htmlproject.util.changeskin.ColorUiUtil;
import com.example.hspcadmin.htmlproject.view.BottomNavigationBar;
import com.example.hspcadmin.htmlproject.view.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        HostView.add(UimoduleUtils.getUimoduleUtils().getUiView(UimoduleUtils.BASEVIEW_WEBVIEW, MainHostActivity.this).getAbstractLayout());
        HostView.add(UimoduleUtils.getUimoduleUtils().getUiView(-1, MainHostActivity.this).getAbstractLayout());
        HostView.add(new HomeViewUi(this));
        HostView.add(new HomeViewUi(this));
        HostView.add(UimoduleUtils.getUimoduleUtils().getUiView(UimoduleUtils.BASEVIEW_HOME_SETTING, MainHostActivity.this).getAbstractLayout());
    }

    private void initData() {
        demoHostViewpager.setViews(HostView);
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
                ToolUtils.starIntentBaseView(AbstractActivity.class, UimoduleUtils.BASEVIEW_HOME_SETTING);
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
        if(HostView.get(i) instanceof AbstractLayout){
            ((AbstractLayout)HostView.get(i)).onResume();
        }
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
        View view = demoHostViewpager.getViews().get(demoHostViewpager.getCurrentItem());
        if (view instanceof AbstractLayout) {
            ((AbstractLayout) view).onResume();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(EventBusUtils eventBusUtils) {
        if(EventBusVal.INSTANCE.getEVENTBUS_VAL_HOME_SKIN().equals(eventBusUtils.getValstr())){
            final View rootView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= 14) {
                rootView.setDrawingCacheEnabled(true);
                rootView.buildDrawingCache(true);
                final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                rootView.setDrawingCacheEnabled(false);
                if (null != localBitmap && rootView instanceof ViewGroup) {
                    final View localView2 = new View(MainHostActivity.this);
                    localView2.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ((ViewGroup) rootView).addView(localView2, params);
                    localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            ColorUiUtil.changeTheme(rootView, getTheme());
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            ((ViewGroup) rootView).removeView(localView2);
                            localBitmap.recycle();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).start();
                }
            } else {
                ColorUiUtil.changeTheme(rootView, getTheme());
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        View view = demoHostViewpager.getChildAt(demoHostViewpager.getChildCount());
        if (view instanceof AbstractLayout) {
            ((AbstractLayout) view).onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
