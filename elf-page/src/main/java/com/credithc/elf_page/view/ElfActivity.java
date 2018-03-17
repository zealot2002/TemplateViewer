package com.credithc.elf_page.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.credithc.common.constants.Constants;
import com.credithc.common.constants.RouterConstants;
import com.credithc.elf_page.R;
import com.zzy.commonlib.base.BaseActivity;

/**
 * ElfActivity
 */
@Route(path = RouterConstants.ROUTER_ELF)
public class ElfActivity extends BaseActivity{


/***********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elf_activity);
        try{
            String action = getIntent().getStringExtra(Constants.PARAM_KEY);
            Fragment f = new ElfFragment(action);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, f)
                    .show(f)
                    .commitAllowingStateLoss();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
