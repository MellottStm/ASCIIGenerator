package com.ascii.generator.act;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.ascii.generator.R;
import com.ascii.generator.app.Configure;
import com.ascii.generator.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;

public class StartAct extends BaseAct{

    private ActivityMainBinding binding;

    private final int REQUESTCODE = 1;


    private static final String[] PERMISSIONS_REQUIRED = new String[] {
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.MANAGE_EXTERNAL_STORAGE"
    };
    @Override
    protected void init() {
        super.init();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        permission();
        startActivity(new Intent(this, MainAct.class));
    }

    private void permission() {
        for (String permission : PERMISSIONS_REQUIRED) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 请求权限
                ActivityCompat.requestPermissions(this, PERMISSIONS_REQUIRED, REQUESTCODE);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "请在设置中开启权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
