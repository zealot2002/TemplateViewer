package com.credithc.elf_page.view.template.engine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.credithc.common.utils.ImageLoaderUtils;
import com.credithc.elf_page.R;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Section;
import com.credithc.elf_page.model.Widget;
import com.credithc.elf_page.view.template.TemplateHelper;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author zzy
 * @date 2018/2/11
 *
 */

public class Engine3 implements ItemViewDelegate {
    private ConvenientBanner convenientBanner;
    private Context context;

    /*****************************************************************************************************/

    public Engine3(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.elf_template3;
    }

    @Override
    public boolean isForViewType(Object obj, int position) {
        if(obj instanceof Section){
            return ((Section)obj).getTemplateId()==3;
        }else{
            return false;
        }
    }

    @Override
    public void convert(ViewHolder holder,Object obj, int position) {
        convenientBanner = holder.getView(R.id.convenientBanner);
        convenientBanner.setPages(
                new CBViewHolderCreator<MyViewHolder>() {
                    @Override
                    public MyViewHolder createHolder() {
                        return new MyViewHolder();
                    }
                }, ((Section)obj).getItemList())
//                .setPageIndicator(new int[]{R.mipmap.home_ic_page_indicator, R.mipmap.home_ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                /*翻页效果可更换*/
//                .setPageTransformer(new AccordionTransformer())
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
//                        String url = vo.getBannerList().get(position).getLinkUrl();
//                        ARouter.getInstance().build(RouterConstants.ROUTER_WEB_MAIN)
//                                .withString(WebViewConfig.KEY_URL, url)
//                                .navigation();
                    }
                });
        ImageLoaderUtils.getInstance().showImg(context,((Section)obj).getBackground(), convenientBanner);
    }

    public class MyViewHolder implements Holder<Item> {
        private View rootView;
        @Override
        public View createView(Context context) {
            rootView = LayoutInflater.from(context).inflate(R.layout.elf_template3_item, null, false);
            return rootView;
        }

        @Override
        public void UpdateUI(final Context context, final int position, Item item) {
            List<Widget> widgets = item.getWidgetList();
            TemplateHelper.fillView(context,widgets,rootView);
        }
    }
}
