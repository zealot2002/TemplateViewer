package com.credithc.templateviewer;

/**
 * @author zzy
 * @date 2018/2/28
 */

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.credithc.common.constants.BusConstants;
import com.credithc.common.constants.RouterConstants;
import com.zzy.commonlib.base.BaseActivity;
import com.zzy.commonlib.core.BusHelper;

@Route(path = RouterConstants.ROUTER_LOGIN)
public class LoginActivity extends BaseActivity {
    private EditText etUsername,etPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {
        etUsername = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHelper.getBus().post(BusConstants.BUS_EVENT_LOGIN_SUCCESS);
                finish();
//                DataProvider.login(etUsername.getText().toString().trim(),etPass.getText().toString().trim());
            }
        });
    }
}