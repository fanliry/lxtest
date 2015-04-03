<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseShift"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
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
	
	//修改双击行
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseShiftAction&flag=3&id="+id, "", "dialogWidth=450px; dialogHeight=320px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = parent.page.document.getElementById("lineviewrow").value;	//每页显示行数
			location.href = ac + "baseShiftAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	//页面登录
	function OnLoad(){
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>
<body onload="OnLoad()">

  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
      	<input type="checkbox" name="check_all" onclick="CheckAll()" class="input_checkbox">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">班次名称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">班次时间</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">出入类型</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">是否当班</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">负责人</div></td>
	  <td class="TD_LIST_TITLE2"><div class="list_title">当班人员</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseShift ta = null;
		String s0,s1,s2,s3,s4,s5,s6;
		for(int i = 0; i < ls.size(); i++){
			ta = (BaseShift)ls.get(i);
			
			s0 = ta.getM_strShiftId();
			s1 = ta.getM_strShiftName();
			s2 = ta.getM_strShiftDate();
			if(ta.getM_strIn_Out_Type().equals("0")){
				s3 = "入/出库";
			}else if(ta.getM_strIn_Out_Type().equals("1")){
				s3 = "入库";
			}else if(ta.getM_strIn_Out_Type().equals("2")){
				s3 = "出库";
			}else{
				s3 = "";
			}
			s4 = ta.getM_strIscurrent();
			s5 = ta.getM_strOp_Man_Id();
			s6 = ta.getM_strOndutyMen();
   %>	
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input name="check_id" type="checkbox" value="<%=s0%>" class="input_checkbox" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=s1==null?"":s1%></td>
     <td class="TD_LIST" align="center"><%=s2==null?"":s2%></td>
     <td class="TD_LIST" align="center"><%=s3%></td>
     <td class="TD_LIST" align="center"><%=s4==null?"":s4%></td>
     <td class="TD_LIST" align="center"><%=s5==null?"":s5%></td>
     <td class="TD_LIST2" align="center"><%=s6==null?"":s6%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
 
</body>
</html>

