<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryTransferDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.dao.base.impl.BasePackageDaoImpl" %>
<%@ page import="com.ricosoft.common.dao.dataSource.EntityDAO" %>
<%@ page import="com.wms3.bms.standard.service.BMSService" %>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackage" %>
<%@ page import="com.wms3.bms.standard.dao.base.impl.BasePackageMastermesgDaoImpl" %>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackageMastermesg" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%
	HashMap<String, List> hsLot = (HashMap<String, List>)session.getAttribute("viewLot");
	List lsLot = hsLot.get("hptzkc");
	EntityDAO dao = BMSService.getm_EntityDAO();
%>
<html>
<head>
  <title>仓储配送管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/commonHandle.js"></script>
  <script type="text/javascript">
  <!--
	 function OnLoad(){
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
			}
		}
	}
  -->
  </script>
</head>

<body onload="javascript:OnLoad();">
<%
	List list = null;
	if(request.getAttribute("exResultList") != null) 
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr class="list_title_tr">
	  <td width="40" class="TD_LIST_TITLE">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll('myform','checkbox_all','checkbox_id')"></td>
	   <td width="40" class="TD_LIST_TITLE"><div class="list_title">行号</div></td> 
	   <td width="60" class="TD_LIST_TITLE"><div class="list_title">状态</div></td>	

	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">库位</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">毛重</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">体积</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">创建人</div></td>
	  <td width="80" class="TD_LIST_TITLE"><div class="list_title">创建时间</div></td>
	  
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM客户ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM包装</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM计量单位</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM批次ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM产品ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM托盘条码</div></td>
	  
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO客户ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO包装ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO计量单位</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO批次ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO产品ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO托盘条码</div></td>
	  
	    <%
		int iLine = 0;	//显示的批次属性个数
		BaseLotSet lotSet = null;
		if(lsLot != null){
			iLine = lsLot.size();
			for(int k = 0; k < iLine; k++){
				lotSet = (BaseLotSet)lsLot.get(k);			
		%>
			 <td class="TD_LIST_TITLE"><div class="list_title">FM<%=lotSet.getLotname()%></div></td>
			 <td class="TD_LIST_TITLE"><div class="list_title">TO<%=lotSet.getLotname()%></div></td>
		<%
				}
			}
		%> 

	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();         
		nbf.setMinimumFractionDigits(2);
		InventoryTransferDetail detail=null;
        String FMlotatt;  			//批次属性
        String TOlotatt;  			//批次属性
        String strDId="";
        String strStatus="";
        String strLocId="";
        double qty;
        String fGrossweight="";
        String fCubic="";
        String creatman="";
        String strEditTime="";
        String strStatusName="";
        
        String fmcustomerid="";
        String fmcustomername="";
        String tocustomername="";
        String fmpackid="";
        String fmpackname="";
        String fmpunit="";
        String fmpunitname="";
        String fmlotid="";
        String fmlotName="";
        String fmproductid="";
        String fmproductname="";
        String fmtraycode="";
        
        String tocustomerid="";
        String topackid="";
        String topackname="";
        String topunit="";
        String topunitname="";
        String tolotid="";
        String tolotname="";
        String toproductid="";
        String toproductname="";
        String totraycode="";
        BasePackageDaoImpl BasePackageIml= new BasePackageDaoImpl(dao);
        BasePackage basepackage=null;
        
        BasePackageMastermesgDaoImpl BasePackageMastermesgIml= new BasePackageMastermesgDaoImpl(dao);
        BasePackageMastermesg basemesg=null;
        
        
        for(int i=0; i<list.size(); i++)
        {
        
             detail = (InventoryTransferDetail)list.get(i); 
             strDId = detail.getTransferdetailid();//详细ID
        	 strStatus = detail.getStatus();  //状态
        	 strStatusName = strStatus.trim().equals("0")?"创建":"完成";
        	 strLocId = detail.getCargo_space_id();	 //库位
        	 qty = (double)detail.getPnum();	//库存数量
        	 fGrossweight = nbf.format(detail.getPnetweight());//毛重
        	 fCubic = nbf.format(detail.getPvolume());		//体积
        	 creatman = detail.getCreatemanid();	 //创建人
        	 strEditTime = detail.getCreatetime(); 	//创建时间
        	 
        	 fmcustomerid = detail.getFmcustomerid(); //FM客户id
        	 fmpackid = detail.getFmpackid();//FM包装
        	 basepackage = BasePackageIml.getPackageById(fmpackid);
        	 if(basepackage!=null){
        	    fmpackname = basepackage.getPkgdesc();
        	 }
        	 
        	 
        	 
        	 fmpunit = detail.getFmpunit();//FM单位
        	 basemesg = BasePackageMastermesgIml.getPackageMastermesg(fmpackid,fmpunit);
        	 if(basemesg!=null){
        	    fmpunitname = basemesg.getPkgunitdesc();
        	 }
        	 
        	 fmlotid = detail.getFmlotid();//FM批次id
        	 fmlotName = detail.getFmlotname();
        	 
        	 fmproductid = detail.getFmproductid();//FM产品id
        	 fmproductname = detail.getFmproductname();
        	 fmtraycode = detail.getFmtraycode();//FM托盘条码
        	 
        	 tocustomerid = detail.getTocustomerid(); //TO客户id
        	 tocustomername = detail.getTocustomername(); //TO客户id
        	 topackid = detail.getTopackid();//TO包装
        	 basepackage = BasePackageIml.getPackageById(topackid);
        	 if(basepackage!=null){
        	    topackname = basepackage.getPkgdesc();
        	 }
        	 topunit = detail.getTopunit();//TO单位
        	 basemesg = BasePackageMastermesgIml.getPackageMastermesg(topackid,topunit);
        	 if(basemesg!=null){
        	    topunitname = basemesg.getPkgunitdesc();
        	 }
        	 tolotid = detail.getTolotid();//TO批次id
        	 tolotname = detail.getToproductname();
        	 toproductid = detail.getToproductid();//TO产品id
        	 toproductname = detail.getToproductname();
        	 totraycode = detail.getTotraycode();//TO托盘条码
       	 
%>	
	<tr class="list_normal_tr" >
	  <td class="TD_LIST">
	    <input type="checkbox" name="checkbox_id" value="<%=strDId%>" class="input_checkbox">
	  </td>
	  <td class="TD_LIST"><%=i+1%></td>
	  <td class="TD_LIST"><%=strStatusName == null ? "&nbsp;" : strStatusName%></td>
	  <td class="TD_LIST"><%=strLocId == null ? "&nbsp;" : strLocId%></td>
	  <td class="TD_LIST"><%=qty%></td>
	  <td class="TD_LIST"><%=fGrossweight%></td>
	  <td class="TD_LIST"><%=fCubic%></td>
	  <td class="TD_LIST"><%=creatman == null ? "&nbsp;" : creatman%></td>
	  <td class="TD_LIST"><%=strEditTime == null ? "&nbsp;" : strEditTime%></td>
	  
	  <td class="TD_LIST"><%=fmcustomerid == null ? "&nbsp;" : fmcustomerid%></td>
	  <td class="TD_LIST"><%=fmpackname == null ? "&nbsp;" : fmpackname%></td>
	  <td class="TD_LIST"><%=fmpunitname == null ? "&nbsp;" : fmpunitname%></td>
	  <td class="TD_LIST"><%=fmlotName == null ? "&nbsp;" : fmlotName%></td>
	  <td class="TD_LIST"><%=fmproductname == null ? "&nbsp;" : fmproductname%></td>
	  <td class="TD_LIST"><%=fmtraycode == null ? "&nbsp;" : fmtraycode%></td>
	  
	  <td class="TD_LIST"><%=tocustomerid == null ? "&nbsp;" : tocustomername%></td>
	  <td class="TD_LIST"><%=topackname == null ? "&nbsp;" : topackname%></td>
	  <td class="TD_LIST"><%=topunitname == null ? "&nbsp;" : topunitname%></td>
	  <td class="TD_LIST"><%=tolotname == null ? "&nbsp;" : tolotname%></td>
	  <td class="TD_LIST"><%=toproductname == null ? "&nbsp;" : toproductname%></td>
	  <td class="TD_LIST"><%=totraycode == null ? "&nbsp;" : totraycode%></td>
 <%
	if(lsLot != null){
		for(int k = 0; k < iLine; k++){
			FMlotatt = "";		//批次属性
			TOlotatt = "";		//批次属性
			lotSet = (BaseLotSet)lsLot.get(k);
			if(lotSet!=null && lotSet.getLotid()!=null && lotSet.getLotid().length()>=7){
			    switch(Integer.parseInt(lotSet.getLotid().substring(6, lotSet.getLotid().length())))
				{
					 case 1:FMlotatt=detail.getFmlotatt1();break;
					 case 2:FMlotatt=detail.getFmlotatt2();break;
					 case 3:FMlotatt=detail.getFmlotatt3();break;
					 case 4:FMlotatt=detail.getFmlotatt4();break;
					 case 5:FMlotatt=detail.getFmlotatt5();break;
					 case 6:FMlotatt=detail.getFmlotatt6();break;
					 case 7:FMlotatt=detail.getFmlotatt7();break;
					 case 8:FMlotatt=detail.getFmlotatt8();break;
					 case 9:FMlotatt=detail.getFmlotatt9();break;
					 case 10:FMlotatt=detail.getFmlotatt10();break;
					 case 11:FMlotatt=detail.getFmlotatt11();break;
					 case 12:FMlotatt=detail.getFmlotatt12();break;
				}
				switch(Integer.parseInt(lotSet.getLotid().substring(6, lotSet.getLotid().length())))
				{
					 case 1:TOlotatt=detail.getTolotatt1();break;
					 case 2:TOlotatt=detail.getTolotatt2();break;
					 case 3:TOlotatt=detail.getTolotatt3();break;
					 case 4:TOlotatt=detail.getTolotatt4();break;
					 case 5:TOlotatt=detail.getTolotatt5();break;
					 case 6:TOlotatt=detail.getTolotatt6();break;
					 case 7:TOlotatt=detail.getTolotatt7();break;
					 case 8:TOlotatt=detail.getTolotatt8();break;
					 case 9:TOlotatt=detail.getTolotatt9();break;
					 case 10:TOlotatt=detail.getTolotatt10();break;
					 case 11:TOlotatt=detail.getTolotatt11();break;
					 case 12:TOlotatt=detail.getTolotatt12();break;
				}
			}			
%>
	 		 <td class="TD_LIST" align="center"><%=FMlotatt!=null?FMlotatt:""%></td>
	 		 <td class="TD_LIST" align="center"><%=TOlotatt!=null?TOlotatt:""%></td>
<%
		}
	}
%>
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
	<tr height="100%">
	  <td height="100%" colspan="<%=21 + 2*iLine%>" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
