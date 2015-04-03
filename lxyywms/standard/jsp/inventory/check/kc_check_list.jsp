<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest"%>

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
			queryDetail(check_ids[i].value);
		}
	}
	
	//查询盘点任务信息
	function queryDetail(id){
	
		parent.detail.location.href = ac + "inventoryCheckAction&flag=2&requestid=" + id;
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
		
		parent.RemoveLoading();
		parent.page.location.reload();
	}

</script>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1">
     	<div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox"></div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">类型</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进场编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">创建时间</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">创建人</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) {
	
		InventoryCheckRequest req = null;  
		
		String requestid;		//ID
		String type;			//类型
		String status;			//状态
		int priority;			//优先级
		String strPriority;
		String warehouseid;		//仓库
		String wharea;			//库区
		String product;			//产品
		String lotnumber;	    //批号
		String traycode;		//托盘条码
    	String productcode;     //产品条码
		String requesttime;		//申请时间
		String starttime;		//开始时间
		String endtime;			//结束时间
		String createman;		//创建人

		for(int i = 0; i < ls.size(); i++){
		
			req = (InventoryCheckRequest)ls.get(i);
			
			requestid = req.getRequestid();		//ID
			type = req.getTypeName();			//类型
			status = req.getStatusName();		//状态
			priority = req.getPriority();		//优先级
			warehouseid = req.getWarehouseid();	//货位
			wharea = req.getWhAreaName();		//库区
			product = req.getProductName();		//产品
			lotnumber = req.getLotinfo();		//批号
			traycode = req.getTraycode();		//托盘条码
    		productcode = req.getProductcode(); //产品条码
			requesttime = req.getRequesttime();	//申请时间
			starttime = req.getStarttime();		//开始时间
			endtime = req.getEndtime();			//结束时间
			createman = req.getCreateman();		//创建人
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData();">
     <td class="TD_LIST1" align="center">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=requestid%>">&nbsp;<%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseid==null?"":warehouseid%></td>
     <td class="TD_LIST" align="center"><%=wharea==null?"":wharea%></td>
     <td class="TD_LIST" align="center"><%=type==null?"":type%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>   
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
     <td class="TD_LIST" align="center"><%=requesttime==null?"":requesttime%></td>
     <td class="TD_LIST2" align="center"><%=createman==null?"":createman%></td>  
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="15 + iLine" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>
