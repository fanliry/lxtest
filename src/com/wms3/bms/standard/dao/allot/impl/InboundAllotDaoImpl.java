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
 * ������
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
		outPBean.strTransId = inPBean.strTransId; // �ջ���¼ID�����ٺţ�
		outPBean.transStatus = inPBean.transStatus; // Ҫ�޸ĵ�״̬
		outPBean.productid = inPBean.productid; // ��Ʒ
		outPBean.packid = inPBean.packid; // ��װ
		outPBean.eunit = inPBean.eunit; // ��λ
		outPBean.putnum = inPBean.putnum; // �ϼ�����
		outPBean.putweight = inPBean.putnetweight; // �ϼ�����
		outPBean.putnetweight = inPBean.putnetweight; // �ϼܾ���
		outPBean.putvolume = inPBean.putvolume; // �ϼ����
		outPBean.lotid = inPBean.lotid; // ��������ID
		outPBean.lotatt1 = inPBean.lotatt1; // ��������1
		outPBean.lotatt2 = inPBean.lotatt2; // ��������2
		outPBean.lotatt3 = inPBean.lotatt3; // ��������3
		outPBean.lotatt4 = inPBean.lotatt4; // ��������4
		outPBean.lotatt5 = inPBean.lotatt5; // ��������5
		outPBean.lotatt6 = inPBean.lotatt6; // ��������6
		outPBean.lotatt7 = inPBean.lotatt7; // ��������7
		outPBean.lotatt8 = inPBean.lotatt8; // ��������8
		outPBean.lotatt9 = inPBean.lotatt9; // ��������9
		outPBean.lotatt10 = inPBean.lotatt10; // ��������10
		outPBean.lotatt11 = inPBean.lotatt11; // ��������11
		outPBean.lotatt12 = inPBean.lotatt12; // ��������12

		outPBean.ownerid = inPBean.ownerid; // ����
		outPBean.reinvoiceid = inPBean.reinvoiceid; // �ջ����ݱ��
		outPBean.reinvoicedetailid = inPBean.reinvoicedetailid; // �ջ�����ϸID
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
		InAllotResponseBean respBean = new InAllotResponseBean();// �ѷ��䷵�ض���

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
		InAllotResponseBean respBean = new InAllotResponseBean();// �ѷ��䷵�ض���

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
		} else {// ��������
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
	 * �õ��洢����
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
	 * ���ݻ�λ���Ʒ�жϿ��Դ�Ų�Ʒ������
	 * 
	 * @param productId
	 * @param cargoSpaceId
	 * @return
	 */
	protected double getPStgNumFromCs(BaseProduct prdt, BaseCargospace cs,
			EntityDAO dao) throws Exception {
		// ��Ʒ����
		double dLength = prdt.getLength();
		double dWidth = prdt.getWidth();
		double dHeight = prdt.getHeight();
		double dVulume = dLength * dWidth * dHeight;
		double dWeight = prdt.getWeight();

		if (dVulume * dWeight == 0)
			return 0;

		// ��λ����
		ICargoSpaceBus iCs = new CargoSpaceBusImpl(dao);
		double useedVolume = iCs.getSpaceUseVolume(cs.getCargoSpaceId());
		double useedWeight = iCs.getSpaceUseWeight(cs.getCargoSpaceId());
		double csVolume = cs.getLength() * cs.getWidth() * cs.getHeight();
		double csWeight = cs.getLoadweight();

		double remainVolume = csVolume - useedVolume;
		double remainWeight = csWeight - useedWeight;

		// ������ɷ�����
		double remainNum = 0;

		double remainNum1 = remainVolume / dVulume;

		// �������ɷ�����
		double remainNum2 = remainWeight / dWeight;

		if (remainNum1 > remainNum2)
			remainNum = remainNum2;
		else
			remainNum = remainNum1;

		return remainNum;
	}

	/**
	 * ���ݲ�ƷId�жϻ�λ���Ƿ���ڲ�Ʒ���
	 * 
	 * @param productId
	 * @param mixProduct
	 *            Y-������ N-��������
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
	 * ���ݲ�Ʒ�����жϻ�λ���Ƿ���ڲ�Ʒ���λ��
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
	 * ָ����������������������������̻�λ
	 * 
	 * @param trays
	 *            ������
	 * @param prdt
	 *            ��Ʒ����
	 * @param pNum
	 *            ��Ʒ����
	 * @param pUnit
	 *            ��С��λ
	 * @param pNumPallet
	 *            ÿ���̿ɷ���С��Ʒ����
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
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���

		String strSql = "from BaseCargospace cs where cs.inlock='N' and (cs.csstatus='1' or ((cs.palletnum >= 1 or cs.palletnum = -1) and cs.palletnum > haspalletnum))";

		if (whAreaId != null && whAreaId.length() > 1) {
			strSql += "  and cs.whAreaId='" + whAreaId + "'";
		}

		if (alleyId != null && alleyId.length() > 1) {
			strSql += "  and cs.cargoAlleyId='" + alleyId + "'";
		}

		IBaseWhAreaDao whAreaDao = new BaseWhAreaDaoImpl(dao);
		BaseWharea whArea = whAreaDao.getWhareaById(whAreaId);
		String isAutoWh = "Y";// �Ƿ��������
		if (!whArea.getwhAreaType().equals("2"))
			isAutoWh = "N";

		List<BaseCargospace> lsCs = null;
		if (trays == 1) {// ֻ����һ����
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

					// ��֤��λ
					if (verifyCsFromTargetCs(prdt, twinCs, pUnit, pNum,
							pNumPallet, dao) == null)
						continue;

					reqBean.iRealTrays = 1;

					// ���»�λ״̬
					updatePalletCsStatus(twinCs, dao);

					// �����Ӧ����
					lsAllot.add(makePalletResponse(twinCs, reqBean, pNum));

					break;
				}

				return lsAllot;
			}

		} else if (trays > 1) {// ��������
			lsCs = dao.searchEntities(strSql);
			for (int i = 0; i < lsCs.size(); i++) {
				BaseCargospace cs = (BaseCargospace) lsCs.get(i);

				if (cs.getPalletnum() == -1) {// �����ƴ��
					// ��֤��λ
					if (verifyCsFromTargetCs(prdt, cs, pUnit, pNum, pNumPallet,
							dao) == null)
						continue;

					alotNum += trays;
					reqBean.iRealTrays += trays;
					updatePalletCsStatus(cs, dao);

					// �����Ӧ����
					lsAllot.add(makePalletResponse(cs, reqBean, pNumPallet));

					break;

				} else {
					while ((cs.getPalletnum() - cs.getHaspalletnum()) > 0) {
						if ((pNum - alotNum) >= pNumPallet) {

							// ��֤��λ
							if (verifyCsFromTargetCs(prdt, cs, pUnit, pNum,
									pNumPallet, dao) == null)
								break;

							alotNum += pNumPallet;
							reqBean.iRealTrays += 1;

							updatePalletCsStatus(cs, dao);

							// �����Ӧ����
							lsAllot.add(makePalletResponse(cs, reqBean,
									pNumPallet));

						} else {
							alotNum += (pNum - alotNum);
							reqBean.iRealTrays += 1;

							updatePalletCsStatus(cs, dao);

							// �����Ӧ����
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
	 * ˫��λ��λ����
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

			// �ж�������Ļ�λ��������Ļ�λ
			if (targetCs.getLocation().equals("1")) {// ���λ
				// �ж������λ�Ƿ�Ϊ�գ������Ϊ�գ����ܷ���
				if (tmpCs.getCsstatus().equals("1")) {
					return null;
				}
			} else {// ���λ
				// �ж����λ�Ƿ�Ϊ�գ����Ϊ�գ����ȷ������λ
				if (tmpCs.getCsstatus().equals("1")) {
					targetCs = tmpCs;
				}
			}
		}

		return targetCs;
	}

	/**
	 * ��ָ֤����λ
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
		// �������
		// (1)�����λ�л������жϻ�λ�Ƿ�һ�����������������
		// (2)�������ĵ�λ���жϻ�λ�Ĵ洢����(���̡��䡢��������)�Ƿ�����
		// (3)�ж��Ƿ������Ʒ��š����λ��
		// (4)���ݻ�λ�ռ����������ж��Ƿ��������Ҫ��
		String mixProduct = prdt.getIsproductmixed();// ��Ʒ���
		String mixBatch = prdt.getIsbatchmixed();// ���λ��
		String batchid = prdt.getLotid();// ����

		try {
			// �����λ�л����жϻ�λ��һ�������䣬���ܷ���˻�λ
			if (!targetCs.getCsstatus().equals("1")
					&& !targetCs.getPuttype().equals("2")) {
				return null;
			}
			// ����������ĵ�λ���λ�Ĵ洢����(���̡��䡢��������)��һ�£����ܷ���˻�λ
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
			// �ж��Ƿ������Ʒ���
			else if (!getCsProductTypes(prdt.getProductid(), mixProduct,
					targetCs.getCargoSpaceId(), dao)) {
				return null;
			}
			// �ж��Ƿ����λ��
			else if (!getCsBatchTypes(prdt.getProductid(), batchid, mixBatch,
					targetCs.getCargoSpaceId(), dao)) {
				return null;
			}
			// ���ݻ�λ�ռ䡢�������ж��Ƿ��������Ҫ��
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

				if (dLength * dWidth * dHeight <= 0) {// ��Ʒ��û�й���������ƶѷ�
					return targetCs;
				}

				// double needVolume = dLength * dWidth * dLength;
				// double needWeight = dWeight;

				// ʣ��ռ䲻�����һ����Ʒ
				if ((dLength * dWidth * dLength) > (targetCs.getLength()
						* targetCs.getWidth() * targetCs.getHeight())
						- useVolume) {
					return null;
				}
				// ʣ�������������һ����Ʒ
				else if (dWeight > (targetCs.getLoadweight() - useWeight)) {
					return null;
				}
			}

			return targetCs;
		} catch (Exception er) {
			throw new Exception("ָ��Ŀ���λ(" + targetCs.getCargoSpaceId()
					+ ")�������" + er.getMessage());
		}
	}

	/**
	 * ��Ŀ����������λ
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
		String targetWhareaId = null;// �ϼܿ���
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

		String putUnit = reqBean.getPutmode();// �ϼܵ�λ
		String customerid = prtBean.getOwnerid();// �ͻ�
		double pnum = prtBean.getPutnum();// ��Ʒ����
		String preUsed = (String) param.get("preUsed");// �����������û�λ

		double allotedNum = 0;// �ѷ�������
		List<InAllotResponseBean> lsAllot = new ArrayList();// �ѷ��䷵�ض���

		// ��ȡ��Ʒ��װ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}

		try {
			if (putUnit.equals("PL")) {// �ϼܵ�λΪ��
				double pNumPallet = 0;// ÿ�в�Ʒ����
				pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg
						.getPackageid(), "PL")).getQty();

				return getNeedPalletCsFromWhArea(reqBean.iTrays, prdt,
						prtBean.putnum, putUnit, pNumPallet, reqBean,
						targetWhareaId, null, dao);
			} else {// �ϼܵ�λΪ������
				String strSql = null;
				double pNumCS = 0;// ÿ���Ʒ����
				pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(),
						"CS")).getQty();

				if (putUnit.equals("EA"))// ����Ǽ�
					pNumCS = 1;

				if (preUsed.toLowerCase().equals("true")) {// �����������û�λ

					// ��ѯ���з����̴洢��λ��Ϊ�ǿջ�λ
					strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.whAreaId='"
							+ targetWhareaId
							+ "' and cs.storagetype!='1' and cs.csstatus !='1'";
					List lsUseCs = dao.searchEntities(strSql);
					for (int i = 0; i < lsUseCs.size(); i++) {
						BaseCargospace cs = (BaseCargospace) lsUseCs.get(i);

						// ��֤��λ
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// ��ȡ��λ����
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						allotedNum += getNum;
						prtBean.realputnum += getNum;

						// ���»�λ״̬
						updateCsStatus(cs, dao);

						// �����Ӧ��
						lsAllot.add(makePalletResponse(cs, reqBean, getNum));

						if (allotedNum >= pnum) {
							break;
						}
					}

					// �ȽϷ�������������������������С�ڣ������ѡ��ջ�λ����
					if (allotedNum < pnum) {
						// ��ѯ���з����̴洢��λ��Ϊ�ջ�λ
						strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.whAreaId='"
								+ targetWhareaId
								+ "' and cs.storagetype!='1' and cs.csstatus ='1'";

						List lsEmptyCs = dao.searchEntities(strSql);
						for (int i = 0; i < lsEmptyCs.size(); i++) {
							BaseCargospace cs = (BaseCargospace) lsUseCs.get(i);

							// ��֤��λ
							param.put("target.cargoSpace", cs);
							if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
									pNumCS, dao) == null)
								continue;

							// ��ȡ��λ����
							double getNum = getPStgNumFromCs(prdt, cs, dao);
							allotedNum += getNum;
							prtBean.realputnum += getNum;

							// ���»�λ״̬
							updateCsStatus(cs, dao);

							// �����Ӧ��
							lsAllot
									.add(makePalletResponse(cs, reqBean, getNum));

							if (allotedNum >= pnum) {
								break;
							}
						}

					}

					return lsAllot;
				} else {// �ӿ�����ѡ��ջ�λ
					strSql = "from BaseCargospace as cs where cs.inlock='N'   and cs.storagetype!='1' and cs.csstatus ='1' and cs.whAreaId='"
							+ targetWhareaId + "'";
					List lsEmptyCs = dao.searchEntities(strSql);
					for (int i = 0; i < lsEmptyCs.size(); i++) {
						BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(i);

						// ��֤��λ
						param.put("target.cargoSpace", cs);
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// ��ȡ��λ����
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						if (getNum == 0)
							continue;

						allotedNum += getNum;
						prtBean.realputnum += getNum;

						// ���»�λ״̬
						updateCsStatus(cs, dao);

						// �����Ӧ��
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

		String targetWhareaId = null;// �ϼܿ���
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = (String) param.get("target.whareaId");
		}

		String putUnit = reqBean.getPutmode();// �ϼܵ�λ
		String customerid = prtBean.getOwnerid();// �ͻ�
		double pnum = prtBean.getPutnum();// ��Ʒ����

		String preUsed = (String) param.get("preUsed");// �����������û�λ
		String storagetype = (String) param.get("storagetype");

		double allotedNum = 0;// �ѷ�������
		List<InAllotResponseBean> lsAllot = new ArrayList();// �ѷ��䷵�ض���

		try {
			// ��ȡ��Ʒ��װ����
			IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
					dao);
			IPackageBus iPkg = new PackageBusImpl(dao);
			BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
			if (pkg == null) {
				ICustomerBus iCustomer = new CustomerBusImpl(dao);
				BaseCustomer customer = iCustomer.getCustomerById(customerid);

				// ��ȡ�ͻ���װ����
				pkg = iPkg.getPackageById(customer.getPakageid());
			}

			double pNumCS = 0;// ÿ���Ʒ����
			pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
					.getQty();

			if (putUnit.equals("EA"))
				pNumCS = 1;

			String strSql = null;
			if (putUnit.equals("PL")) {
				double pNumPallet = 0;// ÿ�в�Ʒ����
				pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg
						.getPackageid(), "PL")).getQty();

				return getNeedPalletCsFromWhArea(reqBean.iTrays, prdt,
						prtBean.putnum, prtBean.eunit, pNumPallet, reqBean,
						targetWhareaId, null, dao);
			} else if (preUsed.toLowerCase().equals("true")) {// �����������û�λ
				storagetype = storagetype.replace("'1'", "''");

				// ��ѯ���з����̴洢��λ��Ϊ�ǿջ�λ
				strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.storagetype in ("
						+ storagetype + ") and cs.csstatus !='1')";

				List<BaseCargospace> lsUseCs = dao.searchEntities(strSql);
				for (int i = 0; i < lsUseCs.size(); i++) {
					BaseCargospace cs = (BaseCargospace) lsUseCs.get(0);

					// ��֤��λ
					param.put("target.cargoSpace", cs);
					if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS,
							dao) == null)
						continue;

					// ��ȡ��λ����
					double getNum = getPStgNumFromCs(prdt, cs, dao);
					allotedNum += getNum;
					prtBean.realputnum += getNum;

					// ���»�λ״̬
					updateCsStatus(cs, dao);

					// �����Ӧ��
					lsAllot.add(makePalletResponse(cs, reqBean, getNum));

					if (allotedNum >= pnum) {
						break;
					}
				}

				// �ȽϷ�������������������������С�ڣ������ѡ��ջ�λ����
				if (allotedNum < pnum) {
					// ��ѯ���з����̴洢��λ��Ϊ�ջ�λ
					strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.storagetype in ("
							+ storagetype + ") and cs.csstatus ='1')";

					List<BaseCargospace> lsEmptyCs = dao.searchEntities(strSql);
					for (int i = 0; i < lsEmptyCs.size(); i++) {
						BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(0);

						// ��֤��λ
						param.put("target.cargoSpace", cs);
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// ��ȡ��λ����
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						allotedNum += getNum;
						prtBean.realputnum += getNum;

						// ���»�λ״̬
						updateCsStatus(cs, dao);

						// �����Ӧ��
						lsAllot.add(makePalletResponse(cs, reqBean, getNum));

						if (allotedNum >= pnum) {
							break;
						}
					}

				}

				return lsAllot;

			} else {// ����Ӧ���ͻ�λ��ѡ��ջ�λ

				storagetype = storagetype.replace("'1'", "''");

				strSql = "from BaseCargospace as cs where cs.inlock='N' and cs.storagetype in ("
						+ storagetype + ") and cs.csstatus ='1')";

				List<BaseCargospace> lsEmptyCs = dao.searchEntities(strSql);
				for (int i = 0; i < lsEmptyCs.size(); i++) {
					BaseCargospace cs = (BaseCargospace) lsEmptyCs.get(0);

					// ��֤��λ
					param.put("target.cargoSpace", cs);
					if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS,
							dao) == null)
						continue;

					// ��ȡ��λ����
					double getNum = getPStgNumFromCs(prdt, cs, dao);
					allotedNum += getNum;
					prtBean.realputnum += getNum;

					// ���»�λ״̬
					updateCsStatus(cs, dao);

					// �����Ӧ��
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
	 * �����ڻ�λ���Ʒ���Է����λ
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected List<InAllotResponseBean> getCsFromSameParam(
			Hashtable<String, Object> param) throws Exception {
		// �������
		// (1)�����������ͬ��ƷId|���εĵ�ǰ��λ
		// (2)������ǰ������ڿ�λ�Ƿ�Ϊ�տ�λ
		// (3)������λ�洢�����Ƿ����ϼ�����һ��
		// (4)����洢�����Ƿ����㣬�����������һ�����ڿջ�λ
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");
		String customerid = prtBean.getOwnerid();// �ͻ�

		String sameType = (String) param.get("sameType");
		String targetWhareaId = null;// �ϼܿ���
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = (String) param.get("target.whareaId");
		}
		String putUnit = reqBean.getPutmode();// �ϼܵ�λ
		double pnum = prtBean.getPutnum();// ��Ʒ����

		// ��ȡ��Ʒ��װ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// ÿ���Ʒ����
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		double assignNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���
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

			// �ϲ����ڿջ�λ
			BaseCargospace cs = iCsDao.getNearCargospace(csId, "1");
			if (putUnit.equals("1")) {
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// ���»�λ״̬
				updatePalletCsStatus(cs, dao);

				// �����Ӧ����
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {
				// ��֤��λ
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// ��ȡ��λ����
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// ���»�λ״̬
				updateCsStatus(cs, dao);

				// �����Ӧ��
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			// �²����ڿջ�λ
			cs = iCsDao.getNearCargospace(csId, "2");
			if (putUnit.equals("1")) {// ����
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// ���»�λ״̬
				updatePalletCsStatus(cs, dao);

				// �����Ӧ����
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {// �䡢��
				// ��֤��λ
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// ��ȡ��λ����
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// ���»�λ״̬
				updateCsStatus(cs, dao);

				// �����Ӧ��
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			// ������ڿջ�λ
			cs = iCsDao.getNearCargospace(csId, "3");
			if (putUnit.equals("1")) {
				// ��֤��λ
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// ���»�λ״̬
				updatePalletCsStatus(cs, dao);

				// �����Ӧ����
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {
				// ��֤��λ
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// ��ȡ��λ����
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// ���»�λ״̬
				updateCsStatus(cs, dao);

				// �����Ӧ��
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			// �ұ����ڿջ�λ
			cs = iCsDao.getNearCargospace(csId, "4");
			if (putUnit.equals("1")) {
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumPallet,
						dao) == null)
					return null;

				assignNum += 1;

				// ���»�λ״̬
				updatePalletCsStatus(cs, dao);

				// �����Ӧ����
				lsAllot.add(makePalletResponse(cs, reqBean, 0));
			} else {
				// ��֤��λ
				if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS, dao) == null)
					continue;

				// ��ȡ��λ����
				double dRet = getPStgNumFromCs(prdt, cs, dao);
				assignNum += dRet;
				prtBean.realputnum += dRet;

				// ���»�λ״̬
				updateCsStatus(cs, dao);

				// �����Ӧ��
				lsAllot.add(makePalletResponse(cs, reqBean, dRet));
			}

			if (assignNum >= pnum)
				return lsAllot;
		}

		// �ӹ��������Ĳ��ҿջ�λ
		if (assignNum < pnum) {
			lsAllot.addAll(getCsFromTargetArea(param));
		}

		return lsAllot;
	}

	/**
	 * ��ȡָ����λ
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

		String customerid = prtBean.getOwnerid();// �ͻ�
		String putUnit = reqBean.getPutmode();// �ϼܵ�λ
		double pnum = prtBean.getPutnum();// ��Ʒ����
		BaseCargospace targetCs = (BaseCargospace) param
				.get("target.cargoSpace");

		// ��ȡ��Ʒ��װ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// ÿ���Ʒ����
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
	 * ��[Ŀ�����]�в��Һ��ʵĿ�λ
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromArea(
			Hashtable<String, Object> param) throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		String targetWhareaId = rPutawayDetail.getTozone();// Ŀ������
		param.put("target.whareaId", targetWhareaId);

		return getCsFromTargetArea(param);

	}

	/**
	 * �ϼܵ�[Ŀ���λ]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromTarget(
			Hashtable<String, Object> param) throws Exception {
		// ��ȡ�������Ŀ���λ�������ϼܿ�λ
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���

		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String putUnit = reqBean.putmode;
		String customerid = prtBean.getOwnerid();// �ͻ�
		String targetWhareaId = rPutawayDetail.getTozone();// Ŀ������

		// ��ȡ��Ʒ��װ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// ÿ���Ʒ����
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String TargetCsId = rPutawayDetail.getTolocationid();// Ŀ���λ
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

			// ��ȡ��λ����
			double getNum = getPStgNumFromCs(prdt, targetCs, dao);
			prtBean.realputnum += getNum;

			updateCsStatus(targetCs, dao);

			lsAllot.add(makeBPResponse(targetCs, reqBean, prdt.getProductid(),
					prtBean.putnum));
		}

		return lsAllot;
	}

	/**
	 * ��sku��ָ����[�ϼܿ���]�в��Һ��ʵĿ�λ
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromSkuArea(
			Hashtable<String, Object> param) throws Exception {
		// ��ȡsku���Ŀ����������Ŀ�����
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		param.put("target.whareaId", prdt.getStorageareaid());

		return getCsFromTargetArea(param);
	}

	/**
	 * �ϼܵ�sku��ָ����[�ϼܿ�λ]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromSkuTarget(
			Hashtable<String, Object> param) throws Exception {
		// ��ȡ��Ʒָ���ϼܿ�λ
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���

		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String putUnit = reqBean.putmode;
		String customerid = prtBean.getOwnerid();// �ͻ�
		param.put("preUsed", "N");

		// ��ȡ��Ʒ��װ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// ÿ���Ʒ����
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String TargetCsId = prdt.getStoragespaceid();// Ŀ���λ
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

			// ��ȡ��λ����
			double getNum = getPStgNumFromCs(prdt, targetCs, dao);
			prtBean.realputnum += getNum;

			updateCsStatus(targetCs, dao);

			lsAllot.add(makeBPResponse(targetCs, reqBean, prdt.getProductid(),
					prtBean.putnum));
		}

		return lsAllot;
	}

	/**
	 * ʹ��[�������λ]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromPieceCs(
			Hashtable<String, Object> param) throws Exception {
		// �������
		// (1)�жϻ�λ��λ�Ƿ�Ϊ���������Ϊ�����򲻷��䣬����
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		if (!reqBean.putmode.equals("EA"))
			return null;

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", "'3'");

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * ʹ��[������λ]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromBoxCs(
			Hashtable<String, Object> param) throws Exception {
		// �������
		// (1)�жϻ�λ��λ�Ƿ�Ϊ�䣬�����Ϊ�䣬�򲻷��䣬����
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		if (!reqBean.putmode.equals("��"))
			return null;

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", "'2'");

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * ʹ��[��/������λ]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromPieceBoxCs(
			Hashtable<String, Object> param) throws Exception {
		// �������
		// (1)�жϻ�λ��λ�Ƿ�Ϊ�����䣬�����Ϊ�����䣬�򲻷��䣬����
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		if (!reqBean.putmode.equals("CS") && !reqBean.putmode.equals("EA"))
			return null;

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", "'4'");

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * �����������Ϊָ��[��������]������[Ŀ�����]�в��Һ��ʵĿ�λ
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromBatchToArea(
			Hashtable<String, Object> param) throws Exception {
		// ��ȡ���������������ֵ���Ʒ��������ֵ�Ƚ�
		// �����ͬ�����ȡ��������ϼ���
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");

		if (compareBatch(rPutawayDetail, prtBean)) {
			param.put("target.whareaId", rPutawayDetail.getTozone());
		} else {
			return null;
		}

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		return getCsFromTargetArea(param);
	}

	/**
	 * �����������Ϊָ��[��������]�����ϼܵ�[Ŀ���λ]
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromBatchToTarget(
			Hashtable<String, Object> param) throws Exception {
		// ��ȡ���������������ֵ���Ʒ��������ֵ�Ƚ�
		// �����ͬ�����ȡ��������ϼܿ�λ
		// ��ȡ��Ʒָ���ϼܿ�λ
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

		// ��ȡ��Ʒ��װ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// ÿ���Ʒ����
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		String TargetCsId = rPutawayDetail.getTolocationid();// Ŀ���λ
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

		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���

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

			// ��ȡ��λ����
			double getNum = getPStgNumFromCs(prdt, targetCs, dao);
			prtBean.realputnum += getNum;

			updateCsStatus(targetCs, dao);

			lsAllot.add(makeBPResponse(targetCs, reqBean, prdt.getProductid(),
					prtBean.putnum));
		}

		return lsAllot;
	}

	/**
	 * ����ͬ���Ʋ�Ʒ���ڿտ�λ
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromSameProductId(
			Hashtable<String, Object> param) throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		String targetWhareaId = rPutawayDetail.getTozone();// Ŀ������
		param.put("target.whareaId", targetWhareaId);
		param.put("sameType", "product");
		return getCsFromSameParam(param);
	}

	/**
	 * ����ͬ���β�Ʒ���ڿտ�λ
	 * 
	 * @param param
	 * @return
	 */
	public List getCsFromSameBatch(Hashtable<String, Object> param)
			throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);

		String targetWhareaId = rPutawayDetail.getTozone();// Ŀ������
		param.put("target.whareaId", targetWhareaId);
		param.put("sameType", "batch");
		return getCsFromSameParam(param);
	}

	/**
	 * ���ݴ洢�����������λ
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsFromStoragetype(
			Hashtable<String, Object> param) throws Exception {
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");

		String storagetype1 = rPutawayDetail.getLoc_handle1();// �洢����1
		String storagetype2 = rPutawayDetail.getLoc_handle2();// �洢����2
		String storagetype3 = rPutawayDetail.getLoc_handle3();// �洢����3
		String storagetype4 = rPutawayDetail.getLoc_handle4();// �洢����4
		String storagetype5 = rPutawayDetail.getLoc_handle5();// �洢����5

		// ������֤
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

		String preUsed = "N";// �ϲ�����
		if (rPutawayDetail.getLoc_restrict() != null
				&& rPutawayDetail.getLoc_restrict().equals(""))
			preUsed = "Y";
		param.put("preUsed", preUsed);
		param.put("storagetype", storagetype);

		return getCsFromTargetStoragetype(param);
	}

	/**
	 * ����Ʒ�������ڸ�����������ȷֲ���ÿ�����
	 * 
	 * @param param
	 * @return
	 */
	public List<InAllotResponseBean> getCsEvenProductDate(
			Hashtable<String, Object> param) throws Exception {
		// �������
		// (1)���ݲ�Ʒ�������������,������������С��������
		// (2)������Щ���˳���ȡ�������Ӧ�Ļ�λ
		// (3)�ж��ϼܵ�λ�Ƿ�Ϊ�У�����������η��䣬�ҿ��Ի�ţ���ѡ���ѷ����λ
		// (4)����洢�����Ƿ����㣬�����������һ����λ
		BaseProduct prdt = (BaseProduct) param.get("BaseProduct");
		InAllotRequestBean reqBean = (InAllotRequestBean) param.get("reqBean");
		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) param
				.get("prtBean");
		RulePutawayDetail rPutawayDetail = (RulePutawayDetail) param
				.get("rPutawayDetail");
		EntityDAO dao = (EntityDAO) param.get("dao");

		String whId = null;
		String targetWhareaId = null;// �ϼܿ���
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = rPutawayDetail.getTozone();
			whId = (String) param.get("target.warehouseid");
		} else {
			whId = reqBean.warehouseid;
		}

		String putUnit = reqBean.getPutmode();// �ϼܵ�λ
		String customerid = prtBean.getOwnerid();// �ͻ�
		String productid = prtBean.getProductid();// ��Ʒ
		double pnum = prtBean.getPutnum();// ��Ʒ����
		int palletNum = reqBean.getITrays();// ������
		String printDate = prtBean.getLotatt1();//��������
		

		// ��ȡ��Ʒ��װ����
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();

		double pNumCS = 0;// ÿ���Ʒ����
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
		
		int alleyNum = lsAlley.size();// �������
		double allotNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���

		if (putUnit.equals("1")) {// ���̴洢
			// ÿ���������һ�����̣�ѭ�����䣬�������û�пջ�λ
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
				numPerAlley = pnum / alleyNum;// ÿ���������
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

						// ��֤��λ
						param.put("target.cargoSpace", cs);
						if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum,
								pNumCS, dao) == null)
							continue;

						// ��ȡ��λ����
						double getNum = getPStgNumFromCs(prdt, cs, dao);
						numPerAlley -= getNum;
						prtBean.realputnum += getNum;
						allotNum += getNum;

						// ���»�λ״̬
						updateCsStatus(cs, dao);

						// �����Ӧ��
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
					numPerAlley = pnum / alleyNum;// ÿ���������
				}
			}
		}

		return lsAllot;
	}

	/**
	 * ÿ�����������䣬�����������
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
		String targetWhareaId = null;// �ϼܿ���
		targetWhareaId = reqBean.whAreaId;
		if (targetWhareaId == null || targetWhareaId.length() < 1) {
			targetWhareaId = rPutawayDetail.getTozone();	
			whId = (String) param.get("target.warehouseid");			
		} else {
			whId = reqBean.warehouseid;
		}

		String putUnit = reqBean.getPutmode();// �ϼܵ�λ
		String customerid = prtBean.getOwnerid();// �ͻ�
		double pnum = prtBean.getPutnum();// ��Ʒ����
		int palletNum = reqBean.getITrays();// ������

		// ��ȡ��Ʒ��װ����
		IPackageBus iPkg = new PackageBusImpl(dao);
		BasePackage pkg = iPkg.getPackageById(prdt.getPakageid());
		if (pkg == null) {
			ICustomerBus iCustomer = new CustomerBusImpl(dao);
			BaseCustomer customer = iCustomer.getCustomerById(customerid);

			// ��ȡ�ͻ���װ����
			pkg = iPkg.getPackageById(customer.getPakageid());
		}
		double pNumPallet = 0;// ÿ�в�Ʒ����
		IBasePackageMastermesgDao pkgMstrDao = new BasePackageMastermesgDaoImpl(
				dao);
		pNumPallet = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "PL"))
				.getQty();
		double pNumCS = 0;// ÿ���Ʒ����
		pNumCS = (pkgMstrDao.getPackageMastermesg(pkg.getPackageid(), "CS"))
				.getQty();
		if (putUnit.equals("EA"))
			pNumCS = 1;

		double allotNum = 0;
		List<InAllotResponseBean> lsAllot = new ArrayList<InAllotResponseBean>();// �ѷ��䷵�ض���

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

			if (putUnit.equals("PL")) {// ���̴洢
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

					// ��֤��λ
					param.put("target.cargoSpace", cs);
					if (verifyCsFromTargetCs(prdt, cs, putUnit, pnum, pNumCS,
							dao) == null)
						continue;

					// ��ȡ��λ����
					double getNum = getPStgNumFromCs(prdt, cs, dao);
					
					if(getNum <=0 )//�����λ�Ų����������λ
						lsEmptyCs.remove(j);
					
					allotNum += getNum;
					prtBean.realputnum += getNum;

					// ���»�λ״̬
					updateCsStatus(cs, dao);

					// �����Ӧ��
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
	 * �����������
	 * 
	 * @param param
	 * @return
	 */
	public List getCsIncrease(Hashtable param) {

		return null;
	}
}
