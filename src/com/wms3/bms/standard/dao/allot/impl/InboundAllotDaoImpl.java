package com.wms3.bms.standard.dao.allot.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.allot.impl.InAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.InAllotResponseBean;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.ICustomerBus;
import com.wms3.bms.standard.business.base.IPackageBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.base.impl.CustomerBusImpl;
import com.wms3.bms.standard.business.base.impl.PackageBusImpl;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.IBasePackageMastermesgDao;
import com.wms3.bms.standard.dao.base.IBaseWhAreaDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BasePackageMastermesgDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseCustomer;
import com.wms3.bms.standard.entity.base.BasePackage;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 入库分配
 * 
 * @author yangwm
 * 
 */
public class InboundAllotDaoImpl {
	protected static int m_preAlleyId;

	public InboundAllotDaoImpl() {
	}

	protected void copyInPBeanToOutPBean(
			InAllotRequestBean.ProductBean inPBean,
			InAllotResponseBean.ProductBean outPBean) {
		outPBean.strTransId = inPBean.strTransId; // 收货记录ID（跟踪号）
		outPBean.transStatus = inPBean.transStatus; // 要修改的状态
		outPBean.productid = inPBean.productid; // 产品
		outPBean.packid = inPBean.packid; // 包装
		outPBean.eunit = inPBean.eunit; // 单位
		outPBean.putnum = inPBean.putnum; // 上架数量
		outPBean.putweight = inPBean.putnetweight; // 上架重量
		outPBean.putnetweight = inPBean.putnetweight; // 上架净重
		outPBean.putvolume = inPBean.putvolume; // 上架体积
		outPBean.lotid = inPBean.lotid; // 批次类型ID
		outPBean.lotatt1 = inPBean.lotatt1; // 批次属性1
		outPBean.lotatt2 = inPBean.lotatt2; // 批次属性2
		outPBean.lotatt3 = inPBean.lotatt3; // 批次属性3
		outPBean.lotatt4 = inPBean.lotatt4; // 批次属性4
		outPBean.lotatt5 = inPBean.lotatt5; // 批次属性5
		outPBean.lotatt6 = inPBean.lotatt6; // 批次属性6
		outPBean.lotatt7 = inPBean.lotatt7; // 批次属性7
		outPBean.lotatt8 = inPBean.lotatt8; // 批次属性8
		outPBean.lotatt9 = inPBean.lotatt9; // 批次属性9
		outPBean.lotatt10 = inPBean.lotatt10; // 批次属性10
		outPBean.lotatt11 = inPBean.lotatt11; // 批次属性11
		outPBean.lotatt12 = inPBean.lotatt12; // 批次属性12

		outPBean.ownerid = inPBean.ownerid; // 货主
		outPBean.reinvoiceid = inPBean.reinvoiceid; // 收货单据编号
		outPBean.reinvoicedetailid = inPBean.reinvoicedetailid; // 收货单详细ID
	}

	protected void updateCsStatus(BaseCargospace cs, EntityDAO dao)
			throws Exception {
		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		cs.setCsstatus("3");
		iCs.updateCargospace(cs);

	}

	protected void updatePalletCsStatus(BaseCargospace cs, EntityDAO dao)
			throws Exception {
		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		cs.setHaspalletnum(cs.getHaspalletnum() + 1);
		cs.setCsstatus("3");
		iCs.updateCargospace(cs);

	}

	protected InAllotResponseBean makeBPResponse(BaseCargospace cs,
			InAllotRequestBean reqBean, String productid, double pNum) {
		InAllotResponseBean respBean = new InAllotResponseBean();// 已分配返回对象

		respBean.cargoSpaceId = cs.getCargoSpaceId();
		respBean.csplatoon = cs.getCsplatoon();
		respBean.cscolumn = cs.getCscolumn();
		respBean.warehouseid = cs.getWarehouseid();
		respBean.whAreaId = cs.getWhAreaId();
		respBean.cargoAlleyId = cs.getCargoAlleyId();
		respBean.oldstatus = cs.getCsstatus();
		respBean.trayCode = reqBean.trayCode;

		List<InAllotRequestBean.ProductBean> ls = reqBean.getListProductBean();

		InAllotRequestBean.ProductBean inPBean = null;
		for (int i = 0; i < ls.size(); i++) {
			inPBean = ls.get(i);
			if (inPBean.productid.equals(productid)) {
				inPBean.putnum = pNum;
				break;
			}
		}

		InAllotResponseBean.ProductBean outPBean = respBean.getNewProductBean();

		copyInPBeanToOutPBean(inPBean, outPBean);
		respBean.lsProductBean.add(outPBean);

		return respBean;
	}

	protected InAllotResponseBean makePalletResponse(BaseCargospace cs,
			InAllotRequestBean reqBean, double pNum) {
		InAllotResponseBean respBean = new InAllotResponseBean();// 已分配返回对象

		respBean.cargoSpaceId = cs.getCargoSpaceId();
		respBean.csplatoon = cs.getCsplatoon();
		respBean.cscolumn = cs.getCscolumn();
		respBean.warehouseid = cs.getWarehouseid();
		respBean.whAreaId = cs.getWhAreaId();
		respBean.cargoAlleyId = cs.getCargoAlleyId();
		respBean.oldstatus = cs.getCsstatus();
		respBean.trayCode = reqBean.trayCode;

		if (reqBean.iTrays == 1) {
			List<InAllotRequestBean.ProductBean> ls = reqBean
					.getListProductBean();
			for (int i = 0; i < ls.size(); i++) {
				InAllotResponseBean.ProductBean outPBean = respBean
						.getNewProductBean();
				InAllotRequestBean.ProductBean inPBean = ls.get(i);
				copyInPBeanToOutPBean(inPBean, outPBean);
				respBean.lsProductBean.add(outPBean);
			}
		} else {// 批量分配
			List<InAllotRequestBean.ProductBean> ls = reqBean
					.getListProductBean();
			InAllotRequestBean.ProductBean inPBean = ls.get(0);
			inPBean.putnum = pNum;
			InAllotResponseBean.ProductBean outPBean = respBean
					.getNewProductBean();
			copyInPBeanToOutPBean(inPBean, outPBean);
			respBean.lsProductBean.add(outPBean);
		}

		return respBean;
	}

	public boolean compareBatch(RulePutawayDetail rPutawayDetail,
			InAllotRequestBean.ProductBean prtBean) {
		boolean bVal = false;

		if (rPutawayDetail.getLotid().equals(prtBean.getLotid())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt1())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt2())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt3())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt4())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt5())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt6())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt7())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt8())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt9())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt10())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt11())
				&& rPutawayDetail.getLotatt1().equals(prtBean.getLotatt12()))

			return true;

		return bVal;
	}

	/**
	 * 得到存储类型
	 * 
	 * @param pUnit
	 * @param stgType1
	 * @param stgType2
	 * @param stgType3
	 * @param stgType4
	 * @param stgType5
	 * @return
	 */
	protected String verifyStgTypes(String pUnit, String stgType1,
			String stgType2, String stgType3, String stgType4, String stgType5) {
		String strVR = null;
		if (stgType1.equals("5") || stgType2.equals("5")
				|| stgType3.equals("5") || stgType4.equals("5")
				|| stgType5.equals("5")) {
			strVR = "'1','2','3','4','5'";

			return strVR;
		}

		if (stgType1.equals(pUnit)) {
			strVR += "'" + stgType1 + "',";
		}
		if (stgType2.equals(pUnit)) {
			strVR += "'" + stgType2 + "',";
		}
		if (stgType3.equals(pUnit)) {
			strVR += "'" + stgType3 + "',";
		}
		if (stgType4.equals(pUnit)) {
			strVR += "'" + stgType4 + "'";
		}
		if (stgType5.equals(pUnit)) {
			strVR += "'" + stgType5 + "'";
		}

		return strVR;
	}

	/**
	 * 根据货位与产品判断可以存放产品的数量
	 * 
	 * @param productId
	 * @param cargoSpaceId
	 * @return
	 */
	protected double getPStgNumFromCs(BaseProduct prdt, BaseCargospace cs,
			EntityDAO dao) throws Exception {
		// 产品属性
		double dLength = prdt.getLength();
		double dWidth = prdt.getWidth();
		double dHeight = prdt.getHeight();
		double dVulume = dLength * dWidth * dHeight;
		double dWeight = prdt.getWeight();

		if (dVulume * dWeight == 0)
			return 0;

		// 货位属性
		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		double useedVolume = iCs.getSpaceUseVolume(cs.getCargoSpaceId());
		double useedWeight = iCs.getSpaceUseWeight(cs.getCargoSpaceId());
		double csVolume = cs.getLength() * cs.getWidth() * cs.getHeight();
		double csWeight = cs.getLoadweight();

		double remainVolume = csVolume - useedVolume;
		double remainWeight = csWeight - useedWeight;

		// 按体积可放数量
		double remainNum = 0;

		double remainNum1 = remainVolume / dVulume;

		// 按重量可放数量
		double remainNum2 = remainWeight / dWeight;

		if (remainNum1 > remainNum2)
			remainNum = remainNum2;
		else
			remainNum = remainNum1;

		return remainNum;
	}

	/**
	 * 根据产品Id判断货位上是否存在产品混放
	 * 
	 * @param productId
	 * @param mixProduct
	 *            Y-允许混放 N-不允许混放
	 * @param CargoSpaceId
	 * @return
	 * @throws Exception
	 */
	protected boolean getCsProductTypes(String productId, String mixProduct,
			String cargoSpaceId, EntityDAO dao) throws Exception {
		if (mixProduct.equals("N")) {
			String strSql = "from InventoryStorage inv where inv.productid !='"
					+ productId + "' and inv.cargoSpaceId='" + cargoSpaceId
					+ "' and (inv.pnum > 0 or inv.holdnum > 0) ";

			List ls = dao.searchEntities(strSql, 0, 1);
			if (ls != null && ls.size() > 0)
				return false;
			else
				return true;
		}

		return true;
	}

	/**
	 * 根据产品批次判断货位上是否存在产品批次混放
	 * 
	 * @param productId
	 * @param batchid
	 * @param mixProduct
	 * @param CargoSpaceId
	 * @return
	 * @throws Exception
	 */
	protected boolean getCsBatchTypes(String productId, String batchid,
			String mixProduct, String cargoSpaceId, EntityDAO dao)
			throws Exception {

		if (mixProduct.equals("N")) {
			String strSql = "from InventoryStorage inv where inv.productid !='"
					+ productId + "' and inv.cargoSpaceId='" + cargoSpaceId
					+ "' and inv.lotid !='" + batchid
					+ "' and (inv.pnum > 0 or inv.holdnum > 0)";

			List ls = dao.searchEntities(strSql, 0, 1);
			if (ls != null && ls.size() > 0)
				return false;
			else
				return true;
		}

		return true;

	}

	/**
	 * 指定库区或巷道分配满足数量的托盘货位
	 * 
	 * @param trays
	 *            托盘数
	 * @param prdt
	 *            产品对象
	 * @param pNum
	 *            产品数量
	 * @param pUnit
	 *            最小单位
	 * @param pNumPallet
	 *            每托盘可放最小产品数量
	 * @param reqBean
	 * @param whAreaId
	 * @param alleyId
	 * @return
	 */
	protected List<InAllotResponseBean> getNeedPalletCsFromWhArea(int trays,
			BaseProduct prdt, double pNum, String pUnit, double pNumPallet,
			InAllotRequestBean reqBean, String whAreaId, String alleyId,
			EntityDAO dao) throws Exception {
		double alotNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象

		String strSql = "from BaseCargospace cs where cs.inlock='N' and (cs.csstatus='1' or ((cs.palletnum >= 1 or cs.palletnum = -1) and cs.palletnum > haspalletnum))";

		if (whAreaId != null && whAreaId.length() > 1) {
			strSql += "  and cs.whAreaId='" + whAreaId + "'";
		}

		if (alleyId != null && alleyId.length() > 1) {
			strSql += "  and cs.cargoAlleyId='" + alleyId + "'";
		}

		IBaseWhAreaDao whAreaDao = new BaseWhAreaDaoImpl(dao);
		BaseWharea whArea = whAreaDao.getWhareaById(whAreaId);
		String isAutoWh = "Y";// 是否立体库区
		if (!whArea.getwhAreaType().equals("2"))
			isAutoWh = "N";

		List<BaseCargospace> lsCs = null;
		if (trays == 1) {// 只分配一托盘
			lsCs = dao.searchEntities(strSql);
			if (lsCs == null || lsCs.size() < 1)
				return null;
			else {
				BaseCargospace twinCs = null;
				for (int i = 0; i < lsCs.size(); i++) {
					BaseCargospace cs = (BaseCargospace) lsCs.get(i);
					if (isAutoWh.equals("Y") && cs.getIstwin().equals("Y")) {
						twinCs = getTwinCs(cs, dao);

						if (twinCs == null)
							continue;
					} else {
						twinCs = cs;
					}

					// 验证货位
					if (verifyCsFromTargetCs(prdt, twinCs, pUnit, pNum,
							pNumPallet, dao) == null)
						continue;

					reqBean.iRealTrays = 1;

					// 更新货位状态
					updatePalletCsStatus(twinCs, dao);

					// 组成响应对象
					lsAllot.add(makePalletResponse(twinCs, reqBean, pNum));

					break;
				}

				return lsAllot;
			}

		} else if (trays > 1) {// 批量分配
			lsCs = dao.searchEntities(strSql);
			for (int i = 0; i < lsCs.size(); i++) {
				BaseCargospace cs = (BaseCargospace) lsCs.get(i);

				if (cs.getPalletnum() == -1) {// 无限制存放
					// 验证货位
					if (verifyCsFromTargetCs(prdt, cs, pUnit, pNum, pNumPallet,
							dao) == null)
						continue;

					alotNum += trays;
					reqBean.iRealTrays += trays;
					updatePalletCsStatus(cs, dao);

					// 组成响应对象
					lsAllot.add(makePalletResponse(cs, reqBean, pNumPallet));

					break;

				} else {
					while ((cs.getPalletnum() - cs.getHaspalletnum()) > 0) {
						if ((pNum - alotNum) >= pNumPallet) {

							// 验证货位
							if (verifyCsFromTargetCs(prdt, cs, pUnit, pNum,
									pNumPallet, dao) == null)
								break;

							alotNum += pNumPallet;
							reqBean.iRealTrays += 1;

							updatePalletCsStatus(cs, dao);

							// 组成响应对象
							lsAllot.add(makePalletResponse(cs, reqBean,
									pNumPallet));

						} else {
							alotNum += (pNum - alotNum);
							reqBean.iRealTrays += 1;

							updatePalletCsStatus(cs, dao);

							// 组成响应对象
							lsAllot.add(makePalletResponse(cs, reqBean,
									(pNum - alotNum)));

							break;
						}
					}
				}

				if (alotNum >= pNum)
					break;
			}

			return lsAllot;
		}

		return null;
	}

	/**
	 * 双升位货位分配
	 * 
	 * @param targetCs
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	protected BaseCargospace getTwinCs(BaseCargospace targetCs, EntityDAO dao)
			throws Exception {
		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);

		if (targetCs.getIstwin().equals("Y")) {
			String twinId = targetCs.getTwincsid();

			BaseCargospace tmpCs = iCs.getCargoSpaceById(twinId);

			// 判断是里面的货位还是外面的货位
			if (targetCs.getLocation().equals("1")) {// 里货位
				// 判断外面货位是否为空，如果不为空，则不能分配
				if (tmpCs.getCsstatus().equals("1")) {
					return null;
				}
			} else {// 外货位
				// 判断里货位是否为空，如果为空，则先分配里货位
				if (tmpCs.getCsstatus().equals("1")) {
					targetCs = tmpCs;
				}
			}
		}

		return targetCs;
	}

	/**
	 * 验证指定货位
	 * 
	 * @param prdt
	 * @param targetCs
	 * @param putUnit
	 * @param pnum
	 * @param pkgNum
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	protected BaseCargospace verifyCsFromTargetCs(BaseProduct prdt,
			BaseCargospace targetCs, String putUnit, double pnum,
			double pkgNum, EntityDAO dao) throws Exception {
		// 处理过程
		// (1)如果货位有货，则判断货位是否一次入库分配或多次入库分配
		// (2)根据入库的单位，判断货位的存储类型(托盘、箱、件、其他)是否满足
		// (3)判断是否允许产品混放、批次混放
		// (4)根据货位空间与重量，判断是否可以满足要求
		String mixProduct = prdt.getIsproductmixed();// 产品混合
		String mixBatch = prdt.getIsbatchmixed();// 批次混合
		String batchid = prdt.getLotid();// 批次

		try {
			// 如果货位有货，判断货位是一次入库分配，则不能分配此货位
			if (!targetCs.getCsstatus().equals("1")
					&& !targetCs.getPuttype().equals("2")) {
				return null;
			}
			// 如果根据入库的单位与货位的存储类型(托盘、箱、件、其他)不一致，则不能分配此货位
			else if ((!putUnit.equals("PL") || !targetCs.getStoragetype()
					.equals("1"))
					&& (!putUnit.equals("CS") || (!targetCs.getStoragetype()
							.equals("2") && !targetCs.getStoragetype().equals(
							"4")))
					&& (!putUnit.equals("EA") || (!targetCs.getStoragetype()
							.equals("3") && !targetCs.getStoragetype().equals(
							"4"))) && !targetCs.getStoragetype().equals("5")) {
				return null;
			}
			// 判断是否允许产品混放
			else if (!getCsProductTypes(prdt.getProductid(), mixProduct,
					targetCs.getCargoSpaceId(), dao)) {
				return null;
			}
			// 判断是否批次混放
			else if (!getCsBatchTypes(prdt.getProductid(), batchid, mixBatch,
					targetCs.getCargoSpaceId(), dao)) {
				return null;
			}
			// 根据货位空间、重量，判断是否可以满足要求
			else if (putUnit.equals("PL")) {
				if (!targetCs.getCsstatus().equals("1")
						&& !(targetCs.getPalletnum() > targetCs
								.getHaspalletnum())) {
					return null;
				}
			} else {
				ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);

				double useVolume = iCs.getSpaceUseVolume(targetCs
						.getCargoSpaceId());
				double useWeight = iCs.getSpaceUseWeight(targetCs
						.getCargoSpaceId());

				double dLength = prdt.getLength();
				double dWidth = prdt.getWidth();
				double dHeight = prdt.getHeight();

				double dWeight = prdt.getWeight();

				if (dLength * dWidth * dHeight <= 0) {// 产品里没有规格或可无限制堆放
					return targetCs;
				}

				// double needVolume = dLength * dWidth * dLength;
				// double needWeight = dWeight;

				// 剩余空间不足放入一件产品
				if ((dLength * dWidth * dLength) > (targetCs.getLength()
						* targetCs.getWidth() * targetCs.getHeight())
						- useVolume) {
					return null;
				}
				// 剩余重量不足放入一件产品
				else if (dWeight > (targetCs.getLoadweight() - useWeight)) {
					return null;
				}
			}

			return targetCs;
		} catch (Exception er) {
			throw new Exception("指定目标库位(" + targetCs.getCargoSpaceId()
					+ ")分配出错：" + er.getMessage());
		}
	}

	/**
	 * 从目标库区分配货位
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected List<InAllotResponseBean> getCsFromTargetArea(
			Hashtable<String, Object> param) throws Exception {
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String whId = null;
		String targetWhareaId = null;// 上架库区
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			if (param.get("target.whareaId") == null)
				return null;
			else
				targetWhareaId = (String) param.get("target.whareaId");
			whId = (String) param.get("target.warehouseid");
		} else {
			whId = reqBean.warehouseid;
		}

		String putUnit = reqBean.getPutmode();// 上架单位
		String customerid = prtBean.getOwnerid();// 客户
		double pnum = prtBean.getPutnum();// 产品数量
		String preUsed = (String) param.get("preUsed");// 优先利用已用货位

		double allotedNum = 0;// 已分配数量
		List<InAllotResponseBean> lsAllot = new ArrayList();// 已分配返回对象

		// 获取产品包装规则
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}

		try {
			if (putUnit.equals("PL")) {// 上架单位为托
				double pNumPallet = 0;// 每托产品数量
				pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg
						.getPackageid(), "PL")).getQty();

				return getNeedPalletCsFromWhArea(reqBean.iTrays, prdt,
						prtBean.putnum, putUnit, pNumPallet, reqBean,
						targetWhareaId, null, dao);
			} else {// 上架单位为件、箱
				String strSql = null;
				double pNumCS = 0;// 每箱产品数量
				pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(),
						"CS")).getQty();

				if (putUnit.equals("EA"))// 如果是件
					pNumCS = 1;

				if (preUsed.toLowerCase().equals("true")) {// 优先利用已用货位

					// 查询所有非托盘存储货位且为非空货位
					strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.whAreaId='"
							+ targetWhareaId
							+ "' and cs.storagetype!='1' and cs.csstatus !='1'";
					List lsUseCs = dao.searchEntities(strSql);
					for (int i = 0; i < lsUseCs.size(); i++) {
						BaseCargospace cs = (BaseCargospace) lsUseCs.get(i);

						// 验证货位
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// 获取货位数量
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						allotedNum += getNum;
						prtBean.realputnum += getNum;

						// 更新货位状态
						updateCsStatus(cs, dao);

						// 组成响应包
						lsAllot.add(makePalletResponse(cs, reqBean, getNum));

						if (allotedNum >= pnum) {
							break;
						}
					}

					// 比较分配数量与所需分配数量，如果小于，则继续选择空货位分配
					if (allotedNum < pnum) {
						// 查询所有非托盘存储货位且为空货位
						strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.whAreaId='"
								+ targetWhareaId
								+ "' and cs.storagetype!='1' and cs.csstatus ='1'";

						List lsEmptyCs = dao.searchEntities(strSql);
						for (int i = 0; i < lsEmptyCs.size(); i++) {
							BaseCargospace cs = (BaseCargospace) lsUseCs.get(i);

							// 验证货位
							param.put("target.cargoSpace", cs);
							if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
									pNumCS, dao) == null)
								continue;

							// 获取货位数量
							double getNum = getPStgNumFromCs(prdt, cs, dao);
							allotedNum += getNum;
							prtBean.realputnum += getNum;

							// 更新货位状态
							updateCsStatus(cs, dao);

							// 组成响应包
							lsAllot
									.add(makePalletResponse(cs, reqBean, getNum));

							if (allotedNum >= pnum) {
								break;
							}
						}

					}

					return lsAllot;
				} else {// 从库区中选择空货位
					strSql = "from BaseCargospace as cs where cs.inlock='N'   and cs.storagetype!='1' and cs.csstatus ='1' and cs.whAreaId='"
							+ targetWhareaId + "'";
					List lsEmptyCs = dao.searchEntities(strSql);
					for (int i = 0; i < lsEmptyCs.size(); i++) {
						BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(i);

						// 验证货位
						param.put("target.cargoSpace", cs);
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// 获取货位数量
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						if (getNum == 0)
							continue;

						allotedNum += getNum;
						prtBean.realputnum += getNum;

						// 更新货位状态
						updateCsStatus(cs, dao);

						// 组成响应包
						lsAllot.add(makePalletResponse(cs, reqBean, getNum));

						if (allotedNum >= pnum) {
							break;
						}
					}
					return lsAllot;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	protected List<InAllotResponseBean> getCsFromTargetStoragetype(
			Hashtable<String, Object> param) throws Exception {
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String targetWhareaId = null;// 上架库区
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = (String) param.get("target.whareaId");
		}

		String putUnit = reqBean.getPutmode();// 上架单位
		String customerid = prtBean.getOwnerid();// 客户
		double pnum = prtBean.getPutnum();// 产品数量

		String preUsed = (String) param.get("preUsed");// 优先利用已用货位
		String storagetype = (String) param.get("storagetype");

		double allotedNum = 0;// 已分配数量
		List<InAllotResponseBean> lsAllot = new ArrayList();// 已分配返回对象

		try {
			// 获取产品包装规则
			IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
					dao);
			IPackageBus iPkg = new PackageBusImpl(dao);
			BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
			if (pkg == null) {
				ICustomerBus iCustomer = new CustomerBusImpl(dao);
				BaseCustomer customer = iCustomer.getCustomerById(customerid);

				// 获取客户包装规则
				pkg = iPkg.getPackageById(customer.getPakageid());
			}

			double pNumCS = 0;// 每箱产品数量
			pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
					.getQty();

			if (putUnit.equals("EA"))
				pNumCS = 1;

			String strSql = null;
			if (putUnit.equals("PL")) {
				double pNumPallet = 0;// 每托产品数量
				pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg
						.getPackageid(), "PL")).getQty();

				return getNeedPalletCsFromWhArea(reqBean.iTrays, prdt,
						prtBean.putnum, prtBean.eunit, pNumPallet, reqBean,
						targetWhareaId, null, dao);
			} else if (preUsed.toLowerCase().equals("true")) {// 优先利用已用货位
				storagetype = storagetype.replace("'1'", "''");

				// 查询所有非托盘存储货位且为非空货位
				strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.storagetype in ("
						+ storagetype + ") and cs.csstatus !='1')";

				List<BaseCargospace> lsUseCs = dao.searchEntities(strSql);
				for (int i = 0; i < lsUseCs.size(); i++) {
					BaseCargospace cs = (BaseCargospace) lsUseCs.get(0);

					// 验证货位
					param.put("target.cargoSpace", cs);
					if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS,
							dao) == null)
						continue;

					// 获取货位数量
					double getNum = getPStgNumFromCs(prdt, cs, dao);
					allotedNum += getNum;
					prtBean.realputnum += getNum;

					// 更新货位状态
					updateCsStatus(cs, dao);

					// 组成响应包
					lsAllot.add(makePalletResponse(cs, reqBean, getNum));

					if (allotedNum >= pnum) {
						break;
					}
				}

				// 比较分配数量与所需分配数量，如果小于，则继续选择空货位分配
				if (allotedNum < pnum) {
					// 查询所有非托盘存储货位且为空货位
					strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.storagetype in ("
							+ storagetype + ") and cs.csstatus ='1')";

					List<BaseCargospace> lsEmptyCs = dao.searchEntities(strSql);
					for (int i = 0; i < lsEmptyCs.size(); i++) {
						BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(0);

						// 验证货位
						param.put("target.cargoSpace", cs);
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// 获取货位数量
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						allotedNum += getNum;
						prtBean.realputnum += getNum;

						// 更新货位状态
						updateCsStatus(cs, dao);

						// 组成响应包
						lsAllot.add(makePalletResponse(cs, reqBean, getNum));

						if (allotedNum >= pnum) {
							break;
						}
					}

				}

				return lsAllot;

			} else {// 从相应类型货位中选择空货位

				storagetype = storagetype.replace("'1'", "''");

				strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.storagetype in ("
						+ storagetype + ") and cs.csstatus ='1')";

				List<BaseCargospace> lsEmptyCs = dao.searchEntities(strSql);
				for (int i = 0; i < lsEmptyCs.size(); i++) {
					BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(0);

					// 验证货位
					param.put("target.cargoSpace", cs);
					if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS,
							dao) == null)
						continue;

					// 获取货位数量
					double getNum = getPStgNumFromCs(prdt, cs, dao);
					allotedNum += getNum;
					prtBean.realputnum += getNum;

					// 更新货位状态
					updateCsStatus(cs, dao);

					// 组成响应包
					lsAllot.add(makePalletResponse(cs, reqBean, getNum));

					if (allotedNum >= pnum) {
						break;
					}
				}

				return lsAllot;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 按相邻货位或产品属性分配货位
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected List<InAllotResponseBean> getCsFromSameParam(
			Hashtable<String, Object> param) throws Exception {
		// 处理过程
		// (1)搜索库存中相同产品Id|批次的当前库位
		// (2)分析当前库的相邻库位是否为空库位
		// (3)分析库位存储类型是否与上架类型一致
		// (4)计算存储数量是否满足，不满足分配下一个相邻空货位
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");
		String customerid = prtBean.getOwnerid();// 客户

		String sameType = (String) param.get("sameType");
		String targetWhareaId = null;// 上架库区
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = (String) param.get("target.whareaId");
		}
		String putUnit = reqBean.getPutmode();// 上架单位
		double pnum = prtBean.getPutnum();// 产品数量

		// 获取产品包装规则
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		double assignNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象
		List<InventoryStorage> lsCurrCs = null;

		String strSql = null;
		if (sameType.equals("product")) {
			strSql = "from InventoryStorage as ins where  ins.whAreaId='"
					+ targetWhareaId + "'";
			strSql += " and ins.productid='" + prtBean.getProductid() + "'";
		} else {
			strSql = "from InventoryStorage as ins where  ins.whAreaId='"
					+ targetWhareaId + "'";
			strSql += " and ins.productid='" + prtBean.getProductid() + "'";
			strSql += " and ins.batchid='" + prtBean.getLotid() + "'";
		}
		lsCurrCs = dao.searchEntities(strSql);

		for (int i = 0; i < lsCurrCs.size() && lsCurrCs != null; i++) {
			InventoryStorage invStg = lsCurrCs.get(i);
			String csId = invStg.getCargoSpaceId();

			IBaseCargoSpaceDao iCsDao = new BaseCargoSpaceDaoImpl(dao);

			// 上层相邻空货位
			BaseCargospace cs = iCsDao.getNearCargospace(csId, "1");
			if (putUnit.equals("1")) {
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// 更新货位状态
				updatePalletCsStatus(cs, dao);

				// 组成响应对象
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {
				// 验证货位
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// 获取货位数量
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// 更新货位状态
				updateCsStatus(cs, dao);

				// 组成响应包
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			// 下层相邻空货位
			cs = iCsDao.getNearCargospace(csId, "2");
			if (putUnit.equals("1")) {// 托盘
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// 更新货位状态
				updatePalletCsStatus(cs, dao);

				// 组成响应对象
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {// 箱、件
				// 验证货位
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// 获取货位数量
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// 更新货位状态
				updateCsStatus(cs, dao);

				// 组成响应包
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			// 左边相邻空货位
			cs = iCsDao.getNearCargospace(csId, "3");
			if (putUnit.equals("1")) {
				// 验证货位
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// 更新货位状态
				updatePalletCsStatus(cs, dao);

				// 组成响应对象
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {
				// 验证货位
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// 获取货位数量
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// 更新货位状态
				updateCsStatus(cs, dao);

				// 组成响应包
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			// 右边相邻空货位
			cs = iCsDao.getNearCargospace(csId, "4");
			if (putUnit.equals("1")) {
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// 更新货位状态
				updatePalletCsStatus(cs, dao);

				// 组成响应对象
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {
				// 验证货位
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// 获取货位数量
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// 更新货位状态
				updateCsStatus(cs, dao);

				// 组成响应包
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			if (assignNum >= pnum)
				return lsAllot;
		}

		// 从规则库区里的查找空货位
		if (assignNum < pnum) {
			lsAllot.addAll(getCsFromTargetArea(param));
		}

		return lsAllot;
	}

	/**
	 * 获取指定货位
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCsFromTargetCs(Hashtable<String, Object> param)
			throws Exception {
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String customerid = prtBean.getOwnerid();// 客户
		String putUnit = reqBean.getPutmode();// 上架单位
		double pnum = prtBean.getPutnum();// 产品数量
		BaseCargospace targetCs = (BaseCargospace) param
				.get("target.cargoSpace");

		// 获取产品包装规则
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		if (putUnit.equals("PL"))
			return verifyCsFromTargetCs(prdt, targetCs, putUnit, pnum,
					pNumPallet, dao);
		else
			return verifyCsFromTargetCs(prdt, targetCs, putUnit, pnum, pNumCS,
					dao);
	}

	/**
	 * 从[目标库区]中查找合适的库位
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromArea(
			Hashtable<String, Object> param) throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		String targetWhareaId = rPutawayDetail.getTozone();// 目标区域
		param.put("target.whareaId", targetWhareaId);

		return getCsFromTargetArea(param);

	}

	/**
	 * 上架到[目标库位]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromTarget(
			Hashtable<String, Object> param) throws Exception {
		// 获取规则里的目标库位并设置上架库位
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象

		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String putUnit = reqBean.putmode;
		String customerid = prtBean.getOwnerid();// 客户
		String targetWhareaId = rPutawayDetail.getTozone();// 目标区域

		// 获取产品包装规则
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String TargetCsId = rPutawayDetail.getTolocationid();// 目标货位
		String strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.cargoSpaceId='"
				+ TargetCsId + "'";
		List ls = dao.searchEntities(strSql, 0, 1);
		BaseCargospace targetCs = null;
		if (ls != null && ls.size() > 0) {
			targetCs = (BaseCargospace) ls.get(0);
		} else {
			return null;
		}

		if (reqBean.putmode.equals("PL")) {
			if (verifyCsFromTargetCs(prdt, targetCs, reqBean.putmode,
					prtBean.putnum, pNumPallet, dao) == null)
				return null;

			updatePalletCsStatus(targetCs, dao);

			reqBean.iTrays = 1;
			reqBean.iRealTrays = 1;
			lsAllot.add(makePalletResponse(targetCs, reqBean, 0));
		} else {
			if (verifyCsFromTargetCs(prdt, targetCs, reqBean.putmode,
					prtBean.putnum, pNumCS, dao) == null)
				return null;

			// 获取货位数量
			double getNum = getPStgNumFromCs(prdt, targetCs, dao);
			prtBean.realputnum += getNum;

			updateCsStatus(targetCs, dao);

			lsAllot.add(makeBPResponse(targetCs, reqBean, prdt.getProductid(),
					prtBean.putnum));
		}

		return lsAllot;
	}

	/**
	 * 从sku所指定的[上架库区]中查找合适的库位
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromSkuArea(
			Hashtable<String, Object> param) throws Exception {
		// 获取sku里的目标区域并设置目标库区
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		param.put("target.whareaId", prdt.getStorageareaid());

		return getCsFromTargetArea(param);
	}

	/**
	 * 上架到sku所指定的[上架库位]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromSkuTarget(
			Hashtable<String, Object> param) throws Exception {
		// 获取产品指定上架库位
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象

		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String putUnit = reqBean.putmode;
		String customerid = prtBean.getOwnerid();// 客户
		param.put("preUsed", "N");

		// 获取产品包装规则
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String TargetCsId = prdt.getStoragespaceid();// 目标货位
		String strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.cargoSpaceId='"
				+ TargetCsId + "')";
		List ls = dao.searchEntities(strSql, 0, 1);
		BaseCargospace targetCs = null;
		if (ls != null && ls.size() > 0) {
			targetCs = (BaseCargospace) ls.get(0);
			param.put("target.cargoSpace", targetCs);
		} else {
			return null;
		}

		if (reqBean.putmode.equals("PL")) {
			if (verifyCsFromTargetCs(prdt, targetCs, reqBean.putmode,
					prtBean.putnum, pNumPallet, dao) == null)
				return null;

			updatePalletCsStatus(targetCs, dao);

			reqBean.iTrays = 1;
			reqBean.iRealTrays = 1;
			lsAllot.add(makePalletResponse(targetCs, reqBean, 0));
		} else {
			if (verifyCsFromTargetCs(prdt, targetCs, reqBean.putmode,
					prtBean.putnum, pNumCS, dao) == null)
				return null;

			// 获取货位数量
			double getNum = getPStgNumFromCs(prdt, targetCs, dao);
			prtBean.realputnum += getNum;

			updateCsStatus(targetCs, dao);

			lsAllot.add(makeBPResponse(targetCs, reqBean, prdt.getProductid(),
					prtBean.putnum));
		}

		return lsAllot;
	}

	/**
	 * 使用[件拣货库位]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromPieceCs(
			Hashtable<String, Object> param) throws Exception {
		// 处理过程
		// (1)判断货位单位是否为件，如果不为件，则不分配，否则
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		if (!reqBean.putmode.equals("EA"))
			return null;

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", "'3'");

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * 使用[箱拣货库位]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromBoxCs(
			Hashtable<String, Object> param) throws Exception {
		// 处理过程
		// (1)判断货位单位是否为箱，如果不为箱，则不分配，否则
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		if (!reqBean.putmode.equals("箱"))
			return null;

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", "'2'");

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * 使用[件/箱拣货库位]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromPieceBoxCs(
			Hashtable<String, Object> param) throws Exception {
		// 处理过程
		// (1)判断货位单位是否为件或箱，如果不为件或箱，则不分配，否则
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		if (!reqBean.putmode.equals("CS") && !reqBean.putmode.equals("EA"))
			return null;

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", "'4'");

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * 如果批次属性为指定[批次属性]，则上[目标库区]中查找合适的库位
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromBatchToArea(
			Hashtable<String, Object> param) throws Exception {
		// 获取规则里的批次属性值与产品批次属性值比较
		// 如果相同，则获取规则里的上架区
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");

		if (compareBatch(rPutawayDetail, prtBean)) {
			param.put("target.whareaId", rPutawayDetail.getTozone());
		} else {
			return null;
		}

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		return getCsFromTargetArea(param);
	}

	/**
	 * 如果批次属性为指定[批次属性]，则上架到[目标库位]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromBatchToTarget(
			Hashtable<String, Object> param) throws Exception {
		// 获取规则里的批次属性值与产品批次属性值比较
		// 如果相同，则获取规则里的上架库位
		// 获取产品指定上架库位
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String customerid = prtBean.ownerid;
		String putUnit = reqBean.putmode;

		if (!compareBatch(rPutawayDetail, prtBean)) {
			return null;
		}

		// 获取产品包装规则
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String TargetCsId = rPutawayDetail.getTolocationid();// 目标货位
		String strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.cargoSpaceId='"
				+ TargetCsId + "')";
		List ls = dao.searchEntities(strSql, 0, 1);
		BaseCargospace targetCs = null;
		if (ls != null && ls.size() > 0) {
			targetCs = (BaseCargospace) ls.get(0);
			param.put("target.cargoSpace", targetCs);
		} else {
			return null;
		}

		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象

		if (reqBean.putmode.equals("PL")) {
			if (verifyCsFromTargetCs(prdt, targetCs, reqBean.putmode,
					prtBean.putnum, pNumPallet, dao) == null)
				return null;

			updatePalletCsStatus(targetCs, dao);

			reqBean.iTrays = 1;
			reqBean.iRealTrays = 1;
			lsAllot.add(makePalletResponse(targetCs, reqBean, 0));
		} else {
			if (verifyCsFromTargetCs(prdt, targetCs, reqBean.putmode,
					prtBean.putnum, pNumCS, dao) == null)
				return null;

			// 获取货位数量
			double getNum = getPStgNumFromCs(prdt, targetCs, dao);
			prtBean.realputnum += getNum;

			updateCsStatus(targetCs, dao);

			lsAllot.add(makeBPResponse(targetCs, reqBean, prdt.getProductid(),
					prtBean.putnum));
		}

		return lsAllot;
	}

	/**
	 * 查找同名称产品相邻空库位
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromSameProductId(
			Hashtable<String, Object> param) throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		String targetWhareaId = rPutawayDetail.getTozone();// 目标区域
		param.put("target.whareaId", targetWhareaId);
		param.put("sameType", "product");
		return getCsFromSameParam(param);
	}

	/**
	 * 查找同批次产品相邻空库位
	 * 
	 * @param param
	 * @return
	 */
	public List getCsFromSameBatch(Hashtable<String, Object> param)
			throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		String targetWhareaId = rPutawayDetail.getTozone();// 目标区域
		param.put("target.whareaId", targetWhareaId);
		param.put("sameType", "batch");
		return getCsFromSameParam(param);
	}

	/**
	 * 根据存储类型来分配货位
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromStoragetype(
			Hashtable<String, Object> param) throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");

		String storagetype1 = rPutawayDetail.getLoc_handle1();// 存储类型1
		String storagetype2 = rPutawayDetail.getLoc_handle2();// 存储类型2
		String storagetype3 = rPutawayDetail.getLoc_handle3();// 存储类型3
		String storagetype4 = rPutawayDetail.getLoc_handle4();// 存储类型4
		String storagetype5 = rPutawayDetail.getLoc_handle5();// 存储类型5

		// 分配验证
		String putType = "";
		if (reqBean.putmode.equals("PL"))
			putType = "1";
		else if (reqBean.putmode.equals("CS"))
			putType = "2";
		else if (reqBean.putmode.equals("EA"))
			putType = "3";

		String storagetype = verifyStgTypes(putType, storagetype1,
				storagetype2, storagetype3, storagetype4, storagetype5);
		if (storagetype == null)
			return null;

		String preUsed = "N";// 合并优先
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", storagetype);

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * 按产品和日期在各巷道数量均匀分布到每个巷道
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsEvenProductDate(
			Hashtable<String, Object> param) throws Exception {
		// 处理过程
		// (1)根据产品、日期搜索巷道,并根据数量从小到大排序
		// (2)根据这些巷道顺序获取巷道里相应的货位
		// (3)判断上架单位是否为托，如果是如果多次分配，且可以混放，则选择已分配货位
		// (4)计算存储数量是否满足，不满足分配下一个货位
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String whId = null;
		String targetWhareaId = null;// 上架库区
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = rPutawayDetail.getTozone();
			whId = (String) param.get("target.warehouseid");
		} else {
			whId = reqBean.warehouseid;
		}

		String putUnit = reqBean.getPutmode();// 上架单位
		String customerid = prtBean.getOwnerid();// 客户
		String productid = prtBean.getProductid();// 产品
		double pnum = prtBean.getPutnum();// 产品数量
		int palletNum = reqBean.getITrays();// 托盘数
		String printDate = prtBean.getLotatt1();//生产日期
		

		// 获取产品包装规则
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();

		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String strSql = " select  a.cargoAlleyId from BaseAlley as a,  InventoryStorage as s where a.cargoAlleyId = s.cargoAlleyId and a.inlock='N'";
		strSql = strSql + " group by a.cargoAlleyId";
		strSql = strSql + " order by sum(case when s.productid='"
				+ productid + "' and s.indate='" + printDate
				+ "'  then 1 else 0 end), a.cargoAlleyId";

		List<String> lsAlley = dao.searchEntities(strSql);
		if(lsAlley == null || lsAlley.size() < 1)
			return null;
		
		int alleyNum = lsAlley.size();// 巷道数量
		double allotNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象

		if (putUnit.equals("1")) {// 托盘存储
			// 每个巷道分配一个托盘，循环分配，除非巷道没有空货位
			while (allotNum < pnum) {
				for (int i = 0; i < alleyNum; i++) {
					String alleyId = lsAlley.get(i);
					List lsTemp = getNeedPalletCsFromWhArea(palletNum, prdt,
							pnum, putUnit, pNumPallet, reqBean, targetWhareaId,
							alleyId, dao);

					if (lsTemp != null) {
						allotNum++;
						lsAllot.addAll(lsTemp);
					} else {
						alleyNum--;
						lsAlley.remove(i);
					}
				}

				if (lsAlley.size() == 0)
					break;
			}
		} else {

			strSql = "from BaseCargospace as cs where cs.inlock='N'  and cs.csstatus ='1' and cs.whAreaId='"
					+ targetWhareaId
					+ "' and  cs.cargoAlleyId='"
					+ lsAlley.get(0) + "'";
			List<BaseCargospace> lsCs = dao.searchEntities(strSql, 0, 1);

			double csNum = getPStgNumFromCs(prdt, lsCs.get(0), dao);
			double numPerAlley = pnum;

			if (csNum >= pnum)
				alleyNum = 1;
			else {
				numPerAlley = pnum / alleyNum;// 每个巷道数量
			}

			while (allotNum < pnum) {
				for (int i = 0; i < alleyNum; i++) {
					strSql = "from BaseCargospace as cs where cs.inlock='N'  and cs.csstatus ='1' and cs.whAreaId='"
							+ targetWhareaId
							+ "' and  cs.cargoAlleyId='"
							+ lsAlley.get(i) + "'";
					List lsEmptyCs = dao.searchEntities(strSql);
					for (int j = 0; j < lsEmptyCs.size(); j++) {
						BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(j);

						// 验证货位
						param.put("target.cargoSpace", cs);
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// 获取货位数量
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						numPerAlley -= getNum;
						prtBean.realputnum += getNum;
						allotNum += getNum;

						// 更新货位状态
						updateCsStatus(cs, dao);

						// 组成响应包
						lsAllot.add(makePalletResponse(cs, reqBean, getNum));

						if (numPerAlley <= 0)
							break;
					}

					if (numPerAlley <= 0) {
						alleyNum--;
						lsAlley.remove(i);
					}
				}

				if (csNum >= pnum - allotNum)
					alleyNum = 1;
				else {
					numPerAlley = pnum / alleyNum;// 每个巷道数量
				}
			}
		}

		return lsAllot;
	}

	/**
	 * 每个巷道逐个分配，除非巷道锁定
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsAlleyByAlley(
			Hashtable<String, Object> param) throws Exception {
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String whId = null;
		String targetWhareaId = null;// 上架库区
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = rPutawayDetail.getTozone();	
			whId = (String) param.get("target.warehouseid");			
		} else {
			whId = reqBean.warehouseid;
		}

		String putUnit = reqBean.getPutmode();// 上架单位
		String customerid = prtBean.getOwnerid();// 客户
		double pnum = prtBean.getPutnum();// 产品数量
		int palletNum = reqBean.getITrays();// 托盘数

		// 获取产品包装规则
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// 获取客户包装规则
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// 每托产品数量
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// 每箱产品数量
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		double allotNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// 已分配返回对象

		int currAlleyId = m_preAlleyId;
		int alleySize = 0;
		String strSql = "select a.cargoAlleyId from BaseAlley as a ";
		strSql = strSql + " order by a.cargoAlleyId";
		List<String> lsAlley = dao.searchEntities(strSql);
		alleySize = lsAlley.size();
		for (int i = 0; i < alleySize; i++) {
			if (currAlleyId > alleySize)
				currAlleyId = 0;

			currAlleyId++;
			m_preAlleyId = currAlleyId;

			if (putUnit.equals("PL")) {// 托盘存储
				List<InAllotResponseBean> tmpLs = getNeedPalletCsFromWhArea(
						palletNum, prdt, pnum, putUnit, pNumPallet, reqBean,
						targetWhareaId, lsAlley.get(i), dao);
				if (tmpLs != null)
					lsAllot.addAll(tmpLs);

			} else {
				strSql = "from BaseCargospace as cs where cs.inlock='N'  and cs.storagetype !='1' and cs.csstatus ='1' and cs.whAreaId='"
						+ targetWhareaId
						+ "' and  cs.cargoAlleyId='"
						+ lsAlley.get(i) + "'";
				List lsEmptyCs = dao.searchEntities(strSql);
				for (int j = 0; j < lsEmptyCs.size(); j++) {
					BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(j);

					// 验证货位
					param.put("target.cargoSpace", cs);
					if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS,
							dao) == null)
						continue;

					// 获取货位数量
					double getNum = getPStgNumFromCs(prdt, cs, dao);
					
					if(getNum <=0 )//如果货位放不下则清除货位
						lsEmptyCs.remove(j);
					
					allotNum += getNum;
					prtBean.realputnum += getNum;

					// 更新货位状态
					updateCsStatus(cs, dao);

					// 组成响应包
					lsAllot.add(makePalletResponse(cs, reqBean, getNum));

					if (allotNum >= pnum)
						break;
					else if(j == lsEmptyCs.size() -1)
						j = 0;
				}
			}
		}

		return lsAllot;
	}

	/**
	 * 巷道递增分配
	 * 
	 * @param param
	 * @return
	 */
	public List getCsIncrease(Hashtable param) {

		return null;
	}
}
