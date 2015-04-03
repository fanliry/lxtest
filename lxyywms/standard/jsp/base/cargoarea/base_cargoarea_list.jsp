<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargoarea" %>
<html>
<head>
<title>货区信息</title>
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

		var result = showModalDialog(ac + "baseCargoareaAction&flag=2&cargoAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseCargoareaAction&method=ricoExecEdit" + result;
		}
	}
-->
</script>
</head>

<body>
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货区编码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">货区名称</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">所属仓库</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseCargoarea cargoarea = null; 
		String cargoAreaId;			// 货区ID
		String cargoAreaName;		// 货区名
		String warehousename;		// 仓库名

		for(int i=0; i<ls.size(); i++){
			cargoarea = (BaseCargoarea)ls.get(i); 
                        
			cargoAreaId = cargoarea.getCargoAreaId();		
			cargoAreaName = cargoarea.getCargoAreaName();	
			warehousename = cargoarea.getWarehousename();		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=cargoAreaId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=cargoAreaId == null ? "" : cargoAreaId%></td>
     <td class="TD_LIST" align="center"><%=cargoAreaName == null ? "" : cargoAreaName%></td>
     <td class="TD_LIST2" align="center"><%=warehousename == null ? "" : warehousename%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="4" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>