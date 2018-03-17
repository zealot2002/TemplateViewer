package com.credithc.elf_page.utils;

import android.util.Log;

/**
 * @author zzy
 * @date 2018/3/9
 */

public class Mylog {
    public static void e(String s){
        if(s.length() > 4000) {
            for(int i=0;i<s.length();i+=4000){
                if(i+4000<s.length())
                    Log.e("zzy"+i,s.substring(i, i+4000));
                else
                    Log.e("zzy"+i,s.substring(i, s.length()));
            }
        } else
            Log.e("zzy",s);
    }
}
