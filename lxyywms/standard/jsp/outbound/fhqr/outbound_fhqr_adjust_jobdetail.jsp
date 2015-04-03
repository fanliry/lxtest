<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--

	//改变背景
    function Change(obj) {
		var check_ids = document.getElementsByName("check_id");
		for (var i=0; i<check_ids.length; i++) {
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}
		obj.parentNode.parentNode.style.backgroundColor = "#AFEF6F";
	}

  //全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	function OnLoad(){	
	
		var back_msg = "<%=request.getAttribute("back_msg") == null ? "" : (String)request.getAttribute("back_msg")%>";
		if (back_msg != "") 
		{
			if(back_msg == "Y")
			{
				alert("确认成功!");
			}else
			{
				alert(back_msg);
			}
		}
	}
	
-->
</script>
</head>

<body onload="OnLoad();">
<div style="width: 100%; height: 100%; overflow:auto;">
 <table width="100%" height="100%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST" id="tb">
   <tr height="20">
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">选择</div></td>
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">作业号</div></td>
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">物品状态</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">进场编码</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">日期</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">数量</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();     
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
	 
		InoutJobdetail jobDetail = null; 
		InoutJob job = null;  
		int iBoxNum = 0;				// 箱号
		
		String strJobDetailId = null;

		String strProductName = null;	// 物品规格名
		String strBatchId = null;		// 批次ID
		String strSstatusName = null;   // 样品类别
		String strMstatusName = null;  	// 物品状态
		String strBatch = null;			// 批号
		String strPrintDate = null;		// 日期
		
		String strBoxCode = null;		// 箱上条码
		
		double  fPNum =0;				// 个数
		String fGrossWeight = "0";		// 毛重
		String fNetWeight = "0";		// 净重
		
		double  fAssPNum =0;				// 个数
		String fAssGrossWeight = "0";		// 毛重
		String fAssNetWeight = "0";		// 净重
		
		String strCustomerName = null; // 客户
		String strCustomerId = null; // 客户
		
		String jobid = "";
		String traycode = "";
		Object[] ob =null;
		for(int i = 0; i < ls.size(); i++)
		{
	    	ob = (Object[])ls.get(i);  
	    	jobDetail = (InoutJobdetail)ob[1];
	    	job = (InoutJob)ob[0];
	    	
	    	jobid = job.getJobid();
	    	traycode = job.getTraycode();
	    	
	    	strJobDetailId = jobDetail.getJobdetailid();	// 作业详细ID
		 	strProductName 	  = jobDetail.getM_strProductName();  // 物品规格名
		 	strBoxCode = jobDetail.getBoxcode();			// 箱上条码	 	
		 	fGrossWeight = nbf.format(jobDetail.getWeight());	// 毛重
			fNetWeight   = nbf.format(jobDetail.getNetweight());// 净重
		  	fPNum        = jobDetail.getJobnum();				// 个数	
		  	fAssPNum = jobDetail.getAssignnum();							// 个数
		    fAssGrossWeight = nbf.format(jobDetail.getAssignweight());		// 毛重
		    fAssNetWeight = nbf.format(jobDetail.getAssignnetweight());		// 净重
	    	  
	    	strCustomerName = jobDetail.getM_strCustomerName();
	    	strCustomerId = jobDetail.getCustomerid();
	    	
	    	strMstatusName = jobDetail.getProductStatusName();
	    	strPrintDate = jobDetail.getPrintdate();
	    	strBatch = jobDetail.getLotinfo();
	    	
	    	
	
	    %>	
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" >
    <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" value="<%=strJobDetailId%>" class="input_radio" onClick="Change(this)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=strProductName==null?"":strProductName%></td>
     <td class="TD_LIST" align="center"><%=strMstatusName==null?"":strMstatusName%></td>
     <td class="TD_LIST" align="center"><%=strBatch==null?"":strBatch%></td>    
     <td class="TD_LIST" align="center"><%=strPrintDate==null?"":strPrintDate%></td>
     <td class="TD_LIST" align="center" <%=fAssPNum != fPNum ? "style='background-color:#AABB45' " : ""%>><%=(int)fPNum%></td>
     <td class="TD_LIST" align="center" <%=fAssPNum != fPNum ? "style='background-color:#AABB45' " : ""%>><%=(int)fAssPNum%></td>
    <tr>
<%		
		}

	}
%>
   
     <td height="100%" colspan="9"  valign="top">
     <div style="color: red;font-size:12;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
 
</div>
</body>
</html>
