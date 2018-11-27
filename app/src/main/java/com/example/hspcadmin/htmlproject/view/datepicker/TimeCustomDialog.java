package com.example.hspcadmin.htmlproject.view.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.exception.AppException;
import com.example.hspcadmin.htmlproject.util.ToolUtils;


/**
 * Created by wzheng on 2018/1/3.
 */

public class TimeCustomDialog {
    private Context context;
    private Dialog dialog;
    private int screenWidth;
    private View timeview;
    private WheelMain timestr;
    private TextView querview,quxview;
    private LinearLayout time_picker_layout;
    private TextView time_picker_one,time_picker_two,time_picker_thr;

    public TimeCustomDialog(Context context) {
        this.context = context;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    public TimeCustomDialog builder(final View view, int gravity) {

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(gravity);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.height = ToolUtils.dpToPx(220);
        lp.width = ToolUtils.getWidth(context);

        if (AppException.getInstance().getSharedPreferencesValue(AppException.SP_THEME).equals("1")) {
            //白天模式
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            //夜间模式
            view.setBackgroundColor(context.getResources().getColor(R.color.trade_tab));
        }
        dialogWindow.setAttributes(lp);

        querview = (TextView)view.findViewById(R.id.text_queding);
        querview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timestr!=null)
                    ((TextView)timeview).setText(timestr.getTime());
                dialog.dismiss();
            }
        });
        quxview = (TextView)view.findViewById(R.id.text_quxiao);
        quxview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        time_picker_layout = (LinearLayout)view.findViewById(R.id.time_picker_layout);
        time_picker_one = (TextView)view.findViewById(R.id.time_picker_one);
        time_picker_two = (TextView)view.findViewById(R.id.time_picker_two);
        time_picker_thr = (TextView)view.findViewById(R.id.time_picker_thr);
        return this;
    }

    public void setView(View view, WheelMain timestr){
        this.timeview = view;
        this.timestr = timestr;
    }

    public void setOnclick(View.OnClickListener onclick){
        querview.setOnClickListener(onclick);
    }

    public void setTimeCustomTopLayout(String onestr, View.OnClickListener onClickListener,
                                       String twostr,
                                       String thrstr){
        time_picker_layout.setVisibility(View.VISIBLE);
        time_picker_one.setText(onestr);
        time_picker_two.setText(twostr);
        time_picker_thr.setText(thrstr);
        time_picker_one.setOnClickListener(onClickListener);
        time_picker_two.setOnClickListener(onClickListener);
        time_picker_thr.setOnClickListener(onClickListener);
    }

    public void show(){
        if(dialog==null){
            return;
        }
        dialog.show();
    }

    public void dismiss(){
        if(dialog==null){
            return;
        }
        dialog.dismiss();
    }

    private OnUpTimeCallBack onUpTimeCallBack;

    public interface OnUpTimeCallBack{
        void onUpTimeCallBack(String startTime, String endTime);
    }

    public void setOnUpTimeCallBack(OnUpTimeCallBack onUpTimeCallBack){
        this.onUpTimeCallBack=onUpTimeCallBack;
    }

}
