package com.wms3.bms.standard.entity.inventory.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;


/**
 * 
 * ÃèÊö: ¿â´æÒÆ¶¯µ¥
 * @since WMS 3.0
 */
public class InventoryMovementHeader  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6407955146763755675L;
	
	private String movementid;		//¿â´æÒÆ¶¯µ¥±àºÅ
	private String warehouseid;	//²Ö¿âÃû³Æid
	private String createManid;	//ÖÆµ¥ÈËid
	private String moveType;	//ÀàĞÍ
	
	private String createTime;	//´´µ¥Ê±¼ä
	
	
	private String Reserved1;      	//Ô¤Áô×Ö¶Î1
    private String Reserved2;      	//Ô¤Áô×Ö¶Î2
    private String Reserved3;      	//Ô¤Áô×Ö¶Î3
    private String Reserved4;      	//Ô¤Áô×Ö¶Î4
    private String Reserved5;      	//Ô¤Áô×Ö¶Î5
    private String Reserved6;      	//Ô¤Áô×Ö¶Î6
    private String Reserved7;      	//Ô¤Áô×Ö¶Î7
    private String Reserved8;      	//Ô¤Áô×Ö¶Î8
	
	
	 //ÅÉÉúÊôĞÔ
	//--------------------------------------------------
    private String warehouseName;	//²Ö¿âÃû³Æ
    private String createMan;	//´´µ¥ÈËÃû

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
			throw new Exception("¸ù¾İÒÆ¿âµ¥ID»ñµÃÒÆ¿âµ¥ĞÅÏ¢³ö´í(InventoryMovementHeader.getIMoveHeader):" + er.getMessage());
		}
		return result;
	}
    
    
    
    
}