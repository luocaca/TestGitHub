package com.hldj.hmyg.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hldj.hmyg.R;
import com.hldj.hmyg.application.MyApplication;
import com.hy.utils.GetServerUrl;
import com.hy.utils.JsonGetInfo;

@SuppressLint("ResourceAsColor")
public class SaveForCartAdapter extends BaseAdapter {
	private static final String TAG = "SaveForCartAdapter";
	private ArrayList<HashMap<String, Object>> data = null;

	private Context context = null;
	private FinalBitmap fb;
	private StringBuffer sb = new StringBuffer();

	public SaveForCartAdapter(Context context,
			ArrayList<HashMap<String, Object>> data) {
		this.data = data;
		this.context = context;
		fb = FinalBitmap.create(context);
		fb.configLoadingImage(R.drawable.no_image_show);
	}

	@Override
	public int getCount() {
		return this.data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.data.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View inflate = LayoutInflater.from(context).inflate(
				R.layout.list_item_save_for_cart, null);
		LinearLayout ll_item_broker = (LinearLayout) inflate
				.findViewById(R.id.ll_item_broker);
		TextView tv_01 = (TextView) inflate.findViewById(R.id.tv_01);
		TextView tv_02 = (TextView) inflate.findViewById(R.id.tv_02);
		TextView tv_03 = (TextView) inflate.findViewById(R.id.tv_03);
		TextView tv_04 = (TextView) inflate.findViewById(R.id.tv_04);
		TextView tv_05 = (TextView) inflate.findViewById(R.id.tv_05);
		tv_01.setText("时间："
				+ data.get(position).get("createDate").toString()
						.substring(0, 10));
		tv_02.setText("单号：" + data.get(position).get("num").toString());
		tv_03.setText("品种："
				+ data.get(position).get("itemCountJson").toString());
		tv_04.setText("费用："
				+ data.get(position).get("validatePrice").toString());
		tv_05.setText("已验苗："
				+ data.get(position).get("verifyedCountJson").toString());
		ll_item_broker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			}
		});

		return inflate;
	}

	public void notify(ArrayList<HashMap<String, Object>> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	private void receiveItem(String id, final int position) {
		// TODO Auto-generated method stub
		FinalHttp finalHttp = new FinalHttp();
		GetServerUrl.addHeaders(finalHttp,true);
		AjaxParams params = new AjaxParams();
		params.put("itemIds", id);
		finalHttp.post(GetServerUrl.getUrl()
				+ "admin/agent/validateApply/receiveItem", params,
				new AjaxCallBack<Object>() {

					@Override
					public void onSuccess(Object t) {
						// TODO Auto-generated method stub
						try {
							JSONObject jsonObject = new JSONObject(t.toString());
							String code = JsonGetInfo.getJsonString(jsonObject,
									"code");
							String msg = JsonGetInfo.getJsonString(jsonObject,
									"msg");
							if (!"".equals(msg)) {
								Toast.makeText(context, msg, Toast.LENGTH_SHORT)
										.show();
							}
							if ("1".equals(code)) {
								data.remove(position);
								notifyDataSetChanged();
							} else if ("6007".equals(code)) {

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						super.onSuccess(t);
					}

					@Override
					public void onFailure(Throwable t, int errorNo,
							String strMsg) {
						// TODO Auto-generated method stub
						Toast.makeText(context, R.string.error_net,
								Toast.LENGTH_SHORT).show();
						super.onFailure(t, errorNo, strMsg);
					}

				});

	}
}
