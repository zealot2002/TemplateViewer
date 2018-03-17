package com.credithc.common.base;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.credithc.common.R;
import com.zzy.commonlib.base.BaseFragment;

;

/**
 * @author zzy
 * @date 2018/2/27
 */

public class BaseLoadingFragment extends BaseFragment implements BaseLoadingView {
    private View root,contentView;
    private FrameLayout flDisconnectRoot,flLoadingErrorRoot,flLoadingRoot;

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.base_layout_loading_fragment, container, false);
        initViews();
        return root;
    }
    protected void setRoot(View root){
        this.root = root;
    }
    protected void initViews(){
        flLoadingRoot = root.findViewById(R.id.flLoadingRoot);
        flDisconnectRoot = root.findViewById(R.id.flDisconnectRoot);
        flLoadingErrorRoot = root.findViewById(R.id.flLoadingErrorRoot);
        (flDisconnectRoot.findViewById(R.id.rlDisconnectReload)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
        (flLoadingErrorRoot.findViewById(R.id.btnErrorReload)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
        hideAll();
    }

//    XTipDialog loading;
    public void showLoading() {
//        loading = XTip.loading(getHoldingActivity());

    }
    public void closeLoading() {
//        XTip.dismiss(loading);
    }

    @Override
    public void showDisconnect() {
        hideAll();
        flDisconnectRoot.setVisibility(View.VISIBLE);
    }
    @Override
    public void showLoadingError() {
        hideAll();
        flLoadingErrorRoot.setVisibility(View.VISIBLE);
    }
    private void hideAll(){
        for(int i=0;i<flLoadingRoot.getChildCount();i++){
            flLoadingRoot.getChildAt(i).setVisibility(View.GONE);
        }
    }
    //设置content layoutId
    public void setContentViewLayoutId(int resId) throws RuntimeException {
        if(flLoadingRoot==null){
            throw new RuntimeException("此方法必须在父类先初始化之后调用");
        }
        if(contentView!=null){
            flLoadingRoot.removeView(contentView);
        }
        contentView = getHoldingActivity().getLayoutInflater().inflate(resId,null);
        contentView.setVisibility(View.GONE);
        flLoadingRoot.addView(contentView);
    }
    //子类复写此方法
    @Override
    public void reload() {}

    //子类复写此方法，需要调用super.updateUI
    @Override
    @CallSuper
    public void updateUI(Object o) {
        hideAll();
        contentView.setVisibility(View.VISIBLE);
    }

}
