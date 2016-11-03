package com.keyliveapp.keylivetv.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/31.
 */

public class ClassfyAllBean {

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
        private int offset;
        private int limit;
        private String sort;


        private List<ItemsBean> items;

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private String preview;
            private String preview2;
            /**
             * Id : 4
             * id : 4
             * Name : 英雄联盟
             * name : 英雄联盟
             * tag : lol
             */

            private GameBean game;

            private ChannelBean channel;
            private int viewers;

            public String getPreview() {
                return preview;
            }

            public void setPreview(String preview) {
                this.preview = preview;
            }

            public String getPreview2() {
                return preview2;
            }

            public void setPreview2(String preview2) {
                this.preview2 = preview2;
            }

            public GameBean getGame() {
                return game;
            }

            public void setGame(GameBean game) {
                this.game = game;
            }

            public ChannelBean getChannel() {
                return channel;
            }

            public void setChannel(ChannelBean channel) {
                this.channel = channel;
            }

            public int getViewers() {
                return viewers;
            }

            public void setViewers(int viewers) {
                this.viewers = viewers;
            }

            public static class GameBean {
                private String Id;
                private String id;
                private String Name;
                private String name;
                private String tag;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

                public String getid() {
                    return id;
                }

                public void setid(String id) {
                    this.id = id;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getname() {
                    return name;
                }

                public void setname(String name) {
                    this.name = name;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }
            }

            public static class ChannelBean {
                private String id;
                private String name;
                private String avatar;
                private int grade;
                private String url;
                private String status;
                private String broadcast_begin;
                private String broadcast_duration;
                private String videos;
                private String _type;
                private String _subtype;
                private String belle;
                private String domain;
                private String flowers;
                private String followers;
                private String glamours;
                private String tag;
                private int vid;
                private int weight;
                private int live_source;
                private int stream_types;

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

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getBroadcast_begin() {
                    return broadcast_begin;
                }

                public void setBroadcast_begin(String broadcast_begin) {
                    this.broadcast_begin = broadcast_begin;
                }

                public String getBroadcast_duration() {
                    return broadcast_duration;
                }

                public void setBroadcast_duration(String broadcast_duration) {
                    this.broadcast_duration = broadcast_duration;
                }

                public String getVideos() {
                    return videos;
                }

                public void setVideos(String videos) {
                    this.videos = videos;
                }

                public String get_type() {
                    return _type;
                }

                public void set_type(String _type) {
                    this._type = _type;
                }

                public String get_subtype() {
                    return _subtype;
                }

                public void set_subtype(String _subtype) {
                    this._subtype = _subtype;
                }

                public String getBelle() {
                    return belle;
                }

                public void setBelle(String belle) {
                    this.belle = belle;
                }

                public String getDomain() {
                    return domain;
                }

                public void setDomain(String domain) {
                    this.domain = domain;
                }

                public String getFlowers() {
                    return flowers;
                }

                public void setFlowers(String flowers) {
                    this.flowers = flowers;
                }

                public String getFollowers() {
                    return followers;
                }

                public void setFollowers(String followers) {
                    this.followers = followers;
                }

                public String getGlamours() {
                    return glamours;
                }

                public void setGlamours(String glamours) {
                    this.glamours = glamours;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public int getVid() {
                    return vid;
                }

                public void setVid(int vid) {
                    this.vid = vid;
                }

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
                    this.weight = weight;
                }

                public int getLive_source() {
                    return live_source;
                }

                public void setLive_source(int live_source) {
                    this.live_source = live_source;
                }

                public int getStream_types() {
                    return stream_types;
                }

                public void setStream_types(int stream_types) {
                    this.stream_types = stream_types;
                }
            }
        }
    }
}
