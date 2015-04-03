package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wms3.bms.standard.business.allot.impl.OutAllotVerifyRequestBean.TrayStorageBean;

/**
 * 出库分配响应对象
 * @author yangwm
 *
 */
public class OutAllotResponseBean implements Serializable{

    /**  */
    private static final long serialVersionUID = 8651094084805908288L;
    
    public String cargoSpaceId;     // 货位ID
    public Integer csplatoon;       // 货位排
    public Integer cscolumn;        // 货位列
    public Integer csfloor;         // 货位层
    public String warehouseid;      // 仓库ID
    public String whAreaId;         // 库区ID
    public String cargoAlleyId;     // 巷道ID
    public String oldstatus;        // 分配货位前的状态(如果保存失败，则还原货位分配前的状态)
    
    public List<TrayStorageBean> lsTrayStorageBean = new ArrayList<TrayStorageBean>();
    
    //一个货位下可能有多个托盘
    public class TrayStorageBean
    {
        public String trayCode;     //托盘条码
        public List<StorageBean> lsStorageBean = new ArrayList<StorageBean>();
        
        public class StorageBean
        {
            public String inventoryid;  //库存ID
            public double num;          //分配数量
            public double weight;       //分配重量
            public double netweight;    //分配净重
            public double volume;       //分配体积    
            public String inventoryoldstatus;  //分配库存前的状态 (如果保存失败，则还原库存分配前的状态)
            
            /**
             * 功能: 获得库存ID
             * @author hug 2012-9-19 
             * @return
             */
            public String getInventoryid() {
                return inventoryid;
            }
            /** 
             * 功能: 设置库存ID
             * @author hug 2012-9-19 
             * @param inventoryid
             */
            public void setInventoryid(String inventoryid) {
                this.inventoryid = inventoryid;
            }
            /**
             * 功能: 获得分配库存前的状态 (如果保存失败，则还原库存分配前的状态)
             * @author hug 2012-9-19 
             * @return
             */
            public String getInventoryoldstatus() {
                return inventoryoldstatus;
            }
            /**
             * 功能: 设置分配库存前的状态 (如果保存失败，则还原库存分配前的状态)
             * @author hug 2012-9-19 
             * @param inventoryoldstatus
             */
            public void setInventoryoldstatus(String inventoryoldstatus) {
                this.inventoryoldstatus = inventoryoldstatus;
            }
            /**
             * 功能: 获得分配净重
             * @author hug 2012-9-19 
             * @return
             */
            public double getNetweight() {
                return netweight;
            }
            /** 
             * 功能: 设置分配净重
             * @author hug 2012-9-19 
             * @param netweight
             */
            public void setNetweight(double netweight) {
                this.netweight = netweight;
            }
            /** 
             * 功能: 获得分配数量
             * @author hug 2012-9-19 
             * @return
             */
            public double getNum() {
                return num;
            }
            /**
             * 功能: 设置分配数量
             * @author hug 2012-9-19 
             * @param num
             */
            public void setNum(double num) {
                this.num = num;
            }
            /**
             * 功能: 获得分配体积 
             * @author hug 2012-9-19 
             * @return
             */
            public double getVolume() {
                return volume;
            }
            /** 
             * 功能: 设置分配体积 
             * @author hug 2012-9-19 
             * @param volume
             */
            public void setVolume(double volume) {
                this.volume = volume;
            }
            /**
             * 功能: 获得分配重量
             * @author hug 2012-9-19 
             * @return
             */
            public double getWeight() {
                return weight;
            }
            /**
             * 功能: 设置分配重量
             * @author hug 2012-9-19 
             * @param weight
             */
            public void setWeight(double weight) {
                this.weight = weight;
            }
      
        }
        
        /**
         * 功能: 返回StorageBean实例
         * @author hug 2012-9-19 
         * @return
         */
        public OutAllotResponseBean.TrayStorageBean.StorageBean getNewStorageBean(){
            return new StorageBean();
        }
        /**
         * 功能: 增加StorageBean对象
         * @author hug 2012-9-19 
         * @param storage
         */
        public void addStorageBean(StorageBean storage){
            lsStorageBean.add(storage);
        }
        /**
         * 功能:返回StorageBean对象大小
         * @author hug 2012-9-19 
         * @return
         */
        public int getStorageBeanSize(){
            return lsStorageBean.size();
        }
        /**
         * 功能:返回StorageBean对象列表
         * @author hug 2012-9-19 
         * @return
         */
        public List<StorageBean> getListStorageBean(){
            return lsStorageBean;
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
    }

    
    /**
     * 功能: 返回TrayStorageBean实例
     * @author hug 2012-9-19 
     * @return
     */
    public OutAllotResponseBean.TrayStorageBean getNewTrayStorageBean(){
        return new TrayStorageBean();
    }
    /**
     * 功能: 增加TrayStorageBean对象
     * @author hug 2012-9-19 
     * @param storage
     */
    public void addTrayStorageBean(TrayStorageBean storage){
        lsTrayStorageBean.add(storage);
    }
    /**
     * 功能:返回TrayStorageBean对象大小
     * @author hug 2012-9-19 
     * @return
     */
    public int getTrayStorageBeanSize(){
        return lsTrayStorageBean.size();
    }
    /**
     * 功能:返回TrayStorageBean对象列表
     * @author hug 2012-9-19 
     * @return
     */
    public List<TrayStorageBean> getListTrayStorageBean(){
        return lsTrayStorageBean;
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
