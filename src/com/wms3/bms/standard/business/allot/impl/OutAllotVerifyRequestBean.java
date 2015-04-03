package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 出库分配验证请求对象
 * @author yangwm
 *
 */
public class OutAllotVerifyRequestBean implements Serializable{
    
    /**  */
    private static final long serialVersionUID = -7994100467891170505L;

    public String cargoSpaceId;     // 货位ID
    
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
        public OutAllotVerifyRequestBean.TrayStorageBean.StorageBean getNewStorageBean(){
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
    public OutAllotVerifyRequestBean.TrayStorageBean getNewTrayStorageBean(){
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
     * 功能: 获得货位ID
     * @author hug 2012-9-25 
     * @return
     */
    public String getCargoSpaceId() {
        return cargoSpaceId;
    }
    /**
     * 功能: 设置货位ID
     * @author hug 2012-9-25 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    
}
