<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace" %>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
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
	
	//改变背景
    function Change(obj){
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
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
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
  <table width="100%" height="100%"  id="theTable" border="0" cellspacing="0" cellpadding="0">
	<tr><td valign="top" height="100%">
	
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货位名</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货位状态</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货区</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">巷道</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">入库锁</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">出库锁</div></td>
    </tr>
<%
	
	if(ls != null && ls.size() > 0)
	{
		BaseCargospace cargospace = null;
		String strCargo_Space_Id = null;
		String strCargo_Space_Name = null;
		String strCsstatus = null;
		String strInLock = null;
		String strOutLock = null;
		String strCargo_Alley_Name = null;
		String strCarGo_Area_Nmae = null;
		String strWarehouseName = null;
		String strWh_Area_Nmae = null;
		
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 		cargospace = (BaseCargospace)ls.get(i);
  	 		
  	 		strCargo_Space_Id = cargospace.getCargoSpaceId();
			strCargo_Space_Name = cargospace.getCargoSpaceName();
			strCsstatus = cargospace.getCsstatusname();
			strInLock = cargospace.getInlock();
			strOutLock = cargospace.getOutlock();
			strCargo_Alley_Name = cargospace.getCargoAlleyName();
			strCarGo_Area_Nmae = cargospace.getCargoAreaName();
			strWarehouseName = cargospace.getWarehousename();
			strWh_Area_Nmae = cargospace.getWhAreaName();
 
%>
   <tr onMouseOver="this.bgColor='#E3F2FF'" onMouseOut="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
     	<input type="checkbox" class="input_checkbox" name="check_id" value="<%=strCargo_Space_Id%>" onClick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=strCargo_Space_Id == null ? "" : strCargo_Space_Id%></td>
     <td class="TD_LIST" align="center"><%=strCargo_Space_Name == null ? "" : strCargo_Space_Name%></td>
     <td class="TD_LIST" align="center"><%=strCsstatus == null ? "" : strCsstatus%></td>
     <td class="TD_LIST" align="center"><%=strCarGo_Area_Nmae == null ? "" : strCarGo_Area_Nmae%></td>
     <td class="TD_LIST" align="center"><%=strCargo_Alley_Name == null ? "" : strCargo_Alley_Name%></td>
     <td class="TD_LIST" align="center"><%=strWh_Area_Nmae == null ? "" : strWh_Area_Nmae%></td>
     <td class="TD_LIST" align="center"><%=strWarehouseName == null ? "" : strWarehouseName%></td>
     <td class="TD_LIST" align="center"><%=strInLock == null ? "" : strInLock%></td>
     <td class="TD_LIST2" align="center"><%=strOutLock == null ? "" : strOutLock%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("cspaging");
	}
%>
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
  <!-- ======== 分页标签 ======== -->
 </td><tr>
 <tr><td>
   <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	 <tr><td height="25px">
	   
     </td><tr>
   </table>
 </td><tr>
</table>
 
</body>
</html>
