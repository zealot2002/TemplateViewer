package com.credithc.elf_page.view.render.impl;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;


import com.credithc.elf_page.R;
import com.credithc.elf_page.model.Page;
import com.credithc.elf_page.view.ElfFragment;
import com.credithc.elf_page.view.inner.MyViewPaper;
import com.credithc.elf_page.view.inner.PageGroupPagerAdapter;
import com.credithc.elf_page.view.render.PageRender;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @date 2018/2/27
 */
public class RenderGroupTop1PageRender implements PageRender {
    //page group
    private View rootView;
    private Context context;
    private Fragment rootFragment;
    private LinearLayout llPageGroup;
    private PageGroupPagerAdapter pageGroupPagerAdapter;
    private MyViewPaper mViewPager;
    private List<Page> pageList;

/**************************************************************************************************/
    public RenderGroupTop1PageRender(Context context, Fragment rootFragment) {
        this.context = context;
        this.rootFragment = rootFragment;
    }
    @Override
    public void render(ViewGroup container, Page page) {
        this.pageList = page.getBody().getDataList();
        if(rootView==null){
            rootView = View.inflate(context, R.layout.elf_page_content_page_group_top1,null);
            container.addView(rootView);
        }
        findPageGroupViews();
    }

    private void findPageGroupViews() {
        if(llPageGroup == null){
            llPageGroup = rootView.findViewById(R.id.llPageGroup);
            mViewPager = rootView.findViewById(R.id.view_pager);

            List<Fragment> fragments=new ArrayList<>();
            for(int i=0;i<pageList.size();i++){
                fragments.add(new ElfFragment(pageList.get(i).getCode()));
            }
            pageGroupPagerAdapter = new PageGroupPagerAdapter(rootFragment.getChildFragmentManager(),fragments);
            mViewPager.setAdapter(pageGroupPagerAdapter);
            pageGroupPagerAdapter.notifyDataSetChanged();

            initMagicIndicator4();
        }
    }
    private void initMagicIndicator4() {
        MagicIndicator magicIndicator = (MagicIndicator) rootView.findViewById(R.id.magic_indicator1);
        CommonNavigator commonNavigator = new CommonNavigator(rootFragment.getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return pageList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(context.getResources().getColor(R.color.text_black));
                simplePagerTitleView.setSelectedColor(context.getResources().getColor(R.color.text_red));
                simplePagerTitleView.setTextSize(UIUtil.dip2px(context, 6));
                simplePagerTitleView.setText(pageList.get(index).getName());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context, 20));
                linePagerIndicator.setColors(context.getResources().getColor(R.color.text_red));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return UIUtil.dip2px(context, 15);
            }
        });

        final FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(magicIndicator);
        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
        fragmentContainerHelper.setDuration(300);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                fragmentContainerHelper.handlePageSelected(position);
            }
        });
    }
}
