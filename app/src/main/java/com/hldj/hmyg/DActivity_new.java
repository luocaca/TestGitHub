package com.hldj.hmyg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.hldj.hmyg.adapter.CartListAdapter;
import com.hldj.hmyg.util.D;
import com.hy.utils.GetServerUrl;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;


/**
 * 收藏夹  列表
 */
public class DActivity_new extends Activity implements IXListViewListener {
    private XListView xlistView_d_new;
    private ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();

    boolean getdata = true; // 避免刷新多出数据
    private CartListAdapter listAdapter;

    private int pageSize = 20;
    private int pageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_d_new);
        xlistView_d_new = (XListView) findViewById(R.id.xlistView_d_new);
        xlistView_d_new.setDivider(null);
        xlistView_d_new.setPullLoadEnable(true);
        xlistView_d_new.setPullRefreshEnable(true);
        xlistView_d_new.setXListViewListener(this);

        initData();
    }

    private void initData() {

        /**
         * 2.5.8.1 收藏夹苗木列表
         1，说明
         收藏夹苗木列表返回该用户对应类型的收藏内容
         2，URL
         Post:      /admin/collect/listSeedling
         3，参数
         admin/collect/listSeedling
         */
        // TODO Auto-generated method stub
        getdata = false;
        FinalHttp finalHttp = new FinalHttp();
        GetServerUrl.addHeaders(finalHttp, true);
        AjaxParams params = new AjaxParams();
        params.put("pageSize", pageSize + "");
        params.put("pageIndex", pageIndex + "");
        finalHttp.post(GetServerUrl.getUrl() + "admin/collect/listSeedling", params,
//        finalHttp.post(GetServerUrl.getUrl() + "admin/cart/list", params,
                new AjaxCallBack<String>() {

                    @Override
                    public void onSuccess(String json) {


                        D.e("========json=========" + json);
//											listAdapter = new CartListAdapter(
//													DActivity_new.this, datas);
//											xListView.setAdapter(listAdapter);
//										pageIndex++;
//                        GsonUtil.formateJson2Bean(json, SaveSeedingGsonBean.class);

                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo,
                                          String strMsg) {
                        // TODO Auto-generated method stub
                        Toast.makeText(DActivity_new.this, R.string.error_net,
                                Toast.LENGTH_SHORT).show();
                        super.onFailure(t, errorNo, strMsg);
                    }

                });
        getdata = true;
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        xlistView_d_new.setPullLoadEnable(false);
        pageIndex = 0;
        datas.clear();
        if (listAdapter == null) {
            listAdapter = new CartListAdapter(DActivity_new.this, datas);
            xlistView_d_new.setAdapter(listAdapter);
        } else {
            listAdapter.notifyDataSetChanged();
        }
        if (getdata == true) {
            initData();
        }
        onLoad();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        onRefresh();
        super.onResume();
    }

    @Override
    public void onLoadMore() {
        xlistView_d_new.setPullRefreshEnable(false);
        initData();
        onLoad();
    }

    private void onLoad() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                xlistView_d_new.stopRefresh();
                xlistView_d_new.stopLoadMore();
                xlistView_d_new.setRefreshTime(new Date().toLocaleString());
                xlistView_d_new.setPullLoadEnable(true);
                xlistView_d_new.setPullRefreshEnable(true);

            }
        }, com.hldj.hmyg.application.Data.refresh_time);

    }

}
