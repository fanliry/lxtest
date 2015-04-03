package com.wms3.bms.standard.business.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * ��������Ӧ����
 * @author yangwm
 *
 */
public class InAllotResponseBean implements Serializable{

    /**  */
    private static final long serialVersionUID = -1479180717586022341L;
    
    //���ص���һ�����̵���Ϣ������ж�����̷���һ����λ�����Ƿ��ظö����б�
    
    public String cargoSpaceId;     // ��λID
    public Integer csplatoon;       // ��λ��
    public Integer cscolumn;        // ��λ��
    public Integer csfloor;         // ��λ��
    public String warehouseid;      // �ֿ�ID
    public String whAreaId;         // ����ID
    public String cargoAlleyId;     // ���ID
    public String oldstatus;        // �����λǰ��״̬
    public String trayCode;         // ��������
    
    public List<ProductBean> lsProductBean = new ArrayList<ProductBean>();   //ProductBeanʵ���б�
    
    /** һ�������ϵĲ�Ʒ��Ϣ   */
    public class ProductBean
    {
        public String strTransId;   //�ջ���¼ID�����ٺţ�
        public String transStatus;  //Ҫ�޸ĵ�״̬
        public String productid;    //��Ʒ
        public String packid;       //��װ
        public String eunit;        //��λ
        public double putnum;       //�ϼ�����
        public double putweight;    //�ϼ�����
        public double putnetweight; //�ϼܾ���
        public double putvolume;    //�ϼ����  
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
        
        public String ownerid;      //����
        public String reinvoiceid;         //�ջ����ݱ��
        public String reinvoicedetailid;   //�ջ�����ϸID
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
         * ����:����ϼܾ���
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutnetweight() {
            return putnetweight;
        }

        /**
         * ����:�����ϼܾ���
         * @author hug 2012-8-31 
         * @param putnetweight
         */
        public void setPutnetweight(double putnetweight) {
            this.putnetweight = putnetweight;
        }

        /**
         * ����:����ϼ�����
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutnum() {
            return putnum;
        }

        /**
         * ����:�����ϼ�����
         * @author hug 2012-8-31 
         * @param putnum
         */
        public void setPutnum(double putnum) {
            this.putnum = putnum;
        }

        /**
         * ����:����ϼ����
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutvolume() {
            return putvolume;
        }

        /**
         * ����:�����ϼ����
         * @author hug 2012-8-31 
         * @param putvolume
         */
        public void setPutvolume(double putvolume) {
            this.putvolume = putvolume;
        }

        /**
         * ����:����ϼ�����
         * @author hug 2012-8-31 
         * @return
         */
        public double getPutweight() {
            return putweight;
        }

        /**
         * ����:�����ϼ�����
         * @author hug 2012-8-31 
         * @param putweight
         */
        public void setPutweight(double putweight) {
            this.putweight = putweight;
        }

        /**
         * ����:����ջ���¼ID�����ٺţ�
         * @author hug 2012-8-31 
         * @return
         */
        public String getStrTransId() {
            return strTransId;
        }

        /**
         * ����:�����ջ���¼ID�����ٺţ�
         * @author hug 2012-8-31 
         * @param strTransId
         */
        public void setStrTransId(String strTransId) {
            this.strTransId = strTransId;
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
         * ���ܣ�����ջ�����ϸID
         */
        public String getReinvoicedetailid() {
            return reinvoicedetailid;
        }

        /**
         * ���ܣ������ջ�����ϸID
         * @param reincoicedetailid
         */
        public void setReinvoicedetailid(String reinvoicedetailid) {
            this.reinvoicedetailid = reinvoicedetailid;
        }

        /**
         * ���ܣ�����ջ����ݱ��
         */
        public String getReinvoiceid() {
            return reinvoiceid;
        }

        /**
         * ���ܣ������ջ����ݱ��
         * @param reinvoiceid
         */
        public void setReinvoiceid(String reinvoiceid) {
            this.reinvoiceid = reinvoiceid;
        }
        /**
         * ����:���Ҫ�޸ĵ�״̬
         * @author hug 2012-9-6 
         * @return
         */
        public String getTransStatus() {
            return transStatus;
        }

        /**
         * ����:����Ҫ�޸ĵ�״̬
         * @author hug 2012-9-6 
         * @param transStatus
         */
        public void setTransStatus(String transStatus) {
            this.transStatus = transStatus;
        }
    }
    
    
    /**
     * ����:����ProductBeanʵ��
     * @author hug 2012-8-31 
     * @return
     */
    public InAllotResponseBean.ProductBean getNewProductBean(){  
        return new ProductBean();
    } 
    /**
     * 
     * ����:����ProductBean����
     * @author hug 2012-8-31 
     * @param product
     */
    public void addProductBean(ProductBean product)
    {
        lsProductBean.add(product);
    }
    /** 
     * ���ܣ�����ProductBean�����С
     * @author hug 2012-8-31 
     * @return
     */
    public int getProductBeanSize(){
        return lsProductBean.size();
    }
    /**
     * ����:����ProductBean�����б�
     * @author hug 2012-8-31 
     * @return
     */
    public List<ProductBean> getListProductBean()
    {
        return lsProductBean;
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
