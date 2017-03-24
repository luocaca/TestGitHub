package com.hldj.hmyg.buyer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import me.imid.swipebacklayout.lib.app.NeedSwipeBackActivity;
import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hldj.hmyg.R;
import com.hldj.hmyg.application.Data;
import com.hldj.hmyg.application.MyApplication;
import com.hy.utils.GetServerUrl;
import com.hy.utils.JsonGetInfo;
import com.zzy.flowers.widget.popwin.EditP2;

public class OrderReceiptListActivity extends NeedSwipeBackActivity implements
		IXListViewListener {
	private XListView xListView;
	private ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();

	boolean getdata; // 避免刷新多出数据
	private ReceiptListAdapter listAdapter;

	private int pageSize = 20;
	private int pageIndex = 0;
	private MultipleClickProcess multipleClickProcess;
	private String id = "";
	private String status = "";
	private String from = "";
	private int allowReceiptInfoCount = 0;
	private TextView tv_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_receipt_list);
		if (getIntent().getStringExtra("id") != null) {
			id = getIntent().getStringExtra("id");
		}
		if (getIntent().getStringExtra("status") != null) {
			status = getIntent().getStringExtra("status");
		}
		if (getIntent().getStringExtra("from") != null) {
			from = getIntent().getStringExtra("from");
		}
		ImageView btn_back = (ImageView) findViewById(R.id.btn_back);
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_add = (TextView) findViewById(R.id.tv_add);

		multipleClickProcess = new MultipleClickProcess();
		xListView = (XListView) findViewById(R.id.xlistView);
		xListView.setDivider(null);
		xListView.setPullLoadEnable(false);
		xListView.setPullRefreshEnable(false);
		xListView.setXListViewListener(this);
		btn_back.setOnClickListener(multipleClickProcess);
		tv_add.setOnClickListener(multipleClickProcess);
		if ("BuyerOrderDetailActivity".equals(from)) {
			if ("unpay".equals(status) || "unsend".equals(status)) {
				tv_add.setVisibility(View.VISIBLE);
			}
		}

		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		getdata = false;
		FinalHttp finalHttp = new FinalHttp();
		GetServerUrl.addHeaders(finalHttp,true);
		AjaxParams params = new AjaxParams();
		params.put("orderId", id);
		finalHttp.post(GetServerUrl.getUrl()
				+ "admin/buyer/order/getReceiptList", params,
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
								// Toast.makeText(OrderReceiptListActivity.this,
								// msg, Toast.LENGTH_SHORT).show();
							}
							if ("1".equals(code)) {
								JSONObject jsonObject2 = jsonObject
										.getJSONObject("data");
								allowReceiptInfoCount = JsonGetInfo.getJsonInt(
										jsonObject2, "allowReceiptInfoCount");
								if (JsonGetInfo.getJsonArray(jsonObject2,
										"receiptList").length() > 0) {
									JSONArray jsonArray_cartList = JsonGetInfo
											.getJsonArray(jsonObject2,
													"receiptList");
									for (int j = 0; j < jsonArray_cartList
											.length(); j++) {
										JSONObject jsonObject4 = jsonArray_cartList
												.getJSONObject(j);
										HashMap<String, Object> products_hash = new HashMap<String, Object>();
										products_hash.put("status", status);
										products_hash.put("from", from);
										products_hash.put("orderId", id);
										products_hash.put("id", JsonGetInfo
												.getJsonString(jsonObject4,
														"id"));
										products_hash.put("receiptDate",
												JsonGetInfo.getJsonString(
														jsonObject4,
														"receiptDate"));
										products_hash.put("orderName",
												JsonGetInfo.getJsonInt(
														jsonObject4,
														"orderName"));
										products_hash
												.put("remarks", JsonGetInfo
														.getJsonString(
																jsonObject4,
																"remarks"));
										products_hash.put("receiptDate",
												JsonGetInfo.getJsonString(
														jsonObject4,
														"receiptDate"));
										products_hash.put("count", JsonGetInfo
												.getJsonInt(jsonObject4,
														"count"));
										products_hash.put(
												"contactName",
												JsonGetInfo.getJsonString(
														JsonGetInfo
																.getJSONObject(
																		jsonObject4,
																		"receiptAddressJson"),
														"contactName"));
										products_hash
												.put("contactPhone",
														JsonGetInfo.getJsonString(
																JsonGetInfo
																		.getJSONObject(
																				jsonObject4,
																				"receiptAddressJson"),
																"contactPhone"));
										products_hash
												.put("receiptAddressId",
														JsonGetInfo.getJsonString(
																JsonGetInfo
																		.getJSONObject(
																				jsonObject4,
																				"receiptAddressJson"),
																"id"));
										products_hash.put(
												"fullAddress",
												JsonGetInfo.getJsonString(
														JsonGetInfo
																.getJSONObject(
																		jsonObject4,
																		"receiptAddressJson"),
														"fullAddress"));
										products_hash.put(
												"allowReceiptInfoCount",
												allowReceiptInfoCount + "");

										datas.add(products_hash);
										if (listAdapter != null) {
											listAdapter.notifyDataSetChanged();
										}
									}
									if (listAdapter == null) {
										listAdapter = new ReceiptListAdapter(
												OrderReceiptListActivity.this,
												datas);
										xListView.setAdapter(listAdapter);
									}

									pageIndex++;

								}

							} else {

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
						Toast.makeText(OrderReceiptListActivity.this,
								R.string.error_net, Toast.LENGTH_SHORT).show();
						super.onFailure(t, errorNo, strMsg);
					}

				});
		getdata = true;
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		xListView.setPullLoadEnable(false);
		pageIndex = 0;
		datas.clear();
		if (listAdapter == null) {
			listAdapter = new ReceiptListAdapter(OrderReceiptListActivity.this,
					datas);

			xListView.setAdapter(listAdapter);
		} else {
			listAdapter.notifyDataSetChanged();
		}
		if (getdata == true) {
			initData();
		}
		onLoad();
	}

	@Override
	public void onLoadMore() {
		xListView.setPullRefreshEnable(false);
		initData();
		onLoad();
	}

	private void onLoad() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				xListView.stopRefresh();
				xListView.stopLoadMore();
				xListView.setRefreshTime(new Date().toLocaleString());
				xListView.setPullLoadEnable(false);
				xListView.setPullRefreshEnable(false);

			}
		}, com.hldj.hmyg.application.Data.refresh_time);

	}

	public class MultipleClickProcess implements OnClickListener {
		private boolean flag = true;
		private EditP2 popwin;

		private synchronized void setFlag() {
			flag = false;
		}

		public void onClick(View view) {
			if (flag) {
				switch (view.getId()) {
				case R.id.btn_back:
					onBackPressed();
					break;
				case R.id.tv_add:
					// 添加收货要求
					Intent toSaveReceipptActivity = new Intent(
							OrderReceiptListActivity.this,
							SaveReceipptActivity.class);
					toSaveReceipptActivity.putExtra("orderId", id);
					toSaveReceipptActivity.putExtra("allowReceiptInfoCount",
							allowReceiptInfoCount);
					startActivityForResult(toSaveReceipptActivity, 1);
					break;
				default:
					break;
				}
				setFlag();
				// do some things
				new TimeThread().start();
			}
		}

		/**
		 * 计时线程（防止在一定时间段内重复点击按钮）
		 */
		private class TimeThread extends Thread {
			public void run() {
				try {
					Thread.sleep(Data.loading_time);
					flag = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == 1) {
			onRefresh();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
