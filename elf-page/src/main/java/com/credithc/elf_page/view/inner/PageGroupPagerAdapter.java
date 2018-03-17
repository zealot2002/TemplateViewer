package com.credithc.elf_page.view.inner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */

public class PageGroupPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public PageGroupPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        mFragments=fragments;
    }
    @Override
    public Fragment getItem(int arg0) {
        return mFragments.get(arg0);
    }
    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(fragments.get(position).getView()); // 移出viewpager两边之外的page布局
    }


}
