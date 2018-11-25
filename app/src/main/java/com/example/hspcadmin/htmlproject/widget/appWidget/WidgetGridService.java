package com.example.hspcadmin.htmlproject.widget.appWidget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.hspcadmin.htmlproject.R;
import com.hundsun.armo.quote.CodeInfo;
import com.hundsun.armo.quote.QuoteConstants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wzheng  create on 2016/8/10  19:44.
 */
public class WidgetGridService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewFactory(this, intent);
    }

    private class GridRemoteViewFactory implements RemoteViewsFactory {

        private Context context;

        private LinkedList<CodeInfo> codeInfoList;//合约列表集合
        private List<String> goldList;//合约列表数据集合
        private ArrayList<String> yanqiCode;//延期合约
        private HashMap<String, String> treatyMap;//协议集合

        public GridRemoteViewFactory(Context context, Intent intent) {
            this.context = context;
            initView();
        }

        /**
         * @author wzheng  created at 2016/8/11 17:41
         * 首次执行，初始化数据时执行onCreate();数据是从数据库里拿的。
         */
        @Override
        public void onCreate() {
            try {
//                String request = SharedPreferenceUtils.getSharedPreferenceUtils().getWidgetpriceData();
//                showQuoteFields(new JSONObject(request));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * @author wzheng  created at 2016/8/11 17:41
         * 当数据源发生变化时，AppWidgetManager调用了 notifyAppWidgetViewDataChanged();方法时执行
         */
        @Override
        public void onDataSetChanged() {
            Log.e("test", "---onDataSetChanged");
            try {
//                String request = SharedPreferenceUtils.getSharedPreferenceUtils().getWidgetpriceData();
//                showQuoteFields(new JSONObject(request));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * @author wzheng  created at 2016/8/11 17:42
         * 销毁时，情况数据源
         */
        @Override
        public void onDestroy() {
//            goldList.clear();
        }

        /**
         * @author wzheng  created at 2016/8/11 17:43
         * 返回Gridview的Item条目数
         */
        @Override
        public int getCount() {
            return goldList.size();
        }

        /**
         * @author wzheng  created at 2016/8/11 17:43
         * 给Gridview设置数据
         */
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_time);
            if (goldList != null) {
//                PriceDBBean priceDBBean = goldList.get(position);
//                String unDownPoints = priceDBBean.getUpDownPoints();
//                StringBuilder sbuilder = new StringBuilder();
//                int colorRes;
//                if ("--".equals(unDownPoints) || unDownPoints == null) {
//                    colorRes = 0xFF9A9A9A; //无数据
//                    sbuilder.append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                } else {
//                    float points = Float.valueOf(unDownPoints);
//                    if (points > 0) {
//                        colorRes = 0xFFF65050;//红色
//                        sbuilder.append("+").append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                    } else if (points < 0) {
//                        colorRes = 0xFF45BE99;//绿色
//                        sbuilder.append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                    } else {
//                        colorRes = 0xFF9A9A9A;//无数据
//                        sbuilder.append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                    }
//                }
//                double unit = Utils.getUnit(treatyMap.get(priceDBBean.getGoldName()));
//                String sbuilderstr = sbuilder.toString().equals("null null")?"- -  - -":sbuilder.toString();
//                remoteViews.setTextViewText(R.id.item_pet_name,priceDBBean.getGoldName());
//                remoteViews.setTextViewText(R.id.item_pet_price,Tool.getDoubleIntValue(unit, priceDBBean.getNowPrice()));
//                remoteViews.setTextColor(R.id.item_pet_price, colorRes);
//                remoteViews.setTextViewText(R.id.item_pet_up,sbuilderstr);
//                remoteViews.setTextColor(R.id.item_pet_up,colorRes);
//                remoteViews.setOnClickFillInIntent(R.id.appwidget_gride, setPendingIntentATD(priceDBBean.getGoldName()));
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        /**
         * @author wzheng  created at 2016/8/11 17:46
         * 无特殊要求，返回的View类型数 ==1；
         */
        @Override
        public int getViewTypeCount() {
            return 1;
        }

        /**
         * @author wzheng  created at 2016/8/11 17:47
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }


        private void initView(){
            codeInfoList = new LinkedList<>();
            codeInfoList.add(new CodeInfo("ATD", QuoteConstants.MARKET_SH_NONFERROUS_METAL));
            codeInfoList.add(new CodeInfo("GTD", QuoteConstants.MARKET_SH_NONFERROUS_METAL));
            codeInfoList.add(new CodeInfo("MTD", QuoteConstants.MARKET_SH_NONFERROUS_METAL));
            codeInfoList.add(new CodeInfo("A9999", QuoteConstants.MARKET_SH_NONFERROUS_METAL));
            codeInfoList.add(new CodeInfo("A9995", QuoteConstants.MARKET_SH_NONFERROUS_METAL));
            codeInfoList.add(new CodeInfo("A100g", QuoteConstants.MARKET_SH_NONFERROUS_METAL));
            goldList = new ArrayList<>();
            for(int i=0;i<codeInfoList.size();i++){
//                Stock stock = new Stock();
//                stock.setCodeInfo(codeInfoList.get(i));
//                PriceDBBean priceDBBean = new PriceDBBean();
//                priceDBBean.setSocket(stock);
//                switch (codeInfoList.get(i).getCode()) {
//                    case "A9999":
//                        priceDBBean.setGoldName("Au99.99");
//                        priceDBBean.setGoldCode("A9999");
//                        break;
//                    case "A9995":
//                        priceDBBean.setGoldName("Au99.95");
//                        priceDBBean.setGoldCode("A9995");
//                        break;
//                    case "A100g":
//                        priceDBBean.setGoldName("Au100g");
//                        priceDBBean.setGoldCode("A100g");
//                        break;
//                    case "ATD":
//                        priceDBBean.setGoldName("Au(T+D)");
//                        priceDBBean.setGoldCode("ATD");
//                        break;
//                    case "GTD":
//                        priceDBBean.setGoldName("Ag(T+D)");
//                        priceDBBean.setGoldCode("GTD");
//                        break;
//                    case "MTD":
//                        priceDBBean.setGoldName("mAu(T+D)");
//                        priceDBBean.setGoldCode("MTD");
//                        break;
//                }
//                goldList.add(priceDBBean);
            }

            yanqiCode = new ArrayList<>();//延期合约
            yanqiCode.add("ATN1");
            yanqiCode.add("ATN2");
            yanqiCode.add("ATD");
            yanqiCode.add("MTD");
            yanqiCode.add("GTD");
//            treatyMap = Utils.getTreatyMap(SharedPreferenceUtils.getSharedPreferenceUtils().getTreatyUnit());

        }

        /**
         * 品种详情信息
         */
        private void showQuoteFields(JSONObject jsonObject) {
            if (jsonObject == null || goldList == null) {
                return;
            }
            try {
                int size = goldList.size();
                Log.e("test", "---size---" + size);
//                PriceDBBean priceDBBean;
//                for (int i = 0; i < size; i++) {
//                    JSONObject json = jsonObject.getJSONObject(goldList.get(i).getGoldCode());
//
//                    priceDBBean = new PriceDBBean();
//                    Stock curStock = new Stock();
//                    CodeInfo codeInfo = new CodeInfo();
//                    codeInfo.setCode(goldList.get(i).getGoldCode());
//                    codeInfo.setCodeType(Short.valueOf("24836"));
//
//                    curStock.setCodeInfo(codeInfo);
//                    String goldCode = codeInfo.getCode();
//                    curStock.setStockName(goldCode);
//
//                    // 将昨收价/现价记录下来，后面可以不再请求昨收价
//                    curStock.setPrevClosePrice(Float.valueOf(json.optString("pp")));
//                    curStock.setNewPrice(Float.valueOf(json.optString("np")));
//                    priceDBBean.setSocket(curStock);
//
//                    priceDBBean.setGoldCode(goldCode);
//
//                    float newPrices = curStock.getNewPrice();//最新价
//                    float prices = curStock.getPrevClosePrice();//比较价
//                    if (yanqiCode.contains(goldCode) || "G9999".equals(goldCode)) { //延期合约
//                        priceDBBean.setPostpone(true);//延期合约
//                    } else {//现货合约
//                        priceDBBean.setPostpone(false);//现价合约
//                    }
//                    priceDBBean.setUpDownPrice(prices);
//                    priceDBBean.setComparePrices(prices);
//                    if (newPrices > 0 && prices > 0) {
//                        float upDownPoint = new BigDecimal(String.valueOf(newPrices)).subtract(new BigDecimal(String.valueOf(prices))).floatValue();
//                        String zdf = Utils.getPersentDecimalFormat().format(upDownPoint * 100 / prices) + "%";
//                        String zdStr = Utils.pricesFormat(String.valueOf(upDownPoint));
//                        if (upDownPoint == 0) {
//                            zdf = "0.00%";
//                            zdStr = "0.00";
//                        } else if (upDownPoint > 0) {
//                            zdf = "+" + zdf;
//                        }
//                        priceDBBean.setUpDownPoints(zdStr);
//                        priceDBBean.setUpDownPercent(zdf);
//                    } else {
//                        priceDBBean.setUpDownPoints("--");
//                        priceDBBean.setUpDownPercent("--");
//                    }
//
//                    String newPricesStr = Utils.pricesFormat(String.valueOf(newPrices));
//                    priceDBBean.setNowPrice("0".equals(newPricesStr) ? "--" : newPricesStr);
//
//                    StringBuilder sbuilder = new StringBuilder();
//                    int colorRes;
//
//                    String unDownPoints = priceDBBean.getUpDownPoints();
//                    if ("--".equals(unDownPoints)) {
//                        colorRes = R.color.text_main;
//                        sbuilder.append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                    } else {
//                        float points = Float.valueOf(unDownPoints);
//                        if (points > 0) {
//                            colorRes = R.color.main_red;
//                            sbuilder.append("+").append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                        } else if (points < 0) {
//                            colorRes = R.color.main_green;
//                            sbuilder.append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                        } else {
//                            colorRes = R.color._9A9A9A;
//                            sbuilder.append(priceDBBean.getUpDownPoints()).append(" ").append(priceDBBean.getUpDownPercent());
//                        }
//                    }
//                    switch (goldCode) {
//                        case "A9999":
//                            priceDBBean.setGoldName("Au99.99");
//                            break;
//                        case "A9995":
//                            priceDBBean.setGoldName("Au99.95");
//                            break;
//                        case "A100g":
//                            priceDBBean.setGoldName("Au100g");
//                            break;
//                        case "ATD":
//                            priceDBBean.setGoldName("Au(T+D)");
//                            break;
//                        case "GTD":
//                            priceDBBean.setGoldName("Ag(T+D)");
//                            break;
//                        case "MTD":
//                            priceDBBean.setGoldName("mAu(T+D)");
//                            break;
//                    }
//
//                    for (int j = 0; j < goldList.size(); j++) {
//                        PriceDBBean tem = goldList.get(j);
//                        if (tem.getSocket().getCodeInfo().getCode().equals(curStock.getCodeInfo().getCode())) {
//                            goldList.set(j, priceDBBean);
//                        }
//                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Intent setPendingIntentATD(String GoldeNmae) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
//            PriceDBBean dbBean = null;
//            for(int i=0;i<goldList.size();i++){
//                if(goldList.get(i).getGoldName().equals(GoldeNmae)){
//                    dbBean = goldList.get(i);
//                }
//            }
//
//            if (dbBean == null) {
//                return null;
//            }
//            bundle.putSerializable("goldData", dbBean);
            intent.putExtras(bundle);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);// 关键的一步，设置启动模式
            
            return intent;
        }
    }
}
