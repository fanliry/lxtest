package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * ���������֤�������
 * @author yangwm
 *
 */
public class OutAllotVerifyRequestBean implements Serializable{
    
    /**  */
    private static final long serialVersionUID = -7994100467891170505L;

    public String cargoSpaceId;     // ��λID
    
    public List<TrayStorageBean> lsTrayStorageBean = new ArrayList<TrayStorageBean>();
    
    //һ����λ�¿����ж������
    public class TrayStorageBean
    {
        public String trayCode;     //��������
        public List<StorageBean> lsStorageBean = new ArrayList<StorageBean>();
        
        public class StorageBean
        {
            public String inventoryid;  //���ID
            public double num;          //��������
            public double weight;       //��������
            public double netweight;    //���侻��
            public double volume;       //�������    
            
            /**
             * ����: ��ÿ��ID
             * @author hug 2012-9-19 
             * @return
             */
            public String getInventoryid() {
                return inventoryid;
            }
            /** 
             * ����: ���ÿ��ID
             * @author hug 2012-9-19 
             * @param inventoryid
             */
            public void setInventoryid(String inventoryid) {
                this.inventoryid = inventoryid;
            }

            /**
             * ����: ��÷��侻��
             * @author hug 2012-9-19 
             * @return
             */
            public double getNetweight() {
                return netweight;
            }
            /** 
             * ����: ���÷��侻��
             * @author hug 2012-9-19 
             * @param netweight
             */
            public void setNetweight(double netweight) {
                this.netweight = netweight;
            }
            /** 
             * ����: ��÷�������
             * @author hug 2012-9-19 
             * @return
             */
            public double getNum() {
                return num;
            }
            /**
             * ����: ���÷�������
             * @author hug 2012-9-19 
             * @param num
             */
            public void setNum(double num) {
                this.num = num;
            }
            /**
             * ����: ��÷������ 
             * @author hug 2012-9-19 
             * @return
             */
            public double getVolume() {
                return volume;
            }
            /** 
             * ����: ���÷������ 
             * @author hug 2012-9-19 
             * @param volume
             */
            public void setVolume(double volume) {
                this.volume = volume;
            }
            /**
             * ����: ��÷�������
             * @author hug 2012-9-19 
             * @return
             */
            public double getWeight() {
                return weight;
            }
            /**
             * ����: ���÷�������
             * @author hug 2012-9-19 
             * @param weight
             */
            public void setWeight(double weight) {
                this.weight = weight;
            }
            
      
        }
        
        /**
         * ����: ����StorageBeanʵ��
         * @author hug 2012-9-19 
         * @return
         */
        public OutAllotVerifyRequestBean.TrayStorageBean.StorageBean getNewStorageBean(){
            return new StorageBean();
        }
        /**
         * ����: ����StorageBean����
         * @author hug 2012-9-19 
         * @param storage
         */
        public void addStorageBean(StorageBean storage){
            lsStorageBean.add(storage);
        }
        /**
         * ����:����StorageBean�����С
         * @author hug 2012-9-19 
         * @return
         */
        public int getStorageBeanSize(){
            return lsStorageBean.size();
        }
        /**
         * ����:����StorageBean�����б�
         * @author hug 2012-9-19 
         * @return
         */
        public List<StorageBean> getListStorageBean(){
            return lsStorageBean;
        }
        
        /**
         * ����:�����������
         * @author hug 2012-9-3 
         * @return
         */
        public String getTrayCode() {
            return trayCode;
        }
        /**
         * ����:������������
         * @author hug 2012-9-3 
         * @param trayCode
         */
        public void setTrayCode(String trayCode) {
            this.trayCode = trayCode;
        }
    }
    /**
     * ����: ����TrayStorageBeanʵ��
     * @author hug 2012-9-19 
     * @return
     */
    public OutAllotVerifyRequestBean.TrayStorageBean getNewTrayStorageBean(){
        return new TrayStorageBean();
    }
    /**
     * ����: ����TrayStorageBean����
     * @author hug 2012-9-19 
     * @param storage
     */
    public void addTrayStorageBean(TrayStorageBean storage){
        lsTrayStorageBean.add(storage);
    }
    /**
     * ����:����TrayStorageBean�����С
     * @author hug 2012-9-19 
     * @return
     */
    public int getTrayStorageBeanSize(){
        return lsTrayStorageBean.size();
    }
    /**
     * ����:����TrayStorageBean�����б�
     * @author hug 2012-9-19 
     * @return
     */
    public List<TrayStorageBean> getListTrayStorageBean(){
        return lsTrayStorageBean;
    }

    
    /**
     * ����: ��û�λID
     * @author hug 2012-9-25 
     * @return
     */
    public String getCargoSpaceId() {
        return cargoSpaceId;
    }
    /**
     * ����: ���û�λID
     * @author hug 2012-9-25 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    
}
