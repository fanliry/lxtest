package com.wms3.bms.standard.entity.inbound.lxyy;

/**
 * ����:�ɹ���ⶩ��
 * @author yao 2015-3-10
 * @since wmsBMS3.0
 */
public class InboundPoHeader implements java.io.Serializable {

	private static final long serialVersionUID = 6465928388683202095L;
	
	private String poid;// PO Id
	private String potype;// PO ����  1�ɹ����  2�������  3������� 4�������
	private String postatus;//PO ״̬0-���� 1-�ر�
	private String createtime;//PO����ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	private String createdate;//PO�������� ���ڸ�ʽ��yyyy-MM-dd 
	private String createmanid;//PO������
	private String warehouseid;//�ֿ��� 
	private String whAreaId;	/* ����ID  */
	private String cargoAreaId;	/* ����ID  */
	private String customerid;  //�ͻ�ID
	private String arrivestarttime;//Ԥ�ڵ���ʱ��(from) ʱ���ʽ��yyyy-MM-dd hh:mm:ss 
	private String arriveendtime;//Ԥ�ڵ���ʱ��(to) ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	private String shipperid;//������
	private String supplierid;//��Ӧ��
	private String remark;//��ע  
    private String poflag;  // 
	private String reserve1;//Ԥ���ֶ�1   
	private String reserve2;//Ԥ���ֶ�2    
	private String reserve3;//Ԥ���ֶ�3    
	private String reserve4;//Ԥ���ֶ�4 
	private String isup;//�Ƿ��ϴ�
	
	
	
	//��������
	private String supplierName;	// ��Ӧ��ȫ��
	private String customerName;	// �ͻ�ȫ��
	private String potypeName;	    // �ɹ�����

	/** default constructor */
	public InboundPoHeader() {
	}
	public String getPoid() {
		return poid;
	}
	public void setPoid(String poid) {
		this.poid = poid;
	}
	public String getPotype() {
		return potype;
	}
	public void setPotype(String potype) {
		this.potype = potype;
	}
	public String getPostatus() {
		return postatus;
	}
	public void setPostatus(String postatus) {
		this.postatus = postatus;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreatemanid() {
		return createmanid;
	}
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getWhAreaId() {
		return whAreaId;
	}
	public void setWhAreaId(String whAreaId) {
		this.whAreaId = whAreaId;
	}
	public String getCargoAreaId() {
		return cargoAreaId;
	}
	public void setCargoAreaId(String cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getArrivestarttime() {
		return arrivestarttime;
	}
	public void setArrivestarttime(String arrivestarttime) {
		this.arrivestarttime = arrivestarttime;
	}
	public String getArriveendtime() {
		return arriveendtime;
	}
	public void setArriveendtime(String arriveendtime) {
		this.arriveendtime = arriveendtime;
	}
	public String getShipperid() {
		return shipperid;
	}
	public void setShipperid(String shipperid) {
		this.shipperid = shipperid;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPoflag() {
		return poflag;
	}
	public void setPoflag(String poflag) {
		this.poflag = poflag;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	public String getReserve4() {
		return reserve4;
	}
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPotypeName() {
		return potypeName;
	}
	public void setPotypeName(String potypeName) {
		this.potypeName = potypeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsup() {
		return isup;
	}
	public void setIsup(String isup) {
		this.isup = isup;
	}
	

	

	
	
}