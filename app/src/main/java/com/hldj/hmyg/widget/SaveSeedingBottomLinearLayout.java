package com.hldj.hmyg.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hldj.hmyg.R;
import com.hldj.hmyg.buy.bean.StorageSave;
import com.hldj.hmyg.util.D;

/**
 * Created by Administrator on 2017/4/15.
 */

public class SaveSeedingBottomLinearLayout extends LinearLayout {

    public SaveSeedingBottomLinearLayout(Context context) {
        super(context);
        initView(context);
    }


    public SaveSeedingBottomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SaveSeedingBottomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SaveSeedingBottomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {

        View rootVoiew = LayoutInflater.from(context).inflate(R.layout.include_seeding_bottom, this);

        holder = new ViewHolder(rootVoiew);

    }

    public void setDatas(StorageSave fromJson) {
        D.e("hellow world");
        holder.et_remark.setText(fromJson.getRemarks());


    }

    public ViewHolder getHolder() {
        return holder;
    }


    ViewHolder holder;

    public static class ViewHolder {
        public View rootView;
        public EditText tv_save_seeding_price_min;
        public EditText tv_save_seeding_price_max;
        public CheckBox cb_is_meet;
        public EditText et_repertory_num;
        public RelativeLayout rl_save_seeding_unit;
        public RelativeLayout rl_save_seeding_home;
        public RelativeLayout rl_save_seeding_useful;
        public EditText et_remark;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_save_seeding_price_min = (EditText) rootView.findViewById(R.id.tv_save_seeding_price_min);
            this.tv_save_seeding_price_max = (EditText) rootView.findViewById(R.id.tv_save_seeding_price_max);
            this.cb_is_meet = (CheckBox) rootView.findViewById(R.id.cb_is_meet);
            this.et_repertory_num = (EditText) rootView.findViewById(R.id.et_repertory_num);
            this.rl_save_seeding_unit = (RelativeLayout) rootView.findViewById(R.id.rl_save_seeding_unit);
            this.rl_save_seeding_home = (RelativeLayout) rootView.findViewById(R.id.rl_save_seeding_home);
            this.rl_save_seeding_useful = (RelativeLayout) rootView.findViewById(R.id.rl_save_seeding_useful);
            this.et_remark = (EditText) rootView.findViewById(R.id.et_remark);
        }

    }
}
