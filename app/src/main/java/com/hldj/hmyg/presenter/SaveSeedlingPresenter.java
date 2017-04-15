package com.hldj.hmyg.presenter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.hldj.hmyg.CallBack.ResultCallBack;
import com.hldj.hmyg.R;
import com.hldj.hmyg.bean.SaveSeedingGsonBean;
import com.hldj.hmyg.util.ConstantState;
import com.hldj.hmyg.util.D;
import com.hldj.hmyg.util.GsonUtil;
import com.hy.utils.GetServerUrl;
import com.hy.utils.TagAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class SaveSeedlingPresenter {


    public SaveSeedlingPresenter() {

    }


    public static void getAllDatas(ResultCallBack<SaveSeedingGsonBean> resultCallBack) {

        D.e("initAutoLayout");
        initDataGetFirstType(resultCallBack);
    }


    private static void initDataGetFirstType(final ResultCallBack<SaveSeedingGsonBean> resultCallBack) {
        // 获取json 数据
        FinalHttp finalHttp = new FinalHttp();
        GetServerUrl.addHeaders(finalHttp, true);
        AjaxParams params = new AjaxParams();
        finalHttp.post(GetServerUrl.getUrl() + "admin/seedling/initPublish",
                params, new AjaxCallBack<String>() {
                    @Override
                    public void onSuccess(String json) {
                        SaveSeedingGsonBean gsonBean = GsonUtil.formateJson2Bean(json, SaveSeedingGsonBean.class);
                        D.e("==========" + gsonBean.toString());
                        gsonBean.getData().getTypeList();//有5个  乔木 灌木  观景  棕榈/苏铁  地被
                        if (gsonBean.getCode().equals(ConstantState.SUCCEED_CODE)) {
                            //成功
                            D.e("===成功===");
                            resultCallBack.onSuccess(gsonBean);
                        } else {
                            D.e("===失败===");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                    }
                });
    }


    //适配器
//    static class tagadapter extends com.zhy.view.flowlayout.TagAdapter {
//
//        public tagadapter(List datas) {
//            super(datas);
//        }
//
//        @Override
//        public View getView(FlowLayout parent, int position, Object o) {
//            return null;
//        }
//    }

    private static TagAdapter<String> mtypeListAdapter;

    public static void initAutoLayout(final TagFlowLayout mFlowLayout, SaveSeedingGsonBean saveSeedingGsonBean, final Activity Activity, TagFlowLayout.OnTagClickListener onTagClickListener) {

        final List str_typeLists = getListNames(saveSeedingGsonBean);

        com.zhy.view.flowlayout.TagAdapter tagAdapter = new com.zhy.view.flowlayout.TagAdapter(str_typeLists) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) Activity.getLayoutInflater().inflate(R.layout.tv, mFlowLayout, false);
                tv.setText(str_typeLists.get(position).toString());
                return tv;
            }
        };

        mFlowLayout.setAdapter(tagAdapter);

        mFlowLayout.setMaxSelectCount(1);

        mFlowLayout.setOnTagClickListener(onTagClickListener);


//        tagadapter = new com.zhy.view.flowlayout.TagAdapter<String>(
//                str_typeLists) {
//
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                TextView tv = (TextView) getLayoutInflater()
//                        .inflate(R.layout.tv,
//                                mFlowLayout, false);
//                tv.setText(s);
//                return tv;
//            }
//        };
//        mFlowLayout.setAdapter(tagadapter);//自动换行layout
//        mFlowLayout
//                .setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//                    @Override
//                    public boolean onTagClick(View view, int position, FlowLayout parent) {
//                        firstSeedlingTypeId = typeLists.get(position).get("id").toString();
//                        firstSeedlingTypeName = str_typeLists.get(position);
//                        seedlingParams = typeLists.get(position).get("seedlingParams").toString();
//                        tv_firstSeedlingTypeName.setText(firstSeedlingTypeName);
//                        unitType = typeLists.get(position).get("defaultUnit").toString();
//                        validity = typeLists.get(position).get("defaultValidity").toString();
//                        if ("plant".equals(unitType)) {
//                            tv_unitType.setText("株");
//                        } else if ("crowd".equals(unitType)) {
//                            tv_unitType.setText("丛");
//                        } else if ("jin"
//                                .equals(unitType)) {
//                            tv_unitType
//                                    .setText("斤");
//                        } else if ("squaremeter"
//                                .equals(unitType)) {
//                            tv_unitType
//                                    .setText("平方米");
//                        } else if ("dai"
//                                .equals(unitType)) {
//                            tv_unitType
//                                    .setText("袋");
//                        } else if ("pen"
//                                .equals(unitType)) {
//                            tv_unitType
//                                    .setText("盆");
//                        } else {
//                            tv_unitType.setText("");
//                        }
//                        tv_day.setText(validity
//                                + "天");
//                        Data.paramsDatas.clear();
//                        return true;
//                    }
//                });
    }


    private static List getListWithKey(List<SaveSeedingGsonBean.DataBean.TypeListBean.PlantTypeListBean> list_enter) {
        int mSize = list_enter.size();
        List<String> lisWithtKey = new ArrayList();
        if (mSize > 0) {
            for (int i = 0; i < mSize; i++) {
                lisWithtKey.add(list_enter.get(i).getText());//获取所有名称
            }
        }
        return lisWithtKey;

    }

    private static List getListNames(SaveSeedingGsonBean saveSeedingGsonBean) {
        int mSize = saveSeedingGsonBean.getData().getTypeList().size();
        List list = new ArrayList();
        if (mSize > 0) {
            for (int i = 0; i < mSize; i++) {
                list.add(saveSeedingGsonBean.getData().getTypeList().get(i).getName());//获取所有名称
            }
        }
        return list;

    }

    //     * plantTypeList : [{"text":"地栽苗","value":"planted"},{"text":"移植苗","value":"transplant"},{"text":"假植苗","value":"heelin"},{"text":"容器苗","value":"container"}]
    public static void initAutoLayout(final TagFlowLayout mFlowLayout, SaveSeedingGsonBean saveSeedingGsonBean, final Activity saveSeedlingActivity) {

        final List<SaveSeedingGsonBean.DataBean.TypeListBean.PlantTypeListBean> plantTypeList = saveSeedingGsonBean.getData().getPlantTypeList();

        com.zhy.view.flowlayout.TagAdapter tagAdapter = new com.zhy.view.flowlayout.TagAdapter(plantTypeList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) saveSeedlingActivity.getLayoutInflater().inflate(R.layout.tv, mFlowLayout, false);
                tv.setText(plantTypeList.get(position).getText());
                return tv;
            }
        };

        mFlowLayout.setAdapter(tagAdapter);

        mFlowLayout.setMaxSelectCount(1);

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                D.e("===position=====" + position);
                D.e("===name=====" + plantTypeList.get(position).getValue());
                return true;
            }
        });


    }
}

