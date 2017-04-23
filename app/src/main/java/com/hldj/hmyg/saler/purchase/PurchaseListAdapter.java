package com.hldj.hmyg.saler.purchase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hldj.hmyg.R;
import com.hldj.hmyg.base.GlobBaseAdapter;
import com.hldj.hmyg.base.ViewHolders;
import com.hldj.hmyg.bean.QuickPriceGsonBean;
import com.hy.utils.StringFormatUtil;

import java.util.List;

/**
 */
@SuppressLint("ResourceAsColor")
public class PurchaseListAdapter extends GlobBaseAdapter<QuickPriceGsonBean.DatabeanX.Pagebean.Databean> {

//    private View view1;

    public PurchaseListAdapter(Context context, List<QuickPriceGsonBean.DatabeanX.Pagebean.Databean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void setConverView(ViewHolders myViewHolder, QuickPriceGsonBean.DatabeanX.Pagebean.Databean databean, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.list_item_market_price_my, null);

        TextView tv_01 = myViewHolder.getView(R.id.tv_01);
        tv_01.setText(databean.blurProjectName + "采购单" + "(" + databean.num + ")");
        TextView tv_03 = myViewHolder.getView(R.id.tv_03);
        tv_03.setText(databean.cityName);


        TextView tv_04 = myViewHolder.getView(R.id.tv_04);
        tv_04.setText(databean.buyer.companyName);

//        FlowTagLayout flow_layout = myViewHolder.getView(R.id.mobile_flow_layout);
        LinearLayout lll = myViewHolder.getView(R.id.lllllllll);

//        for (int i = 0; i < databean.itemNameList.size(); i++) {
//
//            View view = LayoutInflater.from(context).inflate(R.layout.tag_item, null);
//            TextView tv = (TextView) view.findViewById(R.id.tv_tag);
//            tv.setText(databean.itemNameList.get(i));
//            lll.addView(view);
//
//        }

        /**
         *   View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);

         TextView textView = (TextView) view.findViewById(R.id.tv_tag);
         */

//        TagAdapter<String> tagAdapter = new TagAdapter(context);
//
//        flow_layout.setAdapter(tagAdapter);
//        tagAdapter.onlyAddAll(databean.itemNameList);


        TextView tv_11 = myViewHolder.getView(R.id.tv_11);
        if (databean.quoteCountJson > 0) {
            StringFormatUtil fillColor = new StringFormatUtil(context, "已有"
                    + databean.quoteCountJson + "条报价", databean.quoteCountJson + "", R.color.red)
                    .fillColor();
            tv_11.setText(fillColor.getResult());
        } else {
            tv_11.setText("暂无报价");
        }

        TextView tv_caozuo01 = myViewHolder.getView(R.id.tv_caozuo01);
        tv_caozuo01.setText("截止时间：" + databean.closeDate);
//


        /**
         *  FlowTagLayout mMobileFlowTagLayout = (FlowTagLayout) inflate
         .findViewById(R.id.mobile_flow_layout);
         // 移动研发标签
         TagAdapter<String> mMobileTagAdapter = new TagAdapter<String>(
         context);
         // mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
         mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);

         mMobileTagAdapter.onlyAddAll(data.get(position).getItemNameList());
         */


    }


}
