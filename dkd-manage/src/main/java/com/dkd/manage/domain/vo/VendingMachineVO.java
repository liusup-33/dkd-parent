package com.dkd.manage.domain.vo;

import com.dkd.manage.domain.VendingMachine;

public class VendingMachineVO extends VendingMachine {

    private String partnerName;

    private String regionName;

    private String vmTypeName;

    private String nodeName;

    private String vmStatusName;

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getVmTypeName() {
        return vmTypeName;
    }

    public void setVmTypeName(String vmTypeName) {
        this.vmTypeName = vmTypeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getVmStatusName() {
        return vmStatusName;
    }

    public void setVmStatusName(String vmStatusName) {
        this.vmStatusName = vmStatusName;
    }
}
