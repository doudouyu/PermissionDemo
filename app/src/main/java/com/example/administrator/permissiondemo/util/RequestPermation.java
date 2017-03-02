package com.example.administrator.permissiondemo.util;

import com.example.administrator.permissiondemo.activity.BaseActivity;
import com.example.administrator.permissiondemo.myinterface.PermissionRequestListener;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class RequestPermation {
    public void text(){
        BaseActivity.requestRuntimePermission(new String[]{}, new PermissionRequestListener() {

            @Override
            public void onGrantedPermission() {

            }

            @Override
            public void onDeniedPermission(List<String> permissionList) {

            }
        });
    }
}
