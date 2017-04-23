package com.hldj.hmyg.saler.purchase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.hhl.library.FlowTagLayout;
import com.hldj.hmyg.R;
import com.hldj.hmyg.application.MyApplication;
import com.hldj.hmyg.base.GlobBaseAdapter;
import com.hldj.hmyg.base.ViewHolders;
import com.hldj.hmyg.bean.QuickPriceGsonBean;
import com.hy.utils.TagAdapter;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.List;

/**
 */
@SuppressLint("ResourceAsColor")
public class PurchasePyApt extends GlobBaseAdapter<QuickPriceGsonBean.DatabeanX.Pagebean.Databean> {

    public PurchasePyApt(Context context, List<QuickPriceGsonBean.DatabeanX.Pagebean.Databean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void setConverView(ViewHolders myViewHolder, QuickPriceGsonBean.DatabeanX.Pagebean.Databean databean, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.list_item_market_price_my, null);

        TextView tv_01 = myViewHolder.getView(R.id.tv_01);
        tv_01.setText(databean.name + "" + databean.num);
        TextView tv_03 = myViewHolder.getView(R.id.tv_03);
        tv_03.setText(databean.cityName);


        TextView tv_04 = myViewHolder.getView(R.id.tv_04);
        tv_04.setText(databean.buyer.companyName);

        FlowTagLayout mobile_flow_layout = myViewHolder.getView(R.id.mobile_flow_layout);

        mobile_flow_layout.setAdapter(new TagAdapter<String>(MyApplication.getInstance()));


    }


}
