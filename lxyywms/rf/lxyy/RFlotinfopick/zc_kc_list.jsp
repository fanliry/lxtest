<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%
	List ls = (List)request.getAttribute("exResultList");
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
-->
</script>
</head>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table  id="theTable" width="110%" height="110%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进厂编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
   </tr>
<%
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
	    InventoryStorage storage = null;
		String whArea;				//库区
 	 	String product;				//产品
 	 	String lotinfo;		    	//进厂编号
 	 	String traycode;        	//托盘条码
		double pnum;            	//库存数量
        String productcode;         //产品编码
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 	    storage = (InventoryStorage)ls.get(i);
  	 		whArea = storage.getWhAreaName();		//库区
  	 		product = storage.getProductName();		//产品
  	 		lotinfo = storage.getLotinfo();			//进厂编号
  	 		traycode = storage.getTraycode();		//托盘条码
  	 		productcode = storage.getProductcode(); //产品编码		
			pnum = storage.getPnum();           	//库存数量
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onClick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center"><input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=storage.getInventoryid()%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><input type="hidden" name="kcNum" value="<%=pnum %>"></input><input type="text" name="checkNum" value="<%=pnum %>"></input></td>
     <td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="<%=8%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
