package com.wms3.bms.common.tools;

/**
 * ����:������תҳ��
 * @author hugui
 *
 */
public class PageConstant 
{
	
	/**
	 * �º��ࣨbegin��===================================================================
	 */
	//1:������Ϣ����תҳ�棩*****************************************************************
	public final static String m_strEnterValidateUrl = "/jsp/baseInfo/enterValidate.jsp"; //���У�����
	public final static String m_strInitTerminalUrl = "/jsp/baseInfo/initTerminal.jsp"; //��ʼ���ն���Ϣ
	public final static String m_strStorageAreaMgrUrl = "/jsp/baseInfo/storageArea_mgr.jsp"; //�����滮
	public final static String m_strStorageAreaUrl = "/jsp/baseInfo/include/storageArea_main.jsp"; //�����滮iframe
	public final static String m_strWarehouseAddUrl = "/jsp/baseInfo/warehouse_add.jsp"; //�ֿ����
	public final static String m_strWarehouseMgrUrl = "/jsp/baseInfo/warehouse_mgr.jsp"; //�ֿ����
	public final static String m_strCargoAreaAddUrl = "/jsp/baseInfo/cargoArea_add.jsp"; //�������
	public final static String m_strCargoAreaUpdateUrl = "/jsp/baseInfo/cargoArea_update.jsp"; //�����޸�
	public final static String m_strCargoAreaMgrUrl = "/jsp/baseInfo/cargo_area_list.jsp"; //��������
	public final static String m_strCargoSpaceMgrUrl = "/jsp/baseInfo/cargoSpace_mgr.jsp"; //��λ����
	public final static String m_strCargoSpaceMgr_treeUrl = "/jsp/baseInfo/include/cargoSpace_left.jsp"; //��λ����֮�����
	public final static String m_strCargoSpaceMgr_whUrl = "/jsp/baseInfo/include/cargoSpace_wh.jsp"; //��λ����֮�ֿ���ʾ
	public final static String m_strCargoSpaceMgr_caUrl = "/jsp/baseInfo/include/cargoSpace_ca.jsp"; //��λ����֮������ʾ
	public final static String m_strCargoSpaceMgr_csUrl = "/jsp/baseInfo/include/cargoSpace_cs.jsp"; //��λ����֮��λ��ʾ
	public final static String m_strCargoSpaceMgr_initCsUrl = "/jsp/baseInfo/include/cargoSpace_initCs.jsp"; //��λ����֮��λ��ʼ��
	public final static String m_strCargoSpaceMgr_initCs_rightUrl = "/jsp/baseInfo/include/cargoSpace_cs_right.jsp"; //��λ����֮��λ��ʼ��֮�һ����б�
	public final static String m_strTypeMgrUrl = "/jsp/baseInfo/baseType_mgr.jsp"; //���͹���
	/**
	 * �º��ࣨend��=====================================================================
	 */
	/**
	 * ����begin��===================================================================
	 */
	//1:��ɫ����(��תҳ��)************************************************
	public final static String m_strRoleUrl = "/jsp/privilege/roleMgr.jsp";	
	public final static String m_strRoleUserUrl = "jsp/privilege/roleUserList.jsp";//�û��б�
	public final static String m_strRoleMouleUrl = "jsp/privilege/rolePopedomAssign.jsp";//��ɫȨ��
	//2:�û�����(��תҳ��)************************************************
	public final static String m_strUserUrl = "/jsp/privilege/userMgr.jsp";
	public final static String m_strUserRoleUrl = "jsp/privilege/userRoleAssign.jsp";//��ɫ�б�
	public final static String m_strUserModuleUrl = "jsp/privilege/userPopedomAssign.jsp";//�û�Ȩ��
	//3:ģ�����(��תҳ��)************************************************
	public final static String m_strModuleUrl = "/jsp/privilege/moduleMgr.jsp";
	//4:�û������(��תҳ��)************************************************
	public final static String m_strUserGroupUrl = "/jsp/privilege/userGroupMgr.jsp";
	//5:��־����(��תҳ��)************************************************
	public final static String m_strSystemLogUrl = "/jsp/privilege/systemLogMgr.jsp";	//ϵͳ��־
	public final static String m_strJobLogUrl = "/jsp/privilege/jobLogMgr.jsp";		//��ҵ��־
	public final static String m_strAssignLogUrl = "/jsp/privilege/assignmentLogMgr.jsp";	//������־
	//������(��ѹ������ϵ�����)**********************************************
	public final static String m_strBackUpAnalyseStatUrl = "/jsp/inventoryMgr/backUpStatistical.jsp";//��ѹ����ͳ��
	public final static String m_strBackUpReportStatUrl = "/jsp/inventoryMgr/backUpStatReport.jsp";//��ѹ����ͳ�Ʊ���
	public final static String m_strBackUpDetailUrl = "/jsp/inventoryMgr/backUpDetailMesg.jsp";//��ѹ������ϸ��ѯ
	public final static String m_strBackUpDetailReportUrl = "/jsp/inventoryMgr/backUpDetailReport.jsp";////��ѹ������ϸ����
	public final static String m_strSeverFileAnalyseStatUrl = "/jsp/inventoryMgr/severFileStatistical.jsp";//�ϵ�����ͳ��
	public final static String m_strSeverFileReportStatUrl = "/jsp/inventoryMgr/severFileReport.jsp";//�ϵ�����ͳ�Ʊ���
	//������(�Ѷ�����з���)
	public final static String m_strStowMachineUrl = "/jsp/inventoryMgr/stowMachineAnalyse.jsp";
	//������(�����ȷ���)
	public final static String m_strInEvenlyUrl = "/jsp/inventoryMgr/inEvenlyAnalyse.jsp";
	//������(���������)
	public final static String m_strInErrorUrl = "/jsp/inventoryMgr/inErrorAnalyse.jsp";//���������
	public final static String m_strNumberErrorURL = "/jsp/inventoryMgr/numberErrorAnalyse.jsp";//�������������
	//������(���Ԥ������)
	public final static String m_strEarlyWarnStatUrl = "/jsp/inventoryMgr/earlyWarnStatistical.jsp";//Ԥ��ͳ�Ʋ�ѯ
	public final static String m_strEarlyWarnReportUrl = "/jsp/inventoryMgr/earlyWarnStatReport.jsp";//Ԥ��ͳ�Ʊ���
	public final static String m_strEarlyWarnDetailUrl = "/jsp/inventoryMgr/earlyWarnDetailMesg.jsp";//Ԥ����ϸ��ѯ
	public final static String m_strEarlyWarnDetailReportUrl = "/jsp/inventoryMgr/earlyWarnDetailReport.jsp";//Ԥ����ϸ����
	//������(�ݴ��ѯ)
	public final static String m_strTempProductUrl = "/jsp/inventoryMgr/tempProductQuery.jsp";
	//������(����ѯ)
	public final static String m_strCsProductUrl = "/jsp/inventoryMgr/csProductQuery.jsp";
	public final static String m_strCsProductReportUrl = "/jsp/inventoryMgr/csProductQueryReport.jsp";//����ѯ����
	//������(���Ӱ��ѯ)
	public final static String m_strTourSuccessorUrl = "/jsp/inventoryMgr/tourSuccessorQuery.jsp";
	//������(��;��ѯ)
	public final static String m_strOnWayUrl = "/jsp/inventoryMgr/onWayQuery.jsp";
	public final static String m_strOnWayListUrl = "/jsp/inventoryMgr/onWayQueryList.jsp";
	//������(���̵��ѯ)
	public final static String m_strNeedCheckUrl = "/jsp/inventoryMgr/needCheckQuery.jsp";
	public final static String m_strNeedCheckReportUrl = "/jsp/inventoryMgr/needCheckQueryReport.jsp";//���̵��ѯ����
	//������(��ҵ����)
	public final static String m_strIterantJobErrorStatUrl = "/jsp/inventoryMgr/iterantjobErrorStat.jsp";
	public final static String m_strIterantJobErrorDetailUrl = "/jsp/inventoryMgr/iterantjobErrorDetail.jsp";//��ѯ��ϸ
	public final static String m_strInJobErrorUrl = "/jsp/inventoryMgr/injobErrorDetail.jsp";//�����ҵ������
	public final static String m_strOnDutyJobErrorUrl = "/jsp/inventoryMgr/onDutyJobErrorStat.jsp";
	public final static String m_strOnDutyJobErrorDetailUrl = "/jsp/inventoryMgr/onDutyJobErrorDetail.jsp";
	//������(����̵�)
	public final static String m_strCheckCargoSpaceUrl = "/jsp/inventoryMgr/checkCargoSpace.jsp";
	public final static String m_strCheckDetailUrl = "/jsp/inventoryMgr/kc_check_list.jsp";
	public final static String m_strCheckDetailAddUrl = "/jsp/inventoryMgr/checkDetailAdd.jsp";
	//������(�̵��ѯ)
	public final static String m_strCheckQueryUrl = "/jsp/inventoryMgr/checkQueryList.jsp";
	//������(�ݴ��̵�)
	public final static String m_strTemporalityCheckUrl = "/jsp/inventoryMgr/zc_check_list.jsp";
	public final static String m_strTemporalityQueryUrl = "/jsp/inventoryMgr/temporalityCheckQuery.jsp";
	//������(����׷��)
	public final static String m_strBatchTrackQueryUrl  = "/jsp/inventoryMgr/batch_track_list.jsp";
	public final static String m_strBatchTrackReportUrl  = "/jsp/inventoryMgr/batch_track_report.jsp";
	//�ʼ����(���)
	public final static String m_strSpotCheckOutUrl = "/jsp/qualityMgr/spotCheckOutAdd.jsp";
	public final static String m_strSpotTempProductUrl = "/jsp/qualityMgr/tempProductQueryDetail.jsp";
	public final static String m_strSpotCsProductUrl = "/jsp/qualityMgr/csProductQueryDetail.jsp";
	public final static String m_strSpotCheckAddUrl = "/jsp/qualityMgr/spotCheckOutAddDetail.jsp";
	public final static String m_strSpotCheckReportUrl = "/jsp/qualityMgr/spotCheckOutReport.jsp";
	public final static String m_strSpotCheckResultDetUrl = "/jsp/qualityMgr/include/spotCheckOutResultDetail.jsp";
	public final static String m_strSpotCheckCsproductUrl = "/jsp/qualityMgr/include/csproductDetail.jsp";
	public final static String m_strSpotCheckTempproductUrl = "/jsp/qualityMgr/include/tempproductDetail.jsp";
	/**
	 * ����end��=====================================================================
	 */
	
	//6:�ֵ����(��תҳ��)***************************************************
	public  final static String m_strMapUrl = "/standard/jsp/base/dictionary/left.jsp";
	//6-1:��������(��תҳ��)*************************************************
	public final  static String m_strBranchUrl="/standard/jsp/base/dictionary/tab/branchMeans.jsp";
	//6-2:�ֿ����Ա����
	public final  static String m_strSortHouseUrl="/standard/jsp/base/dictionary/tab/storehouse.jsp";
	//6-3:���Ա���Ϲ���
	public final  static String m_strExtUrl="/standard/jsp/base/dictionary/tab/extractor.jsp";
	//6-4:���̹����Ϲ���
	public final  static String m_strRousUrl="/jsp/dictionary/tab/roustabout.jsp";
	//6-5:������λ����
	public final static String m_strMeterUrl="/standard/jsp/base/dictionary/tab/meter.jsp";
	//6-6:��Ʒ����
	public final static String m_strProUrl="/jsp/dictionary/tab/produceSort.jsp";
	//6-7:�豸������Ϲ���
	public final static String m_strEquUrl="/jsp/dictionary/tab/equipmentSort.jsp";
	//6-8�������������Ϲ���
	public final static String m_strCarUrl="/jsp/dictionary/tab/carSort.jsp";
	//6-9����Ϣ���Ϲ���
	public final static String m_strCcmmUrl="/jsp/dictionary/tab/communicationBoard.jsp";
	//6-10��ʡ�����Ϲ���
	public final static String m_strProvUrl="/jsp/dictionary/tab/province.jsp";
	//6-11����װ���������Ϲ���
	public final static String m_strConTypeUrl="/jsp/dictionary/tab/containerType.jsp";
	//6-12����װ����ù���
	public final static String m_srotConFeeUrl="/jsp/dictionary/tab/containerFee.jsp";
	//6-13����Ʒ������
	public final static String m_strGoodsUrl="/jsp/dictionary/tab/goodsSpec.jsp";
    //6-13����װ������
	public final static String m_strPackUrl="/jsp/dictionary/tab/packSpec.jsp";
	//6-14�����ù���
	public final static String m_strLabUrl="/standard/jsp/base/dictionary/tab/labout.jsp";
    //6-14����λ��Ϣ����
	public final static String m_strCarPlaceUrl="/jsp/dictionary/tab/carPlace.jsp";
    //6-15���ͻ���������
	public final static String m_strClientUrl="/standard/jsp/base/dictionary/tab/clientFile.jsp";
	 //6-15���ͻ���������clientFile_main
	public final static String m_strClient_mainUrl="/standard/jsp/base/dictionary/tab/clientFile.jsp";
	//6-16:�������Ϲ���
	public final static String m_strWorkshopUrl="/standard/jsp/base/dictionary/tab/workshop.jsp";
	
	
	
    //�豸����
	public final static String m_strEquimentManageUrl="/jsp/maintenance/equipmentManage.jsp";
	//������ά��
	public final static String m_strBarCodeUrl="/jsp/maintenance/tray_code_list.jsp";
	//�ն������ʼ��
	public final static String m_strXterInfoUrl="/jsp/maintenance/xtermInfo.jsp";
    //��ģ����
	public final static String m_strCmbzUr="/jsp/maintenance/cmbz.jsp";
    //�ӿڹ���
	public final static String m_strInterfaceUrl="/jsp/maintenance/interface_main.jsp";
	//���ݲ�ѯ
	public final static String m_strBilUrl="/jsp/maintenance/bil.jsp";
	//��׼�ͻ���ѯ
	public final static String m_strClientTabeUrl="/jsp/maintenance/client.jsp";
    //���ݱ�׼�ͻ���ѯ
	public final static String m_strClientBileUrl="/jsp/maintenance/client_bil.jsp";
	 //���ݱ�׼�ͻ���ѯ
	public final static String m_strCBileUrl="/jsp/maintenance/clientBil.jsp";
	//��Ʒ����
	public final static String m_strProTypeUr="/jsp/maintenance/include/proTypeTree_main.jsp";
	//�޸���Ʒ����
	public final static String m_strUpdProTypeUr="/jsp/maintenance/proTypeUpd.jsp";
	//�����Ʒ����  
	public final static String m_straddProTypeUr="/jsp/maintenance/proTypeAdd.jsp";
    //��λ�б�  
	public final static String m_strStorageUrl="/jsp/maintenance/include/storage.jsp";
	public final static String m_strLanewayUrl="/jsp/maintenance/include/laneway.jsp";
    //��λ�滮
	public final static String m_strLayoutUrl="/jsp/maintenance/include/layout.jsp";
    //��Ʒ��ѯ
	public final static String m_strTbproUrl="/jsp/maintenance/include/cargo_right.jsp";
	//�޸���Ʒ��Ϣ
	public final static String m_strudpTbproUrl="/jsp/maintenance/include/cargo_updright.jsp";
	//�����Ʒ��Ϣ
	public final static String m_straddTbproUrl="/jsp/maintenance/include/cargo_addright.jsp";
	//��Ʒ��װ��ѯ 
	public final static String m_strTbpacUrl="/jsp/maintenance/include/cargo_right2.jsp";
	//�޸���Ʒ��װ��Ϣ
	public final static String m_strupdTbpacUrl="/jsp/maintenance/include/cargo_updright2.jsp";
	//��ʾ��Ʒ��װ��Ϣ�б�
	public final static String m_strTbpacListUrl="/jsp/maintenance/test/list.jsp";
	public final static String m_strTbpacList1Url="/jsp/maintenance/test1/list.jsp";
	//��ʾ�ͻ�������Ϣ�б�
	public final static String m_strClientFileListUrl="/jsp/maintenance/test2/list.jsp";
	//��ʾ���۵���Ϣ�б�
	public final static String m_strSaleFormListUrl="/jsp/maintenance/order/list.jsp";
	public final static String m_strSFDetailListUrl="/jsp/maintenance/order/list1.jsp";
	//��ʾ������Ϣ�б�(B���ͻ�)
	public final static String m_strB_HouseoutListUrl="/jsp/warehouseout/ck_fhqr_downlist.jsp";
	//��ʾ������Ϣ�б�(A���ͻ�)
	public final static String m_strA_HouseoutListUrl="/jsp/warehouseout/ck_fhqr_up_mainlist.jsp";
	//��ʾ�����Ϣ�б�(A���ͻ�)
	public final static String m_strA_StockListUrl="/jsp/warehouseout/ck_fhqr_up_mainlist.jsp";
	//��ʾж�ع���Ϣ�б�(����ȷ��)
	public final static String m_strSureLabUrl="/jsp/warehouseout/fhqr_right_zxg.jsp";
	//��ʾ�ݴ����Ϣ(����ȷ��)
	public final static String m_strTempUrl="/jsp/warehouseout/ck_tempProduct.jsp";
	//(����ȷ��)
	public final static String m_strExamUrl="/jsp/warehouseout/ck_examine.jsp";
	//��ʾ��Ʒ��װ��Ϣ�б�
	public final static String m_strListUrl="/jsp/maintenance/test2/list.jsp";
	//�𵥲�ѯ�б�
	public final static String m_strDisconnectListUrl="/jsp/warehouseout/ck_gdcz_mgr_disconnect.jsp";
	//���û�λ״���б�
	public final static String m_strGoodsPlacesUrl="/jsp/maintenance/goodsPlaces.jsp";
	//�����Ʒ�������б�
	public final static String m_strTreeUrl="/jsp/maintenance/include/cargo_left.jsp";
	//�����Ʒ�������б� 
	public final static String m_strBarChartUrl="/jsp/maintenance/barChart.jsp";
    //��Ӱ�װ��Ϣ
	public final static String m_strCargo2Url="/jsp/maintenance/include/cargo_addright2.jsp";
	 //��Ʒ��λ��Ϣ
	public final static String m_strHave_placeUrl="/jsp/maintenance/include/hive_place.jsp";
	 //��װ��λ��Ϣ
	public final static String m_strHave2_placeUrl="/jsp/maintenance/include/hive2_place.jsp";
	 //Ҫ�̲���
	public final static String m_strYpclUrl="/jsp/maintenance/ypcl.jsp";
	 //���ò���
	public final static String m_strSzclUrl="/jsp/maintenance/cqclSimple.jsp";
	//���̲���  
	public final static String m_strCpclUrl="/jsp/maintenance/cpclSimple.jsp";
	//���̲���  С���б�
	public final static String m_strXt_cpcl_1Url="/jsp/maintenance/include/xt_cpcl_1.jsp";
	//���̲���  ���С���б�
	public final static String m_strXt_cpcl_2Url="/jsp/maintenance/include/xt_cpcl_2.jsp";
	 //���ò���_������ͻ�
	public final static String m_strXt_cqcl_1Url="/jsp/maintenance/include/xt_cqcl_1.jsp";
	//���ò���_���������ͻ�
	public final static String m_strXt_cqcl_2Url="/jsp/maintenance/include/xt_cqcl_2.jsp";
	//���ò���_���С������
	public final static String m_strXt_cqcl_3Url="/jsp/maintenance/include/xt_cqcl_3.jsp";
	//��ҵ����б�
	public final static String m_strJobUrl="/jsp/dictionary/tab/jobList.jsp";
	//ѡ���û�
	public final static String m_strUserListUrl="/jsp/dictionary/tab/chooseUser/list.jsp";
	//�����ƿ�
	public final static String m_strMoveUrl="/jsp/maintenance/move.jsp";
	//�Ѷ���̵�
	public final static String m_strchkekUrl="/jsp/maintenance/check.jsp";
	//����ԭ���б�
	public final static String m_strSetupUrl="/jsp/maintenance/chuku_mgr.jsp";
	//��λ��������
	public final static String m_strAnalyseUrl="/jsp/maintenance/analyse_main_mar.jsp";
	//�豸����
	public final static String m_strDeviceUrl = "/jsp/maintenance/device_mgr.jsp"; //�豸�б�
	//������ɲ����б�
	public final static String m_strFrom_mainUrl = "/jsp/maintenance/formula_add.jsp"; //�豸�б�
	//�����б�
	public final static String m_strBatchUrl = "/jsp/maintenance/batch_main.jsp"; 
	//ʡ���б�
//public final static String m_strProvinceUrl = "/jsp/maintenance/include/area_province.jsp"; 	
	//�����б�
//	public final static String m_strCityUrl = "/jsp/maintenance/include/area_city.jsp";
	//�޸ĳ���
//	public final static String m_strUpdCityUrl = "/jsp/maintenance/include/area_Updcity.jsp"; 
	//����
//	public final static String m_strCountryUr="/jsp/maintenance/include/area_country.jsp";
	//�޸Ĺ�������
//	public final static String m_strUdpCountryUr="/jsp/maintenance/include/countryTreeUpd.jsp";
	
	//�޸�
	public final static String m_strUpdUrl = "/jsp/maintenance/include/area_Upd.jsp"; 
	//���������
	public final static String m_strTreeUr="/jsp/maintenance/include/area_left.jsp";
	//������Ϣ
	public final static String m_strRegionUrl = "/jsp/maintenance/include/area_main.jsp";
	/**
	 * yangxi��begin��===================================================================
	 */
	//5:�ͻ�������ѯ(��תҳ��)************************************************
	public final static String m_strCustomerQueryUrl = "/jsp/report/customerInfoDetail.jsp";
	//5:�ͻ�������ӡԤ��(��תҳ��)************************************************
	public final static String m_strCustomerPrintUrl = "/jsp/report/customerInfoReport.jsp";
	//6:��Ʒ������ѯ(��תҳ��)************************************************
	public final static String m_strProductInfoUrl = "/jsp/report/productInfoDetail.jsp";
	//6:��Ʒ������ӡԤ��(��תҳ��)************************************************
	public final static String m_strProductPrintUrl = "/jsp/report/productInfoReport.jsp";
	//:�������ѯ��ϸ��̨��Ŀ(��תҳ��)*********************
	public final static String m_strOutHouseListTableUrl = "/jsp/report/outHouseListDetail.jsp";
	//:�������ѯ��̨��Ŀ(��תҳ��)*********************
	public final static String m_strOutHouseTableUrl = "/jsp/report/outHouseDetail.jsp";
	//:���۲�ѯ(��תҳ��)*********************
	public final static String m_strSellQueryUrl = "/jsp/report/sellQueryDetail.jsp";
	//:���۲�ѯ��ϸ(��תҳ��)*********************
	public final static String m_strSellQueryListUrl = "/jsp/report/sellQueryList.jsp";
    //:������ձ�����(��תҳ��)*********************
	public final static String m_strOutInHouseDailyTotalUrl = "/jsp/report/outInHouseDailyTotalMain.jsp";
    //:�����Ѯ���걨��(��תҳ��)*********************
	public final static String m_strOutInHouseUrl = "/jsp/report/outInHouseMain.jsp";
	//:��⽻�Ӱ�ͳ��(��תҳ��)*********************
	public final static String m_strconnectStorageUrl = "/jsp/report/connectStorageDetail.jsp";
	//:��⽻�Ӱ���ϸ(��תҳ��)*********************
	public final static String m_strconnectStorageListUrl = "/jsp/report/connectStorageList.jsp";
	//:���ⵥ�ݲ�ѯ(��תҳ��)*********************
	public final static String m_strMNOutDocQueryUrl = "/jsp/warehouseout/ck_chukudan_search_list.jsp";
	//:������(��תҳ��)*********************
	public final static String m_strMNInMgrUrl = "/jsp/warehousein/rk_ruku_mgr_list.jsp";
	//:�����޸ĳɹ�(��תҳ��)*********************
	public final static String m_strchangePasswdUrl = "/jsp/privilege/changePasswd2.jsp";
	
	/**
	 * yangxi��end��===================================================================
	*/
	
	/**
	 * ��Զ־��begin��===================================================================
	 */
	//�ɳ�����Ʒ��ѯ(��תҳ��)************************************************
	public final static String m_strPPlanSearchUrl = "/jsp/warehousein/rk_pplan_search_list.jsp";						//�������ݲ�ѯ
	public final static String m_strPPlanSearchDetailsUrl = "/jsp/warehousein/rk_pplan_search_details.jsp";			//�������ݲ�ѯ-->>��ϸ
	public final static String m_strRukudanSearchUrl = "/jsp/warehousein/rk_rukudan_search_list.jsp";					//���ⵥ�ݲ�ѯ
	public final static String m_strNwwInWarehouseReporthUrl = "/jsp/warehousein/rk_rukudan_new_report.jsp";			//�½���ⱨ��
	/**
	 * ��Զ־��end��===================================================================
	*/
	
}
