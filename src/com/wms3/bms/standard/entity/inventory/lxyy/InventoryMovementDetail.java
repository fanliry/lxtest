package com.wms3.bms.standard.entity.inventory.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;



/**
 * 
 * ����: ����ƶ�����ϸ
 * @since WMS 3.0
 */
public class InventoryMovementDetail  implements java.io.Serializable {
    
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -39822890723645727L;
	
	private String movemendetailid;		//����ƶ�����ϸID
	private String movementid; 	//�ƿⵥid
	private String productid;	//��Ʒ���
	private String productName; 	//Ʒ��
	private String productDate;		//��������
	
	private String lotid;	//����id
	private String lotNum;	//����
	private String trayCode;	//���̺�
	private String meter;	//��λ
	private String fromWarehouseId;//�Ƴ��ֿ�
	private String fromAreId;	//�Ƴ�����ID
	private String fromAreName;	//�Ƴ�����
	private String fromCargospace;	//�Ƴ���λ
	private String fromCargospaceId;	//�Ƴ���λ
	private String toWarehouseId;//����ֿ�
	private String toAreId;	//�������ID
	private String toAreName;	//�������
	private String toCargospace; 	//�����λ
	
	private String toCargospaceId; 	//�����λID
	private String moveReason;	//�ƿ�ԭ��
	private String remark;	//��ע

	private String Reserved1;      	//Ԥ���ֶ�1
    private String Reserved2;      	//Ԥ���ֶ�2
    private String Reserved3;      	//Ԥ���ֶ�3
    private String Reserved4;      	//Ԥ���ֶ�4
    private String Reserved5;      	//Ԥ���ֶ�5
    private String Reserved6;      	//Ԥ���ֶ�6
    private String Reserved7;      	//Ԥ���ֶ�7
    private String Reserved8;      	//Ԥ���ֶ�8
      
    //��������
	//--------------------------------------------------
    
    public String getFromAreName() {
		return fromAreName;
	}
	public void setFromAreName(String fromAreName) {
		this.fromAreName = fromAreName;
	}
	
	public String getToAreName() {
		return toAreName;
	}
	public void setToAreName(String toAreName) {
		this.toAreName = toAreName;
	}
	
    
    public String getFromCargospaceId() {
		return fromCargospaceId;
	}
	public void setFromCargospaceId(String fromCargospaceId) {
		this.fromCargospaceId = fromCargospaceId;
	}
	public String getToCargospaceId() {
		return toCargospaceId;
	}
	public void setToCargospaceId(String toCargospaceId) {
		this.toCargospaceId = toCargospaceId;
	}
	public String getMovemendetailid() {
		return movemendetailid;
	}
	public void setMovemendetailid(String movemendetailid) {
		this.movemendetailid = movemendetailid;
	}
	public String getMovementid() {
		return movementid;
	}
	public void setMovementid(String movementid) {
		this.movementid = movementid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getLotid() {
		return lotid;
	}
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}
	public String getTrayCode() {
		return trayCode;
	}
	public void setTrayCode(String trayCode) {
		this.trayCode = trayCode;
	}
	public String getMeter() {
		return meter;
	}
	public void setMeter(String meter) {
		this.meter = meter;
	}
	public String getFromCargospace() {
		return fromCargospace;
	}
	public void setFromCargospace(String fromCargospace) {
		this.fromCargospace = fromCargospace;
	}
	public String getToCargospace() {
		return toCargospace;
	}
	public void setToCargospace(String toCargospace) {
		this.toCargospace = toCargospace;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReserved1() {
		return Reserved1;
	}
	public void setReserved1(String reserved1) {
		Reserved1 = reserved1;
	}
	public String getReserved2() {
		return Reserved2;
	}
	public void setReserved2(String reserved2) {
		Reserved2 = reserved2;
	}
	public String getReserved3() {
		return Reserved3;
	}
	public void setReserved3(String reserved3) {
		Reserved3 = reserved3;
	}
	public String getReserved4() {
		return Reserved4;
	}
	public void setReserved4(String reserved4) {
		Reserved4 = reserved4;
	}
	public String getReserved5() {
		return Reserved5;
	}
	public void setReserved5(String reserved5) {
		Reserved5 = reserved5;
	}
	public String getReserved6() {
		return Reserved6;
	}
	public void setReserved6(String reserved6) {
		Reserved6 = reserved6;
	}
	public String getReserved7() {
		return Reserved7;
	}
	public void setReserved7(String reserved7) {
		Reserved7 = reserved7;
	}
	public String getReserved8() {
		return Reserved8;
	}
	public void setReserved8(String reserved8) {
		Reserved8 = reserved8;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLotNum() {
		return lotNum;
	}
	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getFromWarehouseId() {
		return fromWarehouseId;
	}
	public void setFromWarehouseId(String fromWarehouseId) {
		this.fromWarehouseId = fromWarehouseId;
	}
	public String getFromAreId() {
		return fromAreId;
	}
	public void setFromAreId(String fromAreId) {
		this.fromAreId = fromAreId;
	}
	public String getToWarehouseId() {
		return toWarehouseId;
	}
	public void setToWarehouseId(String toWarehouseId) {
		this.toWarehouseId = toWarehouseId;
	}
	public String getToAreId() {
		return toAreId;
	}
	public void setToAreId(String toAreId) {
		this.toAreId = toAreId;
	}
	public String getMoveReason() {
		return moveReason;
	}
	public void setMoveReason(String moveReason) {
		this.moveReason = moveReason;
	}
    
	
	public List getMoveDetailListToId(String strId,EntityDAO dao) throws Exception
	{
		List ls = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryMovementDetail as ad where ad.movementid='" + strId + "'";
				ls = dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("�����ƿⵥID����ƿⵥ��ϸ����(InventoryMovementDetail.getMoveDetailListToId):" + er.getMessage());
		}
		return ls;
	}
   
}