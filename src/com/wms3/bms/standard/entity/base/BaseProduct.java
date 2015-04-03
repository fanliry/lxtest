package com.wms3.bms.standard.entity.base;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * ��������Ʒ��
 * @author hug
 */
public class BaseProduct  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -54472344660924613L;
	
    // Fields    
	private String productid;			// ��ƷID
	private String productCode;			//��Ʒ����
   

	private String productname;			// ��Ʒ��׼��
    private String spec;				// ��Ʒ���
    private String barcode;				// ��Ʒ����
    private String pkspec;				// ��Ʒ������
    private String recunit;				// ������λ
    private String sendunit;			// ȱʡ������λ
    private String useflag;				// ���ñ�־
    private double length;				// ��
    private double width;				// ��
    private double height;				// ��
    private double upperlimit;			// �������
    private double lowerlimit;			// �������
    private double weight;				// ����
    private double netweight;			// ����
    private double tareweight;			// Ƥ��
    private double volume;				// ���
    private String pttypeid;			// ��Ʒ���ID
    private String storageareaid;		// �洢����ID
    private String storagespaceid;		// �洢��λID
    private String putawayid;			// �ϼܹ���ID
    private String allocationid;		// �������ID
    private String replenishid;			// ��������ID
    private String pakageid;		    // ��װ����id
    private String lotid;               // ����ID
    private String customerid;          // �ͻ�ID
    private String producedate;			// ��������
    private double validityterm;		// ��Ч��
    private String validitytype;		// ��Ч������
    private String remark1;				// ��ע
    private String remark2;				// ��ע
    private String remark3;				// ��ע
    private String remark4;				// ��ע
    private String remark5;				// ��ע
    private String remark6;				// ��ע
    private String remark7;				// ��ע
    
    
    private String isexcess;            // �Ƿ����ջ�
    private String isproductmixed;      // �����Ʒ���
    private String isbatchmixed;        // �������λ��
    private String producttype;
    
    
    
    //  ��������
    private String storageareaname;		// �洢��������
    private String storagespacename;	// �洢��λ����
    private String lotdesc;				// ������������
    private String pkgdesc;				// ��װ����
    private String customername;		// �ͻ�����
    private String validitytypename;	// ��Ч��������
    private String pttypename;			// ��Ʒ�����
    private String putawayname;			// �ϼܹ�������
    private String allocationname;		// �����������
    private String replenishname;		// ������������
    private String producttypename;     // ��Ʒ�������
    

    // Constructors

    /** default constructor */
    public BaseProduct() {
    }
    
    public BaseProduct(String productid, String productname) {
        this.productid = productid;
        this.productname = productname;
    }

   
    // Property accessors
    /**
     * ���ܣ������ƷID
     */
    public String getProductid() {
        return this.productid;
    }
    
    /**
     * ���ܣ�������ƷID
     * @param productid
     */ 
    public void setProductid(String productid) {
        this.productid = productid;
    }
    public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
    /**
     * ���ܣ���ò�Ʒ��׼��
     */
    public String getProductname() {
        return this.productname;
    }
    
    /**
     * ���ܣ����ò�Ʒ��׼��
     * @param productname
     */ 
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * ���ܣ���ò�Ʒ���
     */
    public String getSpec() {
        return this.spec;
    }
    
    /**
     * ���ܣ����ò�Ʒ���
     * @param spec
     */ 
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * ���ܣ���ò�Ʒ����
     */
    public String getBarcode() {
        return this.barcode;
    }
    
    /**
     * ���ܣ����ò�Ʒ����
     * @param barcode
     */ 
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ���ò�Ʒ������
     */
    public String getPkspec() {
        return this.pkspec;
    }
    
    /**
     * ���ܣ����ò�Ʒ������
     * @param pkspec
     */ 
    public void setPkspec(String pkspec) {
        this.pkspec = pkspec;
    }



    /**
     * ���ܣ���ü�����λ
     */
    public String getRecunit() {
        return this.recunit;
    }
    
    /**
     * ���ܣ����ü�����λ
     * @param recunit
     */ 
    public void setRecunit(String recunit) {
        this.recunit = recunit;
    }

    /**
     * ���ܣ���÷�����λ
     */
    public String getSendunit() {
        return this.sendunit;
    }
    
    /**
     * ���ܣ����÷�����λ
     * @param sendunit
     */ 
    public void setSendunit(String sendunit) {
        this.sendunit = sendunit;
    }

    /**
     * ���ܣ���ÿ��ñ�־
     */
    public String getUseflag() {
        return this.useflag;
    }
    
    /**
     * ���ܣ����ÿ��ñ�־
     * @param useflag
     */ 
    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }

    /**
     * ���ܣ���ó�
     */
    public double getLength() {
        return this.length;
    }
    
    /**
     * ���ܣ����ó�
     * @param length
     */ 
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * ���ܣ���ÿ�
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * ���ܣ����ÿ�
     * @param width
     */ 
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * ���ܣ���ø�
     */
    public double getHeight() {
        return this.height;
    }
    
    /**
     * ���ܣ����ø�
     * @param height
     */ 
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * ���ܣ���ÿ������
     */
    public double getUpperlimit() {
        return this.upperlimit;
    }
    
    /**
     * ���ܣ����ÿ������
     * @param upperlimit
     */ 
    public void setUpperlimit(double upperlimit) {
        this.upperlimit = upperlimit;
    }

    /**
     * ���ܣ���ÿ������
     */
    public double getLowerlimit() {
        return this.lowerlimit;
    }
    
    /**
     * ���ܣ����ÿ������
     * @param lowerlimit
     */ 
    public void setLowerlimit(double lowerlimit) {
        this.lowerlimit = lowerlimit;
    }

    /**
     * ���ܣ��������
     */
    public double getWeight() {
        return this.weight;
    }
    
    /**
     * ���ܣ���������
     * @param weight
     */ 
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * ���ܣ���þ���
     */
    public double getNetweight() {
        return this.netweight;
    }
    
    /**
     * ���ܣ����þ���
     * @param netweight
     */ 
    public void setNetweight(double netweight) {
        this.netweight = netweight;
    }

    /**
     * ���ܣ����Ƥ��
     */
    public double getTareweight() {
        return this.tareweight;
    }
    
    /**
     * ���ܣ�����Ƥ��
     * @param tareweight
     */ 
    public void setTareweight(double tareweight) {
        this.tareweight = tareweight;
    }

    /**
     * ���ܣ�������
     */
    public double getVolume() {
        return this.volume;
    }
    
    /**
     * ���ܣ��������
     * @param volume
     */ 
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * ���ܣ������Ʒ���ID
     */
    public String getPttypeid() {
        return this.pttypeid;
    }
    
    /**
     * ���ܣ�������Ʒ���ID
     * @param pttypeid
     */ 
    public void setPttypeid(String pttypeid) {
        this.pttypeid = pttypeid;
    }

    /**
     * ���ܣ���ô洢����ID
     */
    public String getStorageareaid() {
        return this.storageareaid;
    }
    
    /**
     * ���ܣ����ô洢����ID
     * @param storageareaid
     */ 
    public void setStorageareaid(String storageareaid) {
        this.storageareaid = storageareaid;
    }
    
    /**
     * ���ܣ��������ID
     */
    public String getLotid() {
        return lotid;
    }

    /**
     * ���ܣ���������ID
     * @param lotid
     */ 
    public void setLotid(String lotid) {
        this.lotid = lotid;
    }

    /**
     * ���ܣ���ð�װID
     */
    public String getPakageid() {
        return this.pakageid;
    }
    
    /**
     * ���ܣ����ð�װID
     * @param pakageid
     */ 
    public void setPakageid(String pakageid) {
        this.pakageid = pakageid;
    }

    /**
     * ���ܣ������������
     */
    public String getProducedate() {
        return this.producedate;
    }
    
    /**
     * ���ܣ�������������
     * @param producedate
     */ 
    public void setProducedate(String producedate) {
        this.producedate = producedate;
    }

    /**
     * ���ܣ������Ч��
     */
    public double getValidityterm() {
        return this.validityterm;
    }
    
    /**
     * ���ܣ�������Ч��
     * @param validityterm
     */ 
    public void setValidityterm(double validityterm) {
        this.validityterm = validityterm;
    }

    /**
     * ���ܣ������Ч������
     */
    public String getValiditytype() {
        return this.validitytype;
    }
    
    /**
     * ���ܣ�������Ч������
     * @param validitytype
     */ 
    public void setValiditytype(String validitytype) {
        this.validitytype = validitytype;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark1() {
        return this.remark1;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark1
     */ 
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark2() {
        return this.remark2;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark2
     */ 
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark3() {
        return this.remark3;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark3
     */ 
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark5() {
        return this.remark5;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark5
     */ 
    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark4() {
        return this.remark4;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark4
     */ 
    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark6() {
        return this.remark6;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark6
     */ 
    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark7() {
        return this.remark7;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark7
     */ 
    public void setRemark7(String remark7) {
        this.remark7 = remark7;
    }
    
    /**
     * ���ܣ���ô洢��������
     */
    public String getStorageareaname() {
        return this.storageareaname;
    }
    
    /**
     * ���ܣ����ô洢��������
     * @param storageareaname
     */ 
    public void setStorageareaname(String storageareaname) {
        this.storageareaname = storageareaname;
    }
    
    /**
     * ���ܣ����������������
     */
    public String getLotdesc() {
        return this.lotdesc;
    }
    
    /**
     * ���ܣ�����������������
     * @param lotdesc
     */ 
    public void setLotdesc(String lotdesc) {
        this.lotdesc = lotdesc;
    }
    
    /**
     * ���ܣ���ð�װ����
     */
    public String getPkgdesc() {
        return this.pkgdesc;
    }
    
    /**
     * ���ܣ����ð�װ����
     * @param pkgdesc
     */ 
    public void setPkgdesc(String pkgdesc) {
        this.pkgdesc = pkgdesc;
    }
    
    /**
     * ���ܣ������Ч��������
     */
    public String getValiditytypename() {
        return this.validitytypename;
    }
    
    /**
     * ���ܣ�������Ч��������
     * @param validitytypename
     */ 
    public void setValiditytypename(String validitytypename) {
        this.validitytypename = validitytypename;
    }

    /**
     * ���ܣ������Ʒ�����
     */
    public String getPttypename() {
        return this.pttypename;
    }
    
    /**
     * ���ܣ�������Ʒ�����
     * @param pttypename
     */ 
    public void setPttypename(String pttypename) {
        this.pttypename = pttypename;
    }

    /**
     * ���ܣ�����������λ��
     */
    public String getIsbatchmixed() {
        return isbatchmixed;
    }

    /**
     * ���ܣ������������λ��
     * @param isbatchmixed
     */ 
    public void setIsbatchmixed(String isbatchmixed) {
        this.isbatchmixed = isbatchmixed;
    }

    /**
     * ���ܣ�����Ƿ����ջ�
     */
    public String getIsexcess() {
        return isexcess;
    }

    /**
     * ���ܣ������Ƿ����ջ�
     * @param isexcess
     */ 
    public void setIsexcess(String isexcess) {
        this.isexcess = isexcess;
    }

    /**
     * ���ܣ���������Ʒ���
     */
    public String getIsproductmixed() {
        return isproductmixed;
    }

    /**
     * ���ܣ����������Ʒ���
     * @param isproductmixed
     */ 
    public void setIsproductmixed(String isproductmixed) {
        this.isproductmixed = isproductmixed;
    }

	/**
	 * ���ܣ���÷������ID
	 */
	public String getAllocationid() {
		return allocationid;
	}

	/**
	 * ���ܣ����÷������ID
	 * @param allocationid
	 */
	public void setAllocationid(String allocationid) {
		this.allocationid = allocationid;
	}

	/**
	 * ���ܣ�����ϼܹ���ID
	 */
	public String getPutawayid() {
		return putawayid;
	}

	/**
	 * ���ܣ������ϼܹ���ID
	 * @param putawayid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
	}

	/**
	 * ���ܣ���ò�������ID
	 */
	public String getReplenishid() {
		return replenishid;
	}

	/**
	 * ���ܣ����ò�������ID
	 * @param replenishid
	 */
	public void setReplenishid(String replenishid) {
		this.replenishid = replenishid;
	}

	/**
	 * ���ܣ���÷����������
	 */
	public String getAllocationname() {
		return allocationname;
	}

	/**
	 * ���ܣ����÷����������
	 * @param allocationname
	 */
	public void setAllocationname(String allocationname) {
		this.allocationname = allocationname;
	}

	/**
	 * ���ܣ�����ϼܹ�������
	 */
	public String getPutawayname() {
		return putawayname;
	}

	/**
	 * ���ܣ������ϼܹ�������
	 * @param putawayname
	 */
	public void setPutawayname(String putawayname) {
		this.putawayname = putawayname;
	}

	/**
	 * ���ܣ���ò�����������
	 */
	public String getReplenishname() {
		return replenishname;
	}

	/**
	 * ���ܣ����ò�����������
	 * @param replenishname
	 */
	public void setReplenishname(String replenishname) {
		this.replenishname = replenishname;
	}

	/**
	 * ���ܣ���ô洢��λID
	 */
	public String getStoragespaceid() {
		return storagespaceid;
	}

	/**
	 * ���ܣ����ô洢��λID
	 * @param storagespaceid
	 */
	public void setStoragespaceid(String storagespaceid) {
		this.storagespaceid = storagespaceid;
	}

	/**
	 * ���ܣ���ô洢��λ����
	 */
	public String getStoragespacename() {
		return storagespacename;
	}

	/**
	 * ���ܣ����ô洢��λ����
	 * @param storagespacename
	 */
	public void setStoragespacename(String storagespacename) {
		this.storagespacename = storagespacename;
	}

	/**
	 * ���ܣ���ÿͻ�ID
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * ���ܣ����ÿͻ�ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * ���ܣ���ÿͻ�����
	 */
	public String getCustomername() {
		return customername;
	}

	/**
	 * ���ܣ����ÿͻ�����
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	
	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

	/**
	 * ���ܣ������Ʒ�Ƿ����
	 * @param printdate
	 * @param losdate
	 * @return 
	 */
	public static long isWarningDate(String printdate , double losdate) {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			Date date = df.parse(printdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, (int)losdate);
			long time= System.currentTimeMillis();
			Date d1=new Date(time);
		    Date d3 = df.parse(df.format(cal.getTime()));
		    long diff = d3.getTime() - d1.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
		    return days;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return 0;
	}
}