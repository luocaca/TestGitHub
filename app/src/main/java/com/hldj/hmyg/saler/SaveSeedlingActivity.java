package com.hldj.hmyg.saler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import com.hldj.hmyg.CallBack.ResultCallBack;
import com.hldj.hmyg.application.MyApplication;
import com.hldj.hmyg.bean.SaveSeedingGsonBean;
import com.hldj.hmyg.presenter.SaveSeedlingPresenter;
import com.hldj.hmyg.util.D;
import com.hldj.hmyg.util.GsonUtil;
import com.hldj.hmyg.util.MyUtil;
import com.hldj.hmyg.util.SPUtil;
import com.hldj.hmyg.widget.SaveSeedingBottomLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布苗木资源
 */
public class SaveSeedlingActivity extends SaveSeedlingActivityBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //暂存草稿箱
        viewHolder.iv_ready_save.setOnClickListener(onClickListener);//

//        DBOpenHelper dbOpenHelper = new DBOpenHelper(this, DB_NAME, null, DB_VERSION);
//        try {
//            db = dbOpenHelper.getWritableDatabase();
//        } catch (SQLiteException ex) {
//            db = dbOpenHelper.getReadableDatabase();
//        }

        getAllData();
    }

    private void getAllData() {

//       获取所有数据  本页
        SaveSeedlingPresenter.getAllDatas(new ResultCallBack<SaveSeedingGsonBean>() {
            @Override
            public void onSuccess(final SaveSeedingGsonBean saveSeedingGsonBean) {

                SaveSeedlingActivity.this.saveSeedingGsonBean = saveSeedingGsonBean;

                initAutoLayout(saveSeedingGsonBean.getData().getTypeList());

                initAutoLayout2(saveSeedingGsonBean.getData().getPlantTypeList());

                //传参数的时候   增加 一个 自动添加布局，不需要点击  （乔木  灌木  庄敬  地被  苏铁  。。。。。。）
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                D.e("============数据加载失败===========");

            }
        });


    }


    View.OnClickListener onClickListener = v -> {
        SaveSeedingGsonBean.DataBean.SeedlingBean seedlingBean = new SaveSeedingGsonBean.DataBean.SeedlingBean();
        //step 1  图片如何插入
        if (viewHolder.publish_flower_info_gv.getAdapter().getDataList().size() != 0) {//

            List<SaveSeedingGsonBean.DataBean.SeedlingBean.ImagesJsonBean> list_imgs
                    = new ArrayList<SaveSeedingGsonBean.DataBean.SeedlingBean.ImagesJsonBean>();
            for (int i = 0; i < viewHolder.publish_flower_info_gv.getAdapter().getDataList().size(); i++) {
                SaveSeedingGsonBean.DataBean.SeedlingBean.ImagesJsonBean
                        imagesJsonBean = new SaveSeedingGsonBean.DataBean.SeedlingBean.ImagesJsonBean()
                        .setLocal_url(viewHolder.publish_flower_info_gv.getAdapter()
                                .getDataList()
                                .get(i).getUrl());

                list_imgs.add(imagesJsonBean);
            }
            seedlingBean.setImagesJson(list_imgs);
        }


        //step 2 中间动态数据 保存
        seedlingBean.setFirstSeedlingTypeId(tag_ID);
        seedlingBean.setPlantType(tag_ID1);
        seedlingBean.setName(viewHolder_top.tv_auto_add_name.getText().toString());

        if (viewHolder_rd != null) {
            if (autoAddRelative_rd.getMTag().equals("dbh")) {
                seedlingBean.setMaxDbh(MyUtil.formateString2Int(viewHolder_rd.et_auto_add_max.getText().toString()));
                seedlingBean.setMinDbh(MyUtil.formateString2Int(viewHolder_rd.et_auto_add_min.getText().toString()));
                seedlingBean.setDbhType(autoAddRelative_rd.getDiameterType());
            } else {

                seedlingBean.setMaxDiameter(MyUtil.formateString2Int(viewHolder_rd.et_auto_add_max.getText().toString()));
                seedlingBean.setMinDiameter(MyUtil.formateString2Int(viewHolder_rd.et_auto_add_min.getText().toString()));
                seedlingBean.setDiameterType(autoAddRelative_rd.getDiameterType());
            }
        }


        //step 3  底部固定布局 数据保存


        SaveSeedingBottomLinearLayout.upLoadDatas upLoadDatas = viewHolder.bottom_ll.getUpLoadDatas();

        if (MyUtil.formateString2Int(upLoadDatas.getPrice_min()) != 0) {
            seedlingBean.setMinPrice(MyUtil.formateString2Int(upLoadDatas.getPrice_min()));
        }
        if (MyUtil.formateString2Int(upLoadDatas.getPrice_max()) != 0) {
            seedlingBean.setMaxPrice(MyUtil.formateString2Int(upLoadDatas.getPrice_max()));
        }
        seedlingBean.setIsNego(upLoadDatas.isMeet());
        if (MyUtil.formateString2Int(upLoadDatas.getRepertory_num()) != 0) {
            seedlingBean.setCount(MyUtil.formateString2Int(upLoadDatas.getRepertory_num()));
        }
//                seedling.getUnitTypeName();
        seedlingBean.setUnitTypeName(upLoadDatas.getUnit());//plant 需要根据  tag  来返回 name 来显示
        seedlingBean.setValidity(MyUtil.formateString2Int(upLoadDatas.getValidity()));
        seedlingBean.setRemarks(upLoadDatas.getRemark());


        SaveSeedingGsonBean.DataBean.SeedlingBean.NurseryJsonBean nurseryJsonBean
                = new SaveSeedingGsonBean.DataBean.SeedlingBean.NurseryJsonBean();

        //地址对象
        AdressListActivity.Address address = upLoadDatas.address;
        seedlingBean.setNurseryId(address.addressId);
        nurseryJsonBean.setPhone(address.contactPhone);
        nurseryJsonBean.setRealName(address.contactName);
        seedlingBean.setCityName(address.cityName);
        seedlingBean.setDefault(address.isDefault);
        seedlingBean.setNurseryJson(nurseryJsonBean);

        SaveSeedlingActivity.this.saveSeedingGsonBean.getData().setSeedling(seedlingBean);
        String json = GsonUtil.Bean2Json(SaveSeedlingActivity.this.saveSeedingGsonBean);


        D.e("==json==" + json);
        SPUtil.put(MyApplication.getInstance(), "save_sp", json);


//        // 创建ContentValues对象
//        ContentValues values = new ContentValues();
//        values.put("_id", 1);
//        values.put("json", json);
//
//        SaveSeedlingActivity.this.db.insert("", null, values);

//        D.e("=============" + SaveSeedlingActivity.this.db.insert("areyouok", null, values));

    };


    /**
     * 存储
     */
    private static String DB_NAME = "flower.db";
    private static final String DB_TABLE = "savetable";
    private static final int DB_VERSION = 2;
    private SQLiteDatabase db;
    private String storage_save_id = System.currentTimeMillis() + "";

    /**
     * 静态Helper类，用于建立、更新和打开数据库
     */
    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE = "create table savetable(_id integer primary key autoincrement,img text,title text,time text,money text,storage_save_id text)";
        private static final String DB_CREATE_NEW = "create table areyouok(_id integer primary key autoincrement,_json text)";
        private static final String DB_CREATE_NEW1 = "CREATE TABLE person(personid integer primary key autoincrement, name varchar(20))";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE_NEW1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
                              int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_CREATE_NEW1);
            onCreate(_db);
        }
    }

}
