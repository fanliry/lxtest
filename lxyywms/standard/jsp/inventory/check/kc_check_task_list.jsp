<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
  	
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
	
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}

</script>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1">
     	<div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox"></div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">申请单号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库单号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) {
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InventoryStorage storage = null;  
		String inventoryid;    	//库存ID
		String wharea;			//库区
		String cargospace;		//货位
		String product;			//产品
		String lotnumber;	    //批号
		String instockid;       //入库单
		String requestid;       //申请单
		String traycode;		//托盘条码
    	String productcode;     //产品条码
		double num;             //库存数量

		for(int i = 0; i < ls.size(); i++){
		
			storage = (InventoryStorage)ls.get(i);
			
			inventoryid = storage.getInventoryid();		//库存ID
			wharea = storage.getWhAreaName();			//库区
			cargospace = storage.getCargoSpaceName();	//货位
			product = storage.getProductName();			//产品
			lotnumber = storage.getLotinfo();		    //批号
			instockid = storage.getInstockid();         //入库单
			requestid = storage.getRequestid();         //申请单
			traycode = storage.getTraycode();			//托盘条码
    		productcode = storage.getProductcode(); 	//产品编码
			num = storage.getPnum();					//库存数量
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData();">
     <td class="TD_LIST1" align="center">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=inventoryid%>">&nbsp;<%=i+1%></td>
     <td class="TD_LIST" align="center"><%=wharea==null?"":wharea%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
     <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>
     <td class="TD_LIST" align="center"><%=requestid==null?"":requestid%></td>  
     <td class="TD_LIST" align="center"><%=instockid==null?"":instockid%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td> 
   </tr>
<%			
		}
	}
%>

	<tr>
      <td height="100%" colspan="10" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>
