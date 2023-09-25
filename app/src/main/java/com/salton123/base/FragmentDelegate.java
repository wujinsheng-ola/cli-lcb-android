package com.salton123.base;

import android.app.Activity;

import androidx.fragment.app.Fragment;

/**
 * User: newSalton@outlook.com
 * Date: 2019/3/19 10:12
 * ModifyTime: 10:12
 * Description:
 */
public class FragmentDelegate extends LifeDelegate {
    private Activity mHost;

    public FragmentDelegate(IComponentLife componentLife) {
        super(componentLife);
        if (componentLife instanceof Fragment) {
            mHost = ((Fragment) componentLife).getActivity();
        } else {
            throw new RuntimeException("instance must Fragment");
        }
    }

    @Override
    Activity activity() {
        return mHost;
    }

}