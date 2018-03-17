package com.credithc.elf_page.view.template.engine;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.credithc.common.utils.ImageLoaderUtils;
import com.credithc.elf_page.R;
import com.credithc.elf_page.constants.PageTypeEnum;
import com.credithc.elf_page.model.Item;
import com.credithc.elf_page.model.Section;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author zzy
 * @date 2018/2/11
 */

public class BannerEngine implements ItemViewDelegate {
    private Context context;
    private int layoutId,templateId;
    private ConvenientBanner convenientBanner;
/*****************************************************************************************************/
    public BannerEngine(Context context, int templateId, int layoutId) {
        this.context = context;
        this.templateId = templateId;
        this.layoutId = layoutId;
    }

    public BannerEngine() {}
    @Override
    public int getItemViewLayoutId() {
        return layoutId;
    }

    @Override
    public boolean isForViewType(Object obj, int position) {
        if(obj instanceof Section){
            return ((Section)obj).getTemplateId()==templateId;
        }else{
            return false;
        }
    }

    @Override
    public void convert(ViewHolder holder,Object obj, int position) {
        if(obj instanceof Section){
            convenientBanner = holder.getView(R.id.convenientBanner);
            convenientBanner.setPages(
                    new CBViewHolderCreator<BannerViewHolder>() {
                        @Override
                        public BannerViewHolder createHolder() {
                            return new BannerViewHolder();
                        }
                    }, ((Section)obj).getItemList())
                    .setPageIndicator(new int[]{R.mipmap.elf_banner_indicator, R.mipmap.elf_banner_indicator_focused})
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
//        convenientBanner.startTurning(AUTO_TURNING_TIME);
        }
    }

    public class BannerViewHolder implements Holder<Item> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Item item) {
            try{
                if(!item.getWidgetList().isEmpty()){
                    ImageLoaderUtils.getInstance().showImg(context,item.getWidgetList().get(1).getImage(), imageView);
                }
            }catch(Exception e){
                e.printStackTrace();
                Log.e("zzy","err: "+e.toString());
            }
        }
    }
}
