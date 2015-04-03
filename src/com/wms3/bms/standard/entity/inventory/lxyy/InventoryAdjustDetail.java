package com.wms3.bms.standard.entity.inventory.lxyy;

/**
 * 
 * ����: ����������ϸ
 * @since WMS 3.0
 */
public class InventoryAdjustDetail  implements java.io.Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adjustdetailid;	//��������ϸID
	private String adjustid;		//������ID
	private String type;		    //1.����������2.������棨����쳣ʱ�ã����ݲ��á�
	private String status;			//״̬��1��δ���� 2���ѵ�����
	private String cargo_space_id;	//��λID
	private String wh_area_id;		//����ID
	private String warehouseid;		//�ֿ�ID
	private String spunit;			//Դ������λ
	private String tpunit;			//�ּ�����λ
	private String sproductid;		//Դ��ƷID
	private String tproductid;		//�ֲ�ƷID
	private String sprintdate;		//Դ��Ʒ��������
	private String tprintdate;		//�ֲ�Ʒ��������
	private String slotid;			//Դ��������
	private String tlotid;			//����������
	private String slotinfo;		//Դ������Ϣ
	private String tlotinfo;		//��������Ϣ
	private String straycode;		//Դ��������
	private String ttraycode;		//����������
	private double syspnum;			//ϵͳ����
	private double realitypnum;		//ʵ������
	private String jobid;			//��ҵID
	private String createtime;		//����ʱ��
	private String adjusttime;		//����ʱ��
	private String inventoryid;		//�������͵�ID  �����ࣺ�����쳣������ ����쳣������  �̵������ �����Ϣ����
	
	//��������
	 private String statusname;	    //״̬��1��δ���� 2���ѵ�����
	 private String warehousename;	//�ֿ���
	 private String sproductname;	//Դ��Ʒ��
	 private String tproductname;	//�ֲ�Ʒ��
	 private String sproductcode;//ԭ��Ʒ����
	 private String tproductcode;//�ֲ�Ʒ����
	 
	 
	private String spunitName;	    //Դ��λ
	 private String tpunitName;	    //�ֵ�λ
	 private String slotName;	    //Դ��������
	 private String tlotName;	    //����������
   
	 
	 
	 public String getSproductcode() {
			return sproductcode;
		}
		public void setSproductcode(String sproductcode) {
			this.sproductcode = sproductcode;
		}
		public String getTproductcode() {
			return tproductcode;
		}
		public void setTproductcode(String tproductcode) {
			this.tproductcode = tproductcode;
		}
   public String getType() {
		return type;
   }
   public void setType(String type) {
		this.type = type;
	}
	public String getAdjustdetailid() {
		return adjustdetailid;
	}
	public void setAdjustdetailid(String adjustdetailid) {
		this.adjustdetailid = adjustdetailid;
	}
	public String getAdjustid() {
		return adjustid;
	}
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCargo_space_id() {
		return cargo_space_id;
	}
	public void setCargo_space_id(String cargo_space_id) {
		this.cargo_space_id = cargo_space_id;
	}
	public String getWh_area_id() {
		return wh_area_id;
	}
	public void setWh_area_id(String wh_area_id) {
		this.wh_area_id = wh_area_id;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getSpunit() {
		return spunit;
	}
	public void setSpunit(String spunit) {
		this.spunit = spunit;
	}
	public String getTpunit() {
		return tpunit;
	}
	public void setTpunit(String tpunit) {
		this.tpunit = tpunit;
	}
	public String getSproductid() {
		return sproductid;
	}
	public void setSproductid(String sproductid) {
		this.sproductid = sproductid;
	}
	public String getTproductid() {
		return tproductid;
	}
	public void setTproductid(String tproductid) {
		this.tproductid = tproductid;
	}
	public String getSprintdate() {
		return sprintdate;
	}
	public void setSprintdate(String sprintdate) {
		this.sprintdate = sprintdate;
	}
	public String getTprintdate() {
		return tprintdate;
	}
	public void setTprintdate(String tprintdate) {
		this.tprintdate = tprintdate;
	}
	public String getSlotid() {
		return slotid;
	}
	public void setSlotid(String slotid) {
		this.slotid = slotid;
	}
	public String getTlotid() {
		return tlotid;
	}
	public void setTlotid(String tlotid) {
		this.tlotid = tlotid;
	}
	public String getSlotinfo() {
		return slotinfo;
	}
	public void setSlotinfo(String slotinfo) {
		this.slotinfo = slotinfo;
	}
	public String getTlotinfo() {
		return tlotinfo;
	}
	public void setTlotinfo(String tlotinfo) {
		this.tlotinfo = tlotinfo;
	}
	public String getStraycode() {
		return straycode;
	}
	public void setStraycode(String straycode) {
		this.straycode = straycode;
	}
	public String getTtraycode() {
		return ttraycode;
	}
	public void setTtraycode(String ttraycode) {
		this.ttraycode = ttraycode;
	}
	public double getSyspnum() {
		return syspnum;
	}
	public void setSyspnum(double syspnum) {
		this.syspnum = syspnum;
	}
	public double getRealitypnum() {
		return realitypnum;
	}
	public void setRealitypnum(double realitypnum) {
		this.realitypnum = realitypnum;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAdjusttime() {
		return adjusttime;
	}
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
	}
	public String getInventoryid() {
		return inventoryid;
	}
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getSproductname() {
		return sproductname;
	}
	public void setSproductname(String sproductname) {
		this.sproductname = sproductname;
	}
	public String getTproductname() {
		return tproductname;
	}
	public void setTproductname(String tproductname) {
		this.tproductname = tproductname;
	}
	public String getSpunitName() {
		return spunitName;
	}
	public void setSpunitName(String spunitName) {
		this.spunitName = spunitName;
	}
	public String getTpunitName() {
		return tpunitName;
	}
	public void setTpunitName(String tpunitName) {
		this.tpunitName = tpunitName;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	public String getTlotName() {
		return tlotName;
	}
	public void setTlotName(String tlotName) {
		this.tlotName = tlotName;
	}
    
}