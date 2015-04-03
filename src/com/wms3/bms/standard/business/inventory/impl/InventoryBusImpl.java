package com.wms3.bms.standard.business.inventory.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 描述: 库存查询业务类
 * @author fangjie
 * 
 */
public class InventoryBusImpl implements IInventoryBus {
	
	protected IInventoryDao inventoryDAO = null;
    protected EntityDAO m_dao;
    protected ILotBus lotBus;

	public InventoryBusImpl(EntityDAO dao) {
		this.m_dao = dao;
		this.inventoryDAO = new InventoryDaoImpl(dao);
        this.lotBus = new LotBusImpl(dao);
	}
	

    
    /**
     * 功能:库存管理->库存统计的SQL语句
     * @param warehouseid       仓库ID
     * @param whAreaId          库区ID
     * @param customer_id       货主
     * @param package_id        品名
     * @param indate_from, indate_to      入库日期
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性 
     * @param sttLotId          批次ID
     * @param total_items       统计项目
     * @return
     * @throws Exception
	 */
	public String[] getInventoryTotalQuery(String warehouseid, String whAreaId, String customer_id, String package_id, String indate_from, String indate_to, String total_items) throws Exception {
		
		String[] strSqls = new String[2];
        try{
            String strSql = "select sto.whAreaName" + total_items.replaceAll("productid", "productName").replaceAll("ownerId", "ownerName") + ", sto.punit, " +
                    "sum(sto.pnum), count(sto.cargoAreaId), sum(sto.pweight), sum(sto.pnetweight), sum(sto.pvolume), " +
                    "sum(sto.holdnum), sum(sto.holdweight), sum(sto.holdnetweight), sum(sto.holdvolume) ";
            
            String strSqlGrp = " group by sto.whAreaId, sto.punit " + total_items;
            String strSqlSort = " order by sto.whAreaId";
            if(total_items.indexOf("sto.productid") > 0){	//品名
            	strSqlSort += ", sto.productid";
            }
            if(total_items.indexOf("sto.ownerId") > 0){		//货主
            	strSqlSort += ", sto.ownerId";
            }
            
          
            
            //where条件部分
            String strSqlCon = getInventoryQuerySql(warehouseid, whAreaId, "", "", "", "", customer_id, package_id, "", 
                    "", "");
            

            strSqls[0] = "select count(*)" + strSqlCon ;
            strSqls[1] = strSql + strSqlCon + strSqlGrp + strSqlSort;
              
        }catch(Exception er){
            throw new Exception("库存管理->库存统计查询出错：" + er.getMessage());
        }
        return strSqls;
	}
    
    /**
     * 功能:库存管理->库存查询,获得查询条件的SQL
     * @param warehouseid       仓库ID
     * @param whAreaId          库区ID
     * @param cargoAlleyId      巷道ID
     * @param platoon           排
     * @param column            列
     * @param floor             层
     * @param customer_id       货主
     * @param package_id        品名规格
     * @param tray_code         托盘条码
     * @param indate_from, indate_to      入库日期
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性
     * @return
     * @throws Exception
     */
    private String getInventoryQuerySql(String warehouseid, String whAreaId, String cargoAlleyId, String platoon, String column, String floor, 
            String customer_id, String package_id, String tray_code, String indate_from, String indate_to) {
        
        StringBuilder strBud = new StringBuilder();        
        if((platoon!=null && platoon.trim().length()>0) || (column!=null && column.trim().length()>0) || (floor!=null && floor.trim().length()>0)){
            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where sto.cargoSpaceId=space.cargoSpaceId");
        }else{
            strBud.append(" From InventoryStorage as sto where 1=1");
        }
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //巷道ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //排
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //列
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //层
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        //货主
        if(customer_id != null && customer_id.trim().length()>0){
        
            strBud.append(" and sto.ownerId='").append(customer_id).append("'");
        }
        
        //品名ID
        if(package_id != null && package_id.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(package_id).append("'");
        }
        
        //托盘条码
        if(tray_code != null && tray_code.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(tray_code).append("'");
        }
        
        //入库日期
        if(indate_from != null && indate_from.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indate_from).append("'");
        }
        if(indate_to != null && indate_to.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indate_to).append(" 99:99:99'");
        }
        
      
        return strBud.toString();
    }

    /**
     * 功能:库存管理->库存查询,获得查询条件的SQL,获得批次属性的SQL
     * @param flg		第几个批次属性
     * @param lotatt	批次属性的值
     * @param lotDetail	批次属性
     * @return
     * @throws Exception
     */
	private String getSqlLotatt(String flg,	String lotatt, BaseLotDetail lotDetail) {
		
        StringBuilder strBud = new StringBuilder();	
		if(lotDetail != null){
			
			//页面查询时候的模式 1-精确查询,2-模糊查询(文本格式),3-范围查询(日期格式)
			String strLotsearch = lotDetail.getM_strLotsearch();	
		    
		    if(strLotsearch != null && strLotsearch.equals("1")){       //1-精确查询
                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
		    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-模糊查询
                strBud.append(" and sto.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
		    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-范围查询(日期格式)    	
	    		String[] lotatts =  lotatt.split("\\|");
		        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
                    strBud.append(" and sto.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
		        }
		        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
                    strBud.append(" and sto.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
		        }
		        
		    }else{		//没有选择查询方式的时候，按照精确查询来处理
                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");
		    }
		}
		return strBud.toString();
	}
    /**
     *  出库分配->统计库存的数量
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inventory.IInventoryBus#getGroupInventoryNum(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Object[] getGroupInventoryNum(String warehouseid, String whAreaId, String ownerid, String productid, String packid) throws Exception {
        Object[] obj = null;
        StringBuilder strBud = new StringBuilder();
        strBud.append("select sum(sto.pnum-sto.assignnum-sto.holdnum), ")
        .append("sum(sto.pweight-sto.assignweight-sto.holdweight), ")
        .append("sum(sto.pnetweight-sto.assignnetweight-sto.holdnetweight), ")
        .append("sum(sto.pvolume-sto.assignvolume-sto.holdvolume) ")
        .append(" from InventoryStorage sto where 1=1 ");
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){ 
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        //货主
        if(ownerid != null && ownerid.trim().length()>0){
            strBud.append(" and sto.ownerId='").append(ownerid).append("'");
        } 
        //品名ID
        if(productid != null && productid.trim().length() > 0){
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //包装
        if(packid != null && packid.trim().length() > 0){
            strBud.append(" and sto.packid='").append(packid).append("'");
        }
       
        List ls = inventoryDAO.querySQL(strBud.toString());
        if(ls != null && ls.size() > 0){
            obj = (Object[])ls.get(0);
        }
        return obj;
    }
    /**
     * 功能：库存移动->库存查询的sql语句
     */
	public String getInventoryHQL(String warehouseid, String whAreaId,
			String cargoAlleyId, String platoon, String column, String floor,
			String packageId, String trayCode,
			String indateFrom, String indateTo,String productcode)
			throws Exception {
		StringBuilder strBud = new StringBuilder();        
//        if((platoon!=null && platoon.trim().length()>0) || (column!=null && column.trim().length()>0) || (floor!=null && floor.trim().length()>0)){
            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where sto.cargoSpaceId=space.cargoSpaceId and space.csstatus='2' and sto.status='0'");
//        }else{
//            strBud.append(" From InventoryStorage as sto where 1=1");
//        }
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //巷道ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //排
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //列
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //层
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        
        //品名ID
        if(packageId != null && packageId.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(packageId).append("'");
        }
        
        //托盘条码
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
        //产品编码
        if(productcode != null && productcode.trim().length()>0){
        
            strBud.append(" and sto.productcode='").append(productcode).append("'");
        }
        //入库日期
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indateTo).append(" 99:99:99'");
        }

       
        
        return strBud.toString();
	}
/*	@Override
	public String getInventoryStatusHQL(String warehouseid,String whareaid,String lotnumber,
			String requestid,String productid,String productstatus)
			throws Exception {
		
		StringBuilder strBud = new StringBuilder();        
        strBud.append(" From InventoryStorage as inven where 1=1");       
        
        if (warehouseid!=null && warehouseid.trim().length()>0) {
			strBud.append(" and inven.warehouseid='").append(warehouseid).append("'");
		}
		if (whareaid!=null && whareaid.trim().length()>0) {
			strBud.append(" and inven.whAreaId='").append(whareaid).append("'");
		}
		if (lotnumber!=null &&lotnumber.trim().length()>0) {
			strBud.append(" and inven.lotinfo='").append(lotnumber).append("'");
		}
		if (requestid!=null &&requestid.trim().length()>0) {
			strBud.append(" and inven.requestid='").append(requestid).append("'");
		}
		if (productid!=null &&productid.trim().length()>0) {
			strBud.append(" and inven.productid='").append(productid).append("'");
		}
		if (productstatus != null && productstatus.trim().length()>0) {
			strBud.append(" and inven.productstatus='").append(productstatus).append("'");
		}
        return strBud.toString();
	}*/
	@Override
	public String[] getInventoryQuerynew(String warehouseid, String whAreaId,String cargoAlleyId, String platoon, String column, String floor,
			String lotnumber, String productid, String indateFrom,String indateTo, String trayCode, String status, String requestid,
			String instockid, String productstatus, String productcode,String kcstatus) throws Exception {
       
		String[] strSqls = new String[2];
		StringBuilder strBud = new StringBuilder();    
		try {
			
        if((platoon!=null && platoon.trim().length()>0) || (column!=null && column.trim().length()>0) || (floor!=null && floor.trim().length()>0)){
            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where sto.cargoSpaceId=space.cargoSpaceId");
        }else{
            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where 1=1 and sto.cargoSpaceId=space.cargoSpaceId ");
        }
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //巷道ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //排
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //列
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //层
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        //批号
        if(lotnumber != null && lotnumber.trim().length()>0){
        
            strBud.append(" and sto.lotnumber='").append(lotnumber).append("'");
        }
        
        //品名ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //托盘条码
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
        
        //入库日期
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indateTo).append(" 99:99:99'");
        }
        //货位状态
        if(status != null && status.trim().length()>0){    
            strBud.append(" and space.csstatus='").append(status).append("'");
        }
        //库存状态
        if(kcstatus != null && kcstatus.trim().length()>0){    
            strBud.append(" and sto.status='").append(kcstatus).append("'");
        }
        //申请单
        if(requestid != null && requestid.trim().length()>0){
        
            strBud.append(" and sto.requestid='").append(requestid).append("'");
        }
        //入库单
        if(instockid != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.instockid='").append(instockid).append("'");
        }
        //产品状态
        if(productstatus != null && productstatus.trim().length()>0){
        
            strBud.append(" and sto.productstatus='").append(productstatus).append("'");
        }
        //产品编号
        if(productcode != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.productcode='").append(productcode).append("'");
        }
        strSqls[0] = strBud.toString()+" order by sto.whAreaId, sto.cargoSpaceId, sto.productid, sto.ownerId";
        strSqls[1] = "select count(*)"+strBud.toString();
		} catch (Exception er) {
			 throw new Exception("库存管理->库存查询语句拼接出错：" + er.getMessage());
		}
		return strSqls;
	}
	/**
	 *   RF复核查询库存语句
	 *
	 */
	public InventoryStorage getInventoryQueryZC(String warehouseid, String whAreaId,String productid, String trayCode) throws Exception {
       
		String sql  ="";
		StringBuilder strBud = new StringBuilder();    
		try {
			
        
          strBud.append("  From InventoryStorage as sto where 1=1 ");
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        //品名ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //托盘条码
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
		} catch (Exception er) {
			 throw new Exception("RF复核查询库存语句出错：" + er.getMessage());
		}
		sql = strBud.toString();
		
		List lsList = m_dao.searchEntities(sql);
		InventoryStorage store = null;
		if(lsList!=null && lsList.size()>0){
			 store = (InventoryStorage)lsList.get(0);
		}
		return store;
	}
	/**
	 * 出库备货->按批号拣货出库->暂存列表
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param productid
	 * @param lotinfo
	 * @param printdate
	 * @param trayCode
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getZCInventoryByCKInfo(String warehouseid, String whAreaId,String Virtualwhid,String productid,String lotinfo,String printdate, String trayCode) throws Exception {
       
		String sql  ="";
		StringBuilder strBud = new StringBuilder();    
		try {
			
        strBud.append("  From InventoryStorage as sto where 1=1 ");
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }else{
        	strBud.append(" and sto.whAreaId in (select whAreaId from BaseWharea  where whAreaType='9')");
        }
        //虚拟库区ID
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        //品名ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //进场编号
        if(lotinfo != null && lotinfo.trim().length() > 0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        //生产日期
        if(printdate != null && printdate.trim().length() > 0){
        
            strBud.append(" and sto.productdate='").append(printdate).append("'");
        }
        //托盘条码
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
		} catch (Exception er) {
			 throw new Exception("出库备货->按批号拣货出库->暂存列表查询出错：" + er.getMessage());
		}
		sql = strBud.toString();
		List lsList = m_dao.searchEntities(sql);
		return lsList;
	}
	@Override
	public String[] getInventoryQuerynew(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String producttype, String cargoAlleyId, String platoon, String column, String floor,
			String lotnumber, String productid, String indateFrom,String indateTo, String trayCode, String status, String requestid,
			String instockid, String productstatus, String productcode) throws Exception {
       
		String[] strSqls = new String[2];
		StringBuilder strBud = new StringBuilder();    
		try {
			
        if((platoon!=null && platoon.trim().length()>0) || (column!=null && column.trim().length()>0) || (floor!=null && floor.trim().length()>0)){
            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where sto.cargoSpaceId=space.cargoSpaceId");
        }else{
            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where 1=1 and sto.cargoSpaceId=space.cargoSpaceId ");
        }
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //逻辑库区id
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
        
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        
        //巷道ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //排
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //列
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //层
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        //批号
        if(lotnumber != null && lotnumber.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotnumber).append("'");
        }
        
        //进厂编号
        if(lotinfo != null && lotinfo.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        
        //品名ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //托盘条码
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
        
        //入库日期
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indateTo).append(" 99:99:99'");
        }
        //货位状态
        if(status != null && status.trim().length()>0){    
            strBud.append(" and space.csstatus='").append(status).append("'");
        }
        //申请单
        if(requestid != null && requestid.trim().length()>0){
        
            strBud.append(" and sto.requestid='").append(requestid).append("'");
        }
        //入库单
        if(instockid != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.instockid='").append(instockid).append("'");
        }
        //产品状态
        if(productstatus != null && productstatus.trim().length()>0){
        
            strBud.append(" and sto.productstatus='").append(productstatus).append("'");
        }
        //产品编号
        if(productcode != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.productcode='").append(productcode).append("'");
        }
        strSqls[0] = strBud.toString()+" order by sto.whAreaId, sto.cargoSpaceId, sto.productid, sto.ownerId";
        strSqls[1] = "select count(*)"+strBud.toString();
		} catch (Exception er) {
			 throw new Exception("库存管理->库存查询语句拼接出错：" + er.getMessage());
		}
		return strSqls;
	}
	
	/**
	 * 功能：(库存管理)物品查询
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param lotinfo
	 * @param productid
	 * @param indateFrom
	 * @param indateTo
	 * @param productstatus
	 * @return
	 * @throws Exception
	 */
	public String getInventoryWpQuerynew(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String productid, String indateFrom,String indateTo,String productstatus) throws Exception {
       
		String strsql = "";
		StringBuilder strBud = new StringBuilder();    
		StringBuilder strBudFrom = new StringBuilder();    
		try {
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //逻辑库区id
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
        
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        
        //进厂编号
        if(lotinfo != null && lotinfo.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        
        //品名ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //产品生产日期
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.productdate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.productdate<='").append(indateTo).append(" 99:99:99'");
        }
        
        //产品状态
        if(productstatus != null && productstatus.trim().length()>0){
        
            strBud.append(" and sto.productstatus='").append(productstatus).append("'");
        }

        strBudFrom.append("SELECT SUM(ISNULL(sto.pnum, 0)),sto.warehouseid,sto.whAreaId,sto.whAreaName,sto.Virtualwhid,sto.Virtualwhname,sto.lotinfo,sto.productid,sto.productName,sto.productcode,sto.productdate,sto.productstatus,sto.productStatusName ")
        		  .append(" From InventoryStorage as sto where 1=1 ");
        
        strBudFrom.append(strBud);
        strBudFrom.append("GROUP BY sto.warehouseid,sto.whAreaId,sto.Virtualwhid,sto.lotinfo,sto.productid,sto.productcode,sto.productdate,sto.productstatus ");
        
        //strsql = strBudFrom.toString();
        strsql = strBudFrom.toString()+" order by sto.whAreaId, sto.productid";
        
        //strSqls[0] = strBud.toString()+" order by sto.whAreaId, sto.cargoSpaceId, sto.productid";
        //strSqls[1] = "select count(*)"+strBud.toString();
		} catch (Exception er) {
			 throw new Exception("库存管理->物品查询语句拼接出错：" + er.getMessage());
		}
		return strsql;
	}

	/**
	 * 功能：(库存管理)物品查询
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param lotinfo
	 * @param productid
	 * @param indateFrom
	 * @param indateTo
	 * @param productstatus
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryWpQuery(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String productid, String indateFrom,String indateTo,String productstatus) throws Exception {
       
		String strsql = "";
		String[] strSqls = new String[2];
		StringBuilder strBud = new StringBuilder();    
		StringBuilder strBudFrom = new StringBuilder();    
		try {
        
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //逻辑库区id
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
        
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        
        //进厂编号
        if(lotinfo != null && lotinfo.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        
        //品名ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //产品生产日期
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.productdate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.productdate<='").append(indateTo).append(" 99:99:99'");
        }
        
        //产品状态
        if(productstatus != null && productstatus.trim().length()>0){
        
            strBud.append(" and sto.productstatus='").append(productstatus).append("'");
        }

        strBudFrom.append("SELECT SUM(ISNULL(sto.pnum, 0)),sto.warehouseid,sto.whAreaId,sto.whAreaName,sto.Virtualwhid,sto.Virtualwhname,sto.lotinfo,sto.productid,sto.productName,sto.productcode,sto.productdate,sto.productstatus,sto.productStatusName,sto.status,sto.statusName ")
        		  .append(" From InventoryStorage as sto where 1=1 ");
        
        strBudFrom.append(strBud);
        strBudFrom.append("GROUP BY sto.warehouseid,sto.whAreaId,sto.Virtualwhid,sto.lotinfo,sto.productid,sto.productcode,sto.productdate,sto.productstatus,sto.status  ");
        
        //strsql = strBudFrom.toString();
        strsql = strBudFrom.toString()+" order by sto.whAreaId, sto.productid";
        
        //strSqls[0] = strBud.toString()+" order by sto.whAreaId, sto.cargoSpaceId, sto.productid";
        //strSqls[1] = "select count(*)"+strBud.toString();
		} catch (Exception er) {
			 throw new Exception("库存管理->物品查询语句拼接出错：" + er.getMessage());
		}
		
		List lsList = m_dao.searchEntities(strsql);
		List<InventoryStorage> lsListd = lsList;
		return lsListd;
	}
	
}