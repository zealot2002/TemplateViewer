package com.credithc.templateviewer.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.credithc.common.constants.Constants;
import com.credithc.elf_page.view.ElfFragment;
import com.credithc.templateviewer.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.zzy.commonlib.base.BaseActivity;
import com.zzy.commonlib.core.BusHelper;
import com.zzy.commonlib.util.ActivityManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private List<Fragment> fragments = new ArrayList<>();
    private LinearLayout mLlAllTab;
    private LinearLayout[] mTabs;
    private int currentTabIndex;

/***********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_home);
        currentTabIndex = 0;
        initTabButton();
        initFragment();
        showFragment(0);
        BusHelper.getBus().register(this);
    }

    private void initFragment() {

//        for(int i=0;i<3;i++){
//            Fragment f = new ElfFragment(P1);
//        }
        fragments.add(new ElfFragment(Constants.PAGE_DATA1));
        fragments.add(new ElfFragment(Constants.PAGE_DATA2));
        fragments.add(new ElfFragment(Constants.PAGE_DATA3));
//        fragments = new Fragment[]{f1,f2, };
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragments.get(0)).show(fragments.get(0)).commitAllowingStateLoss();
    }

    /**
     * 初始化Tab
     */
    private void initTabButton() {
        mLlAllTab = findViewById(R.id.llAllTab);
        mTabs = new LinearLayout[3];
        mTabs[0] = (LinearLayout) findViewById(R.id.llTabLoan);
        mTabs[1] = (LinearLayout) findViewById(R.id.llTabService);
        mTabs[2] = (LinearLayout) findViewById(R.id.llTabMine);
        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
        mTabs[2].setOnClickListener(this);
        mTabs[0].setSelected(true);
    }

    /**
     * 展示/切换Fragment
     * @param index
     */
    private void showFragment(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments.get(currentTabIndex));
            if (!fragments.get(index).isAdded()) {
                trx.add(R.id.fragment_container, fragments.get(index));
            }
            trx.show(fragments.get(index)).commitAllowingStateLoss();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;

        changeTabColor(currentTabIndex);
    }

    /**
     * 切换时变色
     *
     * @param index
     */
    private void changeTabColor(int index) {
        for (int i = 0; i < mLlAllTab.getChildCount(); i++) {

            LinearLayout currentView = (LinearLayout) mLlAllTab.getChildAt(i);
            ImageView currentImage = (ImageView) currentView.getChildAt(0);
            TextView current_TextView = (TextView) currentView.getChildAt(1);
            switch (i) {
                case 0:
                    if (index == i) {
                        currentImage.setImageResource(R.mipmap.home_ic_tab_home_check);
                    } else {
                        currentImage.setImageResource(R.mipmap.home_ic_tab_home_default);
                    }
                    break;

                case 1:
                    if (index == i) {
                        currentImage.setImageResource(R.mipmap.home_ic_tab_products_check);
                    } else {
                        currentImage.setImageResource(R.mipmap.home_ic_tab_products_default);
                    }
                    break;

                case 2:
                    if (index == i) {
                        currentImage.setImageResource(R.mipmap.home_ic_tab_my_check);
                    } else {
                        currentImage.setImageResource(R.mipmap.home_ic_tab_my_default);
                    }
                    break;
                default:
                    break;
            }

            if (i == index) {
                current_TextView.setTextColor(this.getResources().getColor(R.color.home_main_tab_text_color_select));
            } else {
                current_TextView.setTextColor(this.getResources().getColor(R.color.home_main_tab_text_color_unselect));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.llTabService) {
            showFragment(1);
        }else if (view.getId() == R.id.llTabLoan) {
            showFragment(0);
        } else if (view.getId() == R.id.llTabMine) {
            showFragment(2);
        }
    }

    private long exitTime = 0;
    /**
     * 再按一次退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //T.showShort(this, "再按一次退出程序");
                // SnackyHelper.showDefault(this,"再按一次退出程序"); // todo
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.getInstance().exitApp(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(tags = {@Tag(value = "transfFragment")})
    public void onNotificationReceived(String s){
        showFragment(1);
    }
}
