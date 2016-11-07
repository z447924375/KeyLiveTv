package com.keyliveapp.keylivetv.values;

/**
 * Created by dllo on 16/10/25.
 */

public class URLvalues {
    //主页
    public static final String
            HOME_PAGE_URL = "https://a4.plu.cn/api/home/personal?version=3.7.0&device=4&packageId=1";

    //分类
    public static final String
            CLASSIFY_URL_FRONT = "https://a4.plu.cn/api/streams?start-index=0&max-results=30&game=";
    public static final String
            CLASSIFY_URL_BEHIND = "&version=3.7.0&device=4&packageId=1";

    //发现
    public static final String
            DISCOVERY_URL_NEARBY = "https://a4.plu.cn/api/discover/recommend?tab=newest&version=3.7.0&device=4&packageId=1";
    public static final String
            DISCOVERY_URL_HOTTEST = "https://a4.plu.cn/api/ustream/streams?start-index=0&max-results=30&version=3.7.0&device=4&packageId=1";
    public static final String
            DISCOVERY_URL_NEWEST = "https://a4.plu.cn/api/discover/recommend?tab=newest&version=3.7.0&device=4&packageId=1";

    //home中的domian 的拼接前后
    public static final String
            DOMAIN_URL_FRONT = "http://roomapicdn.plu.cn/room/RoomAppStatusV2?domain=";
    public static final String
            DOMAIN_URL_BEHIND = "&version=3.7.0&device=4&packageId=1";
    public static final String
            STREAM_URL_FRONT = "http://livestream.plu.cn/live/GetLivePlayUrl?roomId=";
    public static final String
            STREAN_URL_BEHIND = "&appId=4001&version=3.7.0&device=4&packageId=1";

}
