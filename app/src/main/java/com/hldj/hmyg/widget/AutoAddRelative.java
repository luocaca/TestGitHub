package com.hldj.hmyg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hldj.hmyg.R;
import com.hldj.hmyg.bean.SaveSeedingGsonBean;
import com.neopixl.pixlui.components.relativelayout.RelativeLayout;

/**
 * Created by Administrator on 2017/4/15.
 */

public class AutoAddRelative extends RelativeLayout {

    private ViewHolder viewHolder_add;
    private ViewHolder_rd viewHolder_rd;
    private ViewHolder_top viewHolder_top;
    Context context;

    public AutoAddRelative(Context context) {
        super(context);
        this.context = context;
    }

    public AutoAddRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoAddRelative(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setDatas(SaveSeedingGsonBean.DataBean.TypeListBean.ParamsListBean paramsListBean) {
        viewHolder_add.tv_auto_add_left1.setText(funnel(paramsListBean.getValue()));
        if (!paramsListBean.isRequired())
            viewHolder_add.tv_auto_add_left2.setText("(选填)");//不是必须的话 写选填
    }

    private String funnel(String value) {
        switch (value) {
            case "crown":
                return "冠幅";
            case "diameter":
                return "胸径";
            case "height":
                return "高度";
            case "dbh":
                return "土球";
            case "offbarHeight":
                return "土球";

        }
        return value;

    }

    public void setDatas_rd(SaveSeedingGsonBean.DataBean.TypeListBean.ParamsListBean paramsListBean) {
        viewHolder_rd.tv_auto_add_left1.setText(paramsListBean.getValue());
//        if (!paramsListBean.isRequired())
//            viewHolder_rd.tv_auto_add_left2.setText("(选填)");//不是必须的话 写选填
    }

//    int layoutId;

    public AutoAddRelative initView(int layoutId) {
//        this.layoutId = layoutId;
        View view = LayoutInflater.from(context).inflate(layoutId, this);

        switch (layoutId) {
            case R.layout.save_seeding_auto_add:
                viewHolder_add = new ViewHolder(view);
                break;
            case R.layout.save_seeding_auto_add_radio:
                viewHolder_rd = new ViewHolder_rd(view);
                break;
            case R.layout.save_seeding_auto_add_top:
                viewHolder_top = new ViewHolder_top(view);
                break;
        }
        return this;
    }


    public ViewHolder getViewHolder() {

        return viewHolder_add;
    }

    public ViewHolder_rd getViewHolder_rd() {

        return viewHolder_rd;
    }

    public ViewHolder_top getViewHolder_top() {

        return viewHolder_top;
    }


    private static class ViewHolder {
        public View rootView;
        public TextView tv_auto_add_left1;
        public TextView tv_auto_add_left2;
        public EditText et_auto_add_min;
        public EditText et_auto_add_max;
        public LinearLayout linearLayout2;
        public TextView et_auto_add_unit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_auto_add_left1 = (TextView) rootView.findViewById(R.id.tv_auto_add_left1);
            this.tv_auto_add_left2 = (TextView) rootView.findViewById(R.id.tv_auto_add_left2);
            this.et_auto_add_min = (EditText) rootView.findViewById(R.id.et_auto_add_min);
            this.et_auto_add_max = (EditText) rootView.findViewById(R.id.et_auto_add_max);
            this.linearLayout2 = (LinearLayout) rootView.findViewById(R.id.linearLayout2);
            this.et_auto_add_unit = (TextView) rootView.findViewById(R.id.et_auto_add_unit);
        }

    }

    public static class ViewHolder_rd {
        public View rootView;
        public TextView tv_auto_add_left1;
        public TextView tv_auto_add_left2;
        public EditText et_auto_add_min;
        public EditText et_auto_add_max;
        public LinearLayout linearLayout2;
        public TextView et_auto_add_unit;
        public RadioButton rb_auto_add_left;
        public RadioButton rb_auto_add_center;
        public RadioButton rb_auto_add_right;

        public ViewHolder_rd(View rootView) {
            this.rootView = rootView;
            this.tv_auto_add_left1 = (TextView) rootView.findViewById(R.id.tv_auto_add_left1);
            this.tv_auto_add_left2 = (TextView) rootView.findViewById(R.id.tv_auto_add_left2);
            this.et_auto_add_min = (EditText) rootView.findViewById(R.id.et_auto_add_min);
            this.et_auto_add_max = (EditText) rootView.findViewById(R.id.et_auto_add_max);
            this.linearLayout2 = (LinearLayout) rootView.findViewById(R.id.linearLayout2);
            this.et_auto_add_unit = (TextView) rootView.findViewById(R.id.et_auto_add_unit);
            this.rb_auto_add_left = (RadioButton) rootView.findViewById(R.id.rb_auto_add_left);
            this.rb_auto_add_center = (RadioButton) rootView.findViewById(R.id.rb_auto_add_center);
            this.rb_auto_add_right = (RadioButton) rootView.findViewById(R.id.rb_auto_add_right);
        }

    }

    public static class ViewHolder_top {
        public View rootView;
        public TextView tv_auto_add_left1;
        public EditText tv_auto_add_name;

        public ViewHolder_top(View rootView) {
            this.rootView = rootView;
            this.tv_auto_add_left1 = (TextView) rootView.findViewById(R.id.tv_auto_add_left1);
            this.tv_auto_add_name = (EditText) rootView.findViewById(R.id.tv_auto_add_name);
        }

    }
}
