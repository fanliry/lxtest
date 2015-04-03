<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
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
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
	
	function openDetail(jobid){
		
		condition = "&jobid=" + jobid; 
		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=ricoExecSendSearch&flag=3" + condition, 
					'', "dialogWidth=1000px; dialogHeight=600px; scroll=no");
		
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单据编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">客户名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">车牌号</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">车位</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">进厂批号</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	
	if(ls != null && ls.size()>0){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		Object[] obj = null;
		
		String invoiceid = null;	// 单据编号
		String customer = null;     // 客户
		String vehicleno = null;	// 车牌号
		String vehiclepos = null;	// 车位
		String product = null;		// 产品
     	String traycode = null;     // 托盘条码
     	String cargospace = null;   // 货位
     	String jobid = null;		// 作业号
		double jobnum = 0;			// 数量
		double volume = 0;         	// 体积
 		double weight = 0;         	// 重量
		double netweight = 0;		// 净重
		
		String lotinfo="";
		String printdate = "";
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			invoiceid = obj[0] == null ? "" : obj[0].toString();	// 单据编号
			customer = obj[1] == null ? "" : obj[1].toString();		// 客户
			vehicleno = obj[2] == null ? "" : obj[2].toString();	// 车牌号
			vehiclepos = obj[3] == null ? "" : obj[3].toString();	// 车位
			product = obj[4] == null ? "" : obj[4].toString();		// 产品
			traycode = obj[5] == null ? "" : obj[5].toString();		// 托盘条码
			cargospace = obj[6] == null ? "" : obj[6].toString();	// 货位
			
			//jobid = obj[7] == null ? "" : obj[7].toString();		// 作业号
			
			jobnum = Double.parseDouble(obj[7].toString().trim());			// 数量
			//boxnum = Long.parseLong(obj[9].toString());				// 箱数
			//boxnum =(Long)obj[9];
			volume = Double.parseDouble(obj[8].toString());      	// 体积
 			weight = Double.parseDouble(obj[9].toString());     	// 重量
			netweight = Double.parseDouble(obj[10].toString());		// 净重
			
			lotinfo = obj[11] == null ? "" : obj[11].toString();     	// 进厂批号
			printdate = obj[12] == null ? "" : obj[12].toString();		// 生产日期
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ><!-- onDblClick="openDetail('<%=jobid%>');" -->
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=invoiceid%></td>
     <td class="TD_LIST" align="center"><%=customer%></td>
     <td class="TD_LIST" align="center"><%=vehicleno%></td>
     <td class="TD_LIST" align="center"><%=vehiclepos%></td>
     <td class="TD_LIST" align="center"><%=product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo%></td>
     <td class="TD_LIST" align="center"><%=printdate%></td>
     <td class="TD_LIST" align="center"><%=traycode%></td>
     <td class="TD_LIST" align="center"><%=cargospace%></td>
     <td class="TD_LIST" align="center"><%=(int)jobnum%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>