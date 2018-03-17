package com.credithc.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.autolayout.config.AutoLayoutConifg;

public class BaseApplication extends Application {
    private static BaseApplication sInstance;
    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
//        if (AppConfig.ISDEBUG) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
//        }
        ARouter.init(this);//初始化ARouter路由器
    }

}
