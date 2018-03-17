package com.credithc.templateviewer.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.credithc.templateviewer.R;
import com.zzy.commonlib.base.BaseFragment;


/**
 * 我的界面
 *
 * @author Song Boya
 */
public class MineFragment extends BaseFragment{
    private View view;
    private Button btnLogin;


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment, container, false);

        return view;
    }


}
