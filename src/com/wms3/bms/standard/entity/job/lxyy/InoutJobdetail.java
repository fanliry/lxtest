package com.wms3.bms.standard.entity.job.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;


/**
 * 
 * ����: ��ҵ��ϸ
 * @author hug 2012-3-6
 * @since WMS 3.0
 */
public class InoutJobdetail  implements java.io.Serializable {

    private static final long serialVersionUID = -1749256160040811905L;
    private String jobdetailid;		//��ҵ��ϸID����ҵ��ϸ����ҵһһ��Ӧ��
    private String jobid;          	//��ҵ���
    private String linestatus;     	//״̬0:�½� 4.��� 5.���ϣ���ʱ���ã�
    private String inventoryid;    	//���ID
 	private String productid;      	//��ƷID
 	private String printdate;      	//��������
 	private String packid;      	//��װID ����ʱ���ã�
 	private String lotid;        	//��������ID
 	private String lotinfo;			//�������� (�������)
 	private String punit;          	//��λ �������͹���� ��Ʒ��λһһ��Ӧ��
 	private double jobnum;        	//��ҵ����
 	private double assignnum;     	//���������������ҵ ������������ҵ����һ�£�
 	private String isplit;     	    //�Ƿ��֣�1������ 2���ֲ�������� ������� Ϊ��������assignnum
 	private double volume;         	//�������ʱ���ã�
 	private double weight;         	//��������ʱ���ã�
 	private double netweight;      	//���أ���ʱ���ã�
 	private double assignvolume;   	//�����������ʱ���ã�
 	private double assignweight;   	//������������ʱ���ã�
 	private double assignnetweight;	//���侻�أ���ʱ���ã�
 	private String customerid;     	//�ͻ�ID����ʱ���ã�
 	private String ownerId;        	//����ID����ʱ���ã�
 	private String isreview;        //�Ƿ񸴺� ���˳ɹ� дY 
 	private String reviewid;        //������ID
 	private String boxcode;        	//�����루��ʱ���ã�
 	private String isinvoice;      	//�Ƿ������ɵ��� Y��  N��
 	private String injobdetailid;  	//Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
 	private String reserve1;       	//Ԥ��1   -����¼����ʱ������ԭ��---2014-8-28 09:11:29��
 	private String reserve2;       	//Ԥ��2
 	private String reserve3;       	//Ԥ��3
 	private String reserve4;       	//Ԥ��4

 	
 	private String transreceiptid;	//�ջ���¼ID��
 	private String productStatus;	//��Ʒ״̬
 	
     
     


	//��������
    protected String m_strCustomerName;		// �ͻ�����ʱ���ã�
    protected String m_strOwnerName; 		// ��������ʱ���ã�
    protected String m_strProductName;   	// ��Ʒ
    private String productStatusName;		//��Ʒ״̬����

	private String m_strPackName;       	//��װ���ƣ���ʱ���ã�
    private String m_strUnitName;      		//��λ����
    private String reviewMan;         		//������
    
    private String supplierName;           //��Ӧ������(������⡢ԭ�������  ʹ��)
    private String producttype;		       //��Ʒ���
    
    //�����ֶ�
    private String productcode;        //��Ʒ����
    private double reviewnum;          //��������
    private String reviewTime;         //����ʱ��
    private String supplier;           //��Ӧ��id(������⡢ԭ�������  ʹ��)
    private String lotinfo2;		   //ԭ����� (����֮ǰ�ı�ţ�����Ϊ��)

    // Constructors
    /** default constructor */
    public InoutJobdetail() {
    }

    
    // Property accessors
    /**
     * ���ܣ������ҵ��ϸID
     */
    public String getJobdetailid() {
        return this.jobdetailid;
    }
    /**
     * ���ܣ�������ҵ��ϸID
     * @author hug 2012-3-6 
     * @param jobdetailid
     */
    public void setJobdetailid(String jobdetailid) {
        this.jobdetailid = jobdetailid;
    }
    /**
     * ���ܣ������ҵ���
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * ���ܣ�������ҵ���
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    public String getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
    /**
     * ���ܣ������Ʒ���
     * @author hug 2012-3-6 
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * ���ܣ�������Ʒ���
     * @author hug 2012-3-6 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * ���ܣ���õ�λ
     * @author hug 2012-3-6 
     * @return
     */
    public String getPunit() {
        return this.punit;
    }
    /**
     * ���ܣ����õ�λ
     * @author hug 2012-3-6 
     * @param punit
     */
    public void setPunit(String punit) {
        this.punit = punit;
    }
    /**
     * ���ܣ������ҵ����
     * @author hug 2012-3-6 
     * @return
     */
    public double getJobnum() {
        return this.jobnum;
    }
    /**
     * ���ܣ�������ҵ����
     * @author hug 2012-3-6 
     * @param jobnum
     */
    public void setJobnum(double jobnum) {
        this.jobnum = jobnum;
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
     * ���ܣ�������
     * @author hug 2012-3-6 
     * @return
     */
    public double getVolume() {
        return this.volume;
    }
    /**
     * ���ܣ��������
     * @author hug 2012-3-6 
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    /**
     * ���ܣ��������
     * @author hug 2012-3-6 
     * @return
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * ���ܣ���������
     * @author hug 2012-3-6 
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * ���ܣ���þ���
     * @author hug 2012-3-6 
     * @return
     */
    public double getNetweight() {
        return this.netweight;
    }
    /**
     * ���ܣ����þ���
     * @author hug 2012-3-6 
     * @param netweight
     */
    public void setNetweight(double netweight) {
        this.netweight = netweight;
    }
    /**
     * ���ܣ���ÿͻ�ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCustomerid() {
        return this.customerid;
    }
    /**
     * ���ܣ����ÿͻ�ID
     * @author hug 2012-3-6 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /**
     * ���ܣ���÷��侻��
     * @author hug 2012-4-16 
     * @return
     */
    public double getAssignnetweight() {
        return assignnetweight;
    }
    /**
     * ���ܣ����÷��侻��
     * @author hug 2012-4-16 
     * @param assignnetweight
     */
    public void setAssignnetweight(double assignnetweight) {
        this.assignnetweight = assignnetweight;
    }
    /**
     * ���ܣ���÷������
     * @author hug 2012-4-16 
     * @return
     */
    public double getAssignvolume() {
        return assignvolume;
    }
    /**
     * ���ܣ����÷������
     * @author hug 2012-4-16 
     * @param assignvolume
     */
    public void setAssignvolume(double assignvolume) {
        this.assignvolume = assignvolume;
    }
    /**
     * ���ܣ���÷�������
     * @author hug 2012-4-16 
     * @return
     */
    public double getAssignweight() {
        return assignweight;
    }
    /**
     * ���ܣ����÷�������
     * @author hug 2012-4-16 
     * @param assignweight
     */
    public void setAssignweight(double assignweight) {
        this.assignweight = assignweight;
    }
    /**
     * ���ܣ��������
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * ���ܣ���������
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }
    /**
     * ���ܣ����Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
     * @author hug 2012-4-16 
     * @return
     */
    public String getInjobdetailid() {
        return injobdetailid;
    }
    /**
     * ���ܣ�����Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
     * @author hug 2012-4-16 
     * @param injobdetailid
     */
    public void setInjobdetailid(String injobdetailid) {
        this.injobdetailid = injobdetailid;
    }
    /**
     * ���ܣ���ÿ��ID
     * @author hug 2012-4-16 
     * @return
     */
    public String getInventoryid() {
        return inventoryid;
    }
    /**
     * ���ܣ����ÿ��ID
     * @author hug 2012-4-16 
     * @param inventoryid
     */
    public void setInventoryid(String inventoryid) {
        this.inventoryid = inventoryid;
    }
    /**
     * ���ܣ�����Ƿ������ɵ��� Y��  N��
     * @author hug 2012-4-16 
     * @return
     */
    public String getIsinvoice() {
        return isinvoice;
    }
    /**
     * ���ܣ������Ƿ������ɵ��� Y��  N��
     * @author hug 2012-4-16 
     * @param isinvoice
     */
    public void setIsinvoice(String isinvoice) {
        this.isinvoice = isinvoice;
    }
    /**
     * ���ܣ����Ԥ��1 //Ԥ��1   -����¼����ʱ������ԭ��---2014-8-28 09:11:29��
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve1() {
        return reserve1;
    }
    /**
     * ���ܣ�����Ԥ��1 //Ԥ��1   -����¼����ʱ������ԭ��---2014-8-28 09:11:29��
     * @author hug 2012-4-16 
     * @param reserve1
     */
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }
    /**
     * ���ܣ����Ԥ��2
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve2() {
        return reserve2;
    }
    /**
     * ���ܣ�����Ԥ��2
     * @author hug 2012-4-16 
     * @param reserve2
     */
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }
    /**
     * ���ܣ����Ԥ��3
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve3() {
        return reserve3;
    }
    /**
     * ���ܣ�����Ԥ��3
     * @author hug 2012-4-16 
     * @param reserve3
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
    /**
     * ���ܣ����Ԥ��4
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve4() {
        return reserve4;
    }
    /**
     * ���ܣ�����Ԥ��4
     * @author hug 2012-4-16 
     * @param reserve4
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }
    /**
     * ���ܣ���û���ID
     * @author hug 2012-5-21 
     * @return
     */
    public String getOwnerId() {
        return ownerId;
    }
    /**
     * ���ܣ����û���ID
     * @author hug 2012-5-21 
     * @param ownerId
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    /**
     * ���ܣ����״̬0:�½� 4.��� 5.����
     * @author hug 2012-6-5 
     * @return
     */
    public String getLinestatus() {
        return linestatus;
    }
    /**
     * ���ܣ�����״̬0:�½� 4.��� 5.����
     * @author hug 2012-6-5 
     * @param linestatus
     */
    public void setLinestatus(String linestatus) {
        this.linestatus = linestatus;
    }
    /**
     * ���ܣ���ÿͻ�
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strCustomerName() {
        return m_strCustomerName;
    }
    /**
     * ���ܣ����ÿͻ�
     * @author hug 2012-6-20 
     * @param customerName
     */
    public void setM_strCustomerName(String customerName) {
        m_strCustomerName = customerName;
    }
    /**
     * ���ܣ���û���
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strOwnerName() {
        return m_strOwnerName;
    }
    /**
     * ���ܣ����û���
     * @author hug 2012-6-20 
     * @param ownerName
     */
    public void setM_strOwnerName(String ownerName) {
        m_strOwnerName = ownerName;
    }
    /**
     * ���ܣ���ò�Ʒ
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strProductName() {
        return m_strProductName;
    }
    /**
     * ���ܣ����ò�Ʒ
     * @author hug 2012-6-20 
     * @param productName
     */
    public void setM_strProductName(String productName) {
        m_strProductName = productName;
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
	 * ���ܣ���ð�װID
	 */
	public String getPackid() {
		return packid;
	}


	/**
	 * ���ܣ����ð�װID
	 * @param packid
	 */
	public void setPackid(String packid) {
		this.packid = packid;
	}


	/**
	 * ���ܣ�����ջ���¼ID
	 */
	public String getTransreceiptid() {
		return transreceiptid;
	}


	/**
	 * ���ܣ������ջ���¼ID
	 * @param transreceiptid
	 */
	public void setTransreceiptid(String transreceiptid) {
		this.transreceiptid = transreceiptid;
	}
    
     /**
     * 
     * ����:��ð�װ����
     * @author hug 2012-8-23 
     * @return
     */
    public String getM_strPackName() {
        return m_strPackName;
    }

    /**
     * 
     * ����:���ð�װ����
     * @author hug 2012-8-23 
     * @param packName
     */
    public void setM_strPackName(String packName) {
        m_strPackName = packName;
    }

    /**
     * 
     * ����:��õ�λ����
     * @author hug 2012-8-23 
     * @return
     */
    public String getM_strUnitName() {
        return m_strUnitName;
    }

    /**
     * 
     * ����:���õ�λ����
     * @author hug 2012-8-23 
     * @param unitName
     */
    public void setM_strUnitName(String unitName) {
        m_strUnitName = unitName;
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
     * ���ܣ���ø�������
     * @author hug 2012-9-19 
     * @return
     */
    public double getReviewnum() {
        return reviewnum;
    }

    /**
     * ���ܣ����ø�������
     * @author hug 2012-9-19 
     * @param reviewnum
     */
    public void setReviewnum(double reviewnum) {
        this.reviewnum = reviewnum;
    }


	public String getLotinfo() {
		return lotinfo;
	}


	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}


	public String getReviewid() {
		return reviewid;
	}


	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}


	public String getReviewMan() {
		return reviewMan;
	}


	public void setReviewMan(String reviewMan) {
		this.reviewMan = reviewMan;
	}


	public String getIsplit() {
		return isplit;
	}


	public void setIsplit(String isplit) {
		this.isplit = isplit;
	}


	public String getPrintdate() {
		return printdate;
	}


	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}
	
		
	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * ��ã�ԭ����� (����֮ǰ�ı�ţ�����Ϊ��)
	 * @return
	 */
	public String getLotinfo2() {
		return lotinfo2;
	}

	/**
	 * ���ã�ԭ����� (����֮ǰ�ı�ţ�����Ϊ��)
	 * @param lotinfo2
	 */
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
	 * ����:������ҵid��ѯ��ҵ��ϸ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailByJobid(String jobid,EntityDAO dao) throws Exception {
		
		if(jobid != null){
		
			String strSql = " from InoutJobdetail as t where t.jobid='" + jobid + "'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InoutJobdetail)ls.get(0);
			}
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
		
			String strSql = " select COUNT(*)  from InoutJobdetail as t where t.productid='" + productid + "' and t.lotinfo='" + lotinfo + "'";
			return dao.searchEntitieCount(strSql);

		}
		return 0;
	}

	public String getReviewTime() {
		return reviewTime;
	}


	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}


	public String getIsreview() {
		return isreview;
	}


	public void setIsreview(String isreview) {
		this.isreview = isreview;
	}
	
    public String getProductStatusName() {
		return productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
    
}