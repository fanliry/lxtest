<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
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
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="30"><div class="list_title">选择</div></td>
     
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存毛重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存净重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存体积</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结毛重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结净重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结体积</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库时间</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">入库方式</div></td>
   </tr>
<%
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
	   // Object[] ob = null;
		InventoryStorage storage = null;
		//BaseCargospace cargospace = null;
		String whArea;				//库区
		String status;//库存状态
 		String cargoSpace;			//货位
 		String productcode;//产品编码
 	 	String product;				//产品
 	 	String owner;				//货主
 	 	String traycode;        	//托盘条码
		String punit;				//单位
		double pnum;            	//库存数量
		double pweight;         	//库存毛重
     	double pnetweight;      	//库存净重
     	double pvolume;         	//库存体积
		double holdnum;         	//冻结数量
		double holdweight;      	//冻结毛重
		double holdnetweight;      	//冻结净重
		double holdvolume;         	//冻结体积
		String indate;             	//入库时间
		String intype;             	//入库方式 1.联机 2.脱机
		
		String lotatt;  			//批次属性
		
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{ 	 	  
 	 	    //ob = (Object[])ls.get(i);
 	 	    storage = (InventoryStorage)ls.get(i);
 	 	   // BaseCargospace base=(BaseCargospace)ob[1];
  	 		whArea = storage.getWhAreaName();			//库区
  	 		cargoSpace = storage.getCargoSpaceName();		//货位
  	 		productcode = storage.getProductcode();//产品编码
  	 		product = storage.getProductName();		//产品
  	 		owner = storage.getOwnerName();			//货主
  	 		traycode = storage.getTraycode();		//托盘条码
  	 		punit = storage.getPunitname();			//单位
  	 		status = storage.getStatusName();//库存状态
  	 		pnum = storage.getPnum();			//库存数量
  	 		pweight = storage.getPweight();        //库存毛重
  	 		pnetweight = storage.getPnetweight();     //库存净重
  	 		pvolume = storage.getPvolume();        //库存体积
  	 		holdnum = storage.getHoldnum();		//冻结数量
  	 		holdweight = storage.getHoldweight();     //冻结毛重
			holdnetweight = storage.getHoldnetweight();  //冻结净重
			holdvolume = storage.getHoldvolume();     //冻结体积
			
			indate = storage.getIndate();       //入库时间
  	 		intype = storage.getIntype();		//入库方式 1.联机 2.脱机
  	 		if(intype=="1"){
  	 		    intype="联机";
  	 		}else{
  	 		     intype="脱机";
  	 		}
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=storage.getInventoryid()%>"/></td>
     
     <td class="TD_LIST" align="center"><%=whArea%></td>
      <td class="TD_LIST" align="center"><%=status%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=productcode%></td>
     <td class="TD_LIST" align="center"><%=product%></td>
     <td class="TD_LIST" align="center"><%=traycode%></td>
     <td class="TD_LIST" align="center"><%=punit%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
     <td class="TD_LIST" align="center"><%=pweight%></td>
     <td class="TD_LIST" align="center"><%=pnetweight%></td>
     <td class="TD_LIST" align="center"><%=pvolume%></td>
     <td class="TD_LIST" align="center"><%=holdnum%></td>
     <td class="TD_LIST" align="center"><%=holdweight%></td>
     <td class="TD_LIST" align="center"><%=holdnetweight%></td>
     <td class="TD_LIST" align="center"><%=holdvolume%></td>
     <td class="TD_LIST" align="center"><%=indate%></td>
     <td class="TD_LIST2" align="center"><%=intype%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
