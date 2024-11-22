package com.ascii.generator.app;

import android.app.Application;
import android.os.Environment;

import java.io.File;

public class ASCIGenerator extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configure.externalPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
}
