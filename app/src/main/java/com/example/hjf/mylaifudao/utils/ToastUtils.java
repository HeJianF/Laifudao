package com.example.hjf.mylaifudao.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.hjf.mylaifudao.app.MyApplication;

/**
 * @author heJianfeng
 * @date 2018/12/25
 */
public class ToastUtils {

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast;

    @SuppressLint("ShowToast")
    public static void showToast(String message) {
        handler.post(() -> {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.getApplication(), null, Toast.LENGTH_SHORT);
            }
            toast.setText(message);
            toast.show();
        });
    }
}
