package com.example.hspcadmin.htmlproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;

import java.util.ArrayList;


public class BottomNavigationBar extends LinearLayout implements View.OnClickListener {

    View mLine;
    TextView mTvRadio1;
    RelativeLayout mRlBottom1;

    TextView mTvRadio2;
    RelativeLayout mRlBottom2;

    TextView mTvRadio3;
    RelativeLayout mRlBottom3;

    TextView mTvRadio4;
    RelativeLayout mRlBottom4;
    TextView mTvRadio5;
    RelativeLayout mRlBottom5;
    LinearLayout mBottomLayout;

    private int mUnCheckColor;
    private int mCheckColor;

    public BottomNavigationBar(Context context) {
        this(context, null);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        parseAttrs(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        parseAttrs(context, attrs);
        init();
    }


    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
//        setOrientation(VERTICAL);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_bottom_main, this);
        mTvRadio1 = view.findViewById(R.id.tv_radio_1);
        mRlBottom1 = view.findViewById(R.id.rl_bottom_1);
        mTvRadio2 = view.findViewById(R.id.tv_radio_2);
        mRlBottom2 = view.findViewById(R.id.rl_bottom_2);
        mTvRadio3 = view.findViewById(R.id.tv_radio_3);
        mRlBottom3 = view.findViewById(R.id.rl_bottom_3);
        mTvRadio4 = view.findViewById(R.id.tv_radio_4);
        mRlBottom4 = view.findViewById(R.id.rl_bottom_4);
        mTvRadio5 = view.findViewById(R.id.tv_radio_5);
        mRlBottom5 = view.findViewById(R.id.rl_bottom_5);
        mUnCheckColor = getColor(R.color.yellow);
        mCheckColor = getColor(R.color.yellow);

        mTextViews.add(mTvRadio1);
        mTextViews.add(mTvRadio2);
        mTextViews.add(mTvRadio3);
        mTextViews.add(mTvRadio4);
        mTextViews.add(mTvRadio5);
        mRlBottom1.setOnClickListener(this);
        mRlBottom2.setOnClickListener(this);
        mRlBottom3.setOnClickListener(this);
        mRlBottom4.setOnClickListener(this);
        mRlBottom5.setOnClickListener(this);
    }

    private static final String TAG = "BottomNavigationBar";

    //标记是否显示
    public void checkTradeBradge(int count) {
        // TODO: 2018/6/19

    }

    public void checkOrder(boolean isVisible) {

    }

    private ArrayList<TextView> mTextViews = new ArrayList<>();

    private Drawable[] mCheckDrawables = {
            getDrawable(R.mipmap.home_icon_true),
            getDrawable(R.mipmap.home_icon_true),
            getDrawable(R.mipmap.home_icon_true),
            getDrawable(R.mipmap.home_icon_true),
            getDrawable(R.mipmap.home_icon_true)
    };
    private Drawable[] mUnCheckDrawables = {
            getDrawable(R.mipmap.home_icon_false),
            getDrawable(R.mipmap.home_icon_false),
            getDrawable(R.mipmap.home_icon_false),
            getDrawable(R.mipmap.home_icon_false),
            getDrawable(R.mipmap.home_icon_false)
    };

    private Drawable getDrawable(int resId) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    private int getColor(int resId) {
        return ContextCompat.getColor(getContext(), resId);
    }

    //清除底部radioButton的状态
    public void clearBottomState() {
        for (int i = 0; i < mTextViews.size(); i++) {
            TextView tv = mTextViews.get(i);
            tv.setTextColor(mUnCheckColor);
            tv.setCompoundDrawables(null, mUnCheckDrawables[i], null, null);
        }
    }

    //设置选中状态
    public void setCheckState(int position) {
        if (position < mTextViews.size() && position < mCheckDrawables.length) {
            TextView tv = mTextViews.get(position);
            tv.setTextColor(mCheckColor);
            tv.setCompoundDrawables(null, mCheckDrawables[position], null, null);
        }
    }

    private int lastPressPos = 0;


    private OnTabSelectedListener mListener;

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.rl_bottom_1:
                mListener.onTabselected(0, lastPressPos);
                lastPressPos = 0;
                break;
            case R.id.rl_bottom_2:
                mListener.onTabselected(1, lastPressPos);
                lastPressPos = 1;
                break;
            case R.id.rl_bottom_3:
                mListener.onTabselected(2, lastPressPos);
                lastPressPos = 2;
                break;
            case R.id.rl_bottom_4:
                mListener.onTabselected(3, lastPressPos);
                lastPressPos = 3;
                break;
            case R.id.rl_bottom_5:
                mListener.onTabselected(4, lastPressPos);
                lastPressPos = 4;
                break;
            default:
                break;
        }
    }

    public interface OnTabSelectedListener {
        void onTabselected(int curPosition, int lastPosition);
    }

}
