package com.dkd.manage.domain.request;

import java.util.List;

public class TaskRequest {

    private Long createType;

    private String innerCode;

    private Long userId;

    private Long assignorId;

    private Long productTypeId;

    private String desc;

    private List<TaskDetailRequest> details;

    public Long getCreateType() {
        return createType;
    }

    public void setCreateType(Long createType) {
        this.createType = createType;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAssignorId() {
        return assignorId;
    }

    public void setAssignorId(Long assignorId) {
        this.assignorId = assignorId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<TaskDetailRequest> getDetails() {
        return details;
    }

    public void setDetails(List<TaskDetailRequest> details) {
        this.details = details;
    }
}
