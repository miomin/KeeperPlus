package com.scu.miomin.keeperplus.weex.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;
import com.taobao.weex.utils.WXLogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class WXGetDoctorInfoModule extends WXModule {

    public WXGetDoctorInfoModule() {

    }

    /**
     * @param optionsStr request options include:
     *                   method: GET 、POST、PUT、DELETE、HEAD、PATCH
     *                   headers：object，请求header
     *                   url:
     *                   body: "Any body that you want to add to your request"
     *                   type: json、text、jsonp（native实现时等价与json）
     * @param callback   finished callback,response object:
     *                   status：status code
     *                   ok：boolean 是否成功，等价于status200～299
     *                   statusText：状态消息，用于定位具体错误原因
     *                   data: 响应数据，当请求option中type为json，时data为object，否则data为string类型
     *                   headers: object 响应头
     */
    @WXModuleAnno
    public void fetch(String optionsStr, final JSCallback callback) {

        JSONObject optionsObj = null;
        try {
            optionsObj = JSON.parseObject(optionsStr);
        } catch (JSONException e) {
            WXLogUtils.e("", e);
        }

        if (optionsObj == null)
            return;

        final String phonenumber = KeeperPlusCache.getInstance().getLastCheckUserPhonenumber();

        if (phonenumber == null)
            return;

        BmobQuery<Userbean> query = new BmobQuery<>();
        query.addWhereEqualTo("mobilePhoneNumber", phonenumber);
        query.include("birthday");
        query.include("hospital");
        query.findObjects(new FindListener<Userbean>() {
            @Override
            public void done(List<Userbean> list, BmobException e) {
                if (e == null)
                    if (list.size() > 0) {
                        if (callback != null) {
                            Map<String, Object> resp = new HashMap<>();
                            resp.put("username", list.get(0).getUsername());
                            resp.put("professional", list.get(0).getProfessional());
                            resp.put("administrative", list.get(0).getAdministrative());
                            resp.put("hospitaleName", list.get(0).getHospital().getName());
                            resp.put("introduction", list.get(0).getIntroduction());
                            resp.put("headurl", list.get(0).getHeadUrl());
                            callback.invoke(resp);
                        }
                    }
            }
        });
    }
}
