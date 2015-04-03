package com.wms3.bms.standard.entity.job;

/**
 * 
 * ����: �쳣��ҵ��
 * @author hug 2012-3-6
 * @since WMS 3.0
 */
public class OutExjob  implements java.io.Serializable {

    private static final long serialVersionUID = 3825596874243444441L;
     private String exjobid;        //�쳣ID
     private String jobid;          //������ҵ���
     private String exstatus;       //ִ������״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
     private String createtime;     //ִ����������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     private String readtime;       //ִ���������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     private String cargoAlleyId;   //���ID
     private String cargoSpaceId;   //��λID
     private String traycode;       //��������
     private String messagecode;    //��Ϣ����
     private String message;        //ִ����Ϣ


    // Constructors

    /** default constructor */
    public OutExjob() {
    }

	/** minimal constructor */
    public OutExjob(String exstatus) {
        this.exstatus = exstatus;
    }
    
    /** full constructor */
    public OutExjob(String jobid, String exstatus, String createtime, String readtime, String cargoAlleyId, String cargoSpaceId, String traycode, String messagecode, String message) {
        this.jobid = jobid;
        this.exstatus = exstatus;
        this.createtime = createtime;
        this.readtime = readtime;
        this.cargoAlleyId = cargoAlleyId;
        this.cargoSpaceId = cargoSpaceId;
        this.traycode = traycode;
        this.messagecode = messagecode;
        this.message = message;
    }

   
    // Property accessors
    /**
     * ���ܣ�����쳣ID
     */
    public String getExjobid() {
        return this.exjobid;
    }
    /**
     * ���ܣ������쳣ID
     * @author hug 2012-3-6 
     * @param exjobid
     */
    public void setExjobid(String exjobid) {
        this.exjobid = exjobid;
    }
    /**
     * ���ܣ���õ�����ҵ���
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * ���ܣ����õ�����ҵ���
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * ���ܣ����ִ������״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
     * @author hug 2012-3-6 
     * @return
     */
    public String getExstatus() {
        return this.exstatus;
    }
    /**
     * ���ܣ�����ִ������״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
     * @author hug 2012-3-6 
     * @param exstatus
     */
    public void setExstatus(String exstatus) {
        this.exstatus = exstatus;
    }
    /**
     * ���ܣ����ִ����������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * ���ܣ�����ִ����������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ����ִ���������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getReadtime() {
        return this.readtime;
    }
    /**
     * ���ܣ�����ִ���������ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param readtime
     */
    public void setReadtime(String readtime) {
        this.readtime = readtime;
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
     * ���ܣ���û�λID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    /**
     * ���ܣ����û�λID
     * @author hug 2012-3-6 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
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
     * ���ܣ������Ϣ����
     * @author hug 2012-3-6 
     * @return
     */
    public String getMessagecode() {
        return this.messagecode;
    }
    /**
     * ���ܣ�������Ϣ����
     * @author hug 2012-3-6 
     * @param messagecode
     */
    public void setMessagecode(String messagecode) {
        this.messagecode = messagecode;
    }
    /**
     * ���ܣ����ִ����Ϣ
     * @author hug 2012-3-6 
     * @return
     */
    public String getMessage() {
        return this.message;
    }
    /**
     * ���ܣ�����ִ����Ϣ
     * @author hug 2012-3-6 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
   








}