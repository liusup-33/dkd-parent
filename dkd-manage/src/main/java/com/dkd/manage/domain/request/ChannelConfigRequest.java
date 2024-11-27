package com.dkd.manage.domain.request;

public class ChannelConfigRequest {

    private String innerCode;


    private ChannelListRequest[] channelList;


    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public ChannelListRequest[] getChannelList() {
        return channelList;
    }

    public void setChannelList(ChannelListRequest[] channelList) {
        this.channelList = channelList;
    }
}
