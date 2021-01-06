package com.xiaoyou.face.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * 一些常用的工具类
 * @author 小游
 * @date 2020/12/18
 */
public class Tools {

    /**
     * 随机获取一个颜色
     * @return 颜色值
     */
    public static int getRandomColor(){
        // 存储颜色数组
        String[] colors = new String[]{
                "#1abc9c",
                "#2ecc71",
                "#3498db",
                "#9b59b6",
                "#34495e",
                "#f1c40f",
                "#e67e22",
                "#e74c3c",
                "#ecf0f1",
                "#95a5a6",
                "#95a5a6",
        };
        // 随机获取一个颜色
        Random random = new Random();
        return Color.parseColor(colors[random.nextInt(colors.length)]);
    }


    /**
     * 获取系统的屏幕宽度
     * @param context context对象
     * @return 返回当前宽度(单位px)
     */
    public static int getWidth(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }


}
