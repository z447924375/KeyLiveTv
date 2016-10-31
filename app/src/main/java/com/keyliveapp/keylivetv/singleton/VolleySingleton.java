package com.keyliveapp.keylivetv.singleton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.keyliveapp.keylivetv.myapp.MyAppp;

/**
 * Created by dllo on 16/10/22.
 */

public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyAppp.getmContext());
    }

    public static VolleySingleton getInstance() {
        if (volleySingleton == null) {
            synchronized (VolleySingleton.class) {
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    public void add(Request request) {
        requestQueue.add(request);
    }

}
