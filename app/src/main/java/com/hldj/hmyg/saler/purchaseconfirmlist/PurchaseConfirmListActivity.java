package com.hldj.hmyg.saler.purchaseconfirmlist;

import info.hoang8f.android.segmented.SegmentedGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import me.drakeet.materialdialog.MaterialDialog;
import me.kaede.tagview.OnTagDeleteListener;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;
import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hldj.hmyg.R;
import com.hldj.hmyg.application.MyApplication;
import com.hldj.hmyg.jimiao.SellectMiaoActivity;
import com.hldj.hmyg.store.TypeEx;
import com.hy.utils.GetServerUrl;
import com.hy.utils.JsonGetInfo;
import com.mrwujay.cascade.activity.BaseSecondActivity;
import com.mrwujay.cascade.activity.GetCodeByName;

@SuppressLint("NewApi")
public class PurchaseConfirmListActivity extends BaseSecondActivity implements
		IXListViewListener,
		com.huewu.pla.lib.me.maxwin.view.PLAXListView.IXListViewListener,
		OnCheckedChangeListener, OnWheelChangedListener {

	private RelativeLayout rl_choose_type;
	private ImageView iv_seller_arrow2;
	private ImageView iv_seller_arrow3;
	private XListView xListView;
	private String orderBy = "";
	private String priceSort = "";
	private String publishDateSort = "";
	private ArrayList<PurchaseConfirm> datas = new ArrayList<PurchaseConfirm>();
	private int pageSize = 10;
	private int pageIndex = 0;
	private PurchaseConfirmListAdapter listAdapter;
	boolean getdata; // 避免刷新多出数据
	private String noteType = "1";
	FinalHttp finalHttp = new FinalHttp();
	private String minSpec = "";
	private String maxSpec = "";
	private String minHeight = "";
	private String maxHeight = "";
	private String minCrown = "";
	private String maxCrown = "";
	private String name = "";
	private String cityCode = "";
	private String cityName = "";
	private Dialog dialog;
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	private TagView tagView;
	public int i = 0;
	MaterialDialog mMaterialDialog;
	private boolean isStatus;
	private TextView id_tv_edit_all;
	SellectForPurchase sPurchase = new SellectForPurchase("", "", 0, 0);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_confirm_list);
		mMaterialDialog = new MaterialDialog(this);
		ImageView btn_back = (ImageView) findViewById(R.id.btn_back);
		SegmentedGroup segmented3 = (SegmentedGroup) findViewById(R.id.segmented3);
		RadioButton button31 = (RadioButton) findViewById(R.id.button31);
		RadioButton button32 = (RadioButton) findViewById(R.id.button32);
		button31.setChecked(true);
		segmented3.setOnCheckedChangeListener(this);
		rl_choose_type = (RelativeLayout) findViewById(R.id.rl_choose_type);
		RelativeLayout rl_choose_price = (RelativeLayout) findViewById(R.id.rl_choose_price);
		RelativeLayout rl_choose_time = (RelativeLayout) findViewById(R.id.rl_choose_time);
		RelativeLayout rl_choose_screen = (RelativeLayout) findViewById(R.id.rl_choose_screen);
		iv_seller_arrow2 = (ImageView) findViewById(R.id.iv_seller_arrow2);
		iv_seller_arrow3 = (ImageView) findViewById(R.id.iv_seller_arrow3);
		id_tv_edit_all = (TextView) findViewById(R.id.id_tv_edit_all);
		xListView = (XListView) findViewById(R.id.xlistView);
		xListView.setPullLoadEnable(true);
		xListView.setPullRefreshEnable(true);
		xListView.setXListViewListener(this);
		tagView = (TagView) this.findViewById(R.id.tagview);
		tagView.setOnTagDeleteListener(new OnTagDeleteListener() {

			@Override
			public void onTagDeleted(int position, me.kaede.tagview.Tag tag) {
				// TODO Auto-generated method stub
				if (tag.id == 1) {
					cityCode = "";
					onRefresh();
				} else if (tag.id == 2) {
					name = "";
					onRefresh();
				} else if (tag.id == 3) {
					minSpec = "";
					maxSpec = "";
					onRefresh();
				} else if (tag.id == 4) {
					minHeight = "";
					maxHeight = "";
					onRefresh();
				} else if (tag.id == 5) {
					minCrown = "";
					maxCrown = "";
					onRefresh();
				}

			}
		});
		initData();

		MultipleClickProcess multipleClickProcess = new MultipleClickProcess();
		rl_choose_type.setOnClickListener(multipleClickProcess);
		rl_choose_price.setOnClickListener(multipleClickProcess);
		rl_choose_time.setOnClickListener(multipleClickProcess);
		rl_choose_screen.setOnClickListener(multipleClickProcess);
		btn_back.setOnClickListener(multipleClickProcess);
		id_tv_edit_all.setOnClickListener(multipleClickProcess);

	}

	public class MultipleClickProcess implements OnClickListener {
		private boolean flag = true;

		private synchronized void setFlag() {
			flag = false;
		}

		public void onClick(View view) {
			if (flag) {
				switch (view.getId()) {
				case R.id.btn_back:
					onBackPressed();
					break;
				case R.id.rl_choose_type:
					showCitys();
					break;
				case R.id.rl_choose_price:
					if ("".equals(priceSort)) {
						priceSort = "price_asc";
						iv_seller_arrow2
								.setImageResource(R.drawable.icon_seller_arrow2);
					} else if ("price_asc".equals(priceSort)) {
						priceSort = "price_desc";
						iv_seller_arrow2
								.setImageResource(R.drawable.icon_seller_arrow3);
					} else if ("price_desc".equals(priceSort)) {
						priceSort = "";
						iv_seller_arrow2
								.setImageResource(R.drawable.icon_seller_arrow1);
					}
					onRefresh();
					break;
				case R.id.rl_choose_time:
					if ("".equals(publishDateSort)) {
						publishDateSort = "createDate_asc";
						iv_seller_arrow3
								.setImageResource(R.drawable.icon_seller_arrow2);
					} else if ("createDate_asc".equals(publishDateSort)) {
						publishDateSort = "createDate_desc";
						iv_seller_arrow3
								.setImageResource(R.drawable.icon_seller_arrow3);
					} else if ("createDate_desc".equals(publishDateSort)) {
						publishDateSort = "";
						iv_seller_arrow3
								.setImageResource(R.drawable.icon_seller_arrow1);
					}
					onRefresh();
					break;
				case R.id.rl_choose_screen:
					Intent toSellectActivity = new Intent(
							PurchaseConfirmListActivity.this,
							SellectMiaoActivity.class);
					toSellectActivity.putExtra("minHeight", minHeight);
					toSellectActivity.putExtra("maxHeight", maxHeight);
					toSellectActivity.putExtra("minCrown", minCrown);
					toSellectActivity.putExtra("maxCrown", maxCrown);
					toSellectActivity.putExtra("minSpec", minSpec);
					toSellectActivity.putExtra("maxSpec", maxSpec);
					toSellectActivity.putExtra("name", name);
					startActivityForResult(toSellectActivity, 1);
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_right);
					break;
				case R.id.id_tv_edit_all:
					Intent intent = new Intent(
							PurchaseConfirmListActivity.this,
							SellectForPurchaseConfirmActivity.class);
					Bundle mBundle = new Bundle();
					mBundle.putSerializable("SellectForPurchase", sPurchase);
					intent.putExtras(mBundle);
					startActivityForResult(intent, 1);
					// 筛选
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
					Thread.sleep(200);
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
		if (resultCode == 9) {
			minHeight = data.getStringExtra("minHeight");
			maxHeight = data.getStringExtra("maxHeight");
			minCrown = data.getStringExtra("minCrown");
			maxCrown = data.getStringExtra("maxCrown");
			minSpec = data.getStringExtra("minSpec");
			maxSpec = data.getStringExtra("maxSpec");
			name = data.getStringExtra("name");
			List<Tag> tags = tagView.getTags();
			for (int i = 0; i < tags.size(); i++) {
				if (tags.get(i).id == 2 || tags.get(i).id == 3
						|| tags.get(i).id == 4 || tags.get(i).id == 5) {
					tagView.remove(i);
				}
			}
			if (!"".equals(name)) {
				me.kaede.tagview.Tag tag = new me.kaede.tagview.Tag(name);
				tag.layoutColor = getResources().getColor(R.color.main_color);
				tag.isDeletable = true;
				tag.id = 2; // 1 城市 2.品名 3.规格 4.高度 5.冠幅
				tagView.addTag(tag);
			}
			if (!"".equals(minSpec) || !"".equals(maxSpec)) {
				me.kaede.tagview.Tag tag = new me.kaede.tagview.Tag("规格："
						+ minSpec + "-" + maxSpec);
				tag.layoutColor = getResources().getColor(R.color.main_color);
				tag.isDeletable = true;
				tag.id = 3; // 1 城市 2.品名 3.规格 4.高度 5.冠幅
				tagView.addTag(tag);
			}
			if (!"".equals(minHeight) || !"".equals(maxHeight)) {
				me.kaede.tagview.Tag tag = new me.kaede.tagview.Tag("高度："
						+ minHeight + "-" + maxHeight);
				tag.layoutColor = getResources().getColor(R.color.main_color);
				tag.isDeletable = true;
				tag.id = 4; // 1 城市 2.品名 3.规格 4.高度 5.冠幅
				tagView.addTag(tag);
			}
			if (!"".equals(minCrown) || !"".equals(maxCrown)) {
				me.kaede.tagview.Tag tag = new me.kaede.tagview.Tag("冠幅："
						+ minCrown + "-" + maxCrown);
				tag.layoutColor = getResources().getColor(R.color.main_color);
				tag.isDeletable = true;
				tag.id = 5; // 1 城市 2.品名 3.规格 4.高度 5.冠幅
				tagView.addTag(tag);
			}
			onRefresh();
		} else if (resultCode == 8) {
			onRefresh();
		}else if (resultCode == 13) {
			Bundle bundle = data.getExtras();
			if (bundle.get("SellectForPurchase") != null) {
				sPurchase = (SellectForPurchase) bundle.get("SellectForPurchase");
			}
			onRefresh();
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		xListView.setPullLoadEnable(false);
		pageIndex = 0;
		datas.clear();
		if (listAdapter == null) {
			listAdapter = new PurchaseConfirmListAdapter(
					PurchaseConfirmListActivity.this, datas);
			xListView.setAdapter(listAdapter);
		} else {
			listAdapter.notifyDataSetChanged();
		}
		if (getdata == true) {
			initData();
		}
		onLoad();
	}

	private void initData() {
		// TODO Auto-generated method stub
		getdata = false;
		GetServerUrl.addHeaders(finalHttp,true);
		AjaxParams params = new AjaxParams();
		params.put("pageSize", pageSize + "");
		params.put("pageIndex", pageIndex + "");
		params.put("isStatus", isStatus + "");
		params.put("startDate",sPurchase.getStr_startDate());
		params.put("endDate", sPurchase.getStr_endDate());
		// if ("".equals(priceSort) && !"".equals(publishDateSort)) {
		// orderBy = publishDateSort;
		// } else if (!"".equals(priceSort) && "".equals(publishDateSort)) {
		// orderBy = priceSort;
		// } else if ("".equals(priceSort) && "".equals(publishDateSort)) {
		// orderBy = "";
		// } else {
		// orderBy = priceSort + "," + publishDateSort;
		// }
		//
		// if (orderBy.endsWith(",")) {
		// orderBy = orderBy.substring(0, orderBy.length() - 1);
		// }
		// params.put("orderBy", orderBy);
		// params.put("noteType", noteType);
		// params.put("minSpec", minSpec);
		// params.put("maxSpec", maxSpec);
		// params.put("minHeight", minHeight);
		// params.put("maxHeight", maxHeight);
		// params.put("minCrown", minCrown);
		// params.put("maxCrown", maxCrown);
		// params.put("name", name);
		// params.put("cityCode", cityCode);
		finalHttp.post(GetServerUrl.getUrl() + "admin/purchaseConfirm/list",
				params, new AjaxCallBack<Object>() {

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
							}
							if ("1".equals(code)) {

								JSONObject data = JsonGetInfo.getJSONObject(
										jsonObject, "data");
								JSONObject jsonObject2 = JsonGetInfo
										.getJSONObject(data, "page");
								int total = JsonGetInfo.getJsonInt(jsonObject2,
										"total");
								if (JsonGetInfo.getJsonArray(jsonObject2,
										"data").length() > 0) {
									JSONArray jsonArray = JsonGetInfo
											.getJsonArray(jsonObject2, "data");
									for (int i = 0; i < jsonArray.length(); i++) {
										JSONObject jsonObject3 = jsonArray
												.getJSONObject(i);
										PurchaseConfirm pConfirm = new PurchaseConfirm(
												JsonGetInfo.getJsonString(
														jsonObject3, "id"),
												JsonGetInfo
														.getJsonString(
																jsonObject3,
																"createBy"),
												JsonGetInfo.getJsonString(
														jsonObject3,
														"createDate"),
												JsonGetInfo.getJsonString(
														jsonObject3, "name"),
												JsonGetInfo.getJsonString(
														jsonObject3,
														"contactName"),
												JsonGetInfo.getJsonString(
														jsonObject3,
														"contactPhone"),
												JsonGetInfo.getJsonString(
														jsonObject3,
														"purchaseDate"),
												JsonGetInfo.getJsonString(
														jsonObject3, "status"),
												JsonGetInfo
														.getJsonString(
																jsonObject3,
																"sellerId"),
												JsonGetInfo.getJsonLong(
														jsonObject3,
														"confirmDate"),
												JsonGetInfo.getJsonString(
														jsonObject3,
														"statusName"),
												JsonGetInfo.getJsonDouble(
														jsonObject3,
														"totalJson"),
												JsonGetInfo
														.getJsonString(
																jsonObject3,
																"spceText"));
										datas.add(pConfirm);
										if (listAdapter != null) {
											listAdapter.notifyDataSetChanged();
										}
									}

									if (listAdapter == null) {
										listAdapter = new PurchaseConfirmListAdapter(
												PurchaseConfirmListActivity.this,
												datas);
										xListView.setAdapter(listAdapter);
										xListView
												.setOnItemClickListener(new OnItemClickListener() {

													@Override
													public void onItemClick(
															AdapterView<?> arg0,
															View arg1,
															int position,
															long arg3) {
														
														

													}
												});

										xListView
												.setOnItemLongClickListener(new OnItemLongClickListener() {

													@Override
													public boolean onItemLongClick(
															AdapterView<?> parent,
															View view,
															int position,
															long id) {

														return false;
													}
												});
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
						super.onFailure(t, errorNo, strMsg);
					}

				});
		getdata = true;
	}

	private void doDel(String id, final int pos) {

		// TODO Auto-generated method stub
		FinalHttp finalHttp = new FinalHttp();
		GetServerUrl.addHeaders(finalHttp,true);
		AjaxParams params = new AjaxParams();
		params.put("ids", id);
		finalHttp.post(GetServerUrl.getUrl() + "admin/seedlingNote/doDel",
				params, new AjaxCallBack<Object>() {

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
							}
							if ("1".equals(code)) {
								datas.remove(pos);
								listAdapter.notify(datas);
							} else {
								Toast.makeText(
										PurchaseConfirmListActivity.this,
										"删除失败", Toast.LENGTH_SHORT).show();
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
						super.onFailure(t, errorNo, strMsg);
					}

				});

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
				xListView.setPullLoadEnable(true);
				xListView.setPullRefreshEnable(true);
			}
		}, com.hldj.hmyg.application.Data.refresh_time);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case R.id.button31:
			isStatus = false;
			// 我的资源
			onRefresh();
			break;
		case R.id.button32:
			isStatus = true;
			// 共享资源
			onRefresh();
			break;
		default:
			// Nothing to do
		}
	}

	private void showCitys() {
		View dia_choose_share = getLayoutInflater().inflate(
				R.layout.dia_choose_city, null);
		TextView tv_sure = (TextView) dia_choose_share
				.findViewById(R.id.tv_sure);
		mViewProvince = (WheelView) dia_choose_share
				.findViewById(R.id.id_province);
		mViewCity = (WheelView) dia_choose_share.findViewById(R.id.id_city);
		mViewDistrict = (WheelView) dia_choose_share
				.findViewById(R.id.id_district);
		mViewCity.setVisibility(View.GONE);
		mViewDistrict.setVisibility(View.GONE);
		// 添加change事件
		mViewProvince.addChangingListener(this);
		// 添加change事件
		mViewCity.addChangingListener(this);
		// 添加change事件
		mViewDistrict.addChangingListener(this);
		setUpData();

		dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
		dialog.setContentView(dia_choose_share, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		tv_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cityName = mCurrentProviceName + "\u0020" + mCurrentCityName
						+ "\u0020" + mCurrentDistrictName + "\u0020";
				cityCode = GetCodeByName.initProvinceDatas(
						PurchaseConfirmListActivity.this, mCurrentProviceName,
						mCurrentCityName);
				List<Tag> tags = tagView.getTags();
				for (int i = 0; i < tags.size(); i++) {
					if (tags.get(i).id == 1) {
						tagView.remove(i);
					}
				}
				me.kaede.tagview.Tag tag = new me.kaede.tagview.Tag(
						mCurrentProviceName);
				tag.layoutColor = getResources().getColor(R.color.main_color);
				tag.isDeletable = true;
				tag.id = 1; // 1 城市 2.品名 3.规格 4.高度 5.冠幅
				tagView.addTag(tag);
				onRefresh();
				if (!PurchaseConfirmListActivity.this.isFinishing()
						&& dialog != null) {
					if (dialog.isShowing()) {
						dialog.cancel();
					} else {
						dialog.show();
					}
				}

			}
		});

		if (!PurchaseConfirmListActivity.this.isFinishing()
				&& dialog.isShowing()) {
			dialog.cancel();
		} else if (!PurchaseConfirmListActivity.this.isFinishing()
				&& dialog != null && !dialog.isShowing()) {
			dialog.show();
		}

	}

	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(
				PurchaseConfirmListActivity.this, mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mViewProvince) {
			updateCities();
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
			// mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentCityName
					+ mCurrentDistrictName);
		} else if (wheel == mViewCity) {
			updateAreas();
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentCityName
					+ mCurrentDistrictName);
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentCityName
					+ mCurrentDistrictName);
		}
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict
				.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	private void showSelectedResult() {
		Toast.makeText(
				PurchaseConfirmListActivity.this,
				"当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
						+ mCurrentDistrictName + "," + mCurrentZipCode,
				Toast.LENGTH_SHORT).show();
	}
	

}
