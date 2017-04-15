package com.zzy.common.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hldj.hmyg.adapter.PublishFlowerInfoPhotoAdapter;
import com.hldj.hmyg.application.PermissionUtils;
import com.hldj.hmyg.bean.Pic;
import com.hldj.hmyg.saler.FlowerInfoPhotoChoosePopwin2;
import com.hldj.hmyg.util.D;
import com.hy.utils.ToastUtil;
import com.zzy.flowers.activity.photoalbum.EditGalleryImageActivity;

import java.util.ArrayList;

public class MeasureGridView extends GridView {
    public static Context context;
    /**
     * 照片适配器
     */
    private static PublishFlowerInfoPhotoAdapter adapter;

    public MeasureGridView(Context context, AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;


    }

    public MeasureGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;


    }

    public MeasureGridView(Context context) {
        super(context);
        this.context = context;

    }

    String usrl = "https://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3D450%2C600/sign=dce89ee56a81800a6eb0810a84051fcc/5243fbf2b2119313bdce98da63380cd790238dce.jpg";

    static PhotoGvOnItemClickListener gvOnItemClickListener;

    public void init(Context context, ArrayList<Pic> urlPaths, PhotoGvOnItemClickListener gvOnItemClickListener) {


        adapter = new PublishFlowerInfoPhotoAdapter(context, urlPaths);
        this.setAdapter(adapter);

        this.gvOnItemClickListener = gvOnItemClickListener;
        this.setOnItemClickListener(gvOnItemClickListener);


    }


    public void setImageNumColumns(int num) {
        setNumColumns(num);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    private static FlowerInfoPhotoChoosePopwin2 popwin;

    //自定义点击事件
    public static class PhotoGvOnItemClickListener implements OnItemClickListener {
        View mainView;

        public PhotoGvOnItemClickListener(View mainView) {
            this.mainView = mainView;
        }

        @Override
        public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
            if (position == adapter.getUrlPathsCount()) {
                boolean requestCamerPermissions = new PermissionUtils((Activity) context).requestCamerPermissions(200);
                if (!requestCamerPermissions) {
                    ToastUtil.showShortToast("您未同意拍照权限");
                    return;
                }
                boolean requestReadSDCardPermissions = new PermissionUtils((Activity) context).requestReadSDCardPermissions(200);
                if (!requestReadSDCardPermissions) {
                    ToastUtil.showShortToast("您未同意应用读取SD卡权限");
                    return;
                }
                popwin = new FlowerInfoPhotoChoosePopwin2((Activity) context, new FlowerInfoPhotoChoosePopwin2.onPhotoStateChangeListener() {
                    @Override
                    public void onTakePic() {
                        D.e("=======拍照=======");
                        gvOnItemClickListener.onItemClick(parent, view, position, id);
                    }

                    @Override
                    public void onChoosePic() {
                        D.e("=======相册=======");
                        gvOnItemClickListener.onItemClick(parent, view, position, id);
                    }

                    @Override
                    public void onCancle() {
                        D.e("=======取消=======");
                        gvOnItemClickListener.onItemClick(parent, view, position, id);
                    }
                });
                popwin.showAtLocation(mainView, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
            } else {
                EditGalleryImageActivity.startEditGalleryImageActivity(
                        (Activity) context,
                        EditGalleryImageActivity.TO_EDIT_PUBLISH_IMAGE,
                        position, adapter.getDataList());
            }

        }
    }


}
