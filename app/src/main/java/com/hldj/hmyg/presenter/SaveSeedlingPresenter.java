package com.hldj.hmyg.presenter;

import com.hldj.hmyg.CallBack.ResultCallBack;
import com.hldj.hmyg.application.MyApplication;
import com.hldj.hmyg.bean.LoginGsonBean;
import com.hldj.hmyg.util.ConstantState;
import com.hldj.hmyg.util.D;
import com.hldj.hmyg.util.GsonUtil;
import com.hy.utils.GetServerUrl;
import com.hy.utils.ToastUtil;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * Created by Administrator on 2017/4/14.
 */

public class SaveSeedlingPresenter {


    public SaveSeedlingPresenter() {

    }


    public static void toLoginWithNote(String phone, String smsCode, final ResultCallBack<LoginGsonBean> resultCallBack) {

        FinalHttp finalHttp = new FinalHttp();
        GetServerUrl.addHeaders(finalHttp, false);
        AjaxParams params = new AjaxParams();
        params.put("phone", phone);//手机号
        params.put("smsCode", smsCode);//短信验证码

        AjaxCallBack ajaxCallBack = new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String json) {

                //解析json  返回
                LoginGsonBean loginGsonBean = GsonUtil.formateJson2Bean(json, LoginGsonBean.class);
                resultCallBack.onSuccess(loginGsonBean);
                if (!loginGsonBean.getCode().equals(ConstantState.SUCCEED_CODE))
                    resultCallBack.onFailure(null, Integer.parseInt(loginGsonBean.getCode()), loginGsonBean.getMsg());
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                D.e("");

            }
        };
        finalHttp.post(GetServerUrl.getUrl() + "/user/smslogin", params, ajaxCallBack);

    }


    public void sayHellowWorld() {
        ToastUtil.showShortToast(MyApplication.getInstance(), "hellow world");
    }


}
