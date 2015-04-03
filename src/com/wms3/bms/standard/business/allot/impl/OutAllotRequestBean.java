package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;

/**
 * ��������������
 * @author yangwm
 *
 */
public class OutAllotRequestBean implements Serializable{

    /**  */
    private static final long serialVersionUID = -4051757872468981279L;
    
    public String outstockid;           //���ⵥ�ݺ�
    public String outstockdetailid;     //���ⵥ��ϸID
    public String warehouseid;          //�ֿ�ID
    public String whAreaId;             //����ID
    public String ownerid;              //����
    public String customerid;           //�ͻ�ID(�ջ���)
    public OutAllotRequestBean.ProductBean productBean;
    
    /** ���ⵥ��ϸ�Ĳ�Ʒ��Ϣ  *************************************** */
    public class ProductBean
    {
        public String productid;    //��Ʒ
        public String packid;       //��װ
        public String eunit;        //��λ
        public double num;          //��������
        public double weight;       //��������
        public double netweight;    //���侻��
        public double volume;       //�������  
        public String lotid;        //��������ID
        public String lotatt1;      //��������1
        public String lotatt2;      //��������2
        public String lotatt3;      //��������3
        public String lotatt4;      //��������4
        public String lotatt5;      //��������5
        public String lotatt6;      //��������6
        public String lotatt7;      //��������7
        public String lotatt8;      //��������8
        public String lotatt9;      //��������9
        public String lotatt10;     //��������10
        public String lotatt11;     //��������11
        public String lotatt12;     //��������12
        
     
        /**
         * ����:��ð�װID
         * @author hug 2012-8-31 
         * @return
         */
        public String getPackid() {
            return packid;
        }

        /**
         * ����:���ð�װID
         * @author hug 2012-8-31 
         * @param packid
         */
        public void setPackid(String packid) {
            this.packid = packid;
        }

        /**
         * ����:��ò�Ʒ
         * @author hug 2012-8-31 
         * @return
         */
        public String getProductid() {
            return productid;
        }

        /**
         * ����:���ò�Ʒ
         * @author hug 2012-8-31 
         * @param productid
         */
        public void setProductid(String productid) {
            this.productid = productid;
        }

        /**
         * ����:��÷��侻��
         * @author hug 2012-8-31 
         * @return
         */
        public double getNetweight() {
            return netweight;
        }

        /**
         * ����:���÷��侻��
         * @author hug 2012-8-31 
         * @param putnetweight
         */
        public void setNetweight(double netweight) {
            this.netweight = netweight;
        }

        /**
         * ����:��÷�������
         * @author hug 2012-8-31 
         * @return
         */
        public double getNum() {
            return num;
        }

        /**
         * ����:���÷�������
         * @author hug 2012-8-31 
         * @param putnum
         */
        public void setNum(double num) {
            this.num = num;
        }

        /**
         * ����:��÷������
         * @author hug 2012-8-31 
         * @return
         */
        public double getVolume() {
            return volume;
        }

        /**
         * ����:���÷������
         * @author hug 2012-8-31 
         * @param putvolume
         */
        public void setVolume(double volume) {
            this.volume = volume;
        }

        /**
         * ����:��÷�������
         * @author hug 2012-8-31 
         * @return
         */
        public double getWeight() {
            return weight;
        }

        /**
         * ����:���÷�������
         * @author hug 2012-8-31 
         * @param putweight
         */
        public void setWeight(double weight) {
            this.weight = weight;
        }

        /**
         * ���ܣ������������1
         */
        public String getLotatt1() {
            return lotatt1;
        }

        /**
         * ���ܣ�������������1
         * @param lotatt1
         */
        public void setLotatt1(String lotatt1) {
            this.lotatt1 = lotatt1;
        }

        /**
         * ���ܣ������������10
         */
        public String getLotatt10() {
            return lotatt10;
        }

        /**
         * ���ܣ�������������10
         * @param lotatt10
         */
        public void setLotatt10(String lotatt10) {
            this.lotatt10 = lotatt10;
        }

        /**
         * ���ܣ������������11
         */
        public String getLotatt11() {
            return lotatt11;
        }

        /**
         * ���ܣ�������������11
         * @param lotatt11
         */
        public void setLotatt11(String lotatt11) {
            this.lotatt11 = lotatt11;
        }

        /**
         * ���ܣ������������12
         */
        public String getLotatt12() {
            return lotatt12;
        }

        /**
         * ���ܣ�������������12
         * @param lotatt12
         */
        public void setLotatt12(String lotatt12) {
            this.lotatt12 = lotatt12;
        }

        /**
         * ���ܣ������������2
         */
        public String getLotatt2() {
            return lotatt2;
        }

        /**
         * ���ܣ�������������2
         * @param lotatt2
         */
        public void setLotatt2(String lotatt2) {
            this.lotatt2 = lotatt2;
        }

        /**
         * ���ܣ������������3
         */
        public String getLotatt3() {
            return lotatt3;
        }

        /**
         * ���ܣ�������������3
         * @param lotatt3
         */
        public void setLotatt3(String lotatt3) {
            this.lotatt3 = lotatt3;
        }

        /**
         * ���ܣ������������4
         */
        public String getLotatt4() {
            return lotatt4;
        }

        /**
         * ���ܣ�������������4
         * @param lotatt4
         */
        public void setLotatt4(String lotatt4) {
            this.lotatt4 = lotatt4;
        }

        /**
         * ���ܣ������������5
         */
        public String getLotatt5() {
            return lotatt5;
        }

        /**
         * ���ܣ�������������5
         * @param lotatt5
         */
        public void setLotatt5(String lotatt5) {
            this.lotatt5 = lotatt5;
        }

        /**
         * ���ܣ������������6
         */
        public String getLotatt6() {
            return lotatt6;
        }

        /**
         * ���ܣ�������������6
         * @param lotatt6
         */
        public void setLotatt6(String lotatt6) {
            this.lotatt6 = lotatt6;
        }

        /**
         * ���ܣ������������7
         */
        public String getLotatt7() {
            return lotatt7;
        }

        /**
         * ���ܣ�������������7
         * @param lotatt7
         */
        public void setLotatt7(String lotatt7) {
            this.lotatt7 = lotatt7;
        }

        /**
         * ���ܣ������������8
         */
        public String getLotatt8() {
            return lotatt8;
        }

        /**
         * ���ܣ�������������8
         * @param lotatt8
         */
        public void setLotatt8(String lotatt8) {
            this.lotatt8 = lotatt8;
        }

        /**
         * ���ܣ������������9
         */
        public String getLotatt9() {
            return lotatt9;
        }

        /**
         * ���ܣ�������������9
         * @param lotatt9
         */
        public void setLotatt9(String lotatt9) {
            this.lotatt9 = lotatt9;
        }

        /**
         * ���ܣ������������ID
         */
        public String getLotid() {
            return lotid;
        }

        /**
         * ���ܣ�������������ID
         * @param lotid
         */
        public void setLotid(String lotid) {
            this.lotid = lotid;
        }

        /**
         * ���ܣ���õ�λ
         */
        public String getEunit() {
            return eunit;
        }

        /**
         * ���ܣ����õ�λ
         * @param eunit
         */
        public void setEunit(String eunit) {
            this.eunit = eunit;
        }
     
    }
    /**   *************************************** */
    
    /**
     * ����:��û���
     * @author hug 2012-8-31 
     * @return
     */
    public String getOwnerid() {
        return ownerid;
    }
    /**
     * ����:���û���
     * @author hug 2012-8-31 
     * @param ownerid
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }
    /**
     * ����: ��ÿͻ�ID(�ջ���)
     * @author hug 2012-9-19 
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }
    /**
     * ����: ���ÿͻ�ID(�ջ���)
     * @author hug 2012-9-19 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /**
     * ����: ��ó��ⵥ��ϸID
     * @author hug 2012-9-19 
     * @return
     */
    public String getOutstockdetailid() {
        return outstockdetailid;
    }
    /**
     * ����:  ���ó��ⵥ��ϸID
     * @author hug 2012-9-19 
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }
    /**
     * ����:  ��ó��ⵥ�ݺ�
     * @author hug 2012-9-19 
     * @return
     */
    public String getOutstockid() {
        return outstockid;
    }
    /**
     * ����:  ���ó��ⵥ�ݺ�
     * @author hug 2012-9-19 
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }
    /**
     * ����: ��òֿ�ID
     * @author hug 2012-9-19 
     * @return
     */
    public String getWarehouseid() {
        return warehouseid;
    }
    /**
     * ����: ���òֿ�ID
     * @author hug 2012-9-19 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * ����: ��ÿ���ID
     * @author hug 2012-9-19 
     * @return
     */
    public String getWhAreaId() {
        return whAreaId;
    }
    /**
     * ����: ���ÿ���ID
     * @author hug 2012-9-19 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * ����: ��ó��ⵥ��ϸ�Ĳ�Ʒ��Ϣ
     * @author hug 2012-9-20 
     * @return
     */
    public OutAllotRequestBean.ProductBean getProductBean() {
        return productBean;
    }
    /**
     * ����:���ó��ⵥ��ϸ�Ĳ�Ʒ��Ϣ
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
