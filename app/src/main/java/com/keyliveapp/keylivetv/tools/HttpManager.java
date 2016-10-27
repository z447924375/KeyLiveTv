package com.keyliveapp.keylivetv.tools;

import java.util.Map;

/**
 * Created by dllo on 16/10/25.
 */
public class HttpManager implements IHttpRequest {

    private static final String TAG = "HttpManager";


    private static final class SingletonHolder {
        private static final HttpManager sInstance = new HttpManager();
    }


    public static HttpManager getInstance() {
        return SingletonHolder.sInstance;
    }

    private IHttpRequest mRequest;

    private HttpManager() {
        mRequest = new OkHttpImpl();
    }

    @Override
    public <T> void getRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.getRequest(urlStr, clazz, listener);
    }

    @Override
    public <T> void getRequest(String urlStr, Map<String, String> header, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.getRequest(urlStr, header, clazz, listener);
    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr, requestBody, clazz, listener);
    }

    @Override
    public <T> void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody, Class<T> clazz, OnCompletedListener<T> listener) {
        mRequest.postRequest(urlStr, headers, requestBody, clazz, listener);
    }
}
