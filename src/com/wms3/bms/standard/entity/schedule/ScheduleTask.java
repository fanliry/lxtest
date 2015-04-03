package com.wms3.bms.standard.entity.schedule;
/**
 * 
 * ����: ��������ӿڱ�
 * @author YAO
 * @since WMS 3.0
 */
public class ScheduleTask  implements java.io.Serializable {

	/**
	 * tasktype(��������)�� route(ִ��·��) 
	 * ��� tasktype in('1') and route in('1')
	 * ���� tasktype in('3') and route in('2') �ӳ������ͻ�����
	 * ���� tasktype in('3') and route in('1') ��������ͻ�����
	 * ֱ�ӳ��� tasktype in('2') and route in('1')
	 * ���ֳ��� tasktype in('2') and route in('2')
	 */
	public String taskid;			//����������
	public String jobid;			//��ҵid
	public String tasktype;			//�������� 1�����  2������   3������
	public String floor;			//¥��           1��һ¥  2����¥
	public Integer splatoon;		//Դ��λ��
	public Integer scolumn;			//Դ��λ��
	public Integer sfloor;			//Դ��λ��
	public Integer tplatoon;		//Ŀ���λ��
	public Integer tcolumn;		    //Ŀ���λ��
	public Integer tfloor;			//Ŀ���λ��
    
	public String cargoSpaceId;        // ��λID
	public String cargoAlleyId;        // ���ID
	public String warehouseid;         // �ֿ�ID
	public String whAreaId;            // ����ID
	
	public String TSJH;                  // �������ͻ���
    
	



	public String status;			//����״̬  2.��ִ��   3.��ִ�� 4.��� 5.����  6.ͬ�����  8.ͬ������
	public Integer priority;		//���ȼ�
	public String taskpos;			//������ 1.���� 2.�����
	public String createtime;		//ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	public String starttime;		//��ʼʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	public String asexectime;		//�Ѷ�� ִ��ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	public String finishtime;		//���ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	public String snumber;			//���ͺ�
	public String traycode;			//��������
	public String route;			//ִ��·�� 1-ֱ��/ֱ��  2-����
	public String taskno;			//�����
	public String commkind;			//ָ���ʽ
	public String cargoAreaId;      // ����ID
	public String jobtype;         	//��ҵ���� 
    public int phase ;         		//����ִ�н׶Σ�������1.�·��׶�  2.�Ѷ��ִ�н׶�  3.С��ִ�н׶� 4.���ͻ�ִ�н׶�  7.���
    
    public int jym ;         		//���ͻ��ϵ�У����
    
    //����
  	private String whareaName;
	private String cargoAlleyName;
  	private String cargoSpaceName;
  	private String statusName;
  	private String tasktypeName;

	// Constructors
	/** default constructor */
    public ScheduleTask() {
    }
    public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
  	public String getWhareaName() {
		return whareaName;
	}

	public void setWhareaName(String whareaName) {
		this.whareaName = whareaName;
	}

	public String getCargoAlleyName() {
		return cargoAlleyName;
	}

	public void setCargoAlleyName(String cargoAlleyName) {
		this.cargoAlleyName = cargoAlleyName;
	}

	public String getCargoSpaceName() {
		return cargoSpaceName;
	}

	public void setCargoSpaceName(String cargoSpaceName) {
		this.cargoSpaceName = cargoSpaceName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTasktypeName() {
		return tasktypeName;
	}

	public void setTasktypeName(String tasktypeName) {
		this.tasktypeName = tasktypeName;
	}
    // Property accessors
	/**
	 * ���ܣ���öѶ�� ִ��ʱ��
	 */
	public String getAsexectime() {
		return asexectime;
	}

	/**
	 * ���ܣ����öѶ�� ִ��ʱ��
	 * @param asexectime
	 */
	public void setAsexectime(String asexectime) {
		this.asexectime = asexectime;
	}

	/**
	 * ���ܣ����ָ���ʽ
	 */
	public String getCommkind() {
		return commkind;
	}

	/**
	 * ���ܣ�����ָ���ʽ
	 * @param commkind
	 */
	public void setCommkind(String commkind) {
		this.commkind = commkind;
	}
	public String getTSJH() {
		return TSJH;
	}

	public void setTSJH(String tSJH) {
		TSJH = tSJH;
	}
	/**
	 * ���ܣ����ʱ��
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * ���ܣ�����ʱ��
	 * @param createtime
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * ���ܣ�������ʱ��
	 */
	public String getFinishtime() {
		return finishtime;
	}

	/**
	 * ���ܣ��������ʱ��
	 * @param finishtime
	 */
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	/**
	 * ���ܣ�������ȼ�
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * ���ܣ��������ȼ�
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * ���ܣ����ִ��·�� 1-ֱ��/ֱ�� 2-����
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * ���ܣ�����ִ��·�� 1-ֱ��/ֱ�� 2-����
	 * @param route
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * ���ܣ����Դ��λ��
	 */
	public Integer getScolumn() {
		return scolumn;
	}

	/**
	 * ���ܣ�����Դ��λ��
	 * @param scolumn
	 */
	public void setScolumn(Integer scolumn) {
		this.scolumn = scolumn;
	}

	/**
	 * ���ܣ����Դ��λ��
	 */
	public Integer getSfloor() {
		return sfloor;
	}

	/**
	 * ���ܣ�����Դ��λ��
	 * @param sfloor
	 */
	public void setSfloor(Integer sfloor) {
		this.sfloor = sfloor;
	}

	/**
	 * ���ܣ�������ͺ�
	 */
	public String getSnumber() {
		return snumber;
	}

	/**
	 * ���ܣ��������ͺ�
	 * @param snumber
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	/**
	 * ���ܣ����Դ��λ��
	 */
	public Integer getSplatoon() {
		return splatoon;
	}

	/**
	 * ���ܣ�����Դ��λ��
	 * @param splatoon
	 */
	public void setSplatoon(Integer splatoon) {
		this.splatoon = splatoon;
	}

	/**
	 * ���ܣ���ÿ�ʼʱ��
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * ���ܣ����ÿ�ʼʱ��
	 * @param starttime
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * ���ܣ��������״̬
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * ���ܣ���������״̬
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ���ܣ���õ���������
	 */
	public String getTaskid() {
		return taskid;
	}

	/**
	 * ���ܣ����õ���������
	 * @param taskid
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

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
	 * ���ܣ���������� 1.���� 2.�����
	 */
	public String getTaskpos() {
		return taskpos;
	}

	/**
	 * ���ܣ����������� 1.���� 2.�����
	 * @param taskpos
	 */
	public void setTaskpos(String taskpos) {
		this.taskpos = taskpos;
	}
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * ���ܣ���õ������� 1-��� 2-���� 3-����
	 */
	public String getTasktype() {
		return tasktype;
	}

	/**
	 * ���ܣ����õ������� 1-��� 2-���� 3-����
	 * @param tasktype
	 */
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	/**
	 * ���ܣ����Ŀ���λ��
	 */
	public Integer getTcolumn() {
		return tcolumn;
	}

	/**
	 * ���ܣ�����Ŀ���λ��
	 * @param tcolumn
	 */
	public void setTcolumn(Integer tcolumn) {
		this.tcolumn = tcolumn;
	}

	/**
	 * ���ܣ����Ŀ���λ��
	 */
	public Integer getTfloor() {
		return tfloor;
	}

	/**
	 * ���ܣ�����Ŀ���λ��
	 * @param tfloor
	 */
	public void setTfloor(Integer tfloor) {
		this.tfloor = tfloor;
	}

	/**
	 * ���ܣ����Ŀ���λ��
	 */
	public Integer getTplatoon() {
		return tplatoon;
	}

	/**
	 * ���ܣ�����Ŀ���λ��
	 * @param tplatoon
	 */
	public void setTplatoon(Integer tplatoon) {
		this.tplatoon = tplatoon;
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
    public String getCargoAreaId() {
		return cargoAreaId;
	}

	public void setCargoAreaId(String cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getJym() {
		return jym;
	}

	public void setJym(int jym) {
		this.jym = jym;
	}
	
	
}