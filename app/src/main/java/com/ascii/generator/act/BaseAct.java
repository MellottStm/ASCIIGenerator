package com.ascii.generator.act;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseAct extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init (){

    }

}
