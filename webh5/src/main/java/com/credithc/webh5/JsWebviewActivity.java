package com.credithc.webh5;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.credithc.common.R;
import com.credithc.common.constants.Constants;
import com.credithc.common.constants.RouterConstants;
import com.zzy.commonlib.base.BaseActivity;

/**
 * @author zzy
 * @date 2018/2/28
 */
@Route(path = RouterConstants.ROUTER_WEBVIEW)
public class JsWebviewActivity extends BaseActivity {

/****************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fragment_container);

        String url = getIntent().getStringExtra(Constants.PARAM_KEY);
//        String url = "file:///android_asset/demo.html";
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_KEY, url);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_framelayout, JsAgentWebFragment.getInstance(bundle));
        ft.commit();
    }
}