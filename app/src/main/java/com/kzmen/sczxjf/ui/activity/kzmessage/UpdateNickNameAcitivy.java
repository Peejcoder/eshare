package com.kzmen.sczxjf.ui.activity.kzmessage;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kzmen.sczxjf.AppContext;
import com.kzmen.sczxjf.R;
import com.kzmen.sczxjf.bean.kzbean.UserMessageBean;
import com.kzmen.sczxjf.interfaces.OkhttpUtilResult;
import com.kzmen.sczxjf.net.OkhttpUtilManager;
import com.kzmen.sczxjf.ui.activity.basic.SuperActivity;
import com.kzmen.sczxjf.utils.TextUtil;
import com.kzmen.sczxjf.view.DJEditText;
import com.vondear.rxtools.view.RxToast;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

public class UpdateNickNameAcitivy extends SuperActivity {

    @InjectView(R.id.back)
    PercentRelativeLayout back;
    @InjectView(R.id.title_name)
    TextView titleName;
    @InjectView(R.id.kz_tiltle)
    LinearLayout kzTiltle;
    @InjectView(R.id.tv_save)
    TextView tvSave;
    @InjectView(R.id.et_input)
    DJEditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreateDataForView() {
        setTitle(R.id.kz_tiltle, "昵称修改");
    }

    @Override
    public void setThisContentView() {
        setContentView(R.layout.activity_update_nick_name_acitivy);
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        updateInfo();
    }

    private void updateInfo() {
        String nicname=etInput.getText().toString();
        if(TextUtil.isEmpty(nicname)){
            RxToast.normal("昵称不能为空");
            return;
        }
        Map<String,String>params=new HashMap<>();
        params.put("data[username]",nicname);
        OkhttpUtilManager.postNoCacah(this, "User/save_user_info", params, new OkhttpUtilResult() {
            @Override
            public void onSuccess(int type, String data) {
                Log.e("tst", data);
                try {
                    JSONObject object = new JSONObject(data);
                    Gson gson = new Gson();
                    UserMessageBean bean = gson.fromJson(object.getString("data"), UserMessageBean.class);
                    AppContext.getInstance().setUserMessageBean(bean);
                    EventBus.getDefault().post(bean);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorWrong(int code, String msg) {
             RxToast.normal(msg);
            }
        });
    }
}
