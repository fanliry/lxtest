<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseDevice" %>
<html>
<head>
<title>设备信息</title>
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
	
	//修改双击行
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseDeviceAction&flag=2&id="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseDeviceAction&method=ricoExecEdit" + result;
		}
	}
	
	//根据仓库获得库区的列表
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null ? "" : (String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">设备编号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">设备名称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">设备类型</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">所属仓库</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">所属库区</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">所属巷道</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		BaseDevice device = null; 
		String deviceid;		// 设备ID
		String deviceno;		// 设备编号
		String devicename;		// 设备名称
		String devicetypename;	// 设备类型名
		String warehousename;	// 所属仓库名
		String whAreaName;		// 所属库区名
		String alleyName;		// 所属巷道名
		

		for(int i=0; i<ls.size(); i++){
			device = (BaseDevice)ls.get(i); 
                        
			deviceid = device.getDeviceid();	
			deviceno = device.getDeviceno();		
			devicename = device.getDevicename();	
			devicetypename = device.getDevicetypename();	
			warehousename = device.getWarehousename();
			whAreaName = device.getWhAreaName();
			alleyName = device.getAlleyName();
			
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=deviceid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=deviceno == null ? "" : deviceno%></td>
     <td class="TD_LIST" align="center"><%=devicename == null ? "" : devicename%></td>
     <td class="TD_LIST" align="center"><%=devicetypename == null ? "" : devicetypename%></td>
     <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
     <td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
     <td class="TD_LIST2" align="center"><%=alleyName == null ? "" : alleyName%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="7" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>