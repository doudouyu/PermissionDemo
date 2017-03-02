package com.example.administrator.permissiondemo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.permissiondemo.myinterface.PermissionRequestListener;
import com.example.administrator.permissiondemo.util.ActivityManger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class BaseActivity extends AppCompatActivity {
    private static PermissionRequestListener mListener;
    private static Context _context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _context = this;
        ActivityManger.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.remove(this);
    }

    public  static  void requestRuntimePermission(String[] permissions, PermissionRequestListener listener) {
        //获取当前的栈顶activity
        Activity topActivity = ActivityManger.getTopActivity();
        if (topActivity == null) {
            return;
        }
        mListener = listener;
        //创建一个集合用来放置未申请到的权限
        List<String> permissionList = new ArrayList();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
                //说明权限被拒绝,判断用户是否拒绝再次提醒
                if (ActivityCompat.shouldShowRequestPermissionRationale(topActivity, permission)){
                    Toast.makeText(_context,"您已经关闭权限"+permission+"请您更改设置！",Toast.LENGTH_SHORT).show();
                }else {
                    // 重新申请
                    topActivity.requestPermissions(new String[]{permission}, 1);
                }

            }
        }
        if (permissionList.isEmpty()) {
            //说明权限已经全部获取
            listener.onGrantedPermission();
        } else {
            topActivity.requestPermissions(permissionList.toArray(new String[permissionList.size()]), 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                List<String> permissionList = new ArrayList<>();
                if (grantResults != null && grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        String permission = permissions[i];
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            permissionList.add(permission);
                        }
                    }
                    if (permissionList.isEmpty()) {
                        mListener.onGrantedPermission();
                    } else {
                        mListener.onDeniedPermission(permissionList);
                    }
                }

                break;
            default:
                break;
        }
    }
}
