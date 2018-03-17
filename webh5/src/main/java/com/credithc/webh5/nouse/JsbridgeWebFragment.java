package com.credithc.webh5.nouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.credithc.webh5.AgentWebFragment;
import com.credithc.webh5.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.just.agentweb.AgentWeb;

/**
 * @author zzy
 * @date 2018/3/16
 */
public class JsbridgeWebFragment extends AgentWebFragment {

	private BridgeWebView mBridgeWebView;

/**************************************************************************************************/
	public static JsbridgeWebFragment getInstance(Bundle bundle){
		JsbridgeWebFragment mJsbridgeWebFragment =new JsbridgeWebFragment();
		if(mJsbridgeWebFragment !=null){
			mJsbridgeWebFragment.setArguments(bundle);
		}
		return mJsbridgeWebFragment;
	}
	@Override
	public String getUrl() {
		return super.getUrl();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		LinearLayout mLinearLayout= (LinearLayout) view;
		LayoutInflater.from(getContext()).inflate(R.layout.webh5_fragment_js,mLinearLayout,true);
		super.onViewCreated(view, savedInstanceState);

		mBridgeWebView=new BridgeWebView(getActivity());
//		String url = "file:///android_asset/demo.html";
		mAgentWeb = AgentWeb.with(this)
				.setAgentWebParent((ViewGroup) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
				.useDefaultIndicator(-1, 2)
				.setWebViewClient(new BridgeWebViewClient(mBridgeWebView))
				.setWebChromeClient(mWebChromeClient)
				.setWebView(mBridgeWebView)
				.setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//                .setDownloadListener(mDownloadListener) 4.0.0 删除该API
				.createAgentWeb()
				.ready()
				.go(getUrl());
//				.go(url);
		initView(view);

		mBridgeWebView.registerHandler("submitFromWeb", new BridgeHandler() {
			@Override
			public void handler(String data, CallBackFunction function) {
				Toast.makeText(getActivity(),
						"收到消息！！！！！！！！！！！！！！！！！！！！！！！" + data, Toast.LENGTH_SHORT).show();
//                Log.e("zzy", "收到消息！！！！！！！！！！！！！！！！！！！！！！！" + data);
				function.onCallBack("消息应答！！！！！！！！！！！！！！！！！！！！！！");
			}
		});
	}
}
