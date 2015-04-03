package com.wms3.bms.standard.entity.inventory.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;

/**
 * 
 * ����: ����(һ����λ�ж����Ʒ�����Ρ�����)
 * @author hug 2012-3-6
 * @since WMS 3.0
 */
public class InventoryStorage  implements java.io.Serializable {

     private static final long serialVersionUID = -4103033685800670300L;
     private String inventoryid;    	//���ID
     private String cargoSpaceId;   	//��λID
    

	 private String cargoAlleyId;   	    //���ID
     private String cargoAreaId;    	//����ID
     private String whAreaId;       	//����ID
     private String warehouseid;    	//�ֿ�ID
     private String ownerId;            //����ID����ʱ���ã�
     private String productid;          //��ƷID
     private String productdate;		//��Ʒ��������
     private String indate;             //���ʱ��
     private String lotid;          	//����ID
     private String lotinfo;            //������Ϣ(�������)
     private String packid;             //��װID
     private String intype;             //��ⷽʽ 1.���� 2.�ѻ�
     private String punit;              //������λ
     private String isplit;             //1������ 2���ֲ������
     private String status;             //״̬  0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
     
     
     private double pnum;               //�������
     private double assignnum;          //������������ʱ���ã�
     private double holdnum;            //������������ʱ���ã�
     private double pvolume;            //����������ʱ���ã�
     private double pweight;            //�����������ʱ���ã�
     private double pnetweight;         //��澻�أ���ʱ���ã�
     private double assignvolume;       //�����������ʱ���ã�
     private double assignweight;       //������������ʱ���ã�
     private double assignnetweight;    //���侻�أ���ʱ���ã�
     private double holdvolume;         //�����������ʱ���ã�
     private double holdweight;         //������������ʱ���ã�
     private double holdnetweight;      //���Ά�أ���ʱ���ã�

	 private String injobid;            //��ҵID
     private String injobetailid;       //��ҵ��ϸID
     private String traycode;           //��������

     private String requestid;          //���뵥id
     private String instockid;          //��ⵥid
     private String reserve3;           //Ԥ��3
     private String reserve4;           //Ԥ��4
     
     public String lotatt1;  			//��������1
     public String lotatt2;  			//��������2
     public String lotatt3;  			//��������3------------------------------------
     public String lotatt4;  			//��������4
     public String lotatt5;  			//��������5
     public String lotatt6;  			//��������6
     public String lotatt7;  			//��������7
     public String lotatt8;  			//��������8
     public String lotatt9;  			//��������9
     public String lotatt10;  			//��������10
     public String lotatt11;  			//��������11
     public String lotatt12;  			//��������12
     
	 //��������
     private String warehouseName;		//�ֿ�����
	 private String whAreaName;			//��������
 	 private String cargoSpaceName;		//��λ����
 	 private String productName;		//��Ʒ����
 	 private String ownerName;			//��������
     private String punitname;          //��λ����
     private String packagename;        //��װ����
     private String productStatusName;	//��Ʒ״̬����
     private String statusName;         //״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
     private String Virtualwhname;    	//�߼���������
     private String supplierName;       //��Ӧ������(������⡢ԭ�������  ʹ��)

 	//�����ֶ�
     private String boxcode;            //������
     private String productcode;        //��Ʒ����
     private String productstatus;      //��Ʒ״̬ �ϸ�,���ϸ�,���죻������״̬�ǲ��ܳ���ģ�
     private String Virtualwhid;		//�߼�����id
     private String supplier;           //��Ӧ��id(������⡢ԭ�������  ʹ��)
     private String lotinfo2;		    //ԭ�����
     private String producttype;		    //��Ʒ���
     
     
     public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProductStatusName() {
		return productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}

	// Constructors
    /** default constructor */
    public InventoryStorage() {
    }
    
    // Property accessors
    /**
     * ���ܣ���ÿ��ID
     */
    public String getInventoryid() {
        return this.inventoryid;
    }
    /**
     * ���ܣ����ÿ��ID
     * @author hug 2012-3-6 
     * @param inventoryid
     */
    public void setInventoryid(String inventoryid) {
        this.inventoryid = inventoryid;
    }
    /**
     * ���ܣ���û�λID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    
    public void setCargoSpaceId(String cargoSpaceId) {
		this.cargoSpaceId = cargoSpaceId;
	}
    /**
     * ���ܣ�������ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    /**
     * ���ܣ��������ID
     * @author hug 2012-3-6 
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * ���ܣ���û���ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoAreaId() {
        return this.cargoAreaId;
    }
    /**
     * ���ܣ����û���ID
     * @author hug 2012-3-6 
     * @param cargoAreaId
     */
    public void setCargoAreaId(String cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }
    /**
     * ���ܣ���ÿ���ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    /**
     * ���ܣ����ÿ���ID
     * @author hug 2012-3-6 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * ���ܣ���òֿ�ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * ���ܣ����òֿ�ID
     * @author hug 2012-3-6 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * ���ܣ���û���ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getOwnerId() {
        return this.ownerId;
    }
    /**
     * ���ܣ����û���ID
     * @author hug 2012-3-6 
     * @param ownerId
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    /**
     * ���ܣ���û�ƷID
     * @author hug 2012-3-6 
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * ���ܣ����û�ƷID
     * @author hug 2012-3-6 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * ���ܣ�������ʱ��
     * @author hug 2012-3-6 
     * @return
     */
    public String getIndate() {
        return this.indate;
    }
    /**
     * ���ܣ��������ʱ��
     * @author hug 2012-3-6 
     * @param indate
     */
    public void setIndate(String indate) {
        this.indate = indate;
    }
    /**
     * ���ܣ������������ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getLotid() {
        return this.lotid;
    }
    /**
     * ���ܣ�������������ID
     * @author hug 2012-3-6 
     * @param lotnum
     */
    public void setLotid(String lotid) {
        this.lotid = lotid;
    }
    /**
     * ���ܣ���ð�װID
     * @author hug 2012-3-6 
     * @return
     */
    public String getPackid() {
        return this.packid;
    }
    /**
     * ���ܣ����ð�װID
     * @author hug 2012-3-6 
     * @param packid
     */
    public void setPackid(String packid) {
        this.packid = packid;
    }
    /**
     * ���ܣ������ⷽʽ 1.���� 2.�ѻ�
     * @author hug 2012-3-6 
     * @return
     */
    public String getIntype() {
        return this.intype;
    }
    /**
     * ���ܣ�������ⷽʽ 1.���� 2.�ѻ�
     * @author hug 2012-3-6 
     * @param intype
     */
    public void setIntype(String intype) {
        this.intype = intype;
    }
    /**
     * ���ܣ���ü�����λ
     * @author hug 2012-3-6 
     * @return
     */
    public String getPunit() {
        return this.punit;
    }
    /**
     * ���ܣ����ü�����λ
     * @author hug 2012-3-6 
     * @param punit
     */
    public void setPunit(String punit) {
        this.punit = punit;
    }
    /**
     * ���ܣ���ÿ������
     * @author hug 2012-3-6 
     * @return
     */
    public double getPnum() {
        return this.pnum;
    }
    /**
     * ���ܣ����ÿ������
     * @author hug 2012-3-6 
     * @param pnum
     */
    public void setPnum(double pnum) {
        this.pnum = pnum;
    }
    
    /**
     * ���ܣ���÷�������
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignnum() {
        return this.assignnum;
    }
    /**
     * ���ܣ����÷�������
     * @author hug 2012-3-6 
     * @param assignnum
     */
    public void setAssignnum(double assignnum) {
        this.assignnum = assignnum;
    }
    /**
     * ���ܣ���ö�������
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldnum() {
        return this.holdnum;
    }
    /**
     * ���ܣ����ö�������
     * @author hug 2012-3-6 
     * @param availablenum
     */
    public void setHoldnum(double holdnum) {
        this.holdnum = holdnum;
    }
    /**
     * ���ܣ���ÿ�����
     * @author hug 2012-3-6 
     * @return
     */
    public double getPvolume() {
        return this.pvolume;
    }
    /**
     * ���ܣ����ÿ�����
     * @author hug 2012-3-6 
     * @param pvolume
     */
    public void setPvolume(double pvolume) {
        this.pvolume = pvolume;
    }
    /**
     * ���ܣ���ÿ������
     * @author hug 2012-3-6 
     * @return
     */
    public double getPweight() {
        return this.pweight;
    }
    /**
     * ���ܣ����ÿ������
     * @author hug 2012-3-6 
     * @param pweight
     */
    public void setPweight(double pweight) {
        this.pweight = pweight;
    }
    /**
     * ���ܣ���ÿ�澻��
     * @author hug 2012-3-6 
     * @return
     */
    public double getPnetweight() {
        return this.pnetweight;
    }
    /**
     * ���ܣ����ÿ�澻��
     * @author hug 2012-3-6 
     * @param pnetweight
     */
    public void setPnetweight(double pnetweight) {
        this.pnetweight = pnetweight;
    }
    /**
     * ���ܣ���÷������
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignvolume() {
        return this.assignvolume;
    }
    /**
     * ���ܣ����÷������
     * @author hug 2012-3-6 
     * @param assignvolume
     */
    public void setAssignvolume(double assignvolume) {
        this.assignvolume = assignvolume;
    }
    /**
     * ���ܣ���÷�������
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignweight() {
        return this.assignweight;
    }
    /**
     * ���ܣ����÷�������
     * @author hug 2012-3-6 
     * @param assignweight
     */
    public void setAssignweight(double assignweight) {
        this.assignweight = assignweight;
    }
    /**
     * ���ܣ���÷��侻��
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignnetweight() {
        return this.assignnetweight;
    }
    /**
     * ���ܣ����÷��侻��
     * @author hug 2012-3-6 
     * @param assignnetweight
     */
    public void setAssignnetweight(double assignnetweight) {
        this.assignnetweight = assignnetweight;
    }
    /**
     * ���ܣ���ö������
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldvolume() {
        return this.holdvolume;
    }
    /**
     * ���ܣ����ö������
     * @author hug 2012-3-6 
     * @param availablevolume
     */
    public void setHoldvolume(double availablevolume) {
        this.holdvolume = availablevolume;
    }
    /**
     * ���ܣ���ö�������
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldweight() {
        return this.holdweight;
    }
    /**
     * ���ܣ����ö�������
     * @author hug 2012-3-6 
     * @param availableweight
     */
    public void setHoldweight(double holdweight) {
        this.holdweight = holdweight;
    }
    /**
     * ���ܣ���ö��Ά��
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldnetweight() {
        return this.holdnetweight;
    }
    /**
     * ���ܣ����ö��Ά��
     * @author hug 2012-3-6 
     * @param availablenetweight
     */
    public void setHoldnetweight(double holdnetweight) {
        this.holdnetweight = holdnetweight;
    }
    /**
     * ���ܣ������ҵID
     * @author hug 2012-3-6 
     * @return
     */
    public String getInjobid() {
        return this.injobid;
    }
    /**
     * ���ܣ�������ҵID
     * @author hug 2012-3-6 
     * @param injobid
     */
    public void setInjobid(String injobid) {
        this.injobid = injobid;
    }
    /**
     * ���ܣ������ҵ��ϸID
     * @author hug 2012-3-6 
     * @return
     */
    public String getInjobetailid() {
        return this.injobetailid;
    }
    /**
     * ���ܣ�������ҵ��ϸID
     * @author hug 2012-3-6 
     * @param injobetailid
     */
    public void setInjobetailid(String injobetailid) {
        this.injobetailid = injobetailid;
    }
    /**
     * ���ܣ������������
     * @author hug 2012-3-6 
     * @return
     */
    public String getTraycode() {
        return this.traycode;
    }
    /**
     * ���ܣ�������������
     * @author hug 2012-3-6 
     * @param traycode
     */
    public void setTraycode(String traycode) {
        this.traycode = traycode;
    }
  
    /**
     * ���ܣ����Ԥ��1
     * @author hug 2012-4-23 
     * @return
     */
    public String getRequestid() {
        return requestid;
    }
    /**
     * ���ܣ�����Ԥ��1
     * @author hug 2012-4-23 
     * @param reserve1
     */
    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }
    /**
     * ���ܣ����Ԥ��2
     * @author hug 2012-4-23 
     * @return
     */
    public String getInstockid() {
        return instockid;
    }
    /**
     * ���ܣ�����Ԥ��2
     * @author hug 2012-4-23 
     * @param reserve2
     */
    public void setInstockid(String instockid) {
        this.instockid = instockid;
    }
    /**
     * ���ܣ����Ԥ��3
     * @author hug 2012-4-23 
     * @return
     */
    public String getReserve3() {
        return reserve3;
    }
    /**
     * ���ܣ�����Ԥ��3
     * @author hug 2012-4-23 
     * @param reserve3
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    /**
     * ���ܣ����Ԥ��4
     * @author hug 2012-4-23 
     * @return
     */
    public String getReserve4() {
        return reserve4;
    }

    /**
     * ���ܣ�����Ԥ��4
     * @author hug 2012-4-23 
     * @param reserve4
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }
    /**
     * ���ܣ���û�λ����
     * @author hug 2012-4-23 
     * @return
     */
    public String getCargoSpaceName() {
        return cargoSpaceName;
    }
    /**
     * ���ܣ����û�λ����
     * @author hug 2012-4-23 
     * @param cargoSpaceName
     */
    public void setCargoSpaceName(String cargoSpaceName) {
        this.cargoSpaceName = cargoSpaceName;
    }
    /**
     * ���ܣ������Ʒ����
     * @author hug 2012-4-23 
     * @return
     */
    public String getProductName() {
        return productName;
    }
    /**
     * ���ܣ�������Ʒ����
     * @author hug 2012-4-23 
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * ���ܣ���û�������
     * @author hug 2012-4-23 
     * @return
     */
    public String getOwnerName() {
        return ownerName;
    }
    /**
     * ���ܣ����û�������
     * @author hug 2012-4-23 
     * @param ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
	 * ���ܣ���ÿ�������
	 */
	public String getWhAreaName() {
		return whAreaName;
	}

	/**
	 * ���ܣ����ÿ�������
	 * @param whAreaName
	 */
	public void setWhAreaName(String whAreaName) {
		this.whAreaName = whAreaName;
	}

    /**
     * ����:��õ�λ����
     * @author hug 2012-9-20 
     * @return
     */
    public String getPunitname() {
        return punitname;
    }

    /**
     * ����:���õ�λ����
     * @author hug 2012-9-20 
     * @param punitname
     */
    public void setPunitname(String punitname) {
        this.punitname = punitname;
    }

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
    /**
     * ���ܣ����������
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * ���ܣ�����������
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    /**
     * ���ܣ���ò�Ʒ����
     * @author hug 2012-10-18 
     * @return
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * ���ܣ����ò�Ʒ����
     * @author hug 2012-10-18 
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * ����:�����Ʒ״̬ �ϸ�,���ϸ�,���죻������״̬�ǲ��ܳ���ģ�
     * @author hug 2012-11-6 
     * @return
     */
    public String getProductstatus() {
        return productstatus;
    }

    /**
     * ����:������Ʒ״̬ �ϸ�,���ϸ�,���죻������״̬�ǲ��ܳ���ģ�
     * @author hug 2012-11-6 
     * @param productstatus
     */
    public void setProductstatus(String productstatus) {
        this.productstatus = productstatus;
    }
	 public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getLotinfo() {
		return lotinfo;
	}

	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}

	public String getProductdate() {
		return productdate;
	}

	public void setProductdate(String productdate) {
		this.productdate = productdate;
	}

	public String getIsplit() {
		return isplit;
	}

	public void setIsplit(String isplit) {
		this.isplit = isplit;
	}
	
	public String getVirtualwhname() {
		return Virtualwhname;
	}

	public void setVirtualwhname(String virtualwhname) {
		Virtualwhname = virtualwhname;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getVirtualwhid() {
		return Virtualwhid;
	}

	public void setVirtualwhid(String virtualwhid) {
		Virtualwhid = virtualwhid;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLotinfo2() {
		return lotinfo2;
	}

	public void setLotinfo2(String lotinfo2) {
		this.lotinfo2 = lotinfo2;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	/**
	 * ���ݻ�λ��ѯ���
	 * @param cargoSpaceId
	 * @return
	 */
	public String getQueryHQLByspaceid(String cargoSpaceId)
	{
		String hql = "from InventoryNeedcheck where 1=1 ";
		if(cargoSpaceId != null && cargoSpaceId.trim().length() > 0){
			hql += " and cargoSpaceId='"+cargoSpaceId+"'";
		}
		return hql;
	}
	/**
	 * �����������뼰����
	 * @param traycode
	 * @return
	 */
	public static InventoryStorage getQueryHQLBytraycode(String traycode,String whAreaid,EntityDAO dao)
	{
		String hql = "from InventoryStorage where 1=1 ";
		if(traycode != null && traycode.trim().length() > 0){
			hql += " and traycode='"+traycode+"'";
		}
		if(whAreaid != null && whAreaid.trim().length() > 0){
			hql += " and whAreaId='"+whAreaid+"'";
		}
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() > 0){
			return (InventoryStorage)ls.get(0);
		}
		return null;
	}

	/**
	 * ����:
	 * @param productid			
	 * @param lotinfo			
	 * @return 
	 * @throws Exception
	 */
	public static int getJobDLotsum(String productid,String lotinfo,EntityDAO dao) throws Exception {
		
		if(productid != null && lotinfo != null){
		
			String strSql = " select COUNT(*)  from InventoryStorage as t where t.productid='" + productid + "' and t.lotinfo='" + lotinfo + "'";
			return dao.searchEntitieCount(strSql);

		}
		return 0;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}