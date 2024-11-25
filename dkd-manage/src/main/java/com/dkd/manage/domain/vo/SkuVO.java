package com.dkd.manage.domain.vo;


import com.dkd.manage.domain.TbSku;

import java.math.BigDecimal;

public class SkuVO extends TbSku {

    //商品类型名称
    private String skuClassName;

    //前端显示价格
    private BigDecimal priceVo;

    public String getSkuClassName() {
        return skuClassName;
    }

    public void setSkuClassName(String skuClassName) {
        this.skuClassName = skuClassName;
    }

    public BigDecimal getPriceVo() {
        return priceVo;
    }

    public void setPriceVo(BigDecimal priceVo) {
        this.priceVo = priceVo;
    }
}
