package com.keyliveapp.keylivetv.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeBean {

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

        private List<BannerBean> banner;
        private List<QuickbuttonBean> quickbutton;
        private List<?> advert;
        private List<ColumnsBean> columns;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<QuickbuttonBean> getQuickbutton() {
            return quickbutton;
        }

        public void setQuickbutton(List<QuickbuttonBean> quickbutton) {
            this.quickbutton = quickbutton;
        }

        public List<?> getAdvert() {
            return advert;
        }

        public void setAdvert(List<?> advert) {
            this.advert = advert;
        }

        public List<ColumnsBean> getColumns() {
            return columns;
        }

        public void setColumns(List<ColumnsBean> columns) {
            this.columns = columns;
        }

        public static class BannerBean {
            private String id;
            private String cid;
            private String image;
            private String hrefType;
            private String hrefTarget;
            private String _index;
            private String title;
            private String type;
            private String tag;
            private String tag_color;
            private String tag_name;
            private String ustream_cat;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getHrefType() {
                return hrefType;
            }

            public void setHrefType(String hrefType) {
                this.hrefType = hrefType;
            }

            public String getHrefTarget() {
                return hrefTarget;
            }

            public void setHrefTarget(String hrefTarget) {
                this.hrefTarget = hrefTarget;
            }

            public String get_index() {
                return _index;
            }

            public void set_index(String _index) {
                this._index = _index;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTag_color() {
                return tag_color;
            }

            public void setTag_color(String tag_color) {
                this.tag_color = tag_color;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getUstream_cat() {
                return ustream_cat;
            }

            public void setUstream_cat(String ustream_cat) {
                this.ustream_cat = ustream_cat;
            }
        }

        public static class QuickbuttonBean {
            private String hrefType;
            private String hrefTarget;
            private String _index;
            private String title;
            private String image;
            private String type;

            public String getHrefType() {
                return hrefType;
            }

            public void setHrefType(String hrefType) {
                this.hrefType = hrefType;
            }

            public String getHrefTarget() {
                return hrefTarget;
            }

            public void setHrefTarget(String hrefTarget) {
                this.hrefTarget = hrefTarget;
            }

            public String get_index() {
                return _index;
            }

            public void set_index(String _index) {
                this._index = _index;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ColumnsBean {

            private int viewType;

            public int getViewType() {
                return viewType;
            }

            public void setViewType(int viewType) {
                this.viewType = viewType;
            }

            private GameBean game;
            private int viewers;
            private int channels;
            private String channelsText;

            private List<RoomsBean> rooms;

            public GameBean getGame() {
                return game;
            }

            public void setGame(GameBean game) {
                this.game = game;
            }

            public int getViewers() {
                return viewers;
            }

            public void setViewers(int viewers) {
                this.viewers = viewers;
            }

            public int getChannels() {
                return channels;
            }

            public void setChannels(int channels) {
                this.channels = channels;
            }

            public String getChannelsText() {
                return channelsText;
            }

            public void setChannelsText(String channelsText) {
                this.channelsText = channelsText;
            }

            public List<RoomsBean> getRooms() {
                return rooms;
            }

            public void setRooms(List<RoomsBean> rooms) {
                this.rooms = rooms;
            }

            public static class GameBean {
                private int id;
                private String name;
                private String title;
                private String tag;
                private String icon;
                private String sortby;
                private String template;

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

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public String getSortby() {
                    return sortby;
                }

                public void setSortby(String sortby) {
                    this.sortby = sortby;
                }

                public String getTemplate() {
                    return template;
                }

                public void setTemplate(String template) {
                    this.template = template;
                }
            }

            public static class RoomsBean {
                private String preview;
                private String preview2;
                /**
                 * Id : 113
                 * id : 113
                 * Name : 守望先锋
                 * name : 守望先锋
                 * tag : ow
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
                    private String Name;
                    private String tag;

                    public String getId() {
                        return Id;
                    }

                    public void setId(String Id) {
                        this.Id = Id;
                    }

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
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
}
