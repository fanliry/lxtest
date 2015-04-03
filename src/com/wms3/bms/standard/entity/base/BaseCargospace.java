package com.wms3.bms.standard.entity.base;



/**
 * ��������λ��
 * @author hug
 */
public class BaseCargospace implements java.io.Serializable { 

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3967806561646973624L;
	
	private String cargoSpaceId;		// ��λID
	private String cargoSpaceName;		// ��λ����
	private String csstatus;			// ��λ״̬ 1���ջ�λ��2������λ��3�������䣻4��������䣻5��������̵㣻6���������̵㣻7������ȡ����8���ѳ���
	private Integer csplatoon;			// ��λ��
	private Integer cscolumn;			// ��λ��
	private Integer csfloor;			// ��λ��
	private String cstype;				// ��λ����
	private String inlock;				// ����� Y.�� N.��
	private String outlock;				// ������ Y.�� N.��
	private String cargoAlleyId;		// ���ID
	private String cargoAreaId;			// ����ID
	private String warehouseid;			// �ֿ�ID
	private String whAreaId;			// ����ID
	private String storagetype;			// �洢����(�洢��λ����,��,��,��/��,����)
	private String puttype;				// �ϼ�����(������ͣ�1��ֻ�ܷ���һ��2���ɶ�η���)
	private String picktype;			// ��ѡ����(�������ͣ�1��ֻ�ܷ���һ�Σ�2���ɶ�η���)
	private double length;				// ��
	private double width;				// ��
	private double height;				// ��
	private double volume;				// ���
	private double xcoordinate;			// x����
	private double ycoordinate;			// y����
	private double zcoordinate;			// z����
	private String environment;			// ����
	private Integer layernum;			// ����
	private double loadweight;			// ����
	private Integer palletnum;			// �ɷ�������	-1-û������ 0-���ܷ� n-�������ֱ�ʾ�ɷ�����
    private Integer haspalletnum;       // �ѷ�������
	private String istwin;				// �Ƿ�˫����λ Y.�� N.��
	private String location;			// λ�ã�1�����棻2������
	private String twincsid;			// ����˫����λID
	private String usagetype;   		// ��λʹ��
	private String attributetype;   	// ��λ����
	private String demandtype;   		// ��ת����
	private String createtime;			// ����ʱ��
	private String createmanid;			// ������
	private String updatetime;			// ����޸�ʱ��
	private String updatemanid;			// ����޸���
	
	//��������
	private String cargoAlleyName;		// �������
	private String cargoAreaName;		// ��������
	private String warehousename;		// �ֿ�����
	private String whAreaName;			// ��������
	private String csstatusname;		// ��λ״̬����
	private String cstypename;			// ��λ��������
	private String storagetypename;		// �洢��������
	private String puttypename;			// �ϼ���������
	private String picktypename;		// ��ѡ��������
	private String twincsname;			// ����˫����λ����
	private String usagetypename;   	// ��λʹ������
	private String attributetypename;   // ��λ��������
	private String demandtypename;   	// ��ת��������
	
    // Constructors

    /** default constructor */
    public BaseCargospace() {
    }

   
    // Property accessors
    /**
     * ���ܣ���û�λID
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    
    /**
     * ���ܣ����û�λID
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    
    /**
     * ���ܣ���û�λ����
     */
    public String getCargoSpaceName() {
        return this.cargoSpaceName;
    }
    
    /**
     * ���ܣ����û�λ����
     * @param cargoSpaceName
     */
    public void setCargoSpaceName(String cargoSpaceName) {
        this.cargoSpaceName = cargoSpaceName;
    }

    /**
     * ���ܣ���û�λ״̬
     */
    public String getCsstatus() {
        return this.csstatus;
    }
    
    /**
     * ���ܣ����û�λ״̬
     * @param csstatus
     */
    public void setCsstatus(String csstatus) {
        this.csstatus = csstatus;
    }

    /**
     * ���ܣ���û�λ��
     */
    public Integer getCsplatoon() {
        return this.csplatoon;
    }
    
    /**
     * ���ܣ����û�λ��
     * @param csplatoon
     */
    public void setCsplatoon(Integer csplatoon) {
        this.csplatoon = csplatoon;
    }
    
    /**
     * ���ܣ���û�λ��
     */
    public Integer getCscolumn() {
        return this.cscolumn;
    }
    
    /**
     * ���ܣ����û�λ��
     * @param cscolumn
     */
    public void setCscolumn(Integer cscolumn) {
        this.cscolumn = cscolumn;
    }

    /**
     * ���ܣ���û�λ��
     */
    public Integer getCsfloor() {
        return this.csfloor;
    }
    
    /**
     * ���ܣ����û�λ��
     * @param csfloor
     */
    public void setCsfloor(Integer csfloor) {
        this.csfloor = csfloor;
    }
    
    /**
     * ���ܣ���û�λ����
     */
    public String getCstype() {
        return this.cstype;
    }
    
    /**
     * ���ܣ����û�λ����
     * @param cstype
     */
    public void setCstype(String cstype) {
        this.cstype = cstype;
    }

    /**
     * ���ܣ���������
     */
    public String getInlock() {
        return this.inlock;
    }
    
    /**
     * ���ܣ����������
     * @param inlock
     */
    public void setInlock(String inlock) {
        this.inlock = inlock;
    }

    /**
     * ���ܣ���ó�����
     */
    public String getOutlock() {
        return this.outlock;
    }
    
    /**
     * ���ܣ����ó�����
     * @param outlock
     */    
    public void setOutlock(String outlock) {
        this.outlock = outlock;
    }

    /**
     * ���ܣ�������ID
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    
    /**
     * ���ܣ��������ID
     * @param cargoAlleyId
     */     
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }

    /**
     * ���ܣ���û���ID
     */
    public String getCargoAreaId() {
        return this.cargoAreaId;
    }
    
    /**
     * ���ܣ����û���ID
     * @param cargoAreaId
     */     
    public void setCargoAreaId(String cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }

    /**
     * ���ܣ���òֿ�ID
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    
    /**
     * ���ܣ����òֿ�ID
     * @param warehouseid
     */     
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    
    /**
     * ���ܣ���ÿ���ID
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    
    /**
     * ���ܣ����ÿ���ID
     * @param whAreaId
     */     
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }


    /**
     * ���ܣ���ô洢����
     */
    public String getStoragetype() {
        return this.storagetype;
    }
    
    /**
     * ���ܣ����ô洢����
     * @param storagetype
     */     
    public void setStoragetype(String storagetype) {
        this.storagetype = storagetype;
    }

    /**
     * ���ܣ�����ϼ�����
     */
    public String getPuttype() {
        return this.puttype;
    }
    
    /**
     * ���ܣ������ϼ�����
     * @param puttype
     */     
    public void setPuttype(String puttype) {
        this.puttype = puttype;
    }

    /**
     * ���ܣ���ü�ѡ����
     */
    public String getPicktype() {
        return this.picktype;
    }
    
    /**
     * ���ܣ����ü�ѡ����
     * @param picktype
     */     
    public void setPicktype(String picktype) {
        this.picktype = picktype;
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
     * ���ܣ�������
     */
    public double getVolume() {
        return this.volume;
    }
    
    /**
     * ���ܣ��������
     * @param ���
     */     
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * ���ܣ����x����
     */
    public double getXcoordinate() {
        return this.xcoordinate;
    }
    
    /**
     * ���ܣ�����x����
     * @param x����
     */     
    public void setXcoordinate(double xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    /**
     * ���ܣ����y����
     */
    public double getYcoordinate() {
        return this.ycoordinate;
    }
    
    /**
     * ���ܣ�����y����
     * @param ycoordinate
     */     
    public void setYcoordinate(double ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    /**
     * ���ܣ����z����
     */
    public double getZcoordinate() {
        return this.zcoordinate;
    }
    
    /**
     * ���ܣ�����z����
     * @param zcoordinate
     */     
    public void setZcoordinate(double zcoordinate) {
        this.zcoordinate = zcoordinate;
    }

    /**
     * ���ܣ���û���
     */
    public String getEnvironment() {
        return this.environment;
    }
    
    /**
     * ���ܣ����û���
     * @param environment
     */     
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * ���ܣ���ò���
     */
    public Integer getLayernum() {
        return this.layernum;
    }
    
    /**
     * ���ܣ����ò���
     * @param layernum
     */     
    public void setLayernum(Integer layernum) {
        this.layernum = layernum;
    }

    /**
     * ���ܣ���ó���
     */
    public double getLoadweight() {
        return this.loadweight;
    }
    
    /**
     * ���ܣ����ó���
     * @param loadweight
     */     
    public void setLoadweight(double loadweight) {
        this.loadweight = loadweight;
    }

    /**
     * ���ܣ���ÿɷ�������
     */
    public Integer getPalletnum() {
        return this.palletnum;
    }
    
    /**
     * ���ܣ����ÿɷ�������
     * @param palletnum
     */     
    public void setPalletnum(Integer palletnum) {
        this.palletnum = palletnum;
    }

    /**
     * ���ܣ���ô���ʱ��
     */
    public String getCreatetime() {
        return this.createtime;
    }
    
    /**
     * ���ܣ����ô���ʱ��
     * @param createtime
     */     
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * ���ܣ���ô�����
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    
    /**
     * ���ܣ����ô�����
     * @param createmanid
     */     
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }

    /**
     * ���ܣ��������޸�ʱ��
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
    
    /**
     * ���ܣ���������޸�ʱ��
     * @param updatetime
     */     
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * ���ܣ��������޸���
     */
    public String getUpdatemanid() {
        return this.updatemanid;
    }
    
    /**
     * ���ܣ���������޸���
     * @param updatemanid
     */     
    public void setUpdatemanid(String updatemanid) {
        this.updatemanid = updatemanid;
    }

    /**
     * ���ܣ�����������
     */
    public String getCargoAlleyName() {
        return this.cargoAlleyName;
    }
    
    /**
     * ���ܣ������������
     * @param cargoAlleyName
     */     
    public void setCargoAlleyName(String cargoAlleyName) {
        this.cargoAlleyName = cargoAlleyName;
    }
    
    /**
     * ���ܣ���û�������
     */
    public String getCargoAreaName() {
        return this.cargoAreaName;
    }
    
    /**
     * ���ܣ����û�������
     * @param cargoAlleyName
     */     
    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }
    
    /**
     * ���ܣ���òֿ�����
     */
    public String getWarehousename() {
        return this.warehousename;
    }
    
    /**
     * ���ܣ����òֿ�����
     * @param warehousename
     */     
    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    /**
     * ���ܣ���ÿ�������
     */
    public String getWhAreaName() {
        return this.whAreaName;
    }

    /**
     * ���ܣ����ÿ�������
     * @param warehousename
     */     
    public void setWhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
    }
    
    /**
     * ���ܣ���û�λ״̬����
     */
    public String getCsstatusname() {
        return this.csstatusname;
    }
    
    /**
     * ���ܣ����û�λ״̬����
     * @param csstatusname
     */     
    public void setCsstatusname(String csstatusname) {
        this.csstatusname = csstatusname;
    }
    
    /**
     * ���ܣ���û�λ��������
     */
    public String getCstypename() {
        return this.cstypename;
    }
    
    /**
     * ���ܣ����û�λ��������
     * @param cstypename
     */     
    public void setCstypename(String cstypename) {
        this.cstypename = cstypename;
    }
    
    /**
     * ���ܣ���ô洢��������
     */
    public String getStoragetypename() {
        return this.storagetypename;
    }
    
    /**
     * ���ܣ����ô洢��������
     * @param storagetypename
     */     
    public void setStoragetypename(String storagetypename) {
        this.storagetypename = storagetypename;
    }
    
    /**
     * ���ܣ�����ϼ���������
     */
    public String getPuttypename() {
        return this.puttypename;
    }
    
    /**
     * ���ܣ������ϼ���������
     * @param puttypename
     */     
    public void setPuttypename(String puttypename) {
        this.puttypename = puttypename;
    }
    
    /**
     * ���ܣ���ü�ѡ��������
     */
    public String getPicktypename() {
        return this.picktypename;
    }
    
    /**
     * ���ܣ����ü�ѡ��������
     * @param picktypename
     */     
    public void setPicktypename(String picktypename) {
        this.picktypename = picktypename;
    }



	/**
	 * ���ܣ�����Ƿ�˫����λ Y.�� N.��
	 */
	public String getIstwin() {
		return istwin;
	}



	/**
	 * ���ܣ������Ƿ�˫����λ Y.�� N.��
	 * @param istwin
	 */
	public void setIstwin(String istwin) {
		this.istwin = istwin;
	}



	/**
	 * ���ܣ����λ�ã�1�����棻2������
	 */
	public String getLocation() {
		return location;
	}



	/**
	 * ���ܣ�����λ�ã�1�����棻2������
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}



	/**
	 * ���ܣ��������˫����λID
	 */
	public String getTwincsid() {
		return twincsid;
	}



	/**
	 * ���ܣ���������˫����λID
	 * @param twincsid
	 */
	public void setTwincsid(String twincsid) {
		this.twincsid = twincsid;
	}


	/**
	 * ���ܣ��������˫����λ����
	 */
	public String getTwincsname() {
		return twincsname;
	}


	/**
	 * ���ܣ���������˫����λ����
	 * @param twincsname
	 */
	public void setTwincsname(String twincsname) {
		this.twincsname = twincsname;
	}


	/**
	 * ���ܣ���ÿ�λ����
	 */
	public String getAttributetype() {
		return attributetype;
	}


	/**
	 * ���ܣ����ÿ�λ����
	 * @param attributetype
	 */
	public void setAttributetype(String attributetype) {
		this.attributetype = attributetype;
	}


	/**
	 * ���ܣ������ת����
	 */
	public String getDemandtype() {
		return demandtype;
	}


	/**
	 * ���ܣ�������ת����
	 * @param demandtype
	 */
	public void setDemandtype(String demandtype) {
		this.demandtype = demandtype;
	}


	/**
	 * ���ܣ���ÿ�λʹ��
	 */
	public String getUsagetype() {
		return usagetype;
	}


	/**
	 * ���ܣ����ÿ�λʹ��
	 * @param usagetype
	 */
	public void setUsagetype(String usagetype) {
		this.usagetype = usagetype;
	}


	/**
	 * ���ܣ���ÿ�λ��������
	 */
	public String getAttributetypename() {
		return attributetypename;
	}


	/**
	 * ���ܣ����ÿ�λ��������
	 * @param attributetypename
	 */
	public void setAttributetypename(String attributetypename) {
		this.attributetypename = attributetypename;
	}


	/**
	 * ���ܣ������ת��������
	 */
	public String getDemandtypename() {
		return demandtypename;
	}


	/**
	 * ���ܣ�������ת��������
	 * @param demandtypename
	 */
	public void setDemandtypename(String demandtypename) {
		this.demandtypename = demandtypename;
	}


	/**
	 * ���ܣ���ÿ�λʹ������
	 */
	public String getUsagetypename() {
		return usagetypename;
	}


	/**
	 * ���ܣ����ÿ�λʹ������
	 * @param usagetypename
	 */
	public void setUsagetypename(String usagetypename) {
		this.usagetypename = usagetypename;
	}


    /**
     * ����:����ѷ�������
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getHaspalletnum() {
        return haspalletnum;
    }


    /**
     * ����:�����ѷ�������
     * @author hug 2012-9-6 
     * @param haspalletnum
     */
    public void setHaspalletnum(Integer haspalletnum) {
        this.haspalletnum = haspalletnum;
    }
	/**
	 * ���ݻ�λ��ѯ��λ
	 * @param cargoSpaceId
	 * @return
	 */
	public String getQueryHQLByspaceid(String cargoSpaceId)
	{
		String hql = "from BaseCargospace where 1=1 ";
		if(cargoSpaceId != null && cargoSpaceId.trim().length() > 0){
			hql += " and cargoSpaceId='"+cargoSpaceId+"'";
		}
		return hql;
	}
    
}