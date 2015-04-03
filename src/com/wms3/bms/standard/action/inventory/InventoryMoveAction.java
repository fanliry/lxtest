package com.wms3.bms.standard.action.inventory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.InventoryMoveBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryMoveBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����������ƶ�
 * 
 * @author liuxh
 * 
 */
public class InventoryMoveAction extends AbstractAction {

	protected IInventoryBus inventoryBus;

	protected int maxLine = 20; // ��ҳ��ʾ��������

	@Override
	/**
	 * �ƿ�
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String strWarehouseId = CommonTools.getStrToGbk(request
				.getParameter("warehouseid"));// �ֿ�ID
		String strAreaId = CommonTools.getStrToGbk(request
				.getParameter("whAreaId"));// ����
		String strCargoAlleyId = CommonTools.getStrToGbk(request
				.getParameter("cargoAlleyId"));// ���
		String strPlatoon = CommonTools.getStrToGbk(request
				.getParameter("platoon"));// ��
		String strColumn = CommonTools.getStrToGbk(request
				.getParameter("column"));// ��
		String strFloor = CommonTools
				.getStrToGbk(request.getParameter("floor"));// ��

		String strPackageId = CommonTools.getStrToGbk(request
				.getParameter("package_id"));// �ͻ�ID
		String strTrayCode = CommonTools.getStrToGbk(request
				.getParameter("tray_code"));// ��������
		String productcode = CommonTools.getStrToGbk(request
				.getParameter("productcode"));// ��Ʒ����
		String strLineRow = CommonTools.getStrToGbk(request
				.getParameter("linerow"));// ��ʾ����
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));// ��ʾ����

		String strInventoryId = CommonTools.getStrToGb2312(request
				.getParameter("inventoryid")); // ���ID

		if (strLineRow != null && strLineRow.length() > 0) {
			maxLine = Integer.parseInt(strLineRow);
		}
		inventoryBus = new InventoryBusImpl(dao);
		List ls = null;
		try {
			if (flag != null) {
				if (flag.equals("1")) {// ������=������ƶ�->��ѯ���

					strUrl = "/standard/jsp/inventory/move/kc_move_list.jsp";

					String strQueryHql = inventoryBus.getInventoryHQL(
							strWarehouseId, strAreaId, strCargoAlleyId,
							strPlatoon, strColumn, strFloor, strPackageId,
							strTrayCode, null, null, productcode);
					String strCountHql = "select count(*)" + strQueryHql;

					PagingTool pt = CommonPagination.getPagingTool(dao,
							strCountHql, "select sto" + strQueryHql, strUrl, 1,
							maxLine);
					ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				} else if (flag.equals("2")) {// ����ƶ�-������ƶ���ȡ�����Ϣ

					InventoryMoveBus iBus = new InventoryMoveBusImpl(dao);
					strUrl = "/standard/jsp/inventory/move/movement_view.jsp";
					String[] ids = strInventoryId.split("\\|");
					InventoryStorage iStorage = null;
					List<InventoryStorage> list = new ArrayList<InventoryStorage>();
					for (int i = 0; i < ids.length; i++) {
						iStorage = iBus.getInventoryStorage(ids[i]);
						list.add(iStorage);
					}

					request.setAttribute("ids", strInventoryId);
					request.setAttribute("strWarehouseId", strWarehouseId);
					request.setAttribute("inventory", list);
				}
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("����ƶ�==>����ѯ==>��ѯ���:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	/**
	 * �����ƶ�����
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String strUserCode = request.getSession().getAttribute("userCode")
				.toString();// ������
		String strUserName = request.getSession().getAttribute("userName")
				.toString();// ������
		String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));// flag
		String strId = CommonTools.getStrToGb2312(request.getParameter("ids"));// ���ID

		String strTime = CommonTools.getStrToGb2312(request
				.getParameter("strTime"));// �Ƶ�ʱ��
		
		String warehouseid = CommonTools.getStrToGb2312(request
				.getParameter("warehouseid"));// ����ֿ�ID
		
		String strtoWarehouseid = CommonTools.getStrToGb2312(request
				.getParameter("toWarehouseid"));// ����ֿ�ID
		String strtoWhAreaId = CommonTools.getStrToGb2312(request
				.getParameter("toWhAreaId"));// ������� ID

		String strtoCargospaceId = CommonTools.getStrToGb2312(request
				.getParameter("toCargospaceId"));// �����λID
		String strtoCargospaceName = CommonTools.getStrToGb2312(request
				.getParameter("toCargospaceName"));// �����λ����

		String strreasoncode = CommonTools.getStrToGb2312(request
				.getParameter("reasoncode"));// �ƿ�ԭ��

		String strRemark = CommonTools.getStrToGb2312(request
				.getParameter("remark"));// ��ע

		if (flag != null && flag.equals("4")) {

			InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
			ICargoSpaceBus iCargo = new CargoSpaceBusImpl(dao);
			IJobBus iJobBus = new JobBusImpl(dao);

			int i = 0;
			int x=0,y=0;
			try {
				String strBackMsg = "Y";
				
				
				
				//�ƿⵥ��
				BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
				BaseSeq seqEn = seqDao.getSeqByType("YKD");
				String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(), seqEn.getIcodelength(), dao);
				
				//��������
				List<InventoryStorage> lsInStroStorage = new ArrayList<InventoryStorage>();
				List<BaseCargospace> lsCargospace = new ArrayList<BaseCargospace>();
				List<InventoryMovementDetail> lsInMoverDetail = new ArrayList<InventoryMovementDetail>();
				List<InoutJob> lsInoutJob = new ArrayList<InoutJob>();
				List<InoutJobdetail> lsInoutJobDetail = new ArrayList<InoutJobdetail>();
				
				String id = null;
				String[] ids = strId.split("\\|");
				for (int j = 0; j < ids.length; j++) {
					id=ids[j];
					// ��ÿ����Ϣ
					InventoryStorage inStroStorage = iMoveBus.getInventoryStorage(id);
					// ��ȡ��λ��Ϣ
					BaseCargospace cargospace = iCargo.getCargoSpaceById(inStroStorage.getCargoSpaceId());

					if (cargospace != null&& cargospace.getCsstatus().equals("2")&&inStroStorage.getStatus().equals("0")) {// ��λ��ϢΪ����λ״̬�����Ϊδ����״̬
						
						//��ҵ����
						BaseSeq seqEn1 = seqDao.getSeqByType("CKZY");
						String strNo1 = SeqTool.getNewID(seqEn1.getSeqType(),seqEn1.getSeqPrefix(), seqEn1.getIcodelength(),dao);
						
						
						//�����ƿ���ҵʵ��
						InoutJob inoutJob = iJobBus.createInoutJob(strNo1, strUserCode, strTime,strtoWhAreaId, strtoCargospaceId,strtoWarehouseid, inStroStorage);
						
						//�����ƿ���ҵ��ϸʵ��
						InoutJobdetail inoutJobDetail = iJobBus.createInoutJobDetail(strNo1, strUserCode, strTime,strtoWhAreaId, strtoCargospaceId,strtoWarehouseid, inStroStorage);
						
						//�����ƿⵥ��ϸ
						InventoryMovementDetail inMoverDetail = iMoveBus.createMoveDetail(strNo, strUserCode, strTime,strtoWhAreaId, strtoCargospaceId,strtoCargospaceName,strtoWarehouseid, strreasoncode, strRemark, inStroStorage);
						
						//����Ԫ����ӵ���������
						lsInStroStorage.add(inStroStorage);
						lsCargospace.add(cargospace);
						lsInoutJob.add(inoutJob);
						lsInoutJobDetail.add(inoutJobDetail);
						lsInMoverDetail.add(inMoverDetail);
						
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>����ƶ�");
						sysLog.setM_strContent("�ƶ����ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);
						y++;
					} else {
						strBackMsg = "��ȷ����λ����״̬";
						x++;
					}

				}
				if(x!=0){
					strBackMsg = "��"+x+"�����û��������Ӧ���ƿ���ϸ���ݺ���ҵ";
				}
				if(y!=0 && lsInMoverDetail!=null && lsInMoverDetail.size()>0  && lsInoutJob!=null && lsInoutJob.size()>0&& lsInoutJobDetail!=null && lsInoutJobDetail.size()>0 ){ 
					//�ƿⵥ
					InventoryMovementHeader iMoveHeader = new InventoryMovementHeader();
					iMoveHeader.setMovementid(strNo);
					iMoveHeader.setWarehouseid(strtoWarehouseid);
					iMoveHeader.setCreateManid(strUserCode);
					iMoveHeader.setCreateTime(strTime);
					iMoveBus.addMoveHeaderAddMoveDetailUpdateInvent(iMoveHeader,lsInMoverDetail,lsInoutJob,lsInoutJobDetail,lsInStroStorage,lsCargospace);
				}
			
				// ˢ��
				PagingTool pt = (PagingTool) httpsession.getAttribute("paging");
				List ls = null;
				String strUrl = null;
				if (pt != null) {
					// �����ܼ�¼��
					int rows = pt.getM_iCountRow() + i;
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					ls = CommonPagination.getPagingList(dao, pt);

					strUrl = pt.getM_strUrl();
				}
				if (strUrl == null) {
					strUrl = "/jsp/inventory/move/kc_move_list.jsp";
				}

				request.setAttribute("back_msg", strBackMsg);
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("paging", pt);

				request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception e) {
			//	dao.update(inStroStorage);
				Logger.error("������==>����ƶ�==>�����ƶ�ʧ��:" + e.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}

		}
		return null;
	}

	/**
	 * �ƿⵥ�ݲ�ѯ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecQuery(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");
		
		
		String moventid = CommonTools.getStrToGbk(request
				.getParameter("id")); // �ֿ�ID
		// from
		String fmwarehouseid = CommonTools.getStrToGbk(request
				.getParameter("fmwarehouseid")); // �ֿ�ID
		String fmwhAreaId = CommonTools.getStrToGbk(request
				.getParameter("fmwhAreaid")); // ����ID
		String fmplatoon = CommonTools.getStrToGbk(request
				.getParameter("fmplatoon")); // ��
		String fmcolumn = CommonTools.getStrToGbk(request
				.getParameter("fmcolumn")); // ��
		String fmfloor = CommonTools.getStrToGbk(request
				.getParameter("fmfloor")); // ��

		String fmCustomerId = CommonTools.getStrToGbk(request
				.getParameter("fmcustomerid")); // �ͻ�ID ����
		String fmPackageId = CommonTools.getStrToGbk(request
				.getParameter("fmpackageId")); // Ʒ��ID ����
		String fmTrayCode = CommonTools.getStrToGbk(request
				.getParameter("fmtrayCode")); // �������� ����
		// to
		String towhAreaId = CommonTools.getStrToGbk(request
				.getParameter("towhAreaid")); // ����ID
		String toplatoon = CommonTools.getStrToGbk(request
				.getParameter("toplatoon")); // ��
		String tocolumn = CommonTools.getStrToGbk(request
				.getParameter("tocolumn")); // ��
		String tofloor = CommonTools.getStrToGbk(request
				.getParameter("tofloor")); // ��

		String toCustomerId = CommonTools.getStrToGbk(request
				.getParameter("tocustomerId")); // �ͻ�ID ����
		String toPackageId = CommonTools.getStrToGbk(request
				.getParameter("topackageId")); // Ʒ��ID ����
		String toTrayCode = CommonTools.getStrToGbk(request
				.getParameter("totrayCode")); // �������� ����

		String date_from = CommonTools.getStrToGbk(request
				.getParameter("indate_from")); // ����from
		String date_to = CommonTools.getStrToGbk(request
				.getParameter("indate_to")); // ����to

		
		String flag = CommonTools.getStrToGbk(request
				.getParameter("flag")); 
		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // ÿҳ��ʾ����
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}
		
		
		
		if(flag!=null){
			if(flag.equals("1")){
				InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
				List lsmoves = null;
				PagingTool pt = null;
				try {
					strUrl = "/standard/jsp/inventory/ykquery/yk_query_list.jsp";
					pt = iMoveBus.getMovesLs0(fmwarehouseid, fmwhAreaId, fmplatoon,
							fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode,
							towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId,
							toPackageId, toTrayCode, date_from, date_to, strUrl, 1,
							maxLine);
					lsmoves = pt.getLsResult();
					request.setAttribute("exResultList", lsmoves);
					httpsession.setAttribute("paging", pt);
					request.getRequestDispatcher(strUrl).forward(request, response);

				} catch (Exception er) {
					Logger.error("������==>��潻��==>��ѯʧ��:" + er.getMessage());
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}
			}else if(flag.equals("2")){
				InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
				List lsmoves = null;
				PagingTool pt = null;
				try {
					strUrl = "/standard/jsp/inventory/ykquery/yk_query_list_detail.jsp";
					pt = iMoveBus.getMovesLs1(moventid, strUrl, 1,
							maxLine);
					lsmoves = pt.getLsResult();
					request.setAttribute("exResultList", lsmoves);
					httpsession.setAttribute("paging", pt);
					request.getRequestDispatcher(strUrl).forward(request, response);

				} catch (Exception er) {
					Logger.error("������==>��潻��==>��ѯʧ��:" + er.getMessage());
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}
			}
		}
		

		return null;
	}

	/**
	 * ��ӡ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");
		
		String invoiceid = CommonTools.getStrToGbk(request
				.getParameter("invoiceid")); 
		
		// from
		String fmwarehouseid = CommonTools.getStrToGbk(request
				.getParameter("fmwarehouseid")); // �ֿ�ID
		String fmwhAreaId = CommonTools.getStrToGbk(request
				.getParameter("fmwhAreaid")); // ����ID
		String fmplatoon = CommonTools.getStrToGbk(request
				.getParameter("fmplatoon")); // ��
		String fmcolumn = CommonTools.getStrToGbk(request
				.getParameter("fmcolumn")); // ��
		String fmfloor = CommonTools.getStrToGbk(request
				.getParameter("fmfloor")); // ��

		String fmCustomerId = CommonTools.getStrToGbk(request
				.getParameter("fmcustomerid")); // �ͻ�ID ����
		String fmPackageId = CommonTools.getStrToGbk(request
				.getParameter("fmpackageId")); // Ʒ��ID ����
		String fmTrayCode = CommonTools.getStrToGbk(request
				.getParameter("fmtrayCode")); // �������� ����
		// to
		String towhAreaId = CommonTools.getStrToGbk(request
				.getParameter("towhAreaid")); // ����ID
		String toplatoon = CommonTools.getStrToGbk(request
				.getParameter("toplatoon")); // ��
		String tocolumn = CommonTools.getStrToGbk(request
				.getParameter("tocolumn")); // ��
		String tofloor = CommonTools.getStrToGbk(request
				.getParameter("tofloor")); // ��

		String toCustomerId = CommonTools.getStrToGbk(request
				.getParameter("tocustomerId")); // �ͻ�ID ����
		String toPackageId = CommonTools.getStrToGbk(request
				.getParameter("topackageId")); // Ʒ��ID ����
		String toTrayCode = CommonTools.getStrToGbk(request
				.getParameter("totrayCode")); // �������� ����

		String date_from = CommonTools.getStrToGbk(request
				.getParameter("indate_from")); // ����from
		String date_to = CommonTools.getStrToGbk(request
				.getParameter("indate_to")); // ����to

		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // ÿҳ��ʾ����
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}
		InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
//		List lsmoves = null;
//		// PagingTool pt = null;
//		String strHql = null;
		try {
			strUrl = "/standard/jsp/inventory/ykquery/yk_query_print.jsp";
//			strHql = iMoveBus.getQueryHql(fmwarehouseid, fmwhAreaId, fmplatoon,
//					fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode,
//					towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId,
//					toPackageId, toTrayCode, date_from, date_to);
//			lsmoves = dao.searchEntities(strHql);
			
			InventoryMovementHeader movementHeader = new InventoryMovementHeader();
			InventoryMovementDetail movementDetail = new InventoryMovementDetail();
			
			InventoryMovementHeader moveHeader = movementHeader.getIMoveHeader(invoiceid,dao);
			
			List lsmoveDetail = movementDetail.getMoveDetailListToId(invoiceid, dao);
			
			request.setAttribute("result", moveHeader);
			request.setAttribute("exResultList", lsmoveDetail);
			//request.setAttribute("exResultList", lsmoves);
			// httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("������==>��潻��==>��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		return null;
	}

}
