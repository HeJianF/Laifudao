package com.example.hjf.mylaifudao.utils;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.hjf.mylaifudao.app.MyApplication;

/**
 * @author heJianfeng
 * @date 2018/12/25
 */
public class ToastUtils {

    private static Toast toast = null;

    @SuppressLint("ShowToast")
    public static void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getApplication(), null, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }
}
