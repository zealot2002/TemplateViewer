package com.credithc.templateviewer;

/**
 * @author zzy
 * @date 2018/2/28
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.credithc.common.utils.ImageLoaderUtils;
import com.credithc.templateviewer.home.HomeActivity;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.zzy.commonlib.base.BaseActivity;
import com.zzy.commonlib.core.BusHelper;

public class MainActivity extends BaseActivity {

    private Button btnGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(it);
            }
        });
    }
}