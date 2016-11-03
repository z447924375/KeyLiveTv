package com.keyliveapp.keylivetv.bean;

import java.util.List;

/**
 * Created by dllo on 16/11/3.
 */

public class LiveStreamBean {

    private String liveUrl;
    private int inbandwidth;
    private int isTransfer;
    private int pushLiveStreamType;
    private int liveSourceType;
    private int defaultLine;
    private int defaultRateLevel;

    private List<PlayLinesBean> playLines;

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public int getInbandwidth() {
        return inbandwidth;
    }

    public void setInbandwidth(int inbandwidth) {
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
