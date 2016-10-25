package com.keyliveapp.keylivetv.tools;

import java.util.Map;

/**
 * Created by dllo on 16/10/25.
 */

public interface IHttpRequest {
    /**
     * 普通的GET请求
     *
     * @param urlStr   请求的URL
     * @param clazz   解析的实体类的结果
     * @param listener 网络请求的结果回调
     * @param <T>      解析的实体类类型
     */
    <T> void getRequest(String urlStr, Class<T> clazz, OnCompletedListener<T> listener);

    /**
     * 带请求头的GET请求
     * @param urlStr
     * @param header 请求头
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T> void getRequest(String urlStr, Map<String, String> header,
                        Class<T> clazz, OnCompletedListener<T> listener);

    /**
     *
     * @param urlStr
     * @param requestBody
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T>void postRequest(String urlStr, Map<String, String> requestBody,
                        Class<T> clazz, OnCompletedListener<T> listener);

    /**
     *
     * @param urlStr
     * @param headers
     * @param requestBody
     * @param clazz
     * @param listener
     * @param <T>
     */
    <T>void postRequest(String urlStr, Map<String, String> headers, Map<String, String> requestBody,
                        Class<T> clazz, OnCompletedListener<T> listener);

}
