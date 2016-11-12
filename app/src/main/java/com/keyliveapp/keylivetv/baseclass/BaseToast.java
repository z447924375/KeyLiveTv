package com.keyliveapp.keylivetv.baseclass;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dllo on 16/11/4.
 */

public class BaseToast {

    private static Toast toast;

    public static void showToast(Context context, String content) {

        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }

        toast.show();

    }



}
