package com.hldj.hmyg.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hldj.hmyg.R;
import com.hldj.hmyg.buy.bean.StorageSave;
import com.hldj.hmyg.saler.AdressListActivity;
import com.hldj.hmyg.util.D;
import com.hy.utils.ToastUtil;
import com.zf.iosdialog.widget.ActionSheetDialog;
import com.zzy.common.widget.wheelview.popwin.CustomDaysPickPopwin;

/**
 * Created by Administrator on 2017/4/15.
 */

public class SaveSeedingBottomLinearLayout extends LinearLayout {


    Context context;

    public SaveSeedingBottomLinearLayout(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }


    public SaveSeedingBottomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    public SaveSeedingBottomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SaveSeedingBottomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
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

    private SaveSeedingBottomLinearLayout.upLoadDatas upLoadDatas = new SaveSeedingBottomLinearLayout.upLoadDatas();

    //获取上传的数据
    public SaveSeedingBottomLinearLayout.upLoadDatas getUpLoadDatas() {
        return upLoadDatas;
    }

    ActionSheetDialog dialog = null;

    public class ViewHolder {
        public View rootView;
        public EditText tv_save_seeding_price_min;
        public EditText tv_save_seeding_price_max;
        public CheckBox cb_is_meet;
        public EditText et_repertory_num;
        public RelativeLayout rl_save_seeding_unit;
        public RelativeLayout rl_save_seeding_home;
        public RelativeLayout rl_save_seeding_useful;
        public EditText et_remark;
        public TextView tv_save_seeding_unit;
        public TextView tv_save_seeding_useful;
        public TextView tv_save_seeding_home;//废弃
        public LinearLayout list_item_adress;//地址

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_save_seeding_price_min = (EditText) rootView.findViewById(R.id.tv_save_seeding_price_min);
            this.tv_save_seeding_price_max = (EditText) rootView.findViewById(R.id.tv_save_seeding_price_max);
            this.cb_is_meet = (CheckBox) rootView.findViewById(R.id.cb_is_meet);
            this.et_repertory_num = (EditText) rootView.findViewById(R.id.et_repertory_num);

            this.rl_save_seeding_unit = (RelativeLayout) rootView.findViewById(R.id.rl_save_seeding_unit);//单位 点击区域
            this.tv_save_seeding_unit = (TextView) rootView.findViewById(R.id.tv_save_seeding_unit);//单位 显示

            this.rl_save_seeding_useful = (RelativeLayout) rootView.findViewById(R.id.rl_save_seeding_useful);//有效期  点击
            this.tv_save_seeding_useful = (TextView) rootView.findViewById(R.id.tv_save_seeding_useful);//有效期  显示

            this.rl_save_seeding_home = (RelativeLayout) rootView.findViewById(R.id.rl_save_seeding_home);//苗源地  点击区域
            this.tv_save_seeding_home = (TextView) rootView.findViewById(R.id.tv_save_seeding_home);//苗源地  显示（废弃）
            this.list_item_adress = (LinearLayout) rootView.findViewById(R.id.list_item_adress);//苗源地显示

            this.et_remark = (EditText) rootView.findViewById(R.id.et_remark);


            this.cb_is_meet.setOnClickListener(v ->
                    {

                        if (holder.cb_is_meet.isChecked()) {
                            holder.tv_save_seeding_price_max.setText("");
                            holder.tv_save_seeding_price_max.setFocusable(false);
                            holder.tv_save_seeding_price_min.setFocusable(false);
                            holder.tv_save_seeding_price_min.setText("");
                        } else {
                            holder.tv_save_seeding_price_max.setFocusable(true);
                            holder.tv_save_seeding_price_min.setFocusable(true);
                        }


                    }
            );

            upLoadDatas = new upLoadDatas();

            {
                OnClickListener onClickListener = v -> {
                    D.e("=========苗原地点击===========");
                    AdressListActivity.start2AdressListActivity(context, upLoadDatas.address.addressId, "SaveSeedlingActivity", address -> {
                        D.e("===========返回的地址==========" + address);
                        initAddressView(rootView, address);
                        upLoadDatas.address = address;
                    });


                };
                this.rl_save_seeding_home.setOnClickListener(onClickListener);
                this.list_item_adress.setOnClickListener(onClickListener);
            }


            //单位点击时间
            this.rl_save_seeding_unit.setOnClickListener(v -> {

                if (dialog == null) {
                    dialog = new ActionSheetDialog(context)
                            .builder()
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .setTitle("单位")
                            .initItemsAndAddListener(ib -> {
                                D.e("==========单位返回的对象===============" + ib.toString());
                                holder.tv_save_seeding_unit.setText(ib.value);//key 用于上传 后台数据接口
                                holder.tv_save_seeding_unit.setTag(ib.key);//将 key 放入tag  方便取值
//                              upLoadDatas.unit = ib.key;
                            });
                }
                dialog.show();


            });


            //有效期
            this.rl_save_seeding_useful.setOnClickListener(v -> {

                CustomDaysPickPopwin daysPopwin = new CustomDaysPickPopwin(
                        context, (dayType, pos) -> {
                    if (dayType == 0) {
                        holder.tv_save_seeding_useful.setText(Short.valueOf(days[pos]) + "天");
//                        upLoadDatas.validity = days[pos];
                    } else {
                        holder.tv_save_seeding_useful.setText(Short.valueOf(customDays[pos]) + "天");
//                        upLoadDatas.validity = customDays[pos];
                    }
                }, days, customDays, 0);
                daysPopwin.showAtLocation(holder.rootView, Gravity.BOTTOM
                        | Gravity.CENTER, 0, 0);

            });

        }

    }

    /**
     * 初始化地址栏
     *
     * @param rootView 根布局
     * @param address  地址对象
     */
    private void initAddressView(View rootView, AdressListActivity.Address address) {
        ((TextView) rootView.findViewById(R.id.tv_name)).setText(address.contactName);// 名字
        if (address.isDefault)
            ((TextView) rootView.findViewById(R.id.tv_is_defoloat)).setVisibility(View.VISIBLE);//默认就显示默认地址
        ((TextView) rootView.findViewById(R.id.tv_address_name)).setText(address.cityName); //地址名称
    }

    /**
     * 地址选择后回调接口
     */
    public interface onAddressSelectListener {
        void onAddressSelect(AdressListActivity.Address address);
    }

    //用于上传的数据
    public class upLoadDatas {
        private String unit = "";//单位
        private String validity = "";//有效期
        private String price_min = "";//最小价格
        private String price_max = "";//最大价格
        private boolean isMeet; // 是否面议
        private String repertory_num = "";//库存
        private String remark = "";//备注
        public AdressListActivity.Address address = new AdressListActivity.Address();//苗源地

        public String getUnit() {

            if (null == holder.tv_save_seeding_unit.getTag()) {
                return "plant";//默认为株
            }

            return holder.tv_save_seeding_unit.getTag().toString();
        }

        public String getValidity() {
            String str = holder.tv_save_seeding_useful.getText().toString();

            return str.substring(0, str.length() - 1);
        }

        public boolean isMeet() {
            return holder.cb_is_meet.isChecked();
        }

        public String getPrice_min() {
            return holder.tv_save_seeding_price_min.getText().toString();
        }

        public String getPrice_max() {
            return holder.tv_save_seeding_price_max.getText().toString();
        }

        public String getRepertory_num() {
            return holder.et_repertory_num.getText().toString();
        }

        public String checkNoNull(String str) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            } else {
                ToastUtil.showShortToast("不能为空");
                return "";
            }
        }


        public String getRemark() {
            return checkNoNull(holder.et_remark.getText().toString());
        }


    }

    private String[] days = {"30", "90", "180"};

    private String[] customDays = {"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41",
            "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
            "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63",
            "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74",
            "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85",
            "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96",
            "97", "98", "99", "100", "101", "102", "103", "104", "105", "106",
            "107", "108", "109", "110", "111", "112", "113", "114", "115",
            "116", "117", "118", "119", "120", "121", "122", "123", "124",
            "125", "126", "127", "128", "129", "130", "131", "132", "133",
            "134", "135", "136", "137", "138", "139", "140", "141", "142",
            "143", "144", "145", "146", "147", "148", "149", "150", "151",
            "152", "153", "154", "155", "156", "157", "158", "159", "160",
            "161", "162", "163", "164", "165", "166", "167", "168", "169",
            "170", "171", "172", "173", "174", "175", "176", "177", "178",
            "179", "180"};


}
