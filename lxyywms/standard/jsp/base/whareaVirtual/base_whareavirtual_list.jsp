<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWhareaVirtual" %>
<html>
<head>
<title>库区信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//设置单选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
			
		//改变背景色
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//双击修改行
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseWhareaAction&flag=2&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseWhareaAction&method=ricoExecEdit" + result;
		}
	}
	
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

<body onload="OnLoad()"> 
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">虚拟库区编码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">虚拟库区名称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">所属仓库</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">账套编码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">ERP代码</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">隶属于</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseWhareaVirtual wharea = null; 
		String whAreaId;			// 库区ID
		String whAreaName;			// 库区名
		String warehousename;		// 仓库名
		String ERPCode;				// ERP代码
		String ERPAccount;			// ERP账套
		String belongTowhAreaName;		    //隶属于哪个库区

		for(int i=0; i<ls.size(); i++){
			wharea = (BaseWhareaVirtual)ls.get(i); 
                        
			whAreaId = wharea.getwhAreaId();				// 库区ID
			whAreaName = wharea.getwhAreaName();			// 库区名
			warehousename = wharea.getWarehousename();		// 仓库名
			ERPCode = wharea.getERPCode();					// ERP代码
			ERPAccount = wharea.getERPAccount();
			belongTowhAreaName = wharea.getBelongTowhAreaName();	// 隶属于哪个库区
		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=whAreaId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whAreaId == null ? "" : whAreaId%></td>
     <td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
     <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
     <td class="TD_LIST" align="center"><%=ERPAccount == null ? "" : ERPAccount%></td>
     <td class="TD_LIST" align="center"><%=ERPCode == null ? "" : ERPCode%></td>
     <td class="TD_LIST2" align="center" title="<%=belongTowhAreaName == null ? "" : belongTowhAreaName%>"><%=belongTowhAreaName == null ? "" : belongTowhAreaName%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="7" valign="top" class="TD_last_LIST">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>