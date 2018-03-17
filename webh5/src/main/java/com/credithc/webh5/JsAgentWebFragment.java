package com.credithc.webh5;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by cenxiaozhong on 2017/5/26.
 * source code  https://github.com/Justson/AgentWeb
 */
public class JsAgentWebFragment extends AgentWebFragment {

    public static final JsAgentWebFragment getInstance(Bundle bundle) {
        JsAgentWebFragment mJsAgentWebFragment = new JsAgentWebFragment();
        if (bundle != null){
            mJsAgentWebFragment.setArguments(bundle);
        }
        return mJsAgentWebFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayout mLinearLayout= (LinearLayout) view;
        LayoutInflater.from(getContext()).inflate(R.layout.webh5_fragment_js,mLinearLayout,true);
        super.onViewCreated(view, savedInstanceState);
        if(mAgentWeb!=null){
            //注入对象
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidInterface(mAgentWeb,this.getActivity()));
        }
    }
}
