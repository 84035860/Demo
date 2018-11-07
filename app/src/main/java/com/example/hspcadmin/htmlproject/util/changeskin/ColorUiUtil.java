package com.example.hspcadmin.htmlproject.util.changeskin;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.hspcadmin.htmlproject.activity.abstracts.AbstractLayout;
import com.example.hspcadmin.htmlproject.view.CustomViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ColorUiUtil {
    /**
     * 切换应用主题
     *
     * @param rootView
     */
    public static void changeTheme(View rootView, Resources.Theme theme) {
        if (rootView instanceof AbstractLayout) {
            ((AbstractLayout) rootView).setTheme();
            if (rootView instanceof ViewGroup) {
                int count = ((ViewGroup) rootView).getChildCount();
                for (int i = 0; i < count; i++) {
                    changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
                }
            }
            if (rootView instanceof AbsListView) {
                try {
                    Field localField = AbsListView.class.getDeclaredField("mRecycler");
                    localField.setAccessible(true);
                    Method localMethod = Class.forName("android.widget.AbsListView$RecycleBin").getDeclaredMethod("clear", new Class[0]);
                    localMethod.setAccessible(true);
                    localMethod.invoke(localField.get(rootView), new Object[0]);
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (InvocationTargetException e5) {
                    e5.printStackTrace();
                }
            }
        } else {
             if(rootView instanceof CustomViewPager){
                 CustomViewPager viewPager = ((CustomViewPager) rootView);
                for(int i=0;i<viewPager.getViews().size();i++){
                    if (viewPager.getViews().get(i) instanceof AbstractLayout) {
                        changeTheme(viewPager.getViews().get(i),theme);
                    }
                }
            }else if (rootView instanceof ViewGroup) {
                int count = ((ViewGroup) rootView).getChildCount();
                for (int i = 0; i < count; i++) {
                    changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
                }
            }
            if (rootView instanceof AbsListView) {
                try {
                    Field localField = AbsListView.class.getDeclaredField("mRecycler");
                    localField.setAccessible(true);
                    Method localMethod = Class.forName("android.widget.AbsListView$RecycleBin").getDeclaredMethod("clear", new Class[0]);
                    localMethod.setAccessible(true);
                    localMethod.invoke(localField.get(rootView), new Object[0]);
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                } catch (InvocationTargetException e5) {
                    e5.printStackTrace();
                }
            }
        }
    }



    private static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if(view instanceof AbstractLayout){
            ((AbstractLayout)view).setTheme();
        }else if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                //再次 调用本身（递归）
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

}
