package com.wms3.bms.standard.entity.quality;
/**
 * ���м�¼��
 * @author fanll
 *
 */
public class Release {
	
	private String Releaseid;       //����ID
 	private String Productid;      	//��ƷID
 	private String lotinfo;			//�������� (�������)
 	private String Qualityid ;		//�ʼ쵥��
 	private String createtime; 		//����ʱ��   ʱ���ʽ��yyyy-MM-dd hh:mm:ss
 	private String opManId;    		//������
 	
	//��������
    private String Productcode;     //��Ʒ����
    private String ProductName;   	//��Ʒ����
    
	public String getReleaseid() {
		return Releaseid;
	}
	public void setReleaseid(String releaseid) {
		Releaseid = releaseid;
	}
	public String getProductid() {
		return Productid;
	}
	public void setProductid(String productid) {
		Productid = productid;
	}
	public String getLotinfo() {
		return lotinfo;
	}
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}
	public String getQualityid() {
		return Qualityid;
	}
	public void setQualityid(String qualityid) {
		Qualityid = qualityid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getOpManId() {
		return opManId;
	}
	public void setOpManId(String opManId) {
		this.opManId = opManId;
	}
	public String getProductcode() {
		return Productcode;
	}
	public void setProductcode(String productcode) {
		Productcode = productcode;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}

}
