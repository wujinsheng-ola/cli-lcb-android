package com.salton123.utils;


import sg.partying.lcb.android.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/23 16:53
 * ModifyTime: 16:53
 * Description:
 */
public class MaterialColors {

    public static final int[] colors = new int[]{
            com.salton123.resources.R.color.color_f44336_material,
            com.salton123.resources.R.color.color_e91e63_material,
            com.salton123.resources.R.color.color_9c27b0_material,
            com.salton123.resources.R.color.color_673ab7_material,
            com.salton123.resources.R.color.color_3f51b5_material,
            com.salton123.resources.R.color.color_2196f3_material,
            com.salton123.resources.R.color.color_03a9f4_material,
            com.salton123.resources.R.color.color_00bcd4_material,
            com.salton123.resources.R.color.color_009688_material,
            com.salton123.resources.R.color.color_4caf50_material,
            com.salton123.resources.R.color.color_cddc39_material,
            com.salton123.resources.R.color.color_ffeb3b_material,
            com.salton123.resources.R.color.color_ffc107_material,
            com.salton123.resources.R.color.color_ff9800_material,
            com.salton123.resources.R.color.color_ff5722_material,
            com.salton123.resources.R.color.color_795548_material,
            com.salton123.resources.R.color.color_9e9e9e_material,
            com.salton123.resources.R.color.color_607d8b_material
    };

    public static int random() {
        int index = (int) (Math.random() * colors.length);
        System.out.println("MaterialColors,random:"+index);
        return colors[index];
    }
}
