package com.keyliveapp.keylivetv.tools;

/**
 * Created by dllo on 16/10/25.
 */

public interface OnCompletedListener<T> {
    void onCompleted(T result);

    void onFailed();
}
