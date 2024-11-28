package com.dkd.manage.domain.request;

public class TaskDetailRequest {


    private String channelCode;

    private Long expectCapacity;

    private Long skuId;

    private String skuName;

    private String skuImage;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Long getExpectCapacity() {
        return expectCapacity;
    }

    public void setExpectCapacity(Long expectCapacity) {
        this.expectCapacity = expectCapacity;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public void setSkuImage(String skuImage) {
        this.skuImage = skuImage;
    }
}
