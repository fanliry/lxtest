<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%
   
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script>
<script type="text/javascript">
<!--
	var obj1 = null;
	function setOnBackground(obj)
	{
		obj.style.backgroundColor = "#33FF33";
		if(obj1 != null)
		{
			obj1.style.backgroundColor ="";			
		}
		obj1 = obj; 	
	}
	function OnLoad(){

		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}
-->
</script>
</head>
<body  onLoad="OnLoad()">
 <table width="100%"   id="table" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="30"><div class="list_title">行号</div></td>
     <!-- <td class="TD_LIST_TITLE" align="center"><div class="list_title">作业详细</div></td> -->
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">作业状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">作业号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">库区</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">是否复合</div></td>  
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">托盘条码</div></td>
   </tr>
<%
	if (ls != null && ls.size()>0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();     
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		Object[] obj = null;
      	InoutJob job = null;			// 作业 
		InoutJobdetail jobDetail = null;// 作业详细  
		String strJobDetailId = "";		// 作业详细ID
		String linestatus = "";			// 行状态
		String strJobId = "";			// 作业号
		String strCargoAreaName = "";	// 库区
		String strCargoSpaceName = "";	// 货位
		String strProductName = "";;	// 物品
		String isFH = "";	//是否复合 
		String strTrayCode = "";		// 托盘条码
		double fPNum =0;				// 数量
		double fAssPNum =0;				// 分配数量	
		for (int i=0; i<ls.size(); i++) 
		{
 			obj = (Object[])ls.get(i);
 			job = (InoutJob)obj[0];							// 作业 
		    jobDetail = (InoutJobdetail)obj[1];				// 作业详细	
			strJobDetailId = jobDetail.getJobdetailid();	// 作业详细ID
		 	linestatus = job.getStatusName();			    // 作业状态
		 	strJobId = job.getJobid();						// 作业号ID
		 	strCargoAreaName = job.getScargoWhareaName();	// 库区
		 	strCargoSpaceName = job.getScargoSpaceName();	// 货位
		 	strProductName = jobDetail.getM_strProductName();// 物品名
		 	isFH = jobDetail.getIsreview();	//	是否复合
		 	strTrayCode = job.getTraycode();				 // 托盘条码
		 	fPNum = jobDetail.getJobnum();					 // 数量
		 	fAssPNum = jobDetail.getAssignnum();			 // 分配数量	  	
%>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" onmousedown="setOnBackground(this);">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <!--<td class="TD_LIST" align="center"><%=strJobDetailId == null ? "" : strJobDetailId%></td>-->
     <td class="TD_LIST" align="center"><%=linestatus == null ? "" : linestatus%></td>
     <td class="TD_LIST" align="center"><%=strJobId == null ? "" : strJobId%></td>
     <td class="TD_LIST" align="center"><%=strCargoAreaName == null ? "" : strCargoAreaName%></td>
     <td class="TD_LIST" align="center"><%=strCargoSpaceName == null ? "" : strCargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=strProductName == null ? "" : strProductName%></td>
     <td class="TD_LIST" align="center"><%=isFH == null ? "" : isFH%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(fPNum)%></td>
     <td class="TD_LIST" align="center" <%=fAssPNum != fPNum ? "style='background-color:#AABB45' " : ""%>><%=nbf.format(fAssPNum)%></td>
     <td class="TD_LIST" align="center"><%=strTrayCode == null ? "" : strTrayCode%></td>
   </tr>
<%			
		}

	}
%>
   <tr>
     <td height="100%" colspan="10"  valign="top">
       <div style="color: red; font-size:12;margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
     
</body>
</html>
