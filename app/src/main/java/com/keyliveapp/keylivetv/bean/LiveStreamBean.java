package com.keyliveapp.keylivetv.bean;

import java.util.List;

/**
 * Created by dllo on 16/11/3.
 */

public class LiveStreamBean {


    /**
     * liveUrl : http://star.longzhu.com/153157/
     * inbandwidth : 1577.71875
     * isTransfer : 0
     * playLines : [{"lineType":0,"urls":[{"rateLevel":3,"securityUrl":"http://flvtx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531.flv?txSecret=fd08fba3f5bdbdbe026c68c94e37c9b8&txTime=581b1eb0","resolution":"1920x1080","timeMove":false,"ext":"flv"},{"rateLevel":3,"securityUrl":"rtmp://rtmptx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531?txSecret=fd08fba3f5bdbdbe026c68c94e37c9b8&txTime=581b1eb0","resolution":"1920x1080","timeMove":false,"ext":"rtmp"},{"rateLevel":3,"securityUrl":"http://hlstx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531.m3u8?txSecret=90417df9acf5cdb97fcc05ceeb879492&txTime=581c6dd8","resolution":"1920x1080","timeMove":false,"ext":"m3u8"}],"playLiveStreamType":12}]
     * pushLiveStreamType : 12
     * liveSourceType : 16
     * defaultLine : 0
     * defaultRateLevel : 3
     */

    private String liveUrl;
    private double inbandwidth;
    private int isTransfer;
    private int pushLiveStreamType;
    private int liveSourceType;
    private int defaultLine;
    private int defaultRateLevel;
    /**
     * lineType : 0
     * urls : [{"rateLevel":3,"securityUrl":"http://flvtx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531.flv?txSecret=fd08fba3f5bdbdbe026c68c94e37c9b8&txTime=581b1eb0","resolution":"1920x1080","timeMove":false,"ext":"flv"},{"rateLevel":3,"securityUrl":"rtmp://rtmptx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531?txSecret=fd08fba3f5bdbdbe026c68c94e37c9b8&txTime=581b1eb0","resolution":"1920x1080","timeMove":false,"ext":"rtmp"},{"rateLevel":3,"securityUrl":"http://hlstx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531.m3u8?txSecret=90417df9acf5cdb97fcc05ceeb879492&txTime=581c6dd8","resolution":"1920x1080","timeMove":false,"ext":"m3u8"}]
     * playLiveStreamType : 12
     */

    private List<PlayLinesBean> playLines;

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public double getInbandwidth() {
        return inbandwidth;
    }

    public void setInbandwidth(double inbandwidth) {
        this.inbandwidth = inbandwidth;
    }

    public int getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(int isTransfer) {
        this.isTransfer = isTransfer;
    }

    public int getPushLiveStreamType() {
        return pushLiveStreamType;
    }

    public void setPushLiveStreamType(int pushLiveStreamType) {
        this.pushLiveStreamType = pushLiveStreamType;
    }

    public int getLiveSourceType() {
        return liveSourceType;
    }

    public void setLiveSourceType(int liveSourceType) {
        this.liveSourceType = liveSourceType;
    }

    public int getDefaultLine() {
        return defaultLine;
    }

    public void setDefaultLine(int defaultLine) {
        this.defaultLine = defaultLine;
    }

    public int getDefaultRateLevel() {
        return defaultRateLevel;
    }

    public void setDefaultRateLevel(int defaultRateLevel) {
        this.defaultRateLevel = defaultRateLevel;
    }

    public List<PlayLinesBean> getPlayLines() {
        return playLines;
    }

    public void setPlayLines(List<PlayLinesBean> playLines) {
        this.playLines = playLines;
    }

    public static class PlayLinesBean {
        private int lineType;
        private int playLiveStreamType;
        /**
         * rateLevel : 3
         * securityUrl : http://flvtx.plu.cn/onlive/afc94a9c44d04a82a7ff77398cd8b531.flv?txSecret=fd08fba3f5bdbdbe026c68c94e37c9b8&txTime=581b1eb0
         * resolution : 1920x1080
         * timeMove : false
         * ext : flv
         */

        private List<UrlsBean> urls;

        public int getLineType() {
            return lineType;
        }

        public void setLineType(int lineType) {
            this.lineType = lineType;
        }

        public int getPlayLiveStreamType() {
            return playLiveStreamType;
        }

        public void setPlayLiveStreamType(int playLiveStreamType) {
            this.playLiveStreamType = playLiveStreamType;
        }

        public List<UrlsBean> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlsBean> urls) {
            this.urls = urls;
        }

        public static class UrlsBean {
            private int rateLevel;
            private String securityUrl;
            private String resolution;
            private boolean timeMove;
            private String ext;

            public int getRateLevel() {
                return rateLevel;
            }

            public void setRateLevel(int rateLevel) {
                this.rateLevel = rateLevel;
            }

            public String getSecurityUrl() {
                return securityUrl;
            }

            public void setSecurityUrl(String securityUrl) {
                this.securityUrl = securityUrl;
            }

            public String getResolution() {
                return resolution;
            }

            public void setResolution(String resolution) {
                this.resolution = resolution;
            }

            public boolean isTimeMove() {
                return timeMove;
            }

            public void setTimeMove(boolean timeMove) {
                this.timeMove = timeMove;
            }

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }
        }
    }
}
