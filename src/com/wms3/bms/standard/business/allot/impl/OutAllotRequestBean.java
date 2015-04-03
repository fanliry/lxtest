package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;

/**
 * 出库分配请求对象
 * @author yangwm
 *
 */
public class OutAllotRequestBean implements Serializable{

    /**  */
    private static final long serialVersionUID = -4051757872468981279L;
    
    public String outstockid;           //出库单据号
    public String outstockdetailid;     //出库单详细ID
    public String warehouseid;          //仓库ID
    public String whAreaId;             //库区ID
    public String ownerid;              //货主
    public String customerid;           //客户ID(收货人)
    public OutAllotRequestBean.ProductBean productBean;
    
    /** 出库单详细的产品信息  *************************************** */
    public class ProductBean
    {
        public String productid;    //产品
        public String packid;       //包装
        public String eunit;        //单位
        public double num;          //分配数量
        public double weight;       //分配重量
        public double netweight;    //分配净重
        public double volume;       //分配体积  
        public String lotid;        //批次类型ID
        public String lotatt1;      //批次属性1
        public String lotatt2;      //批次属性2
        public String lotatt3;      //批次属性3
        public String lotatt4;      //批次属性4
        public String lotatt5;      //批次属性5
        public String lotatt6;      //批次属性6
        public String lotatt7;      //批次属性7
        public String lotatt8;      //批次属性8
        public String lotatt9;      //批次属性9
        public String lotatt10;     //批次属性10
        public String lotatt11;     //批次属性11
        public String lotatt12;     //批次属性12
        
     
        /**
         * 功能:获得包装ID
         * @author hug 2012-8-31 
         * @return
         */
        public String getPackid() {
            return packid;
        }

        /**
         * 功能:设置包装ID
         * @author hug 2012-8-31 
         * @param packid
         */
        public void setPackid(String packid) {
            this.packid = packid;
        }

        /**
         * 功能:获得产品
         * @author hug 2012-8-31 
         * @return
         */
        public String getProductid() {
            return productid;
        }

        /**
         * 功能:设置产品
         * @author hug 2012-8-31 
         * @param productid
         */
        public void setProductid(String productid) {
            this.productid = productid;
        }

        /**
         * 功能:获得分配净重
         * @author hug 2012-8-31 
         * @return
         */
        public double getNetweight() {
            return netweight;
        }

        /**
         * 功能:设置分配净重
         * @author hug 2012-8-31 
         * @param putnetweight
         */
        public void setNetweight(double netweight) {
            this.netweight = netweight;
        }

        /**
         * 功能:获得分配数量
         * @author hug 2012-8-31 
         * @return
         */
        public double getNum() {
            return num;
        }

        /**
         * 功能:设置分配数量
         * @author hug 2012-8-31 
         * @param putnum
         */
        public void setNum(double num) {
            this.num = num;
        }

        /**
         * 功能:获得分配体积
         * @author hug 2012-8-31 
         * @return
         */
        public double getVolume() {
            return volume;
        }

        /**
         * 功能:设置分配体积
         * @author hug 2012-8-31 
         * @param putvolume
         */
        public void setVolume(double volume) {
            this.volume = volume;
        }

        /**
         * 功能:获得分配重量
         * @author hug 2012-8-31 
         * @return
         */
        public double getWeight() {
            return weight;
        }

        /**
         * 功能:设置分配重量
         * @author hug 2012-8-31 
         * @param putweight
         */
        public void setWeight(double weight) {
            this.weight = weight;
        }

        /**
         * 功能：获得批次属性1
         */
        public String getLotatt1() {
            return lotatt1;
        }

        /**
         * 功能：设置批次属性1
         * @param lotatt1
         */
        public void setLotatt1(String lotatt1) {
            this.lotatt1 = lotatt1;
        }

        /**
         * 功能：获得批次属性10
         */
        public String getLotatt10() {
            return lotatt10;
        }

        /**
         * 功能：设置批次属性10
         * @param lotatt10
         */
        public void setLotatt10(String lotatt10) {
            this.lotatt10 = lotatt10;
        }

        /**
         * 功能：获得批次属性11
         */
        public String getLotatt11() {
            return lotatt11;
        }

        /**
         * 功能：设置批次属性11
         * @param lotatt11
         */
        public void setLotatt11(String lotatt11) {
            this.lotatt11 = lotatt11;
        }

        /**
         * 功能：获得批次属性12
         */
        public String getLotatt12() {
            return lotatt12;
        }

        /**
         * 功能：设置批次属性12
         * @param lotatt12
         */
        public void setLotatt12(String lotatt12) {
            this.lotatt12 = lotatt12;
        }

        /**
         * 功能：获得批次属性2
         */
        public String getLotatt2() {
            return lotatt2;
        }

        /**
         * 功能：设置批次属性2
         * @param lotatt2
         */
        public void setLotatt2(String lotatt2) {
            this.lotatt2 = lotatt2;
        }

        /**
         * 功能：获得批次属性3
         */
        public String getLotatt3() {
            return lotatt3;
        }

        /**
         * 功能：设置批次属性3
         * @param lotatt3
         */
        public void setLotatt3(String lotatt3) {
            this.lotatt3 = lotatt3;
        }

        /**
         * 功能：获得批次属性4
         */
        public String getLotatt4() {
            return lotatt4;
        }

        /**
         * 功能：设置批次属性4
         * @param lotatt4
         */
        public void setLotatt4(String lotatt4) {
            this.lotatt4 = lotatt4;
        }

        /**
         * 功能：获得批次属性5
         */
        public String getLotatt5() {
            return lotatt5;
        }

        /**
         * 功能：设置批次属性5
         * @param lotatt5
         */
        public void setLotatt5(String lotatt5) {
            this.lotatt5 = lotatt5;
        }

        /**
         * 功能：获得批次属性6
         */
        public String getLotatt6() {
            return lotatt6;
        }

        /**
         * 功能：设置批次属性6
         * @param lotatt6
         */
        public void setLotatt6(String lotatt6) {
            this.lotatt6 = lotatt6;
        }

        /**
         * 功能：获得批次属性7
         */
        public String getLotatt7() {
            return lotatt7;
        }

        /**
         * 功能：设置批次属性7
         * @param lotatt7
         */
        public void setLotatt7(String lotatt7) {
            this.lotatt7 = lotatt7;
        }

        /**
         * 功能：获得批次属性8
         */
        public String getLotatt8() {
            return lotatt8;
        }

        /**
         * 功能：设置批次属性8
         * @param lotatt8
         */
        public void setLotatt8(String lotatt8) {
            this.lotatt8 = lotatt8;
        }

        /**
         * 功能：获得批次属性9
         */
        public String getLotatt9() {
            return lotatt9;
        }

        /**
         * 功能：设置批次属性9
         * @param lotatt9
         */
        public void setLotatt9(String lotatt9) {
            this.lotatt9 = lotatt9;
        }

        /**
         * 功能：获得批次类型ID
         */
        public String getLotid() {
            return lotid;
        }

        /**
         * 功能：设置批次类型ID
         * @param lotid
         */
        public void setLotid(String lotid) {
            this.lotid = lotid;
        }

        /**
         * 功能：获得单位
         */
        public String getEunit() {
            return eunit;
        }

        /**
         * 功能：设置单位
         * @param eunit
         */
        public void setEunit(String eunit) {
            this.eunit = eunit;
        }
     
    }
    /**   *************************************** */
    
    /**
     * 功能:获得货主
     * @author hug 2012-8-31 
     * @return
     */
    public String getOwnerid() {
        return ownerid;
    }
    /**
     * 功能:设置货主
     * @author hug 2012-8-31 
     * @param ownerid
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }
    /**
     * 功能: 获得客户ID(收货人)
     * @author hug 2012-9-19 
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }
    /**
     * 功能: 设置客户ID(收货人)
     * @author hug 2012-9-19 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /**
     * 功能: 获得出库单详细ID
     * @author hug 2012-9-19 
     * @return
     */
    public String getOutstockdetailid() {
        return outstockdetailid;
    }
    /**
     * 功能:  设置出库单详细ID
     * @author hug 2012-9-19 
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }
    /**
     * 功能:  获得出库单据号
     * @author hug 2012-9-19 
     * @return
     */
    public String getOutstockid() {
        return outstockid;
    }
    /**
     * 功能:  设置出库单据号
     * @author hug 2012-9-19 
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }
    /**
     * 功能: 获得仓库ID
     * @author hug 2012-9-19 
     * @return
     */
    public String getWarehouseid() {
        return warehouseid;
    }
    /**
     * 功能: 设置仓库ID
     * @author hug 2012-9-19 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * 功能: 获得库区ID
     * @author hug 2012-9-19 
     * @return
     */
    public String getWhAreaId() {
        return whAreaId;
    }
    /**
     * 功能: 设置库区ID
     * @author hug 2012-9-19 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * 功能: 获得出库单详细的产品信息
     * @author hug 2012-9-20 
     * @return
     */
    public OutAllotRequestBean.ProductBean getProductBean() {
        return productBean;
    }
    /**
     * 功能:设置出库单详细的产品信息
     * @author hug 2012-9-20 
     * @param productBean
     */
    public void setProductBean(OutAllotRequestBean.ProductBean productBean) {
        this.productBean = productBean;
    }
    
    public OutAllotRequestBean.ProductBean newProductBean() {
        return new OutAllotRequestBean.ProductBean();
    }
    
}
