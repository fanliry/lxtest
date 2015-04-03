package com.wms3.bms.standard.entity.inventory;

/**
 * ���������̵�
 * @author hug
 *
 */
public class InventoryNeedcheck  implements java.io.Serializable {
	 
	private static final long serialVersionUID = -7264087671584351975L;
	 private String needcheckid;	//ID
	 private String taskid;			//����������
	 private String jobid;			//��ҵ��
     private String inouttype;		//�������1,������� 2����������
     private String status;			//״̬ 0--�½�  1--���ڵ��� 2--�������

	 private String cargoSpaceId;	//��ҵ��λ
     private String createtime;		//����ʱ��
     private String handleflag;		//�����ʶ Y,N
     private String handlecontent;	//��������
     private String traycode;		//��������
     private String taskno;			//�����
     private String handletime;			//����ʱ��
     private String handleman;			//������
     private String warehouseid;    //�ֿ�ID
     
     
     /*��������*/
 	private String m_strCargoSpaceName;			/*��λ����*/
 	private String handlemanname;		//��������
    private String warehousename;    	    //�ջ��ֿ���
    private String statusname;	//״̬��
 	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getM_strCargoSpaceName() {
 		return m_strCargoSpaceName;
 	}

 	public void setM_strCargoSpaceName(String cargoSpaceName) {
 		m_strCargoSpaceName = cargoSpaceName;
 	}

	/** default constructor */
    public InventoryNeedcheck() {
    }
    
	/** minimal constructor */
    public InventoryNeedcheck(String inouttype, String createtime, String handleflag) {
        this.inouttype = inouttype;
        this.createtime = createtime;
        this.handleflag = handleflag;
    }



	// Property accessors
    /**
	 * ���ܣ���������
	 */
	public String getTaskno() {
		return taskno;
	}

	/**
	 * ���ܣ����������
	 * @param taskno
	 */
	public void setTaskno(String taskno) {
		this.taskno = taskno;
	}

	/**
	 * ���ܣ������������
	 */
	public String getTraycode() {
		return traycode;
	}

	/**
	 * ���ܣ�������������
	 * @param traycode
	 */
	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}
	
    /**
     * ���ܣ����ID
     */
    public String getNeedcheckid() {
        return this.needcheckid;
    }
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    /**
     * ���ܣ�����ID
     * @author hug 2012-3-6 
     * @param needcheckid
     */
    public void setNeedcheckid(String needcheckid) {
        this.needcheckid = needcheckid;
    }
    /**
     * ���ܣ������ҵ��
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * ���ܣ�������ҵ��
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * ���ܣ�����������
     * @author hug 2012-3-6 
     * @return
     */
    public String getInouttype() {
        return this.inouttype;
    }
    /**
     * ���ܣ������������
     * @author hug 2012-3-6 
     * @param inouttype
     */
    public void setInouttype(String inouttype) {
        this.inouttype = inouttype;
    }
    /**
     * ���ܣ������ҵ��λ
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    /**
     * ���ܣ�������ҵ��λ
     * @author hug 2012-3-6 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    /**
     * ���ܣ��������ʱ��
     * @author hug 2012-3-6 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * ���ܣ���������ʱ��
     * @author hug 2012-3-6 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ���ô����ʶ
     * @author hug 2012-3-6 
     * @return
     */
    public String getHandleflag() {
        return this.handleflag;
    }
    /**
     * ���ܣ����ô����ʶ
     * @author hug 2012-3-6 
     * @param handleflag
     */
    public void setHandleflag(String handleflag) {
        this.handleflag = handleflag;
    }
    /**
     * ���ܣ���ô�������
     * @author hug 2012-3-6 
     * @return
     */
    public String getHandlecontent() {
        return this.handlecontent;
    }
    /**
     * ���ܣ����ô�������
     * @author hug 2012-3-6 
     * @param handlecontent
     */
    public void setHandlecontent(String handlecontent) {
        this.handlecontent = handlecontent;
    }

	public String getHandletime() {
		return handletime;
	}

	public void setHandletime(String handletime) {
		this.handletime = handletime;
	}

	public String getHandleman() {
		return handleman;
	}

	public void setHandleman(String handleman) {
		this.handleman = handleman;
	}

	public String getHandlemanname() {
		return handlemanname;
	}

	public void setHandlemanname(String handlemanname) {
		this.handlemanname = handlemanname;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	/**
	 * ������쳣��ѯ
	 * @param warehouseid
	 * @param start_time
	 * @param end_time
	 * @param is_do
	 * @param inouttype
	 * @return
	 */
		public String getQueryHQL(String warehouseid,String start_time,String end_time,String is_do,String inouttype)
		{
			String hql = "from InventoryNeedcheck where 1=1 ";
			if(warehouseid != null && warehouseid.trim().length() > 0){
				hql += " and warehouseid='"+warehouseid+"'";
			}
			if(start_time != null && start_time.trim().length() > 0){
				hql += " and createtime>='"+start_time+"'";
			}
			if(end_time != null && end_time.trim().length() > 0){
				hql += " and createtime<='"+end_time+" 24:60:60'";
			}
			if(is_do != null && is_do.trim().length() > 0){
				hql += " and handleflag='"+is_do+"'";
			}
			if(inouttype != null && inouttype.trim().length() > 0){
				hql += " and inouttype='"+inouttype+"'";
			}
			hql += " order by createtime";
			return hql;
		}
		/**
		 * ������쳣��ѯ
		 * @param warehouseid
		 * @param start_time
		 * @param end_time
		 * @param is_do
		 * @param inouttype
		 * @return
		 */
			public String getQueryHQLCount(String warehouseid,String start_time,String end_time,String is_do,String inouttype)
			{
				String hql = "select count(*) from InventoryNeedcheck where 1=1 ";
				if(warehouseid != null && warehouseid.trim().length() > 0){
					hql += " and warehouseid='"+warehouseid+"'";
				}
				if(start_time != null && start_time.trim().length() > 0){
					hql += " and createtime>='"+start_time+"'";
				}
				if(end_time != null && end_time.trim().length() > 0){
					hql += " and createtime<='"+end_time+" 24:60:60'";
				}
				if(is_do != null && is_do.trim().length() > 0){
					hql += " and handleflag='"+is_do+"'";
				}
				if(inouttype != null && inouttype.trim().length() > 0){
					hql += " and inouttype='"+inouttype+"'";
				}
				return hql;
			}
   
			/**
			 * ��ѯ�̵���Ϣ
			 * @param warehouseid
			 * @return
			 */
			public String getQueryHQLByid(String needcheckid)
			{
				String hql = "from InventoryNeedcheck where 1=1 ";
				if(needcheckid != null && needcheckid.trim().length() > 0){
					hql += " and needcheckid='"+needcheckid+"'";
				}
				return hql;
			}







}