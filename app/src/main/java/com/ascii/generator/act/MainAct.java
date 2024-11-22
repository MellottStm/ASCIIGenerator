package com.ascii.generator.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.ascii.generator.R;
import com.ascii.generator.app.Configure;
import com.ascii.generator.databinding.MainActBinding;
import com.ascii.generator.dialog.SourceImgDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainAct extends BaseAct {

    private MainActBinding binding;

    private final int REQUESTCODE = 1;

    @Override
    protected void init() {
        super.init();
        binding = DataBindingUtil.setContentView(this, R.layout.main_act);
        binding.edt.setText("转载请注明出处");
        binding.tip.setText("请选择图片");
        binding.fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUESTCODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // 获取图片的Uri
            Uri imageUri = data.getData();
            // 可以选择使用Glide或者Picasso等库来加载图片，这里使用最基本的方法
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                SourceImgDialog sourceImgDialog = new SourceImgDialog(this,bitmap,binding.edt.getText().toString());
                sourceImgDialog.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
