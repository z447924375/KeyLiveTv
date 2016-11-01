package com.keyliveapp.keylivetv.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/27.
 */

public class ClassifyBean {

    private String apiVersion;

    private DataBean data;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int totalItems;
        private List<?> tags;

        private List<RecommendBean> recommend;

        private List<ItemsBean> items;

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class RecommendBean {

            private GameBean game;
            private int channels;
            private int viewers;

            public GameBean getGame() {
                return game;
            }

            public void setGame(GameBean game) {
                this.game = game;
            }

            public int getChannels() {
                return channels;
            }

            public void setChannels(int channels) {
                this.channels = channels;
            }

            public int getViewers() {
                return viewers;
            }

            public void setViewers(int viewers) {
                this.viewers = viewers;
            }

            public static class GameBean {
                private String id;
                private String name;
                private String logo;
                private String tag;
                private String icon;
                private String url;
                private int parentid;
                private List<?> subgames;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getParentid() {
                    return parentid;
                }

                public void setParentid(int parentid) {
                    this.parentid = parentid;
                }

                public List<?> getSubgames() {
                    return subgames;
                }

                public void setSubgames(List<?> subgames) {
                    this.subgames = subgames;
                }
            }
        }

        public static class ItemsBean {

            private GameBean game;

            public GameBean getGame() {
                return game;
            }

            public void setGame(GameBean game) {
                this.game = game;
            }

            public static class GameBean {
                private int id;
                private String name;
                private String tag;
                private String sortby;
                private String logo;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getSortby() {
                    return sortby;
                }

                public void setSortby(String sortby) {
                    this.sortby = sortby;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }
            }
        }
    }
}
