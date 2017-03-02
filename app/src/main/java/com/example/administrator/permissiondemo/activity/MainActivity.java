package com.example.administrator.permissiondemo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.permissiondemo.R;
import com.example.administrator.permissiondemo.myinterface.PermissionRequestListener;

import java.security.Permission;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_ASK_CALL_PHONE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionRequestListener() {
                    @Override
                    public void onGrantedPermission() {
                        Toast.makeText(MainActivity.this,"所有权限都同意了",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDeniedPermission(List<String> permissionList) {
                        for(String persmission:permissionList){
                            Toast.makeText(MainActivity.this,"被拒绝的权限："+persmission,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void check() {
        //如果版本大于19 ，就判断权限是否申请
        if (Build.VERSION.SDK_INT >= 23){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                //如果权限不被允许，重新申请
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }else {
                Toast.makeText(this,"用户已经同意您的权限",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
