package com.example.hspcadmin.htmlproject.view.datepicker;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.hspcadmin.htmlproject.R;
import com.example.hspcadmin.htmlproject.util.ToolUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期|单个的时分秒选择器
* **/
public class WheelMain {

	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	private WheelView wv_second;
	public int screenheight;
	private boolean hasSelectTime;
	public Calendar calendar;
	private static int START_YEAR = 2010, END_YEAR = 2030;
	private static int END_MONTH = 11;
	private static int END_DAY = 11;
	private Context mContext;
	private TimeCustomDialog timeCustomDialog;
	private View.OnClickListener monClick;

	public View getView() {
		return view;
	}

	public WheelMain(Context mContext, String date, View.OnClickListener monClick) {
		super();
		hasSelectTime = false;
		this.mContext = mContext;
		this.monClick = monClick;
		this.view = LayoutInflater.from(mContext).inflate(R.layout.timepicker,null);
		initView(date);
		initDialog(view);
	}

	private void initView(String date){
		date = ToolUtils.changeDateFormat("yyyy-MM-dd", "yyyy-MM-dd", date);
		screenheight = ToolUtils.getHeight(mContext);
		Date dd = new Date();
		if (ToolUtils.strToDate(date)!=null) {
			dd = ToolUtils.strToDate(date);
		}else if(ToolUtils.strToTime(date)!=null){
			dd = ToolUtils.strToTime(date);
			hasSelectTime = true;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(hasSelectTime){
			int h = calendar.get(Calendar.HOUR);
			int m = calendar.get(Calendar.MINUTE);
			int s = calendar.get(Calendar.SECOND);
			initDateTimePicker(year, month, day, h, m, s);
		}else {
			initDateTimePicker(year, month, day);
		}
	}

	private void initDialog(View timepickerview){
		timeCustomDialog = new TimeCustomDialog(mContext);
		timeCustomDialog.builder(timepickerview, Gravity.BOTTOM);
		timeCustomDialog.setOnclick(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(monClick!=null){
					monClick.onClick(v);
				}
				timeCustomDialog.dismiss();
			}
		});
	}

	public void initDateTimePicker(int year ,int month,int day){
		this.initDateTimePicker(year, month, day, 0, 0 ,0);
	}

	/**
	 * 弹出日期时间选择器
	 */
	public void initDateTimePicker(int year ,int month ,int day,int h,int m,int s) {
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);

		calendar= Calendar.getInstance();
//		setEND_YEAR(calendar.get(Calendar.YEAR));
//		if((a.get(Calendar.YEAR)) == year &&
//				a.get(Calendar.MONTH) == month){
//			setEndMonth(a.get(Calendar.MONTH)+1);
//			setEndDay(a.get(Calendar.DAY_OF_MONTH));
//			wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
//			wv_year.setCyclic(false);// 可循环滚动
//			wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
//
//			// 月
//			wv_month = (WheelView) view.findViewById(R.id.month);
//			wv_month.setAdapter(new NumericWheelAdapter(1,END_MONTH));
//			wv_month.setCyclic(false);
//			wv_month.setCurrentItem(END_MONTH-1);
//
//			// 日
//			wv_day = (WheelView) view.findViewById(R.id.day);
//			wv_day.setAdapter(new NumericWheelAdapter(1,END_DAY));
//			wv_day.setCyclic(false);
//			wv_day.setCurrentItem(day-1);
//		}else if((a.get(Calendar.YEAR)) == year){
//			setEndMonth(a.get(Calendar.MONTH)+1);
//			wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
//			wv_year.setCyclic(false);// 可循环滚动
//			wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
//
//			// 月
//			wv_month = (WheelView) view.findViewById(R.id.month);
//			wv_month.setAdapter(new NumericWheelAdapter(1,END_MONTH));
//			wv_month.setCyclic(false);
//			wv_month.setCurrentItem(month);
//
//			// 日
//			wv_day = (WheelView) view.findViewById(R.id.day);
//			wv_day.setCyclic(true);
//			// 判断大小月及是否闰年,用来确定"日"的数据
//			if (list_big.contains(String.valueOf(END_MONTH-1))) {
//				wv_day.setAdapter(new NumericWheelAdapter(1, 31));
//			} else if (list_little.contains(String.valueOf(END_MONTH-1))) {
//				wv_day.setAdapter(new NumericWheelAdapter(1, 30));
//			} else {
//				// 闰年
//				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
//					wv_day.setAdapter(new NumericWheelAdapter(1, 29));
//				else
//					wv_day.setAdapter(new NumericWheelAdapter(1, 28));
//			}
//			wv_day.setCurrentItem(day - 1);
//		}else {
			// 年
			wv_year = (WheelView) view.findViewById(R.id.year);
			wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
			wv_year.setCyclic(false);// 可循环滚动
			wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

			// 月
			wv_month = (WheelView) view.findViewById(R.id.month);
			wv_month.setAdapter(new NumericWheelAdapter(1, 12));
			wv_month.setCyclic(true);
			wv_month.setCurrentItem(month);

			// 日
			wv_day = (WheelView) view.findViewById(R.id.day);
			wv_day.setCyclic(true);
			// 判断大小月及是否闰年,用来确定"日"的数据
			if (list_big.contains(String.valueOf(month + 1))) {
				wv_day.setAdapter(new NumericWheelAdapter(1, 31));
			} else if (list_little.contains(String.valueOf(month + 1))) {
				wv_day.setAdapter(new NumericWheelAdapter(1, 30));
			} else {
				// 闰年
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
					wv_day.setAdapter(new NumericWheelAdapter(1, 29));
				else
					wv_day.setAdapter(new NumericWheelAdapter(1, 28));
			}
			wv_day.setCurrentItem(day - 1);
//		}
//			wv_year.setLabel("年");// 添加文字
//			wv_month.setLabel("月");
//			wv_day.setLabel("日");
		setEND_YEAR(calendar.get(Calendar.YEAR));
		setEndMonth(calendar.get(Calendar.MONTH)+1);
		setEndDay(calendar.get(Calendar.DAY_OF_MONTH));

		wv_hours = (WheelView)view.findViewById(R.id.hour);
		wv_mins = (WheelView)view.findViewById(R.id.min);
		wv_second = (WheelView)view.findViewById(R.id.sss);
		if(hasSelectTime){
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);
			wv_second.setVisibility(View.VISIBLE);
			wv_year.setVisibility(View.GONE);
			wv_month.setVisibility(View.GONE);
			wv_day.setVisibility(View.GONE);

			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);// 可循环滚动
			wv_hours.setCurrentItem(h);


			wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
			wv_mins.setCyclic(true);// 可循环滚动
			wv_mins.setCurrentItem(m);

			wv_second.setAdapter(new NumericWheelAdapter(0, 59));
			wv_second.setCyclic(true);// 可循环滚动
			wv_second.setCurrentItem(s);

//			wv_hours.setLabel("时");// 添加文字
//			wv_mins.setLabel("分");// 添加文字
//			wv_second.setLabel("秒");// 添加文字
		}else{
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
			wv_second.setVisibility(View.GONE);
		}
		
		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
//				if(year_num == END_YEAR){
//					wv_month.setAdapter(new NumericWheelAdapter(1,END_MONTH));
//					wv_month.setCyclic(false);
//					if(wv_month.getCurrentItem()+1>END_MONTH){
//						wv_month.setCurrentItem(END_MONTH-1);
//					}
//					if(wv_month.getCurrentItem() == END_MONTH){
//						wv_day.setAdapter(new NumericWheelAdapter(1,END_DAY));
//						wv_day.setCyclic(false);
//						if(wv_day.getCurrentItem()+1>END_DAY){
//							wv_day.setCurrentItem(END_DAY-1);
//						}
//					}
//				}else {
					wv_month.setAdapter(new NumericWheelAdapter(1,12));
					wv_month.setCyclic(true);
//				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				//add by linrf
				/*日期选择器修改月份后日期不重置1*/
//				wv_day.setCurrentItem(0);
//				int year_num = wv_year.getCurrentItem() + START_YEAR;
//				if(year_num == END_YEAR&&month_num == END_MONTH){
//					wv_day.setAdapter(new NumericWheelAdapter(1,END_DAY));
//					wv_day.setCyclic(false);
//					if(wv_day.getCurrentItem()+1>END_DAY){
//						wv_day.setCurrentItem(END_DAY-1);
//					}
//				}else {
					if (list_big.contains(String.valueOf(month_num))) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					} else if (list_little.contains(String.valueOf(month_num))) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 30));
						if(wv_day.getCurrentItem()>29){
							wv_day.setCurrentItem(29);
						}
					} else {
						if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
								.getCurrentItem() + START_YEAR) % 100 != 0)
								|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0){

							wv_day.setAdapter(new NumericWheelAdapter(1, 29));
							if(wv_day.getCurrentItem()>28){
								wv_day.setCurrentItem(28);
							}
						}
						else{
							wv_day.setAdapter(new NumericWheelAdapter(1, 28));
							if(wv_day.getCurrentItem()>27){
								wv_day.setCurrentItem(27);
							}
						}
//					}
					wv_day.setCyclic(true);
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize;
		if(hasSelectTime)
			textSize = (screenheight / 100) * 2;
		else
			textSize = (screenheight / 100) * 3;
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		if(hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_second.TEXT_SIZE = textSize;

	}

	public String getTime() {
		String WV_MINS = "",WV_SECOND = "",WV_HH = "";
		if(wv_hours.getCurrentItem() <10){
			WV_HH = "0"+wv_hours.getCurrentItem();
		}else {
			WV_HH = ""+wv_hours.getCurrentItem();
		}
		if(wv_mins.getCurrentItem() <10){
			WV_MINS = "0"+wv_mins.getCurrentItem();
		}else {
			WV_MINS = ""+wv_mins.getCurrentItem();
		}
		if(wv_second.getCurrentItem() <10){
			WV_SECOND = "0"+wv_second.getCurrentItem();
		}else {
			WV_SECOND = ""+wv_second.getCurrentItem();
		}
		StringBuffer sb = new StringBuffer();
		if(!hasSelectTime)
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("年")
				.append((wv_month.getCurrentItem() + 1)).append("月")
				.append((wv_day.getCurrentItem() + 1)).append("日");
		else
			sb.append(WV_HH).append(":")
			.append(WV_MINS).append(":")
			.append(WV_SECOND);
		return sb.toString();
	}

	public void _show(){
		if(timeCustomDialog == null){
			return;
		}
		timeCustomDialog.show();
	}

	public static int getSTART_YEAR() {
		return START_YEAR;
	}

	public static void setSTART_YEAR(int sTART_YEAR) {
		START_YEAR = sTART_YEAR;
	}

	public static int getEND_YEAR() {
		return END_YEAR;
	}

	public static void setEND_YEAR(int eND_YEAR) {
		END_YEAR = eND_YEAR;
	}

	public static int getEndMonth() {
		return END_MONTH;
	}

	public static void setEndMonth(int endMonth) {
		END_MONTH = endMonth;
	}

	public static int getEndDay() {
		return END_DAY;
	}

	public static void setEndDay(int endDay) {
		END_DAY = endDay;
	}
}
