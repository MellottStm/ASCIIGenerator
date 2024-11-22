package com.ascii.generator.dialog;

import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;

import com.ascii.generator.R;
import com.ascii.generator.app.Configure;
import com.github.chrisbanes.photoview.PhotoView;

public class ResultImgDialog extends BaseDialog{


    private PhotoView img;

    public ResultImgDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.result_img_dialog);
        img = findViewById(R.id.img);
        img.setImageBitmap(BitmapFactory.decodeFile(Configure.externalPath+"/result.jpg"));
    }
}
