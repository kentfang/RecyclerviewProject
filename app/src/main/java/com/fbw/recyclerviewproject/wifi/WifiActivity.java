package com.fbw.recyclerviewproject.wifi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fbw.recyclerviewproject.R;
import com.fbw.recyclerviewproject.utils.LogUtil;

public class WifiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        requestDangerousPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.ACCESS_WIFI_STATE},0);
    }


    public void requestDangerousPermissions(String[] permissions, int requestCode) {
        if (checkDangerousPermissions(permissions)){
            LogUtil.d("fbw","requestDangerousPermissions");
            handlePermissionResult(requestCode, true);
            return;
        }
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    public boolean handlePermissionResult(int requestCode, boolean granted) {
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LogUtil.d("fbw","requestCode:"+requestCode);
        boolean granted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                granted = false;
            }
        }
        boolean finish = handlePermissionResult(requestCode, granted);
        if (!finish){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public boolean checkDangerousPermissions(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return false;
            }
        }
        return true;
    }
}
