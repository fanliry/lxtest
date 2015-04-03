package com.wms3.bms.standard.dao.quality.impl;

import java.util.List;

import com.wms3.bms.standard.dao.quality.IQualityQueryDao;


public class QualityQueryDaoImpl implements IQualityQueryDao {

	@Override
	public String searchInventoryForLotnumber(String warehouseid,
			String whareaid, String lotnumber, String requestid,
			String productid, String productstatus) throws Exception {
		StringBuilder strBud = new StringBuilder();
		try {
			strBud.append("select inven.warehouseid,inven.whAreaName,inven.lotnumber,pro.productname,pro.spec,inven.productstatus,sum(inven.pnum)");
        //分组
		StringBuilder strBudSqlGrp = new StringBuilder();
		strBudSqlGrp.append(" group by inven.lotnumber,pro.productname,pro.spec,inven.productstatus");
		
		strBud.append("From InventoryStorage as inven,BaseProduct as pro where 1=1") ;
		if (warehouseid!=null && warehouseid.trim().length()>0) {
			strBud.append(" and inven.warehouseid='").append(warehouseid).append("'");
		}
		if (whareaid!=null && whareaid.trim().length()>0) {
			strBud.append(" and inven.whAreaId='").append(whareaid).append("'");
		}
		if (lotnumber!=null &&lotnumber.trim().length()>0) {
			strBud.append(" and inven.lotnumber='").append(lotnumber).append("'");
		}
		if (requestid!=null &&requestid.trim().length()>0) {
			strBud.append(" and inven.requestid='").append(requestid).append("'");
		}
		if (productid!=null &&productid.trim().length()>0) {
			strBud.append(" and pro.productid='").append(productid).append("'");
		}
		if (productstatus != null && productstatus.trim().length()>0) {
			strBud.append(" and inven.productstatus='").append(productstatus).append("'");
		}
		
		strBud.append(strBudSqlGrp);
		strBud.append("order by inven.lotnumber");

		
		} catch (Exception er) {
			throw new Exception("质检管理->放行查询语句拼接出错：" + er.getMessage());
		}
		return strBud.toString();
	}

	@Override
	public String searchInventroyForInstock(String warehouseid,
			String lotnumber, String requestid, String productid, String productstatus)
			throws Exception {
		StringBuilder strBud = new StringBuilder();
		try {
			strBud.append("select inven.warehouseid,inven.whAreaName,inven.instockid,pro.productname,pro.spec,inven.productstatus,inven.pnum");
/*        //分组
		StringBuilder strBudSqlGrp = new StringBuilder();
		strBudSqlGrp.append(" group by inven.lotnumber,pro.productname,pro.spec,inven.productstatus");
		*/
		strBud.append("From InventoryStorage as inven,BaseProduct as pro where 1=1") ;
		if (warehouseid!=null && warehouseid.trim().length()>0) {
			strBud.append(" and inven.warehouseid='").append(warehouseid).append("'");
		}
		if (lotnumber!=null &&lotnumber.trim().length()>0) {
			strBud.append(" and inven.lotnumber='").append(lotnumber).append("'");
		}
		if (requestid!=null &&requestid.trim().length()>0) {
			strBud.append(" and inven.requestid='").append(requestid).append("'");
		}
		if (productid!=null &&productid.trim().length()>0) {
			strBud.append(" and pro.productid='").append(productid).append("'");
		}
		if (productstatus != null && productstatus.trim().length()>0) {
			strBud.append(" and inven.productstatus='").append(productstatus).append("'");
		}
		//strBud.append(strBudSqlGrp);
		strBud.append("order by inven.instockid");
	
		} catch (Exception er) {
			throw new Exception("质检管理->放行查询语句拼接出错：" + er.getMessage());
		}
		return strBud.toString();
	}

}
