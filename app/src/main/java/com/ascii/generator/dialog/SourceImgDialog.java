package com.ascii.generator.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.ascii.generator.R;
import com.ascii.generator.app.Configure;
import java.io.File;
import java.io.FileOutputStream;

public class SourceImgDialog extends BaseDialog{


    private Bitmap bitmap;

    private String text;

    private ImageView img;

    private Context context;

    public SourceImgDialog(@NonNull Context context,Bitmap bitmap,String text) {
        super(context);
        this.context = context;
        this.bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());
        this.text = text;
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.source_img_dialog);
        img = findViewById(R.id.source_img);
        img.setImageBitmap(bitmap);
        Toast.makeText(context,"开始绘制...",Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mainGenerator(bitmap,text);
            }
        }).start();
    }

    private void mainGenerator(Bitmap bitmap, String text) {
        int numCols = 100;
        int numRows = (numCols * bitmap.getHeight()) / bitmap.getWidth();
        int charWidth = 20;
        int charHeight = 20;
        int outWidth = numCols * charWidth;
        int outHeight = numRows * charHeight;
        Bitmap resultBitMap = Bitmap.createBitmap(outWidth,outHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitMap);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(charHeight);
        System.out.println("num_rows:" + numRows + ",num_cols:" + numCols);
        int index = 0;
        for (int i = 0; i < numCols; i++) {
            for (int j = 0;j < numRows; j++){
                int pixel = bitmap.getPixel(i * bitmap.getWidth() / numCols,j * bitmap.getHeight() / numRows);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                paint.setColor(Color.rgb(r,g,b));
                char t = text.charAt(index);
                if (index < text.length() - 1) {
                    index ++;
                } else {
                    index = 0;
                }
                canvas.drawText(String.valueOf(t),i * charWidth,j * charHeight, paint);
            }
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,"绘制完成!",Toast.LENGTH_LONG).show();
                try {
                    FileOutputStream outputStream = new FileOutputStream(new File(Configure.externalPath,"result.jpg"));
                    resultBitMap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    ResultImgDialog resultImgDialog = new ResultImgDialog(context);
                    resultImgDialog.show();
                    dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

