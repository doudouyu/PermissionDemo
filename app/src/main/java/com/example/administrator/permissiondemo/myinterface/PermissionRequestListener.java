package com.example.administrator.permissiondemo.myinterface;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public interface PermissionRequestListener {
    void onGrantedPermission();
    void onDeniedPermission(List<String> permissionList);
}
