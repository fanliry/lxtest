<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult" %>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
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
			
			var key = check_ids[i].value.split("|");
			parent.document.getElementById("checknum").value = key[1];
		}
	}
	
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
				parent.document.getElementById("checknum").value="0";
			}
			else{
				alert(back_msg);
			}
		}
		
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px">
     	<div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox"></div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">盘点数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">盘点时间</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">操作人</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">状态</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InventoryCheckResult checkresult = null;
		String checkid;			//ID
		String product;			//产品
		String lotnumber;	    //批号
		String traycode;		//托盘条码
		double num;				//库存数量
		double checknum;		//盘点数量
		String checktime;		//盘点时间
		String createman;		//操作人
		String statusName;		//状态
		
		String param = "";		//ID+盘点数量+盘点重量
		
		for(int i = 0; i < ls.size(); i++){
		
			checkresult = (InventoryCheckResult)ls.get(i);
			checkid = checkresult.getCheckid();			//ID
			product = checkresult.getProductName();		//产品
			lotnumber = checkresult.getLotnumber();	    //批号
    		traycode = checkresult.getTraycode(); 		//托盘条码
			num = checkresult.getNum();					//库存数量
			checknum = checkresult.getChecknum();		//盘点数量
			checktime = checkresult.getChecktime();		//盘点时间
			createman = checkresult.getCreateman();		//操作人
			statusName = checkresult.getStatusName();
			
			param = checkid + "|" + (int)checknum + "|end";
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center" width="50px">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=param%>">&nbsp;<%=i+1%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>  
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=(int)num%></td>
     <td class="TD_LIST" align="center"><%=(int)checknum%></td>
     <td class="TD_LIST" align="center"><%=checktime==null?"":checktime%></td>
     <td class="TD_LIST2" align="center"><%=createman==null?"":createman%></td>
     <td class="TD_LIST2" align="center"><%=statusName==null?"":statusName%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="9" valign="top" class="TD_last_LIST">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
     </td>
   </tr>
   </tbody> 
 </table>
 
</body>
</html>