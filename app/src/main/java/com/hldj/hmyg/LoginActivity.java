package com.hldj.hmyg;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hldj.hmyg.application.Data;
import com.hldj.hmyg.application.MyApplication;
import com.hy.utils.GetServerUrl;
import com.hy.utils.JsonGetInfo;
import com.loginjudge.LoginJudge;
import com.mrwujay.cascade.activity.BaseActivity;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static com.hldj.hmyg.R.id.btn_clear_num;
import static com.hldj.hmyg.R.id.btn_clear_password;
import static com.hldj.hmyg.R.id.et_passward;
import static com.hldj.hmyg.R.id.et_phone;

//public class LoginActivity extends BaseActivity {
public class LoginActivity extends BaseActivity {

    /**
     */
    private ImageView btn_back;
    private TextView find_passward;
    private TextView reg;


    public Editor e;


    private String istab_c;


    HolderPwd holderPwd;

    //密码登录控件管理
    class HolderPwd {
        private TextView login;
        private EditText et_phone;
        private EditText et_passward;
        private ImageButton btn_clear_num;
        private ImageButton btn_clear_password;
    }

    class HolderNote {

    }

    //密码登录控件管理
    public void initHolderPwd() {
        holderPwd = new HolderPwd();
        holderPwd.login = (TextView) findViewById(R.id.login);
        holderPwd.et_phone = (EditText) findViewById(et_phone);
        holderPwd.et_passward = (EditText) findViewById(et_passward);
        holderPwd.btn_clear_num = (ImageButton) findViewById(btn_clear_num);
        holderPwd.btn_clear_password = (ImageButton) findViewById(btn_clear_password);
        holderPwd.et_phone.addTextChangedListener(watcher_num);//电话号码
        holderPwd.et_passward.addTextChangedListener(watcher_num);
        holderPwd.btn_clear_num.setOnClickListener(multipleClickProcess);
        holderPwd.btn_clear_password.setOnClickListener(multipleClickProcess);
        holderPwd.login.setOnClickListener(multipleClickProcess);

    }


    MultipleClickProcess multipleClickProcess;

    /**
     * 初始化共同使用的 view 控件
     *
     * @param
     */
    public void commonView() {
        btn_back = (ImageView) findViewById(R.id.btn_back);
        reg = (TextView) findViewById(R.id.reg);
        find_passward = (TextView) findViewById(R.id.find_passward);
        btn_back.setOnClickListener(multipleClickProcess);
        e = MyApplication.Userinfo.edit();
        reg.setOnClickListener(multipleClickProcess);
        find_passward.setOnClickListener(multipleClickProcess);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2_0);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        multipleClickProcess = new MultipleClickProcess();//初始化点击事件
        initHolderPwd();
        commonView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addAmin2Login();//为logo添加动画
            }
        }, 3000);
    }

    private void addAmin2Login() {
        //使用AnimationUtils类的loadAnimation来加载xml格式的动画文件
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_scale);
        ImageView iv = (ImageView) findViewById(R.id.iv_login_logo);
        iv.clearAnimation();
        iv.startAnimation(animation);
    }

    TextWatcher watcher_num = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            if (s.length() > 0) {

                if (holderPwd.et_phone.getText().toString().length() > 0) {
                    holderPwd.btn_clear_num.setVisibility(View.VISIBLE);
                    if (holderPwd.et_passward.getText().toString().length() > 5)
                    {
                        holderPwd.login.setEnabled(true);
                        holderPwd.login.setSelected(true);
                    }

//                    holderPwd.login.setTextColor(getResources().getColor(R.color.white));
                }
            } else {
                if (holderPwd.et_phone.getText().toString().length() == 0)
                    holderPwd.btn_clear_num.setVisibility(View.GONE);

                holderPwd.login.setEnabled(false);
                holderPwd.login.setSelected(false);
//                holderPwd.login.setTextColor(getResources().getColor(R.color.white));

            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };
    TextWatcher watcher_password = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            if (s.length() > 0) {
                holderPwd.btn_clear_password.setVisibility(View.VISIBLE);
                if (holderPwd.et_phone.getText().toString().length() > 1
                        && holderPwd.et_passward.getText().toString().length() > 5) {
                    holderPwd.login.setEnabled(true);
                    holderPwd.login.setTextColor(getResources().getColor(R.color.white));
                }
            } else {
                holderPwd.btn_clear_password.setVisibility(View.GONE);
                holderPwd.login.setEnabled(false);
                holderPwd.login.setTextColor(getResources().getColor(R.color.white));

            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    public void onBackPressed() {
        if (LoginJudge.isTabc) {
            LoginJudge.isTabc = false;
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

//		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
        overridePendingTransition_back();

    }


    /**
     * 显示短信登录  /    显示验证码登录
     */
    public void showNoteLogin(View view) {
        findViewById(R.id.include_login_note).setVisibility(View.VISIBLE);//短信登录显示
        findViewById(R.id.include_login_pwd).setVisibility(View.GONE);//密码登录显示
    }

    public void showPwdLogin(View view) {
        findViewById(R.id.include_login_note).setVisibility(View.GONE);
        findViewById(R.id.include_login_pwd).setVisibility(View.VISIBLE);
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
                    case btn_clear_num:
                        holderPwd.et_phone.setText("");
                        break;
                    case btn_clear_password:
                        holderPwd.et_passward.setText("");
                        break;
                    case R.id.login:
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        if (imm.isActive() && getCurrentFocus() != null
                                && getCurrentFocus().getWindowToken() != null) {
                            imm.hideSoftInputFromWindow(LoginActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                        // 隐藏关闭输入法
                        // 判断是否为空
                        if ("".equals(holderPwd.et_phone.getText().toString())
                                || "".equals(holderPwd.et_passward.getText().toString())) {
                            Toast.makeText(getApplicationContext(),
                                    "以上内容都需要填写！请检查...", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        FinalHttp finalHttp = new FinalHttp();
                        GetServerUrl.addHeaders(finalHttp, false);
                        AjaxParams params = new AjaxParams();
                        params.put("userName", holderPwd.et_phone.getText().toString());
                        params.put("password", holderPwd.et_passward.getText().toString());
                        finalHttp.post(GetServerUrl.getUrl() + "user/login",
                                params, new AjaxCallBack<Object>() {

                                    @Override
                                    public void onSuccess(Object t) {
                                        // TODO Auto-generated method stub
                                        try {
                                            JSONObject jsonObject = new JSONObject(
                                                    t.toString());
                                            String code = JsonGetInfo
                                                    .getJsonString(jsonObject,
                                                            "code");
                                            String msg = JsonGetInfo.getJsonString(
                                                    jsonObject, "msg");
                                            if (!"".equals(msg)) {

                                            }
                                            if ("1".equals(code)) {
                                                Toast.makeText(LoginActivity.this,
                                                        "登录成功", Toast.LENGTH_SHORT)
                                                        .show();
                                                String userId = jsonObject
                                                        .getJSONObject("data")
                                                        .getString("userId");
                                                getUserInfo(userId, "LoginActivity");
                                            } else {
                                                Toast.makeText(LoginActivity.this,
                                                        msg, Toast.LENGTH_SHORT)
                                                        .show();
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
                                        Toast.makeText(LoginActivity.this,
                                                R.string.error_net,
                                                Toast.LENGTH_SHORT).show();
                                        super.onFailure(t, errorNo, strMsg);
                                    }

                                });

                        break;
                    case R.id.reg://注册
                        Intent toRegisterActivity = new Intent(LoginActivity.this,
                                RegisterActivity.class);
                        startActivity(toRegisterActivity);
//                        overridePendingTransition(R.anim.slide_in_left,
//                                R.anim.slide_out_right);
                        overridePendingTransition_open();
                        break;
                    case R.id.find_passward:
                        Intent toSetPasswardByGetCodeActivity = new Intent(
                                LoginActivity.this,
                                SetPasswardByGetCodeActivity.class);
                        startActivity(toSetPasswardByGetCodeActivity);
//                        overridePendingTransition(R.anim.slide_in_left,
//                                R.anim.slide_out_right);
                        overridePendingTransition_open();
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

    public void getUserInfo(String userId, final String activity) {
        FinalHttp finalHttp = new FinalHttp();
        GetServerUrl.addHeaders(finalHttp, false);
        finalHttp.addHeader("authc", userId);
        AjaxParams params = new AjaxParams();
        finalHttp.post(GetServerUrl.getUrl() + "admin/user/getInfo", params,
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
                                // Toast.makeText(LoginActivity.this, msg,
                                // Toast.LENGTH_SHORT).show();
                            }
                            if ("1".equals(code)) {
                                JSONObject user = jsonObject.getJSONObject(
                                        "data").getJSONObject("user");
                                e.putString("id",
                                        JsonGetInfo.getJsonString(user, "id"));
                                e.putString("code", JsonGetInfo.getJsonString(
                                        user, "storeId"));
                                e.putBoolean("isNewRecord", JsonGetInfo
                                        .getJsonBoolean(user, "isNewRecord"));
                                e.putString("createDate", JsonGetInfo
                                        .getJsonString(user, "createDate"));
                                e.putString("updateDate", JsonGetInfo
                                        .getJsonString(user, "updateDate"));
                                e.putString("delFlag", JsonGetInfo
                                        .getJsonString(user, "delFlag"));
                                e.putString("userName", JsonGetInfo
                                        .getJsonString(user, "userName"));
                                e.putString("realName", JsonGetInfo
                                        .getJsonString(user, "realName"));
                                e.putString("phone", JsonGetInfo.getJsonString(
                                        user, "phone"));
                                e.putString("sex",
                                        JsonGetInfo.getJsonString(user, "sex"));
                                e.putString("tel",
                                        JsonGetInfo.getJsonString(user, "tel"));
                                e.putString("email", JsonGetInfo.getJsonString(
                                        user, "email"));
                                e.putString("cityCode", JsonGetInfo
                                        .getJsonString(user, "cityCode"));
                                e.putString("address", JsonGetInfo
                                        .getJsonString(user, "address"));
                                e.putString("companyName", JsonGetInfo
                                        .getJsonString(user, "companyName"));
                                e.putString("publicName", JsonGetInfo
                                        .getJsonString(user, "publicName"));
                                e.putString("publicPhone", JsonGetInfo
                                        .getJsonString(user, "publicPhone"));
                                e.putString("headImage", JsonGetInfo
                                        .getJsonString(user, "headImage"));

                                // if
                                // (!"".equals(JsonGetInfo.getJsonString(user,
                                // "companyName"))) {
                                // e.putString("showUserName", JsonGetInfo
                                // .getJsonString(user, "companyName"));
                                // } else if
                                // ("".equals(JsonGetInfo.getJsonString(
                                // user, "companyName"))
                                // && !"".equals(JsonGetInfo
                                // .getJsonString(user,
                                // "publicName"))) {
                                // e.putString("showUserName", JsonGetInfo
                                // .getJsonString(user, "publicName"));
                                //
                                // } else if
                                // ("".equals(JsonGetInfo.getJsonString(
                                // user, "companyName"))
                                // && "".equals(JsonGetInfo.getJsonString(
                                // user, "publicName"))) {
                                // e.putString("showUserName", JsonGetInfo
                                // .getJsonString(user, "realName"));
                                //
                                // } else {
                                // e.putString("showUserName", JsonGetInfo
                                // .getJsonString(user, "userName"));
                                // }

                                if (!"".equals(JsonGetInfo.getJsonString(user,
                                        "realName"))) {
                                    e.putString("showUserName", JsonGetInfo
                                            .getJsonString(user, "realName"));
                                } else {
                                    e.putString("showUserName", JsonGetInfo
                                            .getJsonString(user, "userName"));
                                }

                                e.putString("status", JsonGetInfo
                                        .getJsonString(user, "status"));
                                e.putBoolean("isInvoices", JsonGetInfo
                                        .getJsonBoolean(user, "isInvoices"));
                                e.putBoolean("receiptMsg", JsonGetInfo
                                        .getJsonBoolean(user, "receiptMsg"));
                                e.putBoolean("isDirectAgent", JsonGetInfo
                                        .getJsonBoolean(user, "isDirectAgent"));
                                e.putBoolean("isAgent", JsonGetInfo
                                        .getJsonBoolean(user, "isAgent"));
                                e.putBoolean("isPartners", JsonGetInfo
                                        .getJsonBoolean(user, "isPartners"));
                                e.putBoolean("isPurchaseConfirm", JsonGetInfo
                                        .getJsonBoolean(user,
                                                "isPurchaseConfirm"));
                                e.putString("agentTypeName", JsonGetInfo
                                        .getJsonString(user, "agentTypeName"));
                                e.putString("coCityfullName", JsonGetInfo
                                        .getJsonString(JsonGetInfo
                                                        .getJSONObject(user, "coCity"),
                                                "fullName"));
                                e.putString("coCitycityCode", JsonGetInfo
                                        .getJsonString(JsonGetInfo
                                                        .getJSONObject(user, "coCity"),
                                                "cityCode"));
                                e.putString("twCityfullName", JsonGetInfo
                                        .getJsonString(JsonGetInfo
                                                        .getJSONObject(user, "twCity"),
                                                "name"));
                                e.putString("twCitycityCode", JsonGetInfo
                                        .getJsonString(JsonGetInfo
                                                        .getJSONObject(user, "twCity"),
                                                "cityCode"));
                                e.putString("prCode", JsonGetInfo
                                        .getJsonString(user, "prCode"));
                                e.putString("ciCode", JsonGetInfo
                                        .getJsonString(user, "ciCode"));
                                e.putString("coCode", JsonGetInfo
                                        .getJsonString(user, "coCode"));
                                e.putString("twCode", JsonGetInfo
                                        .getJsonString(user, "twCode"));
                                e.putString("storeId", JsonGetInfo
                                        .getJsonString(user, "storeId"));
                                e.putBoolean("isClerk", JsonGetInfo
                                        .getJsonBoolean(user, "isClerk"));
                                e.putBoolean("isLogin", true);
                                e.putBoolean("notification",
                                        MyApplication.Userinfo.getBoolean(
                                                "notification", false));
                                e.commit();
                                JPushInterface.setAlias(LoginActivity.this,
                                        MyApplication.Userinfo.getString("id",
                                                ""), new TagAliasCallback() {

                                            @Override
                                            public void gotResult(int arg0,
                                                                  String arg1,
                                                                  Set<String> arg2) {
                                                // TODO Auto-generated method
                                                // stub

                                            }
                                        });
                                if ("LoginActivity".equals(activity)) {
                                    finish();
                                } else if ("SetProfileActivity"
                                        .equals(activity)) {

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
                        Toast.makeText(LoginActivity.this, R.string.error_net,
                                Toast.LENGTH_SHORT).show();
                        super.onFailure(t, errorNo, strMsg);
                    }

                });

    }

}
