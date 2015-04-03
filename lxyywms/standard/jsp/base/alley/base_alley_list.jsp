<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseAlley" %>
<html>
<head>
<title>巷道信息</title>
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

		var result = showModalDialog(ac + "baseAlleyAction&flag=2&cargoAlleyId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseAlleyAction&method=ricoExecEdit" + result;
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
      <td class="TD_LIST_TITLE"><div class="list_title">巷道编码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">巷道名称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">所属仓库</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">所属库区</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">入库锁</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">出库锁</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">是否双升货位巷道</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		BaseAlley alley = null; 
		String cargoAlleyId;		// 巷道ID
		String cargoAlleyName;		// 巷道名
		String whAreaName;			// 库区名
		String warehousename;		// 仓库名
		String inlock;				// 入库锁
		String outlock;				// 出库锁
		String istwin;				// 是否双升货位巷道

		for(int i=0; i<ls.size(); i++){
			alley = (BaseAlley)ls.get(i); 
                        
			cargoAlleyId = alley.getCargoAlleyId();	
			cargoAlleyName = alley.getCargoAlleyName();		
			whAreaName = alley.getWhAreaName();	
			warehousename = alley.getWarehousename();
			inlock = alley.getInlock();
			outlock = alley.getOutlock();
			istwin = alley.getIstwin();
			
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=cargoAlleyId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=cargoAlleyId == null ? "" : cargoAlleyId%></td>
     <td class="TD_LIST" align="center"><%=cargoAlleyName == null ? "" : cargoAlleyName%></td>
     <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
     <td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
     <td class="TD_LIST" align="center"><%=inlock == null ? "" : inlock%></td>
     <td class="TD_LIST" align="center"><%=outlock == null ? "" : outlock%></td>
     <td class="TD_LIST2" align="center"><%=istwin == null ? "" : istwin%></td>
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