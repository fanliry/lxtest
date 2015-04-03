<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
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

<body   onload="javascript:OnLoad();">
<%
	List list = null;
    String inventoryNCId = (String)request.getAttribute("inventoryNCId");
	if(request.getAttribute("exResultList") != null) 
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
  <table width="200%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr class="list_title_tr">
	  <%--<td width="40" class="TD_LIST_TITLE">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll('myform','checkbox_all','checkbox_id')"></td>--%>
	  <td class="TD_LIST_TITLE" style="width: 50"> <div class="list_title">行号</div></td> 
	  <td class="TD_LIST_TITLE"><div class="list_title">状态</div></td>
	 
	  <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">源产品</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">现产品</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">源单位</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">现单位</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">源生产日期</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">现生产日期</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">源批号类型</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">现批号类型</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">源批号信息</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">现批号信息</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">源托盘条码</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">现托盘条码</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">系统数量</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">实盘数量</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">调整数量</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">创建时间</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">调整时间</div></td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();         
		nbf.setMinimumFractionDigits(2);
		InventoryAdjustDetail detail=null;
		String id;			    //详细id
		String status;			//状态（1，未调整 2，已调整）
		String cargo_space_id;	//货位ID
		String wh_area_id;		//库区ID
		String warehouseid;		//仓库ID
		String spunit;			//源计量单位
		String tpunit;			//现计量单位
		String sproductid;		//源产品ID
		String tproductid;		//现产品ID
		String sprintdate;		//源产品生产日期
		String tprintdate;		//现产品生产日期
		String slotid;			//源批号类型
		String tlotid;			//现批号类型
		String slotstr;		//源批号类型
		String tlotstr;		//现批号类型
		String slotinfo;
		String tlotinfo;
		String straycode;		//源托盘条码
		String ttraycode;		//现托盘条码
		double syspnum;			//系统数量
		double realitypnum;		//实际数量
		String jobid;			//作业ID
		String createtime;		//创建时间
		String adjusttime;		//调整时间
		String inventoryid;		//调整类型单ID
		double adjustnum;       //调整数量
        for(int i=0; i<list.size(); i++)
        {
             detail = (InventoryAdjustDetail)list.get(i); 
             id = detail.getAdjustdetailid();
             status = detail.getStatusname();
        	 jobid = detail.getJobid();
        	 wh_area_id = detail.getWh_area_id();
        	 cargo_space_id = detail.getCargo_space_id();	
        	 sproductid = detail.getSproductname();
        	 tproductid = detail.getTproductname();
        	 spunit = detail.getSpunit();
        	 tpunit = detail.getTpunit();
        	 sprintdate = detail.getSprintdate();
        	 tprintdate = detail.getTprintdate();
        	 slotid = detail.getSlotid();
        	 tlotid = detail.getTlotid();
        	 slotstr = detail.getSlotName();
        	 tlotstr = detail.getTlotName();
        	 slotinfo = detail.getSlotinfo();
        	 tlotinfo =  detail.getTlotinfo();
        	 straycode = detail.getStraycode();
        	 ttraycode = detail.getTtraycode();
        	 syspnum = detail.getSyspnum();
        	 realitypnum = detail.getRealitypnum();
        	 adjustnum = Math.abs(syspnum-realitypnum);
        	 createtime = detail.getCreatetime(); 	//创建时间
        	 adjusttime = detail.getAdjusttime();
        	 inventoryid = detail.getInventoryid();//调整类型单ID
%>	
	<tr class="list_normal_tr" >
	  <td class="TD_LIST" align="center"><input type="checkbox" name="checkbox_id" value="<%=id%>" class="input_checkbox"><%=i+1%></td>
	  <td class="TD_LIST" align="center"><%=status == null ? "&nbsp;" : status %></td>
	  
	  <td class="TD_LIST" align="center"><%=wh_area_id == null ? "&nbsp;" : wh_area_id%></td>
	  <td class="TD_LIST" align="center"><%=cargo_space_id == null ? "&nbsp;" : cargo_space_id%></td>
	  <td class="TD_LIST" align="center"><%=sproductid == null ? "&nbsp;" : sproductid%></td>
	  <td class="TD_LIST" align="center"><%=tproductid == null ? "&nbsp;" : tproductid%></td>
	  <td class="TD_LIST" align="center"><%=spunit == null ? "&nbsp;" : spunit%></td>
	  <td class="TD_LIST" align="center"><%=tpunit == null ? "&nbsp;" : tpunit%></td>
	  <td class="TD_LIST" align="center"><%=sprintdate == null ? "&nbsp;" : sprintdate%></td>
	  <td class="TD_LIST" align="center"><%=tprintdate == null ? "&nbsp;" : tprintdate%></td>
	  <td class="TD_LIST" align="center"><%=slotstr == null ? "&nbsp;" : slotstr%></td>
	  <td class="TD_LIST" align="center"><%=tlotstr == null ? "&nbsp;" : tlotstr%></td>
	  <td class="TD_LIST" align="center"><%=slotinfo == null ? "&nbsp;" : slotinfo%></td>
	  <td class="TD_LIST" align="center"><%=tlotinfo == null ? "&nbsp;" : tlotinfo%></td>
	  <td class="TD_LIST" align="center"><%=straycode == null ? "&nbsp;" : straycode%></td>
	  <td class="TD_LIST" align="center"><%=ttraycode == null ? "&nbsp;" : ttraycode%></td>
	  <td class="TD_LIST" align="center"><%=syspnum%></td>
	  <td class="TD_LIST" align="center"><%=realitypnum%></td>
	  <td class="TD_LIST" align="center"><%=adjustnum%></td>
	  <td class="TD_LIST" align="center"><%=createtime == null ? "&nbsp;" : createtime%></td>
	  <td class="TD_LIST" align="center"><%=adjusttime == null ? "&nbsp;" : adjusttime%></td>
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("page");
	}
%>	
	<tr height="100%">
	  <td height="100%" colspan="<%=13%>" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>

</form>
</body>
</html>
