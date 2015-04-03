package com.wms3.bms.standard.dao.allot.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.allot.impl.OutAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotResponseBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotResponseBean.TrayStorageBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotResponseBean.TrayStorageBean.StorageBean;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.dao.base.IBaseWhAreaDao;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 出库分配
 * 
 * @author yangwm
 * 
 */
public class OutboundAllotDaoImpl {

	/**
	 * 临时对象，用于后面组装响应对象
	 * 
	 * @author yangwm
	 * 
	 */
	public class TempBean {
		public String cargoSpaceId; // 货位ID
		public Integer csplatoon; // 货位排
		public Integer cscolumn; // 货位列
		public Integer csfloor; // 货位层
		public String warehouseid; // 仓库ID
		public String whAreaId; // 库区ID
		public String cargoAlleyId; // 巷道ID
		public String oldstatus; // 分配货位前的状态(如果保存失败，则还原货位分配前的状态)

		public String inventoryid; // 库存ID
		public double num; // 分配数量
		public double weight; // 分配重量
		public double netweight; // 分配净重
		public double volume; // 分配体积
		public String inventoryoldstatus; // 分配库存前的状态 (如果保存失败，则还原库存分配前的状态)

		public TempBean(String cargoSpaceId, Integer csplatoon,
				Integer cscolumn, Integer csfloor, String warehouseid,
				String whAreaId, String cargoAlleyId, String oldstatus,
				String inventoryid, double num, double weight,
				double netweight, double volume, String inventoryoldstatus) {
			this.cargoSpaceId = cargoSpaceId;
			this.csplatoon = csplatoon;
			this.cscolumn = cscolumn;
			this.csfloor = csfloor;
			this.warehouseid = warehouseid;
			this.whAreaId = whAreaId;
			this.cargoAlleyId = cargoAlleyId;
			this.oldstatus = oldstatus;

			this.inventoryid = inventoryid;
			this.num = num;
			this.weight = weight;
			this.netweight = netweight;
			this.volume = volume;
			this.inventoryoldstatus = inventoryoldstatus;
		}
	}

	protected TempBean getTempBean(String cargoSpaceId, Integer csplatoon,
			Integer cscolumn, Integer csfloor, String warehouseid,
			String whAreaId, String cargoAlleyId, String oldstatus,
			String inventoryid, double num, double weight, double netweight,
			double volume, String inventoryoldstatus) {

		return new TempBean(cargoSpaceId, csplatoon, cscolumn, csfloor,
				warehouseid, whAreaId, cargoAlleyId, oldstatus, inventoryid,
				num, weight, netweight, volume, inventoryoldstatus);

	}

	public OutboundAllotDaoImpl() {
	}

	protected void updateAllotStatus(String invId, String invStatus,
			String csId, String csStatus, EntityDAO dao) {
		Session session = dao.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.createQuery(
					"update BaseCargospace set csstatus='" + csStatus
							+ "'  where cargoSpaceId='" + csId + "'")
					.executeUpdate();
			session.createQuery(
					"update BaseCargospace set csstatus='" + invStatus
							+ "'  where inventoryid='" + invId + "'")
					.executeUpdate();
			tx.commit();
		} catch (HibernateException he) {
			throw new HibernateException("根据SQL语句进行更新出错！" + he.getMessage());
		} finally {
			dao.closeSession(session);
		}
	}

	/**
	 * 组装StorageBean响应包
	 * 
	 * @param resBean
	 * @param inventoryid
	 * @param num
	 * @param weight
	 * @param netweight
	 * @param volume
	 * @param inventoryoldstatus
	 * @return
	 */
	protected OutAllotResponseBean.TrayStorageBean.StorageBean makeStorageBean(
			OutAllotResponseBean.TrayStorageBean trayBean, String inventoryid,
			double num, double weight, double netweight, double volume,
			String inventoryoldstatus) {

		OutAllotResponseBean.TrayStorageBean.StorageBean stgBean = trayBean
				.getNewStorageBean();
		stgBean.inventoryid = inventoryid; // 库存ID
		stgBean.num = num; // 分配数量
		stgBean.weight = weight; // 分配重量
		stgBean.netweight = netweight; // 分配净重
		stgBean.volume = volume; // 分配体积
		stgBean.inventoryoldstatus = inventoryoldstatus; // 分配库存前的状态

		return stgBean;
	}

	protected OutAllotResponseBean.TrayStorageBean makeTrayStorageBeanHead(
			OutAllotResponseBean resBean, String trayCode) {
		OutAllotResponseBean.TrayStorageBean trayBean = resBean
				.getNewTrayStorageBean();
		trayBean.trayCode = trayCode;

		return trayBean;
	}

	protected OutAllotResponseBean.TrayStorageBean makeTrayStorageBean(
			OutAllotResponseBean resBean, String trayCode,
			List<StorageBean> lsStgBean) {
		OutAllotResponseBean.TrayStorageBean trayBean = resBean
				.getNewTrayStorageBean();
		trayBean.trayCode = trayCode;
		trayBean.lsStorageBean = lsStgBean;

		return trayBean;
	}

	/**
	 * 组装OutAllotResponseBean之head响应包
	 * 
	 * @param cargoSpaceId
	 * @param csplatoon
	 * @param cscolumn
	 * @param csfloor
	 * @param warehouseid
	 * @param whAreaId
	 * @param cargoAlleyId
	 * @param oldstatus
	 * @return
	 */
	protected OutAllotResponseBean makeOutResponseHead(String cargoSpaceId,
			int csplatoon, int cscolumn, int csfloor, String warehouseid,
			String whAreaId, String cargoAlleyId, String oldstatus) {
		OutAllotResponseBean respBean = new OutAllotResponseBean();// 已分配返回对象

		respBean.cargoSpaceId = cargoSpaceId;
		respBean.csplatoon = csplatoon;
		respBean.cscolumn = cscolumn;
		respBean.csfloor = csfloor;
		respBean.warehouseid = warehouseid;
		respBean.whAreaId = whAreaId;
		respBean.cargoAlleyId = cargoAlleyId;
		respBean.oldstatus = oldstatus;

		return respBean;
	}

	/**
	 * 组装整个响应包
	 * 
	 * @param cs
	 * @param reqBean
	 * @param lsStgBean
	 * @return
	 */
	protected OutAllotResponseBean makeOutResponse(BaseCargospace cs,
			OutAllotRequestBean reqBean, List<TrayStorageBean> lsTrayBean) {
		OutAllotResponseBean respBean = new OutAllotResponseBean();// 已分配返回对象

		respBean.cargoSpaceId = cs.getCargoSpaceId();
		respBean.csplatoon = cs.getCsplatoon();
		respBean.cscolumn = cs.getCscolumn();
		respBean.csfloor = cs.getCsfloor();
		respBean.warehouseid = cs.getWarehouseid();
		respBean.whAreaId = cs.getWhAreaId();
		respBean.cargoAlleyId = cs.getCargoAlleyId();
		respBean.oldstatus = cs.getCsstatus();

		respBean.lsTrayStorageBean = lsTrayBean;

		return respBean;
	}

	// /**
	// * 根据条件获取指定产品的巷道
	// *
	// * @param whId
	// * @param whAreaId
	// * @param backAlley
	// * @param productId
	// * @param printDate
	// * @param NoCsOutLock
	// * Y 没有出库分配中的货位的巷道 N 有出库分配中的货位的巷道
	// * @return
	// */
	// private List<String> getAlleyFromByProductDate(String whId,
	// String whAreaId, String backAlley, String productId,
	// String printDate, String NoCsOutLock, EntityDAO dao) {
	// StringBuffer sqlBuffer = new StringBuffer(512);
	//
	// sqlBuffer
	// .append("select a.cargoAlleyId from BaseAlley as a, InventoryStorage as s
	// where a.cargoAlleyId=s.cargoAlleyId and a.m_strInLock='N'");
	//
	// if (backAlley != null && backAlley.length() > 1) {
	// sqlBuffer.append("and a.cargoAlleyId!='");
	// sqlBuffer.append(backAlley);
	// }
	//
	// if (whId != null && whId.length() > 1) {
	// sqlBuffer.append("and a.warehouseid='");
	// sqlBuffer.append(whId);
	// }
	//
	// if (whAreaId != null && whAreaId.length() > 1) {
	// sqlBuffer.append("and a.whAreaId='");
	// sqlBuffer.append(whAreaId);
	// }
	//
	// if (NoCsOutLock.equals("Y")) {
	// sqlBuffer
	// .append("' and !exist(select from BaseAlley as a2,BaseCargospace cs where
	// a2.cargoAlleyId = cs.cargoAlleyId and cs.outlock='Y')");
	// } else {
	// sqlBuffer.append("'");
	// }
	//
	// sqlBuffer.append(" order by sum(case when s.m_strProductno='");
	// sqlBuffer.append(productId);
	//
	// if (printDate != null & printDate.length() > 1) {
	// sqlBuffer.append("' and s.m_strPrintdate='");
	// sqlBuffer.append(printDate);
	// }
	//
	// sqlBuffer.append("' then 1 else 0 end) desc");
	//
	// return dao.searchEntities(sqlBuffer.toString());
	// }
	//
	// /**
	// * 根据巷道和产品获取出货货位
	// *
	// * @param alleyId
	// * @param productId
	// * @param NoCsOutLock
	// * @return
	// */
	// private List<BaseCargospace> getCsFromAlley(String alleyId,
	// String productId, String NoCsOutLock, EntityDAO dao) {
	// String strSql = "select cs from InventoryStorage inv,BaseCargospace cs
	// where inv.cargoSpaceId=cs.cargoSpaceId and inv.cargoAlleyId='"
	// + alleyId
	// + "' and inv.productid='"
	// + productId
	// + "' and cs.csstatus in ('2','4') and cs.outlock='"
	// + NoCsOutLock + "'";
	//
	// return dao.searchEntities(strSql);
	// }
	//
	// /**
	// * 返回产品的先后入库时间
	// *
	// * @param whId
	// * @param whAreaId
	// * @param productId
	// * @return
	// */
	// private List<String> getDateFromByProudctDateSeq(String whId,
	// String whAreaId, String productId, EntityDAO dao) {
	// String strSql = "select inv.indate from InventoryStorage inv where
	// inv.warehouseid='"
	// + whId
	// + "' and inv.whAreaId='"
	// + whAreaId
	// + "' and inv.productid='" + productId + "' order by inv.indate";
	//
	// return dao.searchEntities(strSql);
	// }

	/**
	 * 按先进先出规则
	 * 
	 * @param param
	 * @return
	 */
	protected List<OutAllotResponseBean> getCsFastInFastOut(
			Hashtable<String, Object> param) throws Exception {
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		OutAllotRequestBean reqBean = (OutAllotRequestBean) param
				.get("reqBean");
		OutAllotRequestBean.ProductBean prtBean = (OutAllotRequestBean.ProductBean) param
				.get("prtBean");
		RuleAllocationDetail rAllocationDetail = (RuleAllocationDetail) param
				.get("rAllocationDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String whId = "";
		String whareaId = reqBean.whAreaId;// 库区
		if (whareaId == null || whareaId.length() < 1) {
			whareaId = rAllocationDetail.getTozone();
			whId = (String) param.get("target.warehouseid");
		} else {
			whId = reqBean.warehouseid;
		}
		String alleyInSet = "";
		String alleySet = rAllocationDetail.getToalleys();
		if (alleySet != null && alleySet.length() > 1) {
			String alleys[] = alleySet.split(",");
			for (int k = 0; k < alleys.length; k++) {
				alleyInSet += "'" + alleys[k] + "',";
			}

			alleyInSet = alleyInSet.substring(0, alleyInSet.length() - 1);
		}

		IBaseWhAreaDao whAreaDao = new BaseWhAreaDaoImpl(dao);
		BaseWharea whArea = whAreaDao.getWhareaById(whareaId);
		String isAutoWh = "Y";// 是否立体库区
		if (!whArea.getwhAreaType().equals("2"))
			isAutoWh = "N";

		String isClearLoc = rAllocationDetail.getClearloc_flag(); // 是否清仓
		String isPickPart = rAllocationDetail.getApart_flag(); // 是否拆零
		String isUnpacking = rAllocationDetail.getPart_flag(); // 拆包
		String isOverLoc = rAllocationDetail.getOver_flag(); // 是否拣货位超量分配
		String isStoragePick = rAllocationDetail.getBulkpick_flag(); // 存储位拣货
		String isAutoSupply = rAllocationDetail.getAuto_flag(); // 自动产生补货任务
		String isPartAllot = rAllocationDetail.getPart_allocation_flag();// 是否部分分配，即不能满足全部数量
		String dateType = (String) param.get("datetype");// 日期类型

		String countUnit = rAllocationDetail.getUnit_flag(); // 计数单位
		double countNum = 0; // 计量数量
		double allotNum = 0;
		double pieceNum = 0;
		double weightNum = 0;
		double netweightNum = 0;
		double volumeNum = 0;

		IInventoryBus invBus = new InventoryBusImpl(dao);
		Object[] sumObj = null;/*invBus.getGroupInventoryNum(whId, whareaId,
				reqBean.ownerid, prtBean.productid, prtBean.packid,
				prtBean.lotatt1, prtBean.lotatt2, prtBean.lotatt3,
				prtBean.lotatt4, prtBean.lotatt5, prtBean.lotatt6,
				prtBean.lotatt7, prtBean.lotatt8, prtBean.lotatt9,
				prtBean.lotatt10, prtBean.lotatt11, prtBean.lotatt12);
*/
		IOutBoundBus outBus = new OutBoundBusImpl(dao);
		Object[] sumSoftObj = outBus.getGroupSoftallocationNum(whId, whareaId,
				reqBean.ownerid, prtBean.productid, prtBean.packid,
				prtBean.lotatt1, prtBean.lotatt2, prtBean.lotatt3,
				prtBean.lotatt4, prtBean.lotatt5, prtBean.lotatt6,
				prtBean.lotatt7, prtBean.lotatt8, prtBean.lotatt9,
				prtBean.lotatt10, prtBean.lotatt11, prtBean.lotatt12);

		double currNum = 0;
		double softNum = 0;
		if (countUnit.equals("1")) {// 件数
			countNum = prtBean.getNum();
			currNum = sumObj[0] == null ? 0 : (Double) sumObj[0];
			softNum = sumSoftObj[0] == null ? 0 : (Double) sumSoftObj[0];
		} else if (countUnit.equals("2")) {// 毛重
			countNum = prtBean.getWeight();
			currNum = sumObj[1] == null ? 0 : (Double) sumObj[1];
			softNum = sumSoftObj[1] == null ? 0 : (Double) sumSoftObj[1];
		} else if (countUnit.equals("3")) {// 净重
			countNum = prtBean.getNetweight();
			currNum = sumObj[2] == null ? 0 : (Double) sumObj[2];
			softNum = sumSoftObj[2] == null ? 0 : (Double) sumSoftObj[2];
		} else if (countUnit.equals("4")) {// 体积
			countNum = prtBean.getVolume();
			currNum = sumObj[3] == null ? 0 : (Double) sumObj[3];
			softNum = sumSoftObj[3] == null ? 0 : (Double) sumSoftObj[3];
		}

		// 判断库存数量-冻结数量-已分配数量-预配数量后是否够分配
		currNum = currNum - softNum;
		if (currNum == 0)
			return null;

		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		String strSql = "select inv_s.inventoryid, inv_s.pnum, inv_s.pweight, inv_s.pnetweight, inv_s.pvolume,inv_s.status,inv_s.traycode";
		strSql += "b_cs.cargo_alley_id, b_cs.cargo_space_id, b_cs.csstatus, b_cs.csplatoon, b_cs.cscolumn, b_cs.csfloor, b_cs.storagetype, b_cs.istwin, b_cs.location, b_cs.twincsid";
		strSql += "  from inventory_storage inv_s, base_cargospace b_cs where inv_s.cargo_space_id=b_cs.cargo_space_id and inv_s.cargo_space_id in";
		strSql += " (select  TOP(100) PERCENT  inv.cargo_space_id";
		strSql += " from inventory_storage inv,base_cargospace cs ";
		strSql += " where inv.cargo_space_id=cs.cargo_space_id and inv.productid='"
				+ prdt.getProductid()
				+ "' and cs.csstatus in ('2','4') and cs.outlock='N'";
		strSql += "  and cs.cargo_area_id='" + whareaId
				+ "' and cs.warehouseid='" + whId + "'";
		strSql += "  and cs.cargo_alley_id in(" + alleyInSet + ")";

		if (prtBean.lotatt1 != null && prtBean.lotatt1.length() > 0) {
			strSql += " and inv.lotatt1='" + prtBean.lotatt1 + "'";
		}

		if (prtBean.lotatt2 != null && prtBean.lotatt2.length() > 0) {
			strSql += " and inv.lotatt2='" + prtBean.lotatt2 + "'";
		}

		if (prtBean.lotatt3 != null && prtBean.lotatt3.length() > 0) {
			strSql += " and inv.lotatt3='" + prtBean.lotatt3 + "'";
		}

		if (prtBean.lotatt4 != null && prtBean.lotatt4.length() > 0) {
			strSql += " and inv.lotatt4='" + prtBean.lotatt4 + "'";
		}

		if (prtBean.lotatt5 != null && prtBean.lotatt5.length() > 0) {
			strSql += " and inv.lotatt5='" + prtBean.lotatt5 + "'";
		}

		if (prtBean.lotatt6 != null && prtBean.lotatt6.length() > 0) {
			strSql += " and inv.lotatt6='" + prtBean.lotatt6 + "'";
		}

		if (prtBean.lotatt7 != null && prtBean.lotatt7.length() > 0) {
			strSql += " and inv.lotatt7='" + prtBean.lotatt7 + "'";
		}

		if (prtBean.lotatt8 != null && prtBean.lotatt8.length() > 0) {
			strSql += " and inv.lotatt8='" + prtBean.lotatt8 + "'";
		}

		if (prtBean.lotatt9 != null && prtBean.lotatt9.length() > 0) {
			strSql += " and inv.lotatt9='" + prtBean.lotatt9 + "'";
		}

		if (prtBean.lotatt10 != null && prtBean.lotatt10.length() > 0) {
			strSql += " and inv.lotatt10='" + prtBean.lotatt10 + "'";
		}

		if (prtBean.lotatt11 != null && prtBean.lotatt11.length() > 0) {
			strSql += " and inv.lotatt11='" + prtBean.lotatt11 + "'";
		}

		if (prtBean.lotatt12 != null && prtBean.lotatt12.length() > 0) {
			strSql += " and inv.lotatt12='" + prtBean.lotatt12 + "'";
		}

		if (dateType.equals("indate")) {// 入库时间
			strSql += " group by inv.cargo_space_id, inv.indate ";
			strSql += " order by inv.indate";
		} else if (dateType.equals("pdate")) {// 到期时间=生产日期
			strSql += " group by inv.traycode, inv.cargo_space_id, inv.lotatt1 ";
			strSql += " order by inv.lotatt1";
		}

		// 清空优先
		if (isClearLoc.equals("Y")) {
			if (countUnit.equals("1")) {// 件数
				strSql += ", sum(inv.pnum)";
			} else if (countUnit.equals("2")) {// 毛重
				strSql += ", sum(inv.pweight)";
			} else if (countUnit.equals("3")) {// 净重
				strSql += ", sum(inv.pnetweight)";
			} else if (countUnit.equals("4")) {// 体积
				strSql += ", sum(inv.pvolume)";
			}
		}

		strSql += ")";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<OutAllotResponseBean> lsOutBean = new ArrayList<OutAllotResponseBean>();

		try {
			String preCsId = null;
			String preTrayCode = null;
			OutAllotResponseBean respBean = null;
			TrayStorageBean trayBean = null;
			conn = dao.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(strSql);
			while (rs.next()) {
				// 从货位中获取当前产品相关数量,按库存流水
				String inventoryid = rs.getString(0);
				double csPieceNum = rs.getDouble(1);
				double csWeightNum = rs.getDouble(2);
				double csNetWeightNum = rs.getDouble(3);
				double csVolumeNum = rs.getDouble(4);
				String status = rs.getString(5);
				String traycode = rs.getString(6);

				String storagetype = rs.getString(7);
				String istwin = rs.getString(8);
				String location = rs.getString(9);
				String twincsid = rs.getString(10);

				double csCountNum = 0;

				String cargo_alley_id = rs.getString(11);
				String cargo_space_id = rs.getString(12);
				String csstatus = rs.getString(13);
				int csplatoon = rs.getInt(14);
				int cscolumn = rs.getInt(15);
				int csfloor = rs.getInt(16);

				if (!cargo_space_id.equals(preCsId)) {
					preCsId = cargo_space_id;

					// 构建响应包头
					respBean = makeOutResponseHead(cargo_space_id, csplatoon,
							cscolumn, csfloor, whId, whareaId, cargo_alley_id,
							csstatus);
					lsOutBean.add(respBean);
				}

				if (!traycode.equals(preTrayCode)) {
					preTrayCode = traycode;

					// 构建托盘包头
					trayBean = makeTrayStorageBeanHead(respBean, traycode);
					respBean.addTrayStorageBean(trayBean);
				}

				if (currNum <= 0 || allotNum >= countNum)
					break;

				if (currNum < countNum - allotNum) {// 当前库存已经不够分配
					if (isAutoSupply.equals("Y")) {
						// 生成补货任务
					}

					if (isPartAllot.equals("N"))
						return null;
				}

				// 判断货位是否为立体库货位
				if (isAutoWh.equals("Y")) {
					// 如果是则判断是否为双升位
					if (istwin.equals("Y")) {
						if (location.equals("1")) {
							// 如果为双升里货位，则判断外货位是否为空，否则不分配
							BaseCargospace tmpCs = iCs
									.getCargoSpaceById(twincsid);
							if (!tmpCs.getCsstatus().equals("1")) {
								continue;
							}
						}
					}
				}

				if (countUnit.equals("1")) {// 件数
					csCountNum = csPieceNum;
				} else if (countUnit.equals("2")) {// 毛重
					csCountNum = csWeightNum;
				} else if (countUnit.equals("3")) {// 净重
					csCountNum = csNetWeightNum;
				} else if (countUnit.equals("4")) {// 体积
					csCountNum = csVolumeNum;
				}

				// 判断货位上是否托、或箱、或件
				if (storagetype.equals("PL") && isPickPart.equals("N")
						&& isOverLoc.equals("N")) {// 判断是否拆零|是否超量拣货

					if ((countNum - allotNum) < csCountNum) {
						continue;
					}

				} else if (storagetype.equals("CS") && isUnpacking.equals("N")
						&& isOverLoc.equals("N")) {// 判断是否拆包|是否超量拣货
					if ((countNum - allotNum) < csCountNum) {
						continue;
					}

				} else if (storagetype.equals("EA")
						&& isStoragePick.equals("N")) {// 判断是否存储位拣货
					continue;
				}

				allotNum += csCountNum;
				currNum -= csCountNum;

				pieceNum += csPieceNum;
				weightNum += csWeightNum;
				netweightNum += csNetWeightNum;
				volumeNum += csPieceNum;

				StorageBean stgBean = makeStorageBean(trayBean, inventoryid,
						csPieceNum, csWeightNum, csNetWeightNum, csVolumeNum,
						status);

				trayBean.addStorageBean(stgBean);

				// 更新库存货位状态
				updateAllotStatus(inventoryid, "1", cargo_space_id, "3", dao);
			}
		} finally {
			if (rs != null)
				rs.close();

			if (st != null)
				st.close();

			if (conn != null)
				conn.close();
		}

		return lsOutBean;
	}

	/**
	 * 按入库日期或到期日期先后顺序及巷道均衡进行分配货位
	 * 
	 * @param param
	 * @return
	 */
	protected List<OutAllotResponseBean> getCsFastInFastOutEvenChannel(
			Hashtable<String, Object> param) throws Exception {
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		OutAllotRequestBean reqBean = (OutAllotRequestBean) param
				.get("reqBean");
		OutAllotRequestBean.ProductBean prtBean = (OutAllotRequestBean.ProductBean) param
				.get("prtBean");
		RuleAllocationDetail rAllocationDetail = (RuleAllocationDetail) param
				.get("rAllocationDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String whId = "";
		String whareaId = reqBean.whAreaId;// 库区
		if (whareaId == null || whareaId.length() < 1) {
			whareaId = rAllocationDetail.getTozone();
			whId = (String) param.get("target.warehouseid");
		} else {
			whId = reqBean.warehouseid;
		}

		// 巷道限制
		String alleyInSet = "";
		String alleySet = rAllocationDetail.getToalleys();
		if (alleySet != null && alleySet.length() > 1) {
			String alleys[] = alleySet.split(",");
			for (int k = 0; k < alleys.length; k++) {
				alleyInSet += "'" + alleys[k] + "',";
			}

			alleyInSet = alleyInSet.substring(0, alleyInSet.length() - 1);
		}

		IBaseWhAreaDao whAreaDao = new BaseWhAreaDaoImpl(dao);
		BaseWharea whArea = whAreaDao.getWhareaById(whareaId);
		String isAutoWh = "Y";// 是否立体库区
		if (!whArea.getwhAreaType().equals("2"))
			isAutoWh = "N";

		String isClearLoc = rAllocationDetail.getClearloc_flag(); // 是否清仓
		String isPickPart = rAllocationDetail.getApart_flag(); // 是否拆零
		String isUnpacking = rAllocationDetail.getPart_flag(); // 拆包
		String isOverLoc = rAllocationDetail.getOver_flag(); // 是否拣货位超量分配
		String isStoragePick = rAllocationDetail.getBulkpick_flag(); // 存储位拣货
		String isAutoSupply = rAllocationDetail.getAuto_flag(); // 自动产生补货任务
		String isPartAllot = rAllocationDetail.getPart_allocation_flag();// 是否部分分配，即不能满足全部数量
		String dateType = (String) param.get("datetype");// 日期类型

		String countUnit = rAllocationDetail.getUnit_flag(); // 计数单位
		double countNum = 0; // 计量数量(需要分配的数量)
		double allotNum = 0;
		double pieceNum = 0;
		double weightNum = 0;
		double netweightNum = 0;
		double volumeNum = 0;

		IInventoryBus invBus = new InventoryBusImpl(dao);
		Object[] sumObj = null;/*invBus.getGroupInventoryNum(whId, whareaId,
				reqBean.ownerid, prtBean.productid, prtBean.packid,
				prtBean.lotatt1, prtBean.lotatt2, prtBean.lotatt3,
				prtBean.lotatt4, prtBean.lotatt5, prtBean.lotatt6,
				prtBean.lotatt7, prtBean.lotatt8, prtBean.lotatt9,
				prtBean.lotatt10, prtBean.lotatt11, prtBean.lotatt12);*/

		IOutBoundBus outBus = new OutBoundBusImpl(dao);
		Object[] sumSoftObj = outBus.getGroupSoftallocationNum(whId, whareaId,
				reqBean.ownerid, prtBean.productid, prtBean.packid,
				prtBean.lotatt1, prtBean.lotatt2, prtBean.lotatt3,
				prtBean.lotatt4, prtBean.lotatt5, prtBean.lotatt6,
				prtBean.lotatt7, prtBean.lotatt8, prtBean.lotatt9,
				prtBean.lotatt10, prtBean.lotatt11, prtBean.lotatt12);

		double currNum = 0;
		double softNum = 0;
		if (countUnit.equals("1")) {// 件数
			countNum = prtBean.getNum();
			currNum = sumObj[0] == null ? 0 : (Double) sumObj[0];
			softNum = sumSoftObj[0] == null ? 0 : (Double) sumSoftObj[0];
		} else if (countUnit.equals("2")) {// 毛重
			countNum = prtBean.getWeight();
			currNum = sumObj[1] == null ? 0 : (Double) sumObj[1];
			softNum = sumSoftObj[1] == null ? 0 : (Double) sumSoftObj[1];
		} else if (countUnit.equals("3")) {// 净重
			countNum = prtBean.getNetweight();
			currNum = sumObj[2] == null ? 0 : (Double) sumObj[2];
			softNum = sumSoftObj[2] == null ? 0 : (Double) sumSoftObj[2];
		} else if (countUnit.equals("4")) {// 体积
			countNum = prtBean.getVolume();
			currNum = sumObj[3] == null ? 0 : (Double) sumObj[3];
			softNum = sumSoftObj[3] == null ? 0 : (Double) sumSoftObj[3];
		}

		// 判断库存数量-冻结数量-已分配数量-预配数量后是否够分配
		currNum = currNum - softNum;
		// if (currNum == 0)
		// return null;

		String strSql0 = "select inv_s.inventoryid, inv_s.pnum, inv_s.pweight, inv_s.pnetweight, inv_s.pvolume,inv_s.status,inv_s.traycode,";
		strSql0 += "b_cs.cargoAlleyId, b_cs.cargoSpaceId, b_cs.csstatus, b_cs.csplatoon, b_cs.cscolumn, b_cs.csfloor, b_cs.storagetype, b_cs.istwin, b_cs.location, b_cs.twincsid";
		strSql0 += "  from InventoryStorage inv_s, BaseCargospace a where inv_s.cargoSpaceId=a.cargoSpaceId and a.cargoAlleyId ";

		String strSql1 = " select  inv.cargoAlleyId";
		strSql1 += " from InventoryStorage inv,BaseAlley a";
		strSql1 += " where inv.cargoAlleyId=a.cargoAlleyId ";
		strSql1 += " and inv.productid='" + prdt.getProductid()
				+ "' and a.outlock='N'";
		strSql1 += " and a.whAreaId='" + whareaId + "' and a.warehouseid='"
				+ whId + "'";
		strSql1 += " and a.cargoAlleyId in(" + alleyInSet + ")";

		String strSql2 = "";
		if (prtBean.lotatt1 != null && prtBean.lotatt1.length() > 0) {
			strSql2 += " and inv.lotatt1='" + prtBean.lotatt1 + "'";
		}

		if (prtBean.lotatt2 != null && prtBean.lotatt2.length() > 0) {
			strSql2 += " and inv.lotatt2='" + prtBean.lotatt2 + "'";
		}

		if (prtBean.lotatt3 != null && prtBean.lotatt3.length() > 0) {
			strSql2 += " and inv.lotatt3='" + prtBean.lotatt3 + "'";
		}

		if (prtBean.lotatt4 != null && prtBean.lotatt4.length() > 0) {
			strSql2 += " and inv.lotatt4='" + prtBean.lotatt4 + "'";
		}

		if (prtBean.lotatt5 != null && prtBean.lotatt5.length() > 0) {
			strSql2 += " and inv.lotatt5='" + prtBean.lotatt5 + "'";
		}

		if (prtBean.lotatt6 != null && prtBean.lotatt6.length() > 0) {
			strSql2 += " and inv.lotatt6='" + prtBean.lotatt6 + "'";
		}

		if (prtBean.lotatt7 != null && prtBean.lotatt7.length() > 0) {
			strSql2 += " and inv.lotatt7='" + prtBean.lotatt7 + "'";
		}

		if (prtBean.lotatt8 != null && prtBean.lotatt8.length() > 0) {
			strSql2 += " and inv.lotatt8='" + prtBean.lotatt8 + "'";
		}

		if (prtBean.lotatt9 != null && prtBean.lotatt9.length() > 0) {
			strSql2 += " and inv.lotatt9='" + prtBean.lotatt9 + "'";
		}

		if (prtBean.lotatt10 != null && prtBean.lotatt10.length() > 0) {
			strSql2 += " and inv.lotatt10='" + prtBean.lotatt10 + "'";
		}

		if (prtBean.lotatt11 != null && prtBean.lotatt11.length() > 0) {
			strSql2 += " and inv.lotatt11='" + prtBean.lotatt11 + "'";
		}

		if (prtBean.lotatt12 != null && prtBean.lotatt12.length() > 0) {
			strSql2 += " and inv.lotatt12='" + prtBean.lotatt12 + "'";
		}

		String strSql3 = "";
		if (dateType.equals("indate")) {// 入库时间
			strSql3 += " group by inv.cargoAlleyId, inv.indate ";
			strSql3 += " order by inv.indate";
		} else if (dateType.equals("pdate")) {// 到期时间=生产日期
			strSql3 += " group by inv.cargoAlleyId, inv.lotatt1 ";
			strSql3 += " order by inv.lotatt1";
		}

		String strSql4 = "";
		if (dateType.equals("indate")) {// 入库时间
			strSql4 += " group by inv.traycode, inv.cargoSpaceId, inv.indate ";
			strSql4 += " order by inv.indate";
		} else if (dateType.equals("pdate")) {// 到期时间=生产日期
			strSql4 += " group by inv.traycode, inv.cargoSpaceId, inv.lotatt1 ";
			strSql4 += " order by inv.lotatt1";
		}

		// 清空优先
		String strSql5 = "";
		if (isClearLoc.equals("Y")) {
			if (countUnit.equals("1")) {// 件数
				strSql5 += ", sum(inv.pnum)";
			} else if (countUnit.equals("2")) {// 毛重
				strSql5 += ", sum(inv.pweight)";
			} else if (countUnit.equals("3")) {// 净重
				strSql5 += ", sum(inv.pnetweight)";
			} else if (countUnit.equals("4")) {// 体积
				strSql5 += ", sum(inv.pvolume)";
			}
		}

		List<OutAllotResponseBean> lsOutBean = new ArrayList<OutAllotResponseBean>();

		String preCsId = null;
		String preTrayCode = null;
		OutAllotResponseBean respBean = null;
		TrayStorageBean trayBean = null;

		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		List<String> lsAlley = dao.searchEntities(strSql1 + strSql2 + strSql3
				+ strSql5);
		for (int i = 0; i < lsAlley.size(); i++) {
			String alleyId = (String) lsAlley.get(i);

			strSql0 += " = '" + alleyId + "'";
			strSql0 += strSql2 + strSql4 + strSql5;

			List<Map> lsInvCs = dao.searchEntities(strSql0, 0, 1);
			Map InvCs = lsInvCs.get(0);

			// 从货位中获取当前产品相关数量,按库存流水
			String inventoryid = (String) InvCs.get("inventoryid");
			double csPieceNum = (Double) InvCs.get("pnum");
			double csWeightNum = (Double) InvCs.get("pweight");
			double csNetWeightNum = (Double) InvCs.get("pnetweight");
			double csVolumeNum = (Double) InvCs.get("pvolume");
			String status = (String) InvCs.get("status");
			String traycode = (String) InvCs.get("traycode");

			String storagetype = (String) InvCs.get("storagetype");
			String istwin = (String) InvCs.get("istwin");
			String location = (String) InvCs.get("location");
			String twincsid = (String) InvCs.get("twincsid");
			
			double csCountNum = 0;

			String cargo_alley_id = (String) InvCs.get("cargoAlleyId");
			String cargo_space_id = (String) InvCs.get("cargoSpaceId");
			String csstatus = (String) InvCs.get("csstatus");
			int csplatoon = (Integer) InvCs.get("csplatoon");
			int cscolumn = (Integer) InvCs.get("cscolumn");
			int csfloor = (Integer) InvCs.get("csfloor");
						
			if (!cargo_space_id.equals(preCsId)) {
				preCsId = cargo_space_id;

				// 构建响应包头
				respBean = makeOutResponseHead(cargo_space_id, csplatoon,
						cscolumn, csfloor, whId, whareaId, cargo_alley_id,
						csstatus);
				lsOutBean.add(respBean);
			}

			if (!traycode.equals(preTrayCode)) {
				preTrayCode = traycode;

				// 构建托盘包头
				trayBean = makeTrayStorageBeanHead(respBean, traycode);
				respBean.addTrayStorageBean(trayBean);
			}

			if (currNum <= 0 || allotNum >= countNum)
				break;

			if (currNum < countNum - allotNum) {// 当前库存已经不够分配
				if (isAutoSupply.equals("Y")) {
					// 生成补货任务
				}

				if (isPartAllot.equals("N"))
					return null;
			}

			// 判断货位是否为立体库货位
			if (isAutoWh.equals("Y")) {
				// 如果是则判断是否为双升位
				if (istwin.equals("Y")) {
					if (location.equals("1")) {
						// 如果为双升里货位，则判断外货位是否为空，否则不分配
						BaseCargospace tmpCs = iCs.getCargoSpaceById(twincsid);
						if (!tmpCs.getCsstatus().equals("1")) {
							continue;
						}
					}
				}
			}

			if (countUnit.equals("1")) {// 件数
				csCountNum = csPieceNum;
			} else if (countUnit.equals("2")) {// 毛重
				csCountNum = csWeightNum;
			} else if (countUnit.equals("3")) {// 净重
				csCountNum = csNetWeightNum;
			} else if (countUnit.equals("4")) {// 体积
				csCountNum = csVolumeNum;
			}

			// 判断货位上是否托、或箱、或件
			if (storagetype.equals("PL") && isPickPart.equals("N")
					&& isOverLoc.equals("N")) {// 判断是否拆零|是否超量拣货

				if ((countNum - allotNum) < csCountNum) {
					continue;
				}

			} else if (storagetype.equals("CS") && isUnpacking.equals("N")
					&& isOverLoc.equals("N")) {// 判断是否拆包|是否超量拣货
				if ((countNum - allotNum) < csCountNum) {
					continue;
				}

			} else if (storagetype.equals("EA") && isStoragePick.equals("N")) {// 判断是否存储位拣货
				continue;
			}

			allotNum += csCountNum;
			currNum -= csCountNum;

			pieceNum += csPieceNum;
			weightNum += csWeightNum;
			netweightNum += csNetWeightNum;
			volumeNum += csPieceNum;

			StorageBean stgBean = makeStorageBean(trayBean, inventoryid,
					csPieceNum, csWeightNum, csNetWeightNum, csVolumeNum,
					status);

			trayBean.addStorageBean(stgBean);


			// 更新库存货位状态
			updateAllotStatus(inventoryid, "1", cargo_space_id, "3", dao);

		}

		return lsOutBean;

	}

	/**
	 * 指定货位分配
	 * 
	 * @param reqBean
	 * @param prtBean
	 * @param rAllocationDetail
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	protected List<OutAllotResponseBean> getCsTarget(
			OutAllotRequestBean reqBean,
			OutAllotRequestBean.ProductBean prtBean,
			RuleAllocationDetail rAllocationDetail, String whId, EntityDAO dao)
			throws Exception {

		String whareaId = reqBean.whAreaId;// 库区
		if (whareaId == null || whareaId.length() < 1) {
			whareaId = rAllocationDetail.getTozone();
		} else {
			whId = reqBean.warehouseid;
		}

		IBaseWhAreaDao whAreaDao = new BaseWhAreaDaoImpl(dao);
		BaseWharea whArea = whAreaDao.getWhareaById(whareaId);
		String isAutoWh = "Y";// 是否立体库区
		if (!whArea.getwhAreaType().equals("2"))
			isAutoWh = "N";

		String csId = rAllocationDetail.getTolocationid();// 指定的库位

		String isPickPart = rAllocationDetail.getApart_flag(); // 是否拆零
		String isUnpacking = rAllocationDetail.getPart_flag(); // 拆包
		String isOverLoc = rAllocationDetail.getOver_flag(); // 是否拣货位超量分配
		String isStoragePick = rAllocationDetail.getBulkpick_flag(); // 存储位拣货
		String isPartAllot = rAllocationDetail.getPart_allocation_flag();// 是否部分分配，即不能满足全部数量

		String countUnit = rAllocationDetail.getUnit_flag(); // 计数单位
		double countNum = 0; // 计量数量
		double allotNum = 0;
		double pieceNum = 0;
		double weightNum = 0;
		double netweightNum = 0;
		double volumeNum = 0;

		if (countUnit.equals("1")) {// 件数
			countNum = prtBean.getNum();
		} else if (countUnit.equals("2")) {// 毛重
			countNum = prtBean.getWeight();
		} else if (countUnit.equals("3")) {// 净重
			countNum = prtBean.getNetweight();
		} else if (countUnit.equals("4")) {// 体积
			countNum = prtBean.getVolume();
		}

		String strSql = "select inv_s.inventoryid, inv_s.pnum, inv_s.pweight, inv_s.pnetweight, inv_s.pvolume,inv_s.status, inv_s.traycode, ";
		strSql += " b_cs.cargoAlleyId, b_cs.cargoSpaceId, b_cs.csstatus, b_cs.csplatoon, b_cs.cscolumn, b_cs.csfloor, b_cs.storagetype, b_cs.istwin, b_cs.location, b_cs.twincsid";
		strSql += "  from InventoryStorage inv_s, BaseCargospace b_cs where inv_s.cargoSpaceId=b_cs.cargoSpaceId and b_cs.cargoSpaceId ='"
				+ csId + "'";

		List<OutAllotResponseBean> lsOutBean = new ArrayList<OutAllotResponseBean>();

		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		List lsInvCs = dao.searchEntities(strSql);
		if (lsInvCs == null || lsInvCs.size() < 1)
			return null;

		Map InvCs = (Map) lsInvCs.get(0);

		// 从货位中获取当前产品相关数量,按库存流水
		String inventoryid = (String) InvCs.get("inventoryid");
		double csPieceNum = (Double) InvCs.get("pnum");
		double csWeightNum = (Double) InvCs.get("pweight");
		double csNetWeightNum = (Double) InvCs.get("pnetweight");
		double csVolumeNum = (Double) InvCs.get("pvolume");
		String status = (String) InvCs.get("status");
		String traycode = (String) InvCs.get("traycode");

		String storagetype = (String) InvCs.get("storagetype");
		String istwin = (String) InvCs.get("istwin");
		String location = (String) InvCs.get("location");
		String twincsid = (String) InvCs.get("twincsid");
		
		double csCountNum = 0;

		String cargo_alley_id = (String) InvCs.get("cargoAlleyId");
		String cargo_space_id = (String) InvCs.get("cargoSpaceId");
		String csstatus = (String) InvCs.get("csstatus");
		int csplatoon = (Integer) InvCs.get("csplatoon");
		int cscolumn = (Integer) InvCs.get("cscolumn");
		int csfloor = (Integer) InvCs.get("csfloor");

		if (countUnit.equals("1")) {// 件数
			csCountNum = csPieceNum;
		} else if (countUnit.equals("2")) {// 毛重
			csCountNum = csWeightNum;
		} else if (countUnit.equals("3")) {// 净重
			csCountNum = csNetWeightNum;
		} else if (countUnit.equals("4")) {// 体积
			csCountNum = csVolumeNum;
		}

		if (csCountNum < countNum) {// 当前库存已经不够分配
			if (isPartAllot.equals("N"))
				return null;
		}

		// 判断货位是否为立体库货位
		if (isAutoWh.equals("Y")) {
			// 如果是则判断是否为双升位
			if (istwin.equals("Y")) {
				if (location.equals("1")) {
					// 如果为双升里货位，则判断外货位是否为空，否则不分配
					BaseCargospace tmpCs = iCs.getCargoSpaceById(twincsid);
					if (!tmpCs.getCsstatus().equals("1")) {
						return null;
					}
				}
			}
		}

		// 判断货位上是否托、或箱、或件
		if (storagetype.equals("PL") && isPickPart.equals("N")
				&& isOverLoc.equals("N")) {// 判断是否拆零|是否超量拣货

			if ((countNum - allotNum) < csCountNum) {
				return null;
			}

		} else if (storagetype.equals("CS") && isUnpacking.equals("N")
				&& isOverLoc.equals("N")) {// 判断是否拆包|是否超量拣货
			if ((countNum - allotNum) < csCountNum) {
				return null;
			}

		} else if (storagetype.equals("EA") && isStoragePick.equals("N")) {// 判断是否存储位拣货
			return null;
		}

		allotNum += csCountNum;

		pieceNum += csPieceNum;
		weightNum += csWeightNum;
		netweightNum += csNetWeightNum;
		volumeNum += csPieceNum;
		
		OutAllotResponseBean respBean = makeOutResponseHead(cargo_space_id, csplatoon,
				cscolumn, csfloor, whId, whareaId, cargo_alley_id,
				csstatus);
		lsOutBean.add(respBean);
 
		TrayStorageBean trayBean = makeTrayStorageBeanHead(respBean, traycode);
		
		StorageBean stgBean = makeStorageBean(trayBean, inventoryid,
				csPieceNum, csWeightNum, csNetWeightNum, csVolumeNum,
				status);

		trayBean.addStorageBean(stgBean);

		// 更新库存与货位状态
		updateAllotStatus(inventoryid, "1", cargo_space_id, "3", dao);
		return lsOutBean;

	}

	/**
	 * 按入库日期先进先出分配出库
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<OutAllotResponseBean> getCsFastInFastOut0(
			Hashtable<String, Object> param) throws Exception {
		param.put("datetype", "indate");

		return getCsFastInFastOut(param);
	}

	/**
	 * 按入库日期先进先出且巷道均衡分配出库
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<OutAllotResponseBean> getCsFastInFastOutEvenChannel0(
			Hashtable<String, Object> param) throws Exception {
		param.put("datetype", "indate");

		return getCsFastInFastOutEvenChannel(param);
	}

	/**
	 * 按生产日期先进先出分配出库
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<OutAllotResponseBean> getCsFastInFastOut1(
			Hashtable<String, Object> param) throws Exception {
		param.put("datetype", "pdate");

		return getCsFastInFastOut(param);
	}

	/**
	 * 按生产日期先进先出且巷道均衡分配出库
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<OutAllotResponseBean> getCsFastInFastOutEvenChannel1(
			Hashtable<String, Object> param) throws Exception {
		param.put("datetype", "pdate");

		return getCsFastInFastOutEvenChannel(param);
	}

	/**
	 * 按指定货位分配出库
	 * 
	 * @param param
	 * @return
	 */
	public List<OutAllotResponseBean> getCsAssign(
			Hashtable<String, Object> param) throws Exception {
		OutAllotRequestBean reqBean = (OutAllotRequestBean) param
				.get("reqBean");
		OutAllotRequestBean.ProductBean prtBean = (OutAllotRequestBean.ProductBean) param
				.get("prtBean");
		RuleAllocationDetail rAllocationDetail = (RuleAllocationDetail) param
				.get("rAllocationDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");
		String whId = (String) param.get("target.warehouseid");

		return getCsTarget(reqBean, prtBean, rAllocationDetail, whId, dao);
	}

}
