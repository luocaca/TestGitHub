package com.hldj.hmyg.presenter;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hldj.hmyg.application.MyApplication;
import com.hldj.hmyg.util.D;
import com.hy.utils.JsonGetInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 登录注册的逻辑代码放这里来写
 * Created by Administrator on 2017/4/12.
 */

public class LoginPresenter {


    public static class MyTextWatcher implements TextWatcher {
        EditText editText;
        ImageView imageView;
        TextView tv_get_code_note;

        public MyTextWatcher(final EditText editText, ImageButton imageView, final TextView tv_get_code_note) {
            this.editText = editText;
            this.imageView = imageView;
            this.tv_get_code_note = tv_get_code_note;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText("");
                }
            });
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (s.length() == 0) imageView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);

            if (s.length() == 11) tv_get_code_note.setSelected(true);
            tv_get_code_note.setSelected(false);


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    public static void Save2Sp(SharedPreferences.Editor editor, String json) {
        JSONObject user = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            user = jsonObject.getJSONObject(
                    "data").getJSONObject("user");
            editor.putString("id",
                    JsonGetInfo.getJsonString(user, "id"));
            editor.putString("code", JsonGetInfo.getJsonString(
                    user, "storeId"));
            editor.putBoolean("isNewRecord", JsonGetInfo
                    .getJsonBoolean(user, "isNewRecord"));
            editor.putString("createDate", JsonGetInfo
                    .getJsonString(user, "createDate"));
            editor.putString("updateDate", JsonGetInfo
                    .getJsonString(user, "updateDate"));
            editor.putString("delFlag", JsonGetInfo
                    .getJsonString(user, "delFlag"));
            editor.putString("userName", JsonGetInfo
                    .getJsonString(user, "userName"));
            editor.putString("realName", JsonGetInfo
                    .getJsonString(user, "realName"));
            editor.putString("phone", JsonGetInfo.getJsonString(
                    user, "phone"));
            editor.putString("sex",
                    JsonGetInfo.getJsonString(user, "sex"));
            editor.putString("tel",
                    JsonGetInfo.getJsonString(user, "tel"));
            editor.putString("email", JsonGetInfo.getJsonString(
                    user, "email"));
            editor.putString("cityCode", JsonGetInfo
                    .getJsonString(user, "cityCode"));
            editor.putString("address", JsonGetInfo
                    .getJsonString(user, "address"));
            editor.putString("companyName", JsonGetInfo
                    .getJsonString(user, "companyName"));
            editor.putString("publicName", JsonGetInfo
                    .getJsonString(user, "publicName"));
            editor.putString("publicPhone", JsonGetInfo
                    .getJsonString(user, "publicPhone"));
            editor.putString("headImage", JsonGetInfo
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
                editor.putString("showUserName", JsonGetInfo
                        .getJsonString(user, "realName"));
            } else {
                editor.putString("showUserName", JsonGetInfo
                        .getJsonString(user, "userName"));
            }

            editor.putString("status", JsonGetInfo
                    .getJsonString(user, "status"));
            editor.putBoolean("isInvoices", JsonGetInfo
                    .getJsonBoolean(user, "isInvoices"));
            editor.putBoolean("receiptMsg", JsonGetInfo
                    .getJsonBoolean(user, "receiptMsg"));
            editor.putBoolean("isDirectAgent", JsonGetInfo
                    .getJsonBoolean(user, "isDirectAgent"));
            editor.putBoolean("isAgent", JsonGetInfo
                    .getJsonBoolean(user, "isAgent"));
            editor.putBoolean("isPartners", JsonGetInfo
                    .getJsonBoolean(user, "isPartners"));
            editor.putBoolean("isPurchaseConfirm", JsonGetInfo
                    .getJsonBoolean(user,
                            "isPurchaseConfirm"));
            editor.putString("agentTypeName", JsonGetInfo
                    .getJsonString(user, "agentTypeName"));
            editor.putString("coCityfullName", JsonGetInfo
                    .getJsonString(JsonGetInfo
                                    .getJSONObject(user, "coCity"),
                            "fullName"));
            editor.putString("coCitycityCode", JsonGetInfo
                    .getJsonString(JsonGetInfo
                                    .getJSONObject(user, "coCity"),
                            "cityCode"));
            editor.putString("twCityfullName", JsonGetInfo
                    .getJsonString(JsonGetInfo
                                    .getJSONObject(user, "twCity"),
                            "name"));
            editor.putString("twCitycityCode", JsonGetInfo
                    .getJsonString(JsonGetInfo
                                    .getJSONObject(user, "twCity"),
                            "cityCode"));
            editor.putString("prCode", JsonGetInfo
                    .getJsonString(user, "prCode"));
            editor.putString("ciCode", JsonGetInfo
                    .getJsonString(user, "ciCode"));
            editor.putString("coCode", JsonGetInfo
                    .getJsonString(user, "coCode"));
            editor.putString("twCode", JsonGetInfo
                    .getJsonString(user, "twCode"));
            editor.putString("storeId", JsonGetInfo
                    .getJsonString(user, "storeId"));
            editor.putBoolean("isClerk", JsonGetInfo
                    .getJsonBoolean(user, "isClerk"));
            editor.putBoolean("isLogin", true);
            editor.putBoolean("notification",
                    MyApplication.Userinfo.getBoolean(
                            "notification", false));
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    static int count;
    static boolean flag = true;

    /**
     * 获取验证码
     *
     * @param tv_get_code_note
     */
    public static void getCode(final TextView tv_get_code_note, int my_count) {

        count = my_count;
        while (flag)

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    D.e("==count=" + count);
                    tv_get_code_note.setText("重新获取(" + count-- + "s)");
                    if (count == 0) tv_get_code_note.setSelected(false);
                    flag = false;
                }
            }, 1000);

    }




//     TextWatcher watcher_note= new TextWatcher() {
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before,
//                                  int count) {
//            // TODO Auto-generated method stub
//            if (s.length() > 0) {
//                holderPwd.btn_clear_password.setVisibility(View.VISIBLE);
//                if (holderPwd.et_phone.getText().toString().length() > 1
//                        && holderPwd.et_passward.getText().toString().length() > 5) {
//                    holderPwd.login.setEnabled(true);
//                    holderPwd.login.setTextColor(getResources().getColor(R.color.white));
//                }
//            } else {
//                holderPwd.btn_clear_password.setVisibility(View.GONE);
//                holderPwd.login.setEnabled(false);
//                holderPwd.login.setTextColor(getResources().getColor(R.color.white));
//            }
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count,
//                                      int after) {
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            // TODO Auto-generated method stub
//
//        }
//    };


}
