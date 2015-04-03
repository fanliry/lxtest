package com.wms3.bms.standard.entity.inventory.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;


/**
 * 
 * ����: ����ƶ���
 * @since WMS 3.0
 */
public class InventoryMovementHeader  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6407955146763755675L;
	
	private String movementid;		//����ƶ������
	private String warehouseid;	//�ֿ�����id
	private String createManid;	//�Ƶ���id
	private String moveType;	//����
	
	private String createTime;	//����ʱ��
	
	
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
    private String warehouseName;	//�ֿ�����
    private String createMan;	//��������

	// Constructors
    /** default constructor */
    public InventoryMovementHeader() {
    }

	public String getMovementid() {
		return movementid;
	}
	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public void setMovementid(String movementid) {
		this.movementid = movementid;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getCreateManid() {
		return createManid;
	}

	public void setCreateManid(String createManid) {
		this.createManid = createManid;
	}

	

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public InventoryMovementHeader getIMoveHeader(String invoiceid,EntityDAO dao) throws Exception{
		InventoryMovementHeader result = null;
		try
		{
			if(invoiceid != null)
			{
				String strSql = "from InventoryMovementHeader as ar where ar.movementid='"+ invoiceid + "'";
				List ls = dao.searchEntities(strSql);
				if(ls!=null){
					for(int i=0;i<ls.size();i++){
						result = (InventoryMovementHeader)ls.get(0);
					}
				}
			}
		}catch(Exception er)
		{
			throw new Exception("�����ƿⵥID����ƿⵥ��Ϣ����(InventoryMovementHeader.getIMoveHeader):" + er.getMessage());
		}
		return result;
	}
    
    
    
    
}