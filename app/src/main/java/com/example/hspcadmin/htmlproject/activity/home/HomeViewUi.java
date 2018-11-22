package com.example.hspcadmin.htmlproject.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractActivity;
import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;
import com.example.hspcadmin.htmlproject.activity.presenter.AbstractPresenter;
import com.example.hspcadmin.htmlproject.util.ToolUtils;
import com.example.hspcadmin.htmlproject.view.ProgressTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.example.hspcadmin.htmlproject.util.UimoduleUtils.BASEVIEW_HOME;
import static com.example.hspcadmin.htmlproject.util.UimoduleUtils.BASEVIEW_OKHTTP;
import static com.example.hspcadmin.htmlproject.util.UimoduleUtils.BASEVIEW_RXJAVA;

/**
 * Created by hspcadmin on 2018/10/10.
 */

public class HomeViewUi extends AbstractLayout {
    @BindView(R.id.app_progresstime)
    ProgressTime appProgresstime;
    @BindView(R.id.app_skip)
    TextView appSkip;
    @BindView(R.id.app_dome1)
    TextView appDome1;
    @BindView(R.id.app_dome2)
    TextView appDome2;
    @BindView(R.id.app_dome3)
    TextView appDome3;
    @BindView(R.id.app_dome4)
    TextView appDome4;
    private Context context;

    public HomeViewUi(Context context) {
        this(context, null);
    }

    public HomeViewUi(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void updataView(AbstractPresenter viewAction) {
        super.updataView(viewAction);
        contextView = LayoutInflater.from(context).inflate(R.layout.home_layout, null, true);
        ButterKnife.bind(this, contextView);
    }

    @OnClick({R.id.app_skip, R.id.app_dome1, R.id.app_dome2, R.id.app_dome3,R.id.app_dome4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.app_skip:
                appSkip.setText("时间已开启");
                appProgresstime.starProgressTime();
                appSkip.setEnabled(false);
                appSkip.setBackgroundColor(getResources().getColor(R.color.white));
                appSkip.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.app_dome1:
                ToolUtils.starIntentBaseView(AbstractActivity.class, BASEVIEW_RXJAVA);
                break;
            case R.id.app_dome2:
                ToolUtils.starIntentBaseView(AbstractActivity.class, BASEVIEW_OKHTTP);
                break;
            case R.id.app_dome3:
                Uri data = Uri.parse("yijintong://123");
                Intent intent = new Intent(Intent.ACTION_VIEW, data);
                try {
                    ((Activity) context).startActivityForResult(intent, RESULT_OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "没有匹配的APP，请下载安装", Toast.LENGTH_SHORT).show();
                }
//                    Intent intent = new Intent(MainHostActivity.this, ProperService.class);
//                    startService(intent);
                break;
            case R.id.app_dome4:
                ToolUtils.starIntentBaseView(AbstractActivity.class, BASEVIEW_HOME);
                break;
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

}

