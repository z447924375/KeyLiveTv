package com.keyliveapp.keylivetv.bean;

import java.util.List;

/**
 * Created by dllo on 16/11/3.
 */

public class DomainBean {




    private int CityId;


    private BaseRoomInfoBean BaseRoomInfo;
    private int FlowerCount;
    private int OnlineCount;
    private boolean IsBroadcasting;
    private String Vid;
    private int CollegeId;
    private int Contribution;
    private int AppChatStatus;

    private BroadcastBean Broadcast;
    private int RoomGrade;

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int CityId) {
        this.CityId = CityId;
    }

    public BaseRoomInfoBean getBaseRoomInfo() {
        return BaseRoomInfo;
    }

    public void setBaseRoomInfo(BaseRoomInfoBean BaseRoomInfo) {
        this.BaseRoomInfo = BaseRoomInfo;
    }

    public int getFlowerCount() {
        return FlowerCount;
    }

    public void setFlowerCount(int FlowerCount) {
        this.FlowerCount = FlowerCount;
    }

    public int getOnlineCount() {
        return OnlineCount;
    }

    public void setOnlineCount(int OnlineCount) {
        this.OnlineCount = OnlineCount;
    }

    public boolean isIsBroadcasting() {
        return IsBroadcasting;
    }

    public void setIsBroadcasting(boolean IsBroadcasting) {
        this.IsBroadcasting = IsBroadcasting;
    }

    public String getVid() {
        return Vid;
    }

    public void setVid(String Vid) {
        this.Vid = Vid;
    }

    public int getCollegeId() {
        return CollegeId;
    }

    public void setCollegeId(int CollegeId) {
        this.CollegeId = CollegeId;
    }

    public int getContribution() {
        return Contribution;
    }

    public void setContribution(int Contribution) {
        this.Contribution = Contribution;
    }

    public int getAppChatStatus() {
        return AppChatStatus;
    }

    public void setAppChatStatus(int AppChatStatus) {
        this.AppChatStatus = AppChatStatus;
    }

    public BroadcastBean getBroadcast() {
        return Broadcast;
    }

    public void setBroadcast(BroadcastBean Broadcast) {
        this.Broadcast = Broadcast;
    }

    public int getRoomGrade() {
        return RoomGrade;
    }

    public void setRoomGrade(int RoomGrade) {
        this.RoomGrade = RoomGrade;
    }

    public static class BaseRoomInfoBean {
        private String Name;
        private String Avatar;
        private String Domain;
        private int Game;
        private String GameName;
        private int UserId;
        private String UserTitle;
        private String Desc;
        private int Type;
        private String AnchorCertification;
        private int AnchorCategory;
        private String BoardCastTitle;
        private String BoardCastAddress;
        private String VerifiedInformation;
        private int SubscribeCount;
        private int DailyPlayHourConfig;
        private String WriteTime;
        private int VideoPermission;
        private int LivePermission;
        private int Status;
        private int Id;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getAvatar() {
            return Avatar;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public String getDomain() {
            return Domain;
        }

        public void setDomain(String Domain) {
            this.Domain = Domain;
        }

        public int getGame() {
            return Game;
        }

        public void setGame(int Game) {
            this.Game = Game;
        }

        public String getGameName() {
            return GameName;
        }

        public void setGameName(String GameName) {
            this.GameName = GameName;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getUserTitle() {
            return UserTitle;
        }

        public void setUserTitle(String UserTitle) {
            this.UserTitle = UserTitle;
        }

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String Desc) {
            this.Desc = Desc;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getAnchorCertification() {
            return AnchorCertification;
        }

        public void setAnchorCertification(String AnchorCertification) {
            this.AnchorCertification = AnchorCertification;
        }

        public int getAnchorCategory() {
            return AnchorCategory;
        }

        public void setAnchorCategory(int AnchorCategory) {
            this.AnchorCategory = AnchorCategory;
        }

        public String getBoardCastTitle() {
            return BoardCastTitle;
        }

        public void setBoardCastTitle(String BoardCastTitle) {
            this.BoardCastTitle = BoardCastTitle;
        }

        public String getBoardCastAddress() {
            return BoardCastAddress;
        }

        public void setBoardCastAddress(String BoardCastAddress) {
            this.BoardCastAddress = BoardCastAddress;
        }

        public String getVerifiedInformation() {
            return VerifiedInformation;
        }

        public void setVerifiedInformation(String VerifiedInformation) {
            this.VerifiedInformation = VerifiedInformation;
        }

        public int getSubscribeCount() {
            return SubscribeCount;
        }

        public void setSubscribeCount(int SubscribeCount) {
            this.SubscribeCount = SubscribeCount;
        }

        public int getDailyPlayHourConfig() {
            return DailyPlayHourConfig;
        }

        public void setDailyPlayHourConfig(int DailyPlayHourConfig) {
            this.DailyPlayHourConfig = DailyPlayHourConfig;
        }

        public String getWriteTime() {
            return WriteTime;
        }

        public void setWriteTime(String WriteTime) {
            this.WriteTime = WriteTime;
        }

        public int getVideoPermission() {
            return VideoPermission;
        }

        public void setVideoPermission(int VideoPermission) {
            this.VideoPermission = VideoPermission;
        }

        public int getLivePermission() {
            return LivePermission;
        }

        public void setLivePermission(int LivePermission) {
            this.LivePermission = LivePermission;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }

    public static class BroadcastBean {
        private int LiveSource;
        private int LiveStreamType;
        private int PlayId;
        private int RoomId;
        private String BeginTime;
        private int GameId;
        private String GameName;
        private int ParentGameId;
        private String ParentGameName;
        private String Title;
        private String Html;
        private int UserId;
        private String Cover;
        private String UpStreamUrl;
        private double Longitude;
        private double Latitude;
        private String Address;
        private String Model;
        private int LiveSourceType;
        private String WatchDirections;
        private int OS;
        private int LiveType;


        private List<ChannelsBean> Channels;

        public int getLiveSource() {
            return LiveSource;
        }

        public void setLiveSource(int LiveSource) {
            this.LiveSource = LiveSource;
        }

        public int getLiveStreamType() {
            return LiveStreamType;
        }

        public void setLiveStreamType(int LiveStreamType) {
            this.LiveStreamType = LiveStreamType;
        }

        public int getPlayId() {
            return PlayId;
        }

        public void setPlayId(int PlayId) {
            this.PlayId = PlayId;
        }

        public int getRoomId() {
            return RoomId;
        }

        public void setRoomId(int RoomId) {
            this.RoomId = RoomId;
        }

        public String getBeginTime() {
            return BeginTime;
        }

        public void setBeginTime(String BeginTime) {
            this.BeginTime = BeginTime;
        }

        public int getGameId() {
            return GameId;
        }

        public void setGameId(int GameId) {
            this.GameId = GameId;
        }

        public String getGameName() {
            return GameName;
        }

        public void setGameName(String GameName) {
            this.GameName = GameName;
        }

        public int getParentGameId() {
            return ParentGameId;
        }

        public void setParentGameId(int ParentGameId) {
            this.ParentGameId = ParentGameId;
        }

        public String getParentGameName() {
            return ParentGameName;
        }

        public void setParentGameName(String ParentGameName) {
            this.ParentGameName = ParentGameName;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getHtml() {
            return Html;
        }

        public void setHtml(String Html) {
            this.Html = Html;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getCover() {
            return Cover;
        }

        public void setCover(String Cover) {
            this.Cover = Cover;
        }

        public String getUpStreamUrl() {
            return UpStreamUrl;
        }

        public void setUpStreamUrl(String UpStreamUrl) {
            this.UpStreamUrl = UpStreamUrl;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String Model) {
            this.Model = Model;
        }

        public int getLiveSourceType() {
            return LiveSourceType;
        }

        public void setLiveSourceType(int LiveSourceType) {
            this.LiveSourceType = LiveSourceType;
        }

        public String getWatchDirections() {
            return WatchDirections;
        }

        public void setWatchDirections(String WatchDirections) {
            this.WatchDirections = WatchDirections;
        }

        public int getOS() {
            return OS;
        }

        public void setOS(int OS) {
            this.OS = OS;
        }

        public int getLiveType() {
            return LiveType;
        }

        public void setLiveType(int LiveType) {
            this.LiveType = LiveType;
        }

        public List<ChannelsBean> getChannels() {
            return Channels;
        }

        public void setChannels(List<ChannelsBean> Channels) {
            this.Channels = Channels;
        }

        public static class ChannelsBean {
            private String Name;
            private String Code;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }
        }
    }
}
