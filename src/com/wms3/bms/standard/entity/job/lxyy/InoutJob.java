package com.wms3.bms.standard.entity.job.lxyy;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.constant.StandardConstant;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.job.ITaskDao;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;



/**
 * 
 * ����: ��ҵ
 * @author yao 
 * @since WMS 3.0
 */
public class InoutJob  implements java.io.Serializable {

    private static final long serialVersionUID = 4020743324182458002L;
    
     private String jobid;      //��ҵ���
     private String jobtype;    //��ҵ���� ����������ҵ����Ϊ ������͵�hlֵ��
     private String onLineType; //����ģʽ 1.���� 2.�ѻ�
     private String isaccount;  //�Ƿ���� Y.�� N.�񣨻����Ĳ����ˣ�
     private String status;     //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
     private Integer priority;  //���ȼ������û�����ȼ���
     private String createtime; //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     private String readtime;   //����ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     private String finishtime; //���ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     private String jobpos;     //��ҵ���� 1.���� 2.����ˣ���ʱû���ã�
     private String inOutType;  //������� 1�ϼ���� 2.����
     private String shiftid;    //��ҵ��Σ���ʱû���ã�
     private String snumber;    //���ͺţ�������ͺ� Ϊ���ͻ��ı���� һ�����������ͬ��
     private String traycode;   //��������
     private String route;      //ִ��·��1-ֱ��/ֱ��2-�����������ջ������Ϊ ֱ�룬�����Ϊֱ�� ���ݴ浽ת���������ǻ�����
     private String opManId;    //������
     private String taskid;     //��������ID
     private String invoicetype;//1.������ҵ���е� 2.���е��ݺ�����ҵ

     private String warehouseid;    //�ֿ�ID
     private String scargoWhareaId; //Դ����ID
     private String scargoSpaceId;  //Դ��λID
     private String scargoAlleyId;  //Դ���ID
     private String tcargoWhareaId; //Ŀ�����ID
     private String tcargoSpaceId;  //Ŀ���λID
     private String tcargoAlleyId;  //Ŀ�����ID
     
     private String oldspacestatus; // ����ǰ��λ״̬
     private String isinvoice;      	//�Ƿ������ɵ��� Y��  N��
     
     

	private String boundstockid; // ����id
     private String boundstockdetailid; // ������ϸid
     private String boundrequeststockid; // ������뵥id
     
     
     //��������
     protected String scargoWhareaName;     // Դ����
     protected String scargoAlleyName;      // Դ���
     protected String scargoSpaceName;      // Դ��λ
     protected String tcargoWhareaName;     // Ŀ�����
     protected String tcargoAlleyName;      // Ŀ�����
     protected String tcargoSpaceName;      // Ŀ���λ
     protected String statusName;           // ��ҵ״̬����
     protected String jobtypeName;          // ��ҵ��������
     protected String opMan;                // ������
     private String warehousename;    	    //�ջ��ֿ���
     private String invoicetypename;    	//1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
     private String jobcategoryname;      //��ҵ��� 1:�Զ����������ҵ 2:�ݴ�����ҵ
     //private double invoicenumz;          //��������
     protected String Virtualwhname;      //�߼���������
     
     //�����ֶ�
     protected String jobcategory;      //��ҵ��� 1:�Զ����������ҵ 2:�ݴ�����ҵ

     protected String Virtualwhid;      //�߼�����id
     
     

    // Constructors

    /** default constructor */
    public InoutJob() {
    }

	/** minimal constructor */
    public InoutJob(String jobid, String onLineType, String status, String jobpos, String taskid) {
        this.jobid = jobid;
        this.onLineType = onLineType;
        this.status = status;
        this.jobpos = jobpos;
        this.taskid = taskid;
    }
    
    /** full constructor */
    public InoutJob(String jobid, String jobtype, String onLineType, String isaccount, String status, Integer priority, String createtime, String readtime, String finishtime, String jobpos, String shiftid, String warehouseid, String snumber, String traycode, String route, String opManId, String taskid) {
        this.jobid = jobid;
        this.jobtype = jobtype;
        this.onLineType = onLineType;
        this.isaccount = isaccount;
        this.status = status;
        this.priority = priority;
        this.createtime = createtime;
        this.readtime = readtime;
        this.finishtime = finishtime;
        this.jobpos = jobpos;
        this.shiftid = shiftid;
        this.warehouseid = warehouseid;
        this.snumber = snumber;
        this.traycode = traycode;
        this.route = route;
        this.opManId = opManId;
        this.taskid = taskid;
    }
    public String getIsinvoice() {
		return isinvoice;
	}
	public void setIsinvoice(String isinvoice) {
		this.isinvoice = isinvoice;
	}
    // Property accessors
    /**
     * ���ܣ������ҵ���
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
    /**
     * ���ܣ������ҵ����1-���2-����3-�ƿ�
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobtype() {
        return this.jobtype;
    }
    /**
     * ���ܣ�������ҵ����1-���2-����3-�ƿ�
     * @author hug 2012-3-6 
     * @param jobtype
     */
    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }
    /**
     * ���ܣ��������ģʽ 1.���� 2.�ѻ�
     * @author hug 2012-3-6 
     * @return
     */
    public String getOnLineType() {
        return this.onLineType;
    }
    /**
     * ���ܣ���������ģʽ 1.���� 2.�ѻ�
     * @author hug 2012-3-6 
     * @param onLineType
     */
    public void setOnLineType(String onLineType) {
        this.onLineType = onLineType;
    }
    /**
     * ���ܣ�����Ƿ���� Y.�� N.��
     * @author hug 2012-3-6 
     * @return
     */
    public String getIsaccount() {
        return this.isaccount;
    }
    /**
     * ���ܣ������Ƿ���� Y.�� N.��
     * @author hug 2012-3-6 
     * @param isaccount
     */
    public void setIsaccount(String isaccount) {
        this.isaccount = isaccount;
    }
    /**
     * ���ܣ������ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
     * @author hug 2012-3-6 
     * @return
     */
    public String getStatus() {
        return this.status;
    }
    /**
     * ���ܣ�������ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
     * @author hug 2012-3-6 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * ���ܣ�������ȼ�
     * @author hug 2012-3-6 
     * @return
     */
    public Integer getPriority() {
        return this.priority;
    }
    /**
     * ���ܣ��������ȼ�
     * @author hug 2012-3-6 
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    /**
     * ���ܣ����ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * ���ܣ�����ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ���ö���ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getReadtime() {
        return this.readtime;
    }
    /**
     * ���ܣ����ö���ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param readtime
     */
    public void setReadtime(String readtime) {
        this.readtime = readtime;
    }
    /**
     * ���ܣ�������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getFinishtime() {
        return this.finishtime;
    }
    /**
     * ���ܣ��������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param finishtime
     */
    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }
    /**
     * ���ܣ������ҵ���� 1.���� 2.�����
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobpos() {
        return this.jobpos;
    }
    /**
     * ���ܣ�������ҵ���� 1.���� 2.�����
     * @author hug 2012-3-6 
     * @param jobpos
     */
    public void setJobpos(String jobpos) {
        this.jobpos = jobpos;
    }
    /**
     * ���ܣ������ҵ���
     * @author hug 2012-3-6 
     * @return
     */
    public String getShiftid() {
        return this.shiftid;
    }
    /**
     * ���ܣ�������ҵ���
     * @author hug 2012-3-6 
     * @param shiftid
     */
    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
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
     * ���ܣ�������ͺ�
     * @author hug 2012-3-6 
     * @return
     */
    public String getSnumber() {
        return this.snumber;
    }
    /**
     * ���ܣ��������ͺ�
     * @author hug 2012-3-6 
     * @param snumber
     */
    public void setSnumber(String snumber) {
        this.snumber = snumber;
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
     * ���ܣ����ִ��·��1-ֱ��/ֱ��2-����
     * @author hug 2012-3-6 
     * @return
     */
    public String getRoute() {
        return this.route;
    }
    /**
     * ���ܣ�����ִ��·��1-ֱ��/ֱ��2-����
     * @author hug 2012-3-6 
     * @param route
     */
    public void setRoute(String route) {
        this.route = route;
    }
    /**
     * ���ܣ���ò�����
     * @author hug 2012-3-6 
     * @return
     */
    public String getOpManId() {
        return this.opManId;
    }
    /**
     * ���ܣ����ò�����
     * @author hug 2012-3-6 
     * @param opManId
     */
    public void setOpManId(String opManId) {
        this.opManId = opManId;
    }
    /**
     * ���ܣ���õ��������
     * @author hug 2012-3-6 
     * @return
     */
    public String getTaskid() {
        return this.taskid;
    }
    /**
     * ���ܣ����õ��������
     * @author hug 2012-3-6 
     * @param taskid
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * ���ܣ�
     * @author hug 2012-4-16 
     * @return
     */
    public String getInvoicetype() {
        return invoicetype;
    }

    /**
     * ���ܣ�����1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
     * @author hug 2012-4-16 
     * @param invoicetype
     */
    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }
    
    /**
     * ���ܣ����Դ��λID
     * @author hug 2012-3-6 
     * @return
     */
    public String getScargoSpaceId() {
        return this.scargoSpaceId;
    }
    /**
     * ���ܣ�����Դ��λID
     * @author hug 2012-3-6 
     * @param scargoSpaceId
     */
    public void setScargoSpaceId(String scargoSpaceId) {
        this.scargoSpaceId = scargoSpaceId;
    }
    /**
     * ���ܣ����Դ���ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getScargoAlleyId() {
        return this.scargoAlleyId;
    }
    /**
     * ���ܣ�����Դ���ID
     * @author hug 2012-3-6 
     * @param scargoAlleyId
     */
    public void setScargoAlleyId(String scargoAlleyId) {
        this.scargoAlleyId = scargoAlleyId;
    }
   
    /**
     * ���ܣ����Ŀ���λID
     * @author hug 2012-3-6 
     * @return
     */
    public String getTcargoSpaceId() {
        return this.tcargoSpaceId;
    }
    /**
     * ���ܣ�����Ŀ���λID
     * @author hug 2012-3-6 
     * @param tcargoSpaceId
     */
    public void setTcargoSpaceId(String tcargoSpaceId) {
        this.tcargoSpaceId = tcargoSpaceId;
    }
    /**
     * ���ܣ����Ŀ�����ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getTcargoAlleyId() {
        return this.tcargoAlleyId;
    }
    /**
     * ���ܣ�����Ŀ�����ID
     * @author hug 2012-3-6 
     * @param tcargoAlleyId
     */
    public void setTcargoAlleyId(String tcargoAlleyId) {
        this.tcargoAlleyId = tcargoAlleyId;
    }
    
    /**
     * ���ܣ����Ŀ�����ID
     * @author hug 2012-4-16 
     * @return
     */
    public String getTcargoWhareaId() {
        return tcargoWhareaId;
    }


    /**
     * ���ܣ�����Ŀ�����ID
     * @author hug 2012-4-16 
     * @param tcargoWhareaId
     */
    public void setTcargoWhareaId(String tcargoWhareaId) {
        this.tcargoWhareaId = tcargoWhareaId;
    }
    /**
     * ���ܣ����Դ����ID
     * @author hug 2012-4-16 
     * @return
     */
    public String getScargoWhareaId() {
        return scargoWhareaId;
    }


    /**
     * ���ܣ�����Դ����ID
     * @author hug 2012-4-16 
     * @param scargoWhareaId
     */
    public void setScargoWhareaId(String scargoWhareaId) {
        this.scargoWhareaId = scargoWhareaId;
    }

    /**
     * ���ܣ����������� 0:�ջ�1�ϼ���� 2.����
     * @author hug 2012-4-18 
     * @return
     */
    public String getInOutType() {
        return inOutType;
    }

    /**
     * ���ܣ������������ 0:�ջ�1�ϼ���� 2.����
     * @author hug 2012-4-18 
     * @param inOutType
     */
    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    /**
     * ���ܣ������ҵ��������
     * @author hug 2012-6-20 
     * @return
     */
    public String getJobtypeName() {
        return jobtypeName;
    }

    /**
     * ���ܣ�������ҵ��������
     * @author hug 2012-6-20 
     * @param jobtypeName
     */
    public void setJobtypeName(String jobtypeName) {
        this.jobtypeName = jobtypeName;
    }

    /**
     * ���ܣ���ò�����
     * @author hug 2012-6-20 
     * @return
     */
    public String getOpMan() {
        return opMan;
    }

    /**
     * ���ܣ����ò�����
     * @author hug 2012-6-20 
     * @param opMan
     */
    public void setOpMan(String opMan) {
        this.opMan = opMan;
    }

    /**
     * ���ܣ����Դ���
     * @author hug 2012-6-20 
     * @return
     */
    public String getScargoAlleyName() {
        return scargoAlleyName;
    }

    /**
     * ���ܣ�����Դ���
     * @author hug 2012-6-20 
     * @param scargoAlleyName
     */
    public void setScargoAlleyName(String scargoAlleyName) {
        this.scargoAlleyName = scargoAlleyName;
    }

    /**
     * ���ܣ����Դ��λ
     * @author hug 2012-6-20 
     * @return
     */
    public String getScargoSpaceName() {
        return scargoSpaceName;
    }

    /**
     * ���ܣ�����Դ��λ
     * @author hug 2012-6-20 
     * @param scargoSpaceName
     */
    public void setScargoSpaceName(String scargoSpaceName) {
        this.scargoSpaceName = scargoSpaceName;
    }

    /**
     * ���ܣ����Դ����
     * @author hug 2012-6-20 
     * @return
     */
    public String getScargoWhareaName() {
        return scargoWhareaName;
    }

    /**
     * ���ܣ�����Դ����
     * @author hug 2012-6-20 
     * @param scargoWhareaName
     */
    public void setScargoWhareaName(String scargoWhareaName) {
        this.scargoWhareaName = scargoWhareaName;
    }

    /**
     * ���ܣ������ҵ״̬����
     * @author hug 2012-6-20 
     * @return
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * ���ܣ�������ҵ״̬����
     * @author hug 2012-6-20 
     * @param statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * ���ܣ����Ŀ�����
     * @author hug 2012-6-20 
     * @return
     */
    public String getTcargoAlleyName() {
        return tcargoAlleyName;
    }

    /**
     * ���ܣ�����Ŀ�����
     * @author hug 2012-6-20 
     * @param tcargoAlleyName
     */
    public void setTcargoAlleyName(String tcargoAlleyName) {
        this.tcargoAlleyName = tcargoAlleyName;
    }

    /**
     * ���ܣ����Ŀ���λ
     * @author hug 2012-6-20 
     * @return
     */
    public String getTcargoSpaceName() {
        return tcargoSpaceName;
    }

    /**
     * ���ܣ�����Ŀ���λ
     * @author hug 2012-6-20 
     * @param tcargoSpaceName
     */
    public void setTcargoSpaceName(String tcargoSpaceName) {
        this.tcargoSpaceName = tcargoSpaceName;
    }

    /**
     * ���ܣ����Ŀ�����
     * @author hug 2012-6-20 
     * @return
     */
    public String getTcargoWhareaName() {
        return tcargoWhareaName;
    }

    /**
     * ���ܣ�����Ŀ�����
     * @author hug 2012-6-20 
     * @param tcargoWhareaName
     */
    public void setTcargoWhareaName(String tcargoWhareaName) {
        this.tcargoWhareaName = tcargoWhareaName;
    }

    /**
     * ����:��÷���ǰ��λ״̬
     * @author hug 2012-9-6 
     * @return
     */
    public String getOldspacestatus() {
        return oldspacestatus;
    }

    /**
     * ����:���÷���ǰ��λ״̬
     * @author hug 2012-9-6 
     * @param oldspacestatus
     */
    public void setOldspacestatus(String oldspacestatus) {
        this.oldspacestatus = oldspacestatus;
    }

    /**
     * ����: �����ҵ��� 1:�Զ����������ҵ
     * @author hug 2012-10-24 
     * @return
     */
    public String getJobcategory() {
        return jobcategory;
    }

    /**
     * ����: ������ҵ��� 1:�Զ����������ҵ
     * @author hug 2012-10-24 
     * @param jobcategory
     */
    public void setJobcategory(String jobcategory) {
        this.jobcategory = jobcategory;
    }

	public String getBoundstockid() {
		return boundstockid;
	}

	public void setBoundstockid(String boundstockid) {
		this.boundstockid = boundstockid;
	}

	public String getBoundstockdetailid() {
		return boundstockdetailid;
	}

	public void setBoundstockdetailid(String boundstockdetailid) {
		this.boundstockdetailid = boundstockdetailid;
	}
	
	public String getVirtualwhname() {
		return Virtualwhname;
	}

	public void setVirtualwhname(String virtualwhname) {
		Virtualwhname = virtualwhname;
	}

	public String getVirtualwhid() {
		return Virtualwhid;
	}

	public void setVirtualwhid(String virtualwhid) {
		Virtualwhid = virtualwhid;
	}

/**
  * ��ҵ���� ��ѯ
  * @param warehouseid
  * @param whAreaId
  * @param indate
  * @param jobid
  * @param instockid
  * @param status
  * @param traycode
  * @param isback
  * @param productid
  * @param lotinfo
  * @return
  */
	public String getQueryHQL(String warehouseid,String Virtualwhid,String whAreaId,String indate,String jobid,String instockid,String status,String traycode,String isback,String productid,String lotinfo,String taskid,String inOutType)
	{
		String hql = "select distinct ta from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
			hql += " and ta.Virtualwhid='"+Virtualwhid+"'";
		}
		if(whAreaId != null && whAreaId.trim().length() > 0){
			hql += " and ta.scargoWhareaId='"+whAreaId+"'";
		}
		if(indate != null && indate.trim().length() > 0){
			hql += " and ta.createtime like '%"+indate+"%'";
		}
		if(jobid != null && jobid.trim().length() > 0){
			hql += " and ta.jobid like '%"+jobid+"%'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.boundstockid='"+instockid+"'";
		}
		if(status != null && status.trim().length() > 0){
			hql += " and ta.status ='"+status+"'";
		}
		if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
			hql += " and ta.jobtype ='hl'";
		}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
			hql += " and ta.jobtype !='hl'";
		}
		if(traycode != null && traycode.trim().length() > 0){
			hql += " and ta.traycode ='"+traycode+"'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo  ='"+lotinfo+"'";
		}
		if(taskid != null && taskid.trim().length() > 0){
			hql += " and ta.taskid  ='"+taskid+"'";
		}
		if(inOutType != null && inOutType.trim().length() > 0){
			hql += " and ta.inOutType  ='"+inOutType+"'";
		}
		hql += " order by ta.warehouseid,ta.boundstockid";
		return hql;
	}
	 /**
	  * ��ҵ���� ��ѯ
	  * @param warehouseid
	  * @param whAreaId
	  * @param indate
	  * @param jobid
	  * @param instockid
	  * @param status
	  * @param traycode
	  * @param isback
	  * @param productid
	  * @param lotinfo
	  * @return
	  */
	public String getQueryHQLCount(String warehouseid,String Virtualwhid,String whAreaId,String indate,String jobid,String instockid,String status,String traycode,String isback,String productid,String lotinfo,String taskid,String inOutType)
	{
		String hql = "select count(distinct ta) from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
			hql += " and ta.Virtualwhid='"+Virtualwhid+"'";
		}
		if(whAreaId != null && whAreaId.trim().length() > 0){
			hql += " and ta.scargoWhareaId='"+whAreaId+"'";
		}
		if(indate != null && indate.trim().length() > 0){
			hql += " and ta.createtime like '%"+indate+"%'";
		}
		if(jobid != null && jobid.trim().length() > 0){
			hql += " and ta.jobid like '%"+jobid+"%'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.boundstockid='"+instockid+"'";
		}
		if(status != null && status.trim().length() > 0){
			hql += " and ta.status ='"+status+"'";
		}
		if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
			hql += " and ta.jobtype ='hl'";
		}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
			hql += " and ta.jobtype !='hl'";
		}
		if(traycode != null && traycode.trim().length() > 0){
			hql += " and ta.traycode ='"+traycode+"'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo  ='"+lotinfo+"'";
		}
		if(taskid != null && taskid.trim().length() > 0){
			hql += " and ta.taskid  ='"+taskid+"'";
		}
		if(inOutType != null && inOutType.trim().length() > 0){
			hql += " and ta.inOutType  ='"+inOutType+"'";
		}
		return hql;
	}
	/**
	 * ��ҵ����ҵ��ϸ��ѯ
	 * @param warehouseid
	 * @param whAreaId
	 * @param alleyId
	 * @param cargospaceid
	 * @param onlinetype
	 * @param indate_from
	 * @param indate_to
	 * @param isback
	 * @param invoicetype
	 * @param instockid
	 * @param productid
	 * @param lotinfo
	 * @param traycode
	 * @param inOutType
	 * @return
	 */
		public String getQueryJobADetailHQL(String warehouseid,String whAreaId,String alleyId,String cargospaceid,String onlinetype,String indate_from,String indate_to,String isback,String invoicetype,String instockid,String productid,String lotinfo,String traycode,String inOutType)
		{
			String hql = " from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
			if(warehouseid != null && warehouseid.trim().length() > 0){
				hql += " and ta.warehouseid='"+warehouseid+"'";
			}
			if(whAreaId != null && whAreaId.trim().length() > 0){
				hql += " and ta.scargoWhareaId='"+whAreaId+"'";
			}
			if(alleyId != null && alleyId.trim().length() > 0){
				hql += " and ta.alleyId = '"+alleyId+"'";
			}
			if(cargospaceid != null && cargospaceid.trim().length() > 0){
				hql += " and ta.cargospaceid = '"+cargospaceid+"'";
			}
			if(onlinetype != null && onlinetype.trim().length() > 0){
				hql += " and ta.onlinetype = '"+onlinetype+"'";
			}
			if(jobid != null && jobid.trim().length() > 0){
				hql += " and ta.jobid like '%"+jobid+"%'";
			}
			if(indate_from != null && indate_from.trim().length() > 0){
				hql += " and ta.createtime >= '"+indate_from+"'";
			}
			if(indate_to != null && indate_to.trim().length() > 0){
				hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
			}
			if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
				hql += " and ta.jobtype ='hl'";
			}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
				hql += " and ta.jobtype !='hl'";
			}
			if(invoicetype != null && invoicetype.trim().length() > 0){
				hql += " and ta.invoicetype='"+invoicetype+"'";
			}
			if(instockid != null && instockid.trim().length() > 0){
				hql += " and ta.boundstockid='"+instockid+"'";
			}
			if(traycode != null && traycode.trim().length() > 0){
				hql += " and ta.traycode ='"+traycode+"'";
			}
			if(productid != null && productid.trim().length() > 0){
				hql += " and tb.productid ='"+productid+"'";
			}
			if(lotinfo != null && lotinfo.trim().length() > 0){
				hql += " and tb.lotinfo  like '%"+lotinfo+"%'";
			}
			if(inOutType != null && inOutType.trim().length() > 0){
				hql += " and ta.inOutType  ='"+inOutType+"'";
			}
			
			hql += " order by ta.warehouseid,ta.boundstockid";
			return hql;
		}
		/**
		 * ��ҵ����ҵ��ϸ��ѯ
		 * @param warehouseid
		 * @param whAreaId
		 * @param alleyId
		 * @param cargospaceid
		 * @param onlinetype
		 * @param indate_from
		 * @param indate_to
		 * @param isback
		 * @param invoicetype
		 * @param instockid
		 * @param productid
		 * @param lotinfo
		 * @param traycode
		 * @param inOutType
		 * @return
		 */
			public String getQueryJobADetailHQLCount(String warehouseid,String whAreaId,String alleyId,String cargospaceid,String onlinetype,String indate_from,String indate_to,String isback,String invoicetype,String instockid,String productid,String lotinfo,String traycode,String inOutType)
			{
				String hql = " select count(*) from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
				if(warehouseid != null && warehouseid.trim().length() > 0){
					hql += " and ta.warehouseid='"+warehouseid+"'";
				}
				if(whAreaId != null && whAreaId.trim().length() > 0){
					hql += " and ta.scargoWhareaId='"+whAreaId+"'";
				}
				if(alleyId != null && alleyId.trim().length() > 0){
					hql += " and ta.alleyId = '"+alleyId+"'";
				}
				if(cargospaceid != null && cargospaceid.trim().length() > 0){
					hql += " and ta.cargospaceid = '"+cargospaceid+"'";
				}
				if(onlinetype != null && onlinetype.trim().length() > 0){
					hql += " and ta.onlinetype = '"+onlinetype+"'";
				}
				if(jobid != null && jobid.trim().length() > 0){
					hql += " and ta.jobid like '%"+jobid+"%'";
				}
				if(indate_from != null && indate_from.trim().length() > 0){
					hql += " and ta.createtime >= '"+indate_from+"'";
				}
				if(indate_to != null && indate_to.trim().length() > 0){
					hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
				}
				if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
					hql += " and ta.jobtype ='hl'";
				}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
					hql += " and ta.jobtype !='hl'";
				}
				if(invoicetype != null && invoicetype.trim().length() > 0){
					hql += " and ta.invoicetype='"+instockid+"'";
				}
				if(instockid != null && instockid.trim().length() > 0){
					hql += " and ta.boundstockid='"+instockid+"'";
				}
				if(traycode != null && traycode.trim().length() > 0){
					hql += " and ta.traycode ='"+traycode+"'";
				}
				if(productid != null && productid.trim().length() > 0){
					hql += " and tb.productid ='"+productid+"'";
				}
				if(lotinfo != null && lotinfo.trim().length() > 0){
					hql += " and tb.lotinfo  like '%"+lotinfo+"%'";
				}
				if(inOutType != null && inOutType.trim().length() > 0){
					hql += " and ta.inOutType  ='"+inOutType+"'";
				}
				return hql;
			}
	public String getBoundrequeststockid() {
		return boundrequeststockid;
	}

	public void setBoundrequeststockid(String boundrequeststockid) {
		this.boundrequeststockid = boundrequeststockid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getInvoicetypename() {
		return invoicetypename;
	}

	public void setInvoicetypename(String invoicetypename) {
		this.invoicetypename = invoicetypename;
	}   
/**
 * ������ ȡ���ջ�
 * @param boundstockid
 * @param boundstockdetailid
 * @return
 */
	public String getQueryHQLByboundid(String boundstockid,String boundstockdetailid)
	{
		String hql = "from InoutJob where 1=1 and status!='5'";
		if(boundstockid != null && boundstockid.trim().length() > 0){
			hql += " and boundstockid='"+boundstockid+"'";
		}
		if(boundstockdetailid != null && boundstockdetailid.trim().length() > 0){
			hql += " and boundstockdetailid='"+boundstockdetailid+"'";
		}
		return hql;
	}
	/**
	 * ����:���������ҵ����ҵ����
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param strUserCode	
	 * @return 
	 * @throws Exception 
	 * @throws Exception
	 */
	public String cancelJobs(String jobids, String strUserCode,EntityDAO dao) throws Exception{
		String strBackMsg = "Y";
		try {
			    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
			    ScheduleTask task = null;
				ITaskDao iTaskDao  = new TaskDaoImpl(dao);
				if(jobids!=null && !jobids.equals("")){
					String[] jobid = jobids.split(",");
					for(int i=0; i<jobid.length; i++){
						   String jobidsString = jobid[i].intern();
							synchronized (jobidsString) {
								InoutJob job = getJobByJobid(jobid[i],dao);
								InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
								if(job != null){
									if(job.getStatus().equals("1") || job.getStatus().equals("2")){
										if(job.getJobtype().equals("hl")){ //������ҵ���ϵĴ���ʽ
											job.setStatus("5"); //���ϻ�����ҵ
											BaseCargospace space = null;
											//��ԭ���ݴ��λ�����
											InventoryStorage inventory = null ;
											inventory = InventoryStorage.getQueryHQLBytraycode(job.getTraycode(), job.getScargoWhareaId(), dao);
											if(job.getTcargoSpaceId()!=null && !job.getTcargoSpaceId().trim().equals("")){
												space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
												space.setCsstatus("1");
											}
											if(inventory == null){
												inventory = new InventoryStorage();
												inventory.setCargoSpaceId(job.getWarehouseid()+StandardConstant.zcidkzl+"010101");
												inventory.setWhAreaId(job.getWarehouseid()+StandardConstant.zcidkzl);
												inventory.setWarehouseid(job.getWarehouseid());
												inventory.setProductid(jobdetail.getProductid());
												inventory.setProductdate(jobdetail.getPrintdate());
												inventory.setIndate(StrTools.getCurrDateTime(2));
												inventory.setLotid(jobdetail.getLotid());
												inventory.setLotinfo(jobdetail.getLotinfo());
												inventory.setPackid(jobdetail.getPackid());
												inventory.setIntype("2"); //�ѻ�
												inventory.setPunit(jobdetail.getPunit());
												inventory.setIsplit("1"); //����
												inventory.setPnum(jobdetail.getJobnum());
												inventory.setInjobid(job.getJobid());
												inventory.setTraycode(job.getTraycode());
												inventory.setProductcode(jobdetail.getProductcode());
												inventory.setProductstatus(jobdetail.getProductStatus());
												inventory.setStatus("0");
												
												task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
												if(task!=null){
													task.setStatus("5");          //����״̬  ����
												}
												ZFJob(job,space,null,inventory,task,dao);
											}else{
												task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
												if(task!=null){
													task.setStatus("5");          //����״̬  ����
												}
												inventory.setPnum(inventory.getPnum()+jobdetail.getJobnum());
												ZFJobupdate(job,space,null,inventory,task,dao);
											}	
										}else{//�������������ҵ
											job.setStatus("5"); //���ϻ�����ҵ
											BaseCargospace space = null;
											if(job.getTcargoSpaceId()!=null && !job.getTcargoSpaceId().trim().equals("")){
												space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
												space.setCsstatus("1");
											}
											ZFJob(job,space,null,null,task,dao);
										}
									}else{
										strBackMsg = "ֻ��δִ�л��ִ����ҵ�������ϣ�";
									}
								}
							}
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "������ҵ����ҵ����ʱ��������";
			return strBackMsg;
		}
		return strBackMsg;
	}
	/**
	 * ����:��ʼ����ҵ����ҵ����
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param strUserCode	
	 * @return 
	 * @throws Exception 
	 * @throws Exception
	 */
	public String initializeJobs(String jobids, String strUserCode,EntityDAO dao) throws Exception{
		String strBackMsg = "��ʼ���ɹ�";
		try {
			    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
			    IAlleyBus alleyBus = new AlleyBusImpl(dao);
				if(jobids!=null && !jobids.equals("")){
					String[] jobid = jobids.split(",");
					for(int i=0; i<jobid.length; i++){
						synchronized (jobid[i]) {
							InoutJob job = getJobByJobid(jobid[i],dao);
							InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
							if(job != null){
								if(job.getInvoicetype().equals("1") && job.getInOutType().equals("1") ){ //�����ҵ���ϵĴ���ʽ 1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
									if(job.getStatus().equals("2")){ //��ִ��״̬
										strBackMsg = "��ִ����ҵ�����ʼ��";
									}else if(job.getStatus().equals("3")){ //��ִ��״̬
										job.setStatus("1"); //��ʼ����ҵ
										job.setTcargoAlleyId("");//��ʼ�����
										job.setTcargoWhareaId("");
										job.setTaskid("");
										job.setReadtime("");
										//��ʼ����λ
										if(job.getTcargoSpaceId()!=null && !job.getTcargoSpaceId().equals("")){
											BaseCargospace space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
											if(space!=null){
												job.setTcargoSpaceId("");
												space.setCsstatus("1"); //�ĳɿջ�λ״̬
												cancelJob(job,space,dao);
											}else{
												strBackMsg = "��ҵ��Ϊ"+job.getJobid()+"����ҵ��ʼ��ʧ��";
											}
										}
									}
								}
							}
						}		
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "������ҵ����ҵ����ʱ��������";
			return strBackMsg;
		}
		return strBackMsg;
	}
/**
 * �����ҵ
 * @param jobids
 * @param strUserCode
 * @param dao
 * @return
 * @throws Exception
 */
	public String finishJobs(String jobids, String strUserCode,EntityDAO dao) throws Exception{
		String strBackMsg = "Y";
		try {
			    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
				if(jobids!=null && !jobids.equals("")){
					String[] jobid = jobids.split(",");
					for(int i=0; i<jobid.length; i++){
								String jobidsString = jobid[i].intern();
								synchronized (jobidsString) {
									InoutJob job = getJobByJobid(jobid[i],dao);
									InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
									if(job!=null){
										if(job.status.equals("1") || job.status.equals("2") || job.status.equals("3")){
											if(job.getTcargoSpaceId()!=null && job.getTcargoSpaceId().length()>0){
												if(!job.getJobtype().equals("hl") && job.getInOutType().equals("1") ){
													job.setStatus("4"); //�����ҵ
														BaseCargospace space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
														if(space!=null){
															space.setCsstatus("2"); //�ĳ�����λ״̬
															InventoryStorage inventory = new InventoryStorage();
															inventory.setCargoSpaceId(job.getTcargoSpaceId());
															inventory.setCargoAlleyId(job.getTcargoAlleyId());
															inventory.setWhAreaId(job.getTcargoWhareaId());
															inventory.setWarehouseid(job.getWarehouseid());
															inventory.setVirtualwhid(job.getVirtualwhid());
															inventory.setProductid(jobdetail.getProductid());
															inventory.setProductdate(jobdetail.getPrintdate());
															inventory.setIndate(StrTools.getCurrDateTime(2));
															inventory.setLotid(jobdetail.getLotid());
															inventory.setLotinfo(jobdetail.getLotinfo());
															inventory.setLotinfo2(jobdetail.getLotinfo2());
															inventory.setSupplier(jobdetail.getSupplier());
															inventory.setPackid(jobdetail.getPackid());
															inventory.setIntype("2"); //�ѻ�
															inventory.setPunit(jobdetail.getPunit());
															inventory.setProductstatus(jobdetail.getProductStatus());//��Ʒ״̬
															inventory.setPnum(jobdetail.getJobnum());
															inventory.setInjobid(job.getJobid());
															inventory.setInjobetailid(jobdetail.getJobdetailid());
															inventory.setTraycode(job.getTraycode());
															inventory.setProductcode(jobdetail.getProductcode());
															inventory.setStatus("0"); //δ����״̬
															
															String finishtime = StrTools.getCurrDateTime(2);
															job.setFinishtime(finishtime);//���ʱ��
															
															ScheduleTask task = null;
															ITaskDao iTaskDao  = new TaskDaoImpl(dao);
															task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
															if(task!=null){
																task.setStatus("4");          //����״̬  4.���
															}
															
															finishJobz(job,space,null,inventory,task,dao);
														}else{
															strBackMsg = "��ҵ��Ϊ"+job.getJobid()+"����ҵ�ֶ����ʧ��";
														}
												}else if(job.getJobtype().equals("hl") && job.getInOutType().equals("1")){ //������ҵ��ɴ���ʽ
													job.setStatus("4"); //�����ҵ
													//�ֶ���� Ŀ���λΪ��
													if(job.getTcargoSpaceId() == null || job.getTcargoSpaceId().length() <= 0){
														
														strBackMsg = "��ҵ:"+job.getJobid()+"Ŀ���λΪ�յ�,�ֶ����ʧ��";
														//��Դ��λID = Դ��λID
														//job.setTcargoSpaceId(job.getScargoSpaceId());  
														
													}
													if(job.getTcargoSpaceId() != null || job.getTcargoSpaceId().length() > 0){
														BaseCargospace space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
														if(space!=null){
															space.setCsstatus("2"); //�ĳ�����λ״̬
															InventoryStorage inventory = new InventoryStorage();
															inventory.setCargoSpaceId(job.getTcargoSpaceId());
															inventory.setCargoAlleyId(job.getTcargoAlleyId());
															inventory.setWhAreaId(job.getTcargoWhareaId());
															inventory.setWarehouseid(job.getWarehouseid());
															inventory.setVirtualwhid(job.getVirtualwhid());
															inventory.setProductid(jobdetail.getProductid());
															inventory.setProductdate(jobdetail.getPrintdate());
															inventory.setIndate(StrTools.getCurrDateTime(2));
															inventory.setLotid(jobdetail.getLotid());
															inventory.setLotinfo(jobdetail.getLotinfo());
															inventory.setLotinfo2(jobdetail.getLotinfo2());
															inventory.setSupplier(jobdetail.getSupplier());
															inventory.setPackid(jobdetail.getPackid());
															inventory.setProductstatus(jobdetail.getProductStatus());//��Ʒ״̬
															inventory.setIntype("2"); //�ѻ�
															inventory.setPunit(jobdetail.getPunit());
															inventory.setPnum(jobdetail.getJobnum());
															inventory.setInjobid(job.getJobid());
															inventory.setInjobetailid(jobdetail.getJobdetailid());
															inventory.setTraycode(job.getTraycode());
															inventory.setProductcode(jobdetail.getProductcode());
															inventory.setStatus("0");
															
															String finishtime = StrTools.getCurrDateTime(2);
															job.setFinishtime(finishtime);//���ʱ��
															
															//finishJob(job,space,null,inventory,dao);
															ScheduleTask task = null;
															ITaskDao iTaskDao  = new TaskDaoImpl(dao);
															task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
															if(task!=null){
																task.setStatus("4");          //����״̬  4.���
															}
															finishJobz(job, space, null, inventory, task, dao);
														}
														/*else{
															strBackMsg = "��ҵ��Ϊ"+job.getJobid()+"����ҵ�ֶ����ʧ��";
														}*/
													}
												}
											}else{
												strBackMsg = "ֻ�з����λ����ҵ�����ֶ���ɣ�";
												break;
											}
										}else{
											strBackMsg = "ֻ��δ�����ҵ�����ֶ���ɣ�";
											break;
										}
									}
								}

					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "�ֶ������ҵ����ҵ����ʱ��������";
			return strBackMsg;
		}
		return strBackMsg;
	}
	/**
	 * �����ҵ �ƿ⵽�ݴ���
	 * @param jobid
	 * @param dao
	 * @return
	 * @throws Exception
	 */
		public String finishJobsToTem(String jobids, String strUserCode,EntityDAO dao) throws Exception{
			String strBackMsg = "�ɹ��ƿ⵽�ݴ���";
			try {
				    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
					if(jobids!=null && !jobids.equals("")){
						String[] jobid = jobids.split(",");
						for(int i=0; i<jobid.length; i++){
							synchronized (jobid[i]) {
								InoutJob job = getJobByJobid(jobid[i],dao);
								InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
								if(job != null){
									InboundDetail detail = new InboundDetail();
									detail =  new InboundDetail().getDetailByidtray(job.getBoundstockid(), job.getTraycode(), dao);
									if(job.getInvoicetype().equals("1") && job.getInOutType().equals("1") ){ //�����ҵ���ϵĴ���ʽ 1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
										if(job.getStatus().equals("1") && detail!=null){ //δִ����ҵ
											job.setStatus("4"); //�����ҵ
											detail.setStatus("4"); //�ϼ�״̬
											//if(job.getTcargoSpaceId()==null || job.getTcargoSpaceId().equals("")){
											if(job.getTcargoSpaceId()==null || job.getTcargoSpaceId().equals("")|| !job.getTcargoSpaceId().equals("")){
												BaseCargospace space = spaceIml.getCargoSpaceById(job.getWarehouseid()+StandardConstant.zcidkzl+"010101"); //�ƿ⵽�ݴ���
												if(space!=null){
													space.setCsstatus("2"); //�ĳ�����λ״̬
													InventoryStorage inventory = new InventoryStorage();
													inventory.setCargoSpaceId(job.getWarehouseid()+StandardConstant.zcidkzl+"010101");
													inventory.setWhAreaId(job.getWarehouseid()+StandardConstant.zcidkzl);
													inventory.setWarehouseid(job.getWarehouseid());
													inventory.setProductid(jobdetail.getProductid());
													inventory.setProductdate(jobdetail.getPrintdate());
													inventory.setIndate(StrTools.getCurrDateTime(2));
													inventory.setLotid(jobdetail.getLotid());
													inventory.setLotinfo(jobdetail.getLotinfo());
													inventory.setPackid(jobdetail.getPackid());
													inventory.setIntype("2"); //�ѻ�
													inventory.setPunit(jobdetail.getPunit());
													inventory.setIsplit("1"); //����
													inventory.setPnum(jobdetail.getJobnum());
													inventory.setInjobid(job.getJobid());
													inventory.setTraycode(job.getTraycode());
													inventory.setRequestid(job.getBoundrequeststockid()); //���뵥
													inventory.setInstockid(job.getBoundstockid());
													inventory.setProductcode(jobdetail.getProductcode());
													inventory.setProductstatus(jobdetail.getProductStatus());
													inventory.setStatus("0"); //δ����״̬�����״̬��
													finishJob(job,space,detail,inventory,dao);
												}else{
													strBackMsg = "��ҵ��Ϊ"+job.getJobid()+"����ҵ�ƿ⵽�ݴ���ʧ�ܣ�û���ҵ���Ӧ���ݴ���";
												}
											}
										}else{
											strBackMsg = "��ҵ��Ϊ"+job.getJobid()+"����ҵ����δִ����ҵ�������Ƶ��ݴ���";
										}
									}
								}
							}
								
						}
					}
			}catch (Exception e) {
				e.printStackTrace();
				strBackMsg = "�ƿ⵽�ݴ���ʧ�ܣ�";
				return strBackMsg;
			}
			return strBackMsg;
		}
	/**
	 * ����:������ҵ,ɾ�����,�޸Ļ�λ״̬
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //�޸���ҵ
            if(job!=null){
            	session.update(job);
            }
            
            //�޸Ļ�λ״̬
            if(cargospace!=null){
            	session.update(cargospace);
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("������ҵ,ɾ�����,�޸Ļ�λ״̬ʱ�����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * �����ҵ
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param dao
     * @throws Exception
     */
	public void finishJob(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //�޸���ҵ
            if(job!=null){
                session.update(job);	
            }

            //�޸Ļ�λ״̬
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //�޸���ҵ
            if(detail!=null){
            	session.update(detail);
            }
            
            //������
            if(inventory!=null){
                session.save(inventory);
            }

            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ,��ӿ��,�޸Ļ�λ״̬,�޸���ⵥ��ϸ״̬ʱ�����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
	/**
     * ���������ҵ(������ҵ����ͨ�����ҵ)
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param dao
     * @throws Exception
     */
	public void ZFJob(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //�޸���ҵ
            if(job!=null){
                session.update(job);	
            }

            //�޸Ļ�λ״̬
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //�޸���ҵ
            if(detail!=null){
            	session.update(detail);
            }
            //�޸ĵ���ָ��
            if(detail!=null){
            	session.update("ScheduleTask",task);
            }
            //������
            if(inventory!=null){
                session.save(inventory);
            }

            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ,��ӿ��,�޸Ļ�λ״̬,�޸���ⵥ��ϸ״̬ʱ�����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * �����ҵ
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param ScheduleTask
     * @param dao
     * @throws Exception
     */
	public void finishJobz(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //�޸���ҵ
            if(job!=null){
                session.update(job);	
            }

            //�޸Ļ�λ״̬
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //�޸���ҵ
            if(detail!=null){
            	session.update(detail);
            }
            
            //������
            if(inventory!=null){
                session.save(inventory);
            }
			// �޸ĵ���
			if (task != null) {
				session.update("ScheduleTask",task);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ,��ӿ��,�޸Ļ�λ״̬,�޸���ⵥ��ϸ״̬ʱ�����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * �������
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param ScheduleTask
     * @param dao
     * @throws Exception
     */
	public void rkadd(BaseCargospace cargospace,InoutJob job,InoutJobdetail jobdetail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();

            //�޸Ļ�λ״̬
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //������ҵ
            if(job!=null){
                session.save(job);	
            }

            //������ҵ��ϸ
            if(jobdetail!=null){
            	session.save(jobdetail);
            }
            
            //������
            if(inventory!=null){
                session.save(inventory);
            }
			//�������
			if (task != null) {
				session.save("ScheduleTask",task);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�������,�޸Ļ�λ״̬,�����ҵ����ҵ��ϸ����桢����,����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * ��ҵ����
     * @param job
     * @param cargospace
     * @param inventory
     * @param dao
     * @throws Exception
     */
	public void ZFJobupdate(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //�޸���ҵ
            if(job!=null){
                session.update(job);	
            }

            //�޸Ļ�λ״̬
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //�޸���ҵ
            if(detail!=null){
            	session.update(detail);
            }
          //�޸ĵ���ָ��
            if(detail!=null){
            	session.update("ScheduleTask",task);
            }
            //������
            if(inventory!=null){
                session.update(inventory);
            }

            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ,��ӿ��,�޸Ļ�λ״̬,�޸���ⵥ��ϸ״̬ʱ�����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
	/**
	 * Rf ������ҵ
	 * @param jobdetail
	 * @param inventory
	 * @param dao
	 * @throws Exception
	 */
		public void fhJob(InoutJobdetail jobdetail,InventoryStorage inventory,EntityDAO dao) throws Exception {
			
			Session session = null;
	        Transaction tx = null;
	        try{
	        	
	            session = dao.getSession();
	            tx = session.beginTransaction();
	            //�޸���ҵ��ϸ
	            if(jobdetail!=null){
	                session.update(jobdetail);	
	            }
	            
	            //������
	            if(inventory!=null){
	                session.save(inventory);
	            }

	            tx.commit();    
	        }catch(Exception er){
	            if(tx != null){
	                tx.rollback();
	            }
	            throw new  Exception("RF���˱���ʱ������ҵ��Ϊ"+jobdetail.getJobid()+"   "+er.getMessage());
	        }finally{
	        	dao.closeSession(session);
	        }
		}

	/**
	 * Rf ������ҵ(����)
	 * 
	 * @param jobdetail
	 * @param inventory
	 * @param dao
	 * @throws Exception
	 */
	public void fhJobz(InoutJobdetail jobdetail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {

		Session session = null;
		Transaction tx = null;
		try {

			session = dao.getSession();
			tx = session.beginTransaction();
			// �޸���ҵ��ϸ
			if (jobdetail != null) {
				session.update(jobdetail);
			}

			// ������
			if (inventory != null) {
				session.save(inventory);
			}
			
			// ɾ������
			if (task != null) {
				session.delete(task);
			}

			tx.commit();
		} catch (Exception er) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception("RF���˱���ʱ������ҵ��Ϊ" + jobdetail.getJobid() + "   " + er.getMessage());
		} finally {
			dao.closeSession(session);
		}
	}
	/**
	 * Rf ������ҵ
	 * @param jobdetail
	 * @param inventory
	 * @param dao
	 * @throws Exception
	 */
		public void fhupdateJob(InoutJobdetail jobdetail,InventoryStorage inventory,EntityDAO dao) throws Exception {
			
			Session session = null;
	        Transaction tx = null;
	        try{
	        	
	            session = dao.getSession();
	            tx = session.beginTransaction();
	            //�޸���ҵ��ϸ
	            if(jobdetail!=null){
	                session.update(jobdetail);	
	            }
	            
	            //������
	            if(inventory!=null){
	                session.update(inventory);
	            }

	            tx.commit();    
	        }catch(Exception er){
	            if(tx != null){
	                tx.rollback();
	            }
	            throw new  Exception("RF���˱���ʱ������ҵ��Ϊ"+jobdetail.getJobid()+"   "+er.getMessage());
	        }finally{
	        	dao.closeSession(session);
	        }
		}
	/**
	 * ����:������ҵid��ѯ��ҵ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid,EntityDAO dao) throws Exception {
		
		if(jobid != null){
		
			String strSql = " from InoutJob as t where t.jobid='" + jobid + "'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InoutJob)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������������ѯ��ҵ ��������ҵ ���ݴ���ҵ
	 * @param traycode			��������id
	 * @return 
	 * @throws Exception
	 */
	public Object[] getJobByJobTraycode(String traycode,String warehouseid, EntityDAO dao) throws Exception {
		
		if(traycode != null && !traycode.equals("")){
		
			String strSql = "select out.customername,de.m_strProductName,de.printdate,de.lotinfo,de.jobnum,de.assignnum,t.jobid  from OutboundInvoiceHeader as out,InoutJobdetail as de,InoutJob as t where t.jobid=de.jobid and t.boundstockid=out.outstockid and t.inOutType='2' and out.outstatus not in('7','8') and t.traycode='"+traycode+"' and t.jobcategory ='1' order by t.createtime desc ";
			if(warehouseid!=null ){
				strSql += " and t.warehouseid = '"+warehouseid+"'";
			}
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (Object[])ls.get(0);
			}
		}
		return null;
	}

	
	
	
	public String getJobcategoryname() {
		return jobcategoryname;
	}

	public void setJobcategoryname(String jobcategoryname) {
		this.jobcategoryname = jobcategoryname;
	}

	/**
	 * ��ҵ����ҵ��ϸ���ֽ�����ѯ
	 * @param warehouseid
	 * @param indate_from
	 * @param indate_to
	 * @param productid
	 * @param jobtype
	 * @return
	 */
	public String getQueryJobADetailHQLPart(String warehouseid,String indate_from,String indate_to,String productid,String jobtype)
	{
		String hql = " from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid and ta.status='4' and tb.isinvoice='N' ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(indate_from != null && indate_from.trim().length() > 0){
			hql += " and ta.createtime >= '"+indate_from+"'";
		}
		if(indate_to != null && indate_to.trim().length() > 0){
			hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
		}
		if(jobtype != null && jobtype.trim().length() > 0){
			hql += " and ta.jobtype='"+jobtype+"'";
		}else{
			hql += " and ta.jobtype<>'hl'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		
		hql += " order by ta.warehouseid,ta.createtime";
		return hql;
	}
	
	/**
	 * ��ҵ����ҵ��ϸ���ֽ�����ѯ
	 * @param warehouseid
	 * @param indate_from
	 * @param indate_to
	 * @param productid
	 * @param jobtype
	 * @return
	 */
	public String getQueryJobADetailHQLCountPart(String warehouseid,String indate_from,String indate_to,String productid,String jobtype)
	{
		String hql = " select count(*) from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid and ta.status='4' and tb.isinvoice='N' ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(indate_from != null && indate_from.trim().length() > 0){
			hql += " and ta.createtime >= '"+indate_from+"'";
		}
		if(indate_to != null && indate_to.trim().length() > 0){
			hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
		}
		if(jobtype != null && jobtype.trim().length() > 0){
			hql += " and ta.jobtype='"+jobtype+"'";
		}else{
			hql += " and ta.jobtype<>'hl'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		return hql;
	}
	
}