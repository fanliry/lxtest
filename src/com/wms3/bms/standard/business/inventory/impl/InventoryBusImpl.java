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
 * ����: ����ѯҵ����
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
     * ����:������->���ͳ�Ƶ�SQL���
     * @param warehouseid       �ֿ�ID
     * @param whAreaId          ����ID
     * @param customer_id       ����
     * @param package_id        Ʒ��
     * @param indate_from, indate_to      �������
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      ��������
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   �������� 
     * @param sttLotId          ����ID
     * @param total_items       ͳ����Ŀ
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
            if(total_items.indexOf("sto.productid") > 0){	//Ʒ��
            	strSqlSort += ", sto.productid";
            }
            if(total_items.indexOf("sto.ownerId") > 0){		//����
            	strSqlSort += ", sto.ownerId";
            }
            
          
            
            //where��������
            String strSqlCon = getInventoryQuerySql(warehouseid, whAreaId, "", "", "", "", customer_id, package_id, "", 
                    "", "");
            

            strSqls[0] = "select count(*)" + strSqlCon ;
            strSqls[1] = strSql + strSqlCon + strSqlGrp + strSqlSort;
              
        }catch(Exception er){
            throw new Exception("������->���ͳ�Ʋ�ѯ����" + er.getMessage());
        }
        return strSqls;
	}
    
    /**
     * ����:������->����ѯ,��ò�ѯ������SQL
     * @param warehouseid       �ֿ�ID
     * @param whAreaId          ����ID
     * @param cargoAlleyId      ���ID
     * @param platoon           ��
     * @param column            ��
     * @param floor             ��
     * @param customer_id       ����
     * @param package_id        Ʒ�����
     * @param tray_code         ��������
     * @param indate_from, indate_to      �������
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      ��������
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   ��������
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //���ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //��
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //��
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //��
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        //����
        if(customer_id != null && customer_id.trim().length()>0){
        
            strBud.append(" and sto.ownerId='").append(customer_id).append("'");
        }
        
        //Ʒ��ID
        if(package_id != null && package_id.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(package_id).append("'");
        }
        
        //��������
        if(tray_code != null && tray_code.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(tray_code).append("'");
        }
        
        //�������
        if(indate_from != null && indate_from.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indate_from).append("'");
        }
        if(indate_to != null && indate_to.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indate_to).append(" 99:99:99'");
        }
        
      
        return strBud.toString();
    }

    /**
     * ����:������->����ѯ,��ò�ѯ������SQL,����������Ե�SQL
     * @param flg		�ڼ�����������
     * @param lotatt	�������Ե�ֵ
     * @param lotDetail	��������
     * @return
     * @throws Exception
     */
	private String getSqlLotatt(String flg,	String lotatt, BaseLotDetail lotDetail) {
		
        StringBuilder strBud = new StringBuilder();	
		if(lotDetail != null){
			
			//ҳ���ѯʱ���ģʽ 1-��ȷ��ѯ,2-ģ����ѯ(�ı���ʽ),3-��Χ��ѯ(���ڸ�ʽ)
			String strLotsearch = lotDetail.getM_strLotsearch();	
		    
		    if(strLotsearch != null && strLotsearch.equals("1")){       //1-��ȷ��ѯ
                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
		    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-ģ����ѯ
                strBud.append(" and sto.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
		    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-��Χ��ѯ(���ڸ�ʽ)    	
	    		String[] lotatts =  lotatt.split("\\|");
		        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
                    strBud.append(" and sto.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
		        }
		        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
                    strBud.append(" and sto.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
		        }
		        
		    }else{		//û��ѡ���ѯ��ʽ��ʱ�򣬰��վ�ȷ��ѯ������
                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");
		    }
		}
		return strBud.toString();
	}
    /**
     *  �������->ͳ�ƿ�������
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
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){ 
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        //����
        if(ownerid != null && ownerid.trim().length()>0){
            strBud.append(" and sto.ownerId='").append(ownerid).append("'");
        } 
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //��װ
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
     * ���ܣ�����ƶ�->����ѯ��sql���
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //���ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //��
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //��
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //��
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        
        //Ʒ��ID
        if(packageId != null && packageId.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(packageId).append("'");
        }
        
        //��������
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
        //��Ʒ����
        if(productcode != null && productcode.trim().length()>0){
        
            strBud.append(" and sto.productcode='").append(productcode).append("'");
        }
        //�������
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //���ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //��
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //��
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //��
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        //����
        if(lotnumber != null && lotnumber.trim().length()>0){
        
            strBud.append(" and sto.lotnumber='").append(lotnumber).append("'");
        }
        
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //��������
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
        
        //�������
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indateTo).append(" 99:99:99'");
        }
        //��λ״̬
        if(status != null && status.trim().length()>0){    
            strBud.append(" and space.csstatus='").append(status).append("'");
        }
        //���״̬
        if(kcstatus != null && kcstatus.trim().length()>0){    
            strBud.append(" and sto.status='").append(kcstatus).append("'");
        }
        //���뵥
        if(requestid != null && requestid.trim().length()>0){
        
            strBud.append(" and sto.requestid='").append(requestid).append("'");
        }
        //��ⵥ
        if(instockid != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.instockid='").append(instockid).append("'");
        }
        //��Ʒ״̬
        if(productstatus != null && productstatus.trim().length()>0){
        
            strBud.append(" and sto.productstatus='").append(productstatus).append("'");
        }
        //��Ʒ���
        if(productcode != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.productcode='").append(productcode).append("'");
        }
        strSqls[0] = strBud.toString()+" order by sto.whAreaId, sto.cargoSpaceId, sto.productid, sto.ownerId";
        strSqls[1] = "select count(*)"+strBud.toString();
		} catch (Exception er) {
			 throw new Exception("������->����ѯ���ƴ�ӳ���" + er.getMessage());
		}
		return strSqls;
	}
	/**
	 *   RF���˲�ѯ������
	 *
	 */
	public InventoryStorage getInventoryQueryZC(String warehouseid, String whAreaId,String productid, String trayCode) throws Exception {
       
		String sql  ="";
		StringBuilder strBud = new StringBuilder();    
		try {
			
        
          strBud.append("  From InventoryStorage as sto where 1=1 ");
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //��������
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
		} catch (Exception er) {
			 throw new Exception("RF���˲�ѯ���������" + er.getMessage());
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
	 * ���ⱸ��->�����ż������->�ݴ��б�
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }else{
        	strBud.append(" and sto.whAreaId in (select whAreaId from BaseWharea  where whAreaType='9')");
        }
        //�������ID
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //�������
        if(lotinfo != null && lotinfo.trim().length() > 0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        //��������
        if(printdate != null && printdate.trim().length() > 0){
        
            strBud.append(" and sto.productdate='").append(printdate).append("'");
        }
        //��������
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
		} catch (Exception er) {
			 throw new Exception("���ⱸ��->�����ż������->�ݴ��б��ѯ����" + er.getMessage());
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //�߼�����id
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
        
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        
        //���ID
        if(cargoAlleyId != null && cargoAlleyId.trim().length()>0){
        
            strBud.append(" and sto.cargoAlleyId='").append(cargoAlleyId).append("'");
        }

        //��
        if(platoon != null && platoon.trim().length() >0){
        
            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
        }
        
        //��
        if(column != null && column.trim().length() >0){
        
            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
        }
        
        //��
        if(floor != null && floor.trim().length() > 0){
        
            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
        }
        
        //����
        if(lotnumber != null && lotnumber.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotnumber).append("'");
        }
        
        //�������
        if(lotinfo != null && lotinfo.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //��������
        if(trayCode != null && trayCode.trim().length()>0){
        
            strBud.append(" and sto.traycode='").append(trayCode).append("'");
        }
        
        //�������
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.indate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.indate<='").append(indateTo).append(" 99:99:99'");
        }
        //��λ״̬
        if(status != null && status.trim().length()>0){    
            strBud.append(" and space.csstatus='").append(status).append("'");
        }
        //���뵥
        if(requestid != null && requestid.trim().length()>0){
        
            strBud.append(" and sto.requestid='").append(requestid).append("'");
        }
        //��ⵥ
        if(instockid != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.instockid='").append(instockid).append("'");
        }
        //��Ʒ״̬
        if(productstatus != null && productstatus.trim().length()>0){
        
            strBud.append(" and sto.productstatus='").append(productstatus).append("'");
        }
        //��Ʒ���
        if(productcode != null && instockid.trim().length()>0){
        
            strBud.append(" and sto.productcode='").append(productcode).append("'");
        }
        strSqls[0] = strBud.toString()+" order by sto.whAreaId, sto.cargoSpaceId, sto.productid, sto.ownerId";
        strSqls[1] = "select count(*)"+strBud.toString();
		} catch (Exception er) {
			 throw new Exception("������->����ѯ���ƴ�ӳ���" + er.getMessage());
		}
		return strSqls;
	}
	
	/**
	 * ���ܣ�(������)��Ʒ��ѯ
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //�߼�����id
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
        
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        
        //�������
        if(lotinfo != null && lotinfo.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //��Ʒ��������
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.productdate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.productdate<='").append(indateTo).append(" 99:99:99'");
        }
        
        //��Ʒ״̬
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
			 throw new Exception("������->��Ʒ��ѯ���ƴ�ӳ���" + er.getMessage());
		}
		return strsql;
	}

	/**
	 * ���ܣ�(������)��Ʒ��ѯ
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
        
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
        
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){
        
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        
        //�߼�����id
        if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
        
            strBud.append(" and sto.Virtualwhid='").append(Virtualwhid).append("'");
        }
        
        //�������
        if(lotinfo != null && lotinfo.trim().length()>0){
        
            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
        }
        
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
        
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        
        //��Ʒ��������
        if(indateFrom != null && indateFrom.trim().length()>0){
        
            strBud.append(" and sto.productdate>='").append(indateFrom).append("'");
        }
        if(indateTo != null && indateTo.trim().length()>0){
            
            strBud.append(" and sto.productdate<='").append(indateTo).append(" 99:99:99'");
        }
        
        //��Ʒ״̬
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
			 throw new Exception("������->��Ʒ��ѯ���ƴ�ӳ���" + er.getMessage());
		}
		
		List lsList = m_dao.searchEntities(strsql);
		List<InventoryStorage> lsListd = lsList;
		return lsListd;
	}
	
}