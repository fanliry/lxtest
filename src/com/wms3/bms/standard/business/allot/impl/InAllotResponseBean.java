package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 入库分配响应对象
 * @author yangwm
 *
 */
public class InAllotResponseBean implements Serializable{

    /**  */
    private static final long serialVersionUID = -1479180717586022341L;
    
    //返回的是一个托盘的信息。如果有多个托盘分配一个货位，就是返回该对象列表
    
    public String cargoSpaceId;     // 货位ID
    public Integer csplatoon;       // 货位排
    public Integer cscolumn;        // 货位列
    public Integer csfloor;         // 货位层
    public String warehouseid;      // 仓库ID
    public String whAreaId;         // 库区ID
    public String cargoAlleyId;     // 巷道ID
    public String oldstatus;        // 分配货位前的状态
    public String trayCode;         // 托盘条码
    
    public List<ProductBean> lsProductBean = new ArrayList<ProductBean>();   //ProductBean实例列表
    
    /** 一个托盘上的产品信息   */
    public class ProductBean
    {
        public String strTransId;   //收货记录ID（跟踪号）
        public String transStatus;  //要修改的状态
        public String productid;    //产品
        public String packid;       //包装
        public String eunit;        //单位
        public double putnum;       //上架数量
        public double putweight;    //上架重量
        public double putnetweight; //上架净重
        public double putvolume;    //上架体积  
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
        
        public String ownerid;      //货主
        public String reinvoiceid;         //收货单据编号
        public String reinvoicedetailid;   //收货单详细ID
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
         * 功能:获得上架净重
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutnetweight() {
            return putnetweight;
        }

        /**
         * 功能:设置上架净重
         * @author hug 2012-8-31 
         * @param putnetweight
         */
        public void setPutnetweight(double putnetweight) {
            this.putnetweight = putnetweight;
        }

        /**
         * 功能:获得上架数量
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutnum() {
            return putnum;
        }

        /**
         * 功能:设置上架数量
         * @author hug 2012-8-31 
         * @param putnum
         */
        public void setPutnum(double putnum) {
            this.putnum = putnum;
        }

        /**
         * 功能:获得上架体积
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutvolume() {
            return putvolume;
        }

        /**
         * 功能:设置上架体积
         * @author hug 2012-8-31 
         * @param putvolume
         */
        public void setPutvolume(double putvolume) {
            this.putvolume = putvolume;
        }

        /**
         * 功能:获得上架重量
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutweight() {
            return putweight;
        }

        /**
         * 功能:设置上架重量
         * @author hug 2012-8-31 
         * @param putweight
         */
        public void setPutweight(double putweight) {
            this.putweight = putweight;
        }

        /**
         * 功能:获得收货记录ID（跟踪号）
         * @author hug 2012-8-31 
         * @return
         */
        public String getStrTransId() {
            return strTransId;
        }

        /**
         * 功能:设置收货记录ID（跟踪号）
         * @author hug 2012-8-31 
         * @param strTransId
         */
        public void setStrTransId(String strTransId) {
            this.strTransId = strTransId;
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
         * 功能：获得收货单详细ID
         */
        public String getReinvoicedetailid() {
            return reinvoicedetailid;
        }

        /**
         * 功能：设置收货单详细ID
         * @param reincoicedetailid
         */
        public void setReinvoicedetailid(String reinvoicedetailid) {
            this.reinvoicedetailid = reinvoicedetailid;
        }

        /**
         * 功能：获得收货单据编号
         */
        public String getReinvoiceid() {
            return reinvoiceid;
        }

        /**
         * 功能：设置收货单据编号
         * @param reinvoiceid
         */
        public void setReinvoiceid(String reinvoiceid) {
            this.reinvoiceid = reinvoiceid;
        }
        /**
         * 功能:获得要修改的状态
         * @author hug 2012-9-6 
         * @return
         */
        public String getTransStatus() {
            return transStatus;
        }

        /**
         * 功能:设置要修改的状态
         * @author hug 2012-9-6 
         * @param transStatus
         */
        public void setTransStatus(String transStatus) {
            this.transStatus = transStatus;
        }
    }
    
    
    /**
     * 功能:返回ProductBean实例
     * @author hug 2012-8-31 
     * @return
     */
    public InAllotResponseBean.ProductBean getNewProductBean(){  
        return new ProductBean();
    } 
    /**
     * 
     * 功能:增加ProductBean对象
     * @author hug 2012-8-31 
     * @param product
     */
    public void addProductBean(ProductBean product)
    {
        lsProductBean.add(product);
    }
    /** 
     * 功能：返回ProductBean对象大小
     * @author hug 2012-8-31 
     * @return
     */
    public int getProductBeanSize(){
        return lsProductBean.size();
    }
    /**
     * 功能:返回ProductBean对象列表
     * @author hug 2012-8-31 
     * @return
     */
    public List<ProductBean> getListProductBean()
    {
        return lsProductBean;
    }
    
    /**
     * 功能:获得托盘条码
     * @author hug 2012-9-3 
     * @return
     */
    public String getTrayCode() {
        return trayCode;
    }
    /**
     * 功能:设置托盘条码
     * @author hug 2012-9-3 
     * @param trayCode
     */
    public void setTrayCode(String trayCode) {
        this.trayCode = trayCode;
    }
    /**
     * 功能:获得巷道ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getCargoAlleyId() {
        return cargoAlleyId;
    }
    /**
     * 功能:设置巷道ID
     * @author hug 2012-9-6 
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * 功能:获得货位ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getCargoSpaceId() {
        return cargoSpaceId;
    }
    /**
     * 功能:设置货位ID
     * @author hug 2012-9-6 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    /**
     * 功能:获得货位列
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getCscolumn() {
        return cscolumn;
    }
    /**
     * 功能:设置货位列
     * @author hug 2012-9-6 
     * @param cscolumn
     */
    public void setCscolumn(Integer cscolumn) {
        this.cscolumn = cscolumn;
    }
    /**
     * 功能:获得货位层
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getCsfloor() {
        return csfloor;
    }
    /**
     * 功能:设置货位层
     * @author hug 2012-9-6 
     * @param csfloor
     */
    public void setCsfloor(Integer csfloor) {
        this.csfloor = csfloor;
    }
    /**
     * 功能:获得货位排
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getCsplatoon() {
        return csplatoon;
    }
    /**
     * 功能:设置货位排
     * @author hug 2012-9-6 
     * @param csplatoon
     */
    public void setCsplatoon(Integer csplatoon) {
        this.csplatoon = csplatoon;
    }
    /** 
     * 功能:获得分配货位前的状态
     * @author hug 2012-9-6 
     * @return
     */
    public String getOldstatus() {
        return oldstatus;
    }
    /**
     * 功能:设置分配货位前的状态
     * @author hug 2012-9-6 
     * @param oldstatus
     */
    public void setOldstatus(String oldstatus) {
        this.oldstatus = oldstatus;
    }
    /**
     * 功能:获得仓库ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getWarehouseid() {
        return warehouseid;
    }
    /**
     * 功能:设置仓库ID
     * @author hug 2012-9-6 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * 功能:获得库区ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getWhAreaId() {
        return whAreaId;
    }
    /**
     * 功能:设置库区ID
     * @author hug 2012-9-6 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    
    
	
}
