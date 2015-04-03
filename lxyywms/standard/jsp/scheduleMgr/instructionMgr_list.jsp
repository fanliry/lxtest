<%@page import="com.wms3.bms.standard.entity.schedule.ScheduleTask"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>	
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
		}
	}
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != ""){
		 	if(back_msg != "Y")
		 	{
			    alert(back_msg);
		 	}else
		 	{
		 		alert("操作成功");
		 	}
		}
	}

</script>
</head>
<body   onload="javascript:OnLoad();">
 <table id="ty" width="130%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
  
   <tr height="10px">
     <td width="50" class="TD_LIST_TITLE"align="center"><div class="list_title">
     <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">调度任务编号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">调度类型</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">巷道</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">货位</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">托盘条码</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">任务号</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">任务状态</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">优先级</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">输送号</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">任务方向</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">执行路线</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">楼层</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">提升机号</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">创建时间</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">开始时间</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">执行时间 </div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">完成时间</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">指令格式</div></td>
   </tr>
   <%
   		List ls = (List)request.getAttribute("exResultList");
   		
   		String scheduleTaskId = "";    //调度任务编号
   		String scheduleType = "";      //调度类型
   		String scheduleTypeName = "";
   		String whAreaId = "";		   //库区
   		String whAreaName = "";
   		String alleyId = "";		   //巷道
   		String alleyName = "";
   		String carogoSpaceId = "";	   //货位
   		String carogoSpaceName = "";
   		String traycode = "";		   //托盘
   		String taskno = "";			   //任务号
   		String status = "";			   //状态
   		String statusName = "";
   		int priority = 0;			   //优先级
   		String snumber = "";		   //输送号
   		String taskpos = "";		   //任务方向
   		String taskposName = "";
   		String route = "";			   //路线
   		String createtime = "";		   //创建时间
   		String starttime = "";		   //开始时间
   		String asexectime = "";		   //执行时间
   		String finishtime = "";		   //完成时间
   		String commkind = "";		   //指令格式
   		
   		String floor = "";
   		String tsjh = "";
   		if(ls != null && ls.size() > 0)
   		{
   			for(int i = 0; i < ls.size(); i++)
   			{
   				ScheduleTask stask = (ScheduleTask)ls.get(i);
   				scheduleTaskId = stask.getTaskid();
   				scheduleType = stask.getTasktype();
   				if("1".equals(scheduleType))
   				{
   					scheduleTypeName = "入库";
   				}else if("2".equals(scheduleType))
   				{
   					scheduleTypeName = "出库";
   				}else
   				{
   					scheduleTypeName = "回流";
   				}
   				whAreaId = stask.getWhAreaId();
   				whAreaName = stask.getWhareaName();
   				alleyId = stask.getCargoAlleyId();
   				alleyName = stask.getCargoAlleyName();
   				carogoSpaceId = stask.getCargoSpaceId();
   				carogoSpaceName = stask.getCargoSpaceName();
   				traycode = stask.getTraycode();
   				taskno = stask.getTaskno();
   				status = stask.getStatus();
   				statusName = stask.getStatusName();
   				priority = stask.getPriority();
   				snumber = stask.getSnumber();
   				taskpos = stask.getTaskpos();
   				taskposName = stask.getTasktypeName();
   				route = stask.getRoute();
   				createtime = stask.getCreatetime();
   				starttime = stask.getStarttime();
   				asexectime = stask.getAsexectime();
   				finishtime = stask.getFinishtime();
   				commkind = stask.getCommkind();
   				
   				floor = stask.getFloor();
   				tsjh = stask.getTSJH();
    %>
   <tr onclick="setSel(<%=i%>)">
    <td class="TD_LIST1" align="center">
    	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=scheduleTaskId%>"><%=i+1%></td>
   	<td class="TD_LIST" align="center"><%=scheduleTaskId == null ? "" : scheduleTaskId%></td>
   	<td class="TD_LIST" align="center"><%=scheduleTypeName == null ? "" : scheduleTypeName%></td>
   	<td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
   	<td class="TD_LIST" align="center"><%=alleyName == null ? "" : alleyName%></td>
   	<td class="TD_LIST" align="center"><%=carogoSpaceName == null ? "" : carogoSpaceName%></td>
   	<td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
   	<td class="TD_LIST" align="center"><%=taskno == null ? "" : taskno%></td>
   	<td class="TD_LIST" align="center"><%=statusName == null ? "" : statusName%></td>
   	<td class="TD_LIST" align="center"><%=priority%></td>
   	<td class="TD_LIST" align="center"><%=snumber == null ? "" : snumber%></td>
   	<td class="TD_LIST" align="center"><%=taskposName == null ? "" : taskposName%></td>
   	<td class="TD_LIST" align="center"><%=route == null ? "" : route%></td>
   	<td class="TD_LIST" align="center"><%=floor == null ? "" : floor%></td>
   	<td class="TD_LIST" align="center"><%=tsjh == null ? "" : tsjh%></td>
   	<td class="TD_LIST" align="center"><%=createtime == null ? "" : createtime%></td>
   	<td class="TD_LIST" align="center"><%=starttime == null ? "" : starttime%></td>
   	<td class="TD_LIST" align="center"><%=asexectime == null ? "" : asexectime%></td>
   	<td class="TD_LIST" align="center"><%=finishtime == null ? "" : finishtime%></td>
   	<td class="TD_LIST" align="center"><%=commkind == null ? "" : commkind%></td>
   </tr>
   <%
   			}
   		}else{
			session.removeAttribute("paging");
		}
    %>
    <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>
