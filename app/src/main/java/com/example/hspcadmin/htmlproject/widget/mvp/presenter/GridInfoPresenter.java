package com.example.hspcadmin.htmlproject.widget.mvp.presenter;


import com.example.hspcadmin.htmlproject.widget.mvp.model.GridInfoModel;
import com.example.hspcadmin.htmlproject.widget.mvp.model.GridInfoModelImpl;
import com.example.hspcadmin.htmlproject.widget.mvp.view.GridInfoView;

/**
 * @author wzheng  create on 2016/8/11  10:00.
 */
public class GridInfoPresenter implements GridInfoModel.OnLoadWidgetInfoListener {
    private GridInfoView gridInfoView;
    private GridInfoModel gridInfoModel;

    public GridInfoPresenter(GridInfoView gridInfoView) {
        this.gridInfoView = gridInfoView;
    }

    public void loadWidgetInfo() {
        gridInfoModel = new GridInfoModelImpl();
        gridInfoModel.loadWidgetInfo(this);
    }

    @Override
    public void loadSuccess(String str) {
        gridInfoView.loadWidgetInfo(str);
    }

    @Override
    public void loadFailed(String s) {
        gridInfoView.loadFailed(s);
    }
}
