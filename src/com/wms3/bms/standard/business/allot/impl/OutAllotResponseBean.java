package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wms3.bms.standard.business.allot.impl.OutAllotVerifyRequestBean.TrayStorageBean;

/**
 * ���������Ӧ����
 * @author yangwm
 *
 */
public class OutAllotResponseBean implements Serializable{

    /**  */
    private static final long serialVersionUID = 8651094084805908288L;
    
    public String cargoSpaceId;     // ��λID
    public Integer csplatoon;       // ��λ��
    public Integer cscolumn;        // ��λ��
    public Integer csfloor;         // ��λ��
    public String warehouseid;      // �ֿ�ID
    public String whAreaId;         // ����ID
    public String cargoAlleyId;     // ���ID
    public String oldstatus;        // �����λǰ��״̬(�������ʧ�ܣ���ԭ��λ����ǰ��״̬)
    
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
            public String inventoryoldstatus;  //������ǰ��״̬ (�������ʧ�ܣ���ԭ������ǰ��״̬)
            
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
             * ����: ��÷�����ǰ��״̬ (�������ʧ�ܣ���ԭ������ǰ��״̬)
             * @author hug 2012-9-19 
             * @return
             */
            public String getInventoryoldstatus() {
                return inventoryoldstatus;
            }
            /**
             * ����: ���÷�����ǰ��״̬ (�������ʧ�ܣ���ԭ������ǰ��״̬)
             * @author hug 2012-9-19 
             * @param inventoryoldstatus
             */
            public void setInventoryoldstatus(String inventoryoldstatus) {
                this.inventoryoldstatus = inventoryoldstatus;
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
        public OutAllotResponseBean.TrayStorageBean.StorageBean getNewStorageBean(){
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
    public OutAllotResponseBean.TrayStorageBean getNewTrayStorageBean(){
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
     * ����:������ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getCargoAlleyId() {
        return cargoAlleyId;
    }
    /**
     * ����:�������ID
     * @author hug 2012-9-6 
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * ����:��û�λID
     * @author hug 2012-9-6 
     * @return
     */
    public String getCargoSpaceId() {
        return cargoSpaceId;
    }
    /**
     * ����:���û�λID
     * @author hug 2012-9-6 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    /**
     * ����:��û�λ��
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getCscolumn() {
        return cscolumn;
    }
    /**
     * ����:���û�λ��
     * @author hug 2012-9-6 
     * @param cscolumn
     */
    public void setCscolumn(Integer cscolumn) {
        this.cscolumn = cscolumn;
    }
    /**
     * ����:��û�λ��
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getCsfloor() {
        return csfloor;
    }
    /**
     * ����:���û�λ��
     * @author hug 2012-9-6 
     * @param csfloor
     */
    public void setCsfloor(Integer csfloor) {
        this.csfloor = csfloor;
    }
    /**
     * ����:��û�λ��
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getCsplatoon() {
        return csplatoon;
    }
    /**
     * ����:���û�λ��
     * @author hug 2012-9-6 
     * @param csplatoon
     */
    public void setCsplatoon(Integer csplatoon) {
        this.csplatoon = csplatoon;
    }
    /** 
     * ����:��÷����λǰ��״̬
     * @author hug 2012-9-6 
     * @return
     */
    public String getOldstatus() {
        return oldstatus;
    }
    /**
     * ����:���÷����λǰ��״̬
     * @author hug 2012-9-6 
     * @param oldstatus
     */
    public void setOldstatus(String oldstatus) {
        this.oldstatus = oldstatus;
    }
    /**
     * ����:��òֿ�ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getWarehouseid() {
        return warehouseid;
    }
    /**
     * ����:���òֿ�ID
     * @author hug 2012-9-6 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * ����:��ÿ���ID
     * @author hug 2012-9-6 
     * @return
     */
    public String getWhAreaId() {
        return whAreaId;
    }
    /**
     * ����:���ÿ���ID
     * @author hug 2012-9-6 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    
}
