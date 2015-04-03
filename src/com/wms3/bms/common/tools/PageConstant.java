package com.wms3.bms.common.tools;

/**
 * 描述:定义跳转页面
 * @author hugui
 *
 */
public class PageConstant 
{
	
	/**
	 * 陈海燕（begin）===================================================================
	 */
	//1:基础信息（跳转页面）*****************************************************************
	public final static String m_strEnterValidateUrl = "/jsp/baseInfo/enterValidate.jsp"; //入库校验参数
	public final static String m_strInitTerminalUrl = "/jsp/baseInfo/initTerminal.jsp"; //初始化终端信息
	public final static String m_strStorageAreaMgrUrl = "/jsp/baseInfo/storageArea_mgr.jsp"; //储区规划
	public final static String m_strStorageAreaUrl = "/jsp/baseInfo/include/storageArea_main.jsp"; //储区规划iframe
	public final static String m_strWarehouseAddUrl = "/jsp/baseInfo/warehouse_add.jsp"; //仓库添加
	public final static String m_strWarehouseMgrUrl = "/jsp/baseInfo/warehouse_mgr.jsp"; //仓库管理
	public final static String m_strCargoAreaAddUrl = "/jsp/baseInfo/cargoArea_add.jsp"; //货区添加
	public final static String m_strCargoAreaUpdateUrl = "/jsp/baseInfo/cargoArea_update.jsp"; //货区修改
	public final static String m_strCargoAreaMgrUrl = "/jsp/baseInfo/cargo_area_list.jsp"; //货区管理
	public final static String m_strCargoSpaceMgrUrl = "/jsp/baseInfo/cargoSpace_mgr.jsp"; //货位管理
	public final static String m_strCargoSpaceMgr_treeUrl = "/jsp/baseInfo/include/cargoSpace_left.jsp"; //货位管理之左边树
	public final static String m_strCargoSpaceMgr_whUrl = "/jsp/baseInfo/include/cargoSpace_wh.jsp"; //货位管理之仓库显示
	public final static String m_strCargoSpaceMgr_caUrl = "/jsp/baseInfo/include/cargoSpace_ca.jsp"; //货位管理之货区显示
	public final static String m_strCargoSpaceMgr_csUrl = "/jsp/baseInfo/include/cargoSpace_cs.jsp"; //货位管理之货位显示
	public final static String m_strCargoSpaceMgr_initCsUrl = "/jsp/baseInfo/include/cargoSpace_initCs.jsp"; //货位管理之货位初始化
	public final static String m_strCargoSpaceMgr_initCs_rightUrl = "/jsp/baseInfo/include/cargoSpace_cs_right.jsp"; //货位管理之货位初始化之右货物列表
	public final static String m_strTypeMgrUrl = "/jsp/baseInfo/baseType_mgr.jsp"; //类型管理
	/**
	 * 陈海燕（end）=====================================================================
	 */
	/**
	 * 胡贵（begin）===================================================================
	 */
	//1:角色管理(跳转页面)************************************************
	public final static String m_strRoleUrl = "/jsp/privilege/roleMgr.jsp";	
	public final static String m_strRoleUserUrl = "jsp/privilege/roleUserList.jsp";//用户列表
	public final static String m_strRoleMouleUrl = "jsp/privilege/rolePopedomAssign.jsp";//角色权限
	//2:用户管理(跳转页面)************************************************
	public final static String m_strUserUrl = "/jsp/privilege/userMgr.jsp";
	public final static String m_strUserRoleUrl = "jsp/privilege/userRoleAssign.jsp";//角色列表
	public final static String m_strUserModuleUrl = "jsp/privilege/userPopedomAssign.jsp";//用户权限
	//3:模块管理(跳转页面)************************************************
	public final static String m_strModuleUrl = "/jsp/privilege/moduleMgr.jsp";
	//4:用户组管理(跳转页面)************************************************
	public final static String m_strUserGroupUrl = "/jsp/privilege/userGroupMgr.jsp";
	//5:日志管理(跳转页面)************************************************
	public final static String m_strSystemLogUrl = "/jsp/privilege/systemLogMgr.jsp";	//系统日志
	public final static String m_strJobLogUrl = "/jsp/privilege/jobLogMgr.jsp";		//作业日志
	public final static String m_strAssignLogUrl = "/jsp/privilege/assignmentLogMgr.jsp";	//任务日志
	//库存管理(积压分析与断档分析)**********************************************
	public final static String m_strBackUpAnalyseStatUrl = "/jsp/inventoryMgr/backUpStatistical.jsp";//积压分析统计
	public final static String m_strBackUpReportStatUrl = "/jsp/inventoryMgr/backUpStatReport.jsp";//积压分析统计报表
	public final static String m_strBackUpDetailUrl = "/jsp/inventoryMgr/backUpDetailMesg.jsp";//积压分析明细查询
	public final static String m_strBackUpDetailReportUrl = "/jsp/inventoryMgr/backUpDetailReport.jsp";////积压分析明细报表
	public final static String m_strSeverFileAnalyseStatUrl = "/jsp/inventoryMgr/severFileStatistical.jsp";//断档分析统计
	public final static String m_strSeverFileReportStatUrl = "/jsp/inventoryMgr/severFileReport.jsp";//断档分析统计报表
	//库存管理(堆垛机运行分析)
	public final static String m_strStowMachineUrl = "/jsp/inventoryMgr/stowMachineAnalyse.jsp";
	//库存管理(入库均匀分析)
	public final static String m_strInEvenlyUrl = "/jsp/inventoryMgr/inEvenlyAnalyse.jsp";
	//库存管理(库存误差分析)
	public final static String m_strInErrorUrl = "/jsp/inventoryMgr/inErrorAnalyse.jsp";//入库误差分析
	public final static String m_strNumberErrorURL = "/jsp/inventoryMgr/numberErrorAnalyse.jsp";//库存数量误差分析
	//库存管理(库存预警分析)
	public final static String m_strEarlyWarnStatUrl = "/jsp/inventoryMgr/earlyWarnStatistical.jsp";//预警统计查询
	public final static String m_strEarlyWarnReportUrl = "/jsp/inventoryMgr/earlyWarnStatReport.jsp";//预警统计报表
	public final static String m_strEarlyWarnDetailUrl = "/jsp/inventoryMgr/earlyWarnDetailMesg.jsp";//预警明细查询
	public final static String m_strEarlyWarnDetailReportUrl = "/jsp/inventoryMgr/earlyWarnDetailReport.jsp";//预警明细报表
	//库存管理(暂存查询)
	public final static String m_strTempProductUrl = "/jsp/inventoryMgr/tempProductQuery.jsp";
	//库存管理(库存查询)
	public final static String m_strCsProductUrl = "/jsp/inventoryMgr/csProductQuery.jsp";
	public final static String m_strCsProductReportUrl = "/jsp/inventoryMgr/csProductQueryReport.jsp";//库存查询报表
	//库存管理(交接班查询)
	public final static String m_strTourSuccessorUrl = "/jsp/inventoryMgr/tourSuccessorQuery.jsp";
	//库存管理(在途查询)
	public final static String m_strOnWayUrl = "/jsp/inventoryMgr/onWayQuery.jsp";
	public final static String m_strOnWayListUrl = "/jsp/inventoryMgr/onWayQueryList.jsp";
	//库存管理(需盘点查询)
	public final static String m_strNeedCheckUrl = "/jsp/inventoryMgr/needCheckQuery.jsp";
	public final static String m_strNeedCheckReportUrl = "/jsp/inventoryMgr/needCheckQueryReport.jsp";//需盘点查询报表
	//库存管理(作业分析)
	public final static String m_strIterantJobErrorStatUrl = "/jsp/inventoryMgr/iterantjobErrorStat.jsp";
	public final static String m_strIterantJobErrorDetailUrl = "/jsp/inventoryMgr/iterantjobErrorDetail.jsp";//查询详细
	public final static String m_strInJobErrorUrl = "/jsp/inventoryMgr/injobErrorDetail.jsp";//入库作业误差分析
	public final static String m_strOnDutyJobErrorUrl = "/jsp/inventoryMgr/onDutyJobErrorStat.jsp";
	public final static String m_strOnDutyJobErrorDetailUrl = "/jsp/inventoryMgr/onDutyJobErrorDetail.jsp";
	//库存管理(库存盘点)
	public final static String m_strCheckCargoSpaceUrl = "/jsp/inventoryMgr/checkCargoSpace.jsp";
	public final static String m_strCheckDetailUrl = "/jsp/inventoryMgr/kc_check_list.jsp";
	public final static String m_strCheckDetailAddUrl = "/jsp/inventoryMgr/checkDetailAdd.jsp";
	//库存管理(盘点查询)
	public final static String m_strCheckQueryUrl = "/jsp/inventoryMgr/checkQueryList.jsp";
	//库存管理(暂存盘点)
	public final static String m_strTemporalityCheckUrl = "/jsp/inventoryMgr/zc_check_list.jsp";
	public final static String m_strTemporalityQueryUrl = "/jsp/inventoryMgr/temporalityCheckQuery.jsp";
	//库存管理(批次追踪)
	public final static String m_strBatchTrackQueryUrl  = "/jsp/inventoryMgr/batch_track_list.jsp";
	public final static String m_strBatchTrackReportUrl  = "/jsp/inventoryMgr/batch_track_report.jsp";
	//质检管理(抽检)
	public final static String m_strSpotCheckOutUrl = "/jsp/qualityMgr/spotCheckOutAdd.jsp";
	public final static String m_strSpotTempProductUrl = "/jsp/qualityMgr/tempProductQueryDetail.jsp";
	public final static String m_strSpotCsProductUrl = "/jsp/qualityMgr/csProductQueryDetail.jsp";
	public final static String m_strSpotCheckAddUrl = "/jsp/qualityMgr/spotCheckOutAddDetail.jsp";
	public final static String m_strSpotCheckReportUrl = "/jsp/qualityMgr/spotCheckOutReport.jsp";
	public final static String m_strSpotCheckResultDetUrl = "/jsp/qualityMgr/include/spotCheckOutResultDetail.jsp";
	public final static String m_strSpotCheckCsproductUrl = "/jsp/qualityMgr/include/csproductDetail.jsp";
	public final static String m_strSpotCheckTempproductUrl = "/jsp/qualityMgr/include/tempproductDetail.jsp";
	/**
	 * 胡贵（end）=====================================================================
	 */
	
	//6:字典管理(跳转页面)***************************************************
	public  final static String m_strMapUrl = "/standard/jsp/base/dictionary/left.jsp";
	//6-1:部门资料(跳转页面)*************************************************
	public final  static String m_strBranchUrl="/standard/jsp/base/dictionary/tab/branchMeans.jsp";
	//6-2:仓库管理员管理
	public final  static String m_strSortHouseUrl="/standard/jsp/base/dictionary/tab/storehouse.jsp";
	//6-3:抽检员资料管理
	public final  static String m_strExtUrl="/standard/jsp/base/dictionary/tab/extractor.jsp";
	//6-4:码盘工资料管理
	public final  static String m_strRousUrl="/jsp/dictionary/tab/roustabout.jsp";
	//6-5:计量单位管理
	public final static String m_strMeterUrl="/standard/jsp/base/dictionary/tab/meter.jsp";
	//6-6:产品类型
	public final static String m_strProUrl="/jsp/dictionary/tab/produceSort.jsp";
	//6-7:设备类别资料管理
	public final static String m_strEquUrl="/jsp/dictionary/tab/equipmentSort.jsp";
	//6-8：汽车类型资料管理
	public final static String m_strCarUrl="/jsp/dictionary/tab/carSort.jsp";
	//6-9：信息资料管理
	public final static String m_strCcmmUrl="/jsp/dictionary/tab/communicationBoard.jsp";
	//6-10：省份资料管理
	public final static String m_strProvUrl="/jsp/dictionary/tab/province.jsp";
	//6-11：集装箱箱型资料管理
	public final static String m_strConTypeUrl="/jsp/dictionary/tab/containerType.jsp";
	//6-12：集装箱费用管理
	public final static String m_srotConFeeUrl="/jsp/dictionary/tab/containerFee.jsp";
	//6-13：物品规格管理
	public final static String m_strGoodsUrl="/jsp/dictionary/tab/goodsSpec.jsp";
    //6-13：包装规格管理
	public final static String m_strPackUrl="/jsp/dictionary/tab/packSpec.jsp";
	//6-14：码用管理
	public final static String m_strLabUrl="/standard/jsp/base/dictionary/tab/labout.jsp";
    //6-14：车位信息管理
	public final static String m_strCarPlaceUrl="/jsp/dictionary/tab/carPlace.jsp";
    //6-15：客户档案管理
	public final static String m_strClientUrl="/standard/jsp/base/dictionary/tab/clientFile.jsp";
	 //6-15：客户档案管理clientFile_main
	public final static String m_strClient_mainUrl="/standard/jsp/base/dictionary/tab/clientFile.jsp";
	//6-16:车间资料管理
	public final static String m_strWorkshopUrl="/standard/jsp/base/dictionary/tab/workshop.jsp";
	
	
	
    //设备管理
	public final static String m_strEquimentManageUrl="/jsp/maintenance/equipmentManage.jsp";
	//条形码维护
	public final static String m_strBarCodeUrl="/jsp/maintenance/tray_code_list.jsp";
	//终端条码初始化
	public final static String m_strXterInfoUrl="/jsp/maintenance/xtermInfo.jsp";
    //缠模管理
	public final static String m_strCmbzUr="/jsp/maintenance/cmbz.jsp";
    //接口管理
	public final static String m_strInterfaceUrl="/jsp/maintenance/interface_main.jsp";
	//单据查询
	public final static String m_strBilUrl="/jsp/maintenance/bil.jsp";
	//标准客户查询
	public final static String m_strClientTabeUrl="/jsp/maintenance/client.jsp";
    //单据标准客户查询
	public final static String m_strClientBileUrl="/jsp/maintenance/client_bil.jsp";
	 //单据标准客户查询
	public final static String m_strCBileUrl="/jsp/maintenance/clientBil.jsp";
	//物品类型
	public final static String m_strProTypeUr="/jsp/maintenance/include/proTypeTree_main.jsp";
	//修改物品类型
	public final static String m_strUpdProTypeUr="/jsp/maintenance/proTypeUpd.jsp";
	//添加物品类型  
	public final static String m_straddProTypeUr="/jsp/maintenance/proTypeAdd.jsp";
    //储位列表  
	public final static String m_strStorageUrl="/jsp/maintenance/include/storage.jsp";
	public final static String m_strLanewayUrl="/jsp/maintenance/include/laneway.jsp";
    //储位规划
	public final static String m_strLayoutUrl="/jsp/maintenance/include/layout.jsp";
    //物品查询
	public final static String m_strTbproUrl="/jsp/maintenance/include/cargo_right.jsp";
	//修改物品信息
	public final static String m_strudpTbproUrl="/jsp/maintenance/include/cargo_updright.jsp";
	//添加物品信息
	public final static String m_straddTbproUrl="/jsp/maintenance/include/cargo_addright.jsp";
	//物品包装查询 
	public final static String m_strTbpacUrl="/jsp/maintenance/include/cargo_right2.jsp";
	//修改物品包装信息
	public final static String m_strupdTbpacUrl="/jsp/maintenance/include/cargo_updright2.jsp";
	//显示物品包装信息列表
	public final static String m_strTbpacListUrl="/jsp/maintenance/test/list.jsp";
	public final static String m_strTbpacList1Url="/jsp/maintenance/test1/list.jsp";
	//显示客户档案信息列表
	public final static String m_strClientFileListUrl="/jsp/maintenance/test2/list.jsp";
	//显示销售单信息列表
	public final static String m_strSaleFormListUrl="/jsp/maintenance/order/list.jsp";
	public final static String m_strSFDetailListUrl="/jsp/maintenance/order/list1.jsp";
	//显示出库信息列表(B：客户)
	public final static String m_strB_HouseoutListUrl="/jsp/warehouseout/ck_fhqr_downlist.jsp";
	//显示出库信息列表(A：客户)
	public final static String m_strA_HouseoutListUrl="/jsp/warehouseout/ck_fhqr_up_mainlist.jsp";
	//显示库存信息列表(A：客户)
	public final static String m_strA_StockListUrl="/jsp/warehouseout/ck_fhqr_up_mainlist.jsp";
	//显示卸载工信息列表(发货确认)
	public final static String m_strSureLabUrl="/jsp/warehouseout/fhqr_right_zxg.jsp";
	//显示暂存表信息(发货确认)
	public final static String m_strTempUrl="/jsp/warehouseout/ck_tempProduct.jsp";
	//(发货确认)
	public final static String m_strExamUrl="/jsp/warehouseout/ck_examine.jsp";
	//显示物品包装信息列表
	public final static String m_strListUrl="/jsp/maintenance/test2/list.jsp";
	//拆单查询列表
	public final static String m_strDisconnectListUrl="/jsp/warehouseout/ck_gdcz_mgr_disconnect.jsp";
	//可用货位状况列表
	public final static String m_strGoodsPlacesUrl="/jsp/maintenance/goodsPlaces.jsp";
	//添加物品类型树列表
	public final static String m_strTreeUrl="/jsp/maintenance/include/cargo_left.jsp";
	//添加物品类型树列表 
	public final static String m_strBarChartUrl="/jsp/maintenance/barChart.jsp";
    //添加包装信息
	public final static String m_strCargo2Url="/jsp/maintenance/include/cargo_addright2.jsp";
	 //物品储位信息
	public final static String m_strHave_placeUrl="/jsp/maintenance/include/hive_place.jsp";
	 //包装储位信息
	public final static String m_strHave2_placeUrl="/jsp/maintenance/include/hive2_place.jsp";
	 //要盘策略
	public final static String m_strYpclUrl="/jsp/maintenance/ypcl.jsp";
	 //设置策略
	public final static String m_strSzclUrl="/jsp/maintenance/cqclSimple.jsp";
	//出盘策略  
	public final static String m_strCpclUrl="/jsp/maintenance/cpclSimple.jsp";
	//出盘策略  小车列表
	public final static String m_strXt_cpcl_1Url="/jsp/maintenance/include/xt_cpcl_1.jsp";
	//出盘策略  添加小车列表
	public final static String m_strXt_cpcl_2Url="/jsp/maintenance/include/xt_cpcl_2.jsp";
	 //设置策略_入库输送机
	public final static String m_strXt_cqcl_1Url="/jsp/maintenance/include/xt_cqcl_1.jsp";
	//设置策略_添加入库输送机
	public final static String m_strXt_cqcl_2Url="/jsp/maintenance/include/xt_cqcl_2.jsp";
	//设置策略_入库小车调度
	public final static String m_strXt_cqcl_3Url="/jsp/maintenance/include/xt_cqcl_3.jsp";
	//作业班次列表
	public final static String m_strJobUrl="/jsp/dictionary/tab/jobList.jsp";
	//选择用户
	public final static String m_strUserListUrl="/jsp/dictionary/tab/chooseUser/list.jsp";
	//批量移库
	public final static String m_strMoveUrl="/jsp/maintenance/move.jsp";
	//堆垛机盘点
	public final static String m_strchkekUrl="/jsp/maintenance/check.jsp";
	//出库原则列表
	public final static String m_strSetupUrl="/jsp/maintenance/chuku_mgr.jsp";
	//货位分析报表
	public final static String m_strAnalyseUrl="/jsp/maintenance/analyse_main_mar.jsp";
	//设备管理
	public final static String m_strDeviceUrl = "/jsp/maintenance/device_mgr.jsp"; //设备列表
	//批次组成部分列表
	public final static String m_strFrom_mainUrl = "/jsp/maintenance/formula_add.jsp"; //设备列表
	//批次列表
	public final static String m_strBatchUrl = "/jsp/maintenance/batch_main.jsp"; 
	//省份列表
//public final static String m_strProvinceUrl = "/jsp/maintenance/include/area_province.jsp"; 	
	//城市列表
//	public final static String m_strCityUrl = "/jsp/maintenance/include/area_city.jsp";
	//修改城市
//	public final static String m_strUpdCityUrl = "/jsp/maintenance/include/area_Updcity.jsp"; 
	//国家
//	public final static String m_strCountryUr="/jsp/maintenance/include/area_country.jsp";
	//修改国家名称
//	public final static String m_strUdpCountryUr="/jsp/maintenance/include/countryTreeUpd.jsp";
	
	//修改
	public final static String m_strUpdUrl = "/jsp/maintenance/include/area_Upd.jsp"; 
	//左边区域树
	public final static String m_strTreeUr="/jsp/maintenance/include/area_left.jsp";
	//区域信息
	public final static String m_strRegionUrl = "/jsp/maintenance/include/area_main.jsp";
	/**
	 * yangxi（begin）===================================================================
	 */
	//5:客户档案查询(跳转页面)************************************************
	public final static String m_strCustomerQueryUrl = "/jsp/report/customerInfoDetail.jsp";
	//5:客户档案打印预览(跳转页面)************************************************
	public final static String m_strCustomerPrintUrl = "/jsp/report/customerInfoReport.jsp";
	//6:物品档案查询(跳转页面)************************************************
	public final static String m_strProductInfoUrl = "/jsp/report/productInfoDetail.jsp";
	//6:物品档案打印预览(跳转页面)************************************************
	public final static String m_strProductPrintUrl = "/jsp/report/productInfoReport.jsp";
	//:出库班结查询明细月台帐目(跳转页面)*********************
	public final static String m_strOutHouseListTableUrl = "/jsp/report/outHouseListDetail.jsp";
	//:出库班结查询月台帐目(跳转页面)*********************
	public final static String m_strOutHouseTableUrl = "/jsp/report/outHouseDetail.jsp";
	//:销售查询(跳转页面)*********************
	public final static String m_strSellQueryUrl = "/jsp/report/sellQueryDetail.jsp";
	//:销售查询明细(跳转页面)*********************
	public final static String m_strSellQueryListUrl = "/jsp/report/sellQueryList.jsp";
    //:出入库日报汇总(跳转页面)*********************
	public final static String m_strOutInHouseDailyTotalUrl = "/jsp/report/outInHouseDailyTotalMain.jsp";
    //:出入库旬月年报表(跳转页面)*********************
	public final static String m_strOutInHouseUrl = "/jsp/report/outInHouseMain.jsp";
	//:入库交接班统计(跳转页面)*********************
	public final static String m_strconnectStorageUrl = "/jsp/report/connectStorageDetail.jsp";
	//:入库交接班明细(跳转页面)*********************
	public final static String m_strconnectStorageListUrl = "/jsp/report/connectStorageList.jsp";
	//:出库单据查询(跳转页面)*********************
	public final static String m_strMNOutDocQueryUrl = "/jsp/warehouseout/ck_chukudan_search_list.jsp";
	//:入库管理(跳转页面)*********************
	public final static String m_strMNInMgrUrl = "/jsp/warehousein/rk_ruku_mgr_list.jsp";
	//:密码修改成功(跳转页面)*********************
	public final static String m_strchangePasswdUrl = "/jsp/privilege/changePasswd2.jsp";
	
	/**
	 * yangxi（end）===================================================================
	*/
	
	/**
	 * 张远志（begin）===================================================================
	 */
	//可出库物品查询(跳转页面)************************************************
	public final static String m_strPPlanSearchUrl = "/jsp/warehousein/rk_pplan_search_list.jsp";						//生产单据查询
	public final static String m_strPPlanSearchDetailsUrl = "/jsp/warehousein/rk_pplan_search_details.jsp";			//生产单据查询-->>明细
	public final static String m_strRukudanSearchUrl = "/jsp/warehousein/rk_rukudan_search_list.jsp";					//进库单据查询
	public final static String m_strNwwInWarehouseReporthUrl = "/jsp/warehousein/rk_rukudan_new_report.jsp";			//新建入库报表
	/**
	 * 张远志（end）===================================================================
	*/
	
}
