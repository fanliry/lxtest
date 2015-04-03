<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>

<html>
<head>
  <title>仓储配送管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript">
  <!--
	//获取明细
    function getDetail(str)
	{
		var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecDetail&id=" + str;
		
		window.parent.myIframe2.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
    function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
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

<body  onload="javascript:OnLoad();">
<%
	List list = null;
	if(request.getAttribute("exResultList") != null)
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
   <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td class="TD_LIST_TITLE"><div class="list_title">
	    行号</div>
	  </td>
	  <td class="TD_LIST_TITLE"><div class="list_title">调整单编号</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">调整类型</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">状态</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>  
	  <td class="TD_LIST_TITLE"><div class="list_title">调整原因</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">原因描述</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">创建时间</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">审核时间</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">调整时间</div></td> 

	  <td class="TD_LIST_TITLE"><div class="list_title">创建人</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">审核人</div></td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
        for(int i=0; i<list.size(); i++)
        {
        	InventoryAdjustHeader adjust = (InventoryAdjustHeader)list.get(i);
        	String strId = adjust.getAdjustid();		//ID
        	String strwharehouseid = adjust.getWarehouseid();		//仓库
        	String strWhname = adjust.getWarehousename();
        	String strAdjusttypename = adjust.getAdjusttypename();//调整类型
        	String strAdjusttype = adjust.getAdjusttype();//调整类型
        	String strEdittime = adjust.getCreatetime();	//创建时间
        	String strAdjustTime = adjust.getAdjusttime();	//调整时间
        	String strReasonCode = adjust.getAdjustreasonname();//调整原因
        	String strReasonDescr = adjust.getReasondiscr();//原因描述
            String strStatusName = adjust.getAdjuststatusname();//调整状态
            String strAuditTime = adjust.getAuditdate();//审核时间
            String strAuditMan = adjust.getAuditmanname();//审核人
            String strCreateMan = adjust.getCreatemanname();//创建人
            String strStatusid = adjust.getStatus();
        	String values = strId+"&wharehouseid="+strwharehouseid+"&adjusttype="+strAdjusttype+"&status="+strStatusid;
%>
	<tr  onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''"  onclick="getDetail('<%=strId%>')">
	  <td class="TD_LIST1">
	    <input type="checkbox" name="checkbox_id" value="<%=values%>"  class="input_checkbox" alt="<%=i+1%>">
	  </td>
	  <td class="TD_LIST1" align="center"><%=strId%></td>
	  <td class="TD_LIST1" align="center"><%=strAdjusttypename == null ? "&nbsp;" : strAdjusttypename%></td>
	  <td class="TD_LIST1" align="center"><%=strStatusName == null ? "&nbsp;" : strStatusName%><input type="hidden" name="status" value="<%=strStatusid %>"></td>
	  <td class="TD_LIST1" align="center"><%=strWhname == null ? "&nbsp;" : strWhname%></td>
	  <td class="TD_LIST1" align="center"><%=strReasonCode == null ? "&nbsp;" : strReasonCode%></td> 
	  <td class="TD_LIST1" align="center"><%=strReasonDescr == null ? "&nbsp;" : strReasonDescr%></td> 
	  <td class="TD_LIST1" align="center"><%=strEdittime == null ? "&nbsp;" : strEdittime%></td> 
	  <td class="TD_LIST1" align="center"><%=strAuditTime == null ? "&nbsp;" : strAuditTime%></td> 
	  <td class="TD_LIST1" align="center"><%=strAdjustTime == null ? "&nbsp;" : strAdjustTime%></td> 
	  <td class="TD_LIST1" align="center"><%=strCreateMan == null ? "&nbsp;" : strCreateMan%></td> 
	  <td class="TD_LIST1" align="center"><%=strAuditMan == null ? "&nbsp;" : strAuditMan%></td> 
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
    <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
