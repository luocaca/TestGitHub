package com.hldj.hmyg.saler;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hldj.hmyg.CallBack.ResultCallBack;
import com.hldj.hmyg.R;
import com.hldj.hmyg.bean.Pic;
import com.hldj.hmyg.bean.SaveSeedingGsonBean;
import com.hldj.hmyg.presenter.SaveSeedlingPresenter;
import com.hldj.hmyg.util.D;
import com.hldj.hmyg.widget.AutoAddRelative;
import com.hldj.hmyg.widget.SaveSeedingBottomLinearLayout;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yangfuhai.asimplecachedemo.lib.ACache;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzy.common.widget.MeasureGridView;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.NeedSwipeBackActivity;

public class SaveSeedlingActivity extends NeedSwipeBackActivity {
    private String[] days = {"30", "90", "180"};
    private ACache mCache;
    public static SaveSeedlingActivity instance;

    ViewHolder viewHolder;//控件管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_seedling);

        viewHolder = new ViewHolder();

        mCache = ACache.get(this);
        KProgressHUD hud_numHud = KProgressHUD.create(SaveSeedlingActivity.this)
                .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                .setLabel("上传中，请等待...").setMaxProgress(100)
                .setCancellable(true);
        instance = this;

        viewHolder.publish_flower_info_gv.setOnItemClickListener(new MeasureGridView.PhotoGvOnItemClickListener(viewHolder.ll_mainView) {
                                                                     @Override
                                                                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                         super.onItemClick(parent, view, position, id);//super 对权限进行检查。。。

                                                                         D.e("===============position===============" + position);
                                                                     }
                                                                 }
        );

        getAllData();


    }


    private void getAllData() {

//       获取所有数据  本页
        SaveSeedlingPresenter.getAllDatas(new ResultCallBack<SaveSeedingGsonBean>() {
            @Override
            public void onSuccess(final SaveSeedingGsonBean saveSeedingGsonBean) {

                List<SaveSeedingGsonBean.DataBean.TypeListBean> typeListBeen = saveSeedingGsonBean.getData().getTypeList();

                initAutoLayout(saveSeedingGsonBean);

                initAutoLayout2(saveSeedingGsonBean);


            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                D.e("============数据加载失败===========");

            }
        });


    }

    //初始化
    private void initAutoLayout2(SaveSeedingGsonBean saveSeedingGsonBean) {
        //设置默认
        //动态添加标签
        SaveSeedlingPresenter.initAutoLayout(viewHolder.id_flowlayout_2, saveSeedingGsonBean, SaveSeedlingActivity.this);
    }

    public void initAutoLayout(final SaveSeedingGsonBean saveSeedingGsonBean) {
        //设置默认
        //动态添加标签
        SaveSeedlingPresenter.initAutoLayout(viewHolder.id_flowlayout, saveSeedingGsonBean, SaveSeedlingActivity.this, new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                paramsListBean = saveSeedingGsonBean.getData().getTypeList().get(position).getParamsList();
                D.e("==tag=点击事件=" + paramsListBean.toString());
                //根据参数来 配置布局
                addParamViews(paramsListBean);
                return true;
            }
        });
    }

    private void addParamViews(List<SaveSeedingGsonBean.DataBean.TypeListBean.ParamsListBean> paramsListBean) {
        int size = paramsListBean.size();
        viewHolder.ll_auto_add_layout.removeAllViews();

        AutoAddRelative autoAddRelative_top = new AutoAddRelative(this)
                .initView(R.layout.save_seeding_auto_add_top);
        viewHolder.ll_auto_add_layout.addView(autoAddRelative_top);

        AutoAddRelative autoAddRelative_rb = new AutoAddRelative(this)
                .initView(R.layout.save_seeding_auto_add_radio);
        viewHolder.ll_auto_add_layout.addView(autoAddRelative_rb);


        for (int i = 0; i < size; i++) {
            AutoAddRelative autoAddRelative = new AutoAddRelative(this).initView(R.layout.save_seeding_auto_add);
            autoAddRelative.setDatas(paramsListBean.get(i));
            viewHolder.ll_auto_add_layout.addView(autoAddRelative);
            // TODO: 2017/4/15    添加 布局  删除布局 动画
        }

    }

    private List<SaveSeedingGsonBean.DataBean.TypeListBean.ParamsListBean> paramsListBean;


    public void removePicUrls(int currentPage) {
        D.e("=========removePicUrls=========");
    }

    public void addPicUrls(ArrayList<Pic> resultPathList) {
        D.e("=========addPicUrls=========");
    }

    public class ViewHolder {

        public MeasureGridView publish_flower_info_gv;
        public TagFlowLayout id_flowlayout;
        public TagFlowLayout id_flowlayout_2;
        public SaveSeedingBottomLinearLayout bottom_ll;
        public TextView iv_ready_save;
        public Button save;
        public LinearLayout ll_auto_add_layout;
        public LinearLayout ll_mainView;


        public ViewHolder() {

            this.publish_flower_info_gv = (MeasureGridView) findViewById(R.id.publish_flower_info_gv);
            this.id_flowlayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
            this.id_flowlayout_2 = (TagFlowLayout) findViewById(R.id.id_flowlayout_2);
            this.bottom_ll = (SaveSeedingBottomLinearLayout) findViewById(R.id.bottom_ll);
            this.iv_ready_save = (TextView) findViewById(R.id.iv_ready_save);
            this.save = (Button) findViewById(R.id.save);
            this.ll_auto_add_layout = (LinearLayout) findViewById(R.id.ll_auto_add_layout);
            this.ll_mainView = (LinearLayout) findViewById(R.id.ll_mainView);
        }

    }
}
